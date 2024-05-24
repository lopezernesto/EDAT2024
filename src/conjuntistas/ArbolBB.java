package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB() {

    }

    public boolean eliminar(Object elem) {
        boolean exit = false;
        if (!esVacio()) {
            // Si el elemento a eliminar esta en la raiz
            if (raiz.equals(elem)) {

            } else {
                // Si no esta en la raiz busco al padre
                NodoABB padre = buscarPadre(raiz, elem);
                exit = eliminarAux(padre, elem);
            }

        }
        return exit;
    }

    private int cantHijos(NodoABB n) {
        int cant = -1;
        if (n.getDerecho() != null && n.getIzquierdo() != null) {
            cant = 3;
        } else {
            if (n.getIzquierdo() != null) {
                cant = 2;
            } else {
                if (n.getDerecho() != null) {
                    cant = 1;
                }

            }
        }
        System.out.println("estoy aca " + cant);
        return cant;
    }

    private void eliminarHijo(NodoABB padre, NodoABB hijo, int pos) {
        // pos 1 significa que lo mando para el hijo derecho
        int a = cantHijos(hijo);
        switch (a) {
            case -1:
                // no tiene hijos
                if (pos == 0)
                    padre.setIzquierdo(null);
                else {
                    padre.setDerecho(null);
                }
                break;
            case 1:
                // tiene hijo derecho
                if (pos == 1)
                    padre.setDerecho(hijo.getDerecho());
                else {
                    padre.setDerecho(hijo.getIzquierdo());
                }
                break;
            case 2:
                // tiene hijo izquierdo
                if (pos == 1)
                    padre.setIzquierdo(hijo.getIzquierdo());
                else {
                    padre.setIzquierdo(hijo.getDerecho());
                }
                break;
            case 3:
                // tiene dos hijos
                break;

            default:
                break;
        }
    }

    private boolean eliminarAux(NodoABB padre, Object elem) {
        boolean exit = true;
        int aux = padre.getElem().compareTo(elem);
        if (aux > 0) {
            // el elemento a elminar es el hijo izquierdo
            NodoABB hijo = padre.getIzquierdo();
            eliminarHijo(padre, hijo, 0);
        } else {
            // es el hijo derecho
            NodoABB hijo = padre.getDerecho();
            eliminarHijo(padre, hijo, 1);
        }
        return exit;
    }

    private NodoABB buscarPadre(NodoABB n, Object elem) {
        NodoABB resultado = null;
        if (n != null) {
            if (n.getIzquierdo().getElem().equals(elem) || n.getDerecho().getElem().equals(elem)) {
                resultado = n;
            } else {
                int aux = n.getElem().compareTo(elem);
                if (aux > 0) {
                    resultado = buscarPadre(n.getIzquierdo(), elem);
                } else {

                    resultado = buscarPadre(n.getDerecho(), elem);
                }
            }

        }
        return resultado;
    }

    public boolean insertar(Comparable elem) {
        boolean exit = false;
        if (esVacio()) {
            raiz = new NodoABB(elem, null, null);
            exit = true;
        } else {
            exit = insertarAux(raiz, elem);
        }
        return exit;
    }

    private boolean insertarAux(NodoABB n, Comparable elem) {
        boolean exit = false;
        int aux = n.getElem().compareTo(elem);
        // >1 s1>s2 n=s1
        // <1 s1<s2
        // 0 s1==s2
        if (aux != 0) {
            NodoABB nuevo = new NodoABB(elem, null, null);
            // si el nodo es mayor, lo pongo a la izquierda
            if (aux > 0) {
                NodoABB hijo = n.getIzquierdo();
                if (hijo == null) {
                    n.setIzquierdo(nuevo);
                } else {
                    exit = insertarAux(hijo, elem);
                }
            } else {
                // sino lo pongo a la derecha
                NodoABB hijo = n.getDerecho();
                if (hijo == null) {
                    n.setDerecho(nuevo);
                } else {
                    exit = insertarAux(hijo, elem);
                }
            }
        }
        return exit;
    }

    public boolean pertenece(Comparable elem) {
        boolean exit = false;
        if (!esVacio()) {
            exit = perteneceAux(raiz, elem);
        }
        return exit;
    }

    private boolean perteneceAux(NodoABB n, Comparable elem) {
        boolean exit = false;
        int aux = n.getElem().compareTo(elem);
        if (aux == 0) {
            exit = true;
        } else if (aux > 0) {
            // busco por izquierda
            NodoABB hijo = n.getIzquierdo();
            if (hijo != null) {
                exit = perteneceAux(hijo, elem);
            }
        } else {
            // busco por derecha
            NodoABB hijo = n.getDerecho();
            if (hijo != null) {
                exit = perteneceAux(hijo, elem);
            }
        }
        return exit;
    }

    public Object minElemento() {
        Object elem = null;
        if (!esVacio()) {
            elem = minElementoAux(raiz, raiz.getElem());
        }
        return elem;
    }

    private Object minElementoAux(NodoABB n, Object elem) {
        Object min = elem;
        NodoABB hijo = n.getIzquierdo();
        if (hijo != null) {
            min = minElementoAux(hijo, hijo.getElem());
        }
        return min;
    }

    public Object maxElemento() {
        Object elem = null;
        if (!esVacio()) {
            elem = maxElementoAux(raiz, raiz.getElem());
        }
        return elem;
    }

    private Object maxElementoAux(NodoABB n, Object elem) {
        Object max = elem;
        NodoABB hijo = n.getDerecho();
        if (hijo != null) {
            max = maxElementoAux(hijo, hijo.getElem());
        }
        return max;
    }

    public Lista listar() {
        Lista l = new Lista();
        if (!esVacio()) {
            listarAux(l, raiz);
        }
        return l;
    }

    private void listarAux(Lista l, NodoABB n) {
        if (n != null) {
            l.insertar(n.getElem(), l.longitud() + 1);
            listarAux(l, n.getIzquierdo());
            listarAux(l, n.getDerecho());
        }
    }

    public Lista listarRango(int a, int b) {
        Lista l = new Lista();
        if (!esVacio() && a < b) {
            listarRangoMin(l, raiz, a, b);
        }
        return l;
    }

    private void listarRangoMin(Lista l, NodoABB n, int min, int max) {
        if (n != null) {
            int aux = n.getElem().compareTo(min);
            if (aux > 0) {
                NodoABB hijo = n.getIzquierdo();

                listarRangoMin(l, hijo, min, max);
            }
        }
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public String toString() {
        String cad = "Arbol vacio";
        if (!esVacio()) {
            cad = toStringAux(raiz);
        }
        return cad;
    }

    public String toStringAux(NodoABB n) {
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
