package bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones;

public class ExcepcionAristaYaExiste extends Exception{
    public ExcepcionAristaYaExiste() {
        super("Arista ya existe en su grafo.");
    }

    public ExcepcionAristaYaExiste(String msg) {
        super(msg);
    }
}
