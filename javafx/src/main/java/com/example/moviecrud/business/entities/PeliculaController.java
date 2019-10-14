package com.example.moviecrud.business.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeliculaController {

    private static final String template = "Hello, %s!";
    private final Long id = new Long;

    @RequestMapping("/Pelicula")

    public Pelicula pelicula(@RequestParam(value="titulo", defaultValue="Batman") String titulo) {
        return new Pelicula(id.incrementAndGet(),
                String.format(template, name));
    }

    }
}