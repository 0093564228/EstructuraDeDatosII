package bo.edu.uagrm.ficct.inf310.ed202102.arboles;

import java.util.*;

public class ArbolBinarioBusquedaEnteroCadena extends ArbolBinarioBusqueda {
    NodoBinario raiz;

    public ArbolBinarioBusquedaEnteroCadena() {

    }

    public void insertar(Integer claveAInsertar, String valorAInsertar) throws NullPointerException {
        if (claveAInsertar == null) {
            throw new NullPointerException("No se permiten insertar claves nulas");
        }

        if (valorAInsertar == null) {
            throw new NullPointerException("No se permiten insertar valores nulas");
        }

        if (this.esArbolVacio()) {
            this.raiz = new NodoBinario(claveAInsertar, valorAInsertar);
            return;
        }

        NodoBinario nodoAnterior = NodoBinario.nodoVacio();
        NodoBinario nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            Integer claveActual = (Integer) nodoActual.getClave();
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

        Integer claveAnterior = (Integer) nodoAnterior.getClave();
        NodoBinario nuevoNodo = new NodoBinario(claveAInsertar, valorAInsertar);

        if (claveAInsertar.compareTo(claveAnterior) < 0) {
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        } else {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }


    public String eliminar(Integer claveAEliminar) throws ExceptionClaveNoExiste {
        String valorAEliminar = (String) this.buscar(claveAEliminar);
        if (valorAEliminar == null) {
            throw new ExceptionClaveNoExiste();
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        return valorAEliminar;
    }

    private NodoBinario eliminar(NodoBinario nodoActual, Integer claveAEliminar) {
        Integer claveActual = (Integer) nodoActual.getClave();
        if (claveAEliminar.compareTo(claveActual) < 0) {
            NodoBinario aparenteNuevoHijoIzquierdo =
                    eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(aparenteNuevoHijoIzquierdo);
            return nodoActual;
        }

        if (claveAEliminar.compareTo(claveActual) > 0) {
            NodoBinario aparenteNuevoHijoDerecho =
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
        NodoBinario nodoDelSucesor = this.nodoSucesor(nodoActual.getHijoDerecho());

        NodoBinario aparenteNuevoHijoDerecho = this.eliminar(
                nodoActual.getHijoDerecho(), (Integer) nodoDelSucesor.getClave());

        nodoActual.setHijoDerecho(aparenteNuevoHijoDerecho);
        nodoActual.setClave(nodoDelSucesor.getClave());
        nodoActual.setValor(nodoDelSucesor.getValor());

        return nodoActual;
    }

    public String buscar(Integer claveABuscar) {
        if (claveABuscar == null) {
            return null;
        }

        NodoBinario nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            Integer claveActual = (Integer) nodoActual.getClave();
            if (claveABuscar.compareTo(claveActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else if (claveABuscar.compareTo(claveActual) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            } else {
                return (String) nodoActual.getValor();
            }
        }
        return null;
    }

    public boolean contiene(Integer claveABuscar) {
        return this.buscar(claveABuscar) != null;
    }

    public int size() {
        return sizeRecursivo(this.raiz);
    }

    private int sizeRecursivo(NodoBinario nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }

        int sizePorIzquierda = sizeRecursivo(nodoActual.getHijoIzquierdo());
        int sizePorDerecho = sizeRecursivo(nodoActual.getHijoDerecho());
        return sizePorIzquierda + sizePorDerecho + 1;
    }

    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NodoBinario nodoActual) {
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

    public void vaciar() {
        this.raiz = NodoBinario.nodoVacio();
    }

    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    public List<Integer> recorridoPorNiveles() {
        List<Integer> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {

            Queue<NodoBinario> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario nodoActual = colaDeNodos.poll();
                //visito el nodo. En este caso estoy agregando la clave a la lista
                recorrido.add((Integer) nodoActual.getClave());
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

    public List<Integer> recorridoEnPreOrden() {
        List<Integer> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoBinario> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                NodoBinario nodoActual = pilaDeNodos.pop();
                recorrido.add((Integer) nodoActual.getClave());

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

    public List<Integer> recorridoEnInOrden() {
        List<Integer> recorrido = new ArrayList<>();
        this.recorridoEnInOrden(this.raiz, recorrido);
        return recorrido;
    }

    //9.1
    private void recorridoEnInOrden(NodoBinario nodoActual, List<Integer> recorrido) {
        //n == 0
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }

        recorridoEnInOrden(nodoActual.getHijoIzquierdo(), recorrido);
        recorrido.add((Integer)nodoActual.getClave());
        recorridoEnInOrden(nodoActual.getHijoDerecho(), recorrido);
    }

    public List<Integer> recorridoEnPostOrden() {
        List<Integer> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoBinario> pilaDeNodos = new Stack<>();
            NodoBinario nodoActual = this.raiz;
            meterEnPilaParaPostOrden(nodoActual, pilaDeNodos);
            while (!pilaDeNodos.isEmpty()) {
                nodoActual = pilaDeNodos.pop();
                //visita del nodo. En este caso estoy agragando la clave a la lista
                recorrido.add((Integer)nodoActual.getClave());

                if (!pilaDeNodos.isEmpty()) {
                    NodoBinario nodoDelTope = pilaDeNodos.peek();
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
    private void meterEnPilaParaPostOrden(NodoBinario nodoActual, Stack<NodoBinario> pilaDeNodos) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();

            }
        }
    }
}
