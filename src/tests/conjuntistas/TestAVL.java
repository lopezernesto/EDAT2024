package tests.conjuntistas;

import conjuntistas.dinamicas.ArbolAVL;

public class TestAVL {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        System.out.println(arbol.insertar(5));
        System.out.println(arbol.insertar(4));
        System.out.println(arbol.insertar(8));
        System.out.println(arbol.insertar(6));
        System.out.println(arbol.toString());
        System.out.println("_---------");
        System.out.println(arbol.insertar(7));
        System.out.println(arbol.insertar(10));

        System.out.println(arbol.toString());

    }
}
