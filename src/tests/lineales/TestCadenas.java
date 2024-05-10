package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

public class TestCadenas {
    private static void modificar(Cola cola) {
        Pila p = new Pila(), invertida = new Pila();
        Cola aux = cola.clone();
        Object elem;
        // Este while es para obtener los elementos de forma invertida
        while (!aux.esVacia()) {
            elem = aux.obtenerFrente();
            invertida.apilar(elem);
            aux.sacar();
        }
        // Este while es para poner los caracteres invertidos y agregarlos a otra pila
        while (!invertida.esVacia()) {
            elem = invertida.obtenerTope();
            p.apilar(elem);
            cola.poner(elem);
            invertida.desapilar();
        }
        // La ultima pila pone los elementos de nuevo en su orden
        while (!p.esVacia()) {
            elem = p.obtenerTope();
            cola.poner(elem);
            p.desapilar();
        }
    }

    private static void agregarCola(Cola original, Cola fragmento) {
        Object elem;
        while (!fragmento.esVacia()) {
            elem = fragmento.obtenerFrente();
            original.poner(elem);
            fragmento.sacar();
        }
    }

    public static Cola generar(Cola c1) {
        Cola nueva = new Cola();
        if (!c1.esVacia()) {
            Object frente = c1.obtenerFrente();
            // Aux va a ser mi cola que va entre #-#
            Cola aux = new Cola();
            do {
                if (frente.equals('#')) {
                    modificar(aux);
                    agregarCola(nueva, aux);
                    nueva.poner('#');
                    aux.vaciar();
                } else {
                    aux.poner(frente);
                }
                c1.sacar();
                frente = c1.obtenerFrente();
            } while (frente != null);
            // Si aux no es vacio, resulta que c1 no terminaba en #
            if (!aux.esVacia()) {
                // Por lo tanto hay que agregar esa ultima parte
                modificar(aux);
                agregarCola(nueva, aux);
                aux.vaciar();
            }
        }
        return nueva;
    }

    public static void main(String[] args) {
        Cola c = new Cola(), resultado;
        c.poner('A');
        c.poner('B');
        c.poner('#');
        c.poner('C');
        c.poner('#');
        c.poner('D');
        c.poner('E');
        c.poner('F');
        System.out.println("La cola original es: " + c.toString());
        resultado = generar(c);
        System.out.println("La nueva cola es: " + resultado.toString());
    }

}
