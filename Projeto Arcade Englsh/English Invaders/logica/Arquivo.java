package logica;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Arquivo {
    
    private static final String NOME = "palavras.txt";

    public static void salvar(ArrayList<Words> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(NOME))) {
            for (Words w : lista) {
                pw.println(w.getPalavra() + ";" + w.getTraducao() + ";" + w.getCategoria() + ";" + w.getTipo());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static ArrayList<Words> carregar() {
        ArrayList<Words> lista = new ArrayList<>();
        File arquivo = new File(NOME);

        if (!arquivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    Words w;
                    if (partes.length >= 4 && partes[3].equalsIgnoreCase("Amiga")) {
                        w = new PalavraAmiga(partes[0], partes[1], partes[2]);
                    } else {
                        w = new PalavraInimiga(partes[0], partes[1], partes[2]);
                    }
                    lista.add(w);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }

        return lista;
    }
    
    public static void salvarVencedor(Player jogador) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("winners.txt", true))) {
            pw.println(jogador.getNome() + ";" + jogador.getPonto());
        } catch (IOException e) {
            System.out.println("Erro ao salvar vencedor: " + e.getMessage());
        }
    }

    public static ArrayList<Player> carregarVencedores() {
        ArrayList<Player> lista = new ArrayList<>();
        File arquivo = new File("winners.txt");

        if (!arquivo.exists()) {
            return lista; //se o arquivo nao existir, retorna a lista vazia
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    if (partes.length == 2) {
                    Player p = new Player(partes[0], Integer.parseInt(partes[1]));
                    lista.add(p);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar vencedores: " + e.getMessage());
        }
        lista.sort((p1, p2) -> Integer.compare(p2.getPonto(), p1.getPonto()));
        return lista;
    }
    
    public static void limparVencedores(){
        try (FileWriter fw = new FileWriter("winners.txt")) {
            fw.flush();
        } catch (IOException e) {
            System.out.println("Erro ao limpar arquivo de vencedores: " + e.getMessage());
        }
    }
}
