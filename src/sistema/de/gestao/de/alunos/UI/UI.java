package sistema.de.gestao.de.alunos.UI;
import sistema.de.gestao.de.alunos.Service.ServiceAlunos;
import sistema.de.gestao.de.alunos.Model.Aluno;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class UI {
    //String nome, int matricula, String curso, int idade
    private ServiceAlunos servico;
    
    public UI(ServiceAlunos s){
        servico = s;
    }
    
    public void iniciar(){
        this.menuPrincipal();
    }
    private byte opcao;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private String lerString(){
        String s;
        
        while(true){
            try{
                s = br.readLine();
                return s;
            }catch(IOException e){
                System.err.println("Erro de Entrada/Saida: " + e.getMessage());
            }
        }
    }
    
    private int lerInt() {
        int n;
        
        while(true){
            try{
                n = Integer.parseInt(br.readLine());
                return n;
            }catch(NumberFormatException e){
                System.err.println("Digite apenas numeros!");
            }catch(IOException e){
                System.err.println("Erro de Entrada/Saida: " + e.getMessage());
            }
        }
    }
    
    private double lerDouble() {
        double n;
        
        while(true){
            try{
                n = Double.parseDouble(br.readLine());
                return n;
            }catch(NumberFormatException e){
                System.err.println("Digite apenas numeros!");
            }catch(IOException e){
                System.err.println("Erro de Entrada/Saida: " + e.getMessage());
            }
        }
    }
    
    private void cadastrarAluno(){
        
        String nome,curso;
        int matricula,idade;
        
        System.out.println("--\tCADASTRAR_ALUNO\t\t--");
        System.out.print("Nome do aluno: ");
        nome = lerString();
        System.out.print("Curso: ");
        curso = lerString();
        System.out.print("Idade: ");
        idade = lerInt();
        System.out.print("Matricula: ");
        matricula = lerInt();
        
        while(true){
            try{
                if(servico.cadastrarAluno(new Aluno(nome,matricula,curso,idade))){
                    System.out.println("Cadastrado com sucesso!");
                    break;
                }else{
                    System.out.println("Erro ao cadastrar aluno!");
                    System.out.println("A matricula " + matricula + " esta sendo usada por outro aluno!");
                }
            }catch(IllegalArgumentException e){
                System.err.println("Erro: " + e.getMessage());
                break;
            }
        }
    }
    
    private void atualizarDadosAluno(){
        String nome,curso;
        int matricula,idade;
        double media;
        
        System.out.println("--\tATUALIZAR_ALUNO\t\t--");
        System.out.print("Nome do aluno: ");
        nome = lerString();
        System.out.print("Curso: ");
        curso = lerString();
        System.out.print("Idade: ");
        idade = lerInt();
        System.out.print("Media: ");
        media = lerDouble();
        System.out.print("Matricula: ");
        matricula = lerInt();
            boolean x = false;
            try{
                x = servico.actualizarDadosAluno(matricula, nome, idade, media, curso);
            }catch(IllegalArgumentException e){
                System.err.print("Erro: " + e.getMessage());
            }
            if(x){
                System.out.println("Atualizado com sucesso!");
                
            }else{
                System.out.println("Erro ao Atualizar dados do aluno!");
                System.out.println("A matricula N*" + matricula + " nao existe!");
                
            }
    }
    
    private void visualizarAlunosCadastrados(){
        System.out.println("--\tLISTA DOS ALUNOS");
        servico.listarAlunos();
    }
    
    private void buscar() {
        int n;
        
        
        System.out.println("Digite a matricula para buscar: ");
        n = lerInt();
        Aluno aluno = servico.buscarPorMatricula(n);
        if(aluno != null){
            System.out.println("\tinfo_aluno");
            System.out.println(aluno);
        }else{
            System.out.println("Aluno nao encontrado!");
        }
        
    }
    
    private void remover() {
        int matricula;
        byte opcao;
        System.out.println("Digite a matricula para remover o aluno: ");
        matricula = lerInt();
        Aluno aluno = servico.buscarPorMatricula(matricula);
        if(aluno != null){
            System.out.println("\tinfo_aluno");
            System.out.println(aluno);
            System.out.println("Deseja mesmo remover o aluno N*" + aluno.getMatricula() +"?");
            System.out.println("[ 1 ] Sim");
            System.out.println("[ 2 ] nao");
            while(true){
            
            try{
                opcao = Byte.parseByte(br.readLine());
                
                if(opcao >= 1 && opcao <= 2){
                    break;
                }else{
                    System.err.println("Digite uma opcao valida!");
                }   
            }catch(NumberFormatException e){
                System.err.println("Digite apenas numeros validos!");
            }catch(IOException e){
                System.err.println("Erro de Entrada/Saida: " + e.getMessage());
            }
            }
            
            switch(opcao){
                case 1 ->{
                    servico.removerAlunoPorMatricula(matricula);
                    System.out.println("\tAluno N*" + aluno.getMatricula() + " Removido com sucesso!");
                }
                case 2 ->{
                    System.out.println("\tRemocao Cancelada!");
                    return;
                }
            }
        }else{
            System.out.println("Aluno nao encontrado!");
        }
        
    }
    
    private void buscarCurso(){
        System.out.print("Escreva o nome do curso para Buscar: ");
        String curso = lerString();
        List<Aluno> alunos = servico.filtroAlunosCurso(curso);
        if(alunos.isEmpty()){
            System.out.println("Nenhum aluno encontrado!");
        }else{
            alunos.stream().forEach(a -> System.out.println(a.toString()));
        }
    }
    
    private void aprovados(){
        List<Aluno> alunos = servico.filtroAlunosAprovados();
        if(alunos.isEmpty()){
            System.out.println("Nenhum aluno encontrado!");
        }else{
            alunos.stream().forEach(a -> System.out.println("Matricula N*" + a.getMatricula() + " | Nome: " + a.getNome()));
        }
        
        
    }
    
    private void ranking(){
        servico.imprimirRankingAlunos();
    }
    
    private void menuPrincipal(){
        while(true){
        
        System.out.println("-\n\t\tSISTEMA DE GESTAO DE ALUNOS\n-");
        System.out.println("-\t\tMENU_PRINCIPAL\t\t\t-");
        System.out.println("[ 1 ] - Cadastrar novo aluno");
        System.out.println("[ 2 ] - Atualizar dados do aluno");
        System.out.println("[ 3 ] - Visualiar alunos cadastrados");
        System.out.println("[ 4 ] - Buscar aluno por matricula");
        System.out.println("[ 5 ] - Buscar aluno por curso");
        System.out.println("[ 6 ] - Visualizar aprovados");
        System.out.println("[ 7 ] - Ranking de medias");
        System.out.println("[ 8 ] - Remover aluno");
        System.out.println("[ 9 ] - Sair do programa");
        System.out.println("-\t\t\t\t\t\t-");
        
        while(true){
            
            try{
                opcao = Byte.parseByte(br.readLine());
                
                if(opcao >= 1 && opcao <= 9){
                    if(opcao >= 2 && opcao <= 8 && !servico.temAlunos()){
                        System.err.println("Nao pode efetuar essa operacao porque nao tem nehum aluno cadastrado!");
                    }else{
                        break;
                    }
                    break;
                }
            }catch(NumberFormatException e){
                System.err.println("Digite apenas numeros validos!");
            }catch(IOException e){
                System.err.println("Erro de Entrada/Saida: " + e.getMessage() + "\n");
            }
        }
        
        switch(opcao){
            
            case 1 ->{
                cadastrarAluno();
            }
            case 2 ->{
                atualizarDadosAluno();
            }
            case 3 ->{
                visualizarAlunosCadastrados();
            }
            case 4 ->{
                buscar();
            }case 5 -> {
                buscarCurso();
            }
            case 6 ->{
                aprovados();
            }
            case 7 ->{
                ranking();
            }
            case 8 ->{
                remover();
            }
            case 9 ->{
                System.out.println("\tfim do programa");
                return;
            }
            default ->{
                System.err.println("Digite uma opcao valida!");
            }
        
        }
      
        }
    }

}
