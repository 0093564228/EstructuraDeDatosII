package bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.no_pesado.MatrizDigrafo;

public class AlgoritmoWarshall {
    private byte[][] matrizDeCamino;

    public AlgoritmoWarshall() {
        this.matrizDeCamino = new byte[0][0];
    }

    /**
     *
     * @param matrizDigrafoAuxiliar
     */
    public AlgoritmoWarshall(MatrizDigrafo matrizDigrafoAuxiliar) {
        int cantidadDeVertices = matrizDigrafoAuxiliar.cantidadDeVertices();
        byte[][] matrizAdyacencia = matrizDigrafoAuxiliar.getMatrizAdyacencia();
        this.matrizDeCamino = new byte[cantidadDeVertices][cantidadDeVertices];
        for (int i = 0; i < cantidadDeVertices; i++) {
            for (int j = 0; j < cantidadDeVertices; j++) {
                this.matrizDeCamino[i][j] = matrizAdyacencia[i][j];
            }
        }
    }


    public void mostrarMatrizDeCamino() {
        for (int i = 0; i < this.matrizDeCamino.length; i++) {
            for (int j = 0; j < this.matrizDeCamino[i].length; j++) {
                System.out.print("[" + this.matrizDeCamino[i][j] + "]");
            }
            System.out.println();
        }
    }

    /**
    Precondicion: existe un digrafo para procesar
     */
    public void procesarAlgoritmoWarshall() {
        for (int pivote = 0; pivote < this.matrizDeCamino.length; pivote++) {
            for (int i = 0; i < this.matrizDeCamino.length; i++) {
                for (int j = 0; j < this.matrizDeCamino[i].length; j++) {
                    if (this.matrizDeCamino[i][j] == 1
                            || (this.matrizDeCamino[i][pivote] == 1 && this.matrizDeCamino[pivote][j] == 1)) {
                        this.matrizDeCamino[i][j] = 1;
                    }
                }
            }
        }
    }

    /**
     *
     * @param posVertice
     */
    public void validarVertice(int posVertice) {
        if (posVertice < 0 || posVertice >= this.matrizDeCamino.length) {//rango
            throw new IllegalArgumentException("No existe vértice en la posición" +
                    posVertice + " en su grafo");//Esto es una excepcion en tiempo de ejecución.
        }
    }

    /**
     * falta probar
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @return
     */
    public boolean hayCamino(int posVerticeOrigen, int posVerticeDestino) {
        this.validarVertice(posVerticeOrigen);
        this.validarVertice(posVerticeDestino);
        if (this.matrizDeCamino[posVerticeOrigen][posVerticeDestino] == 1) {
            return true;
        }
        return false;
    }

    /**
     * falta probar
     * @return
     */
    public boolean hayCiclo() {
        for (int i = 0; i < this.matrizDeCamino.length; i++) {
            if (this.matrizDeCamino[i][i] == 1) {
                return true;
            }
        }
        return false;
    }
    public void ordenamientoToplogico(int posVerticePartida) {

    }


}
