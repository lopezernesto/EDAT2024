package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
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
            while (frente != null && exit) {

                if (frente.equals('(') || frente.equals('[') || frente.equals('{')) {
                    p.apilar(frente);
                } else {
                    if (frente.equals(')') || frente.equals(']') || frente.equals('}')) {

                        exit = verificar(frente, p);
                        if (exit)
                            p.desapilar();
                    }
                }
                c1.sacar();
                frente = c1.obtenerFrente();
            }

        }
        return exit;
    }

<<<<<<< HEAD
    private static boolean verificar(Pila p, Cola c) {
=======
    private static boolean verificar(Object elem, Pila p) {
>>>>>>> bbefb1f2874859c7816a4bf0006c8c977ca58b6f
        boolean exit = true;
        char cierre = (char) elem;
        char tope = (char) p.obtenerTope();
        System.out.println(cierre + " y " + tope);
        switch (cierre) {
            case ')':
                if (tope != '(')
                    exit = false;
                break;
            case ']':
                if (tope != '[')
                    exit = false;
                break;
            case '}':
                if (tope != '{')
                    exit = false;
                break;
            default:
                break;
        }

        return exit;
    }

    public static boolean comprobar(Lista l) {
        // candena0cadena0cadena*
        boolean exit = true;
        Object elem = l.recuperar(1);
        int i = 1, longitud = l.longitud();
        if (!l.esVacia()) {
            // caso de cadena nula
            if (elem.equals(0)) {
                // Si la cadena empieza con un 0 es porque va a ser la cadena nula.
                if ((longitud != 2 || !l.recuperar(2).equals(0))) {
                    // Si la longitud es distinta a 2 o el segundo elemento no es 0...
                    exit = false;
                }
            } else {
                Cola c = new Cola();
                Pila inv = new Pila();
                while (i <= longitud && !elem.equals(0)) {
                    System.out.println("apilo el " + elem);
                    c.poner(elem);
                    inv.apilar(elem);
                    i++;
                    elem = l.recuperar(i);
                }
                // Si termino de recorrer la lista es porque no tenia 0 que separen
                if (i == longitud) {
                    exit = false;
                } else {
                    i++;
                    elem = l.recuperar(i);
                    while (exit && i <= longitud && !elem.equals(0)) {
                        System.out.println("Elem " + elem + " frente: " + c.obtenerFrente());
                        if (!elem.equals(c.obtenerFrente())) {
                            exit = false;
                        }
                        c.sacar();
                        i++;
                        elem = l.recuperar(i);
                    }
                    // Si termino de recorrer o c no es vacia es porque era una cadena de tipo:
                    // cad0cad o cad0cad0 o cad0cad2
                    if (i == longitud || !c.esVacia()) {
                        exit = false;
                    } else {
                        i++;
                        while (exit && i <= longitud) {
                            elem = l.recuperar(i);
                            System.out.println("Elem: " + elem + " Tope: " + inv.obtenerTope());
                            if (!elem.equals(inv.obtenerTope())) {
                                exit = false;
                            }
                            inv.desapilar();
                            i++;
                        }
                        // Si inv no es vacia es porque es cad0cad0cad2
                        if (!inv.esVacia()) {
                            exit = false;
                        }
                    }
                }
            }
        }
        return exit;
    }

    public static void main(String[] args) {
        Cola c = new Cola(), cola = new Cola();
        Lista l = new Lista(), l1 = new Lista();
        l1.insertar(1, 1);
        l1.insertar(0, 2);
        l1.insertar(1, 3);
        l1.insertar(0, 4);
        l1.insertar(1, 5);
        l1.insertar(0, 6);
        l1.insertar(1, 7);

        // 9,6,5,0,9,6,5,0,5,6,9
        l.insertar(9, 1);
        l.insertar(6, 2);
        l.insertar(5, 3);
        l.insertar(0, 4);
        l.insertar(9, 5);
        l.insertar(6, 6);
        l.insertar(5, 7);
        l.insertar(0, 8);
        l.insertar(5, 9);
        l.insertar(6, 10);
        l.insertar(9, 11);

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
        // System.out.println(cola.toString());
        System.out.println(comprobar(l1));
        // System.out.println(cuentaSecuencias(cola));
        // System.out.println("La cola original es: " + c.toString());
        // resultado = generar(c);
        // System.out.println("La nueva cola es: " + resultado.toString());
    }

}
