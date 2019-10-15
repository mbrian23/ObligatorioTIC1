package com.example.moviecrud.business.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Cine {

    @Id
    @Column(name = "idCine")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String nombre;
}
