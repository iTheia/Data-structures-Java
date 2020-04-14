package grafo;

import java.util.ArrayList;

public class Usuarios{
    
    String nombre;
    int numeroControl;
    ArrayList <Integer> Amigos = new ArrayList();
    
    public Usuarios(int numeroControl, String nombre){
        this.numeroControl = numeroControl;
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void agregarAmigo(int IdAmigo){
        Amigos.add(IdAmigo);
    }
    
    public ArrayList<Integer> GetAmigos(){
        return this.Amigos;
    }
    
    public int getId() {
        return numeroControl;
    }

    public void setId(int numeroControl) {
        this.numeroControl = numeroControl;
    }
    
    @Override
    public String toString(){
        return  numeroControl + " " + nombre ;
    }
}