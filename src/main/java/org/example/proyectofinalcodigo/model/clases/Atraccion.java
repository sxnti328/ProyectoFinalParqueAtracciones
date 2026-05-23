package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.enums.MotivoCierre;
import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;
import org.example.proyectofinalcodigo.model.interfaces.IAccesible;
import org.example.proyectofinalcodigo.model.interfaces.IGestionable;
import org.example.proyectofinalcodigo.model.records.Notificacion;

import java.time.LocalDate;
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
    private int  contadorVisitantes;
    private int tiempoEspera;
    private EstadoActual estado;
    private MotivoCierre motivoCierre;
    private Zona zona;
    private IGestionable colaVirtual;
    private List<RevisionTecnica> revisiones;

    public Atraccion(String id, String nombre, TipoAtraccion tipo,
                     int capacidadMaxima, double alturaMinima,
                     int edadMinima, double costoAdicional) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
        this.alturaMinima = alturaMinima;
        this.edadMinima = edadMinima;
        this.costoAdicional = costoAdicional;
        this.contadorVisitantes = 0;
        this.tiempoEspera = 5;
        this.estado = EstadoActual.ACTIVA;
        this.motivoCierre = null;
        this.zona = null;
        this.colaVirtual = new ColaVirtual();
        this.revisiones = new ArrayList<>();
    }

    @Override
    public boolean verificarAcceso(Visitante visitante) {
        if (estado != EstadoActual.ACTIVA)
            return false;
        if (visitante.getEstatura() < alturaMinima)
            return false;
        if (visitante.getEdad() < edadMinima)
            return false;
        return true;
    }

    @Override
    public double getCostoAdicional() {
        return costoAdicional;
    }

    @Override
    public EstadoActual getEstado() {
        return estado;
    }

    @Override
    public String registrarIngreso(Visitante visitante) {
        if (!verificarAcceso(visitante))
            return "Acceso denegado: atraccion no disponible estatura o edad insuficiente.";

        if (costoAdicional > 0 && !visitante.tieneFastPass()) {
            if (!visitante.descontarSaldo(costoAdicional))
                return "Saldo insuficiente para el costo adicional ($" + costoAdicional + ").";
        }
        boolean esFast = visitante.tieneFastPass();
        colaVirtual.agregar(visitante);
        incrementarContador();

        return "Acceso autorizado" + (esFast ? " [FAST-PASS]" : "")
                + ". Tiempo de espera aprox: " + calcularTiempoEspera() + " min.";
    }

    public Notificacion registrarRevisionTecnica(Operador operador, String descripcion) {
        RevisionTecnica r = new RevisionTecnica(
                "REV" + System.currentTimeMillis(),
                this.id,
                operador.getIdEmpleado(),
                descripcion);
        revisiones.add(r);
        estado  = EstadoActual.ACTIVA;
        motivoCierre = null;
        return new Notificacion("REVISION",
                "Atraccion '" + nombre + "' reactivada", LocalDate.now());
    }

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
        estado       = EstadoActual.CERRADA;
        motivoCierre = MotivoCierre.CLIMA;
    }

    public void incrementarContador() {
        contadorVisitantes++;
        actualizarTiempoEspera();
        verificarMantenimientoPreventivo();
    }

    private void actualizarTiempoEspera() {
        int enCola = colaVirtual.getTotalElementos();
        tiempoEspera = Math.max(2, (enCola / Math.max(1, capacidadMaxima)) * 5 + 3);
    }

    public int calcularTiempoEspera() {
        return tiempoEspera;
    }

    public String       getId()            {
        return id;
    }
    public void         setId(String id)   {
        this.id = id;
    }

    public String       getNombre() {
        return nombre;
    }
    public void         setNombre(String nombre) {
        this.nombre = nombre; }

    public TipoAtraccion getTipo() {
        return tipo; }
    public void          setTipo(TipoAtraccion t)  {
        this.tipo = t; }

    public int    getCapacidadMaxima()  { return capacidadMaxima; }
    public void   setCapacidadMaxima(int c)  { this.capacidadMaxima = c; }

    public double getAlturaMinima() { return alturaMinima; }
    public void   setAlturaMinima(double a) { this.alturaMinima = a; }

    public int    getEdadMinima()   { return edadMinima; }
    public void   setEdadMinima(int e)  { this.edadMinima = e; }

    public void   setCostoAdicional(double c)  { this.costoAdicional = c; }

    public int    getContadorVisitantes() {
        return contadorVisitantes;
    }
    public void   setContadorVisitantes(int c) {
        this.contadorVisitantes = c; }

    public int    getTiempoEspera()  {
        return tiempoEspera; }
    public void   setTiempoEspera(int t) {
        this.tiempoEspera = t; }

    public void setEstado(EstadoActual e)  {
        this.estado = e; }

    public MotivoCierre getMotivoCierre()  {
        return motivoCierre; }
    public void         setMotivoCierre(MotivoCierre m) {
        this.motivoCierre = m; }

    public Zona getZona() {
        return zona; }
    public void setZona(Zona zona)  {
        this.zona = zona; }

    public IGestionable getColaVirtual() {
        return colaVirtual; }
    public void         setColaVirtual(IGestionable c) {
        this.colaVirtual = c; }

    public List<RevisionTecnica> getRevisiones() {
        return revisiones; }
    public void                  setRevisiones(List<RevisionTecnica> r) {
        this.revisiones = r; }

    @Override
    public String toString() {
        return nombre + " [" + tipo + "] Estado=" + estado
                + " Visitantes=" + contadorVisitantes
                + " Cola=" + colaVirtual.getTotalElementos()
                + " Espera=" + tiempoEspera + "min";
    }
}
