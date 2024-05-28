package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB() {

    }

    public boolean eliminar(Comparable elem) {
        boolean exit = false;
        if (!esVacio()) {
            // Si el elemento a eliminar esta en la raiz
            if (raiz.getElem().compareTo(elem) == 0) {
                eliminarHijo(null, raiz);
            } else {
                // Si no esta en la raiz busco al padre
                exit = eliminarAux(raiz, elem, null);
            }

        }
        return exit;
    }

    private boolean eliminarAux(NodoABB n, Comparable elem, NodoABB padre) {
        boolean exit = false;
        if (n != null) {
            int aux = n.getElem().compareTo(elem);
            if (aux == 0) {
                eliminarHijo(padre, n);
                exit = true;
            } else {
                if (aux > 0) {
                    exit = eliminarAux(n.getIzquierdo(), elem, n);
                } else {

                    exit = eliminarAux(n.getDerecho(), elem, n);
                }
            }

        }
        return exit;
    }

    private void eliminarHijo(NodoABB padre, NodoABB aEliminar) {
        int temp;
        switch (cantHijos(aEliminar)) {
            // no tiene hijos
            case 0:
                temp = padre.getElem().compareTo(aEliminar.getElem());
                if (temp > 0) {
                    padre.setIzquierdo(null);
                } else {
                    padre.setDerecho(null);
                }
                break;
            // tiene un hijo
            case 1:
                temp = padre.getElem().compareTo(aEliminar.getElem());
                NodoABB aux;
                if (aEliminar.getIzquierdo() != null) {
                    aux = aEliminar.getIzquierdo();
                } else {
                    aux = aEliminar.getDerecho();
                }
                if (temp > 0) {
                    padre.setIzquierdo(aux);
                } else {
                    padre.setDerecho(aux);
                }
                break;
            // tiene dos hijos
            case 2:
                Comparable candidato = mayorIzquierda(aEliminar.getIzquierdo(), aEliminar);
                aEliminar.setElem(candidato);
                break;

            default:
                break;
        }
    }

    private int cantHijos(NodoABB n) {
        int cant = 0;
        if (n.getDerecho() != null && n.getIzquierdo() != null) {
            cant = 2;
        } else {
            if (n.getIzquierdo() != null || n.getDerecho() != null) {
                cant = 1;
            }
        }
        return cant;
    }

    public Comparable mayorIzquierda(NodoABB n, NodoABB padre) {
        Comparable candidato;
        if (n.getDerecho() == null) {
            candidato = n.getElem();
            padre.setDerecho(null);
        } else {
            candidato = mayorIzquierda(n.getDerecho(), n);
        }
        return candidato;
    }

    /*
     * private void eliminarAux(NodoABB padre, NodoABB hijo) {
     * int aux = padre.getElem().compareTo(elem);
     * if (aux > 0) {
     * // el elemento a elminar es el hijo izquierdo
     * NodoABB hijo = padre.getIzquierdo();
     * eliminarHijo(padre, hijo, 0);
     * 
     * } else {
     * // es el hijo derecho
     * NodoABB hijo = padre.getDerecho();
     * eliminarHijo(padre, hijo, 1);
     * 
     * }
     * }
     */
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
                    exit = true;
                } else {
                    exit = insertarAux(hijo, elem);
                }
            } else {
                // sino lo pongo a la derecha
                NodoABB hijo = n.getDerecho();
                if (hijo == null) {
                    n.setDerecho(nuevo);
                    exit = true;
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

    public Lista listarRango(int min, int max) {
        Lista l = new Lista();
        if (!esVacio() && min < max) {
            listarRangoAux(raiz, min, max, l);
        }
        return l;
    }

    private void listarRangoAux(NodoABB n, int min, int max, Lista l) {
        if (n != null) {
            if (n.getElem().compareTo(min) > 0) {
                listarRangoAux(n.getIzquierdo(), min, max, l);
            }
            if ((n.getElem().compareTo(min) >= 0) && (n.getElem().compareTo(max) <= 0))
                l.insertar(n.getElem(), l.longitud() + 1);
            if (n.getElem().compareTo(max) < 0) {
                listarRangoAux(n.getDerecho(), min, max, l);
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
