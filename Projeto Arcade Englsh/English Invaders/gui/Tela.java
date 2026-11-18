package gui;
//import logica.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.*;

//import java.awt.event.*;

public class Tela extends JFrame {

    CardLayout cards = new CardLayout();
    JPanel root = new JPanel(cards);

    JPanel menu = new JPanel();
        JPanel cadastrar_jogadores = new JPanel();
            GameTela game = new GameTela();
        JPanel cadastrar_palavras = new JPanel();
        JPanel clear = new JPanel();

    //Métodos 

    private JButton criarVoltar(String destino) {
        JButton b = new JButton("Voltar");
        b.setFocusable(false);
        b.addActionListener(e -> cards.show(root, destino));
        return b;
    }

    public Tela(){
        
        //Menu
        setTitle("English Invaders");
        menu.setPreferredSize(new Dimension(800, 500));
        menu.setBackground(Color.BLACK);
        JButton botao_jogador = new JButton("Cadastrar Jogadores");
        menu.add(botao_jogador);
        botao_jogador.addActionListener(e -> cards.show(root, "Cadastrar Jogadores"));

            //Cadastrar Jogadores 
            cadastrar_jogadores.add(criarVoltar("Menu"));

            cadastrar_jogadores.setPreferredSize(new Dimension(800, 500));
            cadastrar_jogadores.setBackground(Color.DARK_GRAY);            
            JButton botao_jogar = new JButton("Jogar");
            cadastrar_jogadores.add(botao_jogar);

                //Jogar
                game.add(criarVoltar("Cadastrar Jogadores"));
                JButton voltarGame = criarVoltar("Cadastrar Jogadores");
                voltarGame.addActionListener(ev -> game.parar());
                game.add(voltarGame);

                botao_jogar.addActionListener(e -> {
                    cards.show(root, "Game");
                    game.iniciar();
                    
                });

        //Cadastrar Palavras
        JButton botao_palavras = new JButton("Cadastrar Palavras");
        menu.add(botao_palavras);
        botao_palavras.addActionListener(e -> cards.show(root, "Cadastrar Palavras")); 

            cadastrar_palavras.add(criarVoltar("Menu"));
        
            cadastrar_palavras.setPreferredSize(new Dimension(800, 500));
            cadastrar_palavras.setBackground(Color.DARK_GRAY);

        //Clear
        JButton botao_clear = new JButton("Clear");
        menu.add(botao_clear);
        botao_clear.addActionListener(e -> cards.show(root, "Clear"));

            clear.add(criarVoltar("Menu"));

            clear.setPreferredSize(new Dimension(800, 500));
            clear.setBackground(Color.DARK_GRAY);

        //Adicionar os paineis ao CardLayout
        root.add(menu, "Menu");
            root.add(cadastrar_jogadores, "Cadastrar Jogadores");
                root.add(game, "Game");
            root.add(cadastrar_palavras, "Cadastrar Palavras");
            root.add(clear, "Clear");

        //Configurações da janela
        setContentPane(root);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tela().setVisible(true));
    }


    //Comando que precisa para executar
    //cd "C:\Users\dggm0\OneDrive\Área de Trabalho\Programa-o-Orientada-a-Objetos\Projeto Arcade Englsh\English Invaders"
    //javac gui\Tela.java
    //java gui.Tela
}
    
