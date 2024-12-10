public class Main {
    public static void main(String[] args) {

        Time time1 = new Time("T1");
        Time time2 = new Time("GG");

        SistemaDeControle sistema = new SistemaDeControle(time1, time2);

        int fim_de_jogo = 0;
        while (fim_de_jogo == 0) {
            sistema.OrganizacaoDosTImes();
        }

    }
}