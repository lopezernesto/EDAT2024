package tests.lineales;

import lineales.dinamicas.Lista;

public class TestLista {
    public static void main(String[] args) {
        Lista l = new Lista();
        System.out.println(l.insertar(1, 1) + " inserto 1");
        System.out.println(l.insertar(2, 1) + " inserto 2");
        System.out.println(l.insertar(3, 1) + " inserto 3");
        System.out.println(l.insertar(4, 1) + " inserto 4");
        System.out.println(l.insertar(5, 1) + " inserto 5");
        System.out.println(l.insertar(6, 1) + " inserto 6");
        System.out.println(l.insertar(7, 1) + " inserto 7");
        System.out.println(l.toString());

        System.out.println(l.eliminar(0) + " elimino pos 0");
        System.out.println(l.eliminar(3) + " elimino pos 3");
        System.out.println(l.toString());

        System.out.println(l.insertar(72, 3) + " inserto 72");
        System.out.println(l.toString());

        System.out.println(l.localizar(5) + " localizo el 5");
        System.out.println(l.localizar(3) + " localizo el 3");
        System.out.println(l.eliminar(l.localizar(3)) + " elimino el que localizo antes");
        System.out.println(l.toString());

        System.out.println(l.recuperar(0) + " recupero pos 0 (null)");
        System.out.println(l.recuperar(3) + " recupero pos 3 (72)");

    }
}
