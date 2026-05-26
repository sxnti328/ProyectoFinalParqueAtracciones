package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.TipoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketFamiliarTest {

    private Visitante visitante;

    @BeforeEach
    void setUp() {
        visitante = new Visitante("Laura Torres", "DOC100", 34, 1.65, 500000.0);
    }

    @Test
    void testTipoEsFamiliar() {
        TicketFamiliar ticket = new TicketFamiliar("TF01", 100000.0, 3, "10% descuento");
        assertEquals(TipoTicket.FAMILIAR, ticket.getTipo());
    }

    @Test
    void testEstaActivoPorDefecto() {
        TicketFamiliar ticket = new TicketFamiliar("TF02", 80000.0, 2, "5% descuento");
        assertTrue(ticket.isActivo());
    }

    @Test
    void testDescuentoConDosIntegrantesEsCincoPorciento() {
        TicketFamiliar ticket = new TicketFamiliar("TF03", 100000.0, 2, "5% descuento");
        assertEquals(5000.0, ticket.calcDescuento(), 0.01);
    }

    @Test
    void testDescuentoConTresIntegrantesEsDiezPorciento() {
        TicketFamiliar ticket = new TicketFamiliar("TF04", 100000.0, 3, "10% descuento");
        assertEquals(10000.0, ticket.calcDescuento(), 0.01);
    }

    @Test
    void testDescuentoConCuatroIntegrantesEsQuincePorciento() {
        TicketFamiliar ticket = new TicketFamiliar("TF05", 100000.0, 4, "15% descuento");
        assertEquals(15000.0, ticket.calcDescuento(), 0.01);
    }

    @Test
    void testPrecioFinalRestaDescuento() {
        TicketFamiliar ticket = new TicketFamiliar("TF06", 200000.0, 4, "15% descuento");
        assertEquals(170000.0, ticket.getPrecioFinal(), 0.01);
    }

    @Test
    void testCompraExitosaDescuentaSaldoVisitante() {
        TicketFamiliar ticket = new TicketFamiliar("TF07", 100000.0, 3, "10% descuento");
        boolean comprado = visitante.comprarTicket(ticket);
        assertTrue(comprado);
        assertEquals(410000.0, visitante.getSaldoVirtual(), 0.01);
    }

    @Test
    void testCompraFallidaSiSaldoInsuficiente() {
        Visitante pobre = new Visitante("Juan Perez", "DOC200", 28, 1.70, 5000.0);
        TicketFamiliar ticket = new TicketFamiliar("TF08", 80000.0, 2, "5% descuento");
        boolean comprado = pobre.comprarTicket(ticket);
        assertFalse(comprado);
        assertEquals(5000.0, pobre.getSaldoVirtual(), 0.01);
    }
}
