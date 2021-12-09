package bo.edu.uagrm.ficct.inf310.ed202102.arboles;

public class ExceptionOrdenNoValido extends Exception{
    public ExceptionOrdenNoValido() {
        super("Orden del arbol del arbol no puede ser menor a 3");
    }

    public ExceptionOrdenNoValido(String msg) {
        super(msg);
    }
}
