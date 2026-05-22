package org.example.proyectofinalcodigo.model.clasesAbstractas;

public abstract class Empleado extends Persona {

        private String idEmpleado;

        public Empleado(String nombre, String documento, int edad, String idEmpleado) {
            super(nombre, documento, edad);
            this.idEmpleado = idEmpleado;
        }

        public String getIdEmpleado() {
            return idEmpleado;
        }
        public void setIdEmpleado(String idEmpleado) {
            this.idEmpleado = idEmpleado;
        }

        @Override
        public String toString() {
            return super.toString() + "  ID Empleado: " + idEmpleado;
        }
    }

