
public class Nave {

    //variáveis
    private int posicaoX = 400;
    private int posicaoY = 500;
    private int tamanho = 50;
    private int velocidade = 10;

    //métodos
    public void moverEsquerda() {
        posicaoX -= velocidade;
    }

    public void moverDireita() {
        posicaoX += velocidade;
    }

    public void moverCima() {
        posicaoY -= velocidade;
    }

    public void moverBaixo() {
        posicaoY += velocidade;
    }

    }
