package logica;

import java.util.ArrayList;

public class Words {  

    //Variaveis    

        private String palavra;
        private String traducao;
        private String categoria;
        private ArrayList<Words> listaPalavras = new ArrayList<>();

    //Metodos

        public void recebe_word(String palavra , String traducao, String categoria){
            Words word = new Words(palavra, traducao, categoria);
            listaPalavras.add(word);
        }

        

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

        public ArrayList<Words> getListaPalavras() {
            return listaPalavras;
        }

        public String getPalavra(){
            return palavra;
        }

        public String getTraducao(){
            return traducao;
        }

        public String getCategoria(){
            return categoria;
        }
        
        public Words(String palavra, String traducao, String categoria){
            setPalavra(palavra);
            setTraducao(traducao);
            setCategoria(categoria);
        }
        public Words(){
        }

        //Polimorfismo
        public boolean conferirTraducao(String resposta)
        {
            if (resposta == null || this.traducao == null)
            {
                return false;
            }
            return this.traducao.equalsIgnoreCase(resposta.trim());
        }

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
        
        public String getTipo()
        {
            return "Normal";
        }
    //Fim dos metodos   
}
