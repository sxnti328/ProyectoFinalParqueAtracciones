package org.example.proyectofinalcodigo.model.clasesAbstractas;

public abstract class Persona {
    private String nombre;
    private String documento;
    private int edad;


    public Persona(String nombre, String documento, int edad) {
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
    }


    public String getNombre() {

        return nombre;
    }
    public void setNombre(String nombre) {

        this.nombre = nombre;
    }
    public String getDocumento() {

        return documento;
    }
    public void setDocumento(String documento) {

        this.documento = documento;
    }
    public int getEdad() {

        return edad;
    }
    public void setEdad(int edad) {

        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "  Documento: " + documento + "  Edad: " + edad;
    }
}
