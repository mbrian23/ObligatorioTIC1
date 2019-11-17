package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.NoExiste;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.persistence.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PeliculaMgr {



    @Autowired
    PeliculaRepository peliculaRepository;


    // Create pelicula
    @PostMapping("/pelicula")
    public void save(@RequestBody Pelicula pelicula){
        System.out.println(pelicula+"am i there"+pelicula.getId() + pelicula.getActores()+pelicula.getTitulo());
        peliculaRepository.save(pelicula);
        peliculaRepository.count();
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

    @GetMapping("/pelicula/nombre/{name}")
    public Pelicula getPeliculaByName(String nPelicula) {
        List<Pelicula> peliculas = (List<Pelicula>) peliculaRepository.findAll();
        int l = peliculas.size();
        Pelicula temp = null;
        for (int i = 0; i <l ; i++) {
            if (peliculas.get(i).getTitulo().equals(nPelicula)) {
                temp = peliculas.get(i);
            }
        }
        return temp;
    }

    // Delete a Pelicula by id
    @DeleteMapping("/pelicula/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable(value = "id") Long peliculaId) {
        Pelicula pelicula= peliculaRepository.findById(peliculaId).get();
        peliculaRepository.delete(pelicula);
        return ResponseEntity.ok().build();
    }





}
