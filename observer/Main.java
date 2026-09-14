import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyTopic topic = new MyTopic();

        Observer observer1 = new MyTopicSubscriber("Observer 1");
        Observer observer2 = new MyTopicSubscriber("Observer 2");
        Observer observer3 = new MyTopicSubscriber("Observer 3");

        topic.register(observer1);
        topic.register(observer2);
        topic.register(observer3);

        observer1.setSubject(topic);
        observer2.setSubject(topic);
        observer3.setSubject(topic);

        observer1.update();
        topic.postMessage("Novo pedido recebido");
    }
}

interface Subject {
    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers();
    Object getUpdate(Observer observer);
}

interface Observer {
    void update();
    void setSubject(Subject subject);
}

class MyTopic implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private String message;
    private boolean changed;
    private final Object mutex = new Object();

    @Override
    public void register(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Observer nulo");
        }
        synchronized (mutex) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }
    }

    @Override
    public void unregister(Observer observer) {
        synchronized (mutex) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersCopy;
        synchronized (mutex) {
            if (!changed) {
                return;
            }
            observersCopy = new ArrayList<>(observers);
            changed = false;
        }
        for (Observer observer : observersCopy) {
            observer.update();
        }
    }

    @Override
    public Object getUpdate(Observer observer) {
        return message;
    }

    public void postMessage(String message) {
        System.out.println("Mensagem publicada: " + message);
        this.message = message;
        changed = true;
        notifyObservers();
    }
}

class MyTopicSubscriber implements Observer {
    private final String nome;
    private Subject topic;

    public MyTopicSubscriber(String nome) {
        this.nome = nome;
    }

    @Override
    public void update() {
        String message = (String) topic.getUpdate(this);
        if (message == null) {
            System.out.println(nome + ":: Nenhuma mensagem nova");
        } else {
            System.out.println(nome + ":: Consumindo mensagem::" + message);
        }
    }

    @Override
    public void setSubject(Subject subject) {
        this.topic = subject;
    }
}
