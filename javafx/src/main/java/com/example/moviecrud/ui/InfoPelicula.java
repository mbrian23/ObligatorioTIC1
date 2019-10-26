package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.ui.movie.MovieController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InfoPelicula {

    @Autowired
    PeliculaMgr peliculaMgr;


    @FXML
    private Button inicio;

    @FXML
    private Button cine;

    @FXML
    private Button teatro;

    @FXML
    private Button estrenos;

    @FXML
    private Button precios;

    @FXML
    private Button contacto;

    @FXML
    private Button registrarse;

    @FXML
    private Button iniciarSesion;

    
}
