package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.CineMgr;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.ui.movie.MovieController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class InfoPelicula implements Initializable {

    @Autowired
    PeliculaMgr peliculaMgr;

    @Autowired
    CineMgr cineMgr;


    @FXML
    private ComboBox<String> localidad;

    private ObservableList<String> cadenas = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        int y = cineMgr.getAllCine().size();
//        for (int i = 0; i <y ; i++) {
//            String cine = cineMgr.getAllCine().get(i).getNombre();
//            cadenas.add(i, cine);
//        }
//        localidad.setItems(cadenas);
    }
}
