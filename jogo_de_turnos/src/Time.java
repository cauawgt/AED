public class Time {

    private String nome;
    private Lutador[] lutadores;
    private Lutador[] lutadoresMortos;
    private int ultimo_vivos;
    private int ultimo_mortos;

    public Time(String nome) {
        this.nome = nome;
        this.lutadores = new Lutador[100];
        this.lutadoresMortos = new Lutador[100];
        ultimo_vivos = 0;
        ultimo_mortos = 0;
    }

    public int getUltimo_mortos() {
        return ultimo_mortos;
    }

    public void setUltimo_mortos(int ultimo_mortos) {
        this.ultimo_mortos = ultimo_mortos;
    }

    public Lutador[] getLutadores() {
        Lutador[] resultado = new Lutador[ultimo_vivos];
        for (int i = 0; i < ultimo_vivos; i++) {
            resultado[i] = lutadores[i];
        }
        return resultado;
    }

    public void setLutadores(Lutador lutador) {
        lutadores[ultimo_vivos] = lutador;
        ultimo_vivos = ultimo_vivos + 1;
    }
    public void setLutadoresFila(Lutador lutador, int indice) {
        lutadores[indice] = lutadores[indice + 1];
    }
    public void setLutadorRecolocadoNaFila(Lutador lutador, int indice) {
        lutadores[indice] = lutador;
    }


    public int getUltimo_vivos() {
        return ultimo_vivos;
    }

    public void setUltimo_vivos(int quantidadeLutaroes) {
        this.ultimo_vivos = quantidadeLutaroes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Lutador[] getLutadoresMortos() {
        return lutadoresMortos;
    }

    public void setLutadoresMortos(Lutador lutador) {
        this.lutadoresMortos[ultimo_mortos] = lutador;
        ultimo_mortos = ultimo_mortos + 1;
    }

    public void resetAtaquesTime () {
        for (int i = 0; i < this.ultimo_vivos; i++) {
            lutadores[i].resetQuantidadeAtaques();
        }
    }

    public void ordenandoIniciativaDecrescente() {
        if (lutadores != null && this.ultimo_vivos > 1) {
            for (int i = 1; i < this.ultimo_vivos; i++) {
                Lutador chave = this.lutadores[i]; // Guardar o lutador atual como chave
                int j = i - 1;

                // Comparar e mover os lutadores com base na iniciativa (ordem decrescente)
                while (j >= 0 && this.lutadores[j].getValorBaseIniciativa() < chave.getValorBaseIniciativa()) {
                    this.lutadores[j + 1] = this.lutadores[j]; // Move o lutador para frente
                    j = j - 1;
                }
                this.lutadores[j + 1] = chave; // Inserir o lutador na posição correta
            }
        }

        if (lutadoresMortos != null && this.ultimo_mortos > 1) {
            for (int i = 1; i < this.ultimo_mortos; i++) {
                Lutador chave = this.lutadoresMortos[i]; // Guardar o lutador atual como chave
                int j = i - 1;

                // Comparar e mover os lutadores com base na iniciativa (ordem decrescente)
                while (j >= 0 && this.lutadoresMortos[j].getValorBaseIniciativa() < chave.getValorBaseIniciativa()) {
                    this.lutadoresMortos[j + 1] = this.lutadoresMortos[j]; // Move o lutador para frente
                    j = j - 1;
                }
                this.lutadoresMortos[j + 1] = chave; // Inserir o lutador na posição correta
            }
        }
    }


    public String toString() {

        ordenandoIniciativaDecrescente();

        String resultado = "";
        resultado += "--------------------------";
        resultado += "\nLUTADORES VIVOS: " + getUltimo_vivos();
        resultado += "\n";
        for (int i = 0; i < ultimo_vivos; i++) {
            resultado += "ID: " + this.lutadores[i].getId() + "| Iniciativas: " + this.lutadores[i].getValorBaseIniciativa() +
                    "| Vida: " + this.lutadores[i].getNumeroPontosVida();
            resultado += "\n";
        }
        resultado += "--------------------------";
        resultado += "\nLUTADORES MORTOS: " + getUltimo_mortos();
        resultado += "\n";
        for (int i = 0; i < ultimo_mortos; i++) {
            resultado += "ID: " + this.lutadoresMortos[i].getId() + "| Iniciativas: " + this.lutadoresMortos[i].getValorBaseIniciativa() +
                    "| Vida: " + this.lutadoresMortos[i].getNumeroPontosVida();
            resultado += "\n";
        }


        return resultado;
    }
}
