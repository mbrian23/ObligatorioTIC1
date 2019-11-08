package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Cine;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class SalaManager {

    RestTemplate rest = new RestTemplate();

    public void save(Sala sala){
        rest.postForObject("http://localhost:8080/sala", sala, Sala.class);
    }

    public Sala getSalaByNumSala(Long numSala) {
        return rest.getForObject("http://localhost:8080/sala/{num}", Sala.class);
    }


    public void addSala(String tipo, Long nroSala, Long columnas, Long filas) throws YaExiste, IOException, InformacionInvalida {
        if(tipo == null || "".equals(tipo) || nroSala == null || "".equals(nroSala) || columnas == null || "".equals(columnas) || filas == null || "".equals(filas) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        // Ahora hay que ver si la sala existe ya

        Sala sala = new Sala(filas, columnas, tipo, nroSala);
        save(sala);
    }

    public List<Sala> getAllSalas(){
        return (List<Sala>) rest.exchange("http://localhost:8080/salas", HttpMethod.GET, null, new ParameterizedTypeReference<List<Sala>>() {}).getBody();
    }
}
