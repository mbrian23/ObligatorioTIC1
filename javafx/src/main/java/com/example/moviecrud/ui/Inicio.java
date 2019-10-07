package com.example.moviecrud.ui;

import com.example.moviecrud.business.PeliculaMgr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class Inicio implements Initializable {
    @Autowired
    PeliculaMgr peliculaMgr;


    @FXML
    private Pane pane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
