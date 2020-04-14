package colas;

public class ColaSimple {
    int frente;
    int atras;
    int limite;
    Object[] cola;
    
    ColaSimple(int n){
        limite = n;
        frente=0;
        atras=-1;
        cola = new Object[limite];
    }
    
    public boolean push(Object dato){
        if(llena()){return false;}
        this.atras++;
        this.cola[this.atras] = dato;
        return true;
    }
    
    public Object pop(){
        if (vacia()){return null;}
        Object temporal = this.cola[this.frente];
        this.frente++;
        
        return temporal;
    }
    
    public boolean llena() {
        return (this.atras == this.limite-1);
    }
    
    public boolean vacia(){
        return (this.frente>this.atras);
    }
}
