package org.example.proyectofinalcodigo.model.records;

import java.time.LocalDate;

public record Notificacion(String tipo, String mensaje, LocalDate fecha) {

    @Override
    public String toString() {

        return "[" + tipo + "] " + mensaje + " (" + fecha + ")";
    }
}
