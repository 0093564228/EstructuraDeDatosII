package bo.edu.uagrm.ficct.inf310.ed202102.arboles;

import java.util.*;

public class ArbolB<K extends Comparable<K>, V> extends ArbolMViasBusqueda<K, V> {

    private int nroMaximoDeDatos;
    private int nroMinimoDeDatos;
    private int nroMinimoDeHijos;

    /**
     * 1
     */
    public ArbolB() {
        super();
        this.nroMaximoDeDatos = 2;
        this.nroMinimoDeDatos = 1;
        this.nroMinimoDeHijos = 2;
    }

    /**
     * 2
     *
     * @param orden
     * @throws ExceptionOrdenNoValido
     */
    public ArbolB(int orden) throws ExceptionOrdenNoValido {
        super(orden);
        this.nroMaximoDeDatos = super.orden - 1;
        this.nroMinimoDeDatos = this.nroMaximoDeDatos / 2;
        this.nroMinimoDeHijos = this.nroMinimoDeDatos + 1;
    }

    /**
     * 3
     *
     * @param claveAInsertar
     * @param valorAInsertar
     * @throws NullPointerException
     */
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException {
        if (claveAInsertar == NodoMVias.datoVacio()) {
            throw new NullPointerException("No se permiten insertar claves nulas.");
        }

        if (valorAInsertar == NodoMVias.datoVacio()) {
            throw new NullPointerException("No se permiten insertar valores nulas.");
        }

        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<>(super.orden + 1, claveAInsertar, valorAInsertar);
            return;
        }
        //lociga del arbol-B
        Stack<NodoMVias<K, V>> pilaDePadres = new Stack<>();
        NodoMVias<K, V> nodoActual = super.raiz;//tambien es valido colocar super.raiz porque heredamos de la clase ArbolMVias;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            int posicionDeClaveAInsertar = super.getPosicionDeClave(nodoActual, claveAInsertar);
            if (posicionDeClaveAInsertar != ArbolMViasBusqueda.POSICION_NO_VALIDA) {
                nodoActual.setValor(posicionDeClaveAInsertar, valorAInsertar);
                return;
            }
            //no hay la clave en el nodo
            if (nodoActual.esHoja()) {
                super.insertarClaveYValorEnNodo(nodoActual, claveAInsertar, valorAInsertar);
                if (nodoActual.cantidadDeClavesNoVacias() > this.nroMaximoDeDatos) {
                    this.dividir(nodoActual, pilaDePadres);
                }
                return;
            }

            //no es hoja el nodo actual
            int posicionPorDondeBajar = super.getPosicionPorDondeBajar(nodoActual, claveAInsertar);
            pilaDePadres.push(nodoActual);
            nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
        }
    }

    //3.1
    private void dividir(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDePadres) {
        if (pilaDePadres.isEmpty()) {
            NodoMVias<K, V> nodoPadre = new NodoMVias<K, V>(this.orden + 1,
                    nodoActual.getClave(this.nroMinimoDeDatos),
                    nodoActual.getValor(this.nroMinimoDeDatos));
            NodoMVias<K, V> nuevoNodo1 = new NodoMVias<K, V>(this.orden + 1);
            this.insertarClaveYValorEnNuevosHijos(nodoActual, nuevoNodo1);
            nodoPadre.setHijo(0, nodoActual);
            nodoPadre.setHijo(1, nuevoNodo1);
            this.raiz = nodoPadre;
            return;
        }
        NodoMVias<K, V> nodoPadre = pilaDePadres.pop();
        int posicionHijo = this.getPosicionDeHijo(nodoPadre, nodoActual);
        K claveQueSube = nodoActual.getClave(this.nroMinimoDeDatos);
        V valorQueSube = nodoActual.getValor(this.nroMinimoDeDatos);
        NodoMVias<K, V> nuevoHijo = new NodoMVias<>(this.orden + 1);

        super.insertarClaveYValorEnNodo(nodoPadre, claveQueSube, valorQueSube);
        this.insertarClaveYValorEnNuevosHijos(nodoActual, nuevoHijo);
        nodoPadre.setHijo(nodoPadre.cantidadDeClavesNoVacias() - 1, nodoActual);
        nodoPadre.setHijo(nodoPadre.cantidadDeClavesNoVacias(), nuevoHijo);

        //this.dividir(nodoPadre, pilaDePadres);
    }

    private int getPosicionDeHijo(NodoMVias<K, V> nodoPadre, NodoMVias<K, V> nodoHijo) {
        for (int i = 0; i < nodoPadre.cantidadDeClavesNoVacias(); i++) {
            if (nodoPadre.getHijo(i) == nodoHijo) {
                return i;
            }
        }
        return POSICION_NO_VALIDA;
    }

    private void insertarHijoEnNodo(NodoMVias<K, V> nodoActual, NodoMVias<K, V> hijo) {

    }

    private void insertarClaveYValorEnNuevosHijos(NodoMVias<K, V> nodoActual, NodoMVias<K, V> nuevoNodo1) {
        int pivote = this.nroMinimoDeDatos;

        int j = pivote + 1;
        int i = 0;
        int tope = nodoActual.cantidadDeClavesNoVacias();
        while (j < tope) {
            K clave = nodoActual.getClave(j);
            V valor = nodoActual.getValor(j);
            nuevoNodo1.setClave(i, clave);
            nuevoNodo1.setValor(i, valor);
            i++;
            j++;
        }

        j = pivote;
        while (j < tope) {
            nodoActual.setClave(j, (K) NodoMVias.datoVacio());
            nodoActual.setValor(j, (V) NodoMVias.datoVacio());
            j++;
        }

    }

    /**
     * 4
     *
     * @param claveAEliminar
     * @return
     * @throws ExceptionClaveNoExiste
     */
    @Override
    public V eliminar(K claveAEliminar) throws ExceptionClaveNoExiste {
        if (claveAEliminar == (K) NodoMVias.datoVacio()) {
            throw new IllegalArgumentException("Clave a buscar no puede ser nula.");
        }

        Stack<NodoMVias<K, V>> pilaDePadres = new Stack<>();
        NodoMVias<K, V> nodoActual = this.buscarNodoDeDato(claveAEliminar, pilaDePadres);

        if (NodoMVias.esNodoVacio(nodoActual)) {
            throw new ExceptionClaveNoExiste();
        }
        int posicionDeLaClaveAEliminar = super.getPosicionPorDondeBajar(nodoActual, claveAEliminar) - 1;

        V valorARetornar = nodoActual.getValor(posicionDeLaClaveAEliminar);

        if (nodoActual.esHoja()) {
            super.eliminarClaveYValor(nodoActual, posicionDeLaClaveAEliminar);
            if (nodoActual.cantidadDeClavesNoVacias() < this.nroMinimoDeHijos) {
                //si llego aca hay que ajustar
                if (pilaDePadres.isEmpty()) {
                    if (nodoActual.cantidadDeClavesNoVacias() == 0) {
                        super.vaciar();
                    }
                } else {
                    this.prestarseOFusionarse(nodoActual, pilaDePadres);
                }
            }
        } else {
            pilaDePadres.push(nodoActual);
            NodoMVias<K, V> nodoDelPredecesor = this.obternerNodoDeClavePredecesora(pilaDePadres,
                    nodoActual.getHijo(posicionDeLaClaveAEliminar));
            int posicionDelPredecesor = nodoDelPredecesor.cantidadDeClavesNoVacias() - 1;
            K clavePredecesora = nodoDelPredecesor.getClave(posicionDelPredecesor);
            V datoDelPredecesor = nodoDelPredecesor.getValor(posicionDelPredecesor);
            super.eliminarClaveYValor(nodoDelPredecesor, posicionDelPredecesor);
            nodoActual.setClave(posicionDeLaClaveAEliminar, clavePredecesora);
            nodoActual.setValor(posicionDeLaClaveAEliminar, datoDelPredecesor);
            if (nodoDelPredecesor.cantidadDeClavesNoVacias() < this.nroMinimoDeDatos) {
                this.prestarseOFusionarse(nodoDelPredecesor, pilaDePadres);
            }
        }
        return null;
    }

    private NodoMVias<K, V> obternerNodoDeClavePredecesora(Stack<NodoMVias<K, V>> pilaDePadres, NodoMVias<K, V> hijo) {
        return null;
    }

    private void prestarseOFusionarse(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDePadres) {

    }

    //4.1
    private NodoMVias<K, V> buscarNodoDeDato(K claveAEliminar, Stack<NodoMVias<K, V>> pilaDePadres) {
        return null;
    }
}
