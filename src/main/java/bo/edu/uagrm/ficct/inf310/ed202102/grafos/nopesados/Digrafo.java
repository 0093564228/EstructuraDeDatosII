package bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;

import java.util.Collections;
import java.util.List;

public class Digrafo extends Grafo{
    /**
     * 1)
     */
    public Digrafo() {
        super();
    }

    /**
     * 2)
     * @param nroDeVertices
     * @throws ExcepcionNumVerticesInvalido
     */
    public Digrafo(int nroDeVertices) throws ExcepcionNumVerticesInvalido {
        super(nroDeVertices);
    }

    /**
     * 3) Rehacer
     * @return
     */
    @Override
    public int cantidadDeAristas() {
        return super.cantidadDeAristas();
    }

    /**
     * 4)
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaYaExiste
     */
    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacentesDelVerticeOrigen.add(posVerticeDestino);
        Collections.sort(adyacentesDelVerticeOrigen);
    }

    /**
     * 5)
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaNoExiste
     */
    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        super.eliminarArista(posVerticeOrigen, posVerticeDestino);
    }

    /**
     * 6)
     * @param posDeVertice
     * @return
     */
    @Override
    public int gradoDeVertice(int posDeVertice) {
        //return super.gradoDeUnVertice(posDeVertice);
        throw new UnsupportedOperationException("Metodo no soportado en grafos dirigidos.");
    }

    /**
     * 7)
     * @param posVertice
     * @return
     */
    public int gradoDeEntradaDeVertice(int posVertice) {
        return 0;
    }

    /**
     * 8)
     * @param posVertice
     * @return
     */
    public int gradoDeSalidaDeUnVertice(int posVertice) {
        return super.gradoDeVertice(posVertice);
    }
}
