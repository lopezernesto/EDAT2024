package conjuntistas;

public class NodoABB {
    private NodoABB HI, HD;
    private Comparable elem;

    public NodoABB(Comparable elem, NodoABB hijoIzquierdo, NodoABB hijoDerecho) {
        this.elem = elem;
        HI = hijoIzquierdo;
        HD = hijoDerecho;
    }

    public NodoABB getIzquierdo() {
        return HI;
    }

    public void setIzquierdo(NodoABB hijoIzquierdo) {
        this.HI = hijoIzquierdo;
    }

    public NodoABB getDerecho() {
        return HD;
    }

    public void setDerecho(NodoABB hijoDerecho) {
        this.HD = hijoDerecho;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }
}
