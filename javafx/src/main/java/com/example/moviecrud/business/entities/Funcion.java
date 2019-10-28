package com.example.moviecrud.business.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private LocalDate fechaInicio;

    @NotBlank
    @Column(nullable = false)
    private LocalDate fechaFinal;

    @NotBlank
    @Column(nullable = false)
    private Time horaFuncion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSala")
    private Sala sala;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

}
