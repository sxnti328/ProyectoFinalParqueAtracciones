package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperadorTest {

    private Operador operador;
    private Atraccion atraccion;
    private Zona zona;

    @BeforeEach
    void setUp() {
        operador  = new Operador("Carlos Vega", "OP-DOC10", 31, "OP010", "MANANA", "Z01");
        zona      = new Zona("Z01", "Zona Mecanica", "Zona con atracciones mecanicas", 100);
        atraccion = new Atraccion("A010", "Tornado", TipoAtraccion.MECANICA_ALTURA, 20, 1.45, 13, 0.0);
        zona.agregarAtraccion(atraccion);
    }

    @Test
    void testCrearOperadorCamposCorrectos() {
        assertEquals("Carlos Vega", operador.getNombre());
        assertEquals("OP-DOC10",    operador.getDocumento());
        assertEquals(31,            operador.getEdad());
        assertEquals("OP010",       operador.getIdEmpleado());
        assertEquals("MANANA",      operador.getTurno());
        assertEquals("Z01",         operador.getIdZona());
    }

    @Test
    void testPuedeGestionarAtraccionEnSuZona() {
        assertTrue(operador.puedeGestionarAtraccion(atraccion));
    }

    @Test
    void testNoPuedeGestionarAtraccionEnOtraZona() {
        Zona otraZona = new Zona("Z99", "Zona Lejana", "Otra zona", 50);
        Atraccion otra = new Atraccion("A099", "Cohete", TipoAtraccion.MECANICA_ALTURA, 10, 1.60, 16, 0.0);
        otraZona.agregarAtraccion(otra);

        assertFalse(operador.puedeGestionarAtraccion(otra));
    }

    @Test
    void testNoPuedeGestionarAtraccionSinZonaAsignada() {
        Atraccion sinZona = new Atraccion("A050", "Libre", TipoAtraccion.MECANICA_ALTURA, 10, 0.90, 4, 0.0);
        assertFalse(operador.puedeGestionarAtraccion(sinZona));
    }

    @Test
    void testCambiarTurno() {
        operador.setTurno("NOCHE");
        assertEquals("NOCHE", operador.getTurno());
    }

    @Test
    void testCambiarZonaAsignada() {
        operador.setIdZona("Z02");
        assertEquals("Z02", operador.getIdZona());
    }

    @Test
    void testToStringContieneNombreYTurno() {
        String texto = operador.toString();
        assertTrue(texto.contains("Carlos Vega"));
        assertTrue(texto.contains("MANANA"));
    }
}