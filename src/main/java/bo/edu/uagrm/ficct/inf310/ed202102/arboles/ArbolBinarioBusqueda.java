package bo.edu.uagrm.ficct.inf310.ed202102.arboles;

import java.util.*;
import java.lang.*;

public class ArbolBinarioBusqueda<K extends Comparable<K>, V> implements IArbolBusqueda<K, V> {

    protected NodoBinario<K, V> raiz;

    //15
    public ArbolBinarioBusqueda() {
    }

    /*
    pre-condicion se asume que los recorridos estan correctos
     */
    //validar misma cantidad de valores
    //16
    /**
     *
     * @param clavesInOrden
     * @param valoresInOrden
     * @param clavesNoInOrden
     * @param valoresNoInOrden
     * @param esConPreOrden
     */
    public ArbolBinarioBusqueda(List<K> clavesInOrden, List<V> valoresInOrden,
            List<K> clavesNoInOrden, List<V> valoresNoInOrden, boolean esConPreOrden) {
        if (esConPreOrden) {
            this.raiz = this.reconstruirConPreOrden(clavesInOrden, valoresInOrden,
                    clavesNoInOrden, valoresNoInOrden);
        } else {
            this.raiz = this.reconstruirConPostOrden(clavesInOrden, valoresInOrden,
                    clavesNoInOrden, valoresNoInOrden);
        }
    }

    //16.1
    /**
     *
     * @param listaDeClavesInOrden
     * @param listaDeValoresInOrden
     * @param listaDeClavesPreOrden
     * @param listaDeValoresPreOrden
     * @return
     */
    private NodoBinario<K, V> reconstruirConPreOrden(List<K> listaDeClavesInOrden, List<V> listaDeValoresInOrden,
            List<K> listaDeClavesPreOrden, List<V> listaDeValoresPreOrden) {

        if (listaDeClavesPreOrden.isEmpty()) {
            return NodoBinario.nodoVacio();
        }

        K clave = listaDeClavesPreOrden.get(0);
        V valor = listaDeValoresPreOrden.get(0);

        NodoBinario<K, V> nodoActual = new NodoBinario<>(clave, valor);

        int posicion = this.getPosicion(listaDeClavesInOrden, clave);

        List<K> subListIzquierdaClavesPreOrden = listaDeClavesPreOrden.subList(0, posicion);
        List<V> subListIzquierdaValoresPreOrden = listaDeValoresPreOrden.subList(0, posicion);
        List<K> subListIzquierdaClavesInOrden = listaDeClavesInOrden.subList(0, posicion);
        List<V> subListIzquierdaValoresInOrden = listaDeValoresInOrden.subList(0, posicion);

        NodoBinario<K, V> hijoIzquierdo = reconstruirConPreOrden(subListIzquierdaClavesInOrden, subListIzquierdaValoresInOrden,
                subListIzquierdaClavesPreOrden, subListIzquierdaValoresPreOrden);

        List<K> subListDerechaClavesPreOrden = listaDeClavesPreOrden.subList(posicion, listaDeClavesPreOrden.size() - 1);
        List<V> subListDerechaValoresPreOrden = listaDeValoresPreOrden.subList(posicion, listaDeClavesPreOrden.size() - 1);
        List<K> subListDerechaClavesInOrden = listaDeClavesInOrden.subList(posicion + 1, listaDeClavesInOrden.size());
        List<V> subListDerechaValoresInOrden = listaDeValoresInOrden.subList(posicion + 1, listaDeClavesInOrden.size());

        NodoBinario<K, V> hijoDerecho = reconstruirConPostOrden(subListDerechaClavesInOrden, subListDerechaValoresInOrden,
                subListDerechaClavesPreOrden, subListDerechaValoresPreOrden);

        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        nodoActual.setHijoDerecho(hijoDerecho);

        return nodoActual;
    }

    //16.2
    /**
     *
     * @param listaDeClavesPostOrden
     * @param listaDeValoresPostOrden
     * @param listaDeClavesInOrden
     * @param listaDeValoresInOrden
     * @return
     */
    private NodoBinario<K, V> reconstruirConPostOrden(List<K> listaDeClavesPostOrden, List<V> listaDeValoresPostOrden,
            List<K> listaDeClavesInOrden, List<V> listaDeValoresInOrden) {
        if (listaDeClavesPostOrden.isEmpty()) {
            return NodoBinario.nodoVacio();
        }

        K clave = listaDeClavesPostOrden.get(listaDeClavesPostOrden.size() - 1);
        V valor = listaDeValoresPostOrden.get(listaDeValoresPostOrden.size() - 1);

        NodoBinario<K, V> nodoActual = new NodoBinario<>(clave, valor);

        int posicion = this.getPosicion(listaDeClavesInOrden, clave);

        List<K> subListIzquierdaClavesPostOrden = listaDeClavesPostOrden.subList(0, posicion);
        List<V> subListIzquierdaValoresPostOrden = listaDeValoresPostOrden.subList(0, posicion);
        List<K> subListIzquierdaClavesInOrden = listaDeClavesInOrden.subList(0, posicion);
        List<V> subListIzquierdaValoresInOrden = listaDeValoresInOrden.subList(0, posicion);

        NodoBinario<K, V> hijoIzquierdo = reconstruirConPostOrden(subListIzquierdaClavesPostOrden, subListIzquierdaValoresPostOrden,
                subListIzquierdaClavesInOrden, subListIzquierdaValoresInOrden);

        List<K> subListDerechaClavesPostOrden = listaDeClavesPostOrden.subList(posicion, listaDeClavesPostOrden.size() - 1);
        List<V> subListDerechaValoresPostOrden = listaDeValoresPostOrden.subList(posicion, listaDeValoresPostOrden.size() - 1);
        List<K> subListDerechaClavesInOrden = listaDeClavesInOrden.subList(posicion + 1, listaDeClavesInOrden.size());
        List<V> subListDerechaValoresInOrden = listaDeValoresInOrden.subList(posicion + 1, listaDeClavesInOrden.size());

        NodoBinario<K, V> hijoDerecho = reconstruirConPostOrden(subListDerechaClavesPostOrden, subListDerechaValoresPostOrden,
                subListDerechaClavesInOrden, subListDerechaValoresInOrden);

        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        nodoActual.setHijoDerecho(hijoDerecho);

        return nodoActual;
    }

    /**
     * 16.3 Retorna la posición de una clave de una lista de claves
     *
     * @param litaDeClaves
     * @param claveABuscar
     * @return
     */
    private int getPosicion(List<K> litaDeClaves, K claveABuscar) {
        for (int i = 0; i < litaDeClaves.size(); i++) {
            K claveActual = litaDeClaves.get(i);
            if (claveABuscar.compareTo(claveActual) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 16.4 Retorna una lista de valores.
     *
     * @param listaDeClaves
     * @return
     */
    public List<V> listaDeValores(List<K> listaDeClaves) {
        List<V> recorrido = new LinkedList<>();
        for (int i = 0; i < listaDeClaves.size(); i++) {
            K clave = listaDeClaves.get(i);
            V valor = this.buscar(clave);
            recorrido.add(valor);
        }
        return recorrido;
    }

    /**
     *
     * @param claveAInsertar
     * @param valorAInsertar
     * @throws NullPointerException
     */
    //3
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException {
        if (claveAInsertar == null) {
            throw new NullPointerException("No se permiten insertar claves nulas");
        }

        if (valorAInsertar == null) {
            throw new NullPointerException("No se permiten insertar valores nulas");
        }

        if (this.esArbolVacio()) {
            this.raiz = new NodoBinario<>(claveAInsertar, valorAInsertar);
            return;
        }

        NodoBinario<K, V> nodoAnterior = NodoBinario.nodoVacio();
        NodoBinario<K, V> nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            K claveActual = nodoActual.getClave();
            nodoAnterior = nodoActual;
            if (claveAInsertar.compareTo(claveActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else if (claveAInsertar.compareTo(claveActual) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            } else {
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }

        K claveAnterior = nodoAnterior.getClave();
        NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);

        if (claveAInsertar.compareTo(claveAnterior) < 0) {
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        } else {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }

    //14
    @Override
    public V eliminar(K claveAEliminar) throws ExceptionClaveNoExiste {
        V valorAEliminar = this.buscar(claveAEliminar);
        if (valorAEliminar == null) {
            throw new ExceptionClaveNoExiste();
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        return valorAEliminar;
    }

    //14.1
    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K claveAEliminar) {
        K claveActual = nodoActual.getClave();
        if (claveAEliminar.compareTo(claveActual) < 0) {
            NodoBinario<K, V> aparenteNuevoHijoIzquierdo
                    = eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(aparenteNuevoHijoIzquierdo);
            return nodoActual;
        }

        if (claveAEliminar.compareTo(claveActual) > 0) {
            NodoBinario<K, V> aparenteNuevoHijoDerecho
                    = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(aparenteNuevoHijoDerecho);
            return nodoActual;
        }

        //lo encontre
        //caso1
        if (nodoActual.esHoja()) {
            return NodoBinario.nodoVacio();
        }
        //caso2
        if (!nodoActual.esVacioHijoIzquierdo()
                && nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();
        }

        if (nodoActual.esVacioHijoIzquierdo()
                && !nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoDerecho();
        }

        //caso3
        NodoBinario<K, V> nodoDelSucesor = this.nodoSucesor(nodoActual.getHijoDerecho());

        NodoBinario<K, V> aparenteNuevoHijoDerecho = this.eliminar(
                nodoActual.getHijoDerecho(), nodoDelSucesor.getClave());

        nodoActual.setHijoDerecho(aparenteNuevoHijoDerecho);
        nodoActual.setClave(nodoDelSucesor.getClave());
        nodoActual.setValor(nodoDelSucesor.getValor());

        return nodoActual;
    }

    //14.2
    protected NodoBinario<K, V> nodoSucesor(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoAnterior = NodoBinario.nodoVacio();
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }

    //5
    @Override
    public V buscar(K claveABuscar) {
        if (claveABuscar == null) {
            return null;
        }

        NodoBinario<K, V> nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            K claveActual = nodoActual.getClave();
            if (claveABuscar.compareTo(claveActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else if (claveABuscar.compareTo(claveActual) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            } else {
                return nodoActual.getValor();
            }
        }
        return null;
    }

    //4
    @Override
    public boolean contiene(K claveABuscar) {
        return this.buscar(claveABuscar) != null;
    }

    //10
    // con recorrido postOrden
    @Override
    public int size() {
        int cantidad = 0;
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            NodoBinario<K, V> nodoActual = this.raiz;
            meterEnPilaParaPostOrden(nodoActual, pilaDeNodos);
            while (!pilaDeNodos.isEmpty()) {
                nodoActual = pilaDeNodos.pop();
                //visita del nodo. En este caso estoy agragando la clave a la lista
                cantidad++;

                if (!pilaDeNodos.isEmpty()) {
                    NodoBinario<K, V> nodoDelTope = pilaDeNodos.peek();
                    if (!nodoDelTope.esVacioHijoDerecho()
                            && nodoDelTope.getHijoDerecho() != nodoActual) {
                        meterEnPilaParaPostOrden(nodoDelTope.getHijoDerecho(), pilaDeNodos);
                    }
                }
            }
        }
        return cantidad;
    }

    //11
    /**
     *
     * @return
     */
    public int sizeRecursivo() {
        return sizeRecursivo(this.raiz);
    }

    //11.1
    /**
     *
     * @param nodoActual
     * @return
     */
    private int sizeRecursivo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }

        int sizePorIzquierda = sizeRecursivo(nodoActual.getHijoIzquierdo());
        int sizePorDerecho = sizeRecursivo(nodoActual.getHijoDerecho());
        return sizePorIzquierda + sizePorDerecho + 1;
    }

    //12 nota: la altura tambien puede hacerser con el recorrido por niveles
    @Override
    public int altura() {
        return altura(this.raiz);
    }

    //12.1
    protected int altura(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = altura(nodoActual.getHijoDerecho());

        if (alturaPorIzquierda > alturaPorDerecha) {
            return alturaPorIzquierda + 1;
        } else {
            return alturaPorDerecha + 1;
        }
    }

    /**
     * 13 Con reoccrrido por niveles
     *
     * @return
     */
    public int alturaIterativo() {
        int alturaArbol = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();// una implementacion de cola es linkedlist
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                int nroNodosDelNivel = colaDeNodos.size();
                int contador = 0;
                while (contador < nroNodosDelNivel) {
                    NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                    contador++;
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                    }
                    if (!nodoActual.esVacioHijoDerecho()) {
                        colaDeNodos.offer(nodoActual.getHijoDerecho());
                    }
                }
                alturaArbol++;
            }
        }
        return alturaArbol;
    }

    //1
    /**
     *
     */
    @Override
    public void vaciar() {
        this.raiz = NodoBinario.nodoVacio();//Pierde la referencia a la raiz, garbage collection ve que no hay ninguna variable
    }

    //2 Por que no es estatico?
    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    //6
    /**
     * Iterativo
     *
     * @return
     */
    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {

            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();// una implementacion de cola es linkedlist
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                //visito el nodo. En este caso estoy agregando la clave a la lista
                recorrido.add(nodoActual.getClave());
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }

                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            }
        }
        return recorrido;
    }

    //7
    /**
     * Iterativo
     *
     * @return
     */
    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
                //visita del nodo. En este caso estoy agregando la clave a la lista.
                recorrido.add(nodoActual.getClave());

                if (!nodoActual.esVacioHijoDerecho()) {
                    pilaDeNodos.push(nodoActual.getHijoDerecho());
                }

                if (!nodoActual.esVacioHijoIzquierdo()) {
                    pilaDeNodos.push(nodoActual.getHijoIzquierdo());
                }

            }
        }
        return recorrido;
    }

    //9
    /**
     * Recursivo
     *
     * @return
     */
    @Override
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new ArrayList<>();
        this.recorridoEnInOrden(this.raiz, recorrido);
        return recorrido;
    }

    //9.1
    private void recorridoEnInOrden(NodoBinario<K, V> nodoActual, List<K> recorrido) {
        //n == 0
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }

        recorridoEnInOrden(nodoActual.getHijoIzquierdo(), recorrido);
        recorrido.add(nodoActual.getClave());
        recorridoEnInOrden(nodoActual.getHijoDerecho(), recorrido);
    }

    //8
    /**
     * Recursivo
     *
     * @return
     */
    @Override
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            NodoBinario<K, V> nodoActual = this.raiz;
            meterEnPilaParaPostOrden(nodoActual, pilaDeNodos);
            while (!pilaDeNodos.isEmpty()) {
                nodoActual = pilaDeNodos.pop();
                //visita del nodo. En este caso estoy agragando la clave a la lista
                recorrido.add(nodoActual.getClave());

                if (!pilaDeNodos.isEmpty()) {
                    NodoBinario<K, V> nodoDelTope = pilaDeNodos.peek();
                    if (!nodoDelTope.esVacioHijoDerecho()
                            && nodoDelTope.getHijoDerecho() != nodoActual) {
                        meterEnPilaParaPostOrden(nodoDelTope.getHijoDerecho(), pilaDeNodos);
                    }
                }
            }
        }
        return recorrido;
    }

    //8.1
    private void meterEnPilaParaPostOrden(NodoBinario<K, V> nodoActual, Stack<NodoBinario<K, V>> pilaDeNodos) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();

            }
        }
    }

    /*
    Ejercios para el 28 de Octubre de 2021
     */
    //1. Desarrollar un método que retorne la cantidad de nodos que tiene solo un hijo diferente de vacio.
    //Iterativo, con recorrido por niveles.
    public int cantidadDeNodosConUnSoloHijoIterativo() {
        int cantidad = 0;
        if (this.esArbolVacio()) {
            return cantidad;
        }
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<NodoBinario<K, V>>();
        colaDeNodos.offer(this.raiz);

        while (!colaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = colaDeNodos.poll();

            if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()
                    || nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
                cantidad++;
            }

            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }

            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return cantidad;
    }

    //Recursivo
    public int cantidadDeNodosConUnSoloHijoRecursivo() {
        if (this.esArbolVacio()) {
            return 0;
        }
        return cantidadDeNodosConUnSoloHijoRecursivo(this.raiz);
    }

    private int cantidadDeNodosConUnSoloHijoRecursivo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }

        int cantidadPorIzquierda = cantidadDeNodosConUnSoloHijoRecursivo(nodoActual.getHijoIzquierdo());
        int cantidadPorDerecha = cantidadDeNodosConUnSoloHijoRecursivo(nodoActual.getHijoDerecho());

        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()
                || nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
            return cantidadPorIzquierda + cantidadPorDerecha + 1;
        }

        return cantidadPorIzquierda + cantidadPorDerecha;
    }

    //2. Desarrollar un método que retorne verdadero si los nodos que no son hojas en el arbol solo tienen un hijo.
    // Falso en caso contrario.
    //Iterativo, con recorrido por niveles.
    public boolean esSoloUnHijoIterativo() {
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esHoja()) {
                    if ((!nodoActual.esVacioHijoIzquierdo()) && (!nodoActual.esVacioHijoDerecho())) {
                        return false;
                    }
                }

                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }

                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            }
        }
        return true;
    }

    //Recursivo
    public boolean esSoloUnHijoRecursivo() {
        if (this.esArbolVacio()) {
            return false;
        }
        return esSoloUnHijoRecursivo(this.raiz);
    }

    private boolean esSoloUnHijoRecursivo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return true;
        }

        if (!nodoActual.esHoja()) {
            if (!nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
                return false;
            }
        }

        if (!esSoloUnHijoRecursivo(nodoActual.getHijoIzquierdo())) {
            return false;
        }

        if (!esSoloUnHijoRecursivo(nodoActual.getHijoDerecho())) {
            return false;
        }

        return true;
    }

    //3. Desarrollar un metodo que retorne verdadero si los nodos que no son hojas antes del nivel n en el arbol solo
    // tienen un hijo. Falso en caso contrario.
    //Iterativo, con recorrido por niveles.
    public boolean esSoloUnHijoAntesDeNivelIterativo(int nivel) {
        int controlNivel = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty() && controlNivel < nivel) {
                int nodosEnLaCola = colaDeNodos.size();
                while (nodosEnLaCola > 0) {
                    NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                    nodosEnLaCola--;
                    if (controlNivel < nivel) {
                        if (!nodoActual.esHoja()) {
                            if (!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
                                return false;
                            }
                        }
                    }
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                    }

                    if (!nodoActual.esVacioHijoDerecho()) {
                        colaDeNodos.offer(nodoActual.getHijoDerecho());
                    }
                }
                controlNivel++;
            }
        }
        return true;
    }

    //Recursivo
    public boolean esSoloUnHijoAntesdelNivelRecursivo(int nivel) {

        if (this.esArbolVacio()) {
            return false;
        }
        return esSoloUnHijoAntesdelNivelRecursivo(this.raiz, nivel, 0);
    }

    private boolean esSoloUnHijoAntesdelNivelRecursivo(NodoBinario<K, V> nodoActual, int nivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return true;
        }

        if (nivelActual < nivel) {
            if (!nodoActual.esHoja()) {
                if (!nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
                    return false;
                }
            }
        }
        if (!esSoloUnHijoAntesdelNivelRecursivo(nodoActual.getHijoIzquierdo(), nivel, nivelActual + 1)) {
            return false;
        }

        if (!esSoloUnHijoAntesdelNivelRecursivo(nodoActual.getHijoDerecho(), nivel, nivelActual + 1)) {
            return false;
        }

        return true;
    }

    //---------------------------------------PRACTICO-SOBRE-ARBOLES--------------------------------------
    /*7. Implemente un método iterativo con el recorrido en inorden que retorne la cantidad de nodos
     que tienen ambos hijos distintos de vacío en un árbol binario */
    public int cantidadDeNodosConAmbosHijosNoVacios() {
        int cantidad = 0;

        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            this.meterEnPilaParaInOrden(pilaDeNodos, this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
                if (!nodoActual.esHoja()) {
                    if (!nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
                        cantidad++;
                    }
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    this.meterEnPilaParaInOrden(pilaDeNodos, nodoActual.getHijoDerecho());
                }
            }
        }

        return cantidad;
    }

    private void meterEnPilaParaInOrden(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }
    }

    /*8. Implemente un método recursivo que retorne la cantidad de nodos que tienen un solo hijo no vació*/
    public int cantidadDeNodosConUnSoloHijo() {
        return cantidadDeNodosConUnSoloHijo(this.raiz);
    }

    private int cantidadDeNodosConUnSoloHijo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidadPorIzquierda = cantidadDeNodosConUnSoloHijo(nodoActual.getHijoIzquierdo());
        int cantidadPorDerecha = cantidadDeNodosConUnSoloHijo(nodoActual.getHijoDerecho());

        if (!nodoActual.esHoja()) {
            if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()
                    || nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
                return cantidadPorIzquierda + cantidadPorDerecha + 1;
            }
        }

        return cantidadPorIzquierda + cantidadPorDerecha;
    }

    /*9. Implemente un método iterativo con la lógica de un recorrido en inOrden que retorne el número de hijos
     vacios que tiene un árbol binario.*/
    public int cantidadDeHijosVacios() {
        int cantidad = 0;
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            this.meterEnPilaParaInOrden(pilaDeNodos, this.raiz);

            while (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = pilaDeNodos.pop();

                if (nodoActual.esVacioHijoIzquierdo()) {
                    cantidad++;
                }

                if (nodoActual.esVacioHijoDerecho()) {
                    cantidad++;
                }

                if (!nodoActual.esVacioHijoDerecho()) {
                    this.meterEnPilaParaInOrden(pilaDeNodos, nodoActual.getHijoDerecho());
                }

            }
        }
        return cantidad;
    }

    /*11. Implemente un método privado que reciba un nodo binario de un árbol binario y que
     retorne cuál sería su predecesor inorden de la clave de dicho nodo.*/
    public K predecesorInOrden(K clave) {
        NodoBinario<K, V> nodoABuscar = this.buscarNodo(clave);
        return predecesorInOrden(nodoABuscar.getHijoIzquierdo());
    }

    private NodoBinario<K, V> buscarNodo(K claveABuscar) {
        if (claveABuscar == null) {
            return null;
        }

        NodoBinario<K, V> nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            K claveActual = nodoActual.getClave();
            if (claveABuscar.compareTo(claveActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else if (claveABuscar.compareTo(claveActual) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            } else {
                return nodoActual;
            }
        }
        return null;
    }

    private K predecesorInOrden(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoAnterior = NodoBinario.nodoVacio();
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoDerecho();
        }

        return nodoAnterior.getClave();
    }

    /*15. Para un árbol binario de búsqueda implemente un método que reciba como parámetro
    otro árbol y que retorne verdadero si los arboles son similares, falso en caso contrario.*/
    public NodoBinario<K, V> getRaiz() {
        return this.raiz;
    }

    public boolean esArbolSimilar(ArbolBinarioBusqueda<K, V> unArbol) {
        if (this.esArbolVacio() || unArbol.esArbolVacio()) {
            return false;
        }
        if (this.size() != unArbol.size()) {
            return false;
        }

        //En este punto sé que al menos existe un nodo en ambos árboles, la raiz.
        //Verifico si los árboles tienen la misma estructura.
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        Queue<NodoBinario<K, V>> colaDeNodosDeUnArbol = new LinkedList<>();

        colaDeNodos.offer(this.raiz);
        colaDeNodosDeUnArbol.offer(unArbol.getRaiz());

        while (!colaDeNodos.isEmpty() && !colaDeNodosDeUnArbol.isEmpty()) {
            NodoBinario<K, V> nodoActual = colaDeNodos.poll();
            NodoBinario<K, V> nodoActualDeUnArbol = colaDeNodosDeUnArbol.poll();

            if (nodoActual.esVacioHijoIzquierdo() && !nodoActualDeUnArbol.esVacioHijoIzquierdo()
                    || !nodoActual.esVacioHijoIzquierdo() && nodoActualDeUnArbol.esVacioHijoIzquierdo()) {
                return false;
            }

            if (nodoActual.esVacioHijoDerecho() && !nodoActualDeUnArbol.esVacioHijoDerecho()
                    || !nodoActual.esVacioHijoDerecho() && nodoActualDeUnArbol.esVacioHijoDerecho()) {
                return false;
            }

            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }

            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }

            if (!nodoActualDeUnArbol.esVacioHijoIzquierdo()) {
                colaDeNodosDeUnArbol.offer(nodoActualDeUnArbol.getHijoIzquierdo());
            }

            if (!nodoActualDeUnArbol.esVacioHijoDerecho()) {
                colaDeNodosDeUnArbol.offer(nodoActualDeUnArbol.getHijoDerecho());
            }
        }
        return true;
    }

    public boolean esMonticulo() {
        if (this.esArbolVacio()) {
            return false;
        }

        return this.esMonticulo(this.raiz);
    }

    private boolean esMonticulo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return true;
        }

        NodoBinario<K, V> hijoIzquierdo = nodoActual.getHijoIzquierdo();
        NodoBinario<K, V> hijoDerecho = nodoActual.getHijoDerecho();

        if (!nodoActual.esHoja()) {
            if (!nodoActual.esVacioHijoIzquierdo()) {
                if (nodoActual.getClave().compareTo(hijoIzquierdo.getClave()) > 0) {
                    return false;
                }
            }

            if (!nodoActual.esVacioHijoDerecho()) {
                if (nodoActual.getClave().compareTo(hijoDerecho.getClave()) > 0) {
                    return false;
                }
            }
        }
        if (!this.esMonticulo(nodoActual.getHijoIzquierdo())) {
            return false;
        }

        if (!this.esMonticulo(nodoActual.getHijoDerecho())) {
            return false;
        }

        return true;
    }
}
