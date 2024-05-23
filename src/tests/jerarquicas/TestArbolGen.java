package tests.jerarquicas;

import jerarquicas.ArbolGen;

public class TestArbolGen {
    public static void main(String[] args) {
        ArbolGen a = new ArbolGen(), c;
        a.insertar(1, 0);
        a.insertar(3, 1);
        a.insertar(5, 1);
        a.insertar(7, 1);
        a.insertar(6, 3);
        a.insertar(8, 3);
        a.insertar(2, 3);
        a.insertar(4, 3);
        a.insertar(9, 4);
        // --------------------------------------
        String s = a.toString();
        System.out.println(s);
        System.out.println(a.altura());
        System.out.println(a.padre(2));
        System.out.println(a.pertenece(-1));
        System.out.println(a.listarPreorden().toString());
        System.out.println(a.ancestros(1).toString());
        c = a.clone();
        System.out.println(c.toString());

    }
}
