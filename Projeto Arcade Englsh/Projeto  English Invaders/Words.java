public class Words {    
    //Variaveis    
        private String palavra;
        private String traducao;
        private String categoria;

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
        
        public Words(String palavra, String traducao, String categoria){
            setPalavra(palavra);
            setTraducao(traducao);
            setCategoria(categoria);
        }
        public Words(){
        }
    //Fim dos metodos   
}
