package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.interfaces.IGestionable;

import java.util.ArrayList;
import java.util.List;

public class ParqueDeAtraccion {
    private String nombre;
    private String nit;
    private String direccion;
    private int capacidadMax;
    private int visitantesActuales;
    private ArrayList<Visitante> listVisitante;
    private ArrayList<Administrador> listAdmin;
    private ArrayList<Operador> listOperador;
    private ArrayList<Zona> listZona;
    private GestorReportes gestorReportes;

    public ParqueDeAtraccion(String nombre, String nit, String direccion, int capacidadMax) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.capacidadMax = capacidadMax;
        this.visitantesActuales = 0;
        this.listVisitante = new ArrayList<>();
        this.listAdmin = new ArrayList<>();
        this.listOperador = new ArrayList<>();
        this.listZona = new ArrayList<>();
        this.gestorReportes = new GestorReportes(this);
    }



    public boolean agregarVisitante(Visitante v) {
        if (visitantesActuales >= capacidadMax) return false;
        if (buscarVisitante(v.getDocumento()) != null) return false;
        listVisitante.add(v);
        visitantesActuales++;
        return true;
    }

    public Visitante buscarVisitante(String documento) {
        for (Visitante v : listVisitante)
            if (v.getDocumento().equals(documento)) return v;
        return null;
    }

    public boolean actualizarVisitante(String documento, String nombre, int edad,
                                       double estatura, String telefono, String direccion) {
        Visitante v = buscarVisitante(documento);
        if (v == null) return false;
        v.setNombre(nombre);
        v.setEdad(edad);
        v.setEstatura(estatura);
        v.setTelefono(telefono);
        v.setDireccion(direccion);
        return true;
    }

    public boolean eliminarVisitante(String documento) {
        Visitante v = buscarVisitante(documento);
        if (v == null) return false;
        listVisitante.remove(v);
        visitantesActuales--;
        return true;
    }


    public boolean agregarAdministrador(Administrador a) {
        if (buscarAdministrador(a.getDocumento()) != null) return false;
        listAdmin.add(a);
        return true;
    }

    public Administrador buscarAdministrador(String documento) {
        for (Administrador a : listAdmin)
            if (a.getDocumento().equals(documento)) return a;
        return null;
    }

    public boolean eliminarAdministrador(String documento) {
        Administrador a = buscarAdministrador(documento);
        if (a == null) return false;
        listAdmin.remove(a);
        return true;
    }


    public boolean agregarOperador(Operador o) {
        if (buscarOperador(o.getDocumento()) != null) return false;
        listOperador.add(o);
        return true;
    }

    public Operador buscarOperador(String documento) {
        for (Operador o : listOperador)
            if (o.getDocumento().equals(documento)) return o;
        return null;
    }

    public Operador buscarOperadorPorId(String idEmpleado) {
        for (Operador o : listOperador)
            if (o.getIdEmpleado().equals(idEmpleado)) return o;
        return null;
    }

    public boolean eliminarOperador(String documento) {
        Operador o = buscarOperador(documento);
        if (o == null) return false;
        listOperador.remove(o);
        // remove from zone too
        for (Zona z : listZona)
            z.eliminar(o.getIdEmpleado());
        return true;
    }

    public boolean asignarOperadorAZona(String idEmpleado, String idZona) {
        Operador o = buscarOperadorPorId(idEmpleado);
        IGestionable zona = buscarZona(idZona);  // Zona es IGestionable, no vaya a mover esta vaina que no corre
        if (o == null || zona == null) return false;
        for (Zona z : listZona)
            z.getListOperador().removeIf(op -> op.getIdEmpleado().equals(idEmpleado));
        zona.agregar(o);  // delega en el contrato IGestionable
        o.setIdZona(idZona);
        return true;
    }

    public boolean agregarZona(Zona z) {
        if (buscarZona(z.getIdZona()) != null) return false;
        listZona.add(z);
        return true;
    }

    public Zona buscarZona(String idZona) {
        for (Zona z : listZona)
            if (z.getIdZona().equals(idZona)) return z;
        return null;
    }

    public boolean eliminarZona(String idZona) {
        Zona z = buscarZona(idZona);
        if (z == null) return false;
        listZona.remove(z);
        return true;
    }

    public boolean agregarAtraccionAZona(String idZona, Atraccion a) {
        IGestionable zona = buscarZona(idZona);
        if (zona == null) return false;
        zona.agregar(a);
        return true;
    }

    public Atraccion buscarAtraccion(String idAtraccion) {
        for (Zona z : listZona) {
            Atraccion a = z.buscarAtraccion(idAtraccion);
            if (a != null) return a;
        }
        return null;
    }

    // Usa IGestionable para eliminar
    public boolean eliminarAtraccionDeZona(String idZona, String idAtraccion) {
        IGestionable zona = buscarZona(idZona);
        if (zona == null) return false;
        return zona.eliminar(idAtraccion);
    }

    public List<Atraccion> getTodasLasAtracciones() {
        List<Atraccion> todas = new ArrayList<>();
        for (Zona z : listZona)
            todas.addAll(z.getListAtraccion());
        return todas;
    }




    //GETTERS Y SETTER (te amo auto generar de intelliJ)
    public String getNombre() {
        return nombre;
    }
    public void   setNombre(String n)  {
        this.nombre = n;
    }
    public String getNit() {
        return nit;
    }
    public void   setNit(String n)  {
        this.nit = n; }
    public String getDireccion()
    {
        return direccion; }
    public void   setDireccion(String d) {
        this.direccion = d; }
    public int    getCapacidadMax()  {
        return capacidadMax; }
    public void   setCapacidadMax(int c)  { this.capacidadMax = c; }
    public int    getVisitantesActuales() { return visitantesActuales; }

    public ArrayList<Visitante>     getListVisitante() {
        return listVisitante; }
    public ArrayList<Administrador> getListAdmin()     {
        return listAdmin; }
    public ArrayList<Operador>      getListOperador()  {
        return listOperador;
    }
    public ArrayList<Zona>          getListZona()  {
        return listZona; }

    public void setListVisitante(ArrayList<Visitante> l)  {
        this.listVisitante = l; }
    public void setListAdmin(ArrayList<Administrador> l)    {
        this.listAdmin = l; }
    public void setListZona(ArrayList<Zona> l)               { this.listZona = l; }

    @Override
    public String toString() {
        return "ParqueDeAtraccion: " + nombre + " | NIT: " + nit
                + "  Visitantes: " + visitantesActuales + "/" + capacidadMax
                + "  Zonas: " + listZona.size();
    }
}









