package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.NoExiste;
import com.example.moviecrud.business.exceptions.YaExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
public class PeliculaMgr {

    RestTemplate rest = new RestTemplate();


    public void save( Pelicula pelicula){
        rest.postForObject("http://localhost:8080/pelicula", pelicula, Pelicula.class);
    }


    public void update (@PathVariable("id") Long id, Pelicula pelicula){
        pelicula.setId(id);
        save(rest.postForObject("http://localhost:8080/pelicula", pelicula, Pelicula.class));
    }

    public List<Pelicula> getAllPeliculas(){
        return (List<Pelicula>) rest.exchange("http://localhost:8080/peliculas", HttpMethod.GET, null, new ParameterizedTypeReference<List<Pelicula>>() {}).getBody();
    }

    // Get a Single pelicula by id
   // @GetMapping("/pelicula/{id}")
    public Pelicula getPeliculaById(@PathVariable(value = "id") Long peliculaId) {
        return rest.getForObject("http://localhost:8080/pelicula/{id}", Pelicula.class);
    }

    public Pelicula getPeliculaByName(String nPelicula) {
        return rest.getForObject("http://localhost:8080/pelicula/{name}", Pelicula.class);
    }

    // Delete a Pelicula by id
  //  @DeleteMapping("/pelicula/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable(value = "id") Long peliculaId) {
        rest.delete("http://localhost:8080/pelicula/{id}");
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
        save(pelicula);
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
