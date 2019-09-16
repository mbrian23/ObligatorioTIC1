package com.example.moviecrud.ui.movie;

import com.example.moviecrud.business.PeliculaMgr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private TextField actores;

    @FXML
    private ComboBox<String> genero;

    @FXML
    private TextField duracion;

    @FXML
    private TextArea descripcion;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addClient(ActionEvent event) {

    }


}
