package com.example.moviecrud.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idFuncion")
    private Funcion funcion;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private String asientos;

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

    public String getAsientos() {
        return asientos;
    }


    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Ticket(Funcion funcion, Usuario usuario, String asientos, int precio) {
        this.funcion = funcion;
        this.usuario = usuario;
        this.asientos = asientos;
        this.precio = precio;
    }

    public Ticket(Funcion funcion, Usuario usuario, String asientos) {
        this.funcion = funcion;
        this.usuario = usuario;
        this.asientos = asientos;
    }

    public Ticket() {
    }

    public LocalDate getFechaFuncion (){
        return funcion.getFecha();
    }

    public Time getHoraFuncion (){
        return funcion.getHoraFuncion();
    }

    public Long getNumeroSalaFuncion() {
        if (this.funcion.getSala().getNumeroSala() != null){
            return this.funcion.getSala().getNumeroSala();
        }
        return 3l;
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
}
