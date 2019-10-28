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
    private String tipo;

    @NotBlank
    @Column(nullable = false)
    private String nLugares;

    @ManyToOne
    @JoinColumn(name = "idCine")
    private Cine cine;

    public Sala(Long id, @NotBlank String tipo, @NotBlank String nLugares) {
        this.id = id;
        this.tipo = tipo;
        this.nLugares = nLugares;
    }

    public Sala() {
    }
}
