package com.example.moviecrud.business.entities;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class Ticket {

    private Integer id;

    private Funcion funcion;

    private Usuario usuario;

    private String asientos;

    private int precio;

    private boolean editando = false;
    private Integer idTemp;
    private String titViejo;


    public LocalDate getFechaFuncion (){
        return funcion.getFecha();
    }

    public Time getHoraFuncion (){
        return funcion.getHoraFuncion();
    }

    public Long getNumeroSalaFuncion() {
        if (funcion.getSala().getNumeroSala() != null){
            return funcion.getSala().getNumeroSala();
        }
        return null;
    }
    public String getPeliculaFuncion (){
        return this.funcion.getPelicula().getTitulo();
    }

    public String getLocalFuncion (){
        return this.funcion.getLocal().getName();
    }
    public String getUsernameUsuario (){
        return this.usuario.getUsername();
    }




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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public Ticket(Integer id, Funcion funcion, Usuario usuario, String asientos, int precio) {
        this.id = id;
        this.funcion = funcion;
        this.usuario = usuario;
        this.asientos = asientos;
        this.precio = precio;
    }

    public Ticket(Funcion funcion, Usuario usuario, String asientos, int precio) {
        this.funcion = funcion;
        this.usuario = usuario;
        this.asientos = asientos;
        this.precio = precio;
    }

    public Ticket() {
    }

    public Ticket(Funcion funcion, Usuario usuario, String asientos) {
        this.funcion = funcion;
        this.usuario = usuario;
        this.asientos = asientos;
    }
}
