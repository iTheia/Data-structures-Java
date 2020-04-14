package carreteras;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Grafo grafo = new Grafo(true);
        String[] ciudades = {
            "Durango", "Canatlan", "Cuernavaca", "Fresnillo", "Guadalajara",
            "Leon", "Mexico", "Puebla", "Pechuca", "Queretaro",
            "Saltillo", "San Luis Potosi", "Torreon", "Toluca", "Tulancingo",
            "Zacatecas"};
        ArrayList<Ciudad> City = new ArrayList();
        for (int i = 0; i < ciudades.length; i++) {
            Ciudad c = new Ciudad(i, ciudades[i]);
            City.add(c);
            grafo.AgregarCiudad(c);
        }
        for (int i = 0; i < ciudades.length; i++) {
            System.out.println(i+" ->"+ ciudades[i]);
        }
        grafo.enlazarNodos(0, 12, 235);//Durango Torreon
        grafo.enlazarNodos(12, 10, 262);//Torreon Saltillo
        grafo.enlazarNodos(10, 11, 449);//Saltillo SanLuisPotosi
        grafo.enlazarNodos(3, 15, 66); //Fresnillo Zacatecas
        grafo.enlazarNodos(11, 9, 208);//SanLuisPotosi Queretaro
        grafo.enlazarNodos(9, 5, 176);//Queretaro Leon
        grafo.enlazarNodos(5, 4, 223);//Leon Guadalajara
        grafo.enlazarNodos(4, 13, 476);//Guadalajara Toluca
        grafo.enlazarNodos(6, 13, 66);//Mexico Toluca
        grafo.enlazarNodos(6, 8, 123);//Mexico Pachuca
        grafo.enlazarNodos(6, 14, 122);//Mexico Tulancingo
        grafo.enlazarNodos(6, 7, 95);//Mexico Puebla
        grafo.enlazarNodos(6, 2, 89);//Mexico Cuernavaca
        grafo.enlazarNodos(6, 9, 211);//Mexico Queretaro 

        System.out.println("Quieres crear una relacion extra?");
        if (sc.next().equals("si")) {
            relacionar(grafo);
        }
        System.out.println("Quieres ver los datos generales?");
        if (sc.next().equals("si")) {
            Datos(grafo, City);
        }
        System.out.println("Quieres Buscar una conexion: ");
        if (sc.next().equals("si")) {
            busqueda(grafo);
        }
        System.out.println("Quieres ver la matriz de relaciones?");
        if (sc.next().equals("si")) {
            relaciones(grafo);
        }
        System.out.println("Quieres ver la tabla de pesos?");
        if (sc.next().equals("si")) {
            pesos(grafo);
        }
    }

    public static void busqueda(Grafo grafo) {
        System.out.println("Ingrese el id inicial");
        int i = sc.nextInt();
        System.out.println("Ingrese el id a llegar");
        int f = sc.nextInt();
        buscarImpl(grafo, i, f);
    }

    public static void Datos(Grafo grafo, ArrayList<Ciudad> City) {
        System.out.println("Vertices totales: " + grafo.nVertices());
        System.out.println("Aristas totales: " + grafo.AristasTotales());
        System.out.println("---CIUDADES---");
        System.out.println("");
        for (int i = 0; i < City.size(); i++) {
            System.out.println("---" + grafo.Nodos.get(i).Ciudad.getNombre() + "---");
            System.out.println("Numero de Aristas: " + grafo.AristasNodo(grafo.Nodos.get(i).Ciudad.getId()));
            System.out.println("Grado Nodo: " + grafo.Nodos.get(i).grado());
            System.out.println("Conecta con: ");
            for (int j = 0; j < grafo.Nodos.get(i).carreteras.size(); j++) {
                System.out.println(grafo.Nodos.get(grafo.Nodos.get(i).carreteras.get(j).destino).Ciudad.toString());
            }
            System.out.println(" ");
        }

    }

    public static void buscarImpl(Grafo grafo, int i, int d) {
        int inicio = 4;
        int destino = 9;
        if (grafo.camino(inicio, destino)) {
            System.out.println("Lo encontre");
            System.out.println("El minimo a recorrer es: " + grafo.caminoMasCorto(inicio, destino));
        } else {
            System.out.println("NO LO CONOCO");
        }
        System.out.println("Quieres buscar otro?");
        if (sc.next().equals("si")) {
            busqueda(grafo);
        }
    }

    public static void relaciones(Grafo grafo) {
        boolean[][] matrix = grafo.grid();
        System.out.println("Tabla de Conexionts");
        System.out.println("");
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                if (matrix[i][j]) {
                    System.out.print(matrix[i][j] + "   ");
                } else {
                    System.out.print("----   ");
                }
            }
            System.out.println(" ");
        }
    }

    public static void pesos(Grafo grafo) {
        System.out.println("");
        System.out.println("Tabla de $$$");
        System.out.println("");
        double[][] floyd = grafo.floyd();
        for (int i = 0; i < floyd[0].length; i++) {
            for (int j = 0; j < floyd[1].length; j++) {
                if (floyd[i][j] < 1000000000) {
                    if (floyd[i][j] == 0) {
                        System.out.print(floyd[i][j] + "    ");
                    } else if (floyd[i][j] < 100) {
                        System.out.print(floyd[i][j] + "   ");
                    } else {
                        System.out.print(floyd[i][j] + "  ");
                    }
                } else {
                    System.out.print("-----  ");
                }
            }
            System.out.println(" ");
        }
    }

    private static void relacionar(Grafo grafo) {
        System.out.println("Ingrese punto id inicial");
        int i = sc.nextInt();
        System.out.println("Ingrese punto id final");
        int f = sc.nextInt();
        System.out.println("Ingrese el peso de la relacion");
        double peso = sc.nextDouble();
        relacionarImpl(grafo, i, f, peso);
    }

    private static void relacionarImpl(Grafo grafo, int i, int f, double peso) {
        grafo.enlazarNodos(i, f, peso);
        System.out.println("Quieres crear otra relacion?");
        if (sc.next().equals("si")) {
            relacionar(grafo);
        }
    }

}
