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
import java.net.UnknownHostException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.net.InetAddress;

@RestController
public class CineMgr  {

    @Autowired
    UsuarioMgr usuarioMgr;

    //String ip = "192.168.0.107";
    String ip = InetAddress.getLocalHost().getHostAddress();

    RestTemplate rest = new RestTemplate();

    public CineMgr() throws UnknownHostException {
    }

    public void save(Cine cine) {

        rest.postForObject("http://"+ip+":8080/cine", cine, Cine.class);
    }

    public void update(@PathVariable("id") String id, Cine cine) {
        cine.setNombre(id);
        save(rest.postForObject("http://"+ip+":8080/cine", cine, Cine.class));
    }


    public List<Cine> getAllCine() {
        return (List<Cine>) rest.exchange("http://"+ip+":8080/cines", HttpMethod.GET, null, new ParameterizedTypeReference<List<Cine>>() {}).getBody();
    }


    public Cine getCineById(@PathVariable(value = "id") String id) {
        return rest.getForObject("http://"+ip+":8080/cine?id={id}", Cine.class, id);
    }

    public void deleteCine(Cine cine) {
        rest.delete("http://"+ip+":8080/cine" + "/" +cine.getNombre() );

    }

    public void addCine(String name,String mail,String contra) throws InformacionInvalida, YaExiste, IOException {
        if (name == null || "".equals(name) || mail == null || "".equals(mail) || contra == null || "".equals(contra) ) {

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        Usuario cineUsr = new Usuario(name,contra,mail, "cine");
        usuarioMgr.save(cineUsr);

        Cine cine = new Cine(name);
        save(cine);


    }
    public void eliminarCine (String titulo)  throws InformacionInvalida, NoExiste {
        for (Cine cine: getAllCine()) {
            if (cine.getNombre().equals(titulo)){
                deleteCine(cine);
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