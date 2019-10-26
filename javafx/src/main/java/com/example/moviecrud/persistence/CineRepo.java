package com.example.moviecrud.persistence;

import com.example.moviecrud.business.entities.Cine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CineRepo extends CrudRepository<Cine, String > {
}
