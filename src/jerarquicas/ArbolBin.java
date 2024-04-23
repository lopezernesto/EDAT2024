package jerarquicas;

import lineales.dinamicas.Lista;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        /*
         * 
         * nivel(elemento): int
         * listarInorden(): lista de elementos
         * listarPosorden(): lista de elementos
         * listarNiveles(): lista de elementos
         * clone(): ArbolBinario
         */
    }

    public int nivel(Object elem) {
        int ret = -1;
        if (!esVacio()) {
            ret = nivelAux(raiz, elem);
        }
        return ret;
    }

    public int nivelAux(NodoArbol n, Object elem) {
        int nivel = -1;
        boolean encontrado = false;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                nivel = 0;
                encontrado = true;
            } else {
                nivel = nivelAux(n.getIzquierdo(), elem);
                if (!encontrado && nivel != -1) {
                    nivel++; // Incrementa nivel si el elemento se encuentra en el subárbol izquierdo.
                    encontrado = true;
                } else if (!encontrado) {
                    nivel = nivelAux(n.getDerecho(), elem);
                    if (nivel != -1) {
                        nivel++; // Incrementa nivel si el elemento se encuentra en el subárbol derecho.
                        encontrado = true;
                    }
                }
            }
        }
        return nivel;
    }

    public int nivelAux1(NodoArbol n, Object elem) {
        int nivel = -1;
        boolean encontrado = false;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                nivel = 0;
                encontrado = true;
            } else {

                nivel = nivelAux1(n.getIzquierdo(), elem);
                if (encontrado) {
                    nivel += 1;
                    encontrado = true;
                } else {
                    nivel = nivelAux1(n.getDerecho(), elem);
                    if (encontrado) {
                        encontrado = true;
                        nivel++;
                    }
                }
            }
        }

        return nivel;
    }

    public void vaciar() {
        raiz = null;
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
                NodoArbol nuevo = new NodoArbol(elem, null, null);
                if (postHijo == 0) {
                    if (padre.getIzquierdo() == null) {
                        padre.setIzquierdo(nuevo);
                        exit = true;
                    }
                } else if (postHijo == 1) {
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
        if (!esVacio()) {
            int[] cant = { 1 };
            NodoArbol padre = obtenerNodoPos(raiz, posPadre, cant);
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
        } else {
            raiz = new NodoArbol(elemNuevo, null, null);
        }
        return exit;

    }

    private NodoArbol obtenerNodoPos(NodoArbol n, int posPadre, int[] cant) {
        NodoArbol resultado = null;
        if (n != null) {
            if (cant[0] == posPadre) {
                resultado = n;
            } else {
                // Busco por Hijo Izquierdo
                cant[0] += 1;
                resultado = obtenerNodoPos(n.getIzquierdo(), posPadre, cant);
                if (resultado == null) {
                    // Si no encontro busco en Hijo Derecho
                    resultado = obtenerNodoPos(n.getDerecho(), posPadre, cant);
                }
            }
        }
        return resultado;
    }

    // Altura es la cantidad de nodos desde la raiz hasta la hoja mas lejana
    public int altura() {
        int total = -1, izq = 0, der = 0;
        if (!esVacio()) {
            total = alturaAux(raiz);
        }
        return total;
    }

    public int alturaAux(NodoArbol n) {
        int total = -1, der, izq;
        if (n != null) {
            if (n.getDerecho() == null && n.getDerecho() == null) {
                total = 0;
            } else {
                izq = alturaAux(n.getIzquierdo()) + 1;
                der = alturaAux(n.getDerecho()) + 1;
                total = Math.max(izq, der);
            }
        }

        return total;
    }

    public Object padre(Object elem) {
        String cad = "no tiene padre";
        Object ret = cad;
        if (!esVacio() && raiz.getElem().equals(elem)) {
            ret = buscarPadre(raiz, elem).getElem();
        }
        return ret;
    }

    private NodoArbol buscarPadre(NodoArbol n, Object elem) {
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getIzquierdo().getElem().equals(elem) || n.getDerecho().getElem().equals(elem)) {
                resultado = n;
            } else {
                resultado = buscarPadre(n.getIzquierdo(), elem);
                if (resultado == null) {
                    resultado = buscarPadre(n.getDerecho(), elem);
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

    public String toString() {
        String cad = "Arbol vacio";
        if (!esVacio()) {
            cad = toStringAux(raiz);
        }
        return cad;
    }

    public String toStringAux(NodoArbol n) {
        String cad = "";
        if (n != null) {
            cad += "(" + n.getElem() + ") -> ";
            if (n.getIzquierdo() != null) {
                cad += "HI: " + n.getIzquierdo().getElem() + "  ";
            } else {
                cad += "HI: -  ";
            }
            if (n.getDerecho() != null) {
                cad += "HD: " + n.getDerecho().getElem() + "\n";
            } else {
                cad += "HD: - \n";
            }
            cad += toStringAux(n.getIzquierdo());
            cad += toStringAux(n.getDerecho());
        }
        return cad;
    }
}
