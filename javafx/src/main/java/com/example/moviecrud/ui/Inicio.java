package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.ui.movie.MovieController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

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
    private Button buscador;


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


    @FXML
 void cargarBuscador (ActionEvent event) throws Exception {

       FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("Buscador.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();


    }

 @FXML
 public void adminEmpresa () throws Exception{
  FXMLLoader fxmlLoader = new FXMLLoader();
  fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

  Parent root = fxmlLoader.load(CarteleraSalas.class.getResourceAsStream("CarteleraSalas.fxml"));
  Stage stage = new Stage();
  stage.setScene(new Scene(root));
  stage.show();
 }

 @FXML
 public void iniciarSesion () throws Exception{
  FXMLLoader fxmlLoader = new FXMLLoader();
  fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

  Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("iniciarSesion.fxml"));
  Stage stage = new Stage();
  stage.setScene(new Scene(root));
  stage.show();
 }

}
