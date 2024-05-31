package tests.jerarquicas;

import jerarquicas.ArbolGen;
import lineales.dinamicas.Lista;

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

        // System.out.println(a.altura());
        // System.out.println(a.padre(2));
        // System.out.println(a.pertenece(-1));
        // System.out.println(a.listarPreorden().toString());
        // System.out.println(a.ancestros(1).toString());
        // c = a.clone();
        // System.out.println(a.toString());
        // System.out.println(a.listarHastaNivel(1).toString());
        ArbolGen arbol = new ArbolGen();
        arbol.insertar(1, -1); // Insertar la raíz
        arbol.insertar(2, 1); // Insertar 2 como hijo de 1
        arbol.insertar(3, 1); // Insertar 3 como hijo de 1
        arbol.insertar(4, 1); // Insertar 4 como hijo de 1
        arbol.insertar(5, 2); // Insertar 5 como hijo de 2
        arbol.insertar(6, 2); // Insertar 6 como hijo de 2
        arbol.insertar(7, 4); // Insertar 7 como hijo de 4
        arbol.insertar(8, 7); // Insertar 8 como hijo de 7
        arbol.insertar(9, 7); // Insertar 9 como hijo de 7
        arbol.insertar(10, 7); // Insertar 10 como hijo de 7
        // System.out.println(arbol.listarHastaNivel(2).toString());
        // System.out.println(a.listaQueJustificaLaAltura().toString());
        Lista lista = new Lista(), x;
        lista.insertar(20, 1); // Insertar 1 en la posición 0
        lista.insertar(17, 2); // Insertar 4 en la posición 1

        ArbolGen a1 = new ArbolGen();
        a1.insertar(20, 0);
        a1.insertar(13, 20);
        a1.insertar(54, 20);
        a1.insertar(15, 13);
        a1.insertar(12, 13);
        a1.insertar(11, 54);
        a1.insertar(27, 54);
        a1.insertar(4, 54);
        a1.insertar(17, 27);
        // System.out.println(a1.verificarCamino(lista));
        // System.out.println(a1.listarEntreNiveles(2, 3).toString());
        System.out.println(a1.toString());
        System.out.println(a1.eliminar(13));
        System.out.println(a1.toString());

    }
}
