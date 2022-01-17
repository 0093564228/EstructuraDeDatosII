package bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExceptionVerticeNoValido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorrido_utils.RecorridoUtils;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.recorido_utils.AristaConCosto;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.recorido_utils.RecorridoUtilsCostos;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.recorido_utils.RecorridoUtilsPredecesores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GrafoPesado {
    protected List<List<AdyacenteConPeso>> listaDeAdyacencias;
    private List<AristaConCosto> listaDeAristasConCosto; // para el algoritmo de kruskal

    private static final int POSICION_NO_VALIDA = -1;
    RecorridoUtils controlDeMarcados;
    RecorridoUtilsCostos controlDeCostos;
    RecorridoUtilsPredecesores controlDePredecesores;

    /**
     * 1)
     */
    public GrafoPesado() {
        this.listaDeAdyacencias = new LinkedList<>();
        this.listaDeAristasConCosto = new LinkedList<AristaConCosto>();// para el algoritmo de kruskal
    }

    /**
     * 2)
     *
     * @param nroDeVertices
     * @throws ExcepcionNumVerticesInvalido
     */
    public GrafoPesado(int nroDeVertices) throws ExcepcionNumVerticesInvalido {
        if (nroDeVertices <= 0) {
            throw new ExcepcionNumVerticesInvalido();
        }
        this.listaDeAdyacencias = new LinkedList<>();
        this.listaDeAristasConCosto = new LinkedList<>();// para el algoritmo de kruskal
        for (int i = 0; i < nroDeVertices; i++) {
            this.insertarVertice();//no guardamos el valor real del vertice en el grafo. Esta en la diapositiva
        }
        this.controlDeMarcados = new RecorridoUtils(nroDeVertices);
        this.controlDeCostos = new RecorridoUtilsCostos(nroDeVertices);
        this.controlDePredecesores = new RecorridoUtilsPredecesores(nroDeVertices);
    }

    /**
     * 3)
     */
    private void insertarVertice() {
        List<AdyacenteConPeso> adyacentesDeVerticeAInsertar = new LinkedList<>();
        this.listaDeAdyacencias.add(adyacentesDeVerticeAInsertar);
    }

    /**
     * 4)
     *
     * @return
     */
    public int cantidadDeVertices() {
        return listaDeAdyacencias.size();
    }

    /**
     * 5)
     *
     * @param posVertice
     * @return
     */
    public int gradoDeVertice(int posVertice) {
        validarVertice(posVertice);// si esto lanza una excepcion, todo el método lanzara la excepcion.
        List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(posVertice);
        return adyacentesDelVertice.size();
    }

    /**
     * 6) public por si el usuario desea validar.
     *
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
     *
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

        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> adyacentesDelVerticeDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            AdyacenteConPeso adyacenteDelDestino = new AdyacenteConPeso(posVerticeOrigen, peso);
            adyacentesDelVerticeDestino.add(adyacenteDelDestino);
            Collections.sort(adyacentesDelVerticeDestino);
        }
    }

    /**
     * 8)
     *
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @return
     */
    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        this.validarVertice(posVerticeOrigen);
        this.validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino);// es como un get
        return adyacentesDelVerticeOrigen.contains(adyacenteDelOrigen);
    }

    /**
     * 9) Una lista es un iterable que permite navegar en los elementos de esa coleccion.
     *
     * @param posDeVertice
     * @return
     */
    public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
        this.validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        List<Integer> soloVertices = new ArrayList<>();
        for (AdyacenteConPeso adyacenteConPeso : adyacentesDelVertice) {
            soloVertices.add(adyacenteConPeso.getIndiceDeVertice());
        }
        Iterable<Integer> iterableAdyacentesDelVertice = soloVertices;
        return iterableAdyacentesDelVertice;
    }

    /**
     * 10) cantidad de aristas en un grafo.
     * Probar
     *
     * @return
     */
    public int cantidadDeAristas() {
        int cantidadAristas = 0;
        int cantidadLazos = 0;
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            Iterable<Integer> adyacentesDeUnVertice = this.adyacentesDeVertice(i);
            for (Integer posAdyacente : adyacentesDeUnVertice) {
                if (i != posAdyacente) {
                    cantidadAristas++;
                } else {
                    cantidadLazos++;
                }
            }
        }

        return (cantidadAristas / 2) + cantidadLazos;
    }

    /**
     * 11)
     *
     * @param posVerticeAEliminar
     */
    public void eliminarVertice(int posVerticeAEliminar) {
        this.validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<AdyacenteConPeso> adyacentesDeUnVertice : this.listaDeAdyacencias) {
            AdyacenteConPeso unAdyacenteConPeso = new AdyacenteConPeso(posVerticeAEliminar);
            int posicionDeVerticeAEliminarEnAdy = adyacentesDeUnVertice.indexOf(unAdyacenteConPeso);
            if (posicionDeVerticeAEliminarEnAdy >= 0) {
                adyacentesDeUnVertice.remove(posicionDeVerticeAEliminarEnAdy);
            }

            for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
                AdyacenteConPeso adyacenteEnTurno = adyacentesDeUnVertice.get(i);
                if (adyacenteEnTurno.getIndiceDeVertice() > posVerticeAEliminar) {
                    adyacenteEnTurno.setIndiceDeVertice(adyacenteEnTurno.getIndiceDeVertice() - 1);
                    //adyacentesDeUnVertice.set(i, adyacenteEnTurno - 1);
                }
            }
        }
    }

    /**
     * 12) hacer 2
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

        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> listaDeAdyacenciaDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            AdyacenteConPeso adyacenteDelDestino = new AdyacenteConPeso(posVerticeOrigen);
            int indexOfVerticeAdyacenteDelDestino = listaDeAdyacenciaDestino.indexOf(adyacenteDelDestino);// obtiene la posicion del vertice origen de la lista de adyacencia del vertice destino.
            listaDeAdyacenciaDestino.remove(indexOfVerticeAdyacenteDelDestino);
        }
    }

    /**
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @return
     * @throws ExcepcionAristaNoExiste
     */
    public double peso(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        this.validarVertice(posVerticeOrigen);
        this.validarVertice(posVerticeDestino);
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso unAdyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino);
        int posicionDeLaAdyacencia = adyacentesDelOrigen.indexOf(unAdyacenteDelOrigen);
        AdyacenteConPeso adyacenteDelOrigenReal = adyacentesDelOrigen.get(posicionDeLaAdyacencia);
        return adyacenteDelOrigenReal.getPeso();
    }

    /**
     * @param posDeVerticeOrigen
     * @param posDeVerticeDestino
     * @throws ExcepcionAristaNoExiste
     */
    public void algoritmoDijkstra(int posDeVerticeOrigen, int posDeVerticeDestino) throws ExcepcionAristaNoExiste, ExceptionVerticeNoValido {
        List<Integer> camino = new ArrayList<>();//para guardar los vertices
        this.controlDeMarcados.desmarcarTodos();// estructura auxiliar
        this.controlDeCostos.desmarcarTodos();// estructura auxiliar
        this.controlDePredecesores.desmarcarTodos();// estructura auxiliar
        this.controlDeCostos.setCosto(posDeVerticeOrigen, 0);// el costo del origen debe inicializarse en cero
        int posicionDeVerticeNoMarcadoDeMenorCosto = posDeVerticeOrigen;
        while (posicionDeVerticeNoMarcadoDeMenorCosto != GrafoPesado.POSICION_NO_VALIDA
                && !this.controlDeMarcados.estaVerticeMarcado(posDeVerticeDestino)) {

            this.controlDeMarcados.marcarVertice(posicionDeVerticeNoMarcadoDeMenorCosto);
            camino.add(posicionDeVerticeNoMarcadoDeMenorCosto);

            for (Integer posVerticeAdy : this.adyacentesDeVertice(posicionDeVerticeNoMarcadoDeMenorCosto)) {
                if (!this.controlDeMarcados.estaVerticeMarcado(posVerticeAdy)) {
                    Double unCosto = this.controlDeCostos.getCosto(posVerticeAdy);
                    Double sumaMenorCostoYPeso = this.controlDeCostos.getCosto(posicionDeVerticeNoMarcadoDeMenorCosto)
                            + this.peso(posicionDeVerticeNoMarcadoDeMenorCosto, posVerticeAdy);
                    if (unCosto > sumaMenorCostoYPeso) {
                        this.controlDeCostos.setCosto(posVerticeAdy, sumaMenorCostoYPeso);
                        this.controlDePredecesores.setPredecesor(posVerticeAdy, posicionDeVerticeNoMarcadoDeMenorCosto);
                    }
                }
            }
            posicionDeVerticeNoMarcadoDeMenorCosto = this.getPosVerticeNoMarcadoDeMenorCosto(this.controlDeCostos, this.controlDeMarcados);
        }

        if (posicionDeVerticeNoMarcadoDeMenorCosto == GrafoPesado.POSICION_NO_VALIDA) { //si no existe camino de menor costo para el destino se lanza una excepcion
            throw new ExceptionVerticeNoValido("No se puede encontrar el camino de costo minimo al verticee destino");
        }
        System.out.println("Del vertice de partida (" + posDeVerticeOrigen + ") al vertice de llegada (" + posDeVerticeDestino + ") existe ");
        System.out.println("el camino de costo mínimo -> " + camino + " y tiene un costo mínimo de " + controlDeCostos.getCosto(posDeVerticeDestino) + ".");
        //return camino;
        //return controlDeCostos.getCosto(posDeVerticeDestino);
    }

    /**
     * @param controlDeCostos
     * @return
     */
    private int getPosVerticeNoMarcadoDeMenorCosto(RecorridoUtilsCostos controlDeCostos, RecorridoUtils controlDeMarcados) {
        double menorCosto = this.menorCostoNoMarcado(controlDeCostos, controlDeMarcados);
        int posicion = GrafoPesado.POSICION_NO_VALIDA;

        if (menorCosto != RecorridoUtilsCostos.INFINITO) {
            for (int i = 0; i < this.cantidadDeVertices(); i++) {
                if (!controlDeMarcados.estaVerticeMarcado(i)) {
                    double unCosto = controlDeCostos.getCosto(i);
                    if (unCosto <= menorCosto) {
                        menorCosto = unCosto;
                        posicion = i;
                    }
                }
            }
        }
        return posicion;
    }

    /**
     * @param controlDeCostos
     * @param controlDeMarcados
     * @return
     */
    private double menorCostoNoMarcado(RecorridoUtilsCostos controlDeCostos, RecorridoUtils controlDeMarcados) {
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            if (!controlDeMarcados.estaVerticeMarcado(i)) {
                if (controlDeCostos.getCosto(i) != RecorridoUtilsCostos.INFINITO) {
                    return controlDeCostos.getCosto(i);
                }
            }
        }
        return RecorridoUtilsCostos.INFINITO;
    }

    public GrafoPesado algoritmoDeKruscal() {
        GrafoPesado grafoAuxiliar = new GrafoPesado();

        return grafoAuxiliar;
    }

    private void insertarPosOrigenDestinoYPeso() {

        for (int i = 0; i < this.cantidadDeAristas(); i++) {
            List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(i);
            for (AdyacenteConPeso unAdyacenteConPeso : adyacentesDelVerticeOrigen) {
                int posVerticeOrigen = i;
                int posVerticeDestino = unAdyacenteConPeso.getIndiceDeVertice();

                double costo = unAdyacenteConPeso.getPeso();
                AristaConCosto unaAristaConCosto = new AristaConCosto(posVerticeOrigen, posVerticeDestino, costo);
                this.listaDeAristasConCosto.add(unaAristaConCosto);
            }
        }
    }

    private boolean estaAdyacenciaProcesado(int posDeVerticeOrigen, int posDeVerticeDestino) {
        if (existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)
                || existeAdyacencia(posDeVerticeDestino, posDeVerticeOrigen)){
            return false;
        }
        return false;
    }
}
