package bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones;

public class ExcepcionNumVerticesInvalido extends Exception {
    public ExcepcionNumVerticesInvalido() {
        super("Cantidad de vertices invalido.");
    }

    public ExcepcionNumVerticesInvalido(String msg) {
        super(msg);
    }
}
