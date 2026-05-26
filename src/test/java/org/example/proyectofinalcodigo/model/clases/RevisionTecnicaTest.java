package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.enums.MotivoCierre;
import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RevisionTecnicaTest {

    private RevisionTecnica revision;
    private Atraccion atraccion;
    private Operador operador;

    @BeforeEach
    void setUp() {
        revision  = new RevisionTecnica("REV001", "A001", "OP001", "Inspeccion general de frenos");
        atraccion = new Atraccion("A001", "Coaster Extremo", TipoAtraccion.MECANICA_ALTURA,
                30, 1.50, 14, 0.0);
        operador  = new Operador("Jorge Medina", "OP-DOC01", 35, "OP001", "MANANA", "Z01");
    }

    @Test
    void testCamposInicializadosCorrectamente() {
        assertEquals("REV001",  revision.getIdRevision());
        assertEquals("A001",    revision.getIdAtraccion());
        assertEquals("OP001",   revision.getIdOperador());
        assertEquals("Inspeccion general de frenos", revision.getDescripcion());
    }

    @Test
    void testAprobadaPorDefecto() {
        assertTrue(revision.isAprobada());
    }

    @Test
    void testFechaEsHoy() {
        assertEquals(LocalDate.now(), revision.getFecha());
    }

    @Test
    void testCambiarEstadoAprobada() {
        revision.setAprobada(false);
        assertFalse(revision.isAprobada());
    }

    @Test
    void testRegistrarRevisionReactivaAtraccionEnMantenimiento() {
        atraccion.setEstado(EstadoActual.EN_MANTENIMIENTO);
        atraccion.setMotivoCierre(MotivoCierre.REVISION_TECNICA);

        atraccion.registrarRevisionTecnica(operador, "Mantenimiento completado");

        assertEquals(EstadoActual.ACTIVA, atraccion.getEstado());
        assertNull(atraccion.getMotivoCierre());
    }

    @Test
    void testRegistrarRevisionGuardaHistorial() {
        atraccion.registrarRevisionTecnica(operador, "Primera revision");
        atraccion.registrarRevisionTecnica(operador, "Segunda revision");

        assertEquals(2, atraccion.getRevisiones().size());
    }

    @Test
    void testNotificacionRevisionContieneNombreAtraccion() {
        var notif = atraccion.registrarRevisionTecnica(operador, "Revision de rutina");
        assertTrue(notif.mensaje().contains("Coaster Extremo"));
    }
}
