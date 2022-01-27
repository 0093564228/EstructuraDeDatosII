package bo.edu.uagrm.ficct.inf310.ed202102.arboles;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolMViasBusqueda<K extends Comparable<K>, V> implements IArbolBusqueda<K, V> {

    protected NodoMVias<K, V> raiz;
    protected int orden;
    protected static final int POSICION_NO_VALIDA = -1;
    protected static final int ORDEN_MINIMO = 3;

    //1
    public ArbolMViasBusqueda() {
        this.orden = ArbolMViasBusqueda.ORDEN_MINIMO;
    }

    //2
    public ArbolMViasBusqueda(int ordenDelArbol) throws ExceptionOrdenNoValido {
        if (ordenDelArbol < ArbolMViasBusqueda.ORDEN_MINIMO) {
            throw new ExceptionOrdenNoValido();
        }
        this.orden = ordenDelArbol;
    }

    /**
     *
     * @param claveAInsertar
     * @param valorAInsertar
     * @throws NullPointerException
     */
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException {
        if (claveAInsertar == (K) NodoMVias.datoVacio()) {
            throw new NullPointerException("No se permiten insertar claves nulas");
        }

        if (valorAInsertar == (V) NodoMVias.datoVacio()) {
            throw new NullPointerException("No se permiten insertar valores nulos");
        }

        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
            return;
        }

        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            int posicionDeClaveAInsertar = this.getPosicionDeClave(nodoActual, claveAInsertar);
            if (posicionDeClaveAInsertar != ArbolMViasBusqueda.POSICION_NO_VALIDA) {
                nodoActual.setValor(posicionDeClaveAInsertar, valorAInsertar);
                return;
                //nodoActual = NodoMVias.nodoVacio();
            }

            if (nodoActual.esHoja()) {
                if (nodoActual.estanClavesLLenas()) {
                    NodoMVias<K, V> nuevoHijo = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
                    int posicionDeEnlace = this.getPosicionPorDondeBajar(nodoActual, claveAInsertar);
                    nodoActual.setHijo(posicionDeEnlace, nuevoHijo);
                } else {
                    this.insertarClaveYValorEnNodo(nodoActual, claveAInsertar, valorAInsertar);
                }
                return;
            }

            int posicionPorDondeBajar = this.getPosicionPorDondeBajar(nodoActual, claveAInsertar);
            if (nodoActual.esHijoVacio(posicionPorDondeBajar)) {
                NodoMVias<K, V> nuevoHijo = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
                nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                return;
            }
            nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
        }//fin while
    }


    /**
     * 5.1
     * @param nodoActual
     * @param claveABuscar
     * @return
     */
    protected int getPosicionDeClave(NodoMVias<K, V> nodoActual, K claveABuscar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveABuscar.compareTo(claveActual) == 0) {
                return i;
            }
        }
        return ArbolMViasBusqueda.POSICION_NO_VALIDA;
    }

    /**
     * 5.2
     * @param nodoActual
     * @param claveABuscar
     * @return
     */
    protected int getPosicionPorDondeBajar(NodoMVias<K, V> nodoActual, K claveABuscar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveABuscar.compareTo(claveActual) < 0) {
                return i;
            }
        }
        return nodoActual.cantidadDeClavesNoVacias();
    }

    /**
     * Inserta clave y valor en un nodo en orden ascendente
     *  Precondición
     *  1. Debe existir al menos un dato en el nodo
     *  2. El nodo no debe estar lleno
     *  3. La última posición del nodo no debe tener un dato
     * @param nodoActual
     * @param claveAInsertar
     * @param valorAInsertar
     */
    //5.3

    protected void insertarClaveYValorEnNodo(NodoMVias<K, V> nodoActual, K claveAInsertar, V valorAInsertar) {
        int posicionDondeInsertar = getPosicionDondeInsertar(nodoActual, claveAInsertar);
        int posicionActual = nodoActual.cantidadDeClavesNoVacias();
        while (posicionActual > posicionDondeInsertar) {
            K claveActual = nodoActual.getClave(posicionActual - 1);
            V valorActual = nodoActual.getValor(posicionActual - 1);
            nodoActual.setClave(posicionActual, claveActual);
            nodoActual.setValor(posicionActual, valorActual);
            posicionActual--;
        }
        nodoActual.setClave(posicionDondeInsertar, claveAInsertar);
        nodoActual.setValor(posicionDondeInsertar, valorAInsertar);
    }

    /**
     * 5.4
     * @param nodoActual
     * @param claveAInsertar
     * @return
     */
    protected int getPosicionDondeInsertar(NodoMVias<K, V> nodoActual, K claveAInsertar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAInsertar.compareTo(claveActual) < 0) {
                return i;
            }
        }
        return nodoActual.cantidadDeClavesNoVacias();
    }

    /**
     * 10
     * @param claveAEliminar
     * @return
     * @throws ExceptionClaveNoExiste
     */
    @Override
    public V eliminar(K claveAEliminar) throws ExceptionClaveNoExiste {
        V valorAEliminar = this.buscar(claveAEliminar);
        if (valorAEliminar == null) {
            throw new ExceptionClaveNoExiste();
        }

        this.raiz = eliminar(this.raiz, claveAEliminar);

        return valorAEliminar;
    }

    private NodoMVias<K, V> eliminar(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveEnTurno = nodoActual.getClave(i);
            if (claveAEliminar.compareTo(claveEnTurno) == 0) { // lo encontre la clave a eliminar
                if (nodoActual.esHoja()) {
                    this.eliminarClaveYValor(nodoActual, i);//elimina el dato dejando las claves ordenadas, en este punto el nodo podria estar vacio, es decir sin claves.
                    if (nodoActual.cantidadDeClavesNoVacias() == 0) {
                        return NodoMVias.nodoVacio();
                    }
                    return nodoActual;
                }
                //caso de que no sea hoja el nodo actual
                K claveDeReemplazo;
                int posicionDeClaveAEliminar = i;
                if (this.tieneHijosMasAdelante(nodoActual, i)) {
                    claveDeReemplazo = this.buscarClaveSucesoraInOrden(nodoActual, posicionDeClaveAEliminar);
                } else {
                    claveDeReemplazo = this.buscarClavePredecesoraInOrden(nodoActual, posicionDeClaveAEliminar);
                }

                V valorAsociadoAClaveDeReemplazo = this.buscar(claveDeReemplazo);

                nodoActual = eliminar(nodoActual, claveDeReemplazo);
                nodoActual.setClave(i, claveDeReemplazo);
                nodoActual.setValor(i, valorAsociadoAClaveDeReemplazo);
                return nodoActual;
            }
            //Y si no la encuentro la clave a eliminar?
            if (claveAEliminar.compareTo(claveEnTurno) < 0) {
                NodoMVias<K, V> supuestoHijoNuevo = this.eliminar(nodoActual.getHijo(i), claveAEliminar);
                nodoActual.setHijo(i, supuestoHijoNuevo);
                return nodoActual;
            }
        }//fin del for
        NodoMVias<K, V> supuestoHijoNuevo = this.eliminar(nodoActual.getHijo(orden - 1), claveAEliminar);
        nodoActual.setHijo(orden - 1, supuestoHijoNuevo);
        return nodoActual;
    }

    //10.4
    private K buscarClavePredecesoraInOrden(NodoMVias<K, V> nodoActual, int posicionActual) {
        List<K> recorrido = new LinkedList<>();
        predecesorInorden(nodoActual, recorrido, posicionActual);
        return recorrido.get(0);
    }

    //10.4.1
    private void predecesorInorden(NodoMVias<K, V> nodoActual, List<K> recorrido, int posicionActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }

        int i = nodoActual.cantidadDeClavesNoVacias();

        if (nodoActual.esHoja()) {
            recorrido.add(nodoActual.getClave(i - 1));
            return;
        }

        predecesorInorden(nodoActual.getHijo(posicionActual), recorrido, i);
        if (posicionActual > 0) {
            recorrido.add(nodoActual.getClave(posicionActual - 1));
        }
    }

    //10.3
    private K buscarClaveSucesoraInOrden(NodoMVias<K, V> nodoActual, int posicion) {
        List<K> recorrido = new LinkedList<>();
        sucesorInorden(nodoActual, recorrido, posicion + 1);
        return recorrido.get(0);
    }

    //10.3.1
    private void sucesorInorden(NodoMVias<K, V> nodoActual, List<K> recorrido, int posicion) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }

        int i = posicion;
        if (i < nodoActual.cantidadDeClavesNoVacias()) {
            sucesorInorden(nodoActual.getHijo(i), recorrido, 0);
            recorrido.add(nodoActual.getClave(i));
            return;
        }
        sucesorInorden(nodoActual.getHijo(orden - 1), recorrido, 0);
    }

    //10.2
    private boolean tieneHijosMasAdelante(NodoMVias<K, V> nodoActual, int i) {
        int posicionActual = i + 1;
        while (posicionActual < orden) {
            if (!NodoMVias.esNodoVacio(nodoActual.getHijo(posicionActual))) {
                return true;
            }
            posicionActual++;
        }
        return false;
    }

    //10.1
//    postcondicion
//    el nodo se podria quedar vacio de datos
    protected void eliminarClaveYValor(NodoMVias<K, V> nodoActual, int i) {
        int posicion = i;
        while (posicion < nodoActual.cantidadDeClavesNoVacias() - 1) {
            K claveActual = nodoActual.getClave(posicion + 1);
            V valorActual = nodoActual.getValor(posicion + 1);
            nodoActual.setClave(posicion, claveActual);
            nodoActual.setValor(posicion, valorActual);
            posicion++;
        }
        nodoActual.setClave(posicion, (K) NodoMVias.datoVacio());
        nodoActual.setValor(posicion, (V) NodoMVias.datoVacio());
    }



    /**
     * 4
     * @param claveABuscar
     * @return
     */
    @Override
    public V buscar(K claveABuscar) {
        if (claveABuscar == NodoMVias.datoVacio()) {
            return (V) NodoMVias.datoVacio();
        }

        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            boolean huboCambioDeNodo = false;
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias()
                    && !huboCambioDeNodo; i++) {
                K claveActual = nodoActual.getClave(i);
                if (claveABuscar.compareTo(claveActual) == 0) {
                    return nodoActual.getValor(i);
                }
                if (claveABuscar.compareTo(claveActual) < 0) {
                    nodoActual = nodoActual.getHijo(i);
                    huboCambioDeNodo = true;
                }
            }

            if (!huboCambioDeNodo) {
                //nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias());
                //es lo mismo en este caso que colocar lo siguiente
                nodoActual = nodoActual.getHijo(this.orden - 1);
            }
        }
        return (V) NodoMVias.datoVacio();
    }

    //3
    @Override
    public boolean contiene(K claveABuscar) {
        return this.buscar(claveABuscar) != NodoMVias.datoVacio();
    }

    //8
    @Override
    public int size() {
        return sizeRecursivo(this.raiz);
    }

    //8.1
    private int sizeRecursivo(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        //1 forma, para ahorrarse codigo
        int sizeAcumulado = 0;
        for (int i = 0; i < orden; i++) {
            int sizeDeHijo = sizeRecursivo(nodoActual.getHijo(i));
            sizeAcumulado = sizeAcumulado + sizeDeHijo;
        }

        /*2 forma
        int sizeAcumulado = 0;
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            int sizeDeHijo = sizeRecursivo(nodoActual.getHijo(i));
            sizeAcumulado = sizeAcumulado + sizeDeHijo;
        }
        sizeAcumulado += sizeRecursivo(nodoActual.getHijo(orden - 1));
        */
        return sizeAcumulado + 1;
    }

    //9
    @Override
    public int altura() {
        return alturaRecursiva(this.raiz);
    }

    //9.1
    private int alturaRecursiva(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }

        int alturaMayorDeHijos = 0;
        for (int i = 0; i < orden; i++) {
            int alturaDeHijoEnTurno = alturaRecursiva(nodoActual.getHijo(i));
            if (alturaDeHijoEnTurno > alturaMayorDeHijos) {
                alturaMayorDeHijos = alturaDeHijoEnTurno;
            }
        }

        return alturaMayorDeHijos + 1;
    }

    //3
    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    //4
    @Override
    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
    }

    //6
    @Override
    public List recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoMVias<K, V> nodoActual = colaDeNodos.poll();
                for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
                    recorrido.add(nodoActual.getClave(i));
                    if (!nodoActual.esHijoVacio(i)) {
                        colaDeNodos.offer(nodoActual.getHijo(i));
                    }
                }//fin for

                if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacias())) {
                    colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()));
                }
            }
        }
        return recorrido;
    }

    /**
     *
     * @return
     */
    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new LinkedList<>();
        this.recorridoEnPreOrden(this.raiz, recorrido);
        return recorrido;
    }

    /**
     *
     * @param nodoActual
     * @param recorrido
     */
    private void recorridoEnPreOrden(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            recorrido.add(nodoActual.getClave(i));
            recorridoEnPreOrden(nodoActual.getHijo(i), recorrido);
        }
        recorridoEnPreOrden(nodoActual.getHijo(orden - 1), recorrido);

    }

    /**
     * 6 Recursivo
     * @return
     */
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new ArrayList<>();
        this.recorridoEnInOrden(this.raiz, recorrido);
        return recorrido;
    }

    /**
     * 6.1
     * @param nodoActual
     * @param recorrido
     */
    private void recorridoEnInOrden(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        //n == 0
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        //dependiendo de lo que queremos hacer, por ejemplo procesar algo con los datos es decir dato vacio
        //y no vacio usariamos el orden - 1, y si queremos procesar todos los hijos usamos orden
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            recorridoEnInOrden(nodoActual.getHijo(i), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }
        recorridoEnInOrden(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), recorrido);

        /*for (int i = 0; i < orden - 1; i++) {
            recorridoEnInOrden(nodoActual.getHijo(i), recorrido);
            if (!nodoActual.esClaveVacia(i)) {
                recorrido.add(nodoActual.getClave(i));
            }
        }*/
        //recorridoEnInOrden(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), recorrido);

    }

    //7 Recursivo
    @Override
    public List recorridoEnPostOrden() {
        List<K> recorrido = new ArrayList<>();
        this.recorridoEnPostOrden(this.raiz, recorrido);
        return recorrido;
    }

    //7.1
    private void recorridoEnPostOrden(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        //n == 0
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }

        recorridoEnPostOrden(nodoActual.getHijo(0), recorrido);

        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            recorridoEnPostOrden(nodoActual.getHijo(i + 1), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }
    }

    /*Ejemplo en clase- 18 de nnoviembre*/
    //11. Cantidad de claves no vacias.
    // Recursivo.
    public int cantidadDeClavesNoVacias() {
        return this.cantidadDeClavesNoVacias(this.raiz);
    }

    //11.1
    private int cantidadDeClavesNoVacias(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }

        int clavesNoVaciasAcumuladas = nodoActual.cantidadDeClavesNoVacias();
        for (int i = 0; i < orden; i++) {
            int claveNoVaciasDelHijoEnTurno = cantidadDeClavesNoVacias(nodoActual.getHijo(i));
            clavesNoVaciasAcumuladas = clavesNoVaciasAcumuladas + claveNoVaciasDelHijoEnTurno;
        }
        return clavesNoVaciasAcumuladas;
    }

    //Tarea para el 23 de noviembre.

    //Cantidad de claves vacias iterativo, implementado con recorrido por niveles.
    public int cantidadDeClavesVacias() {
        int cantidad = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoMVias<K, V> nodoActual = colaDeNodos.poll();
                for (int i = 0; i < orden - 1; i++) {
                    if (nodoActual.esClaveVacia(i)) {
                        cantidad = cantidad + 1;
                    }
                    if (!nodoActual.esHijoVacio(i)) {
                        colaDeNodos.offer(nodoActual.getHijo(i));
                    }
                }//fin for

                if (!nodoActual.esHijoVacio(orden - 1)) {
                    colaDeNodos.offer(nodoActual.getHijo(orden - 1));
                }
            }//fin while

        }
        return cantidad;
    }

    //----------------------------------------------------------------------------------------------------------------
    //Tarea para el 30 de noviembre.

    //1. Para un árbol m-vias de búsqueda crear un método que retorne la cantidad de hijos vacios que hay en el árbol.

    //Iterativo, implementado con recorrido por niveles.
    public int cantidadDeHijosVacios() {
        int cantidad = 0; //variable que acumula el número de hijos vacios de cada nodo.

        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);

            while (!colaDeNodos.isEmpty()) {//entra al ciclo si colaDeNodos es no vacio.
                NodoMVias<K, V> nodoActual = colaDeNodos.poll();

                for (int i = 0; i < this.orden; i++) {//utilizo el orden por que proceso los hijos de nodoActual.
                    if (nodoActual.esHijoVacio(i)) {
                        cantidad++;//si es hijo vacio entonces incrementa la cantidad.
                    }

                    if (!NodoMVias.esNodoVacio(nodoActual.getHijo(i))) {
                        colaDeNodos.offer(nodoActual.getHijo(i));//se agrega a colaDeNodos si el hijo es no vacio.
                    }

                }//fin for

            }//fin while

        }

        return cantidad;
    }

    //2. Para un árbol m-vias de búsqueda crear un método que retorne la cantidad de hijos vacios que hay en el nivel n del árbol.

    //Iterativo, implementado con recorrido por niveles.
    public int cantidadDeHijosVaciosEnElNivelN(int nivel) {
        int cantidad = 0; //acumula el número de hijos vacios de cada nodo.
        int nivelActual = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);

            while (!colaDeNodos.isEmpty()) {//entra al ciclo si colaDeNodos es no vacio.
                int cantidadDeNodosEnElNivel = colaDeNodos.size();

                while (cantidadDeNodosEnElNivel > 0) {//el ciclo procesa todos los nodos en colaDeNodos antes de cambiar de nivel.

                    NodoMVias<K, V> nodoActual = colaDeNodos.poll();
                    for (int i = 0; i < this.orden; i++) {//utilizo orden por que proceso todos los hijos de nodoActual.
                        if (nivel == nivelActual) {
                            if (nodoActual.esHijoVacio(i)) {
                                cantidad++;//incrementa si esta en el nivel y es hijo vacio.
                            }
                        }

                        if (!nodoActual.esHijoVacio(i)) {
                            colaDeNodos.offer(nodoActual.getHijo(i));
                        }
                    }//fin for

                    cantidadDeNodosEnElNivel--;
                }//fin while

                nivelActual++;//incrementa en una unidad luego de procesar todos los nodos en colaDeNodos.
            }//fin while
        }
        return cantidad;
    }

    //3. Para un árbol m-vias de búsqueda crear un método que retorne la cantidad de hijos vacios que hay antes del nivel n en el árbol.

    //Iterativo, con recorrido por niveles.
    public int cantidadDeHijosVaciosAntesDelNivelN(int nivel) {
        int cantidad = 0;
        int nivelActual = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);

            while (!colaDeNodos.isEmpty()) {//entra en el ciclo si la cola no esta vacia
                int cantidadDeNodosEnLaCola = colaDeNodos.size();

                while (cantidadDeNodosEnLaCola > 0) {//el ciclo procesa todos los nodos en colaDeNodos.
                    NodoMVias<K, V> nodoActual = colaDeNodos.poll();

                    for (int i = 0; i < this.orden; i++) {//utilizo orden por que procesa los hijos de nodoActual.

                        if (nivelActual < nivel) {
                            if (nodoActual.esHijoVacio(i)) {
                                cantidad++;//incrementa si está antes del nivel y es hijo vacio.
                            }
                        }

                        if (!nodoActual.esHijoVacio(i)) {
                            colaDeNodos.offer(nodoActual.getHijo(i));//añade a la cola si es hijo no vacio.
                        }
                    }//fin for

                    cantidadDeNodosEnLaCola--;
                }//fin while

                nivelActual++;
            }//fin while
        }

        return cantidad;
    }

    //4. Para un árbol m-vias de búsqueda crear un método que retorne la cantidad de hijos vacios a partir del nivel n del árbol.
    public int cantidadDeHijosVaciasAPartirDelNivelN(int nivel) {
        int cantidad = 0;
        int nivelActual = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);

            while (!colaDeNodos.isEmpty()) {
                int cantidadDeNodosEnLaCola = colaDeNodos.size();

                while (cantidadDeNodosEnLaCola > 0) {
                    NodoMVias<K, V> nodoActual = colaDeNodos.poll();
                    for (int i = 0; i < this.orden; i++) {

                        if (nivelActual >= nivel) {
                            if (nodoActual.esHijoVacio(i)) {
                                cantidad++;
                            }
                        }

                        if (!nodoActual.esHijoVacio(i)) {
                            colaDeNodos.offer(nodoActual.getHijo(i));
                        }
                    }//fin for

                    cantidadDeNodosEnLaCola--;
                }//fin while

                nivelActual++;
            }//fin while
        }

        return cantidad;
    }


    //---------------------------------------PRACTICO-SOBRE-ARBOLES--------------------------------------
    /*12. Implemente un método que retorne verdadero si solo hay nodos completos en el nivel n de un árbol m vias.
    Falso en caso contrario..*/

    //Nodo completo es el que tiene claves llenas y todos sus hijos.
    public boolean sonNodosCompletosEnELNivel(int nivel) {

        if (this.esArbolVacio()) {
            return false;
        }
        int nivelActual = 0;
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);

        while (!colaDeNodos.isEmpty() && nivelActual <= nivel) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();

            for (int i = 0; i < this.orden - 1; i++) {
                if (nivel == nivelActual) {
                    if (nodoActual.esClaveVacia(i) || nodoActual.esHijoVacio(i)) {
                        return false;
                    }
                }

                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }//fin for

            if (nivel == nivelActual) {
                if (nodoActual.esHijoVacio(this.orden - 1)) {
                    return false;
                }
            }

            if (!nodoActual.esHijoVacio(this.orden - 1)) {
                colaDeNodos.offer(nodoActual.getHijo(this.orden - 1));
            }

            nivelActual++;
        }

        return true;
    }

    /*14. Para un árbol m vías implementar un método que reciba otro árbol de parámetro y que retorne verdadero
    si los arboles son similares. Falso en caso contrario.*/

    public NodoMVias<K, V> getRaiz() {
        return this.raiz;
    }

    public boolean esArbolSimilar(ArbolMViasBusqueda<K, V> unArbol) {

        if (this.orden != unArbol.orden) {
            return false;
        }

        if (this.esArbolVacio() || unArbol.esArbolVacio()) {
            return false;
        }

        if (this.size() != unArbol.size()) {
            return false;
        }
        //En este punto sé que al menos existe un nodo en ambos árboles, la raiz.
        //Verifico si los árboles tienen la misma estructura.

        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        Queue<NodoMVias<K, V>> colaDeNodosDeUnArbol = new LinkedList<>();

        colaDeNodos.offer(this.raiz);
        colaDeNodosDeUnArbol.offer(unArbol.getRaiz());

        while (!colaDeNodos.isEmpty() && !colaDeNodosDeUnArbol.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            NodoMVias<K, V> nodoActualDeUnArbol = colaDeNodosDeUnArbol.poll();

            for (int i = 0; i < this.orden - 1; i++) {

                if (!nodoActual.esHijoVacio(i) && nodoActualDeUnArbol.esHijoVacio(i)
                        || nodoActual.esHijoVacio(i) && !nodoActualDeUnArbol.esHijoVacio(i)) {
                    return false;
                }

                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }

                if (!nodoActualDeUnArbol.esHijoVacio(i)) {
                    colaDeNodosDeUnArbol.offer(nodoActualDeUnArbol.getHijo(i));
                }

            }

        }
        return true;
    }
}

