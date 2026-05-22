package org.example.proyectofinalcodigo.model.interfaces;

import org.example.proyectofinalcodigo.model.clases.Visitante;
import org.example.proyectofinalcodigo.model.enums.EstadoActual;
    public interface IAccesible {
        boolean verificarAcceso(Visitante visitante);
        double getCostoAdicional();
        EstadoActual getEstado();

        String registrarIngreso(Visitante visitante);
    }

