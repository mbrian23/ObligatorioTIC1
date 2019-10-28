package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.business.exceptions.InformacionPeliculaInvalida;
import com.example.moviecrud.business.exceptions.InformacionSalaInvalida;
import com.example.moviecrud.business.exceptions.PeliculaYaExiste;
import com.example.moviecrud.business.exceptions.SalaYaExiste;
import com.example.moviecrud.persistence.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SalaManager {

    @Autowired
    SalaRepository salaRepository;

    public void save( Sala sala){
        salaRepository.save(sala);
    }

    public void addSala(String tipo, Long nroSala, String nroLugares) throws  SalaYaExiste, IOException, InformacionSalaInvalida {
        if(tipo == null || "".equals(tipo) || nroSala == null || "".equals(nroSala) || nroLugares == null || "".equals(nroLugares) ){

            throw new InformacionSalaInvalida("Algun dato ingresado no es correcto");

        }



        // Ahora hay que ver si la sala existe ya

        Sala sala = new Sala(nroSala, tipo, nroLugares);
        salaRepository.save(sala);
    }
}
