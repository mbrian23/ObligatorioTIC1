package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Cine;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.persistence.CineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CineMgr {

    @Autowired
    CineRepo cineRepo;

    public void save (Cine cine){
        cineRepo.save(cine);
    }

    public List<Cine> getAllSalas(){
        return (List<Cine>) cineRepo.findAll();
    }
}
