package org.example.proyectofinalcodigo.controller;

import org.example.proyectofinalcodigo.model.clases.*;
import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;

import java.util.ArrayList;
import java.util.List;


public class AdminController {

    private ParqueDeAtraccion parque;

    public AdminController(ParqueDeAtraccion parque) {
        this.parque = parque;
    }

    // Operadores
    public boolean agregarOperador(String nombre, String documento, int edad,
                                   String idEmp, String turno, Zona zona) {
        String idZona = (zona != null) ? zona.getIdZona() : "";
        Operador op = new Operador(nombre, documento, edad, idEmp, turno, idZona);
        boolean ok = parque.agregarOperador(op);
        if (ok && zona != null) zona.agregarOperador(op);
        return ok;
    }

    public boolean eliminarOperador(String documento) {
        return parque.eliminarOperador(documento);
    }

    public boolean actualizarOperador(String documento, String nombre, int edad, String turno) {
        Operador o = parque.buscarOperador(documento);
        if (o == null) return false;
        o.setNombre(nombre);
        o.setEdad(edad);
        o.setTurno(turno);
        return true;
    }

    public boolean asignarOperadorAZona(String idEmpleado, String idZona) {
        return parque.asignarOperadorAZona(idEmpleado, idZona);
    }

    public ArrayList<Operador> obtenerListaOperadores() {
        return parque.getListOperador();
    }

    // Zonas
    public boolean agregarZona(String id, String nombre, String descripcion, int capacidad) {
        Zona z = new Zona(id, nombre, descripcion, capacidad);
        return parque.agregarZona(z);
    }

    public boolean eliminarZona(String idZona) {
        return parque.eliminarZona(idZona);
    }

    public boolean actualizarZona(String idZona, String nombre, String descripcion, int capacidad) {
        Zona z = parque.buscarZona(idZona);
        if (z == null) return false;
        z.setNombre(nombre);
        z.setDescripcion(descripcion);
        z.setCapacidadMax(capacidad);
        return true;
    }

    public ArrayList<Zona> obtenerListaZonas() {
        return parque.getListZona();
    }

    // Atracciones
    public boolean agregarAtraccion(Zona zona, TipoAtraccion tipo, String id, String nombre,
                                    int capacidad, double altura, int edad, double costo) {
        if (zona == null) return false;
        Atraccion a = new Atraccion(id, nombre, tipo, capacidad, altura, edad, costo);
        return parque.agregarAtraccionAZona(zona.getIdZona(), a);
    }

    public boolean eliminarAtraccion(Atraccion a) {
        if (a == null || a.getZona() == null) return false;
        return parque.eliminarAtraccionDeZona(a.getZona().getIdZona(), a.getId());
    }

    public boolean actualizarAtraccion(String idAtraccion, String nombre, int capacidad,
                                       double altura, int edad, double costo) {
        Atraccion a = parque.buscarAtraccion(idAtraccion);
        if (a == null) return false;
        a.setNombre(nombre);
        a.setCapacidadMaxima(capacidad);
        a.setAlturaMinima(altura);
        a.setEdadMinima(edad);
        a.setCostoAdicional(costo);
        return true;
    }

    public List<Atraccion> obtenerListaAtracciones() {
        return parque.getTodasLasAtracciones();
    }

    // Alertas y reportes
    public void activarAlertaClimatica() {
        parque.activarAlertaClimatica();
    }

    public void desactivarAlertaClimatica() {
        parque.desactivarAlertaClimatica();
    }

    public String generarReporte() {
        return parque.generarReporteDiario();
    }
}