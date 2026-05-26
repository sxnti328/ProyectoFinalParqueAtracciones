package org.example.proyectofinalcodigo.controller;

import org.example.proyectofinalcodigo.model.clases.Atraccion;
import org.example.proyectofinalcodigo.model.clases.ParqueDeAtraccion;
import org.example.proyectofinalcodigo.model.clases.Visitante;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;
import org.example.proyectofinalcodigo.model.records.Notificacion;

import java.util.ArrayList;
import java.util.List;

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

    public void agregarFavorita(String docVisitante, String idAtraccion) {
        Visitante v = parque.buscarVisitante(docVisitante);
        if (v != null) v.agregarFavorita(idAtraccion);
    }

    public ArrayList<String> getFavoritas(String docVisitante) {
        Visitante v = parque.buscarVisitante(docVisitante);
        if (v == null) return new ArrayList<>();
        return v.getListaFavoritas();
    }

    public String getNotificacionesTexto(String docVisitante) {
        Visitante v = parque.buscarVisitante(docVisitante);
        if (v == null || v.getListNotificaciones().isEmpty()) return "Sin notificaciones.";
        StringBuilder sb = new StringBuilder();
        for (Notificacion n : v.getListNotificaciones())
            sb.append("[").append(n.tipo()).append("] ").append(n.mensaje()).append("\n");
        return sb.toString().trim();
    }

    public List<Atraccion> getTodasAtracciones() {
        return parque.getTodasLasAtracciones();
    }

    public String getAforoParque() {
        return parque.getVisitantesActuales() + " / " + parque.getCapacidadMax();
    }

    public boolean parqueLleno() {
        return parque.getVisitantesActuales() >= parque.getCapacidadMax();
    }
}
