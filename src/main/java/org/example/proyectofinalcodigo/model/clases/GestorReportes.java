package org.example.proyectofinalcodigo.model.clases;


import org.example.proyectofinalcodigo.model.clasesAbstractas.Ticket;
import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.enums.MotivoCierre;

import java.util.ArrayList;
import java.util.List;

public class GestorReportes {

    private ParqueDeAtraccion parque;

    public GestorReportes(ParqueDeAtraccion parque) {

        this.parque = parque;
    }

    public Reporte generarReporte() {
        Reporte reporte = new Reporte("REPORTE DIARIO - " + parque.getNombre());
        reporte.setIngresosDiarios(calcularIngresosDiarios());
        reporte.setTotalVisitantes(parque.getListVisitante().size());
        reporte.setTiempoPromedioEspera(calcularTiempoPromedioEspera());
        reporte.setAtraccionesMasVisitadas(getAtraccionesMasVisitadas());
        reporte.setAtraccionesEnMantenimiento(getAtraccionesEnMantenimiento());
        reporte.setAtraccionesCerradasPorClima(getAtraccionesCerradasPorClima());
        return reporte;
    }

    public String generarReporteDiario() {

        return generarReporte().formatearReporte();
    }

    public double calcularIngresosDiarios() {
        double total = 0;
        for (Visitante v : parque.getListVisitante()) {
            for (Ticket t : v.getListTickets()) {
                total += t.getPrecioFinal();
            }
        }
        return total;
    }

    // ordena las atracciones de mayor a menor por visitantes
    public List<Atraccion> getAtraccionesMasVisitadas() {
        List<Atraccion> todas = new ArrayList<>(parque.getTodasLasAtracciones());
        for (int i = 0; i < todas.size() - 1; i++) {
            for (int j = 0; j < todas.size() - 1 - i; j++) {
                if (todas.get(j).getContadorVisitantes() < todas.get(j + 1).getContadorVisitantes()) {
                    Atraccion tmp = todas.get(j);
                    todas.set(j, todas.get(j + 1));
                    todas.set(j + 1, tmp);
                }
            }
        }
        return todas;
    }

    public List<Atraccion> getAtraccionesEnMantenimiento() {
        List<Atraccion> lista = new ArrayList<>();
        for (Atraccion a : parque.getTodasLasAtracciones()) {
            if (a.getEstado() == EstadoActual.EN_MANTENIMIENTO) {
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Atraccion> getAtraccionesCerradasPorClima() {
        List<Atraccion> lista = new ArrayList<>();
        for (Atraccion a : parque.getTodasLasAtracciones()) {
            if (a.getMotivoCierre() == MotivoCierre.CLIMA) {
                lista.add(a);
            }
        }
        return lista;
    }

    public double calcularTiempoPromedioEspera() {
        int suma = 0;
        int cantidad = 0;
        for (Atraccion a : parque.getTodasLasAtracciones()) {
            if (a.getEstado() == EstadoActual.ACTIVA) {
                suma += a.getTiempoEspera();
                cantidad++;
            }
        }
        if (cantidad == 0) return 0;
        return (double) suma / cantidad;
    }
}