import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
//import java.util.Random;

public class Main {
    
    public static void main(String[] arg){

    Scanner leitor = new Scanner(System.in);
    boolean Rodando_Menu = true;

    ArrayList<Words> listaPalavras = Arquivo.carregar();
    ArrayList<Player> listaJogadores = new ArrayList<>();
    
    while (Rodando_Menu == true) {

        System.out.println("_____________________________________");
        System.out.println("            MENU PRINCIPAL           ");
        System.out.println("1 _ Start Game");
        System.out.println("2 _ Register Words");
        System.out.println("3 _ Show Winners");
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

                       if (jogador.vida <= 0) {
                           System.out.println("Game Over! Você perdeu todas as vidas.");
                           Jogando = false;
                           break;
                       }
                   }
                   System.out.println("Fim de jogo, " + jogador.getNome() + "! Pontuação: " + jogador.pontuacao);
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
                // Add logic to show winners here
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

class Words{

//Variaveis    

    //private int vida = 3;
    String palavra;
    String traducao;
    String categoria;

//Metodos

    //Setters========================================
    public void setPalavra(String palavra){
        this.palavra = palavra;
    }

    public void setTraducao(String traducao){
        this.traducao = traducao;    
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;    
    }

    //Getters========================================
    public String getPalavra(){
        return palavra;
    }

    public String getTraducao(){
        return traducao;
    }

    public String getCategoria(){
        return categoria;
    }
}

class Player{
    
//Variaveis

    int vida = 3;
    String nome;
    int pontuacao = 0;

//Metodos

    public void perderVida(){
        vida--;
    }

    public void ganharPonto(){
        pontuacao++;
    }

    //Setters========================================
    public void setNome(String nome){
        this.nome = nome;
    }

    //Getters========================================
    public String getNome(){
        return nome;
    }

}

class Arquivo {
    private static final String NOME = "palavras.txt";

    public static void salvar(ArrayList<Words> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(NOME))) {
            for (Words w : lista) {
                pw.println(w.getPalavra() + ";" + w.getTraducao() + ";" + w.getCategoria());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static ArrayList<Words> carregar() {
        ArrayList<Words> lista = new ArrayList<>();
        File arquivo = new File(NOME);

        if (!arquivo.exists()) {
            return lista; // se caso ainda não tiver arquivo
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    Words w = new Words();
                    w.setPalavra(partes[0]);
                    w.setTraducao(partes[1]);
                    w.setCategoria(partes[2]);
                    lista.add(w);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }

        return lista;
    }
}
