package com.example.moviecrud.business.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class Sala {


    private Long id;

    private Long filas;

    private Long columnas;

    private String tipo;

    private Long numeroSala;

    private Local local;

    public Sala(Long filas, Long columnas, String tipo, Long numeroSala) {
        this.filas = filas;
        this.columnas = columnas;
        this.tipo = tipo;
        this.numeroSala = numeroSala;
        this.matriz = matriz(filas.intValue(), columnas.intValue());
    }
    private boolean [][] matriz;

    public boolean[][] matriz(int fila,int columna){
        return new boolean[fila][columna];
    }

    public Sala() {
    }

//    public Sala( Long filas ,Long columnas, String tipo,Long numeroSala) {
//        this.filas = filas;
//        this.columnas = columnas;
//        this.tipo = tipo;
//        this.numeroSala = numeroSala;
//        this.matriz = matriz(filas.intValue(), columnas.intValue());
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Long getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Long numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Long getFilas() {
        return filas;
    }

    public void setFilas(Long filas) {
        this.filas = filas;
    }

    public Long getColumnas() {
        return columnas;
    }

    public void setColumnas(Long columnas) {
        this.columnas = columnas;
    }





}
