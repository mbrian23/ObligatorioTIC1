package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.business.exceptions.InformacionPeliculaInvalida;
import com.example.moviecrud.business.exceptions.InformacionSalaInvalida;
import com.example.moviecrud.business.exceptions.PeliculaYaExiste;
import com.example.moviecrud.business.exceptions.SalaYaExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class SalaManager {
    RestTemplate rest = new RestTemplate();


    public void save( Sala sala){
        save(rest.postForObject("http://localhost:8080/sala", sala, Sala.class));;
    }

    public void addSala(String tipo, Long nroSala, String nroLugares) throws  SalaYaExiste, IOException, InformacionSalaInvalida {
        if(tipo == null || "".equals(tipo) || nroSala == null || "".equals(nroSala) || nroLugares == null || "".equals(nroLugares) ){

            throw new InformacionSalaInvalida("Algun dato ingresado no es correcto");

        }



        // Ahora hay que ver si la sala existe ya

        Sala sala = new Sala(nroSala, tipo, nroLugares);
        save(sala);
    }
}
