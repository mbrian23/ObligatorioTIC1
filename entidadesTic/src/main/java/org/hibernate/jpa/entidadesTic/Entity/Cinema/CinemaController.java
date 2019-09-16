package org.hibernate.jpa.entidadesTic.Entity.Cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    @Autowired
    CinemaRepository cinemaRepository;




}
