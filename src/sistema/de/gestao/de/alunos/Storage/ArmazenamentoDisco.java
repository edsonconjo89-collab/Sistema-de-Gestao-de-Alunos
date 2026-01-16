package sistema.de.gestao.de.alunos.Storage;
import sistema.de.gestao.de.alunos.Model.Aluno;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmazenamentoDisco {

    public ArmazenamentoDisco() {
    
    }
    
    public void guardarDados(List<Aluno> lista, Map<Integer,Aluno> mapa){
        this.gravarEmDiscoLista(lista);
        this.gravarEmDiscoMapa(mapa);
    }
    
    private void gravarEmDiscoMapa(Map<Integer,Aluno> alunos){
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(new FileOutputStream("Mapalunos.sga"));
            oos.writeObject(alunos);
        }catch(IOException e){
            System.err.println("Erro ao gravar no disco: " + e.getMessage());
        }finally{
            if(oos != null){
                try{
                    oos.close();
                }catch(IOException e){
                    System.err.println("Erro ao gravar: " + e.getMessage());
                }
            }
        }
    }
    
    private void gravarEmDiscoLista(List<Aluno> alunos){
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(new FileOutputStream("listalunos.sga"));
            oos.writeObject(alunos);
        }catch(IOException e){
            System.err.println("Erro ao gravar no disco: " + e.getMessage());
        }finally{
            if(oos != null){
                try{
                    oos.close();
                }catch(IOException e){
                    System.err.println("Erro ao gravar: " + e.getMessage());
                }
            }
        }
    }
    
    public Map<Integer, Aluno> lerEmDiscoMapa(){
        ObjectInputStream ois = null;
        File fl  = new File("Mapalunos.sga");
        
        if(!fl.exists()){
            try{
                fl.createNewFile();
            }catch(IOException e){
                System.err.println("Erro ao criar ficheiro: " + e.getMessage());
            }
            
            return new HashMap<>();
        }
        
        if(fl.length() == 0){
            return new HashMap<>();
        }

        try{
            ois = new ObjectInputStream(new FileInputStream("Mapalunos.sga"));
            Map<Integer,Aluno> alunos = (Map<Integer, Aluno>)ois.readObject();
            
            return alunos;
        }catch(ClassNotFoundException e){
            System.err.println("Classe nao encontrada: " + e.getMessage());
        }catch(IOException e){
            System.err.println("Erro ao ler dados salvos em disco: " + e.getMessage());
        }finally{
            if(ois != null){
                try{
                    ois.close();
                }catch(IOException e){
                    System.err.println("Erro ao ler: " + e.getMessage());
                }
            }
        }
        
        return new HashMap<>();
    }
    
        public List<Aluno> lerEmDiscoList(){
        ObjectInputStream ois = null;
        File fl  = new File("listalunos.sga");
        
        if(!fl.exists()){
            try{
                fl.createNewFile();
            }catch(IOException e){
                System.err.println("Erro ao criar ficheiro: " + e.getMessage());
            }
            
            return new ArrayList<>();
        }
        
        if(fl.length() == 0){
            return new ArrayList<>();
        }

        try{
            ois = new ObjectInputStream(new FileInputStream("listalunos.sga"));
            List<Aluno> alunos = (List<Aluno>)ois.readObject();
            
            return alunos;
        }catch(ClassNotFoundException e){
            System.err.println("Classe nao encontrada: " + e.getMessage());
        }catch(IOException e){
            System.err.println("Erro ao ler dados salvos em disco: " + e.getMessage());
        }finally{
            if(ois != null){
                try{
                    ois.close();
                }catch(IOException e){
                    System.err.println("Erro ao ler: " + e.getMessage());
                }
            }
        }
        
        return new ArrayList<>();
    }
}
