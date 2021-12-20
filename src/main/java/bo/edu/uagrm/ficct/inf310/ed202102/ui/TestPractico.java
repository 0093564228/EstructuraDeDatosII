package bo.edu.uagrm.ficct.inf310.ed202102.ui;

import bo.edu.uagrm.ficct.inf310.ed202102.arboles.*;

import java.util.*;

import java.util.Scanner;

public class TestPractico {
    public static void main(String[] args) throws ExceptionClaveNoExiste, ExceptionOrdenNoValido {

        //Probando método insertar de arbol binario.-----------------------------------------------------------------
        IArbolBusqueda<Integer, String> arbolBinario1 = new ArbolBinarioBusqueda<>();

        arbolBinario1.insertar(80, "nom1");
        arbolBinario1.insertar(120, "nom2");
        arbolBinario1.insertar(200, "nom3");
        arbolBinario1.insertar(50, "nom4");
        arbolBinario1.insertar(70, "nom5");
        arbolBinario1.insertar(75, "nom6");
        arbolBinario1.insertar(98, "nom7");
        arbolBinario1.insertar(110, "nom8");
        arbolBinario1.insertar(130, "nom9");
        arbolBinario1.insertar(140, "nom10");
        arbolBinario1.insertar(150, "nom11");
        arbolBinario1.insertar(400, "nom12");
        arbolBinario1.insertar(500, "nom13");
        arbolBinario1.insertar(560, "nom14");
        arbolBinario1.insertar(72, "nom15");
        arbolBinario1.insertar(134, "nom16");
        arbolBinario1.insertar(160, "nom17");
        arbolBinario1.insertar(170, "nom18");
        arbolBinario1.insertar(190, "nom19");
        arbolBinario1.insertar(158, "nom20");

        //Insertando claves y valores en otro árbol binario para probar si son similares en estructura

        IArbolBusqueda<Integer, String> arbolBinario2 = new ArbolBinarioBusqueda<>();

        arbolBinario2.insertar(80, "nom1");
        arbolBinario2.insertar(120, "nom2");
        arbolBinario2.insertar(200, "nom3");
        arbolBinario2.insertar(50, "nom4");
        arbolBinario2.insertar(70, "nom5");
        arbolBinario2.insertar(75, "nom6");
        arbolBinario2.insertar(98, "nom7");
        arbolBinario2.insertar(110, "nom8");
        arbolBinario2.insertar(130, "nom9");
        arbolBinario2.insertar(140, "nom10");
        arbolBinario2.insertar(150, "nom11");
        arbolBinario2.insertar(400, "nom12");
        arbolBinario2.insertar(500, "nom13");
        arbolBinario2.insertar(560, "nom14");
        arbolBinario2.insertar(72, "nom15");
        arbolBinario2.insertar(134, "nom16");
        arbolBinario2.insertar(160, "nom17");
        arbolBinario2.insertar(170, "nom18");
        arbolBinario2.insertar(190, "nom19");
        arbolBinario2.insertar(158, "nom20");

        //Probando método insertar de arbol AVL.-----------------------------------------------------------------
        IArbolBusqueda<Integer, String> arbolAVL = new AVL<>();

        System.out.println("1. Respuesta al enunciado: Método insertar de árbol AVL: ");
        arbolAVL.insertar(80, "nom1");
        arbolAVL.insertar(120, "nom2");
        arbolAVL.insertar(200, "nom3");
        arbolAVL.insertar(50, "nom4");
        arbolAVL.insertar(70, "nom5");
        arbolAVL.insertar(75, "nom6");
        arbolAVL.insertar(98, "nom7");
        arbolAVL.insertar(110, "nom8");
        arbolAVL.insertar(130, "nom9");
        arbolAVL.insertar(140, "nom10");
        arbolAVL.insertar(150, "nom11");
        arbolAVL.insertar(400, "nom12");
        arbolAVL.insertar(500, "nom13");
        arbolAVL.insertar(560, "nom14");
        arbolAVL.insertar(72, "nom15");
        arbolAVL.insertar(134, "nom16");
        arbolAVL.insertar(160, "nom17");
        arbolAVL.insertar(170, "nom18");
        arbolAVL.insertar(190, "nom19");
        arbolAVL.insertar(158, "nom20");

        //Probando método insertar de árbol arbol M-Vias.-----------------------------------------------------------------

        IArbolBusqueda<Integer, String> arbolMVias1 = new ArbolMViasBusqueda<>(4);

        arbolMVias1.insertar(80, "nom1");
        arbolMVias1.insertar(120, "nom2");
        arbolMVias1.insertar(200, "nom3");
        arbolMVias1.insertar(50, "nom4");
        arbolMVias1.insertar(70, "nom5");
        arbolMVias1.insertar(75, "nom6");
        arbolMVias1.insertar(98, "nom7");
        arbolMVias1.insertar(110, "nom8");
        arbolMVias1.insertar(130, "nom9");
        arbolMVias1.insertar(140, "nom10");
        arbolMVias1.insertar(150, "nom11");
        //claves y valores comentados para probar el método eliminar con la clave 200 y verificar si son similares.
//        arbolMVias1.insertar(400, "nom12");
//        arbolMVias1.insertar(500, "nom13");
//        arbolMVias1.insertar(560, "nom14");
        arbolMVias1.insertar(72, "nom15");
        arbolMVias1.insertar(134, "nom16");
        arbolMVias1.insertar(160, "nom17");
        arbolMVias1.insertar(170, "nom18");
        arbolMVias1.insertar(190, "nom19");
        arbolMVias1.insertar(158, "nom20");

        //Insertando claves y valores en otro árbol m-vias para probar si son similares en estructura

        IArbolBusqueda<Integer, String> arbolMVias2 = new ArbolMViasBusqueda<>(4);

        arbolMVias2.insertar(80, "nom1");
        arbolMVias2.insertar(120, "nom2");
        arbolMVias2.insertar(200, "nom3");
        arbolMVias2.insertar(50, "nom4");
        arbolMVias2.insertar(70, "nom5");
        arbolMVias2.insertar(75, "nom6");
        arbolMVias2.insertar(98, "nom7");
        arbolMVias2.insertar(110, "nom8");
        arbolMVias2.insertar(130, "nom9");
        arbolMVias2.insertar(140, "nom10");
        arbolMVias2.insertar(150, "nom11");
        arbolMVias2.insertar(400, "nom12");
        arbolMVias2.insertar(500, "nom13");
        arbolMVias2.insertar(560, "nom14");
        arbolMVias2.insertar(72, "nom15");
        arbolMVias2.insertar(134, "nom16");
        arbolMVias2.insertar(160, "nom17");
        arbolMVias2.insertar(170, "nom18");
        arbolMVias2.insertar(190, "nom19");
        arbolMVias2.insertar(158, "nom20");

        System.out.println("Recorrido InORden: " + arbolAVL.recorridoEnInOrden());
        System.out.println("Size: " + arbolAVL.size());
        // Probando método eliminar de AVL con recorrido InOrden.------------------------------------------------------------
        System.out.println("2. Respuesta al enunciado: Método eliminar de árbol AVL: ");
        System.out.println("Se elimino el dato: " + arbolAVL.eliminar(200));
        System.out.println("Se elimino el dato: " + arbolAVL.eliminar(110));
        System.out.println("Recorrido InOrden: " + arbolAVL.recorridoEnInOrden());
        System.out.println("Size: " + arbolAVL.size());
        //------------------------------------------------------------------------------------------------------------
        System.out.println("5. Respuesta al enunciado: Método insertar de árbol M-Vias: ");
        System.out.println("Recorrido InORden: " + arbolMVias1.recorridoEnInOrden());
        System.out.println("Size: " + arbolMVias1.size());
        // Probando método eliminar de M-Vias con recorrido InOrden.------------------------------------------------------------
        System.out.println("6. Respuesta al enunciado: Método eliminar de árbol M-Vias: ");
        System.out.println("Se elimino el dato: " + arbolMVias1.eliminar(200));
        System.out.println("Se elimino el dato: " + arbolMVias1.eliminar(80));
        System.out.println("Recorrido InORden: " + arbolMVias1.recorridoEnInOrden());
        System.out.println("Size: " + arbolMVias1.size());


        System.out.println("7. Respuesta al enunciado: cantidad de nodos que tienen ambos hijos distintos de vacio" +
                " en un árbol binario: " + ((ArbolBinarioBusqueda<Integer, String>) arbolBinario1).cantidadDeNodosConAmbosHijosNoVacios());
        System.out.println("8. Respuesta al enunciado: cantidad de nodos que tienenun solo hijo no vacio: "
                + ((ArbolBinarioBusqueda<Integer, String>) arbolBinario1).cantidadDeNodosConUnSoloHijo());
        System.out.println("9. Respuesta al enunciado: número de hijos vacios que tiene un árbol binario: "
                + ((ArbolBinarioBusqueda<Integer, String>) arbolBinario1).cantidadDeHijosVacios());

        /*
         * Declaro listas de claves y valores en postorden e inorden para reconstruir el árbol.
         */
        List<Integer> listaDeClavesInOrden = arbolBinario1.recorridoEnInOrden();
        List<String> listaDeValoresInOrden = ((ArbolBinarioBusqueda<Integer, String>) arbolBinario1).listaDeValores(listaDeClavesInOrden);

        List<Integer> listaDeClavesPostOrden = arbolBinario1.recorridoEnPostOrden();
        List<String> listaDeValoresPostOrden = ((ArbolBinarioBusqueda<Integer, String>) arbolBinario1).listaDeValores(listaDeClavesPostOrden);

        arbolBinario1 = new ArbolBinarioBusqueda<>(listaDeClavesPostOrden, listaDeValoresPostOrden,
                listaDeClavesInOrden, listaDeValoresInOrden, false);

        System.out.println("10. Método reconstruir con recorrido PostOrden e InOrden, probando con recorrido InOrden: " + arbolBinario1.recorridoEnInOrden());


        System.out.println("11. Respuesta al enunciado: predecesor InORden: "
                + ((ArbolBinarioBusqueda<Integer, String>) arbolBinario1).predecesorInOrden(200));
        System.out.println("12. Respuesta al enunciado: hay nodos completos en el nivel en un árbol m-vias: "
                + ((ArbolMViasBusqueda<Integer, String>) arbolMVias1).sonNodosCompletosEnELNivel(0));

        System.out.println("13. Respuesta al enunciado: Implemente una clase ArbolBinarioBusquedaEnteroCadena que usando como base " +
                "el ArbolBinarioBusqueda ya no sea un árbol genérico, si no un árbol binario de búsqueda con claves enteras y valores cadena.");

        //Insertando claves y valores en árbol entero cadena.
        ArbolBinarioBusquedaEnteroCadena arbolEnteroCadena = new ArbolBinarioBusquedaEnteroCadena();
        arbolEnteroCadena.insertar(50, "nom1");
        arbolEnteroCadena.insertar(20, "nom2");
        arbolEnteroCadena.insertar(75, "nom3");
        arbolEnteroCadena.insertar(10, "nom4");
        arbolEnteroCadena.insertar(90, "nom5");
        arbolEnteroCadena.insertar(25, "nom6");
        arbolEnteroCadena.insertar(70, "nom7");
        arbolEnteroCadena.insertar(80, "nom8");
        //Probando método elimnar de árbol entero cadena.
        System.out.println("Recorrido InOrden de arbolEnteroCadena: " + arbolEnteroCadena.recorridoEnInOrden());
        System.out.println("Eliminando el 90 y 50 respectivamente.");
        arbolEnteroCadena.eliminar(90);
        arbolEnteroCadena.eliminar(50);
        System.out.println("Recorrido InOrden de arbolEnteroCadena: " + arbolEnteroCadena.recorridoEnInOrden());



        System.out.println("14. Respuesta al enunciado: son árboles M-Vias similares: "
                + ((ArbolMViasBusqueda<Integer, String>) arbolMVias1).esArbolSimilar((ArbolMViasBusqueda<Integer, String>) arbolMVias2));
        System.out.println("15. Respuesta al enunciado: son árboles binarios similares: "
                + ((ArbolBinarioBusqueda<Integer, String>) arbolBinario1).esArbolSimilar((ArbolBinarioBusqueda<Integer, String>) arbolBinario2));


    }
}
