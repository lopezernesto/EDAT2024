package conjuntistas.estaticas;

public class HeapMin {
    private final int tamaño = 20;
    private Comparable[] arreglo = new Comparable[tamaño];
    private int ultimo;

    public HeapMin() {
        ultimo = 0;
    }

    public boolean insertar(Comparable elem) {
        boolean exit = true;
        if (ultimo == tamaño + 1) {
            exit = false;
        } else {
            arreglo[++ultimo] = elem;
            hacerSubir(ultimo);
        }
        return exit;
    }

    private void hacerSubir(int pos) {
        int posPadre = (int) pos / 2;
        if (posPadre >= 1 && arreglo[pos].compareTo(arreglo[posPadre]) < 0) {
            Comparable temp = arreglo[posPadre];
            arreglo[posPadre] = arreglo[pos];
            arreglo[pos] = temp;
            hacerSubir(posPadre);
        }
    }

    public boolean eliminarCima() {
        boolean exit = false;
        if (ultimo >= 1) {
            arreglo[1] = arreglo[ultimo--];
            exit = true;
            hacerBajar(1);
        }
        return exit;
    }

    private void hacerBajar(int pos) {
        int hi = pos * 2, hd = pos * 2 + 1;
        // Si tiene HI
        if (hi <= ultimo) {
            // Si tiene HD
            Comparable temp = arreglo[pos];
            if (hd <= ultimo) {
                int aux = arreglo[hi].compareTo(arreglo[hd]);
                // Si el HI es menor que el HD
                if (aux <= 0) {
                    arreglo[pos] = arreglo[hi];
                    arreglo[hi] = temp;
                    hacerBajar(hi);
                } else {
                    arreglo[pos] = arreglo[hd];
                    arreglo[hd] = temp;
                    hacerBajar(hd);
                }
            } else {
                // Si tiene HI pero no tiene HD
                if (arreglo[hi].compareTo(arreglo[pos]) < 0) {
                    arreglo[pos] = arreglo[hi];
                    arreglo[hi] = temp;
                    hacerBajar(hi);
                }
            }

        }
    }

    public Comparable recuperarCima() {
        Comparable cima = null;
        if (!esVacio()) {
            cima = arreglo[1];
        }
        return cima;
    }

    public boolean esVacio() {
        return ultimo == 0;
    }

}
