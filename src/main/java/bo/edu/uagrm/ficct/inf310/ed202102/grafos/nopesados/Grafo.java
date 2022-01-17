package bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorrido_utils.RecorridoUtils;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorrido_utils.RecorridoUtils2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Grafo {
    private static int POSICION_NO_VALIDA = -1;
    protected List<List<Integer>> listaDeAdyacencias;
    RecorridoUtils controlDeMarcados;
    RecorridoUtils2 controlDeMarcados2;

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
    public Grafo(int nroDeVertices) throws ExcepcionNumVerticesInvalido {
        if (nroDeVertices <= 0) {
            throw new ExcepcionNumVerticesInvalido();
        }
        this.listaDeAdyacencias = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.insertarVertice();//no guardamos el valor real del vertice en el grafo. Esta en la diapositiva
        }
        this.controlDeMarcados = new RecorridoUtils(nroDeVertices);
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
        validarVertice(posVertice);// si esto lanza una excepcion, todo el método lanzara la excepcion.
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posVertice);
        return adyacentesDelVertice.size();
    }

    /**
     * 6) public por si el usuario desea validar.
     * @param posVertice
     */
    public void validarVertice(int posVertice) {
        if (posVertice < 0 || posVertice >= this.cantidadDeVertices()) {//rango
            throw new IllegalArgumentException("No existe vértice en la posición" +
                    posVertice + " en su grafo");//Esto es una excepcion en tiempo de ejecución.
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
            adyacentesDelVerticeDestino.add(posVerticeOrigen);
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
        this.validarVertice(posVerticeOrigen);
        this.validarVertice(posVerticeDestino);
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        return adyacentesDelVerticeOrigen.contains(posVerticeDestino);
    }

    /**
     * 9) Una lista es un iterable que permite navegar en los elementos de esa coleccion.
     * @param posDeVertice
     * @return
     */
    public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
        this.validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        Iterable<Integer> iterableAdyacentesDelVertice = adyacentesDelVertice;
        return iterableAdyacentesDelVertice;
    }

    /**
     * 10) cantidad de aristas en un grafo.
     * @return
     */
    public int cantidadDeAristas() {
        int cantidadArista = 0;
        int cantidadLazo = 0;
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            List<Integer> listaDeAdyacenciaDeUnVertice = this.listaDeAdyacencias.get(i);
            for (Integer posAdyacente : listaDeAdyacenciaDeUnVertice) {
                if (i != posAdyacente) {
                    cantidadArista++;
                } else {
                    cantidadLazo++;
                }
            }
        }

        return (cantidadArista / 2) + cantidadLazo;
    }

    /**
     * 11)
     * @param posVerticeAEliminar
     */
    public void eliminarVertice(int posVerticeAEliminar) {
        this.validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<Integer> adyacentesDeUnVertice : this.listaDeAdyacencias) {
            int posicionDeVerticeAEliminarEnAdy = adyacentesDeUnVertice.indexOf(posVerticeAEliminar);
            if (posicionDeVerticeAEliminarEnAdy >= 0) {
                adyacentesDeUnVertice.remove(posicionDeVerticeAEliminarEnAdy);
            }

            for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
                int posicionDeAdyacente = adyacentesDeUnVertice.get(i);
                if (posicionDeAdyacente > posVerticeAEliminar) {
                    adyacentesDeUnVertice.set(i, posicionDeAdyacente - 1);
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
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste("La arista no existe en su grafo");
        }

        List<Integer> listaDeAdyacenciaOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        int indexOfVerticeAdyacenteDestino = listaDeAdyacenciaOrigen.indexOf(posVerticeDestino);
        listaDeAdyacenciaOrigen.remove(indexOfVerticeAdyacenteDestino);

        if (posVerticeOrigen != posVerticeDestino) {
            List<Integer> listaDeAdyacenciaDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            int indexOfVerticeAdyacenteOrigen = listaDeAdyacenciaDestino.indexOf(posVerticeOrigen);// obtiene la posicion del vertice origen de la lista de adyacencia del vertice destino.
            listaDeAdyacenciaDestino.remove(indexOfVerticeAdyacenteOrigen);
        }
    }

    /**
     * 9. Para un grafo no dirigido implementar un método o clase que permita encontrar si en dicho grafo hay ciclo.
     * @return
     * @throws ExcepcionNumVerticesInvalido
     * @throws ExcepcionAristaYaExiste
     */
    public boolean hayCiclo() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        if (this.cantidadDeVertices() == 0) {
            return false;
        }
        Grafo grafoAuxiliar = new Grafo(this.cantidadDeVertices());
        this.controlDeMarcados.desmarcarTodos();

        while (!controlDeMarcados.estanTodosMarcados()) {
            int posVerticeNoMarcado = this.getPosVerticeNoMarcado(this.controlDeMarcados);
            if (hayCiclo(posVerticeNoMarcado, grafoAuxiliar, this.controlDeMarcados)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param posDeVerticeEnTurno
     * @param grafoAux
     * @param controlDeMarcados
     * @return
     * @throws ExcepcionAristaYaExiste
     */
    private boolean hayCiclo(int posDeVerticeEnTurno, Grafo grafoAux, RecorridoUtils controlDeMarcados) throws ExcepcionAristaYaExiste {
        controlDeMarcados.marcarVertice(posDeVerticeEnTurno);
        //recorrido.add(posDeVerticeEnTurno);
        Iterable<Integer> adyacentesDeVerticeEnTurno = this.adyacentesDeVertice(posDeVerticeEnTurno);
        for (Integer posDeVerticeAdy : adyacentesDeVerticeEnTurno) {

            if (controlDeMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                if (!grafoAux.existeAdyacencia(posDeVerticeEnTurno, posDeVerticeAdy)) {
                    return true;
                }
            }

            if (!controlDeMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                grafoAux.insertarArista(posDeVerticeEnTurno, posDeVerticeAdy);
                if (hayCiclo(posDeVerticeAdy, grafoAux, controlDeMarcados)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retorna la posicion de un vertice que no esta marcado
     *
     * @return
     */
    protected int getPosVerticeNoMarcado(RecorridoUtils controlDeMarcados) {
        int i = 0;
        for (Boolean marcado : controlDeMarcados.getMarcados()) {
            if (!marcado) {
                return i;
            }
            i++;
        }
        return Grafo.POSICION_NO_VALIDA;
    }

    /**
     * funciona con recorrido BFS o DFS
     *
     * @return
     */
    public boolean esConexoElGrafo() {

        if (this.cantidadDeVertices() == 0) {
            return false;
        }
        this.controlDeMarcados.desmarcarTodos();
        this.marcarVerticesAdyacentes(0, this.controlDeMarcados);
        return this.controlDeMarcados.estanTodosMarcados();
    }

    /**
     * @param posDeVertice
     * @param controlDeMarcados
     */
    protected void marcarVerticesAdyacentes(int posDeVertice, RecorridoUtils controlDeMarcados) {
        controlDeMarcados.marcarVertice(posDeVertice);

        List<Integer> adyacentesDeVertice = this.listaDeAdyacencias.get(posDeVertice);
        for (Integer posDeVerticeAdy : adyacentesDeVertice) {
            if (!controlDeMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                this.marcarVerticesAdyacentes(posDeVerticeAdy, controlDeMarcados);
            }
        }
    }

    /**
     *
     * @return
     */
    public int cantidadDeIslasEnElGrafo() {
        int cantidad = 0;
        //this.controlDeMarcados.desmarcarTodos();
        if (this.cantidadDeVertices() == 0) {
            return cantidad;
        }
        this.controlDeMarcados.desmarcarTodos();
        while (!this.controlDeMarcados.estanTodosMarcados()) {
            int posDeVerticeNoMarcado = this.getPosVerticeNoMarcado(this.controlDeMarcados);
            this.marcarVerticesAdyacentes(posDeVerticeNoMarcado, this.controlDeMarcados);
            cantidad++;
        }

        return cantidad;
    }

    /**
     * 10. Para un grafo no dirigido implementar método o clase para encontrar los componentes de las
     * diferentes islas que hay en dicho grafo
     * @throws ExcepcionNumVerticesInvalido
     * @throws ExcepcionAristaYaExiste
     */
    public void componentesDeLasIslasDeUnGrago() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        Digrafo digrafoAux = new Digrafo(this.cantidadDeVertices());
        int nroIsla = 0;
        this.controlDeMarcados2 = new RecorridoUtils2(this.cantidadDeVertices());

        while (!this.controlDeMarcados2.estanTodosProcesados()) {
            int posDeVerticeNoMarcado = this.getPosDeVerticeNoMarcado2(this.controlDeMarcados2);
            this.marcarVerticesAdyacentes2(posDeVerticeNoMarcado, this.controlDeMarcados2);

            this.agregarComponentes(nroIsla, this.controlDeMarcados2, digrafoAux);
            nroIsla++;

            this.marcarVerticesComoProcesados(this.controlDeMarcados2);
        }

        for (int i = 0; i < this.cantidadDeIslasEnElGrafo(); i++) {
            System.out.println("Isla[" + i +"]: " + digrafoAux.getListaDeAdyacencia(i));
        }
    }

    /**
     *
     * @param controlDeMarcados2
     * @return
     */
    protected int getPosDeVerticeNoMarcado2(RecorridoUtils2 controlDeMarcados2) {
        int i = 0;
        for (Integer marcado : controlDeMarcados2.getMarcados()) {
            if (marcado == RecorridoUtils2.NO_MARCADO) {
                return i;
            }
            i++;
        }
        return Grafo.POSICION_NO_VALIDA;
    }

    /**
     *
     * @param posDeVertice
     * @param controlDeMarcados2
     */
    protected void marcarVerticesAdyacentes2(int posDeVertice, RecorridoUtils2 controlDeMarcados2) {
        controlDeMarcados2.marcarVertice(posDeVertice, RecorridoUtils2.MARCADO);
        List<Integer> adyacentesDeVertice = this.listaDeAdyacencias.get(posDeVertice);
        for (Integer posDeVerticeAdy : adyacentesDeVertice) {
            if (!controlDeMarcados2.estaVerticeMarcado(posDeVerticeAdy)) {
                this.marcarVerticesAdyacentes2(posDeVerticeAdy, controlDeMarcados2);
            }
        }
    }

    /**
     *
     * @param posDeIsla
     * @param controlDeMarcados2
     * @param digrafoAux
     * @throws ExcepcionAristaYaExiste
     */
    protected void agregarComponentes(int posDeIsla, RecorridoUtils2 controlDeMarcados2, Digrafo digrafoAux)
            throws ExcepcionAristaYaExiste {

        List<Integer> marcadosAux = controlDeMarcados2.getMarcados();
        for (int i = 0; i < marcadosAux.size(); i++) {
            if (controlDeMarcados2.estaVerticeMarcado(i)) {
                digrafoAux.insertarArista(posDeIsla, i);
            }
        }
    }

    /**
     *
     * @param controlDeMarcados2
     */
    protected void marcarVerticesComoProcesados(RecorridoUtils2 controlDeMarcados2) {
        List<Integer> marcadosAux = controlDeMarcados2.getMarcados();
        for (int i = 0; i < marcadosAux.size(); i++) {
            if (marcadosAux.get(i) == RecorridoUtils2.MARCADO) {
                controlDeMarcados2.marcarVertice(i, RecorridoUtils2.PROCESADO);
            }
        }
    }

    /**
     *
     * @param posicion
     * @return
     */
    protected List<Integer> getListaDeAdyacencia(int posicion) {
        return this.listaDeAdyacencias.get(posicion);
    }

    /**
     *
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaYaExiste
     */
    private void insertarAristaParaComponentes(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacentesDelVerticeOrigen.add(posVerticeDestino);
        Collections.sort(adyacentesDelVerticeOrigen);
    }
}