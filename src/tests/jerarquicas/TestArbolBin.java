package tests.jerarquicas;

import jerarquicas.ArbolBin;

public class TestArbolBin {
    public static void main(String[] args) {
        ArbolBin a = new ArbolBin(), b = new ArbolBin();
        System.out.println(a.toString());
        System.out.println(a.insertar(1, 0, 0));
        System.out.println(a.insertar(2, 1, 0));
        System.out.println(a.insertar(3, 1, 1));
        System.out.println(a.insertar(4, 2, 0));
        System.out.println(a.insertar(6, 2, 1));
        System.out.println(a.insertar(5, 3, 0));
        System.out.println(a.insertar(7, 3, 1));

        System.out.println(a.toString());

        System.out.println(a.insertarPorPosicion(8, 6, 1));
        System.out.println(a.toString());
        // System.out.println(a.altura());
        // System.out.println(a.nivel(4));
        b.insertar(1, 0, 0);
        b.insertar(2, 1, 0);
        b.insertar(3, 1, 1);
        b.insertar(4, 2, 0);
        b.insertar(6, 2, 1);
        b.insertar(5, 3, 0);
        b.insertar(7, 3, 1);
        b.insertarPorPosicion(8, 6, 1);
        System.out.println(a.equals(b));

    }
}
