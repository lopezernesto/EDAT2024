package jerarquicas;

import lineales.dinamicas.Lista;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        /*
         * 
         * insertarPorPosicion(elemNuevo, posPadre, posHijo): boolean
         * 
         * altura(): int
         * nivel(elemento): int
         * padre(elemento): elemPadre
         * listarPreorden(): lista de elementos
         * listarInorden(): lista de elementos
         * listarPosorden(): lista de elementos
         * listarNiveles(): lista de elementos
         * clone(): ArbolBinario
         * vaciar () : void
         * toString(): String
         */
    }

    public boolean esVacio() {
        return raiz == null;
    }

    // Inserta un elemento en la primer aparicion del padre
    public boolean insertar(Object elem, Object elemPadre, int postHijo) {
        // posHijo -> 0 = izquierda, 1 = derecha
        boolean exit = false;
        if (esVacio()) {
            raiz = new NodoArbol(elem, null, null);
            exit = true;
        } else {
            NodoArbol padre = obtenerNodo(raiz, elemPadre);
            if (padre != null) {
                NodoArbol nuevo = new NodoArbol(elemPadre, null, null);
                if (postHijo == 0) {
                    if (padre.getIzquierdo() == null) {
                        padre.setIzquierdo(nuevo);
                        exit = true;
                    }
                }
                if (postHijo == 1) {
                    if (padre.getDerecho() == null) {
                        padre.setDerecho(nuevo);
                        exit = true;
                    }
                }
            }
        }
        return exit;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object elem) {
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                resultado = n;
            } else {
                // Busco por Hijo Izquierdo
                resultado = obtenerNodo(n.getIzquierdo(), elem);
                // Si no encontro busco en Hijo Derecho
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), elem);
                }
            }
        }
        return resultado;
    }

    // Inserta un nuevo nodo en la posicion del padre en pre-orden
    public boolean insertarPorPosicion(Object elemNuevo, int posPadre, int posHijo) {
        // posHijo ---> 0 = izquierda 1 = derecha
        boolean exit = false;
        NodoArbol padre = obtenerNodoPos(raiz, posPadre, 0);
        if (padre != null) {
            NodoArbol nuevo = new NodoArbol(elemNuevo, null, null);
            if (posHijo == 0 && padre.getIzquierdo() == null) {
                padre.setIzquierdo(nuevo);
                exit = true;
            } else if (posHijo == 1 && padre.getDerecho() == null) {
                padre.setDerecho(nuevo);
                exit = true;
            }
        }
        return exit;

    }

    private NodoArbol obtenerNodoPos(NodoArbol n, int posPadre, int cant) {
        NodoArbol resultado = null;
        if (n != null) {
            if (cant == posPadre) {
                resultado = n;
            } else {
                // Busco por Hijo Izquierdo
                resultado = obtenerNodoPos(n.getIzquierdo(), posPadre, cant + 1);
                if (resultado == null) {
                    // Si no encontro busco en Hijo Derecho
                    resultado = obtenerNodoPos(n.getDerecho(), posPadre, cant + 1);
                }
            }
        }
        return resultado;
    }

    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol n, Lista lista) {
        if (n != null) {
            // visita el Nodo, lo pone
            lista.insertar(n.getElem(), lista.longitud() + 1);
            // luego visita primero a su hijo izquierdo
            listarPreordenAux(n.getIzquierdo(), lista);
            // y despues a su hijo derecho
            listarPreordenAux(n.getDerecho(), lista);
        }
    }

}
