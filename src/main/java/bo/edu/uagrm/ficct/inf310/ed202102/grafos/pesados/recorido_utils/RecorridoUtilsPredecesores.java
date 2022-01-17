package bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.recorido_utils;

import java.util.LinkedList;
import java.util.List;

public class RecorridoUtilsPredecesores {
    private List<Integer> predecesores;
    private static int  POSICION_NO_VALIDA = -1;


    /**
     * 1)
     * @param nroDeVertices
     */
    public RecorridoUtilsPredecesores(int nroDeVertices) {
        this.predecesores = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.predecesores.add(RecorridoUtilsPredecesores.POSICION_NO_VALIDA);
        }
    }

    /**
     * 2)
     */
    public void desmarcarTodos() {
        for (int i = 0; i < predecesores.size(); i++) {
            predecesores.set(i, RecorridoUtilsPredecesores.POSICION_NO_VALIDA);
        }
    }

    /**
     * 3)
     * Retorna verdadero para indicar que un vertice está marcado.
     * Falso en caso contrario.
     * PRE: Se asume que la posición de vertice es una posicion válida.
     * @param posDeVertice
     * @return
     */
//    public boolean estaVerticeMarcado(int posDeVertice) {
//        return this.predecesores.get(posDeVertice);
//    }

    /**
     * 4)
     *
     * @return
     */
//    public boolean estanTodosMarcados() {
//        for (Boolean marcado: this.predecesores) {
//            if (!marcado) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    /**
     * 5)
     * PRE-Condicion: la posición de vértice, es una posicion válida.
     * @param posDeVertice
     */
    public void setPredecesor(int posDeVertice, int posPredecesor) {
        predecesores.set(posDeVertice, posPredecesor);
    }

    public List<Integer> getPredecesores() {
        return this.predecesores;
    }
}
