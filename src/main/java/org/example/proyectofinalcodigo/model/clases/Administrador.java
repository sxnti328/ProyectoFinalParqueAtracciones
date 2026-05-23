package org.example.proyectofinalcodigo.model.clases;


import org.example.proyectofinalcodigo.model.clasesAbstractas.Empleado;

public class Administrador extends Empleado {

        private String area;
        private GestorReportes gestor;

    public Administrador(String nombre, String documento, int edad, String idEmpleado, String area, GestorReportes gestor) {
        super(nombre, documento, edad, idEmpleado);
        this.area = area;
        this.gestor = gestor;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public GestorReportes getGestor() {
        return gestor;
    }

    public void setGestor(GestorReportes gestor) {
        this.gestor = gestor;
    }

    public String consultarReporte() {
    if (gestor == null) return "Sin gestor de reportes asignado.";
    return gestor.generarReporteDiario();
}

    @Override
    public String toString() {
        return "Administrador{" +
                "area='" + area + '\'' +
                ", gestor=" + gestor +
                '}';
    }
}

