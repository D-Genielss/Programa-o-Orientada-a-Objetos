package logica;
public class Player {
    //Variaveis
        private int vida = 3;
        private String nome;
        private int pontuacao = 0;

    //Metodos
        public void perderVida(){
            if(this.vida > 0){
                this.vida--;
            }
        }
        public int getVida(){
            return this.vida;
        }

        public void ganharPonto(){
            this.pontuacao++;
        }
        public int getPonto(){
            return this.pontuacao;
        }

        //Setters========================================
        public void setNome(String nome){
            this.nome = nome;
        }

        //Getters========================================
        public String getNome(){
            return nome;
        }

        //Construtor (Sobrecarga) para o Player
        public Player(String nome, int pontuacao){
            this.nome = nome;
            if (pontuacao >= 0){
                this.pontuacao = pontuacao;
            }
        }
        public Player(){
        }
    //Fim dos metodos
}
