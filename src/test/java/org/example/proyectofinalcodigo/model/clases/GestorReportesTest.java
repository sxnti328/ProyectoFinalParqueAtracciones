package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.enums.MotivoCierre;
import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorReportesTest {

    private ParqueDeAtraccion parque;
    private GestorReportes gestor;
    private Atraccion atraccionA;
    private Atraccion atraccionB;

    @BeforeEach
    void setUp() {
        parque = new ParqueDeAtraccion("Parque Test", "900-123", "Calle 1", 500);
        gestor = parque.getGestorReportes();

        Zona zona = new Zona("Z01", "Zona Aventura", "Zona principal", 200);
        parque.agregarZona(zona);

        atraccionA = new Atraccion("A001", "Montana Rusa", TipoAtraccion.MECANICA_ALTURA, 20, 1.40, 12, 0.0);
        atraccionB = new Atraccion("A002", "Splash Water",  TipoAtraccion.ACUATICA,        15, 1.20, 8,  0.0);

        parque.agregarAtraccionAZona("Z01", atraccionA);
        parque.agregarAtraccionAZona("Z01", atraccionB);
    }

    @Test
    void testIngresosDiariosEsCeroSinVisitantes() {
        assertEquals(0.0, gestor.calcularIngresosDiarios(), 0.01);
    }

    @Test
    void testIngresosDiariosConTicketsComprados() {
        Visitante v = new Visitante("Ana Gil", "V001", 25, 1.65, 200000.0);
        parque.agregarVisitante(v);
        v.comprarTicket(new TicketGeneral("TK01", 50000.0));
        v.comprarTicket(new TicketGeneral("TK02", 30000.0));

        assertEquals(80000.0, gestor.calcularIngresosDiarios(), 0.01);
    }

    @Test
    void testAtraccionesMasVisitadasOrdenadosMayorAMenor() {
        atraccionA.setContadorVisitantes(10);
        atraccionB.setContadorVisitantes(50);

        List<Atraccion> ordenadas = gestor.getAtraccionesMasVisitadas();

        assertEquals("Splash Water",  ordenadas.get(0).getNombre());
        assertEquals("Montana Rusa", ordenadas.get(1).getNombre());
    }

    @Test
    void testAtraccionesEnMantenimientoSoloDevuelveEsas() {
        atraccionA.setEstado(EstadoActual.EN_MANTENIMIENTO);
        atraccionA.setMotivoCierre(MotivoCierre.REVISION_TECNICA);

        List<Atraccion> enMantenimiento = gestor.getAtraccionesEnMantenimiento();

        assertEquals(1, enMantenimiento.size());
        assertEquals("Montana Rusa", enMantenimiento.get(0).getNombre());
    }

    @Test
    void testAtraccionesCerradasPorClimaFiltradas() {
        atraccionB.setEstado(EstadoActual.CERRADA);
        atraccionB.setMotivoCierre(MotivoCierre.CLIMA);

        List<Atraccion> cerradas = gestor.getAtraccionesCerradasPorClima();

        assertEquals(1, cerradas.size());
        assertEquals("Splash Water", cerradas.get(0).getNombre());
    }

    @Test
    void testTiempoPromedioEsperaConAtraccionesActivas() {
        atraccionA.setTiempoEspera(10);
        atraccionB.setTiempoEspera(20);

        assertEquals(15.0, gestor.calcularTiempoPromedioEspera(), 0.01);
    }

    @Test
    void testTiempoPromedioEsperaEsCeroSinAtraccionesActivas() {
        atraccionA.setEstado(EstadoActual.EN_MANTENIMIENTO);
        atraccionB.setEstado(EstadoActual.CERRADA);

        assertEquals(0.0, gestor.calcularTiempoPromedioEspera(), 0.01);
    }
}
