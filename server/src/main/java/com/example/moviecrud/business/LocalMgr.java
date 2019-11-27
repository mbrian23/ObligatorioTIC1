package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.NoExiste;
import com.example.moviecrud.business.exceptions.YaExiste;
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
public class LocalMgr {
    @Autowired
    LocalRepo localRepo;

    @PostMapping("/local")
    public void save(@RequestBody Local local) {

        localRepo.save(local);
        local.setnCine(local.getCine().getNombre());
    }

    @PutMapping("/local/{id}")
    public void update(@PathVariable("id") String id, Local local) {
        local.setName(id);
        localRepo.save(local);
    }

    @GetMapping("/locales")
    public List<Local> getAllLocales() {
        return (List<Local>) localRepo.findAll();
    }

    @GetMapping("/local")
    public Local getLocalById(@RequestParam(value = "nombre_local") @RequestBody String localId) {
        return localRepo.findById(localId).get();
    }

    @DeleteMapping(value = "/local/{id}")
    public void deleteLocal(@PathVariable String localId) {
        Local local = localRepo.findById(localId).get();
        localRepo.delete(local);

    }

    public void addLocal(String name, Cine cine) throws InformacionInvalida, YaExiste, IOException {
        if (name == null || "".equals(name) || cine.getNombre() == null || "".equals(cine.getNombre())) {

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }


        Local local = new Local(name, cine);
        local.setnCine(cine.getNombre());
        localRepo.save(local);


    }
    public void eliminarLocal (String titulo)  throws InformacionInvalida, NoExiste {
        for (Local local: getAllLocales()) {
            if (local.getName().equals(titulo)){
                deleteLocal(titulo);
            }
        }

    }

    public void editarLocal (String nameViejo, String nameNuevo, Cine cine) throws InformacionInvalida, NoExiste {
        if(nameNuevo == null || "".equals(nameNuevo) || nameViejo == null || "".equals(nameViejo)  || cine == null || "".equals(cine) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        for (Local local: getAllLocales()) {
            if (local.getName().equals(nameViejo)){
                String id = local.getName();
                Local localActualizado = new Local(id, cine);
                update(id,localActualizado);
            }
        }
    }


}