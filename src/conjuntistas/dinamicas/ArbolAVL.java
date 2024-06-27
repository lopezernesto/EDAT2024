package conjuntistas.dinamicas;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {

    }

    public boolean esVacio() {
        return raiz == null;
    }

    public boolean pertenece(Comparable elem) {
        boolean exit = false;
        if (!esVacio()) {
            exit = perteneceAux(raiz, elem);
        }
        return exit;
    }

    private boolean perteneceAux(NodoAVL n, Comparable elem) {
        boolean exit = false;
        if (n != null) {
            int temp = n.getElem().compareTo(elem);
            if (temp == 0) {
                exit = true;
            } else {
                if (temp < 0) {
                    exit = perteneceAux(n.getDerecho(), elem);
                } else {
                    exit = perteneceAux(n.getIzquierdo(), elem);
                }
            }
        }
        return exit;
    }

    public boolean insertar(Comparable elem) {
        boolean exit = false;
        if (esVacio()) {
            raiz = new NodoAVL(elem);
            exit = true;
        } else {
            exit = insertarAux(raiz, elem);

        }
        return exit;
    }

    private boolean insertarAux(NodoAVL n, Comparable elem) {
        boolean exit = false;
        int temp = n.getElem().compareTo(elem);
        if (temp != 0) {
            NodoAVL hijo;
            if (temp < 0) {
                hijo = n.getDerecho();
                if (hijo == null) {
                    exit = true;
                    n.setDerecho(new NodoAVL(elem));
                    calcularBalance(raiz, elem);
                } else {
                    exit = insertarAux(hijo, elem);
                }
            } else {
                hijo = n.getIzquierdo();
                if (hijo == null) {
                    n.setIzquierdo(new NodoAVL(elem));
                    exit = true;
                    calcularBalance(raiz, elem);
                }

            }

        }
        return exit;
    }

    private int calcularBalance(NodoAVL n, Comparable elem) {
        int balance = 0;
        balance = n.getIzquierdo().getAltura() - n.getDerecho().getAltura();
        if (Math.abs(balance) > 1) {

        }
        return balance;
    }
}
