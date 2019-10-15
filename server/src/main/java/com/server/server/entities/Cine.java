package com.server.server.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Cine {

    @Id
    @Column(name = "idCine")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String nombre;
}
