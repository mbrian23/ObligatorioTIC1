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
    public void save(Pelicula pelicula){
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

    @GetMapping("/pelicula/{name}")
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

    public void addPelicula(String titulo, String genero, String actores, String duracion, String descripcion, byte[] movieImage) throws InformacionInvalida, YaExiste, IOException {
        if(titulo == null || "".equals(titulo) || genero == null || "".equals(genero) || actores == null || "".equals(actores) || duracion ==null || "".equals(duracion) || descripcion == null || "".equals(descripcion) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }



        // Ahora hay que ver si la pelicula existe ya
        // if (peliculaRepository.findById(peliculaI)
        // problema para hacer el get de un id que se autogenera

        Pelicula pelicula = new Pelicula(titulo,genero,actores,duracion ,descripcion);
        pelicula.setMovieImage(movieImage);
        peliculaRepository.save(pelicula);
    }
    
    public void eliminarPelicula (String titulo)  throws InformacionInvalida, NoExiste {
        for (Pelicula pelicula: getAllPeliculas()) {
            if (pelicula.getTitulo().equals(titulo)){
               Long id = pelicula.getId();
               deletePelicula(id);
            }
        }
        
    }

    public void editarPelicula (String tituloViejo, String tituloNuevo, String genero, String actores, String duracion,  String descripcion) throws InformacionInvalida, NoExiste {
        if(tituloNuevo == null || "".equals(tituloNuevo) || tituloViejo == null || "".equals(tituloViejo)  || genero == null || "".equals(genero) || actores == null || "".equals(actores) || duracion ==null || "".equals(duracion) || descripcion == null || "".equals(descripcion) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

       for (Pelicula pelicula: getAllPeliculas()) {
            if (pelicula.getTitulo().equals(tituloViejo)){
                Long id = pelicula.getId();
                Pelicula peliculaActualizada = new Pelicula(tituloNuevo,genero ,actores,duracion ,descripcion);
                update(id,peliculaActualizada);
            }
        }
    }





}
