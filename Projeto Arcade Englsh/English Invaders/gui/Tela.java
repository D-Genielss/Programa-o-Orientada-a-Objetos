package gui;
//import logica.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import logica.Arquivo;
import logica.Words;

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

                botao_jogar.addActionListener(e -> {
                    cards.show(root, "Game");
                    game.iniciar();
                    
                });

        //Cadastrar Palavras
         // Cadastrar Palavras
            JButton botao_palavras = new JButton("Cadastrar Palavras");
            menu.add(botao_palavras);
            botao_palavras.addActionListener(e -> cards.show(root, "Cadastrar Palavras"));

            Tela_Cadastro cadastrar_palavras = new Tela_Cadastro(() -> cards.show(root, "Menu"));
            root.add(cadastrar_palavras, "Cadastrar Palavras"); 

            cadastrar_palavras.add(criarVoltar("Menu"));
        
            cadastrar_palavras.setPreferredSize(new Dimension(800, 500));
            cadastrar_palavras.setBackground(Color.DARK_GRAY);
            
            JTextField palavra = new JTextField(20);
            JTextField traducao = new JTextField(20);
            JTextField categoria = new JTextField(20);
            JButton enviar_word = new JButton("Enviar");

            cadastrar_palavras.add(new JLabel("Word:"));
            cadastrar_palavras.add(palavra);
            cadastrar_palavras.add(new JLabel("Tradução:"));
            cadastrar_palavras.add(traducao);
            cadastrar_palavras.add(new JLabel("Categoria:"));
            cadastrar_palavras.add(categoria);
            cadastrar_palavras.add(enviar_word);

            enviar_word.addActionListener(e -> {
            Words novaPalavra = new Words();
            novaPalavra.recebe_word(palavra.getText(), traducao.getText(), categoria.getText());
            Arquivo.salvar(novaPalavra.getListaPalavras());    
            });

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
    
