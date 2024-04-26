package lineales.dinamicas;

public class Cola {
    private Nodo frente, fin;

    public Cola() {
    }

    public boolean poner(Object elem) {
        Nodo nuevo = new Nodo(elem, null);
        if (esVacia()) {
            frente = fin = nuevo;
        } else {
            fin.setEnlace(nuevo);
            fin = nuevo;
        }
        return true;
    }

    public boolean sacar() {
        boolean exit = false;
        if (!esVacia()) {
            exit = true;
            frente = frente.getEnlace();
            if (frente == null) {
                fin = null;
            }
        }
        return exit;
    }

    public boolean esVacia() {
        return frente == null;
    }

    public Object obtenerFrente() {
        Object ret = null;
        if (!esVacia()) {
            ret = frente.getElem();
        }
        return ret;
    }

    public void vaciar() {
        frente = fin = null;
    }

    public Cola clone() {
        Cola clonada = new Cola();
        if (!esVacia()) {
            Nodo aux = frente, nuevo = new Nodo(aux.getElem(), null), clon = nuevo;
            clonada.frente = clon;
            aux = aux.getEnlace();
            while (aux != null) {
                nuevo = new Nodo(aux.getElem(), null);
                clon.setEnlace(nuevo);
                clon = clon.getEnlace();
                clonada.fin = clon;
                aux = aux.getEnlace();
            }

        }
        return clonada;
    }

    public String toString() {
        String cad = "La cola está vacía";
        if (!esVacia()) {
            cad = "";
            Nodo aux = frente;
            while (aux != null) {
                cad += aux.getElem() + "|";
                aux = aux.getEnlace();
            }
        }
        return cad;
    }

    /*
     * c) En una clase TestCadenas, que utilice los TDA Lista, Pila y Cola vistos en
     * la materia, para guardar elementos
     * de tipo CHAR, implementar el método generar (Cola c1) que recibe por
     * parámetro una estructura cola c1
     * que tiene el siguiente formato: a1#a2#a3#….#an, donde cada ai en una sucesión
     * de letras mayúsculas y a
     * partir de c1 debe generar como salida otra Cola de la forma:
     * a1a1´a1#a2a2´a2#….#anan´an donde cada ai´ es la
     * secuencia de letras mayúsculas ai pero invertida. Ejemplo.: Si c1 es :
     * AB#C#DEF , entonces la operación
     * generar devolverá una Cola con el siguiente formato: ABBAAB#CCC#DEFFEDDEF.
     */
    public Cola generar(Cola c1) {
        Cola c = new Cola();
        if (!esVacia()) {
            boolean bandera = true; // bandera = true significa que estoy en el primer elemento
            Nodo original = frente, otro = c.frente;
            while (original != null) {
                String cad = transformar(original.getElem());
            }

        }
        return c;
    }

    private String transformar(Object elem) {
        String cad = "";

        return cad;
    }
}
