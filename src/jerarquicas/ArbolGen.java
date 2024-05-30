package jerarquicas;

import lineales.dinamicas.Lista;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        /*
         * nivel(obj)
         * 
         * 
         * listarPre
         * listarIn
         * listarPos
         * listarPorNivel
         */
    }

    // Dado una Lista verifica que sea el camino desde la raiz hasta una hoja
    public boolean verificarCamino(Lista camino) {
        boolean exit = false;
        if (!esVacio()) {
            Lista aux = camino.clone();
            exit = verificarCaminoAux(raiz, aux);
        }
        return exit;
    }

    private boolean verificarCaminoAux(NodoGen n, Lista camino) {
        boolean exit = false;
        if (n != null) {
            if (!camino.esVacia()) {

            }
        }
        return exit;
    }

    public Lista listaQueJustificaLaAltura() {
        Lista lis = new Lista();
        if (!esVacio()) {
            listaAlturaAux(raiz, lis, lis);
        }
        return lis;
    }

    private void listaAlturaAux(NodoGen n, Lista actual, Lista res) {
        if (n != null) {
            actual.insertar(n.getElem(), actual.longitud() + 1);
            NodoGen hijo = n.getHijoIzquierdo();
            if (hijo == null) {
                Lista clonActual = actual.clone();
            } else {
                while (hijo != null) {
                    listaAlturaAux(hijo, actual, res);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

    }

    /*
     * Dado un numero debe retornar una lista con todos los elementos
     * que se encuentren entre los niveles [0, nivel]
     */
    public Lista listarHastaNivel(int nivel) {
        Lista l = new Lista();
        if (!esVacio() && nivel >= 0) {
            listarNivel(raiz, l, nivel, 0);
        }
        return l;
    }

    private void listarNivel(NodoGen n, Lista l, int nivel, int nivelActual) {
        if (n != null) {
            if (nivel >= nivelActual) {
                l.insertar(n.getElem(), l.longitud() + 1);
                NodoGen hijo = n.getHijoIzquierdo();
                nivelActual++;
                while (hijo != null) {
                    listarNivel(hijo, l, nivel, nivelActual);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        if (!esVacio()) {
            clon.raiz = cloneAux(raiz);
        }
        return clon;
    }

    private NodoGen cloneAux(NodoGen n) {
        NodoGen nuevo = null;
        if (n != null) {
            nuevo = new NodoGen(n.getElem(), null, null);
            NodoGen hijo = n.getHijoIzquierdo();
            if (hijo != null) {
                nuevo.setHijoIzquierdo(cloneAux(hijo));
                NodoGen hijoClon = nuevo.getHijoIzquierdo();
                NodoGen hermano = hijo.getHermanoDerecho();
                while (hermano != null) {
                    hijoClon.setHermanoDerecho(cloneAux(hermano));
                    hijoClon = hijoClon.getHermanoDerecho();
                    hermano = hermano.getHermanoDerecho();
                }
            }
        }
        return nuevo;
    }

    public void vaciar() {
        raiz = null;
    }

    public Lista ancestros(Object elem) {
        Lista l = new Lista();
        if (!esVacio()) {
            ancestrosAux(raiz, elem, l);
        }
        return l;
    }

    private boolean ancestrosAux(NodoGen n, Object elem, Lista l) {
        boolean exit = false;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                exit = true;
                l.insertar(elem, 1);
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && !exit) {
                    exit = ancestrosAux(hijo, elem, l);
                    hijo = hijo.getHermanoDerecho();
                }
                if (exit) {
                    l.insertar(n.getElem(), 1);
                }
            }
        }
        return exit;
    }

    public String toString() {
        return toStringAux(raiz);
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            s += n.getElem().toString() + "->";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    public int altura() {
        int alt = -1;
        if (!esVacio()) {
            alt = alturaAux(raiz);
        }
        return alt;
    }

    private int alturaAux(NodoGen n) {
        int altura = 0, altMaxHijos = 0;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                int altHijo = alturaAux(hijo);
                hijo = hijo.getHermanoDerecho();
                if (altHijo > altMaxHijos) {
                    altMaxHijos = altHijo;
                }
            }
            altura = altMaxHijos + 1;
        }

        return altura;

    }

    public boolean esVacio() {
        return raiz == null;
    }

    public Object padre(Object elem) {
        Object padre = null;
        NodoGen n = padreAux(raiz, elem);
        if (n != null) {
            padre = n.getElem();
        }
        return padre;
    }

    private NodoGen padreAux(NodoGen n, Object elem) {
        NodoGen padre = null;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && padre == null) {
                if (hijo.getElem().equals(elem)) {
                    padre = n;
                } else {
                    hijo = hijo.getHermanoDerecho();
                }
            }
            hijo = n.getHijoIzquierdo();
            while (hijo != null && padre == null) {
                padre = padreAux(hijo, elem);
                hijo = hijo.getHermanoDerecho();
            }

        }
        return padre;
    }

    public boolean pertenece(Object elem) {
        return (obtenerNodo(raiz, elem) != null);
    }

    public boolean insertar(Object elem, Object padre) {
        boolean exit = false;
        if (esVacio()) {
            raiz = new NodoGen(elem, null, null);
            exit = true;
        } else {
            NodoGen p = obtenerNodo(raiz, padre);
            if (p != null) {
                exit = true;
                NodoGen hijo = p.getHijoIzquierdo();
                if (hijo == null) {
                    p.setHijoIzquierdo(new NodoGen(elem, null, null));
                } else {
                    while (hijo.getHermanoDerecho() != null) {
                        hijo = hijo.getHermanoDerecho();
                    }
                    hijo.setHermanoDerecho(new NodoGen(elem, null, null));
                }
            }
        }

        return exit;
    }

    private NodoGen obtenerNodo(NodoGen n, Object elem) {
        NodoGen nodo = null;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                nodo = n;
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && nodo == null) {
                    nodo = obtenerNodo(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return nodo;
    }

    public Lista listarPreorden() {
        Lista lista = new Lista();
        listarPreordenAux(raiz, lista);
        return lista;
    }

    private void listarPreordenAux(NodoGen n, Lista lista) {
        if (n != null) {
            lista.insertar(n.getElem(), lista.longitud() + 1);
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPreordenAux(hijo, lista);
                hijo = hijo.getHermanoDerecho();
            }

        }
    }
}
