public class Main {
    public static void main(String[] args) {
        Computador pc = ComputadorFactory.criar(
                new FabricaPC("8 GB", "500 GB", "3.0 GHz"));
        Computador servidor = ComputadorFactory.criar(
                new FabricaServidor("32 GB", "2 TB", "3.5 GHz"));

        System.out.println("PC: " + pc);
        System.out.println("Servidor: " + servidor);
    }
}

abstract class Computador {
    private final String ram;
    private final String hd;
    private final String cpu;

    protected Computador(String ram, String hd, String cpu) {
        this.ram = ram;
        this.hd = hd;
        this.cpu = cpu;
    }

    @Override
    public String toString() {
        return "RAM=" + ram + ", HD=" + hd + ", CPU=" + cpu;
    }
}

class PC extends Computador {
    public PC(String ram, String hd, String cpu) {
        super(ram, hd, cpu);
    }
}

class Servidor extends Computador {
    public Servidor(String ram, String hd, String cpu) {
        super(ram, hd, cpu);
    }
}

interface FabricaComputador {
    Computador criar();
}

class FabricaPC implements FabricaComputador {
    private final String ram;
    private final String hd;
    private final String cpu;

    public FabricaPC(String ram, String hd, String cpu) {
        this.ram = ram;
        this.hd = hd;
        this.cpu = cpu;
    }

    @Override
    public Computador criar() {
        return new PC(ram, hd, cpu);
    }
}

class FabricaServidor implements FabricaComputador {
    private final String ram;
    private final String hd;
    private final String cpu;

    public FabricaServidor(String ram, String hd, String cpu) {
        this.ram = ram;
        this.hd = hd;
        this.cpu = cpu;
    }

    @Override
    public Computador criar() {
        return new Servidor(ram, hd, cpu);
    }
}

class ComputadorFactory {
    public static Computador criar(FabricaComputador fabrica) {
        return fabrica.criar();
    }
}
