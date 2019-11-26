package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
public class TicketMgr {
    RestTemplate rest = new RestTemplate();

    public void save(Ticket ticket){

        rest.postForObject("http://localhost:8080/ticket", ticket, Ticket.class);
    }

    public void update (@PathVariable("id") Integer id, Ticket ticket){
        ticket.setId(id);
        rest.postForObject("http://localhost:8080/ticket", ticket, Ticket.class);
    }


    public List<Ticket> getAllTickets(){
        return rest.exchange("http://localhost:8080/tickets", HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>() {}).getBody();
    }


    public Ticket getTicketById(@PathVariable(value = "id") Integer id) {
        return rest.getForObject("http://localhost:8080/ticket/{id}", Ticket.class, id);
    }


    //polemico
    public List<Ticket> getTicketByUsuario(@PathVariable(value = "usuario")Usuario usuario){
        return rest.getForObject("http://localhost:8080/ticket/{usuario}", List.class, usuario);
    }

    public void addTicket(Funcion funcion, Usuario usuario, String asientos, Integer precio) throws InformacionInvalida, YaExiste, IOException {
        if(funcion== null || usuario == null ||  asientos == null ||  precio ==null ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }
        Ticket ticket = new Ticket(funcion,usuario,asientos,precio);
        save(ticket);
    }
}
