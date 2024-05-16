package jerarquicas;

public class NodoGen {
    private NodoGen HEI, HD;
    private Object elem;

    public NodoGen(Object elem, NodoGen hijo, NodoGen hermano) {
        this.elem = elem;
        HEI = hijo;
        HD = hermano;
    }

    public NodoGen getHijoIzquierdo() {
        return HEI;
    }

    public void setHijoIzquierdo(NodoGen hijo) {
        this.HEI = hijo;
    }

    public NodoGen getHermanoDerecho() {
        return HD;
    }

    public void setHermanoDerecho(NodoGen hermano) {
        this.HD = hermano;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }
}
