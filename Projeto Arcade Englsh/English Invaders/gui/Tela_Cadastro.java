package gui;

import javax.swing.*;
import javax.swing.border.AbstractBorder;

import logica.Arquivo;
import logica.Words;

import java.awt.*;
import java.io.InputStream;

/**
 * Painel "Cadastrar Palavras" com visual neon espacial.
 * Feito para ser usado como uma "carta" do CardLayout.
 *
 * Uso:
 *   TelaCadastro cadastrar_palavras = new TelaCadastro(() -> cards.show(root, "Menu"));
 *   root.add(cadastrar_palavras, "Cadastrar Palavras");
 *
 * Depende das suas classes: Words (recebe_word, getListaPalavras) e Arquivo (salvar).
 *
 * Arquivos opcionais (se nao existirem, usa um visual alternativo):
 *   /img/fundo.png
 *   /img/gato.png
 *   /fonts/Silkscreen-Regular.ttf
 *   /fonts/SairaCondensed-Italic.ttf
 */
public class Tela_Cadastro extends PainelFundo {

    static final Color VERDE = new Color(124, 252, 60);   // texto
    static final Color BORDA = new Color(190, 240, 80);   // borda amarelo-esverdeada

    public static final int LARGURA = 1100;
    public static final int ALTURA = 690;

    private final CampoNeon palavra;
    private final CampoNeon traducao;
    private final CampoNeon categoria;

    /**
     * @param aoVoltar o que acontece ao clicar em Voltar,
     *                 ex.: () -> cards.show(root, "Menu")
     */
    public Tela_Cadastro(Runnable aoVoltar) {
        setPreferredSize(new Dimension(LARGURA, ALTURA));

        // Fontes (com fallback caso os arquivos .ttf nao existam)
        Font fonteTitulo = carregarFonte("/fonts/Silkscreen-Regular.ttf", 52f,
                new Font("Monospaced", Font.BOLD, 52));
        Font fonteLabel = carregarFonte("/fonts/SairaCondensed-Italic.ttf", 24f,
                new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        Font fonteCampo = new Font("Arial", Font.PLAIN, 22);

        // Titulo
        NeonLabel titulo = new NeonLabel("CADASTRAR PALAVRAS", fonteTitulo, VERDE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(0, 120, LARGURA, 70);
        add(titulo);

        // Labels e campos
        String[] nomes = {"Word :", "Tradução :", "Categoria :"};
        CampoNeon[] campos = new CampoNeon[3];
        int y = 200;
        for (int i = 0; i < 3; i++) {
            NeonLabel lbl = new NeonLabel(nomes[i], fonteLabel, VERDE);
            lbl.setBounds(355, y, 300, 35);
            add(lbl);

            campos[i] = new CampoNeon(fonteCampo, VERDE, BORDA);
            campos[i].setBounds(345, y + 38, 420, 52);
            add(campos[i]);
            y += 117;
        }
        palavra = campos[0];
        traducao = campos[1];
        categoria = campos[2];

        // Botoes
        BotaoNeon voltar = new BotaoNeon("Voltar", fonteLabel, VERDE, BORDA);
        voltar.setBounds(70, 30, 140, 48);
        add(voltar);

        BotaoNeon enviar = new BotaoNeon("Enviar", fonteLabel, VERDE, BORDA);
        enviar.setBounds(625, 590, 140, 48);
        add(enviar);

        // Botao Voltar
        voltar.addActionListener(e -> {
            limparCampos();
            if (aoVoltar != null) aoVoltar.run();
        });

        // Botao Enviar (mesma logica do seu codigo original)
        enviar.addActionListener(e -> {
            if (palavra.getText().trim().isEmpty() || traducao.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha a palavra e a tradução!");
                return;
            }

            Words novaPalavra = new Words();
            novaPalavra.recebe_word(palavra.getText(), traducao.getText(), categoria.getText());
            Arquivo.salvar(novaPalavra.getListaPalavras());

            limparCampos();
            palavra.requestFocus();
        });

        // Enter no ultimo campo tambem envia
        categoria.addActionListener(e -> enviar.doClick());
    }

    private void limparCampos() {
        palavra.setText("");
        traducao.setText("");
        categoria.setText("");
    }

    // Carrega uma fonte .ttf do projeto; se falhar, devolve a fonte alternativa
    static Font carregarFonte(String caminho, float tamanho, Font alternativa) {
        try (InputStream is = Tela_Cadastro.class.getResourceAsStream(caminho)) {
            if (is == null) return alternativa;
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(tamanho);
        } catch (Exception e) {
            return alternativa;
        }
    }

    // Carrega uma imagem do projeto; devolve null se nao encontrar
    static Image carregarImagem(String caminho) {
        java.net.URL url = Tela_Cadastro.class.getResource(caminho);
        if (url == null) {
            System.err.println("Imagem nao encontrada: " + caminho);
            return null;
        }
        return new ImageIcon(url).getImage();
    }
}

// =====================================================================
// Classes auxiliares (sem "public", pois estao no mesmo arquivo)
// =====================================================================

/** Painel com imagem de fundo e o gatinho no canto inferior direito. */
class PainelFundo extends JPanel {
    private final Image fundo;
    private final Image gato;

    PainelFundo() {
        setLayout(null); // posicionamento manual
        fundo = Tela_Cadastro.carregarImagem("/img/Fundo.png");
        gato  = Tela_Cadastro.carregarImagem("/img/Alien_Cat_Esperando(certo).png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if (fundo != null) {
            g2.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
        } else {
            g2.setColor(new Color(5, 10, 5));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        // gato no canto inferior direito, com altura maxima de 240 px
        if (gato != null) {
            int w = gato.getWidth(this);
            int h = gato.getHeight(this);
            if (w > 0 && h > 0) {
                if (h > 240) {
                    w = w * 240 / h;
                    h = 240;
                }
                g2.drawImage(gato, getWidth() - w, getHeight() - h, w, h, this);
            }
        }
        g2.dispose();
    }
}

/** Texto com brilho neon. */
class NeonLabel extends JLabel {
    private final Color cor;

    NeonLabel(String texto, Font fonte, Color cor) {
        super(texto);
        this.cor = cor;
        setFont(fonte);
        setForeground(cor);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(getFont());

        FontMetrics fm = g2.getFontMetrics();
        String texto = getText();
        int x = (getHorizontalAlignment() == SwingConstants.CENTER)
                ? (getWidth() - fm.stringWidth(texto)) / 2
                : 0;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

        // brilho: varias copias deslocadas e transparentes
        g2.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 28));
        for (int dx = -3; dx <= 3; dx++) {
            for (int dy = -3; dy <= 3; dy++) {
                if (dx * dx + dy * dy <= 9) {
                    g2.drawString(texto, x + dx, y + dy);
                }
            }
        }

        g2.setColor(cor);
        g2.drawString(texto, x, y);
        g2.dispose();
    }
}

/** Borda arredondada com cor e espessura configuraveis. */
class BordaArredondada extends AbstractBorder {
    private final Color cor;
    private final int espessura, raio;

    BordaArredondada(Color cor, int espessura, int raio) {
        this.cor = cor;
        this.espessura = espessura;
        this.raio = raio;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(cor);
        g2.setStroke(new BasicStroke(espessura));
        g2.drawRoundRect(x + espessura / 2, y + espessura / 2,
                w - espessura - 1, h - espessura - 1, raio, raio);
        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        int p = espessura + 6;
        return new Insets(p, p + 4, p, p + 4);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        Insets i = getBorderInsets(c);
        insets.set(i.top, i.left, i.bottom, i.right);
        return insets;
    }
}

/** Campo de texto preto com borda arredondada neon. */
class CampoNeon extends JTextField {
    CampoNeon(Font fonte, Color texto, Color borda) {
        setOpaque(false);
        setFont(fonte);
        setForeground(texto);
        setCaretColor(texto);
        setSelectionColor(new Color(124, 252, 60, 90));
        setSelectedTextColor(Color.WHITE);
        setBorder(new BordaArredondada(borda, 3, 18));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 230));
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);
        g2.dispose();
        super.paintComponent(g);
    }
}

/** Botao com fundo translucido, borda arredondada e texto neon. */
class BotaoNeon extends JButton {
    private final Color neon;

    BotaoNeon(String texto, Font fonte, Color neon, Color borda) {
        super(texto);
        this.neon = neon;
        setFont(fonte);
        setForeground(neon);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setRolloverEnabled(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new BordaArredondada(borda, 3, 18));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int alpha = getModel().isPressed() ? 200 : (getModel().isRollover() ? 170 : 110);
        g2.setColor(new Color(0, 25, 0, alpha));
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);

        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        String texto = getText();
        int x = (getWidth() - fm.stringWidth(texto)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

        g2.setColor(new Color(neon.getRed(), neon.getGreen(), neon.getBlue(), 28));
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                if (dx * dx + dy * dy <= 4) {
                    g2.drawString(texto, x + dx, y + dy);
                }
            }
        }
        g2.setColor(neon);
        g2.drawString(texto, x, y);
        g2.dispose();
    }
}