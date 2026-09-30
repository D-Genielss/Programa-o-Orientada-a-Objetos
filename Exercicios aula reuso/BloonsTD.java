//SUper classe seria Entidade , tendo como mini classes Construção e Chefe_Moab

//Cosegui avançar ate a criação de Metodos , porem n consegui equilibrar o tempo 
//ainda com dificuldades em coisas basicas , mas pregredindo 

//uso de IA apenas para o começo (ponto de Start) e Correção de Bugs do inicio 


public class BloonsTD{

    public static void main(String[] args) {

        Entidade mamaco = 
            new Entidade(10);
            mamaco.nome = "Atirador";
            mamaco.dano = 1;
            
        Entidade balao =
            new Entidade(10);
            balao.nome = "Vermelho";
            balao.dano = 1;

        Mapa mapa = 
            new Mapa();
            mapa.nome_Mapa = "Campo_de_Treino";
            mapa.tamanho = 100;
            

        System.out.println("Olá, mundo!");

    }

}

class Entidade {

//Variaveis

    String nome = "Peça";
    int dano;
    int Velocidade = 10;

    public Entidade(int Velocidade){
        this.Velocidade = Velocidade;
    }

//metodos

    void Andar(int Velocidade){

        
    }

}


class Mapa {

    String nome_Mapa;
    int tamanho ;
    int N_Rounds;

}

class Construcao extends Entidade {

    // ja tem as variaveis nome e dano 

    public Construcao(int Velocidade){

        super(Velocidade);
        
    }
    int Gerar_Vida;
    int Gerar_Dinheiro;


}

class Chef_Moab extends Entidade{

    // ja tem as variaveis nome e dano

    public Chef_Moab(int Velocidade){

        super(Velocidade);
        
    }

    String regras;
}