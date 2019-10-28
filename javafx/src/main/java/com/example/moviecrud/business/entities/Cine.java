package com.example.moviecrud.business.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Cine {

    @Id
    @NotBlank
    @Column(name = "nombre_cine",nullable = false, length = 30)
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cine(@NotBlank String nombre) {
        this.nombre = nombre;
    }
}
