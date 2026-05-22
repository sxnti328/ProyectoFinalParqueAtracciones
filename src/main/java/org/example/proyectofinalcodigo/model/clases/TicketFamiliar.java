package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.clasesAbstractas.Ticket;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;

import java.time.LocalDate;

public class TicketFamiliar extends Ticket {
    private int    numIntegrantes;


    public TicketFamiliar(String id, TipoTicket tipo, double precio, boolean activo, LocalDate fechaCompra, int numIntegrantes) {
        super(id, tipo, precio, activo, fechaCompra);
        this.numIntegrantes = numIntegrantes;

    }

    @Override
    public double calcDescuento() {
        // El descuento depende del numero de integrantes
        // 2 integrantes: 5%, 3: 10%, 4+: 15%
        double porcentaje;
        if (numIntegrantes <= 2) {
            porcentaje = 0.05;
        } else if (numIntegrantes == 3) {
            porcentaje = 0.10;
        } else {
            porcentaje = 0.15;
        }
        return precio * porcentaje;
    }

    public int getNumIntegrantes() {
        return numIntegrantes;
    }

    public void setNumIntegrantes(int numIntegrantes) {
        this.numIntegrantes = numIntegrantes;
    }





    @Override
    public String toString() {
        return "TicketFamiliar{" +
                "numIntegrantes=" + numIntegrantes ;
    }
}
