package bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorrido_utils.RecorridoUtils;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorrido_utils.RecorridoUtils2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Digrafo extends Grafo {
    private static int POSICION_NO_VALIDA = -1;
    RecorridoUtils controlDeMarcados1;
    RecorridoUtils2 controlDeMarcados2;

    /**
     * 1)
     */
    public Digrafo() {
        super();
    }

    /**
     * 2)
     *
     * @param nroDeVertices
     * @throws ExcepcionNumVerticesInvalido
     */
    public Digrafo(int nroDeVertices) throws ExcepcionNumVerticesInvalido {
        super(nroDeVertices);
        this.controlDeMarcados2 = new RecorridoUtils2(nroDeVertices);
    }

    /**
     * 3)
     * * @return
     */
    @Override
    public int cantidadDeAristas() {
        int cantidad = 0;
        for (int i = 0; i < super.cantidadDeVertices(); i++) {
            List<Integer> adyacentesDelVertice = super.listaDeAdyacencias.get(i);
            cantidad = cantidad + adyacentesDelVertice.size();
        }

        return cantidad;
        //return super.cantidadDeAristas();
    }

    /**
     * 4)
     *
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
     *
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaNoExiste
     */
    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste("La arista no existe en su grafo.");
        }

        List<Integer> listaDeAdayacenciaOrigen = super.listaDeAdyacencias.get(posVerticeOrigen);
        int indexOfVerticeDestinoEnAdyacencia = listaDeAdayacenciaOrigen.indexOf(posVerticeDestino);
        listaDeAdayacenciaOrigen.remove(indexOfVerticeDestinoEnAdyacencia);

        //super.eliminarArista(posVerticeOrigen, posVerticeDestino);
    }

    /**
     * 6)
     *
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
     *
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
     *
     * @param posVertice
     * @return
     */
    public int gradoDeSalidaDeUnVertice(int posVertice) {
        return super.gradoDeVertice(posVertice);
    }


    /**
     * @return
     */
    public boolean hayCiclo() {
        if (super.cantidadDeVertices() == 0) {
            return false;
        }

        this.controlDeMarcados2.desmarcarTodos();
        while (!controlDeMarcados2.estanTodosProcesados()) {
            int posVerticeNoMarcado = this.getPosVerticeNoMarcado();
            if (hayCiclo(posVerticeNoMarcado, controlDeMarcados2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param posDeVerticeEnTurno
     * @param controlDeMarcados
     * @return
     */
    private boolean hayCiclo(int posDeVerticeEnTurno, RecorridoUtils2 controlDeMarcados) {
        controlDeMarcados.marcarVertice(posDeVerticeEnTurno, RecorridoUtils2.MARCADO);
        //recorrido.add(posDeVerticeEnTurno);
        List<Integer> adyacentesDeVerticeEnTurno = super.listaDeAdyacencias.get(posDeVerticeEnTurno);
        if (adyacentesDeVerticeEnTurno.isEmpty()) { //pregunto si posDeVerticeEnTurno tiene o no adyacentes
            controlDeMarcados.marcarVertice(posDeVerticeEnTurno, RecorridoUtils2.PROCESADO);
        }
        for (Integer posDeVerticeAdy : adyacentesDeVerticeEnTurno) {
            if (controlDeMarcados.getPosMarcado(posDeVerticeAdy) != RecorridoUtils2.NO_MARCADO) {
                if (controlDeMarcados.getPosMarcado(posDeVerticeAdy) != RecorridoUtils2.PROCESADO)
                    return true;
            }

            if (!controlDeMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                if (hayCiclo(posDeVerticeAdy, controlDeMarcados)) {
                    return true;
                }
            }
        }
        //visite todos los adyacentes de posDeVerticeEnTurno? si, marco como PROCESADO; no, no hago nada
        if (this.estanTodosLosAdyacentesProcesados(posDeVerticeEnTurno)) {
            controlDeMarcados.marcarVertice(posDeVerticeEnTurno, RecorridoUtils2.PROCESADO);
        }

        return false;
    }

    /**
     * @param posDeVertice
     * @return
     */
    private boolean estanTodosLosAdyacentesProcesados(int posDeVertice) {
        List<Integer> adyacentesDeVertice = super.listaDeAdyacencias.get(posDeVertice);
        for (Integer posDeVerticeAdy : adyacentesDeVertice) {
            if (controlDeMarcados2.getPosMarcado(posDeVerticeAdy) != RecorridoUtils2.PROCESADO) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return
     */
    private int getPosVerticeNoMarcado() {
        int i = 0;
        for (Integer marcado : controlDeMarcados2.getMarcados()) {
            if (marcado == RecorridoUtils2.NO_MARCADO) {
                return i;
            }
            i++;
        }
        return -1;//posicion no valida
    }

    /**
     * @return
     */
    public boolean esFuertementeConexo() {
        if (super.cantidadDeVertices() == 0) {
            return false;
        }
        super.controlDeMarcados.desmarcarTodos();
        for (int i = 0; i < super.cantidadDeVertices(); i++) {
            super.marcarVerticesAdyacentes(i, super.controlDeMarcados);
            if (!super.controlDeMarcados.estanTodosMarcados()) {
                return false;
            }
            super.controlDeMarcados.desmarcarTodos();
        }
        return true;
    }

    /**
     * se puede solucionar con:
     * matriz de adyacencia o lista de adyacencia
     *
     * @return
     */
    public boolean esDebilmenteConexo() {
        if (super.cantidadDeVertices() == 0) {
            return false;
        }
        super.controlDeMarcados.desmarcarTodos();
        int posDeVertice = 0;

        while (!controlDeMarcados.estanTodosMarcados()) {
            if (posDeVertice != Digrafo.POSICION_NO_VALIDA) {
                super.marcarVerticesAdyacentes(posDeVertice, super.controlDeMarcados);
            }

            if (posDeVertice == Digrafo.POSICION_NO_VALIDA) {
                return false;
            }
            posDeVertice = this.getPosDeVerticeNoMarcadoConAdyMarcado1(super.controlDeMarcados);
        }

        return true;
    }

    /**
     * Utiliza el recorrido auxiliar RecorridoUtils
     * @param controlDeMarcados
     * @return
     */
    private int getPosDeVerticeNoMarcadoConAdyMarcado1(RecorridoUtils controlDeMarcados) {
        for (int i = 0; i < super.cantidadDeVertices(); i++) {
            for (Integer posVerticeAdy : super.adyacentesDeVertice(i)) {
                if (!controlDeMarcados.estaVerticeMarcado(i)) {
                    if (controlDeMarcados.estaVerticeMarcado(posVerticeAdy)) {
                        return i;
                    }
                }
            }
        }
        return Digrafo.POSICION_NO_VALIDA;
    }

    /**
     * Implementar si un digrafo es conexo
     */

    /**
     * No utilizo el método desmarcarVertice de la clase RecorridoUtils por que al momento de desmarcar toma la
     * cantidad de vertices inicial, como concecuencia produce un error al momento de eliminar un vertice y procesar
     * este metodo de nuevo. Para solucionar esto creo una nueva referencia a la clase RecorridoUtils dentro del
     * método para tomar la cantidad actual de vertices.
     * @return
     */
    public int nroDeIslasEnUnDigrafo() {
        int cantidad = 0;
        if (super.cantidadDeVertices() == 0) {
            return cantidad;
        }
        super.controlDeMarcados = new RecorridoUtils(super.cantidadDeVertices());
        //super.controlDeMarcados.desmarcarTodos();
        int posDeVertice = 0;
        while (!controlDeMarcados.estanTodosMarcados()) {

            if (posDeVertice != Digrafo.POSICION_NO_VALIDA) {
                super.marcarVerticesAdyacentes(posDeVertice, super.controlDeMarcados);
            }

            posDeVertice = this.getPosDeVerticeNoMarcadoConAdyMarcado1(super.controlDeMarcados);
            while (posDeVertice != Digrafo.POSICION_NO_VALIDA) {
                super.marcarVerticesAdyacentes(posDeVertice, super.controlDeMarcados);
                posDeVertice = this.getPosDeVerticeNoMarcadoConAdyMarcado1(super.controlDeMarcados);
            }

            cantidad++;
            posDeVertice = super.getPosVerticeNoMarcado(super.controlDeMarcados);
        }
        return cantidad;
    }

    /**
     * @return
     */
    public List<Integer> ordenamientoTopologico() {
        List<Integer> listaOrdenTopologico = new LinkedList<>();
        if (super.cantidadDeVertices() == 0) {
            return listaOrdenTopologico;
        }
        if (!this.esDebilmenteConexo()) {
            return listaOrdenTopologico;
        }
        if (this.hayCiclo()) {
            return listaOrdenTopologico;
        }

        Queue<Integer> colaDeVertices = new LinkedList<>();
        List<Integer> gradoDeEntrada = new LinkedList<>();

        this.meterEnListaGradoDeEntradaDeVertices(gradoDeEntrada);
        this.meterEnColaVerticesConGradoDeEntradaCero(colaDeVertices, gradoDeEntrada);

        while (!colaDeVertices.isEmpty()) {
            int posDeVertice = colaDeVertices.poll();
            listaOrdenTopologico.add(posDeVertice);
            this.decrementarGradoDeEntradaDeVerticeAdy(gradoDeEntrada, posDeVertice);

            if (colaDeVertices.isEmpty()) {
                this.meterEnColaVerticesConGradoDeEntradaCero(colaDeVertices, gradoDeEntrada);
            }
        }

        return listaOrdenTopologico;
    }


    /**
     * @param gradoDeEntrada
     * @param posDeVertice
     */
    private void decrementarGradoDeEntradaDeVerticeAdy(List<Integer> gradoDeEntrada, int posDeVertice) {
        for (Integer posDeVerticeAdy : super.adyacentesDeVertice(posDeVertice)) {
            int gradoDeEntradaDeVerticeAdy = gradoDeEntrada.get(posDeVerticeAdy);
            gradoDeEntrada.set(posDeVerticeAdy, gradoDeEntradaDeVerticeAdy - 1);
        }
        gradoDeEntrada.set(posDeVertice, -1);
    }

    /**
     * @param colaDeVertices
     */
    private void meterEnColaVerticesConGradoDeEntradaCero(Queue<Integer> colaDeVertices
            , List<Integer> gradoDeEntrada) {
        for (int i = 0; i < super.cantidadDeVertices(); i++) {
            if (gradoDeEntrada.get(i) == 0) {
                colaDeVertices.offer(i);
            }
        }
    }

    /**
     * @param gradoDeEntrada
     */
    private void meterEnListaGradoDeEntradaDeVertices(List<Integer> gradoDeEntrada) {
        for (int i = 0; i < super.cantidadDeVertices(); i++) {
            int gradoDeEntradaDeVertice = this.gradoDeEntradaDeVertice(i);
            gradoDeEntrada.add(gradoDeEntradaDeVertice);
        }
    }

    /**
     *
     */
    public void componentesDeLasIslasDeUnDigrafo() throws ExcepcionAristaYaExiste, ExcepcionNumVerticesInvalido {
        Digrafo digrafoAux = new Digrafo(this.cantidadDeVertices());
        int posicionDeIsla = 0;

        this.controlDeMarcados2 = new RecorridoUtils2(super.cantidadDeVertices());
        int posDeVertice = 0;

        while (!this.controlDeMarcados2.estanTodosProcesados()) {

            if (posDeVertice != Digrafo.POSICION_NO_VALIDA) {
                this.marcarVerticesAdyacentes2(posDeVertice, this.controlDeMarcados2);
            }

            posDeVertice = this.getPosDeVerticeNoMarcadoConAdyMarcado2(this.controlDeMarcados2);
            while (posDeVertice != Digrafo.POSICION_NO_VALIDA) {
                this.marcarVerticesAdyacentes2(posDeVertice, this.controlDeMarcados2);
                posDeVertice = this.getPosDeVerticeNoMarcadoConAdyMarcado2(this.controlDeMarcados2);
            }
            this.agregarComponentes(posicionDeIsla, this.controlDeMarcados2, digrafoAux);
            posicionDeIsla++;
            posDeVertice = this.getPosDeVerticeNoMarcado2(this.controlDeMarcados2);
            this.marcarVerticesComoProcesados(this.controlDeMarcados2);
        }

        for (int i = 0; i < this.nroDeIslasEnUnDigrafo(); i++) {
            System.out.println("Isla[" + i +"]: " + digrafoAux.getListaDeAdyacencia(i));
        }
    }

    /**
     *
     * @param posicion
     * @return
     */
    @Override
    protected List<Integer> getListaDeAdyacencia(int posicion) {
        return super.getListaDeAdyacencia(posicion);
    }


    /**
     *
     * @param posDeVertice
     * @param controlDeMarcados2
     */
    @Override
    protected void marcarVerticesAdyacentes2(int posDeVertice, RecorridoUtils2 controlDeMarcados2) {
        super.marcarVerticesAdyacentes2(posDeVertice, controlDeMarcados2);
    }

    /**
     *
     * @param controlDeMarcados2
     */
    @Override
    protected void marcarVerticesComoProcesados(RecorridoUtils2 controlDeMarcados2) {
        super.marcarVerticesComoProcesados(controlDeMarcados2);
    }

    /**
     *
     * @param controlDeMarcados2
     * @return
     */
    @Override
    protected int getPosDeVerticeNoMarcado2(RecorridoUtils2 controlDeMarcados2) {
        return super.getPosDeVerticeNoMarcado2(controlDeMarcados2);
    }

    /**
     *
     * @param posDeIsla
     * @param controlDeMarcados2
     * @param digrafoAux
     * @throws ExcepcionAristaYaExiste
     */
    @Override
    protected void agregarComponentes(int posDeIsla, RecorridoUtils2 controlDeMarcados2, Digrafo digrafoAux) throws ExcepcionAristaYaExiste {
        super.agregarComponentes(posDeIsla, controlDeMarcados2, digrafoAux);
    }

    /**
     * Utiliza el recorrido auxiliar recorridoUtils2(Utiliza 3 estados)
     * @param controlDeMarcados2
     * @return
     */
    private int getPosDeVerticeNoMarcadoConAdyMarcado2(RecorridoUtils2 controlDeMarcados2) {
        List<Integer> marcadosAux = controlDeMarcados2.getMarcados();
        for (int i = 0; i < super.cantidadDeVertices(); i++) {
            for (Integer posVerticeAdy : super.adyacentesDeVertice(i)) {
                if (marcadosAux.get(i) == RecorridoUtils2.NO_MARCADO) {
                    if (controlDeMarcados2.estaVerticeMarcado(posVerticeAdy)) {
                        return i;
                    }
                }
            }
        }
        return Digrafo.POSICION_NO_VALIDA;
    }
}
