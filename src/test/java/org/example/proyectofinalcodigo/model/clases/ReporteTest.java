package org.example.proyectofinalcodigo.model.clases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReporteTest {

    private Reporte reporte;

    @BeforeEach
    void setUp() {
        reporte = new Reporte("REPORTE DIARIO - Parque Test");
    }

    @Test
    void testTituloInicializadoCorrectamente() {
        assertEquals("REPORTE DIARIO - Parque Test", reporte.getTitulo());
    }

    @Test
    void testFechaEsHoy() {
        assertEquals(LocalDate.now(), reporte.getFecha());
    }

    @Test
    void testValoresNumericosInicianEnCero() {
        assertEquals(0.0, reporte.getIngresosDiarios(),     0.01);
        assertEquals(0,   reporte.getTotalVisitantes());
        assertEquals(0.0, reporte.getTiempoPromedioEspera(), 0.01);
    }

    @Test
    void testListasInicialesVacias() {
        assertTrue(reporte.getAtraccionesMasVisitadas().isEmpty());
        assertTrue(reporte.getAtraccionesEnMantenimiento().isEmpty());
        assertTrue(reporte.getAtraccionesCerradasPorClima().isEmpty());
    }

    @Test
    void testSetIngresosDiarios() {
        reporte.setIngresosDiarios(250000.0);
        assertEquals(250000.0, reporte.getIngresosDiarios(), 0.01);
    }

    @Test
    void testSetTotalVisitantes() {
        reporte.setTotalVisitantes(45);
        assertEquals(45, reporte.getTotalVisitantes());
    }

    @Test
    void testFormatearReporteContieneTitulo() {
        String texto = reporte.formatearReporte();
        assertTrue(texto.contains("REPORTE DIARIO - Parque Test"));
    }

    @Test
    void testFormatearReporteContieneSeccionesEsperadas() {
        String texto = reporte.formatearReporte();
        assertTrue(texto.contains("INGRESOS DIARIOS"));
        assertTrue(texto.contains("VISITANTES"));
        assertTrue(texto.contains("MANTENIMIENTO"));
        assertTrue(texto.contains("TIEMPO PROMEDIO"));
    }

    @Test
    void testFormatearReporteConAtraccionEnMantenimiento() {
        Reporte r = new Reporte("Test");
        Atraccion a = new Atraccion("A001", "Free Fall",
                org.example.proyectofinalcodigo.model.enums.TipoAtraccion.MECANICA_ALTURA,
                10, 1.50, 14, 0.0);
        a.setEstado(org.example.proyectofinalcodigo.model.enums.EstadoActual.EN_MANTENIMIENTO);
        a.setMotivoCierre(org.example.proyectofinalcodigo.model.enums.MotivoCierre.REVISION_TECNICA);
        r.setAtraccionesEnMantenimiento(List.of(a));

        assertTrue(r.formatearReporte().contains("Free Fall"));
    }
}