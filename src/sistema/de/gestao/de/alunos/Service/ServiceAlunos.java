package sistema.de.gestao.de.alunos.Service;

import sistema.de.gestao.de.alunos.Model.Aluno;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.Serializable;
import java.util.HashMap;
import sistema.de.gestao.de.alunos.Storage.ArmazenamentoDisco;

public class ServiceAlunos implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<Aluno> listaAlunos;
    private Map<Integer, Aluno> mapaAlunos;
    
    public ServiceAlunos(){
        this.listaAlunos = new ArrayList<>();
        this.mapaAlunos = new HashMap<>();
        this.lerDados();
    }
    
    public boolean cadastrarAluno(Aluno aluno){
        if(mapaAlunos.containsKey(aluno.getMatricula())){
            return false;
        }else{
            listaAlunos.add(aluno);
            mapaAlunos.put(aluno.getMatricula(), aluno);
            this.guardarDados();
            return true;
        }
    }
    
    public void listarAlunos(){
        if(listaAlunos.isEmpty()){
            System.out.println("Nenhum aluno cadastrado!");
        }else{
            listaAlunos.stream().sorted(Comparator.comparing(Aluno::getNome)).forEach(a -> System.out.println("Nome: " + a.getNome() + "|Matricula: " + a.getMatricula()));
        }
    }
    
    public Aluno buscarPorMatricula(int matricula){
        return mapaAlunos.get(matricula);
    }
    
    public boolean removerAlunoPorMatricula(int matricula){
        if(mapaAlunos.containsKey(matricula)){
            Aluno aluno = mapaAlunos.get(matricula);
            mapaAlunos.remove(matricula, aluno);
            listaAlunos.remove(aluno);
            this.guardarDados();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean actualizarDadosAluno(int matricula,String nome, int idade, double media, String curso){
        if(!mapaAlunos.containsKey(matricula)){
            return false;
        }
        
        Aluno aluno = mapaAlunos.get(matricula);
        aluno.setNome(nome);
        aluno.setIdade(idade);
        aluno.setMedia(media);
        aluno.setCurso(curso);
        this.guardarDados();
        return true;
        
    }
    
    public List<Aluno> filtroAlunosCurso(String curso){
        List<Aluno> alunosCurso = listaAlunos.stream()
                .filter(a -> a.getCurso().equalsIgnoreCase(curso))
                .sorted(Comparator.comparing(Aluno::getNome).thenComparingInt(Aluno::getMatricula))
                .toList();
        
        return alunosCurso;
    }
    
    public List<Aluno> filtroAlunosAprovados(){
        List<Aluno> alunosAprovados = listaAlunos.stream()
                .filter(a -> a.getMedia() >= 10)
                .sorted(Comparator.comparing(Aluno::getNome).thenComparingInt(Aluno::getMatricula))
                .toList();
        
        return alunosAprovados;
    }
    
    public List<Aluno> rankingMedias(){
        List<Aluno> AlunosRanking = listaAlunos.stream()
                .sorted(Comparator.comparingDouble(Aluno::getMedia)
                .thenComparing(Comparator.comparing(Aluno::getNome)).reversed())
                .toList();
        
        return AlunosRanking;
    }
    
    public void imprimirRankingAlunos(){
        List<Aluno> ranking = this.rankingMedias();
        AtomicInteger pos = new AtomicInteger(1);
        
        System.out.println("------");
        ranking.stream()
                .forEach(a -> System.out.println(pos.getAndIncrement() + "*\n" + a.toString()));
        System.out.println("------");
    }
    
    public void guardarDados(){
        ArmazenamentoDisco armazenar = new ArmazenamentoDisco();
        armazenar.guardarDados(listaAlunos,mapaAlunos);
    }
    
    public void lerDados(){
        ArmazenamentoDisco ler = new ArmazenamentoDisco();
        mapaAlunos = ler.lerEmDiscoMapa();
        listaAlunos = ler.lerEmDiscoList();
    }
    
    public boolean temAlunos(){
        return !listaAlunos.isEmpty();
    }
    
}
