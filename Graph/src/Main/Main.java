package Main;

import grafo.Escuela;
import grafo.Grafo;
import grafo.Usuarios;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Escuela Generador = new Escuela();

        Scanner sc = new Scanner(System.in);

        System.out.println("Quiere Verl el archivo de prueba?");

        if (sc.next().equals("si")) {
            Generador.GenerarRandom(5);
            Grafo Grafo = new Grafo(true);
            Usuarios[] Usuarios = Generador.obtener();
            test(Grafo, Usuarios);
        } else {
            boolean simple = true;
            System.out.println("Quieres cargar un grafo?");
            if (sc.next().equals("si")) {

            } else {
                System.out.println("Quieres que el grafo sea dirigido?");
                System.out.println("(ALERTA AUN NO SIRVE XD)");
                if (sc.next().equals("LindaEsLinda")) {
                    simple = false;
                }
            }
            Grafo Grafo = new Grafo(simple);
            System.out.println("Quieres ingresar un Alumno?");

            if (sc.next().equals("si")) {
                ingresar(sc, Generador);
            }

            System.out.println("Quieres Generar Alumnos aleatorios?");
            if (sc.next().equals("si")) {
                ingresarRandom(sc, Generador);
            }

            Usuarios[] Usuarios = Generador.obtener();

            for (Usuarios Usuario : Usuarios) {
                Grafo.Nodo(Usuario);
            }
            
            
            System.out.println("Quieres Crear Relaciones?");
            if (sc.next().equals("si")){
                relaciones(Grafo, sc);
            }
            
            datosGenerales(Grafo, Usuarios);
            System.out.println("Quieres vizualizar la matriz?");
            if (sc.next().equals("si")) {
                vizualizarMatrizImpl(Grafo, Usuarios);
            }
        }

    }

    public static void enlazadorRandom(int Usuarios, Grafo grafo) {
        for (int i = 0; i < Usuarios; i++) {
            int n1 = random(Usuarios, 0);
            int n2 = random(Usuarios, 0);
            grafo.enlazarNodos(n1, n2);
        }
    }

    public static int random(int maximo, int minimo) {
        Random r = new Random();
        return (r.nextInt(maximo - minimo) + minimo);
    }
    
    static void datosGenerales(Grafo Grafo, Usuarios [] Usuarios){
        ArrayList <Integer> aristas = new ArrayList();
        System.out.println("");
        System.out.println("Numero de Vertices");
        System.out.println(Grafo.Nodos.size());
        System.out.println("");
        System.out.println("----Usuarios----");
        
        for (Usuarios Usuario : Usuarios) {
            System.out.println(Usuario.toString());
        }
        System.out.println("----------------");
        for (int i  = 0; i < Usuarios.length; i++) {
            System.out.println("");
            System.out.println("Amigos de: " + Usuarios[i].toString());
            Grafo.obtenerAmigos(Usuarios[i].getId());
            System.out.println("Aristas Que lo conectan");
            System.out.println(Grafo.Nodos.get(i).conexiones.size());
        }
    }
    private static void ingresarRandom(Scanner sc, Escuela GenerarNodos) {
        System.out.println("Cuantos Alumnos Aleatorios quiere Generar?");
        GenerarNodos.GenerarRandom(sc.nextInt());
        System.out.println("Quieres Generar Mas?");
        if (sc.next().equals("si")) {
            ingresarRandom(sc, GenerarNodos);
        }

    }
    
    static void relaciones(Grafo Grafo, Scanner sc){
        System.out.println("Ingrese Un ID");
        int id1 = sc.nextInt();
        System.out.println("Ingrese el ID a relacionar");
        int id2 = sc.nextInt();
        Grafo.enlazarNodos(id1, id2);
        System.out.println("Quieres Crear Otra relacion?");
        if (sc.next().equals("si")) {
            relaciones(Grafo ,sc);
        }
    }
    
    public static void ingresar(Scanner sc, Escuela GenerarNodos) {
        System.out.println("Ingrese los siguientes datos");
        System.out.println("Nombre");
        String nombre = sc.next();
        System.out.println("Numero de control");
        int control = sc.nextInt();
        GenerarNodos.insertarEspecifico(control, nombre);
        System.out.println("Quieres ingresar otro alumno?");
        if (sc.next().equals("si")) {
            ingresar(sc, GenerarNodos);
        }
    }

    private static void test(Grafo Grafo, Usuarios[] Usuarios) {
        for (Usuarios Usuario : Usuarios) {
            Grafo.Nodo(Usuario);
        }

        for (Usuarios Usuario : Usuarios) {
            Grafo.enlazarNodos(Usuarios[0].getId(), Usuario.getId());
        }
        Grafo.enlazarNodos(Usuarios[2].getId(), Usuarios[3].getId());

        datosGenerales(Grafo, Usuarios);
        vizualizarMatrizImpl(Grafo, Usuarios);
    }
    
    static void vizualizarMatrizImpl(Grafo Grafo, Usuarios[] Usuarios){
        boolean[][] matrix = Grafo.grid();

        System.out.println("");
        System.out.println("Tabla de amigos");
        System.out.println("");
        for (int i = 0; i < matrix[0].length; i++) {
            System.out.print(Usuarios[i].getId());
            for (int j = 0; j < matrix[1].length; j++) {
                if (matrix[i][j]) {
                    System.out.print(" " + matrix[i][j] + "   ");
                } else {
                    System.out.print(matrix[i][j] + "   ");
                }

            }
            System.out.println(" ");
        }
    
    }

}
