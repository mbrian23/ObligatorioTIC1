package org.hibernate.jpa.entidadesTic.Entity.Pelicula;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String titulo;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String genero;

    @NotBlank
    @Column(nullable = false)
    private String actores;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String duracion;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String Descripcion;
}
