import javax.swing.JButton;
import javax.swing.JFrame;

public class Tela {
    public static void main(String[] arg){
        //Swing___________________________________________________________________________________________________________________________________________________________________________________
    JButton voltar_Menu = new JButton("Voltar");
    JFrame Janela = new JFrame("English Invaders");
        JButton start_botao = new JButton("Start Game");
            JFrame Starting_Game = new JFrame("English Invaders/Starting Game");
        JButton register_botao = new JButton("Register Words");
            JFrame Register_Words = new JFrame("English Invaders/Register Words");
        JButton show_botao = new JButton("Show Winners");
            JFrame Show_Winners = new JFrame("English Invaders/Show Winners");
        JButton clear_botao = new JButton("Clear");
            JFrame Clear = new JFrame("English Invaders/Clear");
    
    
    //janela menu
    Janela.setLayout(null);
    Janela.setBounds(0, 0, 800, 600);   
    Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    Janela.setVisible(true);

        start_botao.setBounds(300, 100, 200, 50);
        Janela.add(start_botao);
        start_botao.addActionListener(e -> {
            // Iniciar o jogo
            Starting_Game.setLayout(null);
            Starting_Game.setVisible(true);
            Starting_Game.setBounds(0, 0, 800, 600);   
            Starting_Game.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Janela.dispose();
            Starting_Game.add(voltar_Menu);
            voltar_Menu.setBounds(10, 10, 100, 30);
            
        });
        
        register_botao.setBounds(300, 150, 200, 50);
        Janela.add(register_botao);
        register_botao.addActionListener(e -> {
            // Registrar palavras
            Register_Words.setLayout(null);
            Register_Words.setVisible(true);
            Register_Words.setBounds(0, 0, 800, 600);   
            Register_Words.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Janela.dispose();
            Register_Words.add(voltar_Menu);
            voltar_Menu.setBounds(10, 10, 100, 30);
        });

        show_botao.setBounds(300, 200, 200, 50);
        Janela.add(show_botao);
        show_botao.addActionListener(e -> {
            // Mostrar vencedores
            Show_Winners.setLayout(null);
            Show_Winners.setVisible(true);
            Show_Winners.setBounds(0, 0, 800, 600);   
            Show_Winners.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Janela.dispose();
            Show_Winners.add(voltar_Menu);
            voltar_Menu.setBounds(10, 10, 100, 30);
        });

        clear_botao.setBounds(300, 250, 200, 50);
        Janela.add(clear_botao);
        clear_botao.addActionListener(e -> {
            // Limpar dados
            Clear.setLayout(null);  
            Clear.setVisible(true);
            Clear.setBounds(0, 0, 800, 600);   
            Clear.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Janela.dispose();
            Clear.add(voltar_Menu);
            voltar_Menu.setBounds(10, 10, 100, 30);
        });

        voltar_Menu.addActionListener( e -> {
            // Voltar para o menu principal
            Janela.setVisible(true);
            Janela.setBounds(0, 0, 800, 600);   
            Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Starting_Game.dispose();
            Register_Words.dispose();
            Show_Winners.dispose();
            Clear.dispose();
        }       );
    }
}
