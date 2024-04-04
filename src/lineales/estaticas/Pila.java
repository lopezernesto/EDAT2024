package lineales.estaticas;

public class Pila {
    private int tope, tamaño = 10;
    private Object arreglo[] = new Object[tamaño];

    public Pila() {
        tope = -1;
    }

    public boolean apilar(Object elem) {
        boolean exit = false;
        if (tope + 1 < tamaño) {
            exit = true;
            tope++;
            arreglo[tope] = elem;
        }
        return exit;
    }

    public boolean esVacia() {
        return tope == -1;
    }

    public boolean desapilar() {
        boolean exit = false;
        if (!esVacia()) {
            arreglo[tope] = null;
            tope--;
            exit = true;
        }
        return exit;
    }

    public Object obtenerTope() {
        Object exit = null;
        if (!esVacia()) {
            exit = arreglo[tope];
        }
        return exit;
    }

    public Pila clone() {
        Pila clonada = new Pila();
        if (!esVacia()) {
            clonada.tope = tope;
            for (int i = 0; i <= tope; i++) {
                clonada.arreglo[i] = this.arreglo[i];
            }
        }
        return clonada;
    }

    public void vaciar() {
        for (int i = 0; i <= tope; i++) {
            arreglo[i] = null;
        }
    }

    public String toString() {
        String cad = "La pila está vacía";
        if (!esVacia()) {
            cad = "";
            for (int i = 0; i < tamaño; i++) {
                Object aux = arreglo[i];
                if (aux == null) {
                    cad += "- ";
                } else {
                    cad += aux + " ";
                }
            }
        }
        return cad;
    }
}
