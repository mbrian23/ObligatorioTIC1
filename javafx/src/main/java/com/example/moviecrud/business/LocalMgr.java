package com.example.moviecrud.business;

import com.example.moviecrud.business.entities.Locales;
import com.example.moviecrud.persistence.CineRepo;
import com.example.moviecrud.persistence.LocalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocalMgr {

    @Autowired
    LocalRepo localRepo;

    public void save (Locales local){
        localRepo.save(local);
    }

    public List<Locales> getAllSalas(){
        return (List<Locales>) localRepo.findAll();
    }
}
