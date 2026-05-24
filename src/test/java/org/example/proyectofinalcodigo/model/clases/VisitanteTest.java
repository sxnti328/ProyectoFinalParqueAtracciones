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
}
