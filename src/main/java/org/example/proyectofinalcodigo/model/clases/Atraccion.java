package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.enums.MotivoCierre;
import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;
import org.example.proyectofinalcodigo.model.interfaces.IAccesible;

import java.util.ArrayList;
import java.util.List;

public class Atraccion implements IAccesible {

    private static final int LIMITE_MANTENIMIENTO = 500;

    private String id;
    private String nombre;
    private TipoAtraccion tipo;
    private int  capacidadMaxima;
    private double alturaMinima;
    private int edadMinima;
    private double costoAdicional;
    private int contadorVisitantes;
    private int tiempoEspera;
    private EstadoActual  estado;
    private MotivoCierre motivoCierre;
    private Zona zona;
    private ColaVirtual  colaVirtual;
    private List<RevisionTecnica> revisiones;

    public Atraccion(String id, String nombre, TipoAtraccion tipo, int capacidadMaxima, double alturaMinima, int edadMinima, double costoAdicional, int contadorVisitantes, int tiempoEspera, EstadoActual estado, MotivoCierre motivoCierre, Zona zona, ColaVirtual colaVirtual, List<RevisionTecnica> revisiones) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
        this.alturaMinima = alturaMinima;
        this.edadMinima = edadMinima;
        this.costoAdicional = costoAdicional;
        this.contadorVisitantes = contadorVisitantes;
        this.tiempoEspera = tiempoEspera;
        this.estado = estado;
        this.motivoCierre = motivoCierre;
        this.zona = zona;
        this.colaVirtual = colaVirtual;
        this.revisiones = revisiones;
    }


    @Override
    public boolean verificarAcceso(Visitante visitante) {
        if (estado != EstadoActual.ACTIVA) return false;
        if (visitante.getEstatura() < alturaMinima)  return false;
        if (visitante.getEdad()     < edadMinima)    return false;
        return true;
    }

    @Override
    public double getCostoAdicional() {
        return costoAdicional;
    }

    @Override
    public EstadoActual getEstado() {
        return estado; }


    public void verificarMantenimientoPreventivo() {
        if (contadorVisitantes >= LIMITE_MANTENIMIENTO && estado == EstadoActual.ACTIVA) {
            estado       = EstadoActual.EN_MANTENIMIENTO;
            motivoCierre = MotivoCierre.REVISION_TECNICA;
        }
    }

    public boolean requiereCierreClimatico() {
        return estado == EstadoActual.ACTIVA
                && (tipo == TipoAtraccion.ACUATICA || tipo == TipoAtraccion.MECANICA_ALTURA);
    }

    public void cerrarPorClima() {
        estado = EstadoActual.CERRADA;
        motivoCierre = MotivoCierre.CLIMA;
    }

    public void incrementarContador() {
        contadorVisitantes++;
        actualizarTiempoEspera();
        verificarMantenimientoPreventivo();
    }

    private void actualizarTiempoEspera() {
        int enCola = colaVirtual.getTotalEnCola();
        tiempoEspera = Math.max(2, (enCola / Math.max(1, capacidadMaxima)) * 5 + 3);
    }
    public int calcularTiempoEspera() {
        return tiempoEspera; }

    public String registrarIngreso(Visitante visitante) {
        if (!verificarAcceso(visitante))
            return "Acceso denegado: atraccion no disponible estatura o edad insuficiente.";

        if (costoAdicional > 0 && !visitante.tieneFastPass()) {
            if (!visitante.descontarSaldo(costoAdicional))
                return "Saldo insuficiente para el costo adicional ($" + costoAdicional + ").";
        }

        boolean esFast = visitante.tieneFastPass();
        colaVirtual.agregarVisitante(visitante, esFast);
        incrementarContador();

        return "Acceso autorizado" + (esFast ? " [FAST-PASS]" : "")
                + ". Tiempo de espera aprox: " + calcularTiempoEspera() + " min.";
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoAtraccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtraccion tipo) {
        this.tipo = tipo;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public double getAlturaMinima() {
        return alturaMinima;
    }

    public void setAlturaMinima(double alturaMinima) {
        this.alturaMinima = alturaMinima;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public void setCostoAdicional(double costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    public int getContadorVisitantes() {
        return contadorVisitantes;
    }

    public void setContadorVisitantes(int contadorVisitantes) {
        this.contadorVisitantes = contadorVisitantes;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public void setEstado(EstadoActual estado) {
        this.estado = estado;
    }

    public MotivoCierre getMotivoCierre() {
        return motivoCierre;
    }

    public void setMotivoCierre(MotivoCierre motivoCierre) {
        this.motivoCierre = motivoCierre;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public ColaVirtual getColaVirtual() {
        return colaVirtual;
    }

    public void setColaVirtual(ColaVirtual colaVirtual) {
        this.colaVirtual = colaVirtual;
    }

    public List<RevisionTecnica> getRevisiones() {
        return revisiones;
    }

    public void setRevisiones(List<RevisionTecnica> revisiones) {
        this.revisiones = revisiones;
    }
}


