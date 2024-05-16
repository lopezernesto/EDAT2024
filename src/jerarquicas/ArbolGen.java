package jerarquicas;

import lineales.dinamicas.Lista;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {

    }

    public Lista listarPreorden() {
        Lista lista = new Lista();
        listarPreordenAux(raiz, lista);
        return lista;
    }

    private void listarPreordenAux(NodoGen n, Lista lista) {
        if (n != null) {
            lista.insertar(n.getElem(), lista.longitud() + 1);
            if (n.getHermanoDerecho() != null) {
                NodoGen hermano = n.getHermanoDerecho();
                while (hermano != null) {
                    listarPreordenAux(n.getHermanoDerecho(), lista);
                }
            }
        }
    }
}
