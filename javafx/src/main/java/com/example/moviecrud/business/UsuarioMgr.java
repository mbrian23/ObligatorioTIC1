package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.NoExiste;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
public class UsuarioMgr {

    RestTemplate rest = new RestTemplate();

   String ip = InetAddress.getLocalHost().getHostAddress();

   // String ip = "10.252.61.196";

    public UsuarioMgr() throws UnknownHostException {
    }


    public void save(Usuario usuario){
            rest.postForObject("http://"+ip+":8080/usuario", usuario, Usuario.class);
    }

    public void update ( Usuario usuario){
        rest.postForObject("http://"+ip+":8080/usuario", usuario, Usuario.class);
    }


    public List<Usuario> getAllUsuarios(){
        return (List<Usuario>) rest.exchange("http://"+ip+":8080/usuarios", HttpMethod.GET, null, new ParameterizedTypeReference<List<Usuario>>() {}).getBody();
    }


    public Usuario getUsuarioById(@PathVariable(value = "id") Long usuarioId) {
        return rest.getForObject("http://"+ip+":8080/usuario/{id}", Usuario.class);
    }

    public Usuario getUsuarioByUsername(@PathVariable(value = "username") String username) {

        return rest.getForObject("http://"+ip+":8080/usuario/nombre?username={username}", Usuario.class, username);

    }

    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long usuarioId) {
        rest.delete("http://"+ip+":8080/usuario/{id}");
        return ResponseEntity.ok().build();
    }

    public void addUsuario(String username, String password, String email, String adminPrivileges) throws InformacionInvalida, YaExiste, IOException {
        if(username == null || "".equals(username) || password == null || "".equals(password) || email == null || "".equals(email)  ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }



        Usuario usuario = new Usuario(username,password,email, adminPrivileges);

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

    public void editarUsuario (String usernameViejo, String usernameNuevo, String password, String email, String adminPrivileges) throws InformacionInvalida, NoExiste {
        if(usernameNuevo == null || "".equals(usernameNuevo) || usernameViejo == null || "".equals(usernameViejo)  || password == null || "".equals(password) || email == null || "".equals(email)){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        for (Usuario usuario: getAllUsuarios()) {
            if (usuario.getUsername().equals(usernameViejo)){
                Long id = usuario.getId();
                Usuario usuarioActualizado = new Usuario(usernameNuevo,password ,email, adminPrivileges);
                usuarioActualizado.setId(id);
                update(usuarioActualizado);
            }
        }
    }

}
