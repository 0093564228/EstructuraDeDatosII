package bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorrido_utils;

import java.util.LinkedList;
import java.util.List;

public class RecorridoUtils2 {
    public static int NO_MARCADO = 0;
    public static int MARCADO = 5;
    public static int PROCESADO = 100;

    private List<Integer> marcados;

    /**
     *
     * @param nroDeVertices
     */
    public RecorridoUtils2(int nroDeVertices) {
        this.marcados = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.marcados.add(RecorridoUtils2.NO_MARCADO);
        }
    }

    /**
     *
     */
    public void desmarcarTodos() {
        for (int i = 0; i < this.marcados.size(); i++) {
            this.marcados.set(i, RecorridoUtils2.NO_MARCADO);
        }
    }

    /**
     *
     * @param posDeVertice
     * @return
     */
    public boolean estaVerticeMarcado(int posDeVertice) {
        return this.marcados.get(posDeVertice) == RecorridoUtils2.MARCADO;
    }

    /**
     *
     * @return
     */
    public boolean estanTodosMarcados() {
        for (Integer marcado: this.marcados) {
            if (marcado != RecorridoUtils2.MARCADO) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    public boolean estanTodosProcesados() {
        for (Integer marcado: this.marcados) {
            if (marcado != RecorridoUtils2.PROCESADO) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param posDeVertice
     * @param estado
     */
    public void marcarVertice(int posDeVertice, Integer estado) {
        this.marcados.set(posDeVertice, estado);
    }

    /**
     *
     * @return
     */
    public List<Integer> getMarcados() {
        return marcados;
    }

    /**
     *
     * @param posicion
     * @return
     */
    public int getPosMarcado(int posicion) {
        return this.marcados.get(posicion);
    }


}
