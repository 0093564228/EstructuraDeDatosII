package bo.edu.uagrm.ficct.inf310.ed202102.ui;

import bo.edu.uagrm.ficct.inf310.ed202102.arboles.*;

//import bo.edu.uagrm.ficct.inf310.ed202102.arboles.ExceptionClaveNoExiste;
//import bo.edu.uagrm.ficct.inf310.ed202102.arboles.ExceptionOrdenNoValido;

import java.util.Scanner;

public class TestArbol {
    public static void main (String []args) throws ExceptionOrdenNoValido, ExceptionClaveNoExiste {
        IArbolBusqueda<Integer, String> arbolDePrueba;

        Scanner entrada = new Scanner(System.in);

        System.out.println("Elija un tipo de árbol (ABB, AVL, AMV): ");
        String tipoArbol = entrada.next();
        switch (tipoArbol) {
            case "ABB":
                arbolDePrueba = new ArbolBinarioBusqueda<>();
                break;
            case "AVL":
                arbolDePrueba = new AVL<>();
                break;
            case "AMV":
                arbolDePrueba = new ArbolMViasBusqueda<>(4);
                break;
            default:
                System.out.println("Tipo de árbol invalido. Usando AVL");
                arbolDePrueba = new AVL<>();
                break;
        }

        if (arbolDePrueba instanceof ArbolBinarioBusqueda) {
            System.out.println();
        }
        /*
        System.out.println("Size:" + arbolDePrueba.size());
        System.out.println("Size recursivo: " + ((ArbolBinarioBusqueda)arbolDePrueba).sizeRecursivo());
        System.out.println("1. Cantidad de nodos que tiene solo un hijo diferente de vacio iterativo: "
                + ((ArbolBinarioBusqueda)arbolDePrueba).cantidadDeNodosConUnSoloHijoIterativo());
        System.out.println("1. Cantidad de nodos que tiene solo un hijo diferente de vacio  recursivo: "
                + ((ArbolBinarioBusqueda<Integer, String>) arbolDePrueba).cantidadDeNodosConUnSoloHijoRecursivo());
        System.out.println("2. Desarrollar un metodo que retorne verdadero si los nodos que no " +
                "son hojas en el arbol solo tienen un hijo. Falso en caso contrario(Iterativo): "
                + ((ArbolBinarioBusqueda<Integer, String>) arbolDePrueba).esSoloUnHijoIterativo());
        System.out.println("2. Desarrollar un metodo que retorne verdadero si los nodos que no" +
                " son hojas en el arbol solo tienen un hijo. Falso en caso contrario(Recursivo): "
                + ((ArbolBinarioBusqueda<Integer, String>) arbolDePrueba).esSoloUnHijoRecursivo());
        System.out.println("3. Desarrollar un metodo que retorne verdadero si los nodos que no son hojas" +
                " antes del nivel n en el arbol solo tienen un hijo. Falso en caso contrario(Iterativo): " +
                ((ArbolBinarioBusqueda<Integer, String>) arbolDePrueba).esSoloUnHijoAntesDeNivelIterativo(2));
        System.out.println("3. Desarrollar un metodo que retorne verdadero si los nodos que no son hojas" +
                " antes del nivel n en el arbol solo tienen un hijo. Falso en caso contrario(Recursivo): " +
                ((ArbolBinarioBusqueda<Integer, String>) arbolDePrueba).esSoloUnHijoAntesdelNivelRecursivo(2));*/
        //-----------------------------------------------------------------------------------------------



        arbolDePrueba.insertar(80, "nom1");
        arbolDePrueba.insertar(120, "nom2");
        arbolDePrueba.insertar(200, "nom3");
        arbolDePrueba.insertar(50, "nom4");
        arbolDePrueba.insertar(70, "nom5");
        arbolDePrueba.insertar(75, "nom6");
        arbolDePrueba.insertar(98, "nom7");
        arbolDePrueba.insertar(110, "nom8");
        arbolDePrueba.insertar(130, "nom9");
        arbolDePrueba.insertar(140, "nom10");
        arbolDePrueba.insertar(150, "nom11");
//        arbolDePrueba.insertar(400, "nom12");
//        arbolDePrueba.insertar(500, "nom13");
//        arbolDePrueba.insertar(560, "nom14");
        arbolDePrueba.insertar(72, "nom15");
        arbolDePrueba.insertar(134, "nom16");
        arbolDePrueba.insertar(160, "nom17");
        arbolDePrueba.insertar(170, "nom18");
        arbolDePrueba.insertar(190, "nom19");
        arbolDePrueba.insertar(158, "nom20");


        System.out.println("Recorrido en PreOrden: " + arbolDePrueba.recorridoEnPreOrden());
        System.out.println("Recorrido en InOrden: " + arbolDePrueba.recorridoEnInOrden());
        System.out.println("Recorrido por niveles: " + arbolDePrueba.recorridoPorNiveles());
        System.out.println("Recorrido en PostOrden: " + arbolDePrueba.recorridoEnPostOrden());
        System.out.println("Tamaño del arbol: " + arbolDePrueba.size());


        System.out.println("Se elimino el dato: " + arbolDePrueba.eliminar(200));
        System.out.println("Recorrido por niveles: " + arbolDePrueba.recorridoPorNiveles());
    //    System.out.println("Se elimino el dato: " + arbolDePrueba.eliminar(160));
       // System.out.println("Recorrido por niveles: " + arbolDePrueba.recorridoPorNiveles());

//          System.out.println("Valor de la clave a buscar: " + arbolDePrueba.buscar(110));
//          System.out.println("size: " + arbolDePrueba.size());
//          System.out.println("altura: " + arbolDePrueba.altura());
//          System.out.println("Cantidad de claves no vacias: " + ((ArbolMViasBusqueda<Integer, String>)arbolDePrueba).cantidadDeClavesNoVacias());
//          System.out.println("Cantidad de claves vacias: " + ((ArbolMViasBusqueda<Integer, String>)arbolDePrueba).cantidadDeClavesVacias());
        //práctico ArbolMViasBusqueda

        if (arbolDePrueba instanceof ArbolMViasBusqueda) {
            System.out.println("Cantidad de hijos vacios en el arbol: " + ((ArbolMViasBusqueda<Integer, String>) arbolDePrueba).cantidadDeHijosVacios());
            System.out.println("Cantidad de hijos vacios en el arbol en el nivel N: " + ((ArbolMViasBusqueda<Integer, String>) arbolDePrueba).cantidadDeHijosVaciosEnElNivelN(2));
            System.out.println("Cantidad de hijos vacios en el arbol antes del nivel N: " + ((ArbolMViasBusqueda<Integer, String>) arbolDePrueba).cantidadDeHijosVaciosAntesDelNivelN(2));
            System.out.println("Cantidad de hijos vacios en el arbol a partir del nivel N: " + ((ArbolMViasBusqueda<?, ?>) arbolDePrueba).cantidadDeHijosVaciasAPartirDelNivelN(2));
        }




    }

}
