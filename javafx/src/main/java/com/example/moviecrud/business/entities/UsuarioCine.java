package com.example.moviecrud.business.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cine")

public class UsuarioCine extends Usuario{
    public UsuarioCine (String username, String password, String email){
        super(username, password, email);
    }
    public UsuarioCine(){
    }
}
