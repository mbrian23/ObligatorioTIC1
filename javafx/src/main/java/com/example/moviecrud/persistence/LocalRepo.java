package com.example.moviecrud.persistence;

import com.example.moviecrud.business.entities.Local;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepo extends CrudRepository<Local,String> {
}
