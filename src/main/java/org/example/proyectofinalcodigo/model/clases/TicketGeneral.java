package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.clasesAbstractas.Ticket;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;

import java.time.LocalDate;

public class TicketGeneral  extends Ticket {

    public TicketGeneral(String id, TipoTicket tipo, double precio, boolean activo, LocalDate fechaCompra) {
        super(id, tipo, precio, activo, fechaCompra);
    }
    @Override
    public double calcDescuento(){
        return 0.0;
    }

}
