package bo.edu.uagrm.ficct.inf310.ed202102.ui.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos.BFS;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos.DFS;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Grafo;

import java.util.Scanner;

public class TestGrafoNoPesado {
    public static void main(String[] args) throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        Grafo grafoDePrueba;
        Scanner entrada = new Scanner(System.in);


        System.out.print("Ingrese el número de vertices para su grafo: ");
        String nro = entrada.next();
        int nro1 = Integer.parseInt(nro);

        grafoDePrueba = new Grafo(nro1);
        System.out.println();

        grafoDePrueba.insertarArista(0, 3);
        grafoDePrueba.insertarArista(2, 0);
        grafoDePrueba.insertarArista(4, 7);
        grafoDePrueba.insertarArista(6, 4);


        DFS dfsPrueba = new DFS(grafoDePrueba, 0);
        BFS bfsPrueba = new BFS(grafoDePrueba, 0);


        System.out.println("Recorrido DFS: " + dfsPrueba.obtenerRecorrido());
        System.out.println("Recorrido BFS: " + bfsPrueba.obtenerRecorrido());

        System.out.println("Cantidad de vertices en el grafo: " + grafoDePrueba.cantidadDeVertices());
        System.out.println("Grado de un vertice en el grafo: " + grafoDePrueba.gradoDeVertice(0));
        System.out.println("Cantidad de aristas en el grafo: " + grafoDePrueba.cantidadDeAristas());
        System.out.println("Hay cilo en el grafo: " + grafoDePrueba.hayCiclo());
        System.out.println("Es conexo el grafo: " + grafoDePrueba.esConexoElGrafo());
        System.out.println("Cantidad de islas en el grafo: " + grafoDePrueba.cantidadDeIslasEnElGrafo());//probar con grafo
        grafoDePrueba.componentesDeLasIslasDeUnGrago();
        //insetar arista, eliminar arista, eliminar vertice,
    }
}
