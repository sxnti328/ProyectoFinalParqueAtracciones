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

    @Test
    void testAccesoDenegadoPorEdadInsuficiente() {
        Visitante menor = new Visitante("Pequeno", "002", 10, 1.50, 10000.0);
        assertFalse(atraccion.verificarAcceso(menor));
    }

    @Test
    void testAccesoDenegadoCuandoAtraccionCerrada() {
        atraccion.setEstado(EstadoActual.CERRADA);
        assertFalse(atraccion.verificarAcceso(visitante));
    }

    @Test
    void testAccesoDenegadoCuandoEnMantenimiento() {
        atraccion.setEstado(EstadoActual.EN_MANTENIMIENTO);
        assertFalse(atraccion.verificarAcceso(visitante));
    }

    @Test
    void testRegistrarIngresoExitosoRetornaMensajeCorrecto() {
        String resultado = atraccion.registrarIngreso(visitante);
        assertTrue(resultado.contains("Acceso autorizado"));
    }

    @Test
    void testRegistrarIngresoIncrementaContadorVisitantes() {
        atraccion.registrarIngreso(visitante);
        assertEquals(1, atraccion.getContadorVisitantes());
    }

    @Test
    void testRegistrarIngresoConCostoAdicionalDescuentaSaldo() {
        atraccion.setCostoAdicional(5000.0);
        double saldoAntes = visitante.getSaldoVirtual();
        atraccion.registrarIngreso(visitante);
        assertEquals(saldoAntes - 5000.0, visitante.getSaldoVirtual(), 0.01);
    }

    @Test
    void testRegistrarIngresoDenegadoPorSaldoInsuficiente() {
        atraccion.setCostoAdicional(50000.0);
        Visitante sinSaldo = new Visitante("Pobre", "003", 15, 1.50, 100.0);
        String resultado = atraccion.registrarIngreso(sinSaldo);
        assertTrue(resultado.contains("Saldo insuficiente"));
    }

    @Test
    void testCerrarPorClimaCambiaEstadoYMotivo() {
        atraccion.cerrarPorClima();
        assertEquals(EstadoActual.CERRADA, atraccion.getEstado());
        assertEquals(org.example.proyectofinalcodigo.model.enums.MotivoCierre.CLIMA, atraccion.getMotivoCierre());
    }

    @Test
    void testNoRequiereCierreClimaticoCuandoYaEstaCerrada() {
        atraccion.cerrarPorClima();
        assertFalse(atraccion.requiereCierreClimatico());
    }

    @Test
    void testMantenimientoPreventivoSeActivaA500Visitantes() {
        atraccion.setContadorVisitantes(499);
        atraccion.incrementarContador();
        assertEquals(EstadoActual.EN_MANTENIMIENTO, atraccion.getEstado());
        assertEquals(org.example.proyectofinalcodigo.model.enums.MotivoCierre.REVISION_TECNICA, atraccion.getMotivoCierre());
    }
}
