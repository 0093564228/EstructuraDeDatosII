package bo.edu.uagrm.ficct.inf310.ed202102.arboles;
import java.util.*;

public interface IArbolBusqueda<K extends Comparable<K>, V> {
    void insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException;
    V eliminar(K claveAEliminar) throws ExceptionClaveNoExiste;
    V buscar(K claveABuscar);
    boolean contiene(K claveABuscar);
    int size();
    int altura();
    void vaciar();
    boolean esArbolVacio();
    List<K> recorridoPorNiveles();
    List<K> recorridoEnPreOrden();
    List<K> recorridoEnInOrden();
    List<K> recorridoEnPostOrden();

}
