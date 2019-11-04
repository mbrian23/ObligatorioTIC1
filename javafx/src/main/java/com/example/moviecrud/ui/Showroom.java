package com.example.moviecrud.ui;

import com.example.moviecrud.business.FuncionMgr;
import com.example.moviecrud.business.SalaManager;
import com.example.moviecrud.business.entities.Sala;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class Showroom implements Initializable {

    @Autowired
    FuncionMgr funcionMgr;

    @Autowired
    SalaManager salaManager;

    @FXML
    private Button compra;

    private Image available = new Image("com/example/moviecrud/ui/images/available.png");

    private Image selected = new Image("com/example/moviecrud/ui/images/selectedSeat.png");

    private Image unavailable = new Image("com/example/moviecrud/ui/images/unavailable.png");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void creacionShowroom (Sala sala){


    }
}
