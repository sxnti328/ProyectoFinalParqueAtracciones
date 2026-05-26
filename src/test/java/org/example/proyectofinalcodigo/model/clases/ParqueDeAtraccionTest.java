package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.TipoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParqueDeAtraccionTest {

    private ParqueDeAtraccion parque;
    private Visitante visitante;

    @BeforeEach
    void setUp() {
        parque   = new ParqueDeAtraccion("Mundo Magico", "800-456", "Av. Principal 10", 100);
        visitante = new Visitante("Sofia Reyes", "V100", 27, 1.68, 150000.0);
    }

    @Test
    void testAgregarVisitanteNuevo() {
        assertTrue(parque.agregarVisitante(visitante));
        assertEquals(1, parque.getVisitantesActuales());
    }

    @Test
    void testNoPuedeAgregarVisitanteDuplicado() {
        parque.agregarVisitante(visitante);
        assertFalse(parque.agregarVisitante(visitante));
        assertEquals(1, parque.getVisitantesActuales());
    }

    @Test
    void testBuscarVisitanteExistente() {
        parque.agregarVisitante(visitante);
        Visitante encontrado = parque.buscarVisitante("V100");
        assertNotNull(encontrado);
        assertEquals("Sofia Reyes", encontrado.getNombre());
    }

    @Test
    void testBuscarVisitanteInexistenteRetornaNull() {
        assertNull(parque.buscarVisitante("V999"));
    }

    @Test
    void testEliminarVisitanteDecremantaContador() {
        parque.agregarVisitante(visitante);
        assertTrue(parque.eliminarVisitante("V100"));
        assertEquals(0, parque.getVisitantesActuales());
        assertNull(parque.buscarVisitante("V100"));
    }

    @Test
    void testRecargarSaldoVisitante() {
        parque.agregarVisitante(visitante);
        String resultado = parque.recargarSaldoVisitante("V100", 50000.0);
        assertTrue(resultado.contains("200000"));
    }

    @Test
    void testVenderTicketGeneralConSaldoSuficiente() {
        parque.agregarVisitante(visitante);
        TicketGeneral tk = new TicketGeneral("TK01", 40000.0);
        visitante.comprarTicket(tk);
        String resultado = parque.venderTicket("V100", TipoTicket.GENERAL, 40000.0, 0);
        assertTrue(resultado.contains("GENERAL") || resultado.contains("comprado"));
    }

    @Test
    void testAgregarZonaNueva() {
        Zona zona = new Zona("Z01", "Zona Norte", "Descripcion", 80);
        assertTrue(parque.agregarZona(zona));
        assertEquals(1, parque.getListZona().size());
    }

    @Test
    void testNoPuedeAgregarZonaDuplicada() {
        Zona zona = new Zona("Z01", "Zona Norte", "Descripcion", 80);
        parque.agregarZona(zona);
        assertFalse(parque.agregarZona(zona));
        assertEquals(1, parque.getListZona().size());
    }

    @Test
    void testBuscarZonaExistente() {
        Zona zona = new Zona("Z05", "Zona Sur", "Descripcion", 50);
        parque.agregarZona(zona);
        assertNotNull(parque.buscarZona("Z05"));
    }
}