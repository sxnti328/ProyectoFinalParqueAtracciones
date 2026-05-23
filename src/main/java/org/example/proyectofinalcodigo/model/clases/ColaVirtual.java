package org.example.proyectofinalcodigo.model.clases;

import org.example.proyectofinalcodigo.model.interfaces.IGestionable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ColaVirtual implements IGestionable {

    private final Queue<Visitante> colaGeneral;
    private final Queue<Visitante> colaFastPass;

    public ColaVirtual() {
        this.colaGeneral  = new LinkedList<>();
        this.colaFastPass = new LinkedList<>();
    }

    @Override
    public void agregar(Object elemento) {
        if (elemento instanceof Visitante v) {
            agregarVisitante(v, v.tieneFastPass());
        }
    }

    @Override
    public boolean eliminar(String documento) {
        boolean r1 = colaGeneral.removeIf(v -> v.getDocumento().equals(documento));
        boolean r2 = colaFastPass.removeIf(v -> v.getDocumento().equals(documento));
        return r1 || r2;
    }

    @Override
    public int getTotalElementos() {
        return getTotalEnCola();
    }

    public void agregarVisitante(Visitante visitante, boolean esFastPass) {
        if (esFastPass) colaFastPass.offer(visitante);
        else            colaGeneral.offer(visitante);
    }

    public Visitante siguienteEnCola() {
        if (!colaFastPass.isEmpty()) return colaFastPass.poll();
        return colaGeneral.poll();
    }

    public int getTotalEnCola() {
        return colaGeneral.size() + colaFastPass.size();
    }

    public int getCantidadGeneral()   {
        return colaGeneral.size();
    }

    public int getCantidadFastPass()  {
        return colaFastPass.size();
    }

    public List<Visitante> verColaGeneral()  {
        return new ArrayList<>(colaGeneral);
    }
    public List<Visitante> verColaFastPass() {
        return new ArrayList<>(colaFastPass);
    }

    public void limpiarCola() {
        colaGeneral.clear();
        colaFastPass.clear();
    }

    @Override
    public String toString() {
        return "ColaGeneral=" + colaGeneral.size() + " | FastPass=" + colaFastPass.size() + "]";
    }
}
