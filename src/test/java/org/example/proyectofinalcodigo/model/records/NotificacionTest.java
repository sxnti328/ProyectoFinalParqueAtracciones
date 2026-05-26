package org.example.proyectofinalcodigo.model.records;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NotificacionTest {

    @Test
    void testCamposDelRecordCorrectamente() {
        LocalDate hoy = LocalDate.now();
        Notificacion n = new Notificacion("CLIMA", "Atraccion cerrada por lluvia", hoy);

        assertEquals("CLIMA",                         n.tipo());
        assertEquals("Atraccion cerrada por lluvia",  n.mensaje());
        assertEquals(hoy,                             n.fecha());
    }

    @Test
    void testDosNotificacionesIgualesSonEquals() {
        LocalDate fecha = LocalDate.of(2025, 6, 10);
        Notificacion n1 = new Notificacion("REVISION", "Mantenimiento completado", fecha);
        Notificacion n2 = new Notificacion("REVISION", "Mantenimiento completado", fecha);

        assertEquals(n1, n2);
    }

    @Test
    void testNotificacionesDiferentesNoSonIguales() {
        LocalDate fecha = LocalDate.now();
        Notificacion n1 = new Notificacion("CLIMA",    "Cierre por tormenta", fecha);
        Notificacion n2 = new Notificacion("REVISION", "Cierre por tormenta", fecha);

        assertNotEquals(n1, n2);
    }

    @Test
    void testToStringContieneTipoYMensaje() {
        Notificacion n = new Notificacion("PROMO", "Descuento en tickets", LocalDate.now());
        String texto = n.toString();
        assertTrue(texto.contains("PROMO"));
        assertTrue(texto.contains("Descuento en tickets"));
    }

    @Test
    void testHashCodeIgualParaNotificacionesIguales() {
        LocalDate fecha = LocalDate.of(2025, 1, 15);
        Notificacion n1 = new Notificacion("AVISO", "Parque abierto", fecha);
        Notificacion n2 = new Notificacion("AVISO", "Parque abierto", fecha);

        assertEquals(n1.hashCode(), n2.hashCode());
    }
}