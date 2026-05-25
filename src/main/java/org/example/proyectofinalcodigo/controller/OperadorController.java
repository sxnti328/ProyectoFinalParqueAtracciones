package org.example.proyectofinalcodigo.controller;

import org.example.proyectofinalcodigo.model.clases.Atraccion;
import org.example.proyectofinalcodigo.model.clases.Operador;
import org.example.proyectofinalcodigo.model.clases.ParqueDeAtraccion;
import org.example.proyectofinalcodigo.model.clases.Zona;
import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.enums.MotivoCierre;

import java.util.ArrayList;
import java.util.List;

public class OperadorController {

    private ParqueDeAtraccion parque;

    public OperadorController(ParqueDeAtraccion parque) {
        this.parque = parque;
    }

    public ArrayList<Operador> getOperadores() {
        return parque.getListOperador();
    }

    public List<Atraccion> getAtraccionesDeZona(String idZona) {
        Zona z = parque.buscarZona(idZona);
        if (z == null) return new ArrayList<>();
        return z.getListAtraccion();
    }

    public String getNombreZona(String idZona) {
        Zona z = parque.buscarZona(idZona);
        return z != null ? z.getNombre() : idZona;
    }

    public String validarAcceso(String docVisitante, Atraccion atraccion) {
        if (atraccion == null) return "Selecciona una atraccion.";
        var visitante = parque.buscarVisitante(docVisitante);
        if (visitante == null) return "Visitante no encontrado.";
        if (visitante.getTicketActivo() == null) return "El visitante no tiene ticket activo.";
        boolean ok = atraccion.verificarAcceso(visitante);
        return ok ? "Acceso permitido: " + visitante.getNombre() : "Acceso denegado por altura o edad.";
    }

    public String cambiarEstado(Atraccion a, EstadoActual estado, MotivoCierre motivo) {
        if (a == null) return "Selecciona una atraccion.";
        a.setEstado(estado);
        a.setMotivoCierre(estado != EstadoActual.ACTIVA ? motivo : null);
        return a.getNombre() + " -> " + estado;
    }

    public String registrarRevision(Operador op, Atraccion a, String descripcion) {
        if (op == null || a == null) return "Faltan datos.";
        a.registrarRevisionTecnica(op, descripcion);
        return "Revision registrada. " + a.getNombre() + " esta nuevamente activa.";
    }

    public String recargarSaldo(String docVisitante, double monto) {
        return parque.recargarSaldoVisitante(docVisitante, monto);
    }
}
