package com.example.moviecrud.business.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Blob;


@Entity
@Table
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPelicula")
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
    @Column(nullable = false, length = 255)
    private String descripcion;


    @Lob
    @Column(nullable = false)
    @NotNull
    private byte[] movieImage;


    public Pelicula(){

    }

    public Pelicula(@NotBlank String titulo, @NotBlank String genero, @NotBlank String actores, @NotBlank String duracion, @NotBlank String descripcion) {
        this.titulo = titulo;
        this.genero = genero;
        this.actores = actores;
        this.duracion = duracion;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public byte[] getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(byte[] movieImage) {
        this.movieImage = movieImage;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}