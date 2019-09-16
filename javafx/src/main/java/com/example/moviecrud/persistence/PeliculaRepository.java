package com.example.moviecrud.persistence;


import com.example.moviecrud.business.entities.Pelicula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends CrudRepository<Pelicula, Long> {

}
