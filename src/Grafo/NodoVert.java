package Grafo;

public class NodoVert {
    private NodoAdy primerArco;
    private NodoVert siguienteVertice;
    private String nombre;

    // Creo un Vertice
    public NodoVert(String nombre) {
        this.nombre = nombre;
    }

    public boolean equals(NodoVert vertice) {
        return nombre.equals(vertice.getNombre());
    }

    public boolean insertarArco(NodoVert destino, int tiempo) {
        boolean exit = false;
        if (primerArco == null) {
            primerArco = new NodoAdy(destino, tiempo);
        } else {
            NodoAdy aux = primerArco;
            while (!exit && aux.getSiguiente() != null) {
                exit = aux.getVertice().equals(destino);
                aux = aux.getSiguiente();
            }
            if (exit)
                aux.setSiguiente(new NodoAdy(destino, tiempo));
        }
        return !exit;
    }

    public boolean eliminarArco(NodoVert destino) {
        boolean exit = false;
        // Dado un vertice que eliminaremos
        NodoAdy aux = primerArco, anterior = null;
        // Para cada arco que haya en este vertice
        while (aux != null) {
            // Verificamos que sea con el que borraremos
            if (aux.getVertice().equals(destino)) {
                exit = true;
                if (anterior == null) {
                    // Si esta en la primer posicion
                    primerArco = primerArco.getSiguiente();
                } else {
                    // Si no esta en una posicion intermedia-final
                    anterior.setSiguiente(aux.getSiguiente());
                }
            }
            anterior = aux;
            aux = aux.getSiguiente();
        }
        return exit;
    }

    public NodoAdy getPrimerArco() {
        return primerArco;
    }

    public void setPrimerArco(NodoAdy primerArco) {
        this.primerArco = primerArco;
    }

    public NodoVert getSiguienteVertice() {
        return siguienteVertice;
    }

    public void setSiguienteVertice(NodoVert siguienteVertice) {
        this.siguienteVertice = siguienteVertice;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
