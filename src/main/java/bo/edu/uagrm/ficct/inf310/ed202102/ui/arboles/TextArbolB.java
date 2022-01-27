/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310.ed202102.ui.arboles;

import bo.edu.uagrm.ficct.inf310.ed202102.arboles.ArbolB;
import bo.edu.uagrm.ficct.inf310.ed202102.arboles.ArbolBinarioBusqueda;
import bo.edu.uagrm.ficct.inf310.ed202102.arboles.ExceptionClaveNoExiste;
import bo.edu.uagrm.ficct.inf310.ed202102.arboles.ExceptionOrdenNoValido;
import bo.edu.uagrm.ficct.inf310.ed202102.arboles.IArbolBusqueda;

/**
 *
 * @author abel1
 */
public class TextArbolB {

    public static void main(String[] args) throws ExceptionOrdenNoValido, ExceptionClaveNoExiste {
        IArbolBusqueda<Integer, String> arbolDePrueba = new ArbolBinarioBusqueda<>();
        arbolDePrueba.insertar(50, "nom1");

        arbolDePrueba.insertar(20, "nom2");
System.out.println("Es monticulo: " + ((ArbolBinarioBusqueda<Integer, String>) arbolDePrueba).esMonticulo());
        arbolDePrueba.insertar(60, "nom3");

        arbolDePrueba.insertar(80, "nom4");
        arbolDePrueba.insertar(25, "nom5");
        arbolDePrueba.insertar(26, "nom6");
        arbolDePrueba.insertar(27, "nom7");
        arbolDePrueba.insertar(100, "nom8");
        arbolDePrueba.insertar(200, "nom9");
        arbolDePrueba.insertar(300, "nom10");
        arbolDePrueba.insertar(51, "nom11");
        arbolDePrueba.insertar(55, "nom12");
        arbolDePrueba.insertar(71, "nom10");
        /**
         * arbolDePrueba.insertar(150, "nom11");
         * arbolDePrueba.insertar(400,"nom12"); arbolDePrueba.insertar(500,
         * "nom13"); arbolDePrueba.insertar(560, "nom14");
         * arbolDePrueba.insertar(72,"nom15"); arbolDePrueba.insertar(134,
         * "nom16"); arbolDePrueba.insertar(160, "nom17");
         * arbolDePrueba.insertar(170, "nom18"); arbolDePrueba.insertar(190,
         * "nom19"); arbolDePrueba.insertar(158, "nom20");*
         */

        System.out.println("Recorrido en PreOrden: " + arbolDePrueba.recorridoEnPreOrden());
        System.out.println("Recorrido en InOrden: " + arbolDePrueba.recorridoEnInOrden());
        System.out.println("Recorrido por niveles: " + arbolDePrueba.recorridoPorNiveles());
        System.out.println("Recorrido en PostOrden: " + arbolDePrueba.recorridoEnPostOrden());
        System.out.println("Tamanio del arbol: " + arbolDePrueba.size());
    }

}
