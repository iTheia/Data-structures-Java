package grafo;

import Nombres.Nombres;
import java.util.ArrayList;
import java.util.Random;

public class Escuela {

    static ArrayList<Usuarios> alumnos = new ArrayList();

    public void insertarEspecifico(int numeroControl, String nombre) {
        Usuarios a = new Usuarios(numeroControl, nombre);
        alumnos.add(a);
    }

    int random(int maximo, int minimo) {
        Random r = new Random();
        return (r.nextInt(maximo - minimo) + minimo);
    }
    

    public void GenerarRandom(int cantidad) {
        Nombres n = new Nombres();
        
        String[] nombres = n.nombres("C:\\Users\\ririv\\Documents\\NetBeansProjects\\Grafo\\src\\Nombres\\nombres.txt");
        
        for (int i = 0; i < cantidad; i++) {
            
            String nombre = nombres[random(nombres.length, 0)];
            int last = random(2148, 700);
            int NumControl = 0;
            if (last >= 1000) {
                NumControl = Integer.parseInt("1" + random(8, 3) + "04" + last);
            }else{
                NumControl = Integer.parseInt("1" + random(8, 3) + "04" +("0" + last) );

            }
            
            Usuarios alumno = new Usuarios(NumControl, nombre);
            alumnos.add(alumno);
        }
    }

    
    public Usuarios [] obtener() {
        Usuarios [] temporal = new Usuarios[alumnos.size()];
        for (int i = 0; i < alumnos.size(); i++) {
            temporal[i] = alumnos.get(i);
        }
        return temporal;
    }
}
