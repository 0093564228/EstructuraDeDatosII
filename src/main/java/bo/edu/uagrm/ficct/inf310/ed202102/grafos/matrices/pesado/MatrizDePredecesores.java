package bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.pesado;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.DigrafoPesado;

public class MatrizDePredecesores {
    int[][] matrizAdyacencia;


    public MatrizDePredecesores() {
        this.matrizAdyacencia = new int[0][0];
    }

    /**
     * @param nroDeVertices
     * @throws ExcepcionNumVerticesInvalido
     */
    public MatrizDePredecesores(int nroDeVertices, DigrafoPesado digrafoAuxiliarPesado) throws ExcepcionNumVerticesInvalido {
        digrafoAuxiliarPesado = new DigrafoPesado(nroDeVertices);
        this.matrizAdyacencia = new int[nroDeVertices][nroDeVertices];
        for (int i = 0; i < this.matrizAdyacencia.length; i++) {
            for (int j = 0; j < this.matrizAdyacencia[i].length; j++) {
                this.matrizAdyacencia[i][j] = -1;
            }
        }
    }

    /**
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaYaExiste
     */
    public void insertarAristaYSuPeso(int posVerticeOrigen, int posVerticeDestino, int peso) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] = peso;
    }

    /**
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaNoExiste
     */
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] = -1;
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
     * @return
     */
    public int cantidadDeVertices() {
        return this.matrizAdyacencia.length;
    }

    /**
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @return
     */
    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        this.validarVertice(posVerticeOrigen);
        this.validarVertice(posVerticeDestino);
        if (this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] != -1) {
            return true;
        }
        return false;
    }

    /**
     * @param posVertice
     */
    public void validarVertice(int posVertice) {
        if (posVertice < 0 || posVertice >= this.cantidadDeVertices()) {//rango
            throw new IllegalArgumentException("No existe vértice en la posición" +
                    posVertice + " en su grafo");//Esto es una excepcion en tiempo de ejecución.
        }
    }

    public int[][] getMatrizAdyacencia() {
        return this.matrizAdyacencia;
    }
}
