package com.example.moviecrud.business.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Sala {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSala")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 30)
    private Long filas;

    @NotNull
    @Column(nullable = false, length = 30)
    private Long columnas;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String tipo;

    @NotNull
    @Column(nullable = false)
    private Long numeroSala;

    @ManyToOne
    @JoinColumn(name = "nombre_local")
    private Local local;

    public Sala(@NotNull Long filas, @NotNull Long columnas, @NotBlank String tipo, @NotNull Long numeroSala) {
        this.filas = filas;
        this.columnas = columnas;
        this.tipo = tipo;
        this.numeroSala = numeroSala;
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
