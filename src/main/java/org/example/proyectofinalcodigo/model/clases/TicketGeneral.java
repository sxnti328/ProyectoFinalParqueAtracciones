package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.clasesAbstractas.Ticket;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;

public class TicketGeneral extends Ticket {

    public TicketGeneral(String id, double precio) {
        super(id, TipoTicket.GENERAL, precio);
    }

    @Override
    public double calcDescuento() {
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
