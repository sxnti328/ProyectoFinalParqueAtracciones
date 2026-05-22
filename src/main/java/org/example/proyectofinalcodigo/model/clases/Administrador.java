package org.example.proyectofinalcodigo.model.clases;


import org.example.proyectofinalcodigo.model.clasesAbstractas.Empleado;

public class Administrador extends Empleado {

        private String area;
        private GestorReportes gestor;

        public void setGestor(GestorReportes gestor) {
            this.gestor = gestor;
        }
        public GestorReportes getGestor()         {
            return gestor;
        }

        public String consultarReporte() {
            if (gestor == null) return "Sin gestor de reportes asignado.";
            return gestor.generarReporteDiario();
        }


        public Administrador(String nombre, String documento, int edad, String idEmpleado) {
            super(nombre, documento, edad, idEmpleado);
            this.area = "General";
        }

        public Administrador(String nombre, String documento, int edad,
                             String idEmpleado, String area) {
            super(nombre, documento, edad, idEmpleado);
            this.area = area;
        }

        public String getArea()            {
            return area; }
        public void   setArea(String area) {
            this.area = area; }

        @Override
        public String toString() {

            return super.toString() + "  Area: " + area;
        }
    }

