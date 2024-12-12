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
            } else {
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
    public void inserirJogador(Lutador lutador, int timeEscolhido) {
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

            } else {
                System.out.println("\n### Erro na inserção. Esse lutador já existe! ###");
            }
        } else {
            System.out.println("Lista cheia.");
        }
    }

    // Função De Remover
    public Lutador removerLutador(int id) {
        Lutador removido = null;
        int n = ultimoListaTodos;
        int verificador = buscarIndiceId(id);

        // Removendo da Lista total
        if (verificador != -1) {
            if (todosOsLutadores[verificador].getNumeroPontosVida() > 0) {
                removido = todosOsLutadores[verificador];
                for (int i = verificador; i < n - 1; i++) {
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
                        for (int i = indice; i < m - 1; i++) {
                            time1.getLutadores()[i] = time1.getLutadores()[i + 1];
                        }
                        time1.setUltimo_vivos(m - 1);
                    }
                } else {
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
                        for (int i = indice; i < m - 1; i++) {
                            time2.getLutadores()[i] = time2.getLutadores()[i + 1];
                        }
                        time2.setUltimo_vivos(m - 1);
                    }
                }
            } else {
                System.out.println("Lutador Morto.");
            }

        } else {
            System.out.println("Lutador não existe.");
        }

        return removido;
    }


    // FASE 1: Organizacao Dos Times
    public void organizacaoDosTImes() {
        Scanner input = new Scanner(System.in);
        int loop = 1;
        while (loop == 1) {
            System.out.println("\n*** FASE DE ORGANIZAÇÂO DOS TIMES ***\n");
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


                    } else {
                        loop = 1;
                    }
                }

            } else if (escolha == 2) {
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
                    } else if (escolha3 == 2) {
                        System.out.println(time2);
                    } else if (escolha3 == 3) {
                        loop = 1; // Volta ao menu principal da Fase 1
                    } else {
                        System.out.println("(Não temos essa opção!)");
                    }
                }

            } else if (escolha == 3) {
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


                    } else if (escolha4 == 2) {
                        loop = 1;
                    } else {
                        System.out.println("Opção Inválida.");
                    }
                }
            } else if (escolha == 4) {
                loop = 0; // Passar para a fase de Combate
                // Ordenando os times
                time1.ordenandoIniciativaDecrescente();
                time2.ordenandoIniciativaDecrescente();
            } else {
                System.out.println("Opção não idenficada.");

            }

        }

    }


    public void reorganizarTimeFila(Time time) {
        int n = time.getUltimo_vivos();
        if (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                time.setLutadoresFila(time.getLutadores()[i], i);
            }
        }


    }

    public void lutaDaVez(Lutador l1, Lutador l2) {
        if (l1.getNumeroPontosVida() > 0 && l2.getNumeroPontosVida() > 0) {
            // L1 ataca L2
            l2.setNumeroPontosVida(l2.getNumeroPontosVida() - l1.getValorDeDano());
            l1.crediteQuantidadeAtaques(); // Precisa ser zerado após o turno
            // L2 ataca L1
            l1.setNumeroPontosVida(l1.getNumeroPontosVida() - l2.getValorDeDano());
            l2.crediteQuantidadeAtaques(); // Precisa ser zerado após o turno
            System.out.printf("\nLutadores: %d x %d", l1.getId(), l2.getId());
            System.out.printf("\nDano causado: %d x %d", l1.getValorDeDano(), l2.getValorDeDano());
            System.out.printf("\nVida atual: %d x %d\n\n", l1.getNumeroPontosVida(), l2.getNumeroPontosVida());
        }
    }


    public void lutaEntreEquipes() {
        // Reseta a quantidae de ataques que todos os lutadores deram numa rodada passada (caso exista)
        time1.resetAtaquesTime();
        time2.resetAtaquesTime();

        if (time1 != null && time2 != null) {
            int todosLutadoresJaAtacaram = 0; // 0 - não; 1 - sim
            int timeTotalmenteMorto = 0;
            while (todosLutadoresJaAtacaram == 0 && timeTotalmenteMorto == 0) {
                Lutador lutadorDaVezTime1 = time1.getLutadores()[0];
                Lutador lutadorDaVezTime2 = time2.getLutadores()[0];

                reorganizarTimeFila(time1);
                reorganizarTimeFila(time2);

                lutaDaVez(lutadorDaVezTime1, lutadorDaVezTime2);

                // Se ele estiver vivo volta para o final da fila
                if (lutadorDaVezTime1.getNumeroPontosVida() > 0) {
                    time1.setLutadorRecolocadoNaFila(lutadorDaVezTime1, time1.getUltimo_vivos() - 1);
                }
                // Senão vai para lista de mortos
                else {
                    time1.setUltimo_vivos(time1.getUltimo_vivos() - 1); // remover o ultimo elemento
                    time1.setLutadoresMortos(lutadorDaVezTime1);
                }

                // Se ele estiver vivo volta para o final da fila
                if (lutadorDaVezTime2.getNumeroPontosVida() > 0) {
                    time2.setLutadorRecolocadoNaFila(lutadorDaVezTime2, time2.getUltimo_vivos() - 1);
                }
                // Senão vai para lista de mortos
                else {
                    time2.setUltimo_vivos(time2.getUltimo_vivos() - 1);
                    time2.setLutadoresMortos(lutadorDaVezTime2);
                }

                if (time1.getUltimo_vivos() == 0 || time2.getUltimo_vivos() == 0) {
                    timeTotalmenteMorto = 1;
                }

                // Verificar se todos os lutadores de cada time realizaram pelo menos 1 ataque
                int cont = 0;
                for (int i = 0; i < time1.getUltimo_vivos(); i++) {
                    if (time1.getLutadores()[i].getQuantidadeAtaques() >= 1) {
                        cont += 1;
                    }

                }
                for (int i = 0; i < time2.getUltimo_vivos(); i++) {
                    if (time2.getLutadores()[i].getQuantidadeAtaques() >= 1) {
                        cont += 1;
                    }
                }
                if (cont == time1.getUltimo_vivos() + time2.getUltimo_vivos()) {
                    todosLutadoresJaAtacaram = 1;
                }

            }

        } else {
            System.out.println("Um time está vazio.");
        }

    }


    // FASE 2: Fase De Combate
    public void faseDeCombate() {
        Scanner input = new Scanner(System.in);
        int loopFaseDeCombate = 1;
        while (loopFaseDeCombate == 1) {
            System.out.println("\n*** FASE DE COMBATE ***\n");
            System.out.println("(1) - Começar combate");
            System.out.println("(2) - voltar");

            System.out.print("\nSelecione: ");
            int escolha5 = input.nextInt();

            if (escolha5 == 1) {
                lutaEntreEquipes();
                System.out.println("BATALHA FINALIZADA!\n");
                loopFaseDeCombate = 0;
            } else {
                loopFaseDeCombate = 0;
            }

        }
    }

}
