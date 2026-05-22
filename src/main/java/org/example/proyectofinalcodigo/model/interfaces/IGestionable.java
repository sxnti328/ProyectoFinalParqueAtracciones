package org.example.proyectofinalcodigo.model.interfaces;

public interface IGestionable {
    public void agregar(Object elemento);
    public boolean eliminar(String id);
    public int getTotalElementos();
}