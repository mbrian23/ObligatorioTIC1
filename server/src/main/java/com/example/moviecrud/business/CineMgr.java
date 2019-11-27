package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.NoExiste;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.persistence.CineRepo;
import com.example.moviecrud.persistence.LocalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@RestController
public class CineMgr  {
    @Autowired
    CineRepo cineRepo;

    @PostMapping("/cine")
    public void save(@RequestBody Cine cine) {
        cineRepo.save(cine);
    }

    @PutMapping("/cine/{id}")
    public void update(@PathVariable("id") String id, Cine cine) {
        cine.setNombre(id);
        cineRepo.save(cine);
    }

    @GetMapping("/cines")
    public List<Cine> getAllCine() {
        return (List<Cine>) cineRepo.findAll();
    }

    @GetMapping("/cine")
    public Cine getCineById(@RequestParam(value = "id")@RequestBody String id) {
        return cineRepo.findById(id).get();
    }

    @DeleteMapping(value = "/cine/{id}")
    public void deleteCine(@PathVariable String cineId) {
        Cine cine = cineRepo.findById(cineId).get();
        cineRepo.delete(cine);
    }

    public void addCine(String name) throws InformacionInvalida, YaExiste, IOException {
        if (name == null || "".equals(name)) {

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }


        Cine cine = new Cine(name);
        cineRepo.save(cine);


    }
    public void eliminarCine (String titulo)  throws InformacionInvalida, NoExiste {
        for (Cine cine: getAllCine()) {
            if (cine.getNombre().equals(titulo)){
                deleteCine(titulo);
            }
        }

    }

    public void editarCine (String nameViejo, String nameNuevo) throws InformacionInvalida, NoExiste {
        if(nameNuevo == null || "".equals(nameNuevo) || nameViejo == null || "".equals(nameViejo)){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        for (Cine cine: getAllCine()) {
            if (cine.getNombre().equals(nameViejo)){
                String id = cine.getNombre();
                Cine cineActualizado = new Cine(id);
                update(id,cineActualizado);
            }
        }
    }


}