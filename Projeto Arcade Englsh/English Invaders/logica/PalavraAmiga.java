package logica;
public class PalavraAmiga extends Words
{

    public PalavraAmiga()
    {
        super();
    }

    public PalavraAmiga(String palavra, String traducao, String categoria)
    {
        super(palavra, traducao, categoria);
    }

    @Override
    public void aplicarEfeito(Player jogador, boolean acertou)
    {
        if (acertou)
        {
            jogador.ganharPonto();
            jogador.ganharPonto(); //Bonus por acertar palavra amiga
        }
        //Palavra amiga nao remove vida do jogador ao errar
    }

    @Override
    public String getTipo()
    {
        return "Amiga";
    }
}