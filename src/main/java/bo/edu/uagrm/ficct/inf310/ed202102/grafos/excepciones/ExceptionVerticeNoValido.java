package bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones;

public class ExceptionVerticeNoValido extends Exception {
    public ExceptionVerticeNoValido() {
        super("El grado de entrada del vertice no es cero, escoja otro vertice");
    }

    public ExceptionVerticeNoValido(String msg) {
        super(msg);
    }

}
