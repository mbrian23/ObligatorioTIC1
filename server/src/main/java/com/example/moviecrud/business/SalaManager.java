package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Cine;
import com.example.moviecrud.business.entities.Local;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.persistence.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class SalaManager {

    @Autowired
    SalaRepository salaRepository;

    @PostMapping("/sala")
    public void save( Sala sala){
        salaRepository.save(sala);
    }

    @PutMapping("/sala/{num}")
    public Sala getSalaByNumSala( Long numSala) {
       List<Sala> salas = (List<Sala>) salaRepository.findAll();
       int l = salas.size();
       Sala temp = null;
        for (int i = 0; i <l ; i++) {
            if (salas.get(i).getNumeroSala() == numSala) {
                temp = salas.get(i);
            }
        }
        return temp;
    }


    public void addSala(String tipo, Long nroSala, Long columnas, Long filas, Local local) throws YaExiste, IOException, InformacionInvalida {
        if(tipo == null || "".equals(tipo) || nroSala == null || "".equals(nroSala) || columnas == null || "".equals(columnas) || filas == null || "".equals(filas) ){

            throw new InformacionInvalida("Algun dato ingresado no es correcto");

        }

        // Ahora hay que ver si la sala existe ya

        Sala sala = new Sala(filas, columnas, tipo, nroSala, local);
//        sala.setnLocal(local.getName());
        salaRepository.save(sala);
    }

    @DeleteMapping("/sala/{num}")
    public ResponseEntity<?> deleteSala(@PathVariable(value = "id") Long salaNum) {
        Sala sala= salaRepository.findById(salaNum).get();
        salaRepository.delete(sala);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/salas")
    public List<Sala> getAllSalas(){
        return (List<Sala>) salaRepository.findAll();
    }
}
