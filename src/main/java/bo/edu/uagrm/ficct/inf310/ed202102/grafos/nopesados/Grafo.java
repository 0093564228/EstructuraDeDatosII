package bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Grafo {
    protected List<List<Integer>> listaDeAdyacencias;

    /**
     * 1)
     */
    public Grafo() {
        this.listaDeAdyacencias = new LinkedList<>();
    }

    /**
     * 2)
     * @param nroDeVertices
     * @throws ExcepcionNumVerticesInvalido
     */
    public Grafo(int nroDeVertices) throws  ExcepcionNumVerticesInvalido{
        if (nroDeVertices <= 0) {
            throw new ExcepcionNumVerticesInvalido();
        }
        this.listaDeAdyacencias = new LinkedList<>();
        for (int i = 0; i <= nroDeVertices; i++) {
            this.insertarVertice();
        }
    }

    /**
     * 3)
     */
    private void insertarVertice() {
        List<Integer> adyacentesDeVerticeAInsertar = new LinkedList<>();
        this.listaDeAdyacencias.add(adyacentesDeVerticeAInsertar);
    }

    /**
     * 4)
     * @return
     */
    public int cantidadDeVertices() {
        return listaDeAdyacencias.size();
    }

    /**
     * 5)
     * @param posVertice
     * @return
     */
    public int gradoDeVertice(int posVertice) {
        validarVertice(posVertice);// si esto lanza una excepcion, todo el método lanzara la excepcion
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posVertice);
        return adyacentesDelVertice.size();
    }

    /**
     * 6) public por si el usuario desea validar.
     * @param posVertice
     */
    public void validarVertice(int posVertice) {
        if(posVertice <= 0 || posVertice >= this.cantidadDeVertices()){//rango
            throw new IllegalArgumentException("No existe vértice en la posición" +
                    posVertice + " en su grafo");//Esto es uma excepcion en tiempo de ejecucion.
        }
    }

    /**
     * 7)
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaYaExiste
     */
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacentesDelVerticeOrigen.add(posVerticeDestino);
        Collections.sort(adyacentesDelVerticeOrigen);

        if (posVerticeOrigen != posVerticeDestino) {
            List<Integer> adyacentesDelVerticeDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            adyacentesDelVerticeOrigen.add(posVerticeOrigen);
            Collections.sort(adyacentesDelVerticeDestino);
        }
    }

    /**
     * 8)
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @return
     */
    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        return adyacentesDelVerticeOrigen.contains(posVerticeDestino);
    }

    /**
     * 9)
     * @param posDeVertice
     * @return
     */
    public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
        this.validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        Iterable<Integer>iterableAdyacentesDelVertice = adyacentesDelVertice;
        return iterableAdyacentesDelVertice;
    }

    /**
     * 10)
     * @return
     */
    public int cantidadDeAristas() {
        return 0;
    }

    /**
     * 11)
     * @param posVerticeAEliminar
     */
    public void eliminarVertice(int posVerticeAEliminar) {
        validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<Integer> adyacentesDeUnVertice : this.listaDeAdyacencias) {
            int posicionDeVerticeAEliminarEnAdy = adyacentesDeUnVertice.indexOf(posVerticeAEliminar);
            if (posicionDeVerticeAEliminarEnAdy >= 0) {
                adyacentesDeUnVertice.remove(posicionDeVerticeAEliminarEnAdy);
            }

            for (int i= 0; i < adyacentesDeUnVertice.size(); i++) {
                int posicionDeAdyacente = adyacentesDeUnVertice.get(i);
                if (posicionDeAdyacente > posVerticeAEliminar) {
                    adyacentesDeUnVertice.set(i, posicionDeAdyacente  - 1);
                }
            }
        }
    }

    /**
     * 12)
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaNoExiste
     */
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {

    }
}
