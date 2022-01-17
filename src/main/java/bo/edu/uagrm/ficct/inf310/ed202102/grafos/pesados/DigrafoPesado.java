package bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExceptionVerticeNoValido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DigrafoPesado extends GrafoPesado{

    /**
     * 1)
     */
    public DigrafoPesado() {
        super();
    }

    /**
     * 2)
     * @param nroDeVertices
     * @throws ExcepcionNumVerticesInvalido
     */
    public DigrafoPesado(int nroDeVertices) throws ExcepcionNumVerticesInvalido {
        super(nroDeVertices);
    }

    /**
     * 3) cantidad de aristas en un grafo.
     * Probar
     * @return
     */
    public int cantidadDeAristas() {
        int cantidad = 0;
        for (int i = 0; i < super.cantidadDeVertices(); i++) {
            List<AdyacenteConPeso> adyacentesDeUnVertice = super.listaDeAdyacencias.get(i);
            cantidad = cantidad + adyacentesDeUnVertice.size();
        }

        return cantidad;
        //return super.cantidadDeAristas();
    }

    /**
     * 4)
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaYaExiste
     */
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double peso) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino, peso);
        adyacentesDelVerticeOrigen.add(adyacenteDelOrigen);
        Collections.sort(adyacentesDelVerticeOrigen);
    }

    /**
     * 5)
     * falta probar
     *
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaNoExiste
     */
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste("La arista no existe en su grafo");
        }

        List<AdyacenteConPeso> listaDeAdyacenciaOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino);
        int indexOfVerticeAdyacenteDelOrigen = listaDeAdyacenciaOrigen.indexOf(adyacenteDelOrigen);
        listaDeAdyacenciaOrigen.remove(indexOfVerticeAdyacenteDelOrigen);
    }

    /**
     * 6)
     * @param posVertice
     * @return
     */
    public int gradoDeVertice(int posVertice) {
        throw new UnsupportedOperationException("Metodo no soportado en grafos dirigidos.");
    }

    /**
     * 7)
     * falta probar
     * @param posVertice
     * @return
     */
    public int gradoDeEntradaDeVertice(int posVertice) {
        super.validarVertice(posVertice);
        int cantidad = 0;
        for (int i = 0; i < super.cantidadDeVertices(); i++) {
            for (Integer posVerticeAdyacente : super.adyacentesDeVertice(i)) {
                if (posVertice == posVerticeAdyacente) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    /**
     * 8)
     * @param posVertice
     * @return
     */
    public int gradoDeSalidaDeUnVertice(int posVertice) {
        return super.gradoDeVertice(posVertice);
    }

    /**
     * 9) Falta probar
     * @param posVerticeAEliminar
     */
    public void eliminarVertice(int posVerticeAEliminar) {
        super.eliminarVertice(posVerticeAEliminar);
    }

    /**
     * 10)
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @return
     * @throws ExcepcionAristaNoExiste
     */
    public double peso(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        return super.peso(posVerticeOrigen, posVerticeDestino);
    }

    /**
     * 15. Para un grafo dirigido pesado implementar el algoritmo de Dijkstra que muestre con que vértices
     * hay caminos de costo mínimo partiendo desde un vértice v, con qué costo y cuáles son los caminos.
     * @param posDeVerticeOrigen
     * @param posDeVerticeDestino
     * @throws ExcepcionAristaNoExiste
     * @throws ExceptionVerticeNoValido
     */
    @Override
    public void algoritmoDijkstra(int posDeVerticeOrigen, int posDeVerticeDestino) throws ExcepcionAristaNoExiste, ExceptionVerticeNoValido {
        super.algoritmoDijkstra(posDeVerticeOrigen, posDeVerticeDestino);
    }

}
