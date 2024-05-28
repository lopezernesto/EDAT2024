package tests.conjuntistas;

import conjuntistas.ArbolBB;

public class TestABB {
    public static void main(String[] args) {

        ArbolBB a = new ArbolBB();
        a.insertar(50);
        a.insertar(30);
        a.insertar(70);
        a.insertar(20);
        a.insertar(40);
        a.insertar(60);
        a.insertar(80);
        a.insertar(10);
        a.insertar(25);
        a.insertar(35);
        a.insertar(45);
        a.insertar(55);
        a.insertar(65);
        a.insertar(75);
        a.insertar(85);

        System.out.println(a.toString());
        a.eliminar(50);
        System.out.println(a.toString());
        System.out.println(a.listarRango(10, 40).toString());

    }
}
