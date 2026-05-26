package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;
import org.example.proyectofinalcodigo.model.records.Notificacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZonaTest {

    private Zona zona;
    private Atraccion atraccionAcuatica;
    private Atraccion atraccionMecanica;
    private Visitante visitante;
    private Operador operador;

    @BeforeEach
    void setUp() {
        zona             = new Zona("Z01", "Zona Agua", "Zona acuatica principal", 3);
        atraccionAcuatica = new Atraccion("A001", "Rio Loco",    TipoAtraccion.ACUATICA,        10, 1.10, 6, 0.0);
        atraccionMecanica = new Atraccion("A002", "Carrusel",    TipoAtraccion.MECANICA_ALTURA,   15, 0.80, 3, 0.0);
        visitante        = new Visitante("Luis Mora", "V001", 20, 1.70, 50000.0);
        operador         = new Operador("Sandra Rios", "OP-DOC01", 29, "OP001", "TARDE", "Z01");
    }

    @Test
    void testZonaIniciaActivaYVacia() {
        assertEquals(EstadoActual.ACTIVA, zona.getEstadoZona());
        assertEquals(0, zona.getVisitantesActuales());
    }

    @Test
    void testAgregarAtraccionIncrementaTotalElementos() {
        zona.agregarAtraccion(atraccionAcuatica);
        assertEquals(1, zona.getListAtraccion().size());
        assertSame(zona, atraccionAcuatica.getZona());
    }

    @Test
    void testAgregarOperadorLoAsignaALaZona() {
        zona.agregarOperador(operador);
        assertEquals(1, zona.getListOperador().size());
        assertEquals("Z01", operador.getIdZona());
    }

    @Test
    void testZonaNoEstaLlenaConCapacidadDisponible() {
        zona.setVisitantesActuales(2);
        assertFalse(zona.estaLlena());
    }

    @Test
    void testZonaEstaLlenaAlAlcanzarCapacidadMaxima() {
        zona.setVisitantesActuales(3);
        assertTrue(zona.estaLlena());
    }

    @Test
    void testRegistrarIngresoExitosoIncrementaContador() {
        zona.registrarIngreso(visitante);
        assertEquals(1, zona.getVisitantesActuales());
    }

    @Test
    void testRegistrarIngresoDenegadoCuandoZonaLlena() {
        zona.setVisitantesActuales(3);
        String resultado = zona.registrarIngreso(visitante);
        assertTrue(resultado.contains("denegado"));
        assertEquals(3, zona.getVisitantesActuales());
    }

    @Test
    void testBuscarAtraccionPorIdExistente() {
        zona.agregarAtraccion(atraccionMecanica);
        Atraccion encontrada = zona.buscarAtraccion("A002");
        assertNotNull(encontrada);
        assertEquals("Carrusel", encontrada.getNombre());
    }

    @Test
    void testBuscarAtraccionInexistenteRetornaNull() {
        assertNull(zona.buscarAtraccion("A999"));
    }

    @Test
    void testActivarAlertaClimaticaCierraAtraccionesAcuaticas() {
        zona.agregarAtraccion(atraccionAcuatica);
        zona.agregarAtraccion(atraccionMecanica);

        Notificacion notif = zona.activarAlertaClimatica();

        assertEquals(EstadoActual.CERRADA, atraccionAcuatica.getEstado());
        assertEquals(EstadoActual.ACTIVA,  atraccionMecanica.getEstado());
        assertNotNull(notif);
    }
}