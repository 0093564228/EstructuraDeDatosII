package bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Grafo;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorrido_utils.RecorridoUtils;

import java.util.LinkedList;
import java.util.List;

public class DFS {
    private RecorridoUtils controlDeMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;

    /**
     * 1)
     * @param unGrafo
     * @param posDeVerticeDePartida
     */
    public DFS(Grafo unGrafo, int posDeVerticeDePartida) {
        this.grafo = unGrafo;
        grafo.validarVertice(posDeVerticeDePartida);
        recorrido = new LinkedList<>();
        controlDeMarcados = new RecorridoUtils(grafo.cantidadDeVertices());
        procesarDFS(posDeVerticeDePartida);
    }

    /**
     * 2)
     * @param posDeVerticeEnTurno
     */
    public void procesarDFS(int posDeVerticeEnTurno) {
        controlDeMarcados.marcarVertice(posDeVerticeEnTurno);
        recorrido.add(posDeVerticeEnTurno);
        Iterable<Integer> adyacentesDeVerticeEnTurno = grafo.adyacentesDeVertice(posDeVerticeEnTurno);
        for (Integer posDeVerticeAdy : adyacentesDeVerticeEnTurno) {
            if (!controlDeMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                procesarDFS(posDeVerticeAdy);
            }
        }
    }

    /**
     * 3)
     *
     * @return
     */
    public boolean hayCaminosATodos() {
        return controlDeMarcados.estanTodosMarcados();
    }

    /**
     * 4)
     *
     * @return
     */
    public Iterable<Integer> obtenerRecorrido() {
        return this.recorrido;
    }

    /**
     * 5)
     *
     * @param posDeVerticeDestino
     * @return
     */
    public boolean hayCaminoAVertice(int posDeVerticeDestino) {
        grafo.validarVertice(posDeVerticeDestino);
        return controlDeMarcados.estaVerticeMarcado(posDeVerticeDestino);
    }

    //--------------------------------

    /*
     * Ordenamiento topológico
     * - Es sobre grafo dirigido
     * - El digrafo debe ser debilmente conexo (?)
     * - Que no tenga ciclos (?)
     */

    /*
    Algoritmo de warshall
     */
}
