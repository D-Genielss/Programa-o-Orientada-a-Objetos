//import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    
    public static void main(String[] arg){
  
        boolean Rodando_Menu = true;
        Scanner leitor = new Scanner(System.in);
        

        //Sistema principal: faz sempre voltar pro menu inicial 
        while(Rodando_Menu == true){
            System.out.println("_____________________________________");            
            System.out.println("            MENU PRINCIPAL           ");
            System.out.println("1 _ Regras");
            System.out.println("2 _ Dividendos e Atrasos");
            System.out.println("3 _ Historico de Alugados");
            System.out.println("4 _ Alugar ");// Mostrar os que ja estoa alugados no momento 
            System.out.println("5 _ Devolver");
            System.out.println("6 _ Cadastrar");
            System.out.println("0 _ Para Encerrar");
            System.out.println("_____________________________________");
            System.out.print("Escolha : ");            
            int escolha = leitor.nextInt();
            
            //limpa buffer
            leitor.nextLine();
        
            switch (escolha) {
                //Encerrar
                case 0:
                System.out.println("Encerrando ....");
                Rodando_Menu = false;

                break;
                //Regras
                case 1:

                    System.out.println("Regras");

                    //digitar as regras 

                    System.out.print("Digite 'ok' se entendeu : ");
                    boolean voltar = true;
                    while (voltar == true) {
                        
                        String OK = leitor.nextLine();
                        
                        System.out.println(OK);

                        if(OK.equals("ok")){
                            voltar = false;     

                        }else{
                            System.out.println("ops tente novamente");
                        }
                    }
                    
                break;
                //Dividendos e atrasos
                case 2:

                System.out.println("Dividendos e atrasos");
                
                break;
                //Cadastrar
                case 6:

                    System.out.println("Cadastrar");
                    System.out.println("1_cadastrar Pessoa");
                    System.out.println("2_cadastrar Itens Esportivos");
                    System.out.print("Escolha : ");
                    int Cadastrar = leitor.nextInt();

                    if(Cadastrar == 1){
                    //Cadastra Pessoa

                    }else if(Cadastrar == 2){
                    //Cadastra Itens Esportivos

                    }else{

                        System.out.println("ops algo deu errado");

                    }

                break;
            
                default:

                System.out.println("Escolha Errada , por favor tente novamente");
                break;
            }
        }

        leitor.close();
        
    }

   
}
