package org.hibernate.jpa.entidadesTic.Entity.Pelicula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeliculaController {

    @Autowired
    PeliculaRepository peliculaRepository;

    // Create pelicula
    @PostMapping("/pelicula")
    public void save( Pelicula pelicula){
        peliculaRepository.save(pelicula);
    }

    //Edit pelicula by id
    @PutMapping("/pelicula/{id}")
    public void update (@PathVariable("id") Long id, Pelicula pelicula){
        pelicula.setId(id);
        peliculaRepository.save(pelicula);
    }

    //Get all peliculas
    @GetMapping("/peliculas")
    public List<Pelicula> getAllPeliculas(){
        return (List<Pelicula>) peliculaRepository.findAll();
    }

    // Get a Single pelicula by id
    @GetMapping("/pelicula/{id}")
    public Pelicula getPeliculaById(@PathVariable(value = "id") Long peliculaId) {
        return peliculaRepository.findById(peliculaId).get();
    }

    // Delete a Pelicula by id
    @DeleteMapping("/pelicula/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable(value = "id") Long peliculaId) {
        Pelicula pelicula= peliculaRepository.findById(peliculaId).get();
        peliculaRepository.delete(pelicula);
        return ResponseEntity.ok().build();
    }
}
