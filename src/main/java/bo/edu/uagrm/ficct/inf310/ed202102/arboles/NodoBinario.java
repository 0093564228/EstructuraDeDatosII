package bo.edu.uagrm.ficct.inf310.ed202102.arboles;

/**
 * @author Abel Alejandro  López Cabero
 * @param <K>
 * @param <V>
 */
public class NodoBinario<K, V> {
    private K clave;
    private V valor;
    private NodoBinario<K,V> hijoIzquierdo;
    private NodoBinario<K,V> hijoDerecho;

    public NodoBinario(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public K getClave() {
        return clave;
    }

    public void setClave(K clave) {
        this.clave = clave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public NodoBinario<K, V> getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBinario<K, V> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoBinario<K, V> getHijoDerecho() {
        return this.hijoDerecho;
    }

    public void setHijoDerecho(NodoBinario<K, V> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    //Se adiciona los siguientes metodos
    //Metodo estatico(Metodo compartido, variable compartida)
    public static NodoBinario nodoVacio() {
        return null;
    }

    public static boolean esNodoVacio(NodoBinario elNodo) {
        return elNodo == NodoBinario.nodoVacio();
    }

    public boolean esVacioHijoIzquierdo() {
        return NodoBinario.esNodoVacio(this.getHijoIzquierdo());
    }

    public boolean esVacioHijoDerecho() {
        return NodoBinario.esNodoVacio(this.getHijoDerecho());
    }

    public boolean esHoja() {
        return this.esVacioHijoIzquierdo() &&
                this.esVacioHijoDerecho();
    }
}
