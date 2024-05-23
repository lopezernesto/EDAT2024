package jerarquicas;

import lineales.dinamicas.Lista;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        /*
         * nivel(obj)
         * 
         * clone()
         * listarPre
         * listarIn
         * listarPos
         * listarPorNivel
         * toString()
         */
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
                NodoGen hijoClon = new NodoGen(hijo.getElem(), null, null);
                nuevo.setHijoIzquierdo(hijoClon);
                hijo = hijo.getHermanoDerecho();
                while (hijo != null) {
                    hijoClon.setHermanoDerecho(new NodoGen(hijo.getElem(), null, null));
                    hijoClon = hijoClon.getHermanoDerecho();
                    hijo = hijo.getHermanoDerecho();
                }
                hijo = n.getHijoIzquierdo();
                hijoClon = nuevo.getHijoIzquierdo();
                nuevo.setHijoIzquierdo(hijo);
                while (hijo != null) {
                    hijoClon = (cloneAux(hijo));
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
        return nuevo;
    }

    public void vaciar() {
        raiz = null;
    }

    // Preguntar sobre el (pertenece)
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
