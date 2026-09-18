

public class Exercicio1 {

    public static void main(String[] args) {
    //criar uma classe mamaco e uma classe ballon , pique bloons td

    Entidade mamaco = 
        new Entidade();
    mamaco.dano = 10;
    mamaco.classe = "Mamaco Dardo";

    Entidade balao = 
        new Entidade();
    balao.dano = 10;
    balao.classe = "Balão Vermelho";
    balao.vida = 10;
    balao.Velocidade = 10;

    //código em si , ou tentativas disso eu acho 
    
    //    System.out.printf("Nome : %s \n",mamaco.classe);
    //    System.out.println("");
    //    System.out.printf("Nome : %s \n",balao.classe);
        
    }

}
class Entidade {

    //Atributos das entidades

    int dano;
    String classe;
    int vida;
    int Velocidade;

    //metodos (acoes do jogo)

        void Mostrar_Ficha(){
            
            System.out.println(classe);

        }

        void Metodo_Qualquer(String qualquer){

        }

}

class Construcao extends Entidade {

        //tem td que a Entidade tem , e mais oq eu adicionar

        int Gera_Dinheiro ;

        public Construcao(String tipo){
            Metodo_Qualquer(tipo);
        }

        //metodos podem ser reescritos 

        @Override 

        void Metodo_Qualquer(String qualquer){
            //retorna outro metodo
            // + outro metodo que eu quiser

        }
}

   

