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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
public class FuncionMgr {
    RestTemplate rest = new RestTemplate();

  String ip = InetAddress.getLocalHost().getHostAddress();

  //  String ip = "10.252.61.196";

    public FuncionMgr() throws UnknownHostException {
    }

    public void save(Funcion funcion){

        rest.postForObject("http://"+ip+":8080/funcion", funcion, Funcion.class);
    }

    public void update ( Funcion funcion){
        rest.postForObject("http://"+ip+":8080/funcion", funcion, Funcion.class);
    }


    public List<Funcion> getAllFunciones(){
        return rest.exchange("http://"+ip+":8080/funciones", HttpMethod.GET, null, new ParameterizedTypeReference<List<Funcion>>() {}).getBody();
    }


    public Funcion getFuncionById(@PathVariable(value = "id") Long id) {
        return rest.getForObject("http://"+ip+":8080/funcion/{id}", Funcion.class, id);
    }

    public ResponseEntity<?> deleteFuncion(@PathVariable(value = "id") Long funcionId) {
        rest.delete("http://"+ip+":8080/funcion/{id}");
        return ResponseEntity.ok().build();
    }

    public void addFuncion(LocalDate fechaInicio, LocalDate fechaFinal, Time horaFuncion, Sala sala, Pelicula pelicula, Local local) throws InformacionInvalida, YaExiste, IOException {
        if(fechaFinal== null || fechaInicio == null ||  horaFuncion == null ||  sala ==null ||  pelicula == null ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        Period period = Period.between(fechaInicio, fechaFinal);
        int dif = period.getDays();
        for (int i = 0; i <= dif; i++) {

            fechaInicio = fechaInicio.plusDays(1);
            Funcion funcion = new Funcion();
            funcion.setLocal(local);
            funcion.setPelicula(pelicula);
            funcion.setSala(sala);
            funcion.setNumSala(sala.getNumeroSala());

            int tempColumnas = sala.getColumnas().intValue();
            int tempFilas = sala.getFilas().intValue();
            boolean [] [] matri = new boolean[tempFilas][6];
            funcion.setMatriz(matri);
            funcion.setFechaInicio(fechaInicio);
            funcion.setHoraFuncion(horaFuncion);
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