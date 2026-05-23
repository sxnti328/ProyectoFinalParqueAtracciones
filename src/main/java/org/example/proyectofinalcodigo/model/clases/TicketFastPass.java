package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.clasesAbstractas.Ticket;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;

import java.util.ArrayList;
import java.util.List;

public class TicketFastPass extends Ticket {

    private List<String> atraccHabilitadas;
    private int          prioridadCola;

    public TicketFastPass(String id, double precio) {
        super(id, TipoTicket.FAST_PASS, precio);
        this.atraccHabilitadas = new ArrayList<>();
        this.prioridadCola     = 1;
    }

    @Override
    public double calcDescuento() {
        return 0.0;
    }

    public boolean tienePrioridad(String idAtraccion) {
        return atraccHabilitadas.isEmpty() || atraccHabilitadas.contains(idAtraccion);
    }

    public void agregarAtraccionHabilitada(String idAtraccion) {
        if (!atraccHabilitadas.contains(idAtraccion))
            atraccHabilitadas.add(idAtraccion);
    }

    public List<String> getAtraccHabilitadas()  {
        return atraccHabilitadas;
    }
    public void  setAtraccHabilitadas(List<String> lista)  { this.atraccHabilitadas = lista; }

    public int  getPrioridadCola() {
        return prioridadCola;
    }
    public void setPrioridadCola(int prioridad) {
        this.prioridadCola = prioridad;
    }

    @Override
    public String toString() {
        return super.toString() + " | Prioridad=" + prioridadCola;
    }
}
