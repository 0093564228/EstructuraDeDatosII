package bo.edu.uagrm.ficct.inf310.ed202102.ui.grafos.pesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExceptionVerticeNoValido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.pesado.MatrizDePredecesores;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.pesado.MatrizDigrafoPesado;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.AlgoritmoDeFloyd;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.DigrafoPesado;

public class TestDigrafoPesado {
    public static void main(String[] args) throws ExcepcionNumVerticesInvalido
            , ExcepcionAristaYaExiste, ExcepcionAristaNoExiste, ExceptionVerticeNoValido {
        DigrafoPesado digrafoPesado = new DigrafoPesado(7);

        digrafoPesado.insertarArista(0, 1, 50);
        digrafoPesado.insertarArista(0, 2, 10);
        digrafoPesado.insertarArista(0, 4, 60);
        digrafoPesado.insertarArista(0, 5, 100);

        digrafoPesado.insertarArista(1, 3, 50);
        digrafoPesado.insertarArista(1, 4, 15);

        digrafoPesado.insertarArista(2, 1, 5);

        digrafoPesado.insertarArista(3, 0, 80);
        digrafoPesado.insertarArista(3, 5, 20);

        digrafoPesado.insertarArista(4, 5, 20);

        digrafoPesado.insertarArista(5, 1, 40);
        digrafoPesado.insertarArista(5, 2, 70);

        System.out.println("Cantidad de vertices: " + digrafoPesado.cantidadDeVertices());
        System.out.println("Cantidad de aristas: " + digrafoPesado.cantidadDeAristas());
        System.out.println("Grado de entrada de un vertice: " + digrafoPesado.gradoDeEntradaDeVertice(2));
        System.out.println("Grado de salida de un vertice: " + digrafoPesado.gradoDeSalidaDeUnVertice(0));
        //System.out.println("Peso de 2 a 3: " + digrafoPesado.peso(2, 3));

        //System.out.println("Camino de costo minimo: " + digrafoPesado.algoritmoDijkstra(6, 0));

        DigrafoPesado digrafoPesadoPrueba = new DigrafoPesado();
        MatrizDigrafoPesado matrizPrueba = new MatrizDigrafoPesado(5, digrafoPesadoPrueba);
        matrizPrueba.insertarAristaYSuPeso(0,1,1);
        matrizPrueba.insertarAristaYSuPeso(1,3,4);
        matrizPrueba.insertarAristaYSuPeso(1,4,7);
        matrizPrueba.insertarAristaYSuPeso(2,0,3);
        matrizPrueba.insertarAristaYSuPeso(2,1,2);
        matrizPrueba.insertarAristaYSuPeso(2,4,4);
        matrizPrueba.insertarAristaYSuPeso(3,0,6);
        matrizPrueba.insertarAristaYSuPeso(3,4,2);
        matrizPrueba.insertarAristaYSuPeso(4,3,3);
        matrizPrueba.mostrarMatrizDeAdyacencia();

        MatrizDePredecesores predecesoresPrueba = new MatrizDePredecesores(5, digrafoPesadoPrueba);
        predecesoresPrueba.mostrarMatrizDeAdyacencia();
        AlgoritmoDeFloyd floydPrueba = new AlgoritmoDeFloyd(matrizPrueba);
        System.out.println("-----------------------------------------------------------------");
        floydPrueba.mostrarMatrizDeAdyacencia();
        floydPrueba.procesarAlgoritmoFloyd();
        System.out.println("-----------------------------------------------------------------");
        floydPrueba.mostrarMatrizDeAdyacencia();
        System.out.println("Camino de costo minimo: ");
        floydPrueba.caminoDeCostoMinimo(4, 1);
    }
}