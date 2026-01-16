package sistema.de.gestao.de.alunos.Model;
import java.util.Objects;
import java.io.Serializable;
public class Aluno implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nome;
    private  int matricula;
    private String curso;
    private int idade;
    private double media;
    
    public Aluno(String nome, int matricula, String curso, int idade){
        this.setNome(nome);
        this.setMatricula(matricula);
        this.setCurso(curso);
        this.setIdade(idade);
        this.setMedia(0.0);
    }
    
    public void setNome(String nome){
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException ("Nome invalido!");
        }
        this.nome = nome;
    }
    
    public String getNome(){    
        return this.nome;
    }
    
    public int getMatricula(){
        return this.matricula;
    }
    
    private void setMatricula(int matricula){
        if(matricula < 0){
            throw new IllegalArgumentException ("Matricula Invalida!");
        }
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        if(curso == null || curso.trim().isEmpty()){
            throw new IllegalArgumentException ("Curso invalido!");
        }
        this.curso = curso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if(idade < 0 || idade > 120){
            throw new IllegalArgumentException ("Idade invalida!");
        }
        this.idade = idade;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        if(media < 0 || media > 20){
            throw new IllegalArgumentException ("Media invalido!");
        }
        this.media = media;
    }
    
    @Override
    public String toString(){
        String s = "Nome: " + this.getNome() + "\nIdade: " + this.getIdade() + "\nCurso: " + this.getCurso() + "\nMedia: " + this.getMedia() + "\nMatricula: " + this.getMatricula();
        return s;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        
        if(o == null || this.getClass() != o.getClass() ){
            return false;
        }
        
        final Aluno other = (Aluno) o;
        return this.matricula == other.matricula;
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(matricula);
    }
}
