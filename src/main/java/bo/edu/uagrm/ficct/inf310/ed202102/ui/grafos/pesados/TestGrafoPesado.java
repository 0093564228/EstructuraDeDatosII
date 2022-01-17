package bo.edu.uagrm.ficct.inf310.ed202102.ui.grafos.pesados;

import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.excepciones.ExceptionVerticeNoValido;
import bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.GrafoPesado;

public class TestGrafoPesado {
    public static void main(String[] args) throws ExcepcionNumVerticesInvalido
            , ExcepcionAristaYaExiste, ExcepcionAristaNoExiste, ExceptionVerticeNoValido {
        GrafoPesado grafoPesado = new GrafoPesado(7);

        grafoPesado.insertarArista(1, 1, 200);

        grafoPesado.insertarArista(0, 1, 50);
        grafoPesado.insertarArista(0, 2, 10);
        grafoPesado.insertarArista(0, 4, 60);
        grafoPesado.insertarArista(0, 5, 100);

        grafoPesado.insertarArista(1, 3, 50);
        grafoPesado.insertarArista(1, 4, 15);

        grafoPesado.insertarArista(2, 1, 5);

        grafoPesado.insertarArista(3, 0, 80);
        grafoPesado.insertarArista(3, 5, 20);

        grafoPesado.insertarArista(4, 5, 20);

        grafoPesado.insertarArista(5, 1, 40);
        grafoPesado.insertarArista(5, 2, 70);

        System.out.println("Cantidad de vertices: " + grafoPesado.cantidadDeVertices());
        System.out.println("Cantidad de aristas: " + grafoPesado.cantidadDeAristas());
        //System.out.println("Peso de 2 a 3: " + grafoPesado.peso(2, 3));
        //System.out.println("Camino de costo minimo" + grafoPesado.algoritmoDijkstra(1, 1));
    }
}
