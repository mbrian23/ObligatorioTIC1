package com.example.moviecrud.ui.movie;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.CineMgr;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.ui.CarteleraCines;
import com.example.moviecrud.ui.Showroom;
import com.example.moviecrud.ui.movie.CineController;
import com.example.moviecrud.ui.movie.MovieController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class InfoPelicula implements Initializable {

    @Autowired
    CineMgr cineMgr;

    @Autowired
    PeliculaMgr peliculaMgr;

    @FXML
    AnchorPane paneImg;

    @FXML
    private ComboBox<String> localidad;

    private ObservableList<String> cadenas = FXCollections.observableArrayList();

    @Autowired
    CarteleraCines carteleraCines;

    @FXML
    private Button btncompra;


    @FXML
    public void loadShowroom (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent paneImg = fxmlLoader.load(Showroom.class.getResourceAsStream("Showroom.fxml"));
        Scene inicioScene = new Scene(paneImg, 1200, 1200);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBox();
    }

    public void setBox(){
        int y = cineMgr.getAllCine().size();
        for (int i = 0; i <y ; i++) {
            String cine = cineMgr.getAllCine().get(i).getNombre();
            cadenas.add(i, cine);
        }
        localidad.setItems(cadenas);
    }
}
