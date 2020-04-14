package Nombres;

import java.io.*;
import java.util.ArrayList;

public class Nombres {

    ArrayList<String> textos = new ArrayList();

    public String[] nombres(String direccion) {

        String texto = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfReader = "";
            while ((bfReader = bf.readLine()) != null) {
                temp += bfReader;
            }
            texto = temp;
        } catch (Exception e) {
            System.err.println("El archivo no existe");
        }

        int PrimerLetra = 0;
        for (int i = 0; i < texto.length(); i++) {
            String letra = texto.substring(i, i + 1);
            if (" ".equals(letra)) {
                textos.add(texto.substring(PrimerLetra, i));
                PrimerLetra = i + 1;
            }
        }

        String arreglo[] = new String[textos.size()];

        for (int i = 0; i < textos.size(); i++) {
            arreglo[i] = textos.get(i);

        }

        return arreglo;
    }

}
