package tests.conjuntistas;

import conjuntistas.ArbolBB;

public class TestABB {
    public static void main(String[] args) {

        /*
         * ArbolBB a = new ArbolBB();
         * a.insertar(50);
         * a.insertar(30);
         * a.insertar(70);
         * a.insertar(20);
         * a.insertar(40);
         * a.insertar(60);
         * a.insertar(80);
         * a.insertar(10);
         * a.insertar(25);
         * a.insertar(35);
         * a.insertar(45);
         * a.insertar(55);
         * a.insertar(65);
         * a.insertar(75);
         * a.insertar(85);
         * 
         * System.out.println(a.toString());
         * a.eliminar(50);
         * System.out.println(a.toString());
         * System.out.println(a.listarRango(2, 20).toString());
         */
        ArbolBB a1 = new ArbolBB();
        a1.insertar(30);
        a1.insertar(123);
        a1.insertar(23);
        a1.insertar(3);
        a1.insertar(1);
        a1.insertar(12);
        a1.insertar(33);
        a1.insertar(91);
        a1.insertar(24);
        a1.insertar(13);
        a1.insertar(10);
        a1.insertar(29);
        a1.insertar(100);
        a1.insertar(200);
        a1.insertar(139);

        // System.out.println(a1.toString());
        // System.out.println(a1.listarRango(200, 5));
        // System.out.println(a1.listarMayorIgual(20).toString());
        // System.out.println(a1.listarMenoresIgual(23));
        // System.out.println(a1.clonarInvertido(123).toString());
        ArbolBB x = new ArbolBB();
        x.insertar(16);
        x.insertar(10);
        x.insertar(7);
        x.insertar(24);
        x.insertar(20);
        x.insertar(18);
        x.insertar(22);
        x.insertar(26);
        System.out.println(x.toString());
        System.out.println(x.listarMenoresIgual(24).toString());

    }
}
