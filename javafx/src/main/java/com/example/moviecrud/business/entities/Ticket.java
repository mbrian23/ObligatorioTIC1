package com.example.moviecrud.business.entities;

import java.util.List;

public class Ticket {

    private Integer id;

    private Funcion funcion;

    private Usuario usuario;

    private List<String> asientos;

    private int precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<String> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<String> asientos) {
        this.asientos = asientos;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Ticket(Integer id, Funcion funcion, Usuario usuario, List<String> asientos, int precio) {
        this.id = id;
        this.funcion = funcion;
        this.usuario = usuario;
        this.asientos = asientos;
        this.precio = precio;
    }

    public Ticket() {
    }
}
