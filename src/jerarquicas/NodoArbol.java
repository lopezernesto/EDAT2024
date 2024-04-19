package jerarquicas;

public class NodoArbol {
    private NodoArbol izquierdo, derecho;
    private Object elem;

    public NodoArbol(Object elem, NodoArbol izq, NodoArbol der) {
        this.elem = elem;
        izquierdo = izq;
        derecho = der;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }
}
