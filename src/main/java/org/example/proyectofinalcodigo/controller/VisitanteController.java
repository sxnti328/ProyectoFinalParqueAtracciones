package org.example.proyectofinalcodigo.controller;

import org.example.proyectofinalcodigo.model.clases.ParqueDeAtraccion;
import org.example.proyectofinalcodigo.model.clases.Visitante;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;

import java.util.ArrayList;

public class VisitanteController {

    private ParqueDeAtraccion parque;

    public VisitanteController(ParqueDeAtraccion parque) {
        this.parque = parque;
    }

    public boolean agregarVisitante(String nombre, String documento, int edad,
                                    double estatura, String telefono, String direccion) {
        Visitante v = new Visitante(nombre, documento, edad, estatura, 0, telefono, direccion);
        return parque.agregarVisitante(v);
    }

    public boolean actualizarVisitante(String documento, String nombre, int edad,
                                       double estatura, String telefono, String direccion) {
        return parque.actualizarVisitante(documento, nombre, edad, estatura, telefono, direccion);
    }

    public boolean eliminarVisitante(String documento) {
        return parque.eliminarVisitante(documento);
    }

    public String comprarTicket(String documento, TipoTicket tipo, double precio, int numIntegrantes) {
        return parque.venderTicket(documento, tipo, precio, numIntegrantes);
    }

    public ArrayList<Visitante> getVisitantes() {
        return parque.getListVisitante();
    }
}
