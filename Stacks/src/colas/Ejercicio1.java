package colas;

public class Ejercicio1 {

    public static void entrada(String texto, int n) {
        ColaSimple cola = new ColaSimple(1000);
        ColaSimple cola2 = new ColaSimple(1000);

        for (int i = 0; i < texto.length(); i++) {
            cola.push(texto.charAt(i));
        }
        
        for (int i = 0; i < n; i++) {
            if (!cola.vacia()) {
                System.out.println("Esta es la primer cola");
                loop(cola, cola2);
            }else if (!cola2.vacia()) {
                System.out.println("Esta es la segunda cola");
                loop(cola2, cola);
            }
        }

    }

    public static void loop(ColaSimple cola, ColaSimple cola2) {
        while (!cola.vacia()) {
            cola2.push(transferir(cola));
        }
        System.out.println("");
    }

    public static char transferir(ColaSimple cola) {
        char dato = (char) cola.pop();
        System.out.print(dato);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return dato;
    }
}
