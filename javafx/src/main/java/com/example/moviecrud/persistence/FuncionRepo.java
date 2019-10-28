package com.example.moviecrud.persistence;

import com.example.moviecrud.business.entities.Funcion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionRepo extends CrudRepository<Funcion, Long> {
    }

