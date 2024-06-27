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
        if (exit) {
            balancear(raiz, null, elem);
        }
        return exit;

    }

    private boolean insertarAux(NodoAVL n, Comparable elem) {
        System.out.println("Hola soy :" + n.getElem());
        boolean exit = false;
        int temp = n.getElem().compareTo(elem);
        if (temp != 0) {
            NodoAVL hijo;
            if (temp < 0) {
                hijo = n.getDerecho();
                if (hijo == null) {
                    exit = true;
                    n.setDerecho(new NodoAVL(elem));
                } else {
                    exit = insertarAux(hijo, elem);
                    if (exit) {
                        n.recalcularAltura();
                    }

                }
            } else {
                hijo = n.getIzquierdo();
                if (hijo == null) {
                    n.setIzquierdo(new NodoAVL(elem));
                    exit = true;
                } else {
                    exit = insertarAux(hijo, elem);
                    if (exit) {
                        n.recalcularAltura();
                    }
                }

            }

        }
        return exit;
    }

    private void balancear(NodoAVL n, NodoAVL padre, Comparable elem) {
        // caso a tener en cuenta n=raiz y balance +- 2
        int temp = n.getElem().compareTo(elem);
        if (temp != 0) {
            int balanceHijo, balance = calcularBalance(n);
            System.out.println("El balance de " + n.getElem() + " es " + balance);
            if (Math.abs(balance) == 2) {
                if (balance == -2) {
                    balanceHijo = calcularBalance(n.getDerecho());
                    if (balance * balanceHijo >= 0) {
                        simpleDerIzq(padre, n);
                    } else {
                        simpleIzqDer(n, n.getDerecho());
                        simpleDerIzq(padre, n);
                    }

                } else {
                    if (balance == 2) {
                        balanceHijo = calcularBalance(n.getIzquierdo());
                        if (balance * balanceHijo >= 0) {
                            simpleIzqDer(padre, n);
                        } else {
                            simpleDerIzq(n, n.getIzquierdo());
                            simpleIzqDer(padre, n);
                        }
                    }

                }
            } else {
                if (temp < 0)
                    balancear(n.getDerecho(), n, elem);
                else {
                    balancear(n.getIzquierdo(), n, elem);
                }
            }
        }
    }

    private int calcularBalance(NodoAVL n) {
        int altHD = -1, altHI = -1, balance;
        if (n.getIzquierdo() != null)
            altHI = n.getIzquierdo().getAltura();
        if (n.getDerecho() != null)
            altHD = n.getDerecho().getAltura();
        balance = altHI - altHD;
        System.out.println("Calcula el balance de: " + n.getElem() + " y es: " + altHI + " - " + altHD + " es igual a: "
                + balance);
        return balance;
    }

    private void simpleIzqDer(NodoAVL padre, NodoAVL n) {
        // n es mi Nodo desbalanceado
        NodoAVL hijo = n.getIzquierdo();
        padre.setIzquierdo(hijo);
        n.setIzquierdo(hijo.getDerecho());
        hijo.setDerecho(n);
    }

    private void simpleDerIzq(NodoAVL padre, NodoAVL n) {
        // n es mi Nodo desbalanceado
        NodoAVL hijo = n.getDerecho();
        padre.setDerecho(hijo);
        n.setDerecho(hijo.getIzquierdo());
        hijo.setIzquierdo(n);
    }

    public String toString() {
        String cad = "Arbol vacio";
        if (!esVacio()) {
            cad = toStringAux(raiz);
        }
        return cad;
    }

    public String toStringAux(NodoAVL n) {
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
