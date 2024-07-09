package tests.conjuntistas;

import conjuntistas.dinamicas.ArbolAVL;

public class TestAVL {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        // arbol.insertar(7);
        // arbol.insertar(8);
        // arbol.insertar(5);

        // arbol.insertar(6);

        // arbol.insertar(9);

        arbol.insertar(15);
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(5);
        arbol.insertar(12);
        arbol.insertar(25);
        arbol.insertar(3);
        arbol.insertar(8);

        System.out.println(arbol.toString());
        arbol.eliminar(15);
        arbol.insertar(4);
        arbol.insertar(6);
        arbol.insertar(11);
        System.out.println(arbol.toString());

        // ------ 15
        // ----- / \
        // --- 10 - 20
        // --- / \ - \
        // ---5 -12 - 25
        // --/ \
        // -3 - 8

    }
}
