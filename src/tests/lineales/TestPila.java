package tests.lineales;

import java.util.Scanner;

//import lineales.dinamicas.Pila;
import lineales.estaticas.Pila;

/*
 * FAI-1990 Lopez Ernesto
 * FAI-3666 Fuentes Santino
 * FAI-2086 Novoa Facundo
 */

public class TestPila {
    public static void menu() {
        System.out.println("---------------------------------");
        System.out.println("Ingrese una opcion");
        System.out.println("1) Apilar");
        System.out.println("2) Desapilar");
        System.out.println("3) Obtener tope");
        System.out.println("4) Vaciar");
        System.out.println("5) Clonar");
        System.out.println("0) Salir");
        System.out.println("---------------------------------");
    }

    public static void apilar(Pila p1) {
        int num;
        boolean exit;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un numero para apilar");
        num = sc.nextInt();
        exit = p1.apilar(num);
        if (exit) {
            System.out.println("Apilamos el " + num + " en la pila: [" + p1.toString() + "]");
        } else {
            System.out.println("No se pudo apilar, pila llena. [" + p1.toString() + "]");
        }
    }

    public static void desapilar(Pila p1) {
        Object tope = p1.obtenerTope();
        boolean exit = p1.desapilar();
        if (exit) {
            System.out.println("Se ha desapilado el " + tope + " en la pila: [" + p1.toString() + "]");
        } else {
            System.out.println("No se puede desapilar si la pila está vacía");
        }

    }

    public static void obtenerTope(Pila p1) {
        Object tope = p1.obtenerTope();
        if (tope == null) {
            System.out.println("La pila está vacía, no tiene tope. [" + p1.toString() + "]");
        } else {
            System.out.println("El tope de la pila [" + p1.toString() + "] es: " + tope);
        }
    }

    public static void vaciar(Pila p1) {
        if (p1.esVacia()) {
            System.out.println("La pila ya está vacía");
        } else {
            Pila aux = p1.clone();
            p1.vaciar();
            System.out.println("Vaciamos la pila [" + aux.toString() + "] ||| [" + p1.toString() + "]");
        }
    }

    public static void clonar() {

    }

    public static void main(String[] args) {
        int opcion;
        Pila p1 = new Pila(), clon;
        Scanner sc = new Scanner(System.in);
        do {
            menu();
            System.out.println("Ingrese la opcion a realizar");
            opcion = sc.nextInt();
            if (opcion != 0 && opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4 && opcion != 5) {
                System.out.println("---------------------------------");
                System.out.println("La opcion ingresada es incorrecta");
            }
            switch (opcion) {
                case 1:
                    apilar(p1);
                    break;
                case 2:
                    desapilar(p1);
                    break;
                case 3:
                    obtenerTope(p1);
                    break;
                case 4:
                    vaciar(p1);
                    break;
                case 5:
                    break;
                default:

                    break;
            }
        } while (opcion != 0);
    }
}
