package com.example.moviecrud.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Local {

    private String name;

    private Cine cine;

    public Local(String name) {
        this.name = name;
    }

    public Local() {
    }

    private String nCine;

    public String getnCine() {
        return nCine;
    }

    public void setnCine(String nCine) {
        this.nCine = nCine;
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

    public Local(String name, Cine cine) {
        this.name = name;
        this.cine = cine;
    }
}
