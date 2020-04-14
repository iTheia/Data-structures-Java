package colas;

import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Ejercicio1 e = new Ejercicio1();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Ingrese un texto");
        String texto = sc.nextLine();
        
        System.out.println("Cuantas veces quiere el loop?");
        int n = sc.nextInt();
        
        e.entrada(texto, n);
    }
}
