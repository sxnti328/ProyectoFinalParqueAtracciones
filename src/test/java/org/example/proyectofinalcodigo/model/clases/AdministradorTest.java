package org.example.proyectofinalcodigo.model.clases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {

    private Administrador admin;
    private ParqueDeAtraccion parque;

    @BeforeEach
    void setUp() {
        admin  = new Administrador("Rosa Mendez", "ADM-DOC01", 42, "ADM001");
        parque = new ParqueDeAtraccion("Parque Fun", "900-789", "Calle 50", 200);
    }

    @Test
    void testCrearAdministradorCamposCorrectos() {
        assertEquals("Rosa Mendez", admin.getNombre());
        assertEquals("ADM-DOC01",   admin.getDocumento());
        assertEquals(42,            admin.getEdad());
        assertEquals("ADM001",      admin.getIdEmpleado());
    }

    @Test
    void testAreaPorDefectoEsGeneral() {
        assertEquals("General", admin.getArea());
    }

    @Test
    void testCrearAdministradorConAreaEspecifica() {
        Administrador adminOps = new Administrador("Luis Gomez", "ADM-DOC02", 38, "ADM002", "Operaciones");
        assertEquals("Operaciones", adminOps.getArea());
    }

    @Test
    void testCambiarArea() {
        admin.setArea("Seguridad");
        assertEquals("Seguridad", admin.getArea());
    }

    @Test
    void testConsultarReporteSinGestorRetornaMensaje() {
        String resultado = admin.consultarReporte();
        assertEquals("Sin gestor de reportes asignado.", resultado);
    }

    @Test
    void testConsultarReporteConGestorAsignado() {
        admin.setGestor(parque.getGestorReportes());
        String reporte = admin.consultarReporte();
        assertNotNull(reporte);
        assertFalse(reporte.isBlank());
    }

    @Test
    void testGestorInicialmentEsNull() {
        assertNull(admin.getGestor());
    }

    @Test
    void testToStringContieneNombreYArea() {
        String texto = admin.toString();
        assertTrue(texto.contains("Rosa Mendez"));
        assertTrue(texto.contains("General"));
    }
}
