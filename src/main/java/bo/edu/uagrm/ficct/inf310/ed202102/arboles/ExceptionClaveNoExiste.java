package bo.edu.uagrm.ficct.inf310.ed202102.arboles;

public class ExceptionClaveNoExiste extends Exception{
    public ExceptionClaveNoExiste() {
        super("Clave no existe en el arbol");
    }

    public ExceptionClaveNoExiste(String msg) {
        super(msg);
    }
}
