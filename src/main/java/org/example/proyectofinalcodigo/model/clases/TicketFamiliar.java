package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.clasesAbstractas.Ticket;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;

public class TicketFamiliar extends Ticket {

    private int    numIntegrantes;
    private String condicionesDescuento;

    public TicketFamiliar(String id, double precio, int numIntegrantes, String condicionesDescuento) {
        super(id, TipoTicket.FAMILIAR, precio);
        this.numIntegrantes       = numIntegrantes;
        this.condicionesDescuento = condicionesDescuento;
    }

    @Override
    public double calcDescuento() {
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

    public int    getNumIntegrantes()  {
        return numIntegrantes;
    }
    public void   setNumIntegrantes(int n){
        this.numIntegrantes = n;
    }

    public String getCondicionesDescuento()  {
        return condicionesDescuento;
    }
    public void   setCondicionesDescuento(String c) {
        this.condicionesDescuento = c;
    }

    @Override
    public String toString() {
        double desc = calcDescuento();
        return super.toString()
                + " | Integrantes=" + numIntegrantes
                + " | Descuento=" + (int)(desc / precio * 100) + "% ($" + desc + ")";
    }
}
