package org.hibernate.jpa.entidadesTic.Entity.Pelicula;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends CrudRepository<Pelicula, Long> {

}
