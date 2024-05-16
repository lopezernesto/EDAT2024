package jerarquicas;

import lineales.dinamicas.Lista;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        /*
         * 
         * altura()
         * nivel(obj)
         * ancestro(obj)
         * clone()
         * vaciar()
         * listarPre
         * listarIn
         * listarPos
         * listarPorNivel
         * toString()
         */
    }

    public int altura() {
        return -1;
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
            if (n.getHermanoDerecho() != null) {
                NodoGen hermano = n.getHermanoDerecho();
                while (hermano != null) {
                    listarPreordenAux(n.getHermanoDerecho(), lista);
                }
            }
        }
    }
}
