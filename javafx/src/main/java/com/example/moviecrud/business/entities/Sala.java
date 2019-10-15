package com.example.moviecrud.business.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Sala {

    @Id()
    @Column(name = "idSala")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idCine")
    private Cine cine;

}
