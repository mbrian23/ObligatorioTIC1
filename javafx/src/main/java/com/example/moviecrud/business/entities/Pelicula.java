package com.example.moviecrud.business.entities;



import java.sql.Blob;


public class Pelicula {


    private Long id;


    private String titulo;


    private String genero;


    private String actores;


    private String duracion;


    private String descripcion;



    private byte[] movieImage;


    public Pelicula(){

    }

    public Pelicula(String titulo, String genero, String actores, String duracion, String descripcion) {
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