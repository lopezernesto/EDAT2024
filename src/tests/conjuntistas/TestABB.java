package tests.conjuntistas;

import conjuntistas.ArbolBB;

public class TestABB {
    public static void main(String[] args) {
        ArbolBB a = new ArbolBB();
        a.insertar(10);
        a.insertar(5);
        a.insertar(20);
        a.insertar(2);
        a.insertar(7);
        a.insertar(15);
        a.insertar(25);
        a.insertar(18);
        System.out.println(a.toString());
        System.out.println(a.eliminar(15));
        System.out.println(a.toString());

    }
}
