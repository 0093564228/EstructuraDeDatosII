package bo.edu.uagrm.ficct.inf310.ed202102.ui.arboles;

import bo.edu.uagrm.ficct.inf310.ed202102.arboles.ArbolBinarioBusqueda;
import bo.edu.uagrm.ficct.inf310.ed202102.arboles.ExceptionClaveNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.arboles.ExceptionOrdenNoValido;
import bo.edu.uagrm.ficct.inf310.ed202102.arboles.IArbolBusqueda;

public class TestDePrueba {
    public static void main (String []args) throws ExceptionOrdenNoValido, ExceptionClaveNoExiste {
        IArbolBusqueda<Integer, String> arbolDePrueba = new ArbolBinarioBusqueda<>();

        arbolDePrueba.insertar(120, "nom2");
        arbolDePrueba.insertar(80, "nom1");
        //arbolDePrueba.insertar(200, "nom3");
        arbolDePrueba.insertar(50, "nom4");
        arbolDePrueba.insertar(70, "nom5");
        arbolDePrueba.insertar(75, "nom6");
        arbolDePrueba.insertar(76, "nom6");
        arbolDePrueba.insertar(74, "nom6");
//        arbolDePrueba.insertar(98, "nom7");
//        arbolDePrueba.insertar(110, "nom8");
//        arbolDePrueba.insertar(130, "nom9");
//        arbolDePrueba.insertar(140, "nom10");
//        arbolDePrueba.insertar(150, "nom11");
//        arbolDePrueba.insertar(400, "nom12");
//        arbolDePrueba.insertar(500, "nom13");
//        arbolDePrueba.insertar(560, "nom14");
//        arbolDePrueba.insertar(72, "nom15");
//        arbolDePrueba.insertar(134, "nom16");
//        arbolDePrueba.insertar(160, "nom17");
//        arbolDePrueba.insertar(170, "nom18");
//        arbolDePrueba.insertar(190, "nom19");
//        arbolDePrueba.insertar(158, "nom20");


        System.out.println("Recorrido en PreOrden: " + arbolDePrueba.recorridoEnPreOrden());
        System.out.println("Recorrido en InOrden: " + arbolDePrueba.recorridoEnInOrden());
        System.out.println("Recorrido por niveles: " + arbolDePrueba.recorridoPorNiveles());
        System.out.println("Recorrido en PostOrden: " + arbolDePrueba.recorridoEnPostOrden());
        System.out.println("Tamaño del arbol: " + arbolDePrueba.size());

        System.out.println("Es solo un hijo recursivo: " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).esSoloUnHijoRecursivo());
        System.out.println("Es solo un hijo iterativo: " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).esSoloUnHijoIterativo());

    }
}
