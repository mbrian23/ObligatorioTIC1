package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.exceptions.InformacionPeliculaInvalida;
import com.example.moviecrud.business.exceptions.PeliculaNoExiste;
import com.example.moviecrud.business.exceptions.PeliculaYaExiste;
import com.example.moviecrud.persistence.PeliculaRepository;
import com.example.moviecrud.ui.Principal;
import com.example.moviecrud.ui.movie.MovieController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController
public class PeliculaMgr {



    @Autowired
    PeliculaRepository peliculaRepository;

    // Create pelicula
   // @PostMapping("/pelicula")
    public void save( Pelicula pelicula){
        peliculaRepository.save(pelicula);
    }

    //Edit pelicula by id
   // @PutMapping("/pelicula/{id}")
    public void update (@PathVariable("id") Long id, Pelicula pelicula){
        pelicula.setId(id);
        peliculaRepository.save(pelicula);
    }

    //Get all peliculas
   // @GetMapping("/peliculas")
    public List<Pelicula> getAllPeliculas(){
        return (List<Pelicula>) peliculaRepository.findAll();
    }

    // Get a Single pelicula by id
   // @GetMapping("/pelicula/{id}")
    public Pelicula getPeliculaById(@PathVariable(value = "id") Long peliculaId) {
        return peliculaRepository.findById(peliculaId).get();
    }

    // Delete a Pelicula by id
  //  @DeleteMapping("/pelicula/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable(value = "id") Long peliculaId) {
        Pelicula pelicula= peliculaRepository.findById(peliculaId).get();
        peliculaRepository.delete(pelicula);
        return ResponseEntity.ok().build();
    }

    public void addPelicula (String titulo, String genero, String actores, String duracion,  String descripcion) throws InformacionPeliculaInvalida, PeliculaYaExiste {
        if(titulo == null || "".equals(titulo) || genero == null || "".equals(genero) || actores == null || "".equals(actores) || duracion ==null || "".equals(duracion) || descripcion == null || "".equals(descripcion)){

            throw new InformacionPeliculaInvalida("Algun dato ingresado no es correcto");

        }



        // Ahora hay que ver si la pelicula existe ya
        // if (peliculaRepository.findById(peliculaI)
        // problema para hacer el get de un id que se autogenera

        Pelicula pelicula = new Pelicula(titulo,genero,actores,duracion ,descripcion);
        peliculaRepository.save(pelicula);
    }
    
    public void eliminarPelicula (String titulo)  throws InformacionPeliculaInvalida, PeliculaNoExiste {
        for (Pelicula pelicula: getAllPeliculas()) {
            if (pelicula.getTitulo().equals(titulo)){
               Long id = pelicula.getId();
               deletePelicula(id);
            }
        }
        
    }

    public void editarPelicula (String tituloViejo, String tituloNuevo, String genero, String actores, String duracion,  String descripcion) throws InformacionPeliculaInvalida, PeliculaNoExiste {
        if(tituloNuevo == null || "".equals(tituloNuevo) || tituloViejo == null || "".equals(tituloViejo)  || genero == null || "".equals(genero) || actores == null || "".equals(actores) || duracion ==null || "".equals(duracion) || descripcion == null || "".equals(descripcion)){

            throw new InformacionPeliculaInvalida("Algun dato ingresado no es correcto");

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
