package com.example.moviecrud.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Locales {

    @Id()
    @Column(nullable = false, name = "nombre_local")
    @NotNull
    private String name;

    @ManyToOne
    @NotBlank
    @JoinColumn(nullable = false, name = "nombre_cine")
    private Cine cine;

    public Locales(@NotNull String name) {
        this.name = name;
    }

    public Locales() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }
}
