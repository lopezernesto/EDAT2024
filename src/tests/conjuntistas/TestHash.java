package tests.conjuntistas;

import conjuntistas.dinamicas.TablaHash;

public class TestHash {
    public static void main(String[] args) {
        Object elem = "og";
        TablaHash th = new TablaHash();
        System.out.println(th.insertar(elem));
        System.out.println(th.insertar(elem));
        System.out.println(th.eliminar(1));
        System.out.println(th.pertenece(1));
    }
}
