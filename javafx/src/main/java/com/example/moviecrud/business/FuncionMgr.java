package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Funcion;
import com.example.moviecrud.business.entities.Local;
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
import java.sql.Time;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
public class FuncionMgr {
    RestTemplate rest = new RestTemplate();


    public void save(Funcion funcion){
        rest.postForObject("http://localhost:8080/funcion", funcion, Funcion.class);
    }

    public void update (@PathVariable("id") Long id, Funcion funcion){
        funcion.setId(id);
        rest.postForObject("http://localhost:8080/funcion", funcion, Funcion.class);
    }


    public List<Funcion> getAllFunciones(){
        return (List<Funcion>) rest.exchange("http://localhost:8080/funciones", HttpMethod.GET, null, new ParameterizedTypeReference<List<Funcion>>() {}).getBody();
    }


    public Funcion getFuncionById(@PathVariable(value = "id") Long funcionId) {
        return rest.getForObject("http://localhost:8080/funcion/{id}", Funcion.class);
    }

    public ResponseEntity<?> deleteFuncion(@PathVariable(value = "id") Long funcionId) {
        rest.delete("http://localhost:8080/funcion/{id}");
        return ResponseEntity.ok().build();
    }

    public void addFuncion(LocalDate fechaInicio, LocalDate fechaFinal, Time horaFuncion, Sala sala, Pelicula pelicula, Local local) throws InformacionInvalida, YaExiste, IOException {
        if(fechaFinal== null || "".equals(fechaFinal) || fechaInicio == null || "".equals(fechaInicio) || horaFuncion == null || "".equals(horaFuncion) || sala ==null || "".equals(sala) || pelicula == null || "".equals(pelicula) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        Period period = Period.between(fechaInicio, fechaFinal);
        int dif = period.getDays();
        LocalDate fecha = fechaInicio.minusDays(1);
        for (int i = 0; i <= dif; i++) {

            fecha = fecha.plusDays(1);
            Funcion funcion = new Funcion(fecha, horaFuncion);
            funcion.setLocal(local);
            funcion.setPelicula(pelicula);
            funcion.setSala(sala);
            funcion.setNumSala(sala.getNumeroSala());
            save(funcion);
        }
    }

   //public void eliminarFuncion (String titulo)  throws InformacionInvalida, NoExiste {
   //    for (Pelicula pelicula: getAllPeliculas()) {
   //      if (pelicula.getTitulo().equals(titulo)){
   //        Long id = pelicula.getId();
   //      deletePelicula(id);
   //}
    //}

    //}

    //public void editarPelicula (String tituloViejo, String tituloNuevo, String genero, String actores, String duracion,  String descripcion) throws InformacionInvalida, NoExiste {
    //    if(tituloNuevo == null || "".equals(tituloNuevo) || tituloViejo == null || "".equals(tituloViejo)  || genero == null || "".equals(genero) || actores == null || "".equals(actores) || duracion ==null || "".equals(duracion) || descripcion == null || "".equals(descripcion) ){
//
  //          throw new InformacionInvalida("Algun dato ingresado no es correcto");
//
  //      }

    //    for (Pelicula pelicula: getAllPeliculas()) {
      //      if (pelicula.getTitulo().equals(tituloViejo)){
        //        Long id = pelicula.getId();
          //      Pelicula peliculaActualizada = new Pelicula(tituloNuevo,genero ,actores,duracion ,descripcion);
            //    update(id,peliculaActualizada);
           // }
        //}
    //}





}