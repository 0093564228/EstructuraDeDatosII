package bo.edu.uagrm.ficct.inf310.ed202102.ui.grafos.matrices;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.AlgoritmoWarshall;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Digrafo;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.no_pesado.MatrizDigrafo;

public class TestMatriz {

    public static void main(String[] args) throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste,
            ExcepcionAristaNoExiste {
        Digrafo digrafoPrueba = new Digrafo();

        MatrizDigrafo matrizPrueba = new MatrizDigrafo(5, digrafoPrueba);

        matrizPrueba.insertarArista(0,1);
        matrizPrueba.insertarArista(1,3);
        matrizPrueba.insertarArista(3,1);
        matrizPrueba.insertarArista(1,4);
        matrizPrueba.insertarArista(4,2);
        matrizPrueba.insertarArista(2,4);
        matrizPrueba.insertarArista(2,2);

        matrizPrueba.mostrarMatrizDeAdyacencia();
      //  matrizPrueba.eliminarArista(3,2);
//        matrizPrueba.eliminarArista(0,4);
//        System.out.println("--------------------------");
//        matrizPrueba.mostrarMatrizDeAdyacencia();
        System.out.println("Cantidad de vertices: " + matrizPrueba.cantidadDeVertices());
        System.out.println("Grado de entrada de un vertice: " + matrizPrueba.gradoDeEntradaDeUnVertice(1));
        System.out.println("Grado de salida de un vertice: " + matrizPrueba.gradoDeSalidaDeUnVertice(3));
        //-----------------------------------------------------------------
        System.out.println("--------------------------");
        System.out.println("Algoritmo de warshall");
        System.out.println("Matriz inicial: ");

        AlgoritmoWarshall warshallPrueba = new AlgoritmoWarshall(matrizPrueba);
        warshallPrueba.mostrarMatrizDeCamino();
        warshallPrueba.procesarAlgoritmoWarshall();
        System.out.println("--------------------------");
        System.out.println("Matriz de camino final:");
        warshallPrueba.mostrarMatrizDeCamino();
    }
}
