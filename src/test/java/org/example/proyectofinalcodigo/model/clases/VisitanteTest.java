package org.example.proyectofinalcodigo.model.clases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitanteTest {

    private Visitante visitante;

    @BeforeEach
    void setUp() {
        visitante = new Visitante("Carlos Lopez", "123456", 22, 1.75, 50000.0);
    }

    @Test
    void testCrearVisitante() {
        assertEquals("Carlos Lopez", visitante.getNombre());
        assertEquals("123456", visitante.getDocumento());
        assertEquals(22, visitante.getEdad());
        assertEquals(50000.0, visitante.getSaldoVirtual());
    }

    @Test
    void testRecargarSaldo() {
        visitante.recargarSaldo(20000.0);
        assertEquals(70000.0, visitante.getSaldoVirtual());
    }

    @Test
    void testSinFastPassPorDefecto() {
        assertFalse(visitante.tieneFastPass());
    }

    @Test
    void testDescontarSaldo() {
        boolean resultado = visitante.descontarSaldo(10000.0);
        assertTrue(resultado);
        assertEquals(40000.0, visitante.getSaldoVirtual());
    }

    @Test
    void testDescontarSaldoInsuficienteRetornaFalseYNoModifica() {
        boolean resultado = visitante.descontarSaldo(999999.0);
        assertFalse(resultado);
        assertEquals(50000.0, visitante.getSaldoVirtual());
    }

    @Test
    void testRecargarSaldoNegativoNoModificaSaldo() {
        visitante.recargarSaldo(-5000.0);
        assertEquals(50000.0, visitante.getSaldoVirtual());
    }

    @Test
    void testAgregarFavorita() {
        visitante.agregarFavorita("A001");
        assertTrue(visitante.tieneFavorita("A001"));
    }

    @Test
    void testNoDuplicaFavorita() {
        visitante.agregarFavorita("A001");
        visitante.agregarFavorita("A001");
        assertEquals(1, visitante.getListaFavoritas().size());
    }

    @Test
    void testEliminarFavorita() {
        visitante.agregarFavorita("A001");
        visitante.eliminarFavorita("A001");
        assertFalse(visitante.tieneFavorita("A001"));
    }

    @Test
    void testTieneFastPassConTicketActivo() {
        TicketFastPass fp = new TicketFastPass("FP01", 120000.0);
        visitante.comprarTicket(fp);
        assertTrue(visitante.tieneFastPass());
    }

    @Test
    void testAgregarNotificacionSeGuardaEnLaLista() {
        org.example.proyectofinalcodigo.model.records.Notificacion n =
                new org.example.proyectofinalcodigo.model.records.Notificacion(
                        "AVISO", "Bienvenido al parque", java.time.LocalDate.now());
        visitante.agregarNotificacion(n);
        assertEquals(1, visitante.getListNotificaciones().size());
    }

    @Test
    void testGetTicketActivoRetornaNullSinTickets() {
        assertNull(visitante.getTicketActivo());
    }
}
