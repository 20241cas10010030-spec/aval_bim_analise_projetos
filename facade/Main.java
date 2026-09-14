public class Main {
    public static void main(String[] args) {
        String tabela = "Cliente";

        HelperFacade.gerarRelatorio(
                HelperFacade.TipoBanco.MYSQL,
                HelperFacade.TipoRelatorio.HTML,
                tabela);

        HelperFacade.gerarRelatorio(
                HelperFacade.TipoBanco.ORACLE,
                HelperFacade.TipoRelatorio.PDF,
                tabela);
    }
}

class MySqlHelper {
    public static String getMySqlDBConnection() {
        return "conexao MySQL";
    }

    public void gerarHTML(String tabela, String conexao) {
        System.out.println("MySQL - relatorio HTML da tabela " + tabela);
    }

    public void gerarPDF(String tabela, String conexao) {
        System.out.println("MySQL - relatorio PDF da tabela " + tabela);
    }
}

class OracleHelper {
    public static String getOracleDBConnection() {
        return "conexao Oracle";
    }

    public void gerarHTML(String tabela, String conexao) {
        System.out.println("Oracle - relatorio HTML da tabela " + tabela);
    }

    public void gerarPDF(String tabela, String conexao) {
        System.out.println("Oracle - relatorio PDF da tabela " + tabela);
    }
}

class HelperFacade {
    public static void gerarRelatorio(TipoBanco banco, TipoRelatorio relatorio,
            String tabela) {
        if (banco == TipoBanco.MYSQL) {
            String conexao = MySqlHelper.getMySqlDBConnection();
            MySqlHelper helper = new MySqlHelper();

            if (relatorio == TipoRelatorio.HTML) {
                helper.gerarHTML(tabela, conexao);
            } else {
                helper.gerarPDF(tabela, conexao);
            }
        } else {
            String conexao = OracleHelper.getOracleDBConnection();
            OracleHelper helper = new OracleHelper();

            if (relatorio == TipoRelatorio.HTML) {
                helper.gerarHTML(tabela, conexao);
            } else {
                helper.gerarPDF(tabela, conexao);
            }
        }
    }

    enum TipoBanco {
        MYSQL, ORACLE
    }

    enum TipoRelatorio {
        HTML, PDF
    }
}
