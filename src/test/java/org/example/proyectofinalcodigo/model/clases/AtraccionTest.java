package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtraccionTest {

    private Atraccion atraccion;
    private Visitante visitante;

    @BeforeEach
    void setUp() {
        atraccion = new Atraccion("A001", "Montana Rusa", TipoAtraccion.MECANICA_ALTURA,
                20, 1.40, 12, 0.0);
        visitante = new Visitante("Ana Gomez", "789", 15, 1.50, 30000.0);
    }

    @Test
    void testAtraccionIniciaActiva() {
        assertEquals(EstadoActual.ACTIVA, atraccion.getEstado());
        assertNull(atraccion.getMotivoCierre());
    }

    @Test
    void testAccesoDenegadoPorEstatura() {
        Visitante bajo = new Visitante("Nino", "001", 13, 1.20, 10000.0);
        assertFalse(atraccion.verificarAcceso(bajo));
    }

    @Test
    void testAccesoPermitido() {
        // 1.50 >= 1.40 y 15 >= 12, DEBERIA poder entrar
        assertTrue(atraccion.verificarAcceso(visitante));
    }

    @Test
    void testRequiereCierreClimatico() {
        assertTrue(atraccion.requiereCierreClimatico());
    }
}
