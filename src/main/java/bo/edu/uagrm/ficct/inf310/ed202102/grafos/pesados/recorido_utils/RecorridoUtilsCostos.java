package bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.recorido_utils;

import java.util.LinkedList;
import java.util.List;

public class RecorridoUtilsCostos {
    private List<Double> costos;
    public static final double INFINITO = 999999999;


    /**
     * 1)
     * @param nroDeVertices
     */
    public RecorridoUtilsCostos(int nroDeVertices) {
        this.costos = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.costos.add(RecorridoUtilsCostos.INFINITO);
        }
    }

    /**
     * 2)
     */
    public void desmarcarTodos() {
        for (int i = 0; i < costos.size(); i++) {
            costos.set(i, RecorridoUtilsCostos.INFINITO);
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
//        return this.marcados.get(posDeVertice);
//    }

    /**
     * 4)
     *
     * @return
     */
//    public boolean estanTodosMarcados() {
//        for (Boolean marcado: this.marcados) {
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
    public void setCosto(int posDeVertice, double costo) {
        costos.set(posDeVertice, costo);
    }

    public List<Double> getCostos() {
        return this.costos;
    }

    public double getCosto(int posicion) {
        return costos.get(posicion);
    }
}
