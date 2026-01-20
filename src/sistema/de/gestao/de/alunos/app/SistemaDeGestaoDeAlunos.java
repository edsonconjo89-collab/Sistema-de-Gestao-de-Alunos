package sistema.de.gestao.de.alunos.app;

import java.io.Serializable;
import sistema.de.gestao.de.alunos.Service.ServiceAlunos;
import sistema.de.gestao.de.alunos.Storage.ArmazenamentoDisco;
import sistema.de.gestao.de.alunos.UI.UI;

public class SistemaDeGestaoDeAlunos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public static void main(String[] args) {
        
        ArmazenamentoDisco ar = new ArmazenamentoDisco();
        ServiceAlunos ser = new ServiceAlunos(ar);
        UI ui = new UI(ser);
        
        ui.iniciar();
    }
}
