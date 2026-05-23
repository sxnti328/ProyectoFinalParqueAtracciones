package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.enums.EstadoActual;
import org.example.proyectofinalcodigo.model.interfaces.IAccesible;
import org.example.proyectofinalcodigo.model.interfaces.IGestionable;
import org.example.proyectofinalcodigo.model.records.Notificacion;

import java.time.LocalDate;
import java.util.ArrayList;

public class Zona implements IGestionable, IAccesible {

    private String  idZona;
    private String nombre;
    private String  descripcion;
    private int capacidadMax;
    private int  visitantesActuales;
    private EstadoActual estadoZona;
    private ArrayList<Atraccion> listAtraccion;
    private ArrayList<Operador> listOperador;

    public Zona(String idZona, String nombre, String descripcion, int capacidadMax) {
        this.idZona  = idZona;
        this.nombre = nombre;
        this.descripcion  = descripcion;
        this.capacidadMax = capacidadMax;
        this.visitantesActuales = 0;
        this.estadoZona = EstadoActual.ACTIVA;
        this.listAtraccion = new ArrayList<>();
        this.listOperador = new ArrayList<>();
    }



    @Override
    public void agregar(Object elemento) {
        if (elemento instanceof Atraccion a) {
            agregarAtraccion(a);
        } else if (elemento instanceof Operador o) {
            agregarOperador(o);
        }
    }

    @Override
    public boolean eliminar(String id) {
        boolean r1 = listAtraccion.removeIf(a -> a.getId().equals(id));
        boolean r2 = listOperador.removeIf(o -> o.getIdEmpleado().equals(id));
        return r1 || r2;
    }

    @Override
    public int getTotalElementos() {
        return listAtraccion.size() + listOperador.size();
    }



    @Override
    public boolean verificarAcceso(Visitante v) {
        return estadoZona == EstadoActual.ACTIVA && !estaLlena();
    }

    @Override
    public double getCostoAdicional() {
        return 0.0;
    }

    @Override
    public EstadoActual getEstado() {
        return estadoZona;
    }

    @Override
    public String registrarIngreso(Visitante v) {
        if (!verificarAcceso(v))
            return "Acceso a zona '" + nombre + "' denegado: zona llena o cerrada.";
        visitantesActuales++;
        return "Ingreso a zona '" + nombre + "' registrado. Ocupacion: "
                + visitantesActuales + "" + capacidadMax;
    }


    public void agregarAtraccion(Atraccion atraccion) {
        atraccion.setZona(this);
        listAtraccion.add(atraccion);
    }

    public void agregarOperador(Operador operador) {
        operador.setIdZona(idZona);
        listOperador.add(operador);
    }

    public Atraccion buscarAtraccion(String id) {
        for (Atraccion a : listAtraccion)
            if (a.getId().equals(id)) return a;
        return null;
    }

    public Operador buscarOperador(String idEmpleado) {
        for (Operador o : listOperador)
            if (o.getIdEmpleado().equals(idEmpleado)) return o;
        return null;
    }

    public boolean tieneOperadores() {
        return !listOperador.isEmpty();
    }

    public boolean estaLlena() {
        return visitantesActuales >= capacidadMax;
    }

    public Notificacion activarAlertaClimatica() {
        int cerradas = 0;
        for (Atraccion a : listAtraccion) {
            if (a.requiereCierreClimatico()) {
                a.cerrarPorClima();
                cerradas++;
            }
        }
        if (cerradas > 0)
            return new Notificacion("CLIMA",
                    cerradas + " atracciones cerradas en zona " + nombre + " por alerta climatica.", LocalDate.now());
        return null;
    }

    public void desactivarAlertaClimatica() {
        for (Atraccion a : listAtraccion)
            if (a.getMotivoCierre() != null
                    && a.getMotivoCierre().name().equals("CLIMA")) {
                a.setEstado(EstadoActual.ACTIVA);
                a.setMotivoCierre(null);
            }
    }



    public String getIdZona()  { return idZona; }
    public void setIdZona(String idZona)  { this.idZona = idZona; }

    public String getNombre()   { return nombre; }
    public void setNombre(String nombre)   { this.nombre = nombre; }

    public String getDescripcion()  { return descripcion; }
    public void setDescripcion(String d)   { this.descripcion = d; }

    public int getCapacidadMax()  { return capacidadMax; }
    public void setCapacidadMax(int c)  { this.capacidadMax = c; }

    public int getVisitantesActuales() { return visitantesActuales; }
    public void setVisitantesActuales(int v)  { this.visitantesActuales = v; }

    public EstadoActual getEstadoZona()  { return estadoZona; }
    public void  setEstadoZona(EstadoActual e)  { this.estadoZona = e; }

    public ArrayList<Atraccion> getListAtraccion()  { return listAtraccion; }
    public void setListAtraccion(ArrayList<Atraccion> l) { this.listAtraccion = l; }

    public ArrayList<Operador>  getListOperador() { return listOperador; }
    public void setListOperador(ArrayList<Operador> l)   { this.listOperador = l; }

    @Override
    public String toString() {
        return nombre + "  Capacidad: " + visitantesActuales + "/" + capacidadMax
                + "  Atracciones: " + listAtraccion.size()
                + "  Operadores: " + listOperador.size();
    }
}
