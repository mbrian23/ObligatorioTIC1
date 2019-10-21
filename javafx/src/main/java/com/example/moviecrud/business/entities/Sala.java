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

    public String getnLugares() {
        return nLugares;
    }

    public void setnLugares(String nLugares) {
        this.nLugares = nLugares;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }
}
