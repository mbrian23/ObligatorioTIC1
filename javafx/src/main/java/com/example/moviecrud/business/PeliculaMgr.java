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


    public void update ( Pelicula pelicula){
        rest.postForObject("http://localhost:8080/pelicula", pelicula, Pelicula.class);

    }

    public List<Pelicula> getAllPeliculas(){
        return (List<Pelicula>) rest.exchange("http://localhost:8080/peliculas", HttpMethod.GET, null, new ParameterizedTypeReference<List<Pelicula>>() {}).getBody();
    }


    public Pelicula getPeliculaById(@PathVariable(value = "id") Long id) {
        return rest.getForObject("http://localhost:8080/pelicula/{id}", Pelicula.class, id);
    }

    public Pelicula getPeliculaByName(@PathVariable(value = "titulo") String titulo) {
        return rest.getForObject("http://localhost:8080/pelicula?titulo={titulo}", Pelicula.class, titulo);
    }


    public void deletePelicula(Pelicula pelicula) {
        rest.delete("http://localhost:8080/pelicula" + "/" + pelicula.getId());
    }

    public void addPelicula(String titulo, String genero, String actores, String duracion, String descripcion, byte[] movieImage) throws InformacionInvalida, YaExiste, IOException {
        if(titulo == null || "".equals(titulo) || genero == null || "".equals(genero) || actores == null || "".equals(actores) || duracion ==null || "".equals(duracion) || descripcion == null || "".equals(descripcion) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }



        // Ahora hay que ver si la pelicula existe ya
        // if (peliculaRepository.findById(peliculaI)
        // problema para hacer el get de un id que se autogenera

        Pelicula pelicula = new Pelicula(titulo,genero,actores,duracion,descripcion);
        pelicula.setMovieImage(movieImage);
        System.out.println(pelicula.getTitulo()+pelicula.getActores()+pelicula.getDuracion()+" I am here");
        save(pelicula);
        System.out.println(pelicula.getTitulo()+pelicula.getActores()+pelicula.getDuracion()+" I am here after save");
    }
    
    public void eliminarPelicula (String titulo)  throws InformacionInvalida, NoExiste {
        for (Pelicula pelicula: getAllPeliculas()) {
            if (pelicula.getTitulo().equals(titulo)){
               deletePelicula(pelicula);
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
               // Pelicula peliculaActualizada = new Pelicula(tituloNuevo,genero ,actores,duracion ,descripcion);
                //update(peliculaActualizada);

//                RestTemplate restTemplate = new RestTemplate();
//               restTemplate.put(
//                        "http://localHost:8080/pelicula/" + id+ "/" + tituloNuevo + "/" + actores + "/" + genero + "/" +descripcion + "/" + duracion,
//                        HttpMethod.PUT,
//                        null,
//                        new ParameterizedTypeReference<String>() {
//                        });

            }
        }
    }





}
