package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Usuario;
import com.example.moviecrud.business.entities.UsuarioCine;
import com.example.moviecrud.business.entities.UsuarioFinal;
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

import java.io.IOException;
import java.util.List;

@RestController
public class UsuarioMgr {

    RestTemplate rest = new RestTemplate();
    public void save(Usuario usuario){
        rest.postForObject("http://localhost:8080/usuario", usuario, Usuario.class);
    }


    public void update (@PathVariable("id") Long id, Usuario usuario){
        usuario.setId(id);
        save(rest.postForObject("http://localhost:8080/usuario", usuario, Usuario.class));
    }

    //Get all users
    // @GetMapping("/users")
    public List<Usuario> getAllUsuarios(){
        return (List<Usuario>) rest.exchange("http://localhost:8080/usuarios", HttpMethod.GET, null, new ParameterizedTypeReference<List<Usuario>>() {}).getBody();
    }

    // Get a Single user by id
    // @GetMapping("/usuario/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Long usuarioId) {
        return rest.getForObject("http://localhost:8080/usuario/{id}", Usuario.class);
    }

    public Usuario getUsuarioByUsername(String nUsuario) {
        return rest.getForObject("http://localhost:8080/usuario/{name}", Usuario.class);
    }

    // Delete a Usuario by id
    //  @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long usuarioId) {
        rest.delete("http://localhost:8080/usuario/{id}");
        return ResponseEntity.ok().build();
    }

    public void addUsuarioCine(String username, String password, String email) throws InformacionInvalida, YaExiste, IOException {
        if(username == null || "".equals(username) || password == null || "".equals(password) || email == null || "".equals(email)  ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }



        // Ahora hay que ver si el usuario existe ya
        // if (peliculaRepository.findById(peliculaI)
        // problema para hacer el get de un id que se autogenera

        UsuarioCine usuario = new UsuarioCine(username,password,email);

        save(usuario);
    }


    public void addUsuarioFinal(String username, String password, String email) throws InformacionInvalida, YaExiste, IOException {
        if(username == null || "".equals(username) || password == null || "".equals(password) || email == null || "".equals(email)  ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }



        // Ahora hay que ver si el usuario existe ya
        // if (peliculaRepository.findById(peliculaI)
        // problema para hacer el get de un id que se autogenera

        UsuarioFinal usuario = new UsuarioFinal(username,password,email);

        save(usuario);
    }



    public void eliminarUsuario (String username)  throws InformacionInvalida, NoExiste {
        for (Usuario usuario: getAllUsuarios()) {
            if (usuario.getUsername().equals(username)){
                Long id = usuario.getId();
                deleteUsuario(id);
            }
        }

    }

    public void editarUsuarioCine (String usernameViejo, String usernameNuevo, String password, String email) throws InformacionInvalida, NoExiste {
        if(usernameNuevo == null || "".equals(usernameNuevo) || usernameViejo == null || "".equals(usernameViejo)  || password == null || "".equals(password) || email == null || "".equals(email) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        for (Usuario usuario: getAllUsuarios()) {
            if (usuario.getUsername().equals(usernameViejo)){
                Long id = usuario.getId();
                UsuarioCine usuarioActualizado = new UsuarioCine(usernameNuevo,password ,email);
                update(id,usuarioActualizado);
            }
        }
    }

    public void editarUsuarioFinal (String usernameViejo, String usernameNuevo, String password, String email) throws InformacionInvalida, NoExiste {
        if(usernameNuevo == null || "".equals(usernameNuevo) || usernameViejo == null || "".equals(usernameViejo)  || password == null || "".equals(password) || email == null || "".equals(email) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        for (Usuario usuario: getAllUsuarios()) {
            if (usuario.getUsername().equals(usernameViejo)){
                Long id = usuario.getId();
                UsuarioFinal usuarioActualizado = new UsuarioFinal(usernameNuevo,password ,email);
                update(id,usuarioActualizado);
            }
        }
    }

}
