public class Lutador {

    private int id;
    private Time time;
    private int valorDeDano;
    private int numeroPontosVida;
    private int valorBaseIniciativa;

    public Lutador(int id, Time time, int valorDeDano, int valorBaseIniciativa) {
        this.id = id;
        this.time = time;
        this.valorDeDano = valorDeDano;
        this.numeroPontosVida = 100;
        setValorBaseIniciativa(valorBaseIniciativa);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getValorDeDano() {
        return valorDeDano;
    }

    public void setValorDeDano(int valorDeDano) {
        this.valorDeDano = valorDeDano;
    }

    public int getNumeroPontosVida() {
        return numeroPontosVida;
    }

    public void setNumeroPontosVida(int numeroPontosVida) {
        this.numeroPontosVida = numeroPontosVida;
    }

    public int getValorBaseIniciativa() {
        return valorBaseIniciativa;
    }

    public void setValorBaseIniciativa(int valorBaseIniciativa) {
        if (valorBaseIniciativa < 1 || valorBaseIniciativa > 100) {
            throw new IllegalArgumentException();
        } else {
            this.valorBaseIniciativa = valorBaseIniciativa;
        }
    }

    public String toString () {
        String resultado = "-------------------";
        resultado += "\nID: "+ getId();
        resultado += "\nTime: "+ getTime().getNome();

        return resultado;
    }
}
