public class Player {
        
    //Variaveis

        int vida = 3;
        String nome;
        int pontuacao = 0;

    //Metodos

        public void perderVida(){
            vida--;
        }

        public void ganharPonto(){
            pontuacao++;
        }

        //Setters========================================
        public void setNome(String nome){
            this.nome = nome;
        }

        //Getters========================================
        public String getNome(){
            return nome;
        }

    //Fim dos metodos

}
