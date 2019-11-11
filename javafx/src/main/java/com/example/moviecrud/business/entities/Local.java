package com.example.moviecrud.business.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class Local {


    @NotNull
    private String name;


    private Cine cine;

    public Local(@NotNull String name) {
        this.name = name;
    }

    public Local() {
    }



    public String getNombreCine() {
        return cine.getNombre();
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

    public Local(@NotNull String name, @NotBlank Cine cine) {
        this.name = name;
        this.cine = cine;
    }
}
