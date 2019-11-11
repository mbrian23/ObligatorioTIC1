package com.example.moviecrud.business.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class UsuarioAdmin extends Usuario{
    public UsuarioAdmin (String username, String password, String email){
        super(username, password, email);
    }
    public UsuarioAdmin(){
    }
}
