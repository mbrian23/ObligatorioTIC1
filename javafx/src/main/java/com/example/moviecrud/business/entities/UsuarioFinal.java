package com.example.moviecrud.business.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("clienteFinal")
public class UsuarioFinal extends Usuario {
    public UsuarioFinal (String username, String password, String email){
        super(username, password, email);
    }
    public UsuarioFinal(){
    }
}
