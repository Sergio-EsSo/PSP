package tcp0;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private int edad;

    public Persona(){}

    public Persona(String nom, int ed){
        this.nombre = nom;
        this.edad = ed;
    }
    public String getNombre(){ return this.nombre; }
    public void setNombre(String n){ this.nombre = n; }
    public int getEdad(){ return this.edad; }
    public void setEdad(int e){ this.edad = e; }

    @Override 
    public String toString(){
        return "Nombre: "+this.nombre+"\nEdad: "+edad;
    }
}