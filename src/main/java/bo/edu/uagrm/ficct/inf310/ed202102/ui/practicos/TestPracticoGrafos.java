package bo.edu.uagrm.ficct.inf310.ed202102.ui.practicos;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExceptionVerticeNoValido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.no_pesado.MatrizDigrafo;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.pesado.MatrizDePredecesores;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.matrices.pesado.MatrizDigrafoPesado;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.AlgoritmoWarshall;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Digrafo;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Grafo;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos.BFS;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos.DFS;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.AlgoritmoDeFloyd;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.DigrafoPesado;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.GrafoPesado;

import java.util.Scanner;

public class TestPracticoGrafos {
    public static void main(String[] args) throws ExcepcionAristaYaExiste
            , ExcepcionNumVerticesInvalido, ExcepcionAristaNoExiste, ExceptionVerticeNoValido {

        //--------------------------------------------------------------------------------------




//        Scanner entrada3 = new Scanner(System.in);
//        System.out.print("Ingrese el número de vertices para su grafo pesado: ");
//        String nro31 = entrada3.next();
//        int nro32 = Integer.parseInt(nro31);
//        GrafoPesado grafoDePruebaPesado = new GrafoPesado(nro32);

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Respuesta al enunciado 1: ");
        System.out.println("Métodos a probar con (grafoDePruebaNoPesado)");
        Grafo grafoDePruebaNoPesado = new Grafo(10);
        grafoDePruebaNoPesado.insertarArista(0, 1);
        grafoDePruebaNoPesado.insertarArista(0, 2);
        grafoDePruebaNoPesado.insertarArista(0, 6);
        grafoDePruebaNoPesado.insertarArista(1, 3);
        grafoDePruebaNoPesado.insertarArista(1, 4);
        grafoDePruebaNoPesado.insertarArista(2, 4);
        grafoDePruebaNoPesado.insertarArista(3, 4);
        grafoDePruebaNoPesado.insertarArista(3, 5);
        grafoDePruebaNoPesado.insertarArista(4, 6);
        grafoDePruebaNoPesado.insertarArista(5, 6);
        grafoDePruebaNoPesado.insertarArista(5, 8);
        grafoDePruebaNoPesado.insertarArista(6, 7);
        grafoDePruebaNoPesado.insertarArista(6, 8);
        grafoDePruebaNoPesado.insertarArista(6, 9);
        System.out.println( "10 vertices insertados");
        System.out.println("Cantidad de aristas en el grafo no pesado: " + grafoDePruebaNoPesado.cantidadDeAristas());
        System.out.println("Grado del vertice 0 en el grafo no pesado: " + grafoDePruebaNoPesado.gradoDeVertice(0));
        System.out.println("Cantidad de vertices en el grafo no pesado: " + grafoDePruebaNoPesado.cantidadDeVertices());
        grafoDePruebaNoPesado.eliminarArista(6,7);
        System.out.println("Arista eliminada: (6, 7)");
        grafoDePruebaNoPesado.eliminarVertice(0);
        System.out.println("Vertice eliminado: 0");
        System.out.println("Cantidad de vertices  despues de eliminar el vertice 0 en el grafo no pesado: " + grafoDePruebaNoPesado.cantidadDeVertices());
        System.out.println("Cantidad de aristas despues de eliminar el vertice 0 y arista (6, 7) en el grafo no pesado: " + grafoDePruebaNoPesado.cantidadDeAristas());
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Respuesta al enunciado 2: ");
        System.out.println("Métodos a probar con (digrafoDePruebaNoPesado)");
        Digrafo digrafoDePruebaNoPesado = new Digrafo(10);
        digrafoDePruebaNoPesado.insertarArista(0, 1);
        digrafoDePruebaNoPesado.insertarArista(0, 2);
        digrafoDePruebaNoPesado.insertarArista(0, 6);
        digrafoDePruebaNoPesado.insertarArista(1, 3);
        digrafoDePruebaNoPesado.insertarArista(2, 4);
        digrafoDePruebaNoPesado.insertarArista(3, 4);
        digrafoDePruebaNoPesado.insertarArista(4, 1);
        digrafoDePruebaNoPesado.insertarArista(5, 3);
        digrafoDePruebaNoPesado.insertarArista(5, 6);
        digrafoDePruebaNoPesado.insertarArista(6, 4);
        digrafoDePruebaNoPesado.insertarArista(6, 7);
        digrafoDePruebaNoPesado.insertarArista(6, 8);
        digrafoDePruebaNoPesado.insertarArista(8, 5);
        digrafoDePruebaNoPesado.insertarArista(9, 6);
        System.out.println("10 vertices insertados");
        System.out.println("Cantidad de aristas en el digrafo no pesado: " + digrafoDePruebaNoPesado.cantidadDeAristas());
        System.out.println("Grado de entrada de vertice 6 en el digrafo no pesado: " + digrafoDePruebaNoPesado.gradoDeEntradaDeVertice(6));
        System.out.println("Grado de salida de vertice 6 en el digrafo no pesado: " + digrafoDePruebaNoPesado.gradoDeSalidaDeUnVertice(5));
        System.out.println("Cantidad de vertices en el digrafo no pesado: " + digrafoDePruebaNoPesado.cantidadDeVertices());
        digrafoDePruebaNoPesado.eliminarArista(0,2);
        System.out.println("Arista eliminada: (0, 2)");
        digrafoDePruebaNoPesado.eliminarVertice(6);
        System.out.println("Vertice eliminado: 6");
        System.out.println("Cantidad de vertices  despues de eliminar el vertice 6 en el digrafo no pesado: " + digrafoDePruebaNoPesado.cantidadDeVertices());
        System.out.println("Cantidad de aristas despues de eliminar el vertice 6 y arista (0, 2) en el digrafo no pesado: " + digrafoDePruebaNoPesado.cantidadDeAristas());
        System.out.println("----------------------------------------------------------------------------------");
        //3. Para un grafo dirigido implementar método o clase para encontrar si hay ciclos sin usar matriz de caminos.
        System.out.println("Respuesta al enunciado 3: ");
        System.out.println("Método a probar con (digrafoDePruebaNoPesado)");
        System.out.println("Hay ciclo en el digrafo no pesado: " + digrafoDePruebaNoPesado.hayCiclo());
        System.out.println("----------------------------------------------------------------------------------");
        //4. Para un grafo dirigido implementar método o clase para encontrar si hay ciclos usando la matriz de caminos
        System.out.println("Respuesta al enunciado 4: ");
        System.out.println("Método a probar con (warshallPrueba1)");
        Digrafo digrafoParaMatriz1 = new Digrafo();//utlizo un digrafo para obtener la amtriz de adyacencia
        MatrizDigrafo matrizDirigidoPrueba1 = new MatrizDigrafo(5, digrafoParaMatriz1);
        matrizDirigidoPrueba1.insertarArista(0, 1);
        matrizDirigidoPrueba1.insertarArista(1, 4);
        matrizDirigidoPrueba1.insertarArista(1, 3);
        matrizDirigidoPrueba1.insertarArista(3, 1);
        matrizDirigidoPrueba1.insertarArista(4, 2);
        matrizDirigidoPrueba1.insertarArista(2, 4);
        matrizDirigidoPrueba1.insertarArista(2, 2);
        AlgoritmoWarshall warshallPrueba1 = new AlgoritmoWarshall(matrizDirigidoPrueba1);//cargo una matriz de adyacencia para verificar si hay ciclo con el algoritmo de warshall
        warshallPrueba1.procesarAlgoritmoWarshall();
        System.out.println("Hay ciclo en el digrafo no pesado, con matriz de camino(precondicion: algoritmo de warshall procesado): "
                + warshallPrueba1.hayCiclo());//hay ciclo con matriz de camino
        System.out.println("----------------------------------------------------------------------------------");
        //5. Para un grafo dirigido implementar un método o clase que sea capas de retornar los componentes
        // de las islas que existen en dicho digrafo.
        System.out.println("Respuesta al enunciado 5: ");
        System.out.println("Método a probar con (digrafoDePruebaNoPesado2)");
        System.out.println("Componentes de las islas que existe en un digrafo: ");
        Digrafo digrafoDePruebaNoPesado2 = new Digrafo(7);
        digrafoDePruebaNoPesado2.insertarArista(0, 1);
        digrafoDePruebaNoPesado2.insertarArista(2, 0);
        digrafoDePruebaNoPesado2.insertarArista(4, 5);
        digrafoDePruebaNoPesado2.insertarArista(5, 4);
        digrafoDePruebaNoPesado2.insertarArista(6, 4);
        digrafoDePruebaNoPesado2.componentesDeLasIslasDeUnDigrafo();

        System.out.println("----------------------------------------------------------------------------------");
        //6. Para un grafo dirigido implemente un método o clase para encontrar la matriz de caminos
        // de dicho grafo dirigido
        System.out.println("Respuesta al enunciado 6: ");
        System.out.println("Método a probar con (matrizDigrafoPrueba2)");
        Digrafo digrafoParaMatriz2 = new Digrafo();
        MatrizDigrafo matrizDigrafoPrueba2 = new MatrizDigrafo(5, digrafoParaMatriz2);
        matrizDigrafoPrueba2.insertarArista(0, 1);
        matrizDigrafoPrueba2.insertarArista(1, 4);
        matrizDigrafoPrueba2.insertarArista(1, 3);
        matrizDigrafoPrueba2.insertarArista(3, 1);
        matrizDigrafoPrueba2.insertarArista(4, 2);
        matrizDigrafoPrueba2.insertarArista(2, 4);
        matrizDigrafoPrueba2.insertarArista(2, 2);
        System.out.println("Matriz de camino de un digrafo (digrafoParaMatriz2), sin haber procesado el algoritmo de warshall: ");
        matrizDigrafoPrueba2.mostrarMatrizDeAdyacencia();// matriz de camino inicial sin procesar el algoritmo de warshall.
        System.out.println("----------------------------------------------------------------------------------");
        //7. Para un grafo dirigido implementar un método o clase que permita determinar si el digrafo
        // es débilmente conexo
        System.out.println("Respuesta al enunciado 7: ");
        System.out.println("Método a probar con (digrafoDePruebaNoPesado)");
        System.out.println("Es digrafo debilmente conexo: " + digrafoDePruebaNoPesado.esDebilmenteConexo());
        System.out.println("----------------------------------------------------------------------------------");
        //8. Para un grafo dirigido implementar un método o clase que permita determinar si el digrafo es
        // fuertemente conexo
        System.out.println("Respuesta al enunciado 8: ");
        System.out.println("Método a probar con (digrafoDePruebaNoPesado)");
        System.out.println("Es digrafo fuertemente conexo: " + digrafoDePruebaNoPesado.esFuertementeConexo());
        System.out.println("----------------------------------------------------------------------------------");
        //9. Para un grafo no dirigido implementar un método o clase que permita encontrar si en dicho grafo hay ciclo.
        System.out.println("Respuesta al enunciado 9: ");
        System.out.println("Método a probar con (grafoDePruebaNoPesado)");
        System.out.println("Hay ciclo en el grafo no pesado: " + grafoDePruebaNoPesado.hayCiclo());
        System.out.println("----------------------------------------------------------------------------------");
        //10. Para un grafo no dirigido implementar método o clase para encontrar los componentes de las
        // diferentes islas que hay en dicho grafo
        System.out.println("Respuesta al enunciado 10: ");
        System.out.println("Método a probar con (grafoDePruebaNoPesado1)");
        System.out.println("Componentes de las islas que existe en un grafo: ");
        Grafo grafoDePruebaNoPesado1 = new Grafo(8);
        grafoDePruebaNoPesado1.insertarArista(0, 3);
        grafoDePruebaNoPesado1.insertarArista(2, 0);
        grafoDePruebaNoPesado1.insertarArista(4, 7);
        grafoDePruebaNoPesado1.insertarArista(6, 4);
        grafoDePruebaNoPesado1.componentesDeLasIslasDeUnGrago();
        System.out.println("----------------------------------------------------------------------------------");
        //11. Para un grafo dirigido implementar un algoritmo para encontrar el número de islas que hay en el grafo
        System.out.println("Respuesta al enunciado 11: ");
        System.out.println("Método a probar con (digrafoDePruebaNoPesado)");
        System.out.println("Número de islas que hay en un digrafo no pesado: " + digrafoDePruebaNoPesado.nroDeIslasEnUnDigrafo());
        System.out.println("----------------------------------------------------------------------------------");
        //12. Para un grafo dirigido implementar el algoritmo de Wharsall, que luego muestre entre que vértices
        // hay camino.
        System.out.println("Respuesta al enunciado 12: ");
        System.out.println("Método a probar con (warshallPrueba2)");
        Digrafo digrafoParaMatriz3 = new Digrafo();
        MatrizDigrafo matrizDigrafoPrueba3 = new MatrizDigrafo(5, digrafoParaMatriz3);
        matrizDigrafoPrueba3.insertarArista(0, 1);
        matrizDigrafoPrueba3.insertarArista(1, 4);
        matrizDigrafoPrueba3.insertarArista(1, 3);
        matrizDigrafoPrueba3.insertarArista(3, 1);
        matrizDigrafoPrueba3.insertarArista(4, 2);
        matrizDigrafoPrueba3.insertarArista(2, 4);
        matrizDigrafoPrueba3.insertarArista(2, 2);
        AlgoritmoWarshall warshallPrueba2 = new AlgoritmoWarshall(matrizDigrafoPrueba3);
        warshallPrueba2.procesarAlgoritmoWarshall();//<-algoritmo warshall
        warshallPrueba2.mostrarMatrizDeCamino();
        System.out.println("Entre que vertices hay camino(Precondicion algoritmo de warshall procesado): ");
        System.out.println("----------------------------------------------------------------------------------");
        //13. Para un grafo dirigido usando la implementación del algoritmo de Floyd-Wharsall encontrar los
        // caminos de costo mínimo entre un vértice a y el resto. Mostrar los costos y cuáles son los caminos
        System.out.println("Respuesta al enunciado 13: ");
        System.out.println("Método a probar con (floydPrueba)");
        DigrafoPesado digrafoPesadoParaMatriz = new DigrafoPesado();
        MatrizDigrafoPesado matrizPrueba = new MatrizDigrafoPesado(5, digrafoPesadoParaMatriz);
        matrizPrueba.insertarAristaYSuPeso(0,1,1);
        matrizPrueba.insertarAristaYSuPeso(1,3,4);
        matrizPrueba.insertarAristaYSuPeso(1,4,7);
        matrizPrueba.insertarAristaYSuPeso(2,0,3);
        matrizPrueba.insertarAristaYSuPeso(2,1,2);
        matrizPrueba.insertarAristaYSuPeso(2,4,4);
        matrizPrueba.insertarAristaYSuPeso(3,0,6);
        matrizPrueba.insertarAristaYSuPeso(3,4,2);
        matrizPrueba.insertarAristaYSuPeso(4,3,3);
        AlgoritmoDeFloyd floydPrueba = new AlgoritmoDeFloyd(matrizPrueba);
        floydPrueba.procesarAlgoritmoFloyd();//<- algoritmo Floyd-Warshall
        floydPrueba.caminoDeCostoMinimo(4, 1);//<- caminos de costo minimo
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Respuesta al enunciado 14: ");
        System.out.println("----------------------------------------------------------------------------------");
        //15. Para un grafo dirigido pesado implementar el algoritmo de Dijkstra que muestre con que vértices
        // hay caminos de costo mínimo partiendo desde un vértice v, con qué costo y cuáles son los caminos.
        System.out.println("Respuesta al enunciado 15: ");
        System.out.println("Método a probar con digrafoDePruebaPesado");
        DigrafoPesado digrafoDePruebaPesado = new DigrafoPesado(6);
        //DigrafoPesado digrafoDePruebaPesado = new DigrafoPesado(7);
        digrafoDePruebaPesado.insertarArista(0, 1, 50);
        digrafoDePruebaPesado.insertarArista(0, 2, 10);
        digrafoDePruebaPesado.insertarArista(0, 4, 60);
        digrafoDePruebaPesado.insertarArista(0, 5, 100);
        digrafoDePruebaPesado.insertarArista(1, 3, 50);
        digrafoDePruebaPesado.insertarArista(1, 4, 15);
        digrafoDePruebaPesado.insertarArista(2, 1, 5);
        digrafoDePruebaPesado.insertarArista(3, 0, 80);
        digrafoDePruebaPesado.insertarArista(3, 5, 20);
        digrafoDePruebaPesado.insertarArista(4, 5, 20);
        digrafoDePruebaPesado.insertarArista(5, 1, 40);
        digrafoDePruebaPesado.insertarArista(5, 2, 70);
        digrafoDePruebaPesado.algoritmoDijkstra(0, 5);
        System.out.println("----------------------------------------------------------------------------------");
        //16. Para un grafo no dirigido pesado implementar el algoritmo de Kruskal que muestre cual es el
        // grafo encontrado por el algoritmo
        System.out.println("Respuesta al enunciado 16: ");
        System.out.println("Método a probar con grafoDePruebaPesado");
        GrafoPesado grafoDePruebaPesado = new GrafoPesado(10);

        grafoDePruebaPesado.insertarArista(0, 1, 5);
        grafoDePruebaPesado.insertarArista(0, 2, 10);
        grafoDePruebaPesado.insertarArista(0, 3, 8);

        grafoDePruebaPesado.insertarArista(1, 3, 6);
        grafoDePruebaPesado.insertarArista(1, 5, 5);

        grafoDePruebaPesado.insertarArista(2, 3, 7);
        grafoDePruebaPesado.insertarArista(2, 4, 8);
        grafoDePruebaPesado.insertarArista(2, 7, 15);

        grafoDePruebaPesado.insertarArista(3, 4, 5);
        grafoDePruebaPesado.insertarArista(3, 5, 11);

        grafoDePruebaPesado.insertarArista(4, 6, 4);
        grafoDePruebaPesado.insertarArista(4, 7, 3);

        grafoDePruebaPesado.insertarArista(5, 6, 9);
        grafoDePruebaPesado.insertarArista(5, 8, 7);

        grafoDePruebaPesado.insertarArista(6, 7, 12);
        grafoDePruebaPesado.insertarArista(6, 8, 4);
        grafoDePruebaPesado.insertarArista(6, 9, 6);

        grafoDePruebaPesado.insertarArista(7, 9, 12);

        grafoDePruebaPesado.insertarArista(8, 9, 7);
        GrafoPesado unGrafo1 = grafoDePruebaPesado.algoritmoDeKruscal();//<- algoritmo de Kruskal
        Grafo unGrafoNoPesado = grafoDePruebaPesado.transformarGrafoPesadoANoPesado(unGrafo1);//para mostrar con los recorridos
        System.out.println("Árbol de expansión de costo mínimo con algoritmo de kruskal, representado con el recorrido  BFS y DFS: ");
        BFS bfsPrueba = new BFS(unGrafoNoPesado, 0);
        System.out.println("BFS: "+ bfsPrueba.obtenerRecorrido());
        DFS dfsPrueba = new DFS(unGrafoNoPesado, 0);
        System.out.println("DFS: "+ dfsPrueba.obtenerRecorrido());

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Respuesta al enunciado 17: ");
        System.out.println("----------------------------------------------------------------------------------");
        //18. Para un grafo dirigido implementar al algoritmo de ordenamiento topológico. Debe mostrar cual
        // es el orden de los vértices según este algoritmo.
        System.out.println("Respuesta al enunciado 18: ");
        System.out.println("Método a probar con digrafoDePruebaNoPesado1");
        Digrafo digrafoDePruebaNoPesado1 = new Digrafo(5);

        digrafoDePruebaNoPesado1.insertarArista(0, 1);
        digrafoDePruebaNoPesado1.insertarArista(0, 2);
        digrafoDePruebaNoPesado1.insertarArista(0, 3);
        digrafoDePruebaNoPesado1.insertarArista(0, 4);
        digrafoDePruebaNoPesado1.insertarArista(1, 3);
        digrafoDePruebaNoPesado1.insertarArista(2, 1);
        digrafoDePruebaNoPesado1.insertarArista(3, 4);
        System.out.println("Ordenamiento topológico: " + digrafoDePruebaNoPesado1.ordenamientoTopologico());
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Respuesta al enunciado 19: ");

        System.out.println("");
    }
}
