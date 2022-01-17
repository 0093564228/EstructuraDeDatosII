package bo.edu.uagrm.ficct.inf310.ed202102.ui.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos.BFS;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.recorridos.DFS;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.nopesados.Digrafo;

import java.util.Scanner;

public class TestDigrafoNoPesado {
    public static void main(String[] args) throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {

        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el número de vertices para su digrafo: ");
        String nro = entrada.next();
        int nro1 = Integer.parseInt(nro);

        Digrafo digrafoDePrueba = new Digrafo(nro1);

        System.out.println();
// aristas comentadaas de ordenamiento topologico
//        digrafoDePrueba.insertarArista(0, 1);
//        digrafoDePrueba.insertarArista(1, 3);
//        digrafoDePrueba.insertarArista(3, 2);
//        digrafoDePrueba.insertarArista(0, 3);
//        digrafoDePrueba.insertarArista(2, 0);

        digrafoDePrueba.insertarArista(0, 1);
        digrafoDePrueba.insertarArista(2, 0);
        digrafoDePrueba.insertarArista(4, 5);
        digrafoDePrueba.insertarArista(5, 4);
        digrafoDePrueba.insertarArista(6, 4);

        DFS dfsPrueba = new DFS(digrafoDePrueba, 0);
        BFS bfsPrueba = new BFS(digrafoDePrueba, 0);

        System.out.println("Recorrido DFS: " + dfsPrueba.obtenerRecorrido());
        System.out.println("Recorrido BFS: " + bfsPrueba.obtenerRecorrido());

        System.out.println("Cantidad de vertices en el digrafo: " + digrafoDePrueba.cantidadDeVertices());
        System.out.println("Grado de entrada de un vertice en el grafo: " + ((Digrafo) digrafoDePrueba).gradoDeEntradaDeVertice(0));
        System.out.println("Grado de salida de un vertice en el grafo: " + ((Digrafo) digrafoDePrueba).gradoDeSalidaDeUnVertice(0));
        System.out.println("Cantidad de aristas en el digrafo: "
                + digrafoDePrueba.cantidadDeAristas());
        System.out.println("Hay ciclo en el digrafo: " + ((Digrafo) digrafoDePrueba).hayCiclo());
        System.out.println("Es fuertemente conexo el digrafo: " + ((Digrafo) digrafoDePrueba).esFuertementeConexo());
        System.out.println("Es debilmente conexo el digrafo: " + ((Digrafo) digrafoDePrueba).esDebilmenteConexo());
        System.out.println("Cantidad de islas en el digrafo: " + ((Digrafo) digrafoDePrueba).nroDeIslasEnUnDigrafo());
        System.out.println("Ordenamiento topologico del digrafo: " + ((Digrafo) digrafoDePrueba).ordenamientoTopologico());
        digrafoDePrueba.componentesDeLasIslasDeUnDigrafo();
        //insetar arista, eliminar arista, eliminar vertice,
    }
}