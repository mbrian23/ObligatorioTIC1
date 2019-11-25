package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.NoExiste;
import com.example.moviecrud.business.exceptions.YaExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@RestController
public class LocalMgr {
    RestTemplate rest = new RestTemplate();


    public void save(Local local) {
        rest.postForObject("http://localhost:8080/local", local, Local.class);
    }

    public void update(@PathVariable("id") String id, Local local) {
        local.setName(id);
        save(rest.postForObject("http://localhost:8080/local", local, Local.class));
    }


    public List<Local> getAllLocales() {
        return rest.exchange("http://localhost:8080/locales", HttpMethod.GET, null, new ParameterizedTypeReference<List<Local>>() {}).getBody();
    }


    public Local getLocalById(@PathVariable(value = "nombre_local") String nombre_local) {
            return rest.getForObject("http://localhost:8080/local?nombre_local={nombre_local}", Local.class, nombre_local);
    }

    public ResponseEntity<?> deleteLocal(@PathVariable(value = "id") String localId) {
        rest.delete("http://localhost:8080/local/{id}");
        return ResponseEntity.ok().build();
    }

    public void addLocal(String name, Cine cine) throws InformacionInvalida, YaExiste, IOException {
        if (name == null || "".equals(name) || cine.getNombre() == null || "".equals(cine.getNombre())) {

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }


        Local local = new Local(name, cine);
        save(local);


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