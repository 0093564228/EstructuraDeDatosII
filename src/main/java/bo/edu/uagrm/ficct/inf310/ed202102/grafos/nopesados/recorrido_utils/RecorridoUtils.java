package bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorrido_utils;

import java.util.*;
public class RecorridoUtils {
    private List<Boolean> marcados;


    /**
     * 1)
     * @param nroDeVertices
     */
    public RecorridoUtils(int nroDeVertices) {
        this.marcados = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.marcados.add(Boolean.FALSE);
        }
    }

    /**
     * 2)
     */
    public void desmarcarTodos() {
        for (int i = 0; i < marcados.size(); i++) {
            marcados.set(i, Boolean.FALSE);
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
    public boolean estaVerticeMarcado(int posDeVertice) {
        return this.marcados.get(posDeVertice);
    }

    /**
     * 4)
     *
     * @return
     */
    public boolean estanTodosMarcados() {
        for (Boolean marcado: this.marcados) {
            if (!marcado) {
                return false;
            }
        }

        return true;
    }

    /**
     * 5)
     * PRE-Condicion: la posición de vértice, es una posicion válida.
     * @param posDeVertice
     */
    public void marcarVertice(int posDeVertice) {
        marcados.set(posDeVertice, Boolean.TRUE);
    }

    public List<Boolean> getMarcados() {
        return this.marcados;
    }
}
