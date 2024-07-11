package tests.grafo;

import Grafo.Grafo;

public class TestGrafo {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.insertarVertice("Cipolletti");
        grafo.insertarVertice("Neuquen");
        grafo.insertarVertice("Senillosa");
        grafo.insertarVertice("Plottier");
        grafo.insertarArco("Cipolletti", "Neuquen", 10);
        System.out.println("------------");
        grafo.insertarArco("Neuquen", "Plottier", 1110);
        grafo.insertarArco("Senillosa", "Neuquen", 0);
        System.out.println(grafo.eliminarArco("Neuquen", "Plottier"));
        System.out.println(grafo.toString());
    }
}
