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
    private NodoBinario<K, V> reconstruirConPreOrden(List<K> listaDeClavesInOrden, List<V> listaDeValoresInOrden,
                                                     List<K> ListaDeClavesNoInOrden, List<V> ListaDeValoresNoInOrden) {

        return null;
    }

    //16.2
    private NodoBinario<K, V> reconstruirConPostOrden(List<K> listaDeClavesInOrden, List<V> listaDeValoresInOrden,
                                                      List<K> ListaDeClavesNoInOrden, List<V> ListaDeValoresNoInOrden) {
        K claveActual = recorridoEnPostOrden().get(recorridoEnPostOrden().size());

        return null;
    }

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
            NodoBinario<K, V> aparenteNuevoHijoIzquierdo =
                    eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(aparenteNuevoHijoIzquierdo);
            return nodoActual;
        }

        if (claveAEliminar.compareTo(claveActual) > 0) {
            NodoBinario<K, V> aparenteNuevoHijoDerecho =
                    eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(aparenteNuevoHijoDerecho);
            return nodoActual;
        }

        //lo encontre
        //caso1
        if (nodoActual.esHoja()) {
            return NodoBinario.nodoVacio();
        }
        //caso2
        if (!nodoActual.esVacioHijoIzquierdo() &&
                nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();
        }

        if (nodoActual.esVacioHijoIzquierdo() &&
                !nodoActual.esVacioHijoDerecho()) {
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
                    if (!nodoDelTope.esVacioHijoDerecho() &&
                            nodoDelTope.getHijoDerecho() != nodoActual) {
                        meterEnPilaParaPostOrden(nodoDelTope.getHijoDerecho(), pilaDeNodos);
                    }
                }
            }
        }
        return cantidad;
    }

    //11
    public int sizeRecursivo() {
        return sizeRecursivo(this.raiz);
    }

    //11.1
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

    //Con reoccrrido por niveles
    //13
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
                    if (!nodoDelTope.esVacioHijoDerecho() &&
                            nodoDelTope.getHijoDerecho() != nodoActual) {
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
                    if (!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
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

    private boolean esSoloUnHijoRecursivo(NodoBinario<K,V> nodoActual) {
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
                while (nodosEnLaCola>0) {
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
}



