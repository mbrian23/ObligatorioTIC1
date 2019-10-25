package com.example.moviecrud.business.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private Date fechaHora;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSala")
    private Sala sala;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

}
