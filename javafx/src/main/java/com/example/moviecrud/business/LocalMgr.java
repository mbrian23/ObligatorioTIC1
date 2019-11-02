package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.NoExiste;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.persistence.LocalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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


    public void save(Local local) {

        localRepo.save(local);
        local.setnCine(local.getCine().getNombre());
    }

    public void update(@PathVariable("id") String id, Local local) {
        local.setName(id);
        localRepo.save(local);
    }


    public List<Local> getAllLocales() {
        return (List<Local>) localRepo.findAll();
    }


    public Local getLocalById(@PathVariable(value = "id") String localId) {
        return localRepo.findById(localId).get();
    }

    public ResponseEntity<?> deleteLocal(@PathVariable(value = "id") String localId) {
        Local local = localRepo.findById(localId).get();
        localRepo.delete(local);
        return ResponseEntity.ok().build();
    }

    public void addLocal(String name, Cine cine) throws InformacionInvalida, YaExiste, IOException {
        if (name == null || "".equals(name) || cine == null || "".equals(cine)) {

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }


        Local local = new Local(name, cine);
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