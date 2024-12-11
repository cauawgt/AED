import java.util.Scanner;

public class SistemaDeControle {

    private Lutador[] todosOsLutadores;
    private Time time1;
    private Time time2;
    private int ultimoListaTodos;

    public SistemaDeControle(Time time1, Time time2) {
        this.todosOsLutadores = new Lutador[100];
        this.time1 = time1;
        this.time2 = time2;
        this.ultimoListaTodos = 0;
    }

    public int getUltimoListaTodos() {
        return ultimoListaTodos;
    }

    public void setUltimoListaTodos(int ultimoListaTodos) {
        this.ultimoListaTodos = ultimoListaTodos;
    }

    public Lutador[] getTodosOsLutadores() {
        return todosOsLutadores;
    }

    public void setTodosOsLutadores(Lutador[] todosOsLutadores) {
        this.todosOsLutadores = todosOsLutadores;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    // Função de Buscar indice de ID
    public int buscarIndiceId(int id) {
        int indice = -1;
        int n = ultimoListaTodos;
        int i = 0;
        // Verificando se existe esse id no time 1
        while (i < n) {
            if (todosOsLutadores != null && todosOsLutadores[i].getId() == id) {
                indice = i;
                i = n + 1;
            }
            else {
                i = i + 1;
            }
        }

        return indice;
    }

    // Função de Buscar ID
    public int buscarId(int id) {
        int indice = buscarIndiceId(id);
        if (indice > -1) {
            return todosOsLutadores[indice].getId();
        } else {
            return -1;
        }
    }

    // Função de Adicionar jogador
    public void inserirJogador (Lutador lutador, int timeEscolhido) {
        int n = ultimoListaTodos;
        if (n < 100) {
            int verificador = buscarIndiceId(lutador.getId());
            if (verificador == -1) {
                todosOsLutadores[n] = lutador;
                n = n + 1;
                ultimoListaTodos = n;

                if (timeEscolhido == 1) {
                    time1.setLutadores(lutador);
                } else {
                    time2.setLutadores(lutador);
                }

            }
            else {
                System.out.println("\n### Erro na inserção. Esse lutador já existe! ###");
            }
        }
        else {
            System.out.println("Lista cheia.");
        }
    }

    // Função De Remover
    public Lutador removerLutador (int id) {
        Lutador removido = null;
        int n = ultimoListaTodos;
        int verificador = buscarIndiceId (id);

        // Removendo da Lista total
        if (verificador != -1) {
            if (todosOsLutadores[verificador].getNumeroPontosVida() > 0) {
                removido = todosOsLutadores[verificador];
                for (int i = verificador; i < n-1; i++) {
                    todosOsLutadores[i] = todosOsLutadores[i + 1];
                }
                ultimoListaTodos = n - 1;

                // Removendo do time1
                if (todosOsLutadores[verificador].getTime() == time1) {
                    Lutador removidoTime = null;
                    int indice = -1;
                    for (int i = 0; i < time1.getUltimo_vivos(); i++) {
                        if (todosOsLutadores[verificador].getId() == time1.getLutadores()[i].getId()) {
                            indice = i;
                        }
                    }

                    if (indice != -1) {
                        int m = time1.getUltimo_vivos();
                        removidoTime = time1.getLutadores()[indice];
                        for (int i = indice; i < m-1; i++) {
                            time1.getLutadores()[i] = time1.getLutadores()[i + 1];
                        }
                        time1.setUltimo_vivos(m-1);
                    }
                }
                else {
                    Lutador removidoTime = null;
                    int indice = -1;
                    for (int i = 0; i < time2.getUltimo_vivos(); i++) {
                        if (todosOsLutadores[verificador].getId() == time2.getLutadores()[i].getId()) {
                            indice = i;
                        }
                    }

                    if (indice != -1) {
                        int m = time2.getUltimo_vivos();
                        removidoTime = time2.getLutadores()[indice];
                        for (int i = indice; i < m-1; i++) {
                            time2.getLutadores()[i] = time2.getLutadores()[i + 1];
                        }
                        time2.setUltimo_vivos(m-1);
                    }
                }
            }
            else {
                System.out.println("Lutador Morto.");
            }

        }
        else {
            System.out.println("Lutador não existe.");
        }

        return removido;
    }


    // Organizacao Dos Times
    public void OrganizacaoDosTImes() {
        Scanner input = new Scanner(System.in);
        int loop = 1;
        while (loop == 1) {
            System.out.println("\n*** Fase de Organização dos Times ***\n");
            System.out.println("(1) - Inserção de lutadores em times");
            System.out.println("(2) - Relatório de Status de um time");
            System.out.println("(3) - Fuga de Lutador");
            System.out.println("(4) - Ir para próxima fase");
            System.out.print("\nSelecione:");
            int escolha = input.nextInt();

            if (escolha == 1) {
                loop = 2; // Primeira opção do menu: Inserção de lutadores em times
                while (loop == 2) {
                    System.out.println("\n*** Inserção de Lutadores ***\n");
                    System.out.println("(1) - Inserir Jogador");
                    System.out.println("(2) - Voltar");
                    System.out.print("\nSelecione:");
                    int escolha2 = input.nextInt();

                    // Inserção de lutadores em times
                    if (escolha2 == 1) {
                        System.out.println("INSIRA UM NOVO LUTADOR");
                        System.out.println("===============================");
                        System.out.print("Id: ");
                        int id = input.nextInt();
                        System.out.print("Time (1) ou (2): ");
                        int escolhaTime = input.nextInt();
                        System.out.print("Valor de Dano: ");
                        int valorDano = input.nextInt();
                        System.out.print("Valor base de iniciativa: ");
                        int valorBaseIniciativa = input.nextInt();
                        System.out.println("===============================");


                        if (escolhaTime == 1) {
                            Lutador lutadorCriado = new Lutador(id, this.time1, valorDano, valorBaseIniciativa);
                            inserirJogador(lutadorCriado, escolhaTime);
                        } else {
                            Lutador lutadorCriado = new Lutador(id, this.time2, valorDano, valorBaseIniciativa);
                            inserirJogador(lutadorCriado, escolhaTime);
                        }


                    }
                    else {
                        loop = 1;
                    }
                }

            }

            else if (escolha == 2) {
                loop = 3; // Segunda opção do menu: Relatório de status de um time
                while (loop == 3) {
                    System.out.println("\n*** Relatório de Status ***");
                    System.out.println("(1) - Time 1");
                    System.out.println("(2) - Time 2");
                    System.out.println("(3) - voltar");
                    System.out.print("\nSelecione:");
                    int escolha3 = input.nextInt();

                    if (escolha3 == 1) {
                        System.out.println(time1);
                    }
                    else if (escolha3 == 2) {
                        System.out.println(time2);
                    }
                    else if (escolha3 == 3) {
                        loop = 1; // Volta ao menu principal da Fase 1
                    }
                    else {
                        System.out.println("(Não temos essa opção!)");
                    }
                }

            }
            else if (escolha == 3) {
                loop = 4; // 3 Opção do menu: Remover Lutador que esteja vido
                while (loop == 4) {
                    System.out.println("\n*** Remover um Lutador Vivo ***");
                    System.out.println("(1) - Inserir ID");
                    System.out.println("(2) - Voltar");
                    System.out.print("\nSelecione:");
                    int escolha4 = input.nextInt();

                    if (escolha4 == 1) {
                        System.out.println("\nREMOÇÃO DE LUTADAOR");
                        System.out.println("===============================");
                        System.out.print("ID:");
                        int idLutadorRemovido = input.nextInt();

                        Lutador removido = removerLutador(idLutadorRemovido);
                        if (removido != null) {
                            System.out.printf("\nLutador com id (%d) foi removido. \n", removido.getId());
                        }
                        System.out.println("===============================");



                    }
                    else if (escolha4 == 2) {
                        loop = 1;
                    }
                    else {
                        System.out.println("Opção Inválida.");
                    }
                }
            }
            else if (escolha == 4) {

            }
            else {

            }

        }
    }


}
