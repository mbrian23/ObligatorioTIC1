package com.example.moviecrud.business;


import com.example.moviecrud.business.entities.Funcion;
import com.example.moviecrud.business.entities.Ticket;
import com.example.moviecrud.business.entities.Usuario;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.persistence.FuncionRepo;
import com.example.moviecrud.persistence.TicketRepository;
import com.example.moviecrud.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class TicketMgr {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    FuncionRepo funcionRepo;

    // Create user
    @PostMapping("/ticket")
    public void save(@RequestBody Ticket ticket) {
        ticketRepository.save(ticket);
        ticketRepository.count();
    }

    //Edit usuario by id
    @PutMapping("/ticket/{id}")
    public void update(@PathVariable("id") Integer id, Ticket ticket) {
        ticket.setId(id);
        ticketRepository.save(ticket);
    }

    //Get all users
    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    // Get a Single user by id
    @GetMapping("/ticket/{id}")
    public Ticket getTicketById(@PathVariable(value = "id") Integer ticketId) {
        return ticketRepository.findById(ticketId).get();
    }

    @GetMapping("/ticket/usuario/{usuario}")
    public List<Ticket> getTicketByUsuario(@PathVariable(value = "usuario")Usuario usuario) {

        List<Ticket> listaTickets = new LinkedList<Ticket>();
        List<Ticket> todos = (List<Ticket>) ticketRepository.findAll();
        int l = todos.size();
        Usuario temp = null;
        for (int i = 0; i < l; i++) {
            if (todos.get(i).getUsuario().equals(usuario)) {
                listaTickets.add(todos.get(i));
            }
        }

        return listaTickets;
    }



    public void addTicket(Funcion funcion, Usuario usuario, String ascientos, int precio) throws InformacionInvalida, YaExiste, IOException {
        if (funcion == null || usuario == null || ascientos == null || precio==0 ) {

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }


        // Ahora hay que ver si el usuario existe ya
        // if (peliculaRepository.findById(peliculaI)
        // problema para hacer el get de un id que se autogenera

        Ticket ticket = new Ticket(funcion,usuario,ascientos,precio);

        ticketRepository.save(ticket);
    }

    @DeleteMapping(value = "/ticket/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketRepository.delete(ticketRepository.findById(id).get());
    }

}
