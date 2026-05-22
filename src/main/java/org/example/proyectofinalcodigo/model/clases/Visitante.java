package org.example.proyectofinalcodigo.model.clases;
import org.example.proyectofinalcodigo.model.clasesAbstractas.Persona;
import org.example.proyectofinalcodigo.model.clasesAbstractas.Ticket;
import org.example.proyectofinalcodigo.model.record.Notificacion;

import java.util.ArrayList;

public class Visitante extends Persona {

    private double estatura;
    private double saldoVirtual;
    private String telefono;
    private String direccion;
    private ArrayList<Ticket>  listTickets;
    private ArrayList<String> listaFavoritas;
    private ArrayList<Notificacion> listNotificaciones;

    public Visitante(String nombre, String documento, int edad,
                     double estatura, double saldoVirtual) {
        super(nombre, documento, edad);
        this.estatura = estatura;
        this.saldoVirtual = saldoVirtual;
        this.telefono = "";
        this.direccion = "";
        this.listTickets = new ArrayList<>();
        this.listaFavoritas  = new ArrayList<>();
        this.listNotificaciones = new ArrayList<>();
    }

    public Visitante(String nombre, String documento, int edad,
                     double estatura, double saldoVirtual,
                     String telefono, String direccion) {
        this(nombre, documento, edad, estatura, saldoVirtual);
        this.telefono  = telefono;
        this.direccion = direccion;
    }

    public boolean comprarTicket(Ticket ticket) {
        double precio = ticket.getPrecioFinal();
        if (saldoVirtual < precio) return false;
        saldoVirtual -= precio;
        listTickets.add(ticket);
        return true;
    }

    public boolean descontarSaldo(double monto) {
        if (saldoVirtual < monto) return false;
        saldoVirtual -= monto;
        return true;
    }

    public void recargarSaldo(double monto) {
        if (monto > 0) {
            saldoVirtual += monto;
        }
    }

    public Ticket getTicketActivo() {
        for (Ticket t : listTickets)
            if (t.isActivo())
                return t;
        return null;
    }

    public boolean tieneFastPass() {
        return getTicketActivo() instanceof TicketFastPass;
    }

    public void agregarFavorita(String idAtraccion) {
        if (!listaFavoritas.contains(idAtraccion))
            listaFavoritas.add(idAtraccion);
    }

    public void eliminarFavorita(String idAtraccion) {
        listaFavoritas.remove(idAtraccion);
    }

    public boolean tieneFavorita(String idAtraccion) {
        return listaFavoritas.contains(idAtraccion);
    }

    public void agregarNotificacion(Notificacion n) {
        listNotificaciones.add(n);
    }

    public double getEstatura()    {
        return estatura;
    }
    public void   setEstatura(double e) {
        this.estatura = e;
    }
    public double getSaldoVirtual() {
        return saldoVirtual;
    }
    public void   setSaldoVirtual(double s) {
        this.saldoVirtual = s;
    }
    public String getTelefono()  {
        return telefono;
    }
    public void   setTelefono(String t) {
        this.telefono = t;
    }
    public String getDireccion() {
        return direccion;
    }
    public void   setDireccion(String d){
        this.direccion = d; }
    public ArrayList<Ticket>       getListTickets()        {
        return listTickets; }
    public ArrayList<String>       getListaFavoritas()     {
        return listaFavoritas; }
    public ArrayList<Notificacion> getListNotificaciones() {
        return listNotificaciones; }

    @Override
    public String toString() {
        return super.toString() + "  Estatura: " + estatura + " Saldo: $" + saldoVirtual;
    }
}