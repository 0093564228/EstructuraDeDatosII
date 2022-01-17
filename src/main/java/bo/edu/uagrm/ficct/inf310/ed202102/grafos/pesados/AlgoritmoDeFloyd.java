package bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.pesado.MatrizDePredecesores;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.pesado.MatrizDigrafoPesado;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.AlgoritmoWarshall;

import java.util.LinkedList;
import java.util.List;

public class AlgoritmoDeFloyd {
    double[][] matrizAdyacencia;
    int[][] matrizPredecesores;
    private static final int INFINITO = 999999999;

    public AlgoritmoDeFloyd() {
        this.matrizAdyacencia = new double[0][0];
        this.matrizPredecesores = new int[0][0];
    }


    public AlgoritmoDeFloyd(MatrizDigrafoPesado matriAuxiliarPesado) {
        int cantVertices = matriAuxiliarPesado.cantidadDeVertices();
        this.matrizAdyacencia = new double[cantVertices][cantVertices];
        this.matrizPredecesores = new int[cantVertices][cantVertices];
        double[][] matrizAdyacencia = matriAuxiliarPesado.getMatrizAdyacencia();
        //int[][] matrizAdyacencia2 = matrizDePredecesores.getMatrizAdyacencia();
        for (int i = 0; i < this.matrizAdyacencia.length; i++) {
            for (int j = 0; j < this.matrizAdyacencia[i].length; j++) {
                this.matrizAdyacencia[i][j] = matrizAdyacencia[i][j];
                this.matrizPredecesores[i][j] = -1;
            }
        }
    }

    /**
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
     */
    public void mostrarMatrizDeAdyacencia() {
        for (int i = 0; i < this.matrizAdyacencia.length; i++) {
            for (int j = 0; j < this.matrizAdyacencia[i].length; j++) {
                System.out.print("[" + this.matrizAdyacencia[i][j] + "]");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
        for (int i = 0; i < this.matrizPredecesores.length; i++) {
            for (int j = 0; j < this.matrizPredecesores[i].length; j++) {
                System.out.print("[" + this.matrizPredecesores[i][j] + "]");
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
        if (this.matrizAdyacencia[posVerticeOrigen][posVerticeDestino] != AlgoritmoDeFloyd.INFINITO) {
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

    /**
     *
     * @return
     */
    public double[][] getMatrizAdyacencia() {
        return this.matrizAdyacencia;
    }

    /**
     *
     */
    public void procesarAlgoritmoFloyd() {
        for (int pivote = 0; pivote < this.matrizAdyacencia.length; pivote++) {
            for (int i = 0; i < this.matrizAdyacencia.length; i++) {
                for (int j = 0; j < this.matrizAdyacencia[i].length; j++) {
                    if (i != j) {
                        if (this.matrizAdyacencia[i][j] > this.matrizAdyacencia[i][pivote] + this.matrizAdyacencia[pivote][j]) {
                            this.matrizAdyacencia[i][j] = this.matrizAdyacencia[i][pivote] + this.matrizAdyacencia[pivote][j];
                            this.matrizPredecesores[i][j] = pivote;
                        }
                    }
                }
            }
        }
    }

    /**
     * Procesa el algoritmo de Floyd luego muesta el camino de costo minimo y su costo.
     * @param posDeVerticeOrigen
     * @param posDeVerticeDestino
     * @return
     * @throws ExcepcionAristaNoExiste
     */
    public void caminoDeCostoMinimo(int posDeVerticeOrigen, int posDeVerticeDestino) throws ExcepcionAristaNoExiste {
        List<Integer> camino = new LinkedList<>();
        if (posDeVerticeOrigen != posDeVerticeDestino) {
            if (this.matrizAdyacencia[posDeVerticeOrigen][posDeVerticeDestino]
                    != AlgoritmoDeFloyd.INFINITO) {
                camino.add(posDeVerticeOrigen);
                this.caminoDeCostoMinimo(posDeVerticeOrigen, posDeVerticeDestino, camino);
                camino.add(posDeVerticeDestino);
            }
        }

        if (camino.isEmpty()) {
            throw new ExcepcionAristaNoExiste("No existe camino de costo minimo...");
        }
        System.out.println("Del vertice de partida (" + posDeVerticeOrigen + ") al vertice de llegada (" + posDeVerticeDestino + ") existe ");
        System.out.println("el camino de costo mínimo -> " + camino + " y tiene un costo mínimo de " + this.matrizAdyacencia[posDeVerticeOrigen][posDeVerticeDestino] + ".");
        //return camino;
    }

    /**
     *
     * @param posDeVerticeOrigen
     * @param posDeVerticeDestino
     * @param camino
     * @throws ExcepcionAristaNoExiste
     */
    private void caminoDeCostoMinimo(int posDeVerticeOrigen, int posDeVerticeDestino, List<Integer> camino) throws ExcepcionAristaNoExiste {
        if (this.matrizPredecesores[posDeVerticeOrigen][posDeVerticeDestino] == -1) {
            return;
        }
        int posDeVerticeEnturno = this.matrizPredecesores[posDeVerticeOrigen][posDeVerticeDestino];
        this.caminoDeCostoMinimo(posDeVerticeOrigen, posDeVerticeEnturno, camino);
        camino.add(posDeVerticeEnturno);
        this.caminoDeCostoMinimo(posDeVerticeEnturno, posDeVerticeDestino, camino);
    }

}
