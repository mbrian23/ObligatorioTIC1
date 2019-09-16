package com.example.moviecrud.ui.movie;

import com.example.moviecrud.business.PeliculaMgr;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieController {

    @Autowired
    private PeliculaMgr peliculaMgr;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClose;

    @FXML
    private TextField titulo;

    @FXML
    private Tex
}
