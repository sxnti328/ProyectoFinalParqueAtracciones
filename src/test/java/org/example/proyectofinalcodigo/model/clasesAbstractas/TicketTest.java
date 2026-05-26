package org.example.proyectofinalcodigo.model.clasesAbstractas;

import org.example.proyectofinalcodigo.model.clases.TicketGeneral;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    private TicketGeneral ticket;

    @BeforeEach
    void setUp() {
        ticket = new TicketGeneral("T001", 60000.0);
    }

    @Test
    void testIdInicializadoCorrectamente() {
        assertEquals("T001", ticket.getId());
    }

    @Test
    void testPrecioInicializadoCorrectamente() {
        assertEquals(60000.0, ticket.getPrecio(), 0.01);
    }

    @Test
    void testActivoPorDefecto() {
        assertTrue(ticket.isActivo());
    }

    @Test
    void testFechaCompraEsHoy() {
        assertEquals(LocalDate.now(), ticket.getFechaCompra());
    }

    @Test
    void testTipoEsElEsperado() {
        assertEquals(TipoTicket.GENERAL, ticket.getTipo());
    }

    @Test
    void testSetActivoAFalso() {
        ticket.setActivo(false);
        assertFalse(ticket.isActivo());
    }

    @Test
    void testSetPrecio() {
        ticket.setPrecio(90000.0);
        assertEquals(90000.0, ticket.getPrecio(), 0.01);
    }

    @Test
    void testGetPrecioFinalSinDescuento() {
        assertEquals(60000.0, ticket.getPrecioFinal(), 0.01);
    }
}