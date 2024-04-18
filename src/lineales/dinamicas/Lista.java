package lineales.dinamicas;

public class Lista {
    private Nodo cabecera;
    private int longitud;

    public Lista() {
        longitud = 0;
    }

    public boolean insertar(Object elem, int pos) {
        boolean exit = false;
        if (pos > 0 && pos <= longitud + 1) {
            exit = true;
            if (pos == 1) {
                cabecera = new Nodo(elem, cabecera);
            } else {
                int i = 2;
                Nodo aux = cabecera;
                while (i <= pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            longitud++;
        }
        return exit;
    }

    public boolean eliminar(int pos) {
        boolean exit = false;
        if (pos > 0 && pos <= longitud) {
            exit = true;
            if (pos == 1) {
                cabecera = cabecera.getEnlace();
            } else {
                int i = 2;
                Nodo aux = cabecera;
                while (i <= pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            longitud--;
        }
        return exit;
    }

    public Object recuperar(int pos) {
        Object ret = null;
        if (!esVacia()) {
            if (pos > 0 && pos <= longitud) {
                if (pos == 1) {
                    ret = cabecera.getElem();
                } else {
                    int i = 2;
                    Nodo aux = cabecera;
                    while (i <= pos) {
                        aux = aux.getEnlace();
                        i++;
                    }
                    ret = aux.getElem();
                }
            }
        }
        return ret;
    }

    public int localizar(Object elem) {
        int pos = -1;
        if (!esVacia()) {
            Nodo aux = cabecera;
            int i = 1;
            do {
                if (aux.getElem().equals(elem)) {
                    pos = i;
                    i = longitud + 1;
                }
                i++;
                aux = aux.getEnlace();
            } while (i <= longitud);
        }
        return pos;
    }

    public int longitud() {
        return longitud;
    }

    public boolean esVacia() {
        return (longitud == 0);
    }

    public void vaciar() {
        longitud = 0;
        cabecera = null;
    }

    public Lista clone() {
        Lista clonada = new Lista();
        if (!esVacia()) {
            Nodo aux = cabecera;
            clonada.cabecera = new Nodo(aux.getElem(), null);
            Nodo clon = clonada.cabecera;
            aux = aux.getEnlace();
            while (aux != null) {
                Nodo nuevo = new Nodo(aux.getElem(), null);
                clon.setEnlace(nuevo);
                clon = clon.getEnlace();
                aux = aux.getEnlace();
            }
        }
        return clonada;
    }

    public String toString() {
        String cad = "La lista está vacía";
        if (!esVacia()) {
            cad = "[";
            Nodo aux = cabecera;
            while (aux != null) {
                cad += aux.getElem() + " ";
                aux = aux.getEnlace();
            }
            cad += "]";
        }
        return cad;
    }
}