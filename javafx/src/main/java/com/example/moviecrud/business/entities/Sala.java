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
    private Long numeroSala;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String tipo;

    @NotNull
    @Column(nullable = false)
    private Long nLugares;

    @ManyToOne
    @JoinColumn(name = "nombre_local")
    private Locales local;

    public Sala(Long numeroSala, @NotBlank String tipo, @NotNull Long nLugares) {
        this.numeroSala = numeroSala;
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

    public Long getnLugares() {
        return nLugares;
    }

    public void setnLugares(Long nLugares) {
        this.nLugares = nLugares;
    }

    public Locales getLocal() {
        return local;
    }

    public void setLocal(Locales local) {
        this.local = local;
    }

    public Long getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Long numeroSala) {
        this.numeroSala = numeroSala;
    }
}
