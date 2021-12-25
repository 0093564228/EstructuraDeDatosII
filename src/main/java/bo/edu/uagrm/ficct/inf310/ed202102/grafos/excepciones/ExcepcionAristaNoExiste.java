package bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones;

public class ExcepcionAristaNoExiste extends Exception{
    public ExcepcionAristaNoExiste() {
        super("La arista no existe en su grafo.");
    }
    public ExcepcionAristaNoExiste(String msg) {
        super(msg);
    }

}
