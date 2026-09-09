
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
    balao.classe = "Vermelho";
    balao.vida = 10;
    balao.Velocidade = 10;
    
        System.out.printf("Nome : %s \n",mamaco.classe);
        System.out.println("");
        System.out.printf("Nome : %s \n",balao.classe);
    }

}
    class Entidade {

//Atributos das entidades

    int dano;
    String classe;
    int vida;
    int Velocidade;

    //metodos (acoes do jogo)

    }

   

