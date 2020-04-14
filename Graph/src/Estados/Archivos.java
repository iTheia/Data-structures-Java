package Estados;

import java.util.*;
import grafo.*;
import java.io.*;

public class Archivos {

    private Formatter x;
    private Scanner scData;
    private Scanner scA;

    ArrayList<Usuarios> Usuarios = new ArrayList();

    private String direccion = "C:\\Users\\ririv\\Documents\\NetBeansProjects\\Grafo\\src\\Estados\\";

    public void abrir(String direccion) {

        direccion = this.direccion + direccion + ".txt";

        try {
            x = new Formatter(direccion);
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    public void cargarAmigos(String direccion) {
        direccion = this.direccion + direccion + ".txt";
        try {
            scA = new Scanner(new File(direccion));
        } catch (Exception e) {
        }
    }

    public void AmigosId() {
        while (scA.hasNext()) {
            if (scA.next() != null) {
                for (int i = 0; i < Usuarios.size(); i++) {
                    Usuarios.get(i).agregarAmigo(Integer.parseInt(scA.next()));
                }
            }
        }
    }

    public void cargarUsuarios(String direccion) {

        direccion = this.direccion + direccion + ".txt";
        try {
            scData = new Scanner(new File(direccion));
        } catch (Exception e) {

        }
    }

    public ArrayList<Usuarios> ReturnUsuarios() {
        while (scData.hasNext()) {
            int id = Integer.parseInt(scData.next());
            String nombre = scData.next();
            Usuarios a = new Usuarios(id, nombre);
            Usuarios.add(a);
        }
        return Usuarios;
    }

    public void add(Usuarios alumno) {
        x.format("%s%s\n", alumno.getId() + " ", alumno.getNombre() + " ");
    }

    public void cerrarsc() {
        scData.close();
    }

    public void cerrar() {
        x.close();
    }

    public void crearFile(String direccion) {

        direccion = this.direccion + direccion + ".txt";

        File x = new File(direccion);
        try {
            x.createNewFile();
        } catch (IOException a) {
            System.out.println("you got an error");
        }
    }
}
