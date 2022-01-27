package bo.edu.uagrm.ficct.inf310.ed202102.arboles;

import java.util.*;

public class NodoMVias<K, V> {
    private List<K> listaDeClaves;
    private List<V> listaDeValores;
    private List<NodoMVias<K, V>> listaDeHijos;
    //1
    public NodoMVias(int orden) {
        this.listaDeClaves = new LinkedList<K>();
        this.listaDeValores = new LinkedList<V>();
        this.listaDeHijos = new LinkedList<>();
        for (int i = 0; i < orden - 1; i++) {
            this.listaDeClaves.add((K)NodoMVias.nodoVacio());
            this.listaDeValores.add((V)NodoMVias.nodoVacio());
            this.listaDeHijos.add(NodoMVias.nodoVacio());
        }
        this.listaDeHijos.add(NodoMVias.nodoVacio());
    }
    //2
    public NodoMVias(int orden, K primerClave, V primerValor) {
        this(orden);
        this.listaDeClaves.set(0, primerClave);
        this.listaDeValores.set(0 , primerValor);
    }
    //3
    public static NodoMVias nodoVacio() {
        return null;
    }
    //1
    public static boolean esNodoVacio(NodoMVias elNodo) {
        return elNodo == NodoMVias.nodoVacio();
    }

    //4
    public static Object datoVacio() {
        return null;
    }
    //5
    public K getClave(int posicion) {
        return this.listaDeClaves.get(posicion);
    }
    //6
    public void setClave(int posicion, K clave) {
        this.listaDeClaves.set(posicion,clave);
    }
    //7
    public V getValor(int posicion) {
        return this.listaDeValores.get(posicion);
    }
    //8
    public void setValor(int posicion, V valor) {
        this.listaDeValores.set(posicion,valor);
    }

    //9
    public NodoMVias<K ,V> getHijo(int posicion) {
        return this.listaDeHijos.get(posicion);
    }
    //10
    public void setHijo(int posicion, NodoMVias<K, V> nodoHijo) {
        this.listaDeHijos.set(posicion, nodoHijo);
    }
    //11
    public boolean esClaveVacia(int posicion) {
        return this.listaDeClaves.get(posicion) == NodoMVias.datoVacio();
    }
    //12

    public boolean esHijoVacio(int posicion) {
        //return this.listaDeHijos.get(posicion) == NodoMVias.nodoVacio();
        return NodoMVias.esNodoVacio(this.listaDeHijos.get(posicion));
    }
    //13
    public boolean esHoja() {
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!this.esHijoVacio(i)) {
                return false;
            }
        }
        return true;
    }

    //14
    public boolean estanClavesLLenas() {
        for (int i = 0; i < this.listaDeClaves.size(); i++) {
            if (this.esClaveVacia(i)) {
                return false;
            }
        }
        return true;
    }
    //15
    public int cantidadDeHijosNoVacios() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!this.esHijoVacio(i)) {
                cantidad++;
            }
        }
        return cantidad;
    }
    //16
    public int cantidadDeClavesNoVacias() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeClaves.size(); i++) {
            if (!this.esClaveVacia(i)) {
                cantidad++;
            }
        }
        return cantidad;
    }
    //17
    public int cantidadDeHijosVacios() {
        return this.listaDeHijos.size() - this.cantidadDeHijosNoVacios();
    }
}