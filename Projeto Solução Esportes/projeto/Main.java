//import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    
    public static void main(String[] arg){
  
        boolean Rodando_Menu = true;
        Scanner leitor = new Scanner(System.in);
        

        //Sistema principal: faz sempre voltar pro menu inicial 
        while(Rodando_Menu == true){

            System.out.println("Escolha : ");
            System.out.println("1 _ Regras");
            System.out.println("2 _ Dividendos e Atrasos");
            System.out.println("0 _ Para Encerrar");
            int escolha = leitor.nextInt();
            
            //limpa buffer
            leitor.nextLine();
        
            switch (escolha) {
                //Encerrar
                case 0:

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
            
                default:

                System.out.println("Escolha Errada , por favor tente novamente");
                break;
            }
        }

        leitor.close();
        
    }

   
}
