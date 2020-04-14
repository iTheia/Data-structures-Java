package grafo;

import java.util.ArrayList;

public class Grafo {

    public ArrayList<Nodo> Nodos = new ArrayList();
    public boolean simple = true;
    
    public Grafo(boolean simple){
        this.simple = simple;
    }
    public class Nodo {

        public ArrayList<Integer> conexiones = new ArrayList();

        public Usuarios Usuario = null;

        public Nodo(Usuarios Usuario) {
            this.Usuario = Usuario;
        }

        public void crearConexion(int id) {
            if (nuevoAmigo(id)) {
                conexiones.add(id);
            }
        }

        public boolean nuevoAmigo(int id) {
            boolean nuevo = true;
            for (int i = 0; i < conexiones.size(); i++) {
                if (conexiones.get(i) == id) {
                    nuevo = false;
                }
            }
            return nuevo;
        }
    }

    private boolean existe(int IdNodo) {
        for (int i = 0; i < Nodos.size(); i++) {
            if (Nodos.get(i).Usuario.getId() == IdNodo) {
                return true;
            }
        }
        return false;
    }
    

    public void obtenerAmigos(int id) {
        Nodo temp = Nodos.get(getNodoPosition(id));
        for (int i = 0; i < temp.conexiones.size(); i++) {
            Nodo Amigo = Nodos.get(getNodoPosition(temp.conexiones.get(i)));
            System.out.println(Amigo.Usuario.toString());
        }
    }

    public void enlazarNodos(int IdNodo1, int IdNodo2) {
        if (existe(IdNodo1) && existe(IdNodo2)) {
            Nodos.get(getNodoPosition(IdNodo1)).crearConexion(IdNodo2);
            Nodos.get(getNodoPosition(IdNodo2)).crearConexion(IdNodo1);
        }
    }

    public void Nodo(Usuarios Usuario) {
        Nodo Nodo = new Nodo(Usuario);
        this.Nodos.add(Nodo);
    }

    public Usuarios obtenerUsuarios(int id) {
        for (int i = 0; i < Nodos.size(); i++) {
            if (Nodos.get(i).Usuario.getId() == id) {
                return Nodos.get(i).Usuario;
            }
        }
        return null;
    }

    private int getNodoPosition(int id) {
        for (int i = 0; i < Nodos.size(); i++) {
            if (Nodos.get(i).Usuario.getId() == id) {
                return i;
            }
        }
        return 0;
    }

    public ArrayList<Integer> conexiones(int id) {
        return Nodos.get(getNodoPosition(id)).conexiones;
    }

    public boolean[][] grid() {
        boolean[][] grid = new boolean[Nodos.size()][Nodos.size()];
        for (int i = 0; i < Nodos.size(); i++) {
            ArrayList<Integer> conexionesNodo = Nodos.get(i).conexiones;
            if (!conexionesNodo.isEmpty()) {
                for (int j = 0; j < conexionesNodo.size(); j++) {
                    grid[i][getNodoPosition(conexionesNodo.get(j))] = true;
                }    
            }
        }
        return grid;
    }
}
