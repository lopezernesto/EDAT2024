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
        if (ultimo == tamaño) {
            exit = false;
        } else {
            ultimo++;
            arreglo[ultimo] = elem;
            hacerSubir();
        }
        return exit;
    }

    private void hacerSubir() {
        int padre = (int) ultimo / 2;
    }

    public boolean eliminarCima() {
        boolean exit = false;
        if (ultimo >= 1) {
            arreglo[1] = arreglo[ultimo];
            // arreglo[ultimo] = null;
            ultimo--;
            exit = true;
            hacerBajar(1);
        }
        return exit;
    }

    private void hacerBajar(int padre) {

    }

}
