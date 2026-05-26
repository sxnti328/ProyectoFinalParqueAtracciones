package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.TipoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketFastPassTest {

    private TicketFastPass ticket;
    private Visitante visitante;

    @BeforeEach
    void setUp() {
        ticket   = new TicketFastPass("FP01", 120000.0);
        visitante = new Visitante("Maria Fuentes", "DOC400", 28, 1.63, 300000.0);
    }

    @Test
    void testTipoEsFastPass() {
        assertEquals(TipoTicket.FAST_PASS, ticket.getTipo());
    }

    @Test
    void testActivoPorDefecto() {
        assertTrue(ticket.isActivo());
    }

    @Test
    void testDescuentoEsCero() {
        assertEquals(0.0, ticket.calcDescuento(), 0.01);
    }

    @Test
    void testPrecioFinalIgualAlPrecioBase() {
        assertEquals(120000.0, ticket.getPrecioFinal(), 0.01);
    }

    @Test
    void testPrioridadColaInicialEsUno() {
        assertEquals(1, ticket.getPrioridadCola());
    }

    @Test
    void testTienePrioridadParaCualquierAtraccionSiListaVacia() {
        assertTrue(ticket.tienePrioridad("A001"));
        assertTrue(ticket.tienePrioridad("A999"));
    }

    @Test
    void testTienePrioridadParaAtraccionHabilitada() {
        ticket.agregarAtraccionHabilitada("A010");
        assertTrue(ticket.tienePrioridad("A010"));
    }

    @Test
    void testNoTienePrioridadParaAtraccionNoHabilitada() {
        ticket.agregarAtraccionHabilitada("A010");
        assertFalse(ticket.tienePrioridad("A999"));
    }

    @Test
    void testNoDuplicaAtraccionHabilitada() {
        ticket.agregarAtraccionHabilitada("A010");
        ticket.agregarAtraccionHabilitada("A010");
        assertEquals(1, ticket.getAtraccHabilitadas().size());
    }

    @Test
    void testCompraExitosaActivaFastPassEnVisitante() {
        visitante.comprarTicket(ticket);
        assertTrue(visitante.tieneFastPass());
    }
}
