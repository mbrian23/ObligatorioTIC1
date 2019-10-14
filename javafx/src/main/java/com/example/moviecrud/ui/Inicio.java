package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.PeliculaMgr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class Inicio implements Initializable {

   MovieCrudApplication movieCrudApplication;

    @Autowired
    public Inicio (MovieCrudApplication movieCrudApplication){ this.movieCrudApplication=movieCrudApplication;}

    @Autowired
    PeliculaMgr peliculaMgr;


    @FXML
    private Pane pane;

    @FXML
    private TextField buscadorInicio;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
     try {
      pane.getChildren().add(movieCrudApplication.createContent());
     } catch (Exception e) {
      e.printStackTrace();
     }
    }
}
