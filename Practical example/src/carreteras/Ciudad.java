package carreteras;

public class Ciudad {
    int id;
    String nombre;
    
    public Ciudad(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
    void setId(int id){
        this.id = id;
    }
    
    int getId(){
        return this.id;
    }
    
    void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    String getNombre(){
        return this.nombre;
    }
    
    @Override
    public String toString(){
        return id + " " +nombre;
    }
}
