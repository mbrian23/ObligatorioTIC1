package com.example.moviecrud.business.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.Type;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String username;

    @NotBlank
    @Column(nullable = false, length = 60)
    private String password;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String adminPrivileges;

    public Usuario(@NotBlank String username, @NotBlank String password, @NotBlank String email, @NotBlank String adminPrivileges) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.email = email;
        this.adminPrivileges = adminPrivileges;
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
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminPrivileges() {
        return adminPrivileges;
    }

    public void setAdminPrivileges(String adminPrivileges) {
        this.adminPrivileges = adminPrivileges;
    }
}
