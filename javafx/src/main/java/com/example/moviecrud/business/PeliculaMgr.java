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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
public class PeliculaMgr {

    RestTemplate rest = new RestTemplate();

    String ip = InetAddress.getLocalHost().getHostAddress();

    //String ip = "10.252.61.196";

    public PeliculaMgr() throws UnknownHostException {
    }


    public void save( Pelicula pelicula){

        rest.postForObject("http://"+ip+":8080/pelicula", pelicula, Pelicula.class);

    }


    public void update ( Pelicula pelicula){
        rest.postForObject("http://"+ip+":8080/pelicula", pelicula, Pelicula.class);

    }

    public List<Pelicula> getAllPeliculas(){
        return (List<Pelicula>) rest.exchange("http://"+ip+":8080/peliculas", HttpMethod.GET, null, new ParameterizedTypeReference<List<Pelicula>>() {}).getBody();
    }


    public Pelicula getPeliculaById(@PathVariable(value = "id") Long id) {
        return rest.getForObject("http://"+ip+":8080/pelicula/{id}", Pelicula.class, id);
    }

    public Pelicula getPeliculaByName(@PathVariable(value = "titulo") String titulo) {
        return rest.getForObject("http://"+ip+":8080/pelicula?titulo={titulo}", Pelicula.class, titulo);
    }


    public void deletePelicula(Pelicula pelicula) {
        rest.delete("http://"+ip+":8080/pelicula" + "/" + pelicula.getId());
    }

    public void addPelicula(String titulo, String genero, String actores, String duracion, String descripcion, byte[] movieImage) throws InformacionInvalida, YaExiste, IOException {
        if(titulo == null || "".equals(titulo) || genero == null || "".equals(genero) || actores == null || "".equals(actores) || duracion ==null || "".equals(duracion) || descripcion == null || "".equals(descripcion) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }



        Pelicula pelicula = new Pelicula(titulo,genero,actores,duracion,descripcion);
        pelicula.setMovieImage(movieImage);
        save(pelicula);
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


            }
        }
    }





}
