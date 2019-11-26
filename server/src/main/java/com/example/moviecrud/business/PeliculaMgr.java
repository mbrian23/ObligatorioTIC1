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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PeliculaMgr {



    @Autowired
    PeliculaRepository peliculaRepository;


    // Create pelicula
    @PostMapping("/pelicula")
    public void save(@RequestBody Pelicula pelicula){
        peliculaRepository.save(pelicula);
        peliculaRepository.count();
    }


    @PutMapping("/pelicula")
    public ResponseEntity<Pelicula> update (
                                            @Valid @RequestBody Pelicula pelicula){
        Pelicula pel = peliculaRepository.findById(pelicula.getId()).get();
        pel.setMovieImage(pelicula.getMovieImage());
        pel.setActores(pelicula.getActores());
        pel.setDescripcion(pelicula.getDescripcion());
        pel.setDuracion(pelicula.getDuracion());
        pel.setGenero(pelicula.getGenero());
        pel.setTitulo(pelicula.getTitulo());
        final Pelicula peliculaPronta = peliculaRepository.save(pel);
        return ResponseEntity.ok(peliculaPronta);

    }
//    @PutMapping("/pelicula")
//    public void update(@RequestBody Pelicula pelicula){
//        peliculaRepository.save(pelicula);
//    }


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

    @GetMapping("/pelicula")
    public Pelicula getPeliculaByName(@RequestParam(value = "titulo") @RequestBody String titulo) {
        List<Pelicula> peliculas = (List<Pelicula>) peliculaRepository.findAll();
        int l = peliculas.size();
        Pelicula temp = null;
        for (int i = 0; i <l ; i++) {
            if (peliculas.get(i).getTitulo().equals(titulo)) {
                temp = peliculas.get(i);
            }
        }
        return temp;
    }

    @DeleteMapping(value = "/pelicula/{id}")
    public void deletePelicula(@PathVariable Long id) {
        peliculaRepository.delete(peliculaRepository.findById(id).get());
    }





}
