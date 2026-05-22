package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.clasesAbstractas.Ticket;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;

import java.time.LocalDate;
import java.util.List;

public class TicketFastPass extends Ticket {
    private List<String> atraccHabilitadas;
    private int prioridadCola;

    public TicketFastPass(String id, TipoTicket tipo, double precio, boolean activo, LocalDate fechaCompra, List<String> atraccHabilitadas, int prioridadCola) {
        super(id, tipo, precio, activo, fechaCompra);
        this.atraccHabilitadas = atraccHabilitadas;
        this.prioridadCola = prioridadCola;
    }

    @Override
    public double calcDescuento() {
        return 0.0;
    }
    public boolean tienePrioridad(String idAtraccion) {
        return atraccHabilitadas.isEmpty() || atraccHabilitadas.contains(idAtraccion);
    }

    public void agregarAtraccionHabilitada(String idAtraccion) {
        if (!atraccHabilitadas.contains(idAtraccion)) atraccHabilitadas.add(idAtraccion);
    }

    public List<String> getAtraccHabilitadas() {
        return atraccHabilitadas;
    }

    public void setAtraccHabilitadas(List<String> atraccHabilitadas) {
        this.atraccHabilitadas = atraccHabilitadas;
    }

    public int getPrioridadCola() {
        return prioridadCola;
    }

    public void setPrioridadCola(int prioridadCola) {
        this.prioridadCola = prioridadCola;
    }

    @Override
    public String toString() {
        return "TicketFastPass{" +
                "prioridadCola=" + prioridadCola +
                '}';
    }
}
