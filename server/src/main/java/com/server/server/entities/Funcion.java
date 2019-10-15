package com.server.server.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table
public class Funcion {

    @Id
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private Date fechaHora;

    @ManyToOne
    @JoinColumn(name = "idSala")
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

}
