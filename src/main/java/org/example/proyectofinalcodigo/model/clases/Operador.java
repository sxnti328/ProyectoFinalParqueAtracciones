package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.clasesAbstractas.Empleado;

public class Operador extends Empleado {

    private String turno;
    private String idZona;

    public Operador(String nombre, String documento, int edad,
                    String idEmpleado, String turno, String idZona) {
        super(nombre, documento, edad, idEmpleado);
        this.turno  = turno;
        this.idZona = idZona;
    }

    public boolean puedeGestionarAtraccion(Atraccion atraccion) {
        if (atraccion.getZona() == null) return false;
        return atraccion.getZona().getIdZona().equals(idZona);
    }

    public String getTurno()  {
        return turno; }
    public void   setTurno(String turno)  { this.turno = turno; }

    public String getIdZona()  {
        return idZona;
    }
    public void   setIdZona(String idZona){
        this.idZona = idZona;
    }

    @Override
    public String toString() {
        return super.toString() + " Turno: " + turno + "  Zona: " + idZona;
    }
}
