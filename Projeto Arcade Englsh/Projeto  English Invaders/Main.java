import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.ArrayList;
//import java.util.Random;

public class Main {
    
    public static void main(String[] arg){
//Variáveis____________________________________________________________________________________________________________________________________________________________________________________
    Scanner leitor = new Scanner(System.in);
    boolean Rodando_Menu = true;

    ArrayList<Words> listaPalavras = Arquivo.carregar();
    ArrayList<Player> listaJogadores = new ArrayList<>();

//Swing___________________________________________________________________________________________________________________________________________________________________________________
    JButton voltar_Menu = new JButton("Voltar");
    JFrame Janela = new JFrame("English Invaders");
        JButton start_botao = new JButton("Start Game");
            JFrame Starting_Game = new JFrame("English Invaders/Starting Game");
        JButton register_botao = new JButton("Register Words");
            JFrame Register_Words = new JFrame("English Invaders/Register Words");
        JButton show_botao = new JButton("Show Winners");
            JFrame Show_Winners = new JFrame("English Invaders/Show Winners");
        JButton clear_botao = new JButton("Clear");
            JFrame Clear = new JFrame("English Invaders/Clear");
    
    
    //janela menu
    Janela.setLayout(null);
    Janela.setBounds(0, 0, 800, 600);   
    Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    Janela.setVisible(true);

        start_botao.setBounds(300, 100, 200, 50);
        Janela.add(start_botao);
        start_botao.addActionListener(e -> {
            // Iniciar o jogo
            Starting_Game.setLayout(null);
            Starting_Game.setVisible(true);
            Starting_Game.setBounds(0, 0, 800, 600);   
            Starting_Game.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Janela.dispose();
            Starting_Game.add(voltar_Menu);
            voltar_Menu.setBounds(10, 10, 100, 30);
            
        });
        
        register_botao.setBounds(300, 150, 200, 50);
        Janela.add(register_botao);
        register_botao.addActionListener(e -> {
            // Registrar palavras
            Register_Words.setLayout(null);
            Register_Words.setVisible(true);
            Register_Words.setBounds(0, 0, 800, 600);   
            Register_Words.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Janela.dispose();
            Register_Words.add(voltar_Menu);
            voltar_Menu.setBounds(10, 10, 100, 30);
        });

        show_botao.setBounds(300, 200, 200, 50);
        Janela.add(show_botao);
        show_botao.addActionListener(e -> {
            // Mostrar vencedores
            Show_Winners.setLayout(null);
            Show_Winners.setVisible(true);
            Show_Winners.setBounds(0, 0, 800, 600);   
            Show_Winners.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Janela.dispose();
            Show_Winners.add(voltar_Menu);
            voltar_Menu.setBounds(10, 10, 100, 30);
        });

        clear_botao.setBounds(300, 250, 200, 50);
        Janela.add(clear_botao);
        clear_botao.addActionListener(e -> {
            // Limpar dados
            Clear.setLayout(null);  
            Clear.setVisible(true);
            Clear.setBounds(0, 0, 800, 600);   
            Clear.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Janela.dispose();
            Clear.add(voltar_Menu);
            voltar_Menu.setBounds(10, 10, 100, 30);
        });

        voltar_Menu.addActionListener( e -> {
            // Voltar para o menu principal
            Janela.setVisible(true);
            Janela.setBounds(0, 0, 800, 600);   
            Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Starting_Game.dispose();
            Register_Words.dispose();
            Show_Winners.dispose();
            Clear.dispose();
        }       );

    while (Rodando_Menu == true) {

        System.out.println("_____________________________________");
        System.out.println("            MENU PRINCIPAL           ");
        System.out.println("1 _ Start Game");
        System.out.println("2 _ Register Words");
        System.out.println("3 _ Show Winners");
        System.out.println("4 _ Clear");
        System.out.println("0 _ Exit");
        System.out.println("_____________________________________");

        System.out.print("Escolha : ");            
        int escolha = leitor.nextInt();

        //limpa buffer
        leitor.nextLine();

        switch (escolha) {
            case 1:
                //JOGO____________________________________________________________________________________________________________________________________________________________________________________
                System.out.println("Starting Game...");
                boolean Jogando = true;

                while (Jogando == true) {
                    
                   System.out.print("   Digite o nome do jogador: ");
                   String nomeJogador = leitor.nextLine();

                   Player jogador = new Player();
                   jogador.setNome(nomeJogador);

                   listaJogadores.add(jogador);

                   for (Words w : listaPalavras) {
                       System.out.println("Palavra: " + w.getPalavra());
                       System.out.print("Digite a tradução: ");
                       String resposta = leitor.nextLine();

                       if (resposta.equalsIgnoreCase(w.getTraducao())) {
                           System.out.println("Correto!");
                           jogador.ganharPonto();
                       } else {
                           System.out.println("Errado! A tradução correta é: " + w.getTraducao());
                           jogador.perderVida();
                       }

                       if (jogador.getVida() <= 0) {
                           System.out.println("Game Over! Você perdeu todas as vidas.");
                           Jogando = false;
                           break;
                       }
                   }
                   System.out.println("Fim de jogo, " + jogador.getNome() + "! Pontuação: " + jogador.getPonto());
                   Arquivo.salvarVencedor(jogador);
                   Jogando = false;
                }
                break;

            case 2:
                //REGISTRO DE PALAVRAS____________________________________________________________________________________________________________________________________________________________________________________
                System.out.println("Registering Words...");
                boolean Registrando_Palavras = true;
                System.out.println("");//pula linha

                System.out.println("Palavras registradas:");//Mostra as palavras ja registradas
                for (Words w : listaPalavras) {

                    System.out.println(w.getPalavra() + " = " + w.getTraducao() + " (" + w.getCategoria() + ")");

                }

                System.out.println("");//pula linha
                
                while (Registrando_Palavras == true) {
                
                    System.out.print("Digite a palavra: ");
                    String palavra = leitor.nextLine();

                    System.out.print("Digite a tradução: ");
                    String traducao = leitor.nextLine();

                    System.out.print("Digite a categoria: ");
                    String categoria = leitor.nextLine();

                    Words novaPalavra = new Words();
                    novaPalavra.setPalavra(palavra);
                    novaPalavra.setTraducao(traducao);
                    novaPalavra.setCategoria(categoria);

                    listaPalavras.add(novaPalavra);
                    Arquivo.salvar(listaPalavras);

                    System.out.println("Palavra registrada com sucesso!");

                    System.out.print("Deseja registrar outra palavra? (s/n): ");
                    String resposta = leitor.nextLine();
                    
                    if (resposta.equalsIgnoreCase("n")) {
                        Registrando_Palavras = false;
                    }
                }

                break;
            case 3:
                System.out.println("Showing Winners...");
                System.out.println("");
                
                ArrayList<Player> vencedores = Arquivo.carregarVencedores();
                
                if (vencedores.isEmpty()) {
                    System.out.println("Nenhum jogador registrado ainda.");
                } else {
                    int posicao = 1;
                    for (Player p : vencedores) {
                        System.out.println(posicao + "º - " + p.getNome() + " | Pontos: " + p.getPonto());
                        posicao++;
                    }
                }
                System.out.println("");
                break;
            
            case 4:
                //Dar clear nas Words ou Winners
                System.out.println("_____________________________________");
                System.out.println("             CLEAR MENU              ");
                System.out.println("1 _ Clear Words");
                System.out.println("2 _ Clear Winners");
                System.out.println("0 _ Cancel");
                System.out.print("Escolha o que deseja limpar: ");
                
                int escolhaClear = leitor.nextInt();
                leitor.nextLine(); //limpa o buffer do teclado

                if (escolhaClear == 1) {
                    System.out.println("Clearing Words...");
                    listaPalavras.clear(); //limpa a lista na memória
                    Arquivo.salvar(listaPalavras); //salva a lista vazia no arquivo
                    System.out.println("Todas as palavras foram apagadas com sucesso!");
                } else if (escolhaClear == 2) {
                    System.out.println("Clearing Winners...");
                    Arquivo.limparVencedores(); //chama o metodo que zera o txt
                    System.out.println("O histórico de vencedores foi apagado com sucesso!");
                } else if (escolhaClear == 0) {
                    System.out.println("Operação cancelada.");
                } else {
                    System.out.println("Opção inválida.");
                }
                break;

            case 0:
                System.out.println("Exiting ....");
                Rodando_Menu = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        
    }
        leitor.close();
    }
}
