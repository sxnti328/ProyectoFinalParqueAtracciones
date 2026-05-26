package org.example.proyectofinalcodigo.model.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reporte {
    private String    titulo;
    private LocalDate fecha;
    private double ingresosDiarios;
    private int totalVisitantes;
    private double  tiempoPromedioEspera;
    private List<Atraccion> atraccionesMasVisitadas;
    private List<Atraccion> atraccionesEnMantenimiento;
    private List<Atraccion> atraccionesCerradasPorClima;

    public Reporte(String titulo) {
        this.titulo = titulo;
        this.fecha   = LocalDate.now();
        this.ingresosDiarios = 0.0;
        this.totalVisitantes  = 0;
        this.tiempoPromedioEspera = 0.0;
        this.atraccionesMasVisitadas = new ArrayList<>();
        this.atraccionesEnMantenimiento  = new ArrayList<>();
        this.atraccionesCerradasPorClima  = new ArrayList<>();
    }

    public String formatearReporte() {
        String texto = "";
        texto += "========================================\n";
        texto += "   " + titulo + "\n";
        texto += "   Fecha: " + fecha + "\n";
        texto += "========================================\n\n";

        texto += "-- INGRESOS DIARIOS --\n";
        texto += "   Total: $" + ingresosDiarios + "\n\n";

        texto += "-- VISITANTES --\n";
        texto += "   Registrados: " + totalVisitantes + "\n\n";

        texto += "-- ATRACCIONES MAS VISITADAS --\n";
        for (int i = 0; i < atraccionesMasVisitadas.size() && i < 5; i++) {
            Atraccion a = atraccionesMasVisitadas.get(i);
            texto += "   " + (i + 1) + ". " + a.getNombre()
                    + " - " + a.getContadorVisitantes() + " visitantes\n";
        }

        texto += "\n-- ATRACCIONES EN MANTENIMIENTO --\n";
        if (atraccionesEnMantenimiento.isEmpty()) {
            texto += "   (ninguna)\n";
        } else {
            for (Atraccion a : atraccionesEnMantenimiento) {
                texto += "   - " + a.getNombre() + " (" + a.getMotivoCierre() + ")\n";
            }
        }

        texto += "\n-- CIERRES POR CLIMA --\n";
        if (atraccionesCerradasPorClima.isEmpty()) {
            texto += "   (ninguna)\n";
        } else {
            for (Atraccion a : atraccionesCerradasPorClima) {
                texto += "   - " + a.getNombre() + "\n";
            }
        }

        texto += "\n-- TIEMPO PROMEDIO DE ESPERA --\n";
        texto += "   " + tiempoPromedioEspera + " min\n";

        texto += "\n========================================\n";
        return texto;
    }

    public String getTitulo()  {
        return titulo;
    }
    public void   setTitulo(String t)  {
        this.titulo = t;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void   setFecha(LocalDate f)  {
        this.fecha = f;
    }
    public double getIngresosDiarios()  {
        return ingresosDiarios;
    }
    public void   setIngresosDiarios(double i) {
        this.ingresosDiarios = i; }
    public int    getTotalVisitantes()   {
        return totalVisitantes; }
    public void   setTotalVisitantes(int t) {
        this.totalVisitantes = t; }
    public double getTiempoPromedioEspera() { return tiempoPromedioEspera; }
    public void   setTiempoPromedioEspera(double t) {
        this.tiempoPromedioEspera = t;
    }
    public List<Atraccion> getAtraccionesMasVisitadas(){
        return atraccionesMasVisitadas;
    }
    public void   setAtraccionesMasVisitadas(List<Atraccion> l) {
        this.atraccionesMasVisitadas = l;
    }
    public List<Atraccion> getAtraccionesEnMantenimiento() {
        return atraccionesEnMantenimiento;
    }
    public void   setAtraccionesEnMantenimiento(List<Atraccion> l) {
        this.atraccionesEnMantenimiento = l;
    }
    public List<Atraccion> getAtraccionesCerradasPorClima() {
        return atraccionesCerradasPorClima;
    }
    public void   setAtraccionesCerradasPorClima(List<Atraccion> l) {
        this.atraccionesCerradasPorClima = l;
    }

    @Override
    public String toString() {
        return formatearReporte();
    }
}

