package carreteras;

import java.util.ArrayList;

public class Nodo {

    public ArrayList<Carretera> carreteras = new ArrayList();
    int entrada = 0;
    int salida = 0;
    int grado = 0;
    public Ciudad Ciudad = null;

    public Nodo(Ciudad Ciudad) {
        this.Ciudad = Ciudad;
    }

    public void crearConexion(int destino, double peso) {
        if (NuevaCalle(destino)) {
            Carretera Carretera = new Carretera(destino, peso);
            carreteras.add(Carretera);
        }
    }

    public boolean NuevaCalle(int destino) {
        boolean nuevo = true;
        for (int i = 0; i < carreteras.size(); i++) {
            if (carreteras.get(i).destino == destino) {
                nuevo = false;
            }
        }
        return nuevo;
    }
    
    public void salida(){
        salida++;
        grado += salida;
    }
    
    public int getEntrada(){
        return entrada;
    }
    
    public int getSalida(){
        return salida;
    }
    
    public void entrada(){
        entrada++;
        grado += entrada;
    }
    
    public int grado(){
        return grado;
    }
}
