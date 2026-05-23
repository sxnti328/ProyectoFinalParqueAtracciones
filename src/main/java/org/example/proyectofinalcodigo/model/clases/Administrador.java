package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.clasesAbstractas.Empleado;

public class Administrador extends Empleado {

    private String         area;
    private GestorReportes gestor;

    public Administrador(String nombre, String documento, int edad, String idEmpleado) {
        super(nombre, documento, edad, idEmpleado);
        this.area   = "General";
        this.gestor = null;
    }

    public Administrador(String nombre, String documento, int edad, String idEmpleado, String area) {
        super(nombre, documento, edad, idEmpleado);
        this.area   = area;
        this.gestor = null;
    }

    public Administrador(String nombre, String documento, int edad,
                         String idEmpleado, String area, GestorReportes gestor) {
        super(nombre, documento, edad, idEmpleado);
        this.area   = area;
        this.gestor = gestor;
    }

    public String consultarReporte() {
        if (gestor == null) return "Sin gestor de reportes asignado.";
        return gestor.generarReporteDiario();
    }

    public String getArea()                    { return area; }
    public void   setArea(String area)         { this.area = area; }

    public GestorReportes getGestor()          { return gestor; }
    public void           setGestor(GestorReportes g) { this.gestor = g; }

    @Override
    public String toString() {
        return super.toString() + "  Area: " + area;
    }
}
