package colas;

public class Colas {

    private int nDatos;
    private int limite;
    private Object[] cola;

    Colas(int longitud) {
        this.nDatos = 0;
        this.limite = longitud;
        this.cola = new Object[this.limite];
    }

    public boolean vacia() {
        return (this.nDatos == 0);
    }

    public boolean llena() {
        return (this.nDatos == this.limite);
    }

    public boolean push(Object c) {
        if (llena()) {
            return false;
        }
        this.cola[this.nDatos] = c;
        this.nDatos++;
        return true;
    }

    public Object pop() {
        if (vacia()) {
            return null;
        }
        for (int i = 1; i < this.nDatos-1; i++) {
            this.cola[i-1] = this.cola[i];
        }
        this.nDatos--;
        return this.cola[this.nDatos];
    }
}
