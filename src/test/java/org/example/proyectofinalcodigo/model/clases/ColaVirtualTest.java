package org.example.proyectofinalcodigo.model.clases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColaVirtualTest {

    private ColaVirtual cola;
    private Visitante visitanteGeneral;
    private Visitante visitanteFastPass;

    @BeforeEach
    void setUp() {
        cola = new ColaVirtual();
        visitanteGeneral  = new Visitante("Pedro Ruiz", "AAA111", 25, 1.70, 50000.0);
        visitanteFastPass = new Visitante("Maria Paz",  "BBB222", 30, 1.65, 200000.0);
        TicketFastPass ticket = new TicketFastPass("FP01", 120000.0);
        visitanteFastPass.comprarTicket(ticket);
    }

    @Test
    void testVisitanteGeneralVaAColaGeneral() {
        cola.agregar(visitanteGeneral);
        assertEquals(1, cola.getCantidadGeneral());
        assertEquals(0, cola.getCantidadFastPass());
    }

    @Test
    void testVisitanteFastPassVaAColaFastPass() {
        cola.agregar(visitanteFastPass);
        assertEquals(0, cola.getCantidadGeneral());
        assertEquals(1, cola.getCantidadFastPass());
    }

    @Test
    void testFastPassTienePrioridadSobreGeneral() {
        cola.agregar(visitanteGeneral);
        cola.agregar(visitanteFastPass);
        Visitante siguiente = cola.siguienteEnCola();
        assertEquals("BBB222", siguiente.getDocumento());
    }

    @Test
    void testEliminarVisitantePorDocumento() {
        cola.agregar(visitanteGeneral);
        boolean eliminado = cola.eliminar("AAA111");
        assertTrue(eliminado);
        assertEquals(0, cola.getTotalEnCola());
    }

    @Test
    void testGetTotalElementosContaAmbasColas() {
        cola.agregar(visitanteGeneral);
        cola.agregar(visitanteFastPass);
        assertEquals(2, cola.getTotalElementos());
    }

    @Test
    void testLimpiarColaVaciaAmbas() {
        cola.agregar(visitanteGeneral);
        cola.agregar(visitanteFastPass);
        cola.limpiarCola();
        assertEquals(0, cola.getTotalEnCola());
    }
}
