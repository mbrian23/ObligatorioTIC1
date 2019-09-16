package org.hibernate.jpa.entidadesTic.Entity.Cinema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String nombre;

//    @NotBlank
//
//    private List<Sala> salas;


    public Cinema() {
    }
}
