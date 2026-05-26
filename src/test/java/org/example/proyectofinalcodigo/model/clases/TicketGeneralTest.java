package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.TipoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketGeneralTest {

    private TicketGeneral ticket;
    private Visitante visitante;

    @BeforeEach
    void setUp() {
        ticket   = new TicketGeneral("TG01", 45000.0);
        visitante = new Visitante("Pedro Salas", "DOC300", 30, 1.72, 100000.0);
    }

    @Test
    void testTipoEsGeneral() {
        assertEquals(TipoTicket.GENERAL, ticket.getTipo());
    }

    @Test
    void testActivoPorDefecto() {
        assertTrue(ticket.isActivo());
    }

    @Test
    void testCalculoDescuentoEsCero() {
        assertEquals(0.0, ticket.calcDescuento(), 0.01);
    }

    @Test
    void testPrecioFinalIgualAlPrecioBase() {
        assertEquals(45000.0, ticket.getPrecioFinal(), 0.01);
    }

    @Test
    void testCompraExitosaDescontaSaldoVisitante() {
        visitante.comprarTicket(ticket);
        assertEquals(55000.0, visitante.getSaldoVirtual(), 0.01);
    }

    @Test
    void testCompraFallidaSiSaldoInsuficiente() {
        Visitante pobre = new Visitante("Sin Plata", "DOC301", 25, 1.60, 1000.0);
        boolean comprado = pobre.comprarTicket(ticket);
        assertFalse(comprado);
        assertEquals(1000.0, pobre.getSaldoVirtual(), 0.01);
    }

    @Test
    void testTicketQuedaEnListaDelVisitanteTrasCompra() {
        visitante.comprarTicket(ticket);
        assertEquals(1, visitante.getListTickets().size());
        assertSame(ticket, visitante.getListTickets().get(0));
    }

    @Test
    void testDesactivarTicket() {
        ticket.setActivo(false);
        assertFalse(ticket.isActivo());
        assertNull(visitante.getTicketActivo());
    }
}
