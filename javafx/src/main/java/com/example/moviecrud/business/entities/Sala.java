package com.example.moviecrud.business.entities;




public class Sala {


    private Long id;


    private String tipo;


    private String nLugares;


    private Cine cine;

    public Sala(Long id, String tipo, String nLugares) {
        this.id = id;
        this.tipo = tipo;
        this.nLugares = nLugares;
    }

    public Sala() {
    }
}
