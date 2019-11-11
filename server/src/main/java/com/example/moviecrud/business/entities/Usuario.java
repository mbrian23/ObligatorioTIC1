package com.example.moviecrud.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table (name = "usuario")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)

public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String username;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String password;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String email;

    public Usuario(@NotBlank String username, @NotBlank String password, @NotBlank String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Usuario(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
