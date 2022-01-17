package bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.no_pesado;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Digrafo;

import java.util.Arrays;

//import java.util.*;
public class MatrizDigrafo {
    byte[][] matrizAdyacencia;

    public MatrizDigrafo() {
        this.matrizAdyacencia = new byte[0][0];
    }

    /**
     *
     * @param nroDeVertices
     * @throws ExcepcionNumVerticesInvalido
     */
    public MatrizDigrafo(int nroDeVertices, Digrafo digrafoAuxiliar) throws ExcepcionNumVerticesInvalido {
       digrafoAuxiliar = new Digrafo(nroDeVertices);
       this.matrizAdyacencia = new byte[nroDeVertices][nroDeVertices];
       for (int i = 0; i < this.matrizAdyacencia.length; i++) {
           for (int j = 0; j < this.matrizAdyacencia[i].length; j++) {
               this.matrizAdyacencia[i][j] = 0;//cero representa que no hay posicion de vertice adyacente.
           }
       }
    }

    /**
     *
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaYaExiste
     */
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] = 1;
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
        this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] = 0;

    }

    /**
     *
     * @param posDeVerticeAEliminar
     */
    public void eliminarVertice(int posDeVerticeAEliminar) {

    }

    /**
     *6. Para un grafo dirigido implemente un método o clase para encontrar la matriz de caminos
     */
    public void mostrarMatrizDeAdyacencia() {
        for (int i = 0; i < this.matrizAdyacencia.length; i++) {
            for (int j = 0; j < this.matrizAdyacencia[i].length; j++) {
                System.out.print("[" + this.matrizAdyacencia[i][j] + "]");
            }
            System.out.println();
        }
    }
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
        if (this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] == 1) {
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
            if (this.matrizAdyacencia[i][posVertice] == 1) {
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
                if (this.matrizAdyacencia[posVertice][i] == 1) {
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

    public byte[][] getMatrizAdyacencia() {
        return this.matrizAdyacencia;
    }
}
