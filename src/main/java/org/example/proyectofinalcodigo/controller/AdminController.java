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