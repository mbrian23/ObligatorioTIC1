package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
public class SalaManager {

    RestTemplate rest = new RestTemplate();

    String ip = InetAddress.getLocalHost().getHostAddress();

    //String ip = "10.252.61.196";

    public SalaManager() throws UnknownHostException {
    }


    public void save( Sala sala){
        rest.postForObject("http://"+ip+":8080/sala", sala, Sala.class);
    }

    public Sala getSalaByNumSala(@PathVariable(value = "numeroSala") Long numeroSala ) {
        return rest.getForObject("http://"+ip+":8080/sala?numeroSala={numeroSala}", Sala.class, numeroSala);
    }


    public void addSala(String tipo, Long nroSala, Long columnas, Long filas, Local local) throws YaExiste, IOException, InformacionInvalida {
        if(tipo == null || "".equals(tipo) || nroSala == null || "".equals(nroSala) || columnas == null || "".equals(columnas) || filas == null || "".equals(filas) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        // Ahora hay que ver si la sala existe ya

        Sala sala = new Sala(filas, columnas, tipo, nroSala, local);
//        sala.setnLocal(local.getName());
        save(sala);
    }

    public List<Sala> getAllSalas(){
        return (List<Sala>) rest.exchange("http://"+ip+":8080/salas", HttpMethod.GET, null, new ParameterizedTypeReference<List<Sala>>() {}).getBody();
    }

    public void update ( Sala sala){
        rest.postForObject("http://"+ip+":8080/sala", sala, Sala.class);
    }

    public Sala findSalaById(@PathVariable(value = "id") Long id){
        return rest.getForObject("http://"+ip+":8080/sala?id={id}", Sala.class, id);
    }
}
