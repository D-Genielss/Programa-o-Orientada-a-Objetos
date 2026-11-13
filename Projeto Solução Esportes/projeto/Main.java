import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
// Bibliotecas para deixar deixar os horários bonitinhos
//Superclasse que serve de moldo para todos que usarem o sistema
abstract class Usuario 
{
    private String nome;
    private String ra;
    private int diasBloqueado;
    private int quantidadeItensPegos;
    //Aqui foi feito o Encapsulamente e o Construtor
    public Usuario(String nome, String ra) {
        this.nome = nome;
        this.ra = ra;
        this.diasBloqueado = 0;
        this.quantidadeItensPegos = 0;
}
    //Polimorfismo
    public abstract int getLimiteMaximoItens();

    public boolean podePegarEmprestado()
    {
        return this.diasBloqueado == 0 && this.quantidadeItensPegos < getLimiteMaximoItens();
    }

    public void aplicarPunicao(int dias)
    {
        this.diasBloqueado += dias;
    }

    public void registrarRetiradaItem()
    {
        this.quantidadeItensPegos++;
    }

    public void registrarDevolucaoItem()
    {
        if (this.quantidadeItensPegos > 0)
            {
            this.quantidadeItensPegos--;
            }
    }
    //Getters
    public String getNome() { return nome; }
    public String getRa() { return ra; }
    public int getDiasBloqueado() { return diasBloqueado; }
    public int getQuantidadeItensPegos() { return quantidadeItensPegos; }
}

class Aluno extends Usuario
{   //Reutiliza a Superclasse, no caso é uma Subclasse Aluno
    public Aluno(String nome, String ra)
    {
        super(nome, ra);
    }
    //Polimorfismo para limitar os itens em 2
    @Override
    public int getLimiteMaximoItens() { return 2; }
}

class Servidor extends Usuario
{   //Subclasse Servidor, como no caso acima do aluno
    public Servidor(String nome, String ra)
    {
        super(nome, ra);
    }
    //Polimorfismo para limitar os itens em 4
    @Override
    public int getLimiteMaximoItens() { return 4; }
}
//Superclasse para os itens
abstract class Item
{
    private int id;
    private String nome;
    private boolean disponivel;
    private String estadoConservacao;
    //Construtor
    public Item(int id, String nome)
    {
        this.id = id;
        this.nome = nome;
        this.disponivel = true;
        this.estadoConservacao = "Perfeito estado";
    }
    //Sobrecarga para iniciar um item já com valores de uso
    public Item(int id, String nome, String estadoConservacao)
    {
        this.id = id;
        this.nome = nome;
        this.disponivel = true;
        this.estadoConservacao = estadoConservacao;
    }
    //Polimorfismo
    public abstract int getPrazoDias();

    public void emprestar()
    {
        this.disponivel = false;
    }

    public void devolver()
    {
        this.disponivel = true;
    }

    public void devolver(String descricaoDano)
    {
        this.disponivel = true;
        this.estadoConservacao = "Danificado: " + descricaoDano;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public boolean isDisponivel() { return disponivel; }
    public String getEstadoConservacao() { return estadoConservacao; }
}
//Para itens mais comuns e menores
class ItemComum extends Item
{
    private int prazoDias;

    public ItemComum(int id, String nome, int prazoDias)
    {
        super(id, nome);
        this.prazoDias = prazoDias;
    }

    //Sobrecarga
    public ItemComum(int id, String nome, int prazoDias, String estadoConservacao)
    {
        super(id, nome, estadoConservacao);
        this.prazoDias = prazoDias;
    }
    //Polimorfismo
    @Override
    public int getPrazoDias() { return this.prazoDias; }
}
//Modelo para as Bicicletas, que tem que ter vistoria
class Bicicleta extends Item
{
    private boolean pneuOk;
    private boolean freioOk;
    private boolean correnteOk;

    public Bicicleta(int id, String nome) {
        super(id, nome);
        this.pneuOk = true;
        this.freioOk = true;
        this.correnteOk = true;
    }
    //Polimorfismo
    @Override
    public int getPrazoDias() {
        return 7;
    }
    //Sobrecarga da devolução
    public void devolver(boolean pneuOk, boolean freioOk, boolean correnteOk) {
        this.pneuOk = pneuOk;
        this.freioOk = freioOk;
        this.correnteOk = correnteOk;

        if (!pneuOk || !freioOk || !correnteOk)
            {
            super.devolver("Problema na vistoria (Pneu/Freio/Corrente)");
            } else
                {
                super.devolver();
                }
    }
}

class Emprestimo
{
    private Usuario usuario;
    private Item item;
    private LocalDateTime dataRetirada;
    private LocalDateTime dataPrevistaDevolucao;
    private LocalDateTime dataRealDevolucao;
    private String observacaoDano;

    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Emprestimo(Usuario usuario, Item item)
    {
        this.usuario = usuario;
        this.item = item;
        this.dataRetirada = LocalDateTime.now();
        
        if (item.getPrazoDias() == 0)
            {
            this.dataPrevistaDevolucao = this.dataRetirada.withHour(23).withMinute(59);
            } else {
            this.dataPrevistaDevolucao = this.dataRetirada.plusDays(item.getPrazoDias());
            }
        
        this.dataRealDevolucao = null;
        this.observacaoDano = "Sem avarias";

        item.emprestar();
        usuario.registrarRetiradaItem();
    }
    //Sobrecarga
    public void finalizarEmprestimo()
    {
        this.dataRealDevolucao = LocalDateTime.now();
        this.item.devolver();
        this.usuario.registrarDevolucaoItem();
    }
    //Sobrecarga
    public void finalizarEmprestimo(String danoRelatado)
    {
        this.dataRealDevolucao = LocalDateTime.now();
        this.observacaoDano = danoRelatado;
        this.item.devolver(danoRelatado);
        this.usuario.registrarDevolucaoItem();
    }

    public boolean isEmAndamento()
    {
        return this.dataRealDevolucao == null;
    }
    //Calcularia o tanto de dias corridos se tiver atraso para devolver
    public int calcularDiasDeAtraso()
    {
        LocalDateTime dataReferencia = (dataRealDevolucao != null) ? dataRealDevolucao : LocalDateTime.now();
        if (dataReferencia.isAfter(dataPrevistaDevolucao))
            {
            long dias = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataReferencia);
            return (int) Math.max(1, dias);
            }
        return 0;
    }

    public String formatarItemEmUso()
    {
        return String.format("Item: %s (ID: %d) | Com: %s (RA: %s) | Retirado em: %s | Devolver até: %s",
                item.getNome(), item.getId(), usuario.getNome(), usuario.getRa(),
                dataRetirada.format(FORMATADOR), dataPrevistaDevolucao.format(FORMATADOR));
    }

    public String formatarHistorico()
    {
        String statusDevolucao = (dataRealDevolucao != null) 
                ? dataRealDevolucao.format(FORMATADOR) 
                : "Ainda emprestado";

        return String.format("[%s -> %s] Item: %s | Usuário: %s (RA: %s) | Obs: %s",
                dataRetirada.format(FORMATADOR), statusDevolucao,
                item.getNome(), usuario.getNome(), usuario.getRa(), observacaoDano);
    }

    public Usuario getUsuario() { return usuario; }
    public Item getItem() { return item; }
}

class ControleEmprestimos
{
    private List<Emprestimo> listaDeEmprestimos;

    public ControleEmprestimos()
    {
        this.listaDeEmprestimos = new ArrayList<>();
    }

    public void registrarNovoEmprestimo(Emprestimo novo)
    {
        this.listaDeEmprestimos.add(novo);
    }
    //Para exibir os itens em posse de alguém
    public void exibirMenuItensAlugados()
    {
        System.out.println("\n=== ITENS EM USO NO MOMENTO ===");
        boolean encontrou = false;

        for (Emprestimo emp : listaDeEmprestimos)
            {
            if (emp.isEmAndamento())
                {
                System.out.println(emp.formatarItemEmUso());
                encontrou = true;
                }
            }

        if (!encontrou)
            {
            System.out.println("Nenhum equipamento está emprestado neste momento.");
            }
    }
    //Exibe o histórico dos empréstimos
    public void exibirHistoricoCompleto()
    {
        System.out.println("\n=== HISTÓRICO GERAL DE EMPRÉSTIMOS ===");
        if (listaDeEmprestimos.isEmpty())
            {
            System.out.println("Nenhum empréstimo foi registrado no sistema ainda.");
            return;
            }

        for (Emprestimo emp : listaDeEmprestimos)
            {
            System.out.println(emp.formatarHistorico());
            }
    }
    
    public List<Emprestimo> getListaDeEmprestimos()
    {
        return listaDeEmprestimos;
    }
}

public class Main
{
    public static void main(String[] arg)
    {
  
        boolean Rodando_Menu = true;
        Scanner leitor = new Scanner(System.in);
        
        //Listas e controlador para conectar as classes ao menu
        List<Usuario> listaUsuarios = new ArrayList<>();
        List<Item> listaItens = new ArrayList<>();
        ControleEmprestimos controle = new ControleEmprestimos();

        //Um pré cadastro para os itens
        listaItens.add(new ItemComum(1, "Bola de Futsal", 0));
        listaItens.add(new ItemComum(2, "Bola de Vôlei", 0));
        listaItens.add(new ItemComum(3, "Bola de Basquete", 0, "Levemente gasta"));
        listaItens.add(new ItemComum(4, "Colete Esportivo", 0));
        listaItens.add(new ItemComum(5, "Raquete de Tênis de Mesa", 2));
        listaItens.add(new Bicicleta(6, "Bicicleta Aro 29 - Reitoria"));

        //Sistema principal, faz sempre voltar pro menu inicial 
        while (Rodando_Menu == true) {
            System.out.println("_____________________________________");            
            System.out.println("            MENU PRINCIPAL           ");
            System.out.println("1 - Regras");
            System.out.println("2 - Dividendos e Atrasos");
            System.out.println("3 - Historico de Alugados");
            System.out.println("4 - Alugar ");// Mostrar os que ja estoa alugados no momento 
            System.out.println("5 - Devolver");
            System.out.println("6 - Cadastrar");
            System.out.println("0 - Para Encerrar");
            System.out.println("_____________________________________");
            System.out.print("Escolha : ");            
            int escolha = leitor.nextInt();
            
            //limpa buffer
            leitor.nextLine();
        
            switch (escolha)
            {
                //Encerrar
                case 0:
                    System.out.println("Encerrando ...");
                    Rodando_Menu = false;
                    break;

                case 1:
                    System.out.println("\n==================================================");
                    System.out.println("            REGULAMENTO DE EMPRÉSTIMOS            ");
                    System.out.println("==================================================");
                    System.out.println("1. LIMITES POR UTILIZADOR:");
                    System.out.println("   - Alunos: Podem retirar no máximo até 2 itens simultaneamente.");
                    System.out.println("   - Servidores: Podem retirar no máximo até 4 itens simultaneamente.");
                    System.out.println();
                    System.out.println("2. PRAZOS DE DEVOLUÇÃO POR EQUIPAMENTO:");
                    System.out.println("   - Bolas e Coletes: Devolução até ao fim do próprio dia (23:59).");
                    System.out.println("   - Raquetes de Tênis de Mesa: Prazo de até 2 dias.");
                    System.out.println("   - Bicicletas: Prazo de 1 semana (7 dias).");
                    System.out.println();
                    System.out.println("3. VISTORIA OBRIGATÓRIA:");
                    System.out.println("   - A devolução de bicicletas exige inspeção de pneus, freio e corrente.");
                    System.out.println();
                    System.out.println("4. PENALIDADES:");
                    System.out.println("   - Atrasos na entrega resultam no bloqueio de novos empréstimos.");
                    System.out.println("   - Danos nos equipamentos são anotados no histórico.");
                    System.out.println("==================================================");

                    System.out.print("Digite 'ok' para voltar ao menu: ");
                    boolean voltar = true;
                    while (voltar)
                        {
                        String OK = leitor.nextLine();
                        if (OK.equalsIgnoreCase("ok"))
                            {
                            voltar = false;     
                            } else
                                {
                                System.out.print("Opção inválida. Digite 'ok' para continuar: ");
                                }
                        }
                    break;

                //Penalidades e bloqueios
                case 2:
                    System.out.println("\n=== CONSULTA DE BLOQUEIOS E ATRASOS ===");
                    if (listaUsuarios.isEmpty())
                        {
                        System.out.println("Nenhum usuário cadastrado no sistema.");
                        break;
                        }
                    for (Usuario u : listaUsuarios)
                        {
                        System.out.println("Usuário: " + u.getNome() + " (RA: " + u.getRa() + ") | Dias de Bloqueio: " + u.getDiasBloqueado());
                        }
                    break;

                //Historico de alugados
                case 3:
                    controle.exibirHistoricoCompleto();
                    break;

                //Alugar e mostrar os que já estão alugados
                case 4:
                    //Mostra os que já estão alugados no momento
                    controle.exibirMenuItensAlugados();

                    //Mostra itens disponíveis e realiza o empréstimo
                    System.out.println("\n=== REALIZAR NOVO EMPRÉSTIMO ===");
                    System.out.print("Digite o RA do usuário: ");
                    String raBusca = leitor.nextLine();

                    Usuario usuarioEncontrado = null;
                    for (Usuario u : listaUsuarios)
                        {
                        if (u.getRa().equals(raBusca))
                            {
                            usuarioEncontrado = u;
                            break;
                            }
                        }

                    if (usuarioEncontrado == null)
                        {
                        System.out.println("Usuário não encontrado! Cadastre primeiro na opção 6.");
                        break;
                        }

                    if (!usuarioEncontrado.podePegarEmprestado())
                        {
                        System.out.println("Este usuário está bloqueado ou já atingiu o limite de itens!");
                        break;
                        }

                    System.out.println("Itens disponíveis:");
                    boolean temDisponivel = false;
                    for (Item i : listaItens)
                        {
                        if (i.isDisponivel())
                            {
                            System.out.println("ID: " + i.getId() + " - " + i.getNome() + " | Prazo: " 
                                + (i.getPrazoDias() == 0 ? "Mesmo dia" : i.getPrazoDias() + " dias") 
                                + " (" + i.getEstadoConservacao() + ")");
                            temDisponivel = true;
                            }
                        }

                    if (!temDisponivel)
                        {
                        System.out.println("Nenhum equipamento disponível para empréstimo no momento!");
                        break;
                        }

                    System.out.print("Digite o ID do item que deseja alugar: ");
                    int idItemAlugar = leitor.nextInt();
                    leitor.nextLine(); // limpa buffer

                    Item itemEncontrado = null;
                    for (Item i : listaItens)
                        {
                        if (i.getId() == idItemAlugar && i.isDisponivel())
                            {
                            itemEncontrado = i;
                            break;
                            }
                        }

                    if (itemEncontrado != null)
                        {
                        Emprestimo novoEmp = new Emprestimo(usuarioEncontrado, itemEncontrado);
                        controle.registrarNovoEmprestimo(novoEmp);
                        System.out.println("Empréstimo registrado com sucesso!");
                        } else
                            {
                        System.out.println("Item não encontrado ou já está emprestado.");
                            }
                    break;

                //Devolver
                case 5:
                    System.out.println("\n=== DEVOLVER EQUIPAMENTO ===");
                    System.out.print("Digite o ID do item que está sendo devolvido: ");
                    int idDevolucao = leitor.nextInt();
                    leitor.nextLine(); // limpa buffer

                    Emprestimo empAtivo = null;
                    for (Emprestimo emp : controle.getListaDeEmprestimos())
                        {
                        if (emp.isEmAndamento() && emp.getItem().getId() == idDevolucao)
                            {
                            empAtivo = emp;
                            break;
                            }
                        }

                    if (empAtivo == null)
                        {
                        System.out.println("Nenhum empréstimo ativo encontrado para este ID.");
                        break;
                        }

                    //Se for bicicleta, exige vistoria
                    if (empAtivo.getItem() instanceof Bicicleta)
                        {
                        Bicicleta bike = (Bicicleta) empAtivo.getItem();
                        System.out.print("Pneu está OK? (true/false): ");
                        boolean pneu = leitor.nextBoolean();
                        System.out.print("Freio está OK? (true/false): ");
                        boolean freio = leitor.nextBoolean();
                        System.out.print("Corrente está OK? (true/false): ");
                        boolean corrente = leitor.nextBoolean();
                        leitor.nextLine(); // limpa buffer

                        bike.devolver(pneu, freio, corrente);
                        if (!pneu || !freio || !corrente)
                            {
                            empAtivo.finalizarEmprestimo("Reprovado na vistoria da bicicleta");
                            } else
                                {
                                empAtivo.finalizarEmprestimo();
                                }
                    } else
                        {
                        System.out.print("O item voltou com algum dano? (s/n): ");
                        String teveDano = leitor.nextLine();
                        if (teveDano.equalsIgnoreCase("s")) {
                            System.out.print("Descreva o dano: ");
                            String descDano = leitor.nextLine();
                            empAtivo.finalizarEmprestimo(descDano); //Usa a sobrecarga com dano
                        } else
                            {
                            empAtivo.finalizarEmprestimo(); //Usa a sobrecarga normal
                            }
                        }

                    System.out.println("Devolução concluída!");
                    break;

                //Cadastrar
                case 6:
                    System.out.println("Cadastrar");
                    System.out.println("1 - cadastrar Pessoa");
                    System.out.println("2 - cadastrar Itens Esportivos");
                    System.out.print("Escolha : ");
                    int Cadastrar = leitor.nextInt();
                    leitor.nextLine(); // limpa buffer para ler os nomes a seguir

                    if (Cadastrar == 1)
                        {
                        //Cadastra Pessoa usando as classes Aluno e Servidor
                        System.out.print("Digite o nome: ");
                        String nome = leitor.nextLine();
                        System.out.print("Digite o RA: ");
                        String ra = leitor.nextLine();
                        System.out.print("Tipo (1 - Aluno | 2 - Servidor): ");
                        int tipoUsuario = leitor.nextInt();
                        leitor.nextLine();

                        if (tipoUsuario == 1)
                            {
                            listaUsuarios.add(new Aluno(nome, ra));
                            System.out.println("Aluno cadastrado com sucesso!");
                            } else if (tipoUsuario == 2)
                            {
                            listaUsuarios.add(new Servidor(nome, ra));
                            System.out.println("Servidor cadastrado com sucesso!");
                            } else
                            {
                            System.out.println("Tipo inválido!");
                            }

                    } else if (Cadastrar == 2)
                        {
                        //Cadastra itens usando ItemComum e Bicicleta
                        int novoId = listaItens.size() + 1;
                        System.out.println("Qual equipamento deseja cadastrar?");
                        System.out.println("1 - Bola ou Colete (Prazo: mesmo dia)");
                        System.out.println("2 - Raquete de Tênis de Mesa (Prazo: 2 dias)");
                        System.out.println("3 - Bicicleta (Prazo: 7 dias + vistoria)");
                        System.out.print("Escolha: ");
                        int tipoItem = leitor.nextInt();
                        leitor.nextLine();

                        System.out.print("Digite o nome/descrição do item (ex: Bola de Futsal): ");
                        String nomeItem = leitor.nextLine();

                        if (tipoItem == 1)
                            {
                            listaItens.add(new ItemComum(novoId, nomeItem, 0));
                            System.out.println("Item cadastrado com ID " + novoId);
                            } else if (tipoItem == 2)
                                {
                            listaItens.add(new ItemComum(novoId, nomeItem, 2));
                            System.out.println("Raquete cadastrada com ID " + novoId);
                                } else if (tipoItem == 3)
                                    {
                            listaItens.add(new Bicicleta(novoId, nomeItem));
                            System.out.println("Bicicleta cadastrada com ID " + novoId);
                                    } else
                                        {
                            System.out.println("Tipo de equipamento inválido!");
                                        }
                    } else
                        {
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