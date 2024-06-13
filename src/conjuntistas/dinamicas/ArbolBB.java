package conjuntistas.dinamicas;

import lineales.dinamicas.Lista;

public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB() {

    }

    // Dado un elemento busca el nodo y clona el subarbol de ese nodo
    public ArbolBB clonarInvertido(Comparable elem) {
        ArbolBB arbol = new ArbolBB();
        if (!esVacio()) {
            arbol.raiz = buscarElem(raiz, elem);
        }
        return arbol;
    }

    private NodoABB buscarElem(NodoABB n, Comparable elem) {
        NodoABB a = null;
        if (n != null) {
            int aux = n.getElem().compareTo(elem);
            if (aux == 0) {
                a = clonarInvertido(n);
            } else {
                if (aux < 0) {
                    a = buscarElem(n.getDerecho(), elem);
                } else {
                    a = buscarElem(n.getIzquierdo(), elem);
                }
            }
        }
        return a;

    }

    private NodoABB clonarInvertido(NodoABB original) {
        NodoABB nuevo = null;
        if (original != null) {
            nuevo = new NodoABB(original.getElem(), null, null);
            nuevo.setDerecho(clonarInvertido(original.getIzquierdo()));
            nuevo.setIzquierdo(clonarInvertido(original.getDerecho()));
        }
        return nuevo;
    }

    public Lista listarMenoresIgual(Comparable elem) {
        Lista l = new Lista();
        if (!esVacio()) {
            menoresIgualAux(raiz, elem, l);
        }
        return l;
    }

    private void menoresIgualAux(NodoABB n, Comparable elem, Lista l) {
        if (n != null) {
            int aux = n.getElem().compareTo(elem);
            if (aux <= 0) {
                menoresIgualAux(n.getDerecho(), elem, l);
                l.insertar(n.getElem(), 1);
            }
            menoresIgualAux(n.getIzquierdo(), elem, l);
        }
    }

    public Lista listarMayorIgual(Comparable elem) {
        Lista l = new Lista();
        if (!esVacio()) {
            mayorIgualAux(raiz, elem, l);
        }
        return l;
    }

    private void mayorIgualAux(NodoABB n, Comparable elem, Lista l) {
        if (n != null) {
            int aux = n.getElem().compareTo(elem);
            if (aux >= 0) {
                mayorIgualAux(n.getIzquierdo(), elem, l);
                l.insertar(n.getElem(), 1);
            }
            mayorIgualAux(n.getDerecho(), elem, l);

        }
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
                Comparable candidato = mayorIzquierda(aEliminar.getElem(), aEliminar.getIzquierdo(), aEliminar);
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

    private Comparable mayorIzquierda(Comparable elem, NodoABB n, NodoABB padre) {
        Comparable candidato = null;

        if (n.getDerecho() == null) {
            if (padre.getElem().compareTo(elem) == 0) {
                padre.setIzquierdo(n.getIzquierdo());
            } else {
                padre.setDerecho(null);
            }
            candidato = n.getElem();

        } else {
            candidato = mayorIzquierda(elem, n.getDerecho(), n);

        }
        return candidato;
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
            listarRangoAux(raiz, l, min, max);
        }
        return l;
    }

    private void listarRangoAux(NodoABB n, Lista l, int min, int max) {
        if (n != null) {
            System.out.println("entre con nodo: " + n.getElem());
            if (n.getElem().compareTo(min) > 0) {
                listarRangoAux(n.getIzquierdo(), l, min, max);

            }
            if ((n.getElem().compareTo(min) >= 0) && (n.getElem().compareTo(max) <= 0)) {
                l.insertar(n.getElem(), l.longitud() + 1);
            }
            if (n.getElem().compareTo(max) < 0) {
                listarRangoAux(n.getDerecho(), l, min, max);
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
