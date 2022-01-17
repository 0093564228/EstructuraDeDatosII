package bo.edu.uagrm.ficct.inf310.ed202102.ui.grafos.pesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExceptionVerticeNoValido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Grafo;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos.BFS;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos.DFS;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.GrafoPesado;

public class TestGrafoPesado {
    public static void main(String[] args) throws ExcepcionNumVerticesInvalido
            , ExcepcionAristaYaExiste, ExcepcionAristaNoExiste, ExceptionVerticeNoValido {
        GrafoPesado grafoPesado = new GrafoPesado(10);

        grafoPesado.insertarArista(0, 1, 5);
        grafoPesado.insertarArista(0, 2, 10);
        grafoPesado.insertarArista(0, 3, 8);

        grafoPesado.insertarArista(1, 3, 6);
        grafoPesado.insertarArista(1, 5, 5);

        grafoPesado.insertarArista(2, 3, 7);
        grafoPesado.insertarArista(2, 4, 8);
        grafoPesado.insertarArista(2, 7, 15);

        grafoPesado.insertarArista(3, 4, 5);
        grafoPesado.insertarArista(3, 5, 11);

        grafoPesado.insertarArista(4, 6, 4);
        grafoPesado.insertarArista(4, 7, 3);

        grafoPesado.insertarArista(5, 6, 9);
        grafoPesado.insertarArista(5, 8, 7);

        grafoPesado.insertarArista(6, 7, 12);
        grafoPesado.insertarArista(6, 8, 4);
        grafoPesado.insertarArista(6, 9, 6);

        grafoPesado.insertarArista(7, 9, 12);

        grafoPesado.insertarArista(8, 9, 7);


        GrafoPesado unGrafo1 = grafoPesado.algoritmoDeKruscal();
        Grafo unGrafoNoPEsado = grafoPesado.transformarGrafoPesadoANoPesado(unGrafo1);

        BFS bfsPrueba = new BFS(unGrafoNoPEsado, 0);
        System.out.println("BFS: "+ bfsPrueba.obtenerRecorrido());

        DFS dfsPrueba = new DFS(unGrafoNoPEsado, 0);
        System.out.println("BFS: "+ dfsPrueba.obtenerRecorrido());

        System.out.println("Cantidad de vertices: " + grafoPesado.cantidadDeVertices());
        System.out.println("Cantidad de aristas: " + grafoPesado.cantidadDeAristas());
        //System.out.println("Peso de 2 a 3: " + grafoPesado.peso(2, 3));
        //System.out.println("Camino de costo minimo" + grafoPesado.algoritmoDijkstra(1, 1));
    }
}
