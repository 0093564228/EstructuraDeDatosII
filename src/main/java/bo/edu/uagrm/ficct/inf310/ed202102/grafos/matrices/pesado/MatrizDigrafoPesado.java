package bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.pesado;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Digrafo;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.DigrafoPesado;

public class MatrizDigrafoPesado {
    double[][] matrizAdyacencia;
    private static final double INFINITO = 999999999;

    public MatrizDigrafoPesado() {
        this.matrizAdyacencia = new double[0][0];
    }

    /**
     *
     * @param nroDeVertices
     * @throws ExcepcionNumVerticesInvalido
     */
    public MatrizDigrafoPesado(int nroDeVertices, DigrafoPesado digrafoAuxiliarPesado) throws ExcepcionNumVerticesInvalido {
        digrafoAuxiliarPesado = new DigrafoPesado(nroDeVertices);
        this.matrizAdyacencia = new double[nroDeVertices][nroDeVertices];
        for (int i = 0; i < this.matrizAdyacencia.length; i++) {
            for (int j = 0; j < this.matrizAdyacencia[i].length; j++) {
                if (i == j) {
                    this.matrizAdyacencia[i][j] = 0;
                } else {
                    this.matrizAdyacencia[i][j] = MatrizDigrafoPesado.INFINITO;//INFINITO representa que no hay posicion de vertice adyacente.
                }
            }
        }
    }

    /**
     *
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaYaExiste
     */
    public void insertarAristaYSuPeso(int posVerticeOrigen, int posVerticeDestino, double peso) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] = peso;
    }

    /**
     *
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaNoExiste
     */
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] = MatrizDigrafoPesado.INFINITO;
    }

    /**
     *
     * @param posDeVerticeAEliminar
     */
    public void eliminarVertice(int posDeVerticeAEliminar) {

    }

    /**
     *
     */
    public void mostrarMatrizDeAdyacencia() {
        for (int i = 0; i < this.matrizAdyacencia.length; i++) {
            for (int j = 0; j < this.matrizAdyacencia[i].length; j++) {
                System.out.print("[" + this.matrizAdyacencia[i][j] + "]");
            }
            System.out.println();
        }
    }

    /**
     *
     * @return
     */
    public int cantidadDeVertices() {
        return this.matrizAdyacencia.length;
    }

    /**
     *
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @return
     */
    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        this.validarVertice(posVerticeOrigen);
        this.validarVertice(posVerticeDestino);
        if (this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] != MatrizDigrafoPesado.INFINITO) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param posVertice
     * @return
     */
    public int gradoDeEntradaDeUnVertice(int posVertice) {
        this.validarVertice(posVertice);
        int cantidad = 0;
        for (int i = 0; i < this.matrizAdyacencia.length; i++) {
            if (this.matrizAdyacencia[i][posVertice] != MatrizDigrafoPesado.INFINITO) {
                cantidad++;
            }
        }
        return cantidad;
    }

    /**
     *
     * @param posVertice
     * @return
     */
    public int gradoDeSalidaDeUnVertice(int posVertice) {
        this.validarVertice(posVertice);
        int cantidad = 0;
        for (int i = 0; i < this.matrizAdyacencia[posVertice].length; i++) {
            if (this.matrizAdyacencia[posVertice][i] != MatrizDigrafoPesado.INFINITO) {
                cantidad++;
            }
        }
        return cantidad;
    }

    /**
     *
     * @param posVertice
     */
    public void validarVertice(int posVertice) {
        if (posVertice < 0 || posVertice >= this.cantidadDeVertices()) {//rango
            throw new IllegalArgumentException("No existe vértice en la posición" +
                    posVertice + " en su grafo");//Esto es una excepcion en tiempo de ejecución.
        }
    }


    public boolean hayCiclo() {
        return false;
    }

    public boolean hayCamino() {
        return false;
    }

    public double[][] getMatrizAdyacencia() {
        return this.matrizAdyacencia;
    }
}
