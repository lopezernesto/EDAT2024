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

    // Dado una cola, verifica los capicuas de una cola de Char separados por $
    public static int cuentaSecuencias(Cola c) {
        int cont = 0;
        Cola c1 = c.clone();
        if (!c1.esVacia()) {
            Cola c2 = new Cola();
            Object frente = c1.obtenerFrente();
            Pila p = new Pila();
            while (frente != null) {
                if (frente.equals('$')) {
                    if (iguales(p, c2))
                        cont++;
                } else {
                    p.apilar(frente);
                    c2.poner(frente);
                }
                c1.sacar();
                frente = c1.obtenerFrente();
            }
            if (iguales(p, c2))
                cont++;
        }
        return cont;
    }

    private static boolean iguales(Pila p, Cola c) {
        boolean exit = true;
        Object tope = p.obtenerTope();
        while (exit && tope != null) {
            if (!p.obtenerTope().equals(c.obtenerFrente())) {
                exit = false;
            } else {
                p.desapilar();
                c.sacar();
            }
            tope = p.obtenerTope();

        }
        p.vaciar();
        c.vaciar();
        return exit;
    }

    public static boolean verificarBalanceo(Cola c) {
        boolean exit = true;
        if (!c.esVacia()) {
            Cola c1 = c.clone(), c2 = new Cola();
            Pila p = new Pila();
            Object frente = c1.obtenerFrente();
            while (frente != null) {
                if (frente.equals('(') || frente.equals(')') || frente.equals('[') || frente.equals(']')
                        || frente.equals('{') || frente.equals('}')) {
                    p.apilar(frente);
                    c2.poner(frente);
                }
                c1.sacar();
                frente = c1.obtenerFrente();
            }
            exit = verificar(p, c2);
        }
        return exit;
    }

    private static boolean verificar2(Pila p, Cola c) {
        boolean exit = true;
        Pila invertida = new Pila();
        Object tope = p.obtenerTope();
        while (tope != null) {
            invertida.apilar(tope);
            p.desapilar();
            tope = p.obtenerTope();
        }
        tope = invertida.obtenerTope();
        while (exit && tope != null) {
            System.out.println("tope: " + tope + " frente: " + c.obtenerFrente());
            if (!tope.equals(c.obtenerFrente())) {
                exit = false;
            } else {
                invertida.desapilar();
                c.sacar();
                tope = invertida.obtenerTope();
            }
        }
        return exit;
    }

    public static void main(String[] args) {
        Cola c = new Cola(), cola = new Cola();
        cola.poner('{');
        cola.poner('5');
        cola.poner('+');
        cola.poner('[');
        cola.poner('8');
        cola.poner('*');
        cola.poner('9');
        cola.poner('-');
        cola.poner('(');
        cola.poner('4');
        cola.poner('/');
        cola.poner('2');
        cola.poner(')');
        cola.poner('+');
        cola.poner('7');
        cola.poner(']');
        cola.poner('-');
        cola.poner('1');
        cola.poner('}');

        // ABCBA$CDDE$AFCCFA
        c.poner('A');
        c.poner('B');
        c.poner('C');
        c.poner('B');
        c.poner('A');
        c.poner('$');
        c.poner('C');
        c.poner('D');
        c.poner('D');
        c.poner('E');
        c.poner('$');
        c.poner('A');
        c.poner('F');
        c.poner('C');
        c.poner('C');
        c.poner('F');
        c.poner('A');
        System.out.println(cola.toString());
        System.out.println(verificarBalanceo(cola));
        // System.out.println(cuentaSecuencias(cola));
        // System.out.println("La cola original es: " + c.toString());
        // resultado = generar(c);
        // System.out.println("La nueva cola es: " + resultado.toString());
    }

}
