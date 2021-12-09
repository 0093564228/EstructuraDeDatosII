package bo.edu.uagrm.ficct.inf310.ed202102.arboles;

import javax.swing.*;
import java.util.List;

public class AVL<K extends Comparable<K>, V> extends ArbolBinarioBusqueda<K, V>{
    private static final byte TOPE_DIFERENCIA = 1;

    //1
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException {
        if (valorAInsertar == null) {
            throw new NullPointerException("No se permite insertar claves nulas en el árbol");
        }
        this.raiz = this.insertar(this.raiz, claveAInsertar, valorAInsertar);
    }

    //1.1
    private NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual, K claveAInsertar, V valorAInsertar) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            NodoBinario<K, V> nuevoNodo = new NodoBinario<K, V>(claveAInsertar, valorAInsertar);
            return nuevoNodo;//en este punto no se necesita balancear porque es una hoja.
        }

        K claveActual = nodoActual.getClave();
        if (claveAInsertar.compareTo(claveActual) < 0) {
            NodoBinario<K, V> aparenteNuevoHijoIzquierdo =
                    insertar(nodoActual.getHijoIzquierdo(), claveAInsertar, valorAInsertar);
            nodoActual.setHijoIzquierdo(aparenteNuevoHijoIzquierdo);
            return balancear(nodoActual);// en este punto, si podria ser necesario balancear.
        }

        if (claveAInsertar.compareTo(claveActual) > 0) {
            NodoBinario<K, V> aparenteNuevoHijoDerecho =
                    insertar(nodoActual.getHijoDerecho(), claveAInsertar, valorAInsertar);
            nodoActual.setHijoDerecho(aparenteNuevoHijoDerecho);
            return balancear(nodoActual);// en este punto, si podria ser necesario balancear.
        }

        //si llego por aca, quiere decir que encontre el nodo
        //donde está la clave a insertar
        nodoActual.setValor(valorAInsertar);
        return nodoActual;
    }

    private NodoBinario<K,V> balancear(NodoBinario<K,V> nodoActual) {
        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha   = altura(nodoActual.getHijoDerecho());

        int diferenciaDeAltura = alturaPorIzquierda - alturaPorDerecha;
        if (diferenciaDeAltura > TOPE_DIFERENCIA) {
            //necesitamos una rotacion a derecha
            NodoBinario<K, V> hijoIzquierdoDelActual = nodoActual.getHijoIzquierdo();
            alturaPorIzquierda = altura(hijoIzquierdoDelActual.getHijoIzquierdo());
            alturaPorDerecha = altura(hijoIzquierdoDelActual.getHijoDerecho());
            if (alturaPorDerecha > alturaPorIzquierda) {
                return rotacionDobleALaDerecha(nodoActual);
            }
            return rotacionSimpleALaDerecha(nodoActual);
        } else if (diferenciaDeAltura < -TOPE_DIFERENCIA) {
            //necesitamos una ratacion a izquierda
            NodoBinario<K, V> hijoDerechoDelActual = nodoActual.getHijoDerecho();
            alturaPorIzquierda = altura(hijoDerechoDelActual.getHijoIzquierdo());
            alturaPorDerecha = altura(hijoDerechoDelActual.getHijoDerecho());
            if (alturaPorIzquierda > alturaPorDerecha) {
                return rotacionDobleALaIzquierda(nodoActual);
            }
            return rotacionSimpleALaIzquierda(nodoActual);
        }

        return nodoActual;
    }

    private NodoBinario<K,V> rotacionDobleALaIzquierda(NodoBinario<K,V> nodoActual) {
        NodoBinario<K, V> primerNodoARotar = rotacionSimpleALaDerecha(nodoActual.getHijoDerecho());
        nodoActual.setHijoDerecho(primerNodoARotar);
        return rotacionSimpleALaIzquierda(nodoActual);

    }
    private NodoBinario<K, V> rotacionDobleALaDerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> primerNodoARotar = rotacionSimpleALaIzquierda(nodoActual.getHijoIzquierdo());
        nodoActual.setHijoIzquierdo(primerNodoARotar);
        return rotacionSimpleALaDerecha(nodoActual);
    }
    private NodoBinario<K,V> rotacionSimpleALaDerecha(NodoBinario<K,V> nodoActual) {
        NodoBinario<K, V> nodoARotar = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoARotar.getHijoDerecho());
        nodoARotar.setHijoDerecho(nodoActual);
        return nodoARotar;
    }

    private NodoBinario<K,V> rotacionSimpleALaIzquierda(NodoBinario<K,V> nodoActual){
        NodoBinario<K, V> nodoARotar = nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoARotar.getHijoIzquierdo());
        nodoARotar.setHijoIzquierdo(nodoActual);
        return nodoARotar;

    }

    @Override
    public V eliminar(K claveAEliminar) throws ExceptionClaveNoExiste {
        V valorAEliminar = this.buscar(claveAEliminar);
        if (valorAEliminar == null) {
            throw new ExceptionClaveNoExiste();
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        return valorAEliminar;
    }
    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K claveAEliminar) {
        K claveActual = nodoActual.getClave();
        if (claveAEliminar.compareTo(claveActual) < 0) {
            NodoBinario<K, V> aparenteNuevoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(aparenteNuevoHijoIzquierdo);
            return balancear(nodoActual);
        }

        if (claveAEliminar.compareTo(claveActual) > 0) {
            NodoBinario<K, V> aparenteNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(aparenteNuevoHijoDerecho);
            return balancear(nodoActual);
        }

        //caso 1
        if (nodoActual.esHoja()) {
            return NodoBinario.nodoVacio();
        }

        //caso 2
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();
        }

        if (!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()) {
            return nodoActual.getHijoDerecho();
        }

        //caso 3
        NodoBinario<K, V> nodoDelSucesor = this.nodoSucesor(nodoActual.getHijoDerecho());

        NodoBinario<K, V> aparenteNuevoHijoDerecho = this.eliminar(nodoActual.getHijoDerecho(), nodoDelSucesor.getClave());

        nodoActual.setHijoDerecho(aparenteNuevoHijoDerecho);
        nodoActual.setClave(nodoDelSucesor.getClave());
        nodoActual.setValor(nodoDelSucesor.getValor());
        return balancear(nodoActual);
    }
    public List<K> recorridoNiveles() {
        return this.recorridoPorNiveles();
    }
}
