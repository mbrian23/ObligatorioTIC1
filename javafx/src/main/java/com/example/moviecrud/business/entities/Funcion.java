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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_Local")
    private Local local;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcion(@NotBlank LocalDate fechaInicio, @NotBlank LocalDate fechaFinal, @NotBlank Time horaFuncion, Sala sala, Pelicula pelicula) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.horaFuncion = horaFuncion;
        this.sala = sala;
        this.pelicula = pelicula;
    }
}
