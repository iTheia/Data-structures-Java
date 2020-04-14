package carreteras;

import java.util.ArrayList;

public class Grafo {

    public  ArrayList<Nodo> Nodos = new ArrayList();

    boolean simple = true;

    public Grafo(boolean simple) {
        this.simple = simple;
    }

    public int nVertices() {
        return Nodos.size();
    }

    public int AristasNodo(int id) {
        return Nodos.get(getNodoPosition(id)).carreteras.size();
    }

    public int AristasTotales() {
        int contador = 0;
        for (int i = 0; i < Nodos.size(); i++) {
            contador += Nodos.get(i).carreteras.size();
        }
        return contador;
    }

    public void AgregarCiudad(Ciudad Ciudad) {
        Nodo Nodo = new Nodo(Ciudad);
        this.Nodos.add(Nodo);
    }

    private boolean existe(int IdNodo) {
        for (int i = 0; i < Nodos.size(); i++) {
            if (Nodos.get(i).Ciudad.getId() == IdNodo) {
                return true;
            }
        }
        return false;
    }

    public void nodosAdyacentes(int id) {
        Nodo temp = Nodos.get(getNodoPosition(id));
        for (int i = 0; i < temp.carreteras.size(); i++) {
            Nodo Adyacente = Nodos.get(getNodoPosition(temp.carreteras.get(i).destino));
            System.out.println(Adyacente.Ciudad.toString());
        }
    }

    public void conectar(Nodo Nodo, int destino, double peso) {
        Nodo.crearConexion(destino, peso);
        if (destino == Nodo.Ciudad.getId()) {
            Nodo.salida();
        }
        Nodo.salida();
    }

    private boolean Vecino(ArrayList<Integer> opciones, int destino) {
        for (int i = 0; i < opciones.size(); i++) {
            if (opciones.get(i) == destino) {
                return true;
            }
        }
        return false;
    }

    private boolean caminoImpl(int inicio, int destino, boolean[] revisados) {
        ArrayList<Carretera> Carretera = Nodos.get(getNodoPosition(inicio)).carreteras;
        ArrayList<Integer> opciones = new ArrayList();
        for (int i = 0; i < Carretera.size(); i++) {
            if (!revisados[getNodoPosition(Carretera.get(i).destino)]) {
                opciones.add(Carretera.get(i).destino);
            }
        }
        if (Vecino(opciones, destino)) {
            return true;
        } else {
            revisados[getNodoPosition(inicio)] = true;
            for (int i = 0; i < opciones.size(); i++) {
                for (int j = 0; j < revisados.length; j++) {
                    if (!revisados[j]) {
                        if (caminoImpl(opciones.get(i), destino, revisados)) {
                            revisados[getNodoPosition(opciones.get(i))] = true;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean camino(int inicio, int destino) {
        boolean[] revisados = new boolean[Nodos.size()];
        if (caminoImpl(inicio, destino, revisados)) {
            for (int i = 0; i < revisados.length; i++) {
                if (revisados[i]) {
                    System.out.println(revisados[i] + " " + Nodos.get(i).Ciudad.getNombre());
                }
            }
            return true;
        }
        return false;
    }

    public void enlazarNodos(int IdNodo1, int IdNodo2, double peso) {
        if (existe(IdNodo1) && existe(IdNodo2)) {
            if (simple) {
                conectar(Nodos.get(getNodoPosition(IdNodo1)), IdNodo2, peso);
                conectar(Nodos.get(getNodoPosition(IdNodo2)), IdNodo1, peso);
            } else {
                conectar(Nodos.get(getNodoPosition(IdNodo1)), IdNodo2, peso);
                Nodos.get(getNodoPosition(IdNodo2)).entrada();
            }
        }
    }

    public Ciudad obtenerUsuarios(int id) {
        for (int i = 0; i < Nodos.size(); i++) {
            if (Nodos.get(i).Ciudad.getId() == id) {
                return Nodos.get(i).Ciudad;
            }
        }
        return null;
    }

    private int getNodoPosition(int id) {
        for (int i = 0; i < Nodos.size(); i++) {
            if (Nodos.get(i).Ciudad.getId() == id) {
                return i;
            }
        }
        return 0;
    }

    public ArrayList<Carretera> conexiones(int id) {
        return Nodos.get(getNodoPosition(id)).carreteras;
    }

    ArrayList<Integer> destinos(Nodo Nodo) {
        ArrayList<Integer> temporal = new ArrayList();
        for (int i = 0; i < Nodo.carreteras.size(); i++) {
            temporal.add(Nodo.carreteras.get(i).destino);
        }
        return temporal;
    }

    public boolean[][] grid() {
        boolean[][] grid = new boolean[Nodos.size()][Nodos.size()];
        for (int i = 0; i < Nodos.size(); i++) {
            ArrayList<Integer> conexionesNodo = destinos(Nodos.get(i));
            if (!conexionesNodo.isEmpty()) {
                for (int j = 0; j < conexionesNodo.size(); j++) {
                    grid[i][getNodoPosition(conexionesNodo.get(j))] = true;
                }
            }
        }
        return grid;
    }

    public double peso(int i, int j) {
        ArrayList<Carretera> temporal = Nodos.get(i).carreteras;
        for (int k = 0; k < temporal.size(); k++) {
            if (temporal.get(k).destino == j) {
                return temporal.get(k).peso;
            }
        }
        return 0;
    }
    
    public double caminoMasCorto(int i, int j){
        double [][] grid = caminoCorto();
        return grid[i][j];
    }
    public double[][] caminoCorto() {
        double[][] floyd = floyd();
        for (int k = 0; k < Nodos.size(); k++) {
            for (int i = 0; i < Nodos.size(); i++) {
                for (int j = 0; j < Nodos.size(); j++) {
                    if (floyd[i][k] + floyd[k][j] < floyd[i][j]) {
                        floyd[i][j] = floyd[i][k] + floyd[k][j];
                    }
                }
            }
        }
        return floyd;
    }

    public double[][] floyd() {
        boolean[][] grid = grid();
        double[][] newGrid = new double[grid[0].length][grid[1].length];
        for (int i = 0; i < newGrid[0].length; i++) {
            for (int j = 0; j < newGrid[1].length; j++) {
                if (grid[i][j] && i == j) {
                    newGrid[i][j] = 0;
                } else if (grid[i][j]) {
                    newGrid[i][j] = peso(i, j);
                } else if (!grid[i][j]) {
                    newGrid[i][j] = 1000000000;
                }
            }
        }
        return newGrid;
    }


}
