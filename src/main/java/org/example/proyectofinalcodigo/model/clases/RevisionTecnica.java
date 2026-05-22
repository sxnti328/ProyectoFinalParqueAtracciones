package org.example.proyectofinalcodigo.model.clases;

import java.time.LocalDate;

public class RevisionTecnica {

        private final String idRevision;
        private final String idAtraccion;
        private final String idOperador;
        private final LocalDate fecha;
        private final String descripcion;
        private boolean aprobada;

        public RevisionTecnica(String idRevision, String idAtraccion,
                               String idOperador, String descripcion) {
            this.idRevision = idRevision;
            this.idAtraccion = idAtraccion;
            this.idOperador  = idOperador;
            this.descripcion = descripcion;
            this.fecha  = LocalDate.now();
            this.aprobada  = true;
        }

        public String getIdRevision()  {
            return idRevision;
        }
        public String getIdAtraccion() {
            return idAtraccion;
        }
        public String getIdOperador()  {
            return idOperador;
        }
        public LocalDate getFecha()    {
            return fecha;
        }
        public String getDescripcion() {
            return descripcion;
        }
        public boolean isAprobada()    {
            return aprobada;
        }
        public void setAprobada(boolean aprobada) {
            this.aprobada = aprobada; }

        @Override
        public String toString() {
            return "Revision[" + idRevision + "] atraccion=" + idAtraccion
                    + " operador=" + idOperador + " fecha=" + fecha;
        }
    }

