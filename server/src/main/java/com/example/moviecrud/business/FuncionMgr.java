package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Funcion;
import com.example.moviecrud.business.entities.Local;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.NoExiste;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.persistence.FuncionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@RestController
public class FuncionMgr {

    @Autowired
    FuncionRepo funcionRepo;

    @PostMapping("/funcion")
    public void save(Funcion funcion){
        funcionRepo.save(funcion);
    }

    @PutMapping("/funcion/{id}")
    public void update (@PathVariable("id") Long id, Funcion funcion){
        funcion.setId(id);
        funcionRepo.save(funcion);
    }

    @GetMapping("/funciones")
    public List<Funcion> getAllFunciones(){
        return (List<Funcion>) funcionRepo.findAll();
    }

    @GetMapping("/funcion/{id}")
    public Funcion getFuncionById(@PathVariable(value = "id") Long funcionId) {
        return funcionRepo.findById(funcionId).get();
    }
    @DeleteMapping("/funcion/{id}")
    public ResponseEntity<?> deleteFuncion(@PathVariable(value = "id") Long funcionId) {
        Funcion funcion= funcionRepo.findById(funcionId).get();
        funcionRepo.delete(funcion);
        return ResponseEntity.ok().build();
    }

    public void addFuncion(LocalDate fechaInicio, LocalDate fechaFinal, Time horaFuncion, Sala sala, Pelicula pelicula, Local local) throws InformacionInvalida, YaExiste, IOException {
        if(fechaFinal== null || "".equals(fechaFinal) || fechaInicio == null || "".equals(fechaInicio) || horaFuncion == null || "".equals(horaFuncion) || sala ==null || "".equals(sala) || pelicula == null || "".equals(pelicula) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }



        Funcion funcion = new Funcion(fechaInicio, fechaFinal, horaFuncion);
        funcionRepo.save(funcion);
    }

//   public void eliminarFuncion (String titulo)  throws InformacionInvalida, NoExiste {
//       for (Pelicula pelicula: getAllPeliculas()) {
//         if (pelicula.getTitulo().equals(titulo)){
//           Long id = pelicula.getId();
//         deletePelicula(id);
//   }
//    }
//
//    }

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