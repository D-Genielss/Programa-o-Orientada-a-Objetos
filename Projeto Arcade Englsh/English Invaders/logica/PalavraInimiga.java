package logica;
public class PalavraInimiga extends Words
{

    public PalavraInimiga()
    {
        super();
    }

    public PalavraInimiga(String palavra, String traducao, String categoria)
    {
        super(palavra, traducao, categoria);
    }

    @Override
    public void aplicarEfeito(Player jogador, boolean acertou)
    {
        if (acertou)
        {
            jogador.ganharPonto();
        } else
        {
            jogador.perderVida();
        }
    }

    @Override
    public String getTipo()
    {
        return "Inimiga";
    }
}