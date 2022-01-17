package bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Grafo;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorrido_utils.RecorridoUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    private RecorridoUtils controlDeMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;

    /**
     * 1)
     * @param unGrafo
     * @param posDeVerticeDePartida
     */
    public BFS(Grafo unGrafo, int posDeVerticeDePartida) {
        this.grafo = unGrafo;
        grafo.validarVertice(posDeVerticeDePartida);
        recorrido = new LinkedList<>();
        controlDeMarcados = new RecorridoUtils(grafo.cantidadDeVertices());
        ejecturaBFS(posDeVerticeDePartida);
    }

    /**
     * 2)
     * @param posDeVerticeDePartida
     */
    private void ejecturaBFS(int posDeVerticeDePartida) {
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(posDeVerticeDePartida);
        controlDeMarcados.marcarVertice(posDeVerticeDePartida);

        do {
            int posVerticeEnTurno = cola.poll();
            recorrido.add(posVerticeEnTurno);
            Iterable<Integer> adyacentesDeVerticeEnTurno = grafo.adyacentesDeVertice(posVerticeEnTurno);
            for (Integer posDeVerticeAdy : adyacentesDeVerticeEnTurno) {
                if (!controlDeMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                    cola.offer(posDeVerticeAdy);
                    controlDeMarcados.marcarVertice(posDeVerticeAdy);
                }
            }

        } while (!cola.isEmpty());
    }

    /**
     * 3)
     * @return
     */
    public boolean hayCaminosATodos() {
        return controlDeMarcados.estanTodosMarcados();
    }

    /**
     * 4)
     * @return
     */
    public Iterable<Integer> obtenerRecorrido() {
        return this.recorrido;
    }

    /**
     * 5)
     * @param posDeVerticeDestino
     * @return
     */
    public boolean hayCaminoAVertice(int posDeVerticeDestino) {
        grafo.validarVertice(posDeVerticeDestino);
        return controlDeMarcados.estaVerticeMarcado(posDeVerticeDestino);
    }
}
