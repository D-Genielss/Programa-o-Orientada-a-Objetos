package gui;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class GameTela extends JPanel implements ActionListener, KeyListener {

    // estado do objeto
    private int x = 100, y = 100;
    private final int tamanho = 40;
    private final int velocidade = 5;
    private boolean cima, baixo, esquerda, direita;

    // loop do jogo: chama actionPerformed a cada 16 ms (~60 FPS)
    private final Timer timer = new Timer(16, this);

    public GameTela() {
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);      // necessário para receber o teclado
        addKeyListener(this);
    }

    public void iniciar() {
        timer.start();
        requestFocusInWindow();  // pede o foco do teclado
    }

    public void parar() {
        timer.stop();
        cima = baixo = esquerda = direita = false;
    }

    // chamado a cada tick do Timer
    @Override
    public void actionPerformed(ActionEvent e) {
        if (cima) y -= velocidade;
        if (baixo) y += velocidade;
        if (esquerda) x -= velocidade;
        if (direita) x += velocidade;

        // não deixa sair da tela
        x = Math.max(0, Math.min(getWidth() - tamanho, x));
        y = Math.max(0, Math.min(getHeight() - tamanho, y));

        repaint();
    }

    // desenha o objeto
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);   // limpa a tela com a cor de fundo
        g.setColor(Color.GREEN);
        g.fillRect(x, y, tamanho, tamanho);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> cima = true;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> baixo = true;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> esquerda = true;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> direita = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> cima = false;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> baixo = false;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> esquerda = false;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> direita = false;
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
}