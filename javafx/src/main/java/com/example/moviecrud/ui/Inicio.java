package com.example.moviecrud.ui;

import com.example.moviecrud.DisplayShelf;
import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.ui.movie.MovieController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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

  Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("menuAdmin.fxml"));
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

 @FXML
 public void filtrado() throws Exception{
  int z = peliculaMgr.getAllPeliculas().size();
  Image [] todas = new Image[z];
  ObservableList<Pelicula> listaPeliculas = FXCollections.observableArrayList();
  listaPeliculas.clear();
  listaPeliculas.addAll(peliculaMgr.getAllPeliculas());

  Image[] aPeliculas = new Image[z];
  DisplayShelf displayShelf = new DisplayShelf(aPeliculas);
  int contador = 0;
 if (buscadorInicio.getText() != null) {
  for (int i = 0; i < z; i++) {
   if (listaPeliculas.get(i).getTitulo().contains(buscadorInicio.getText())) {
    byte [] img = listaPeliculas.get(i).getMovieImage();
    ByteArrayInputStream bis = new ByteArrayInputStream(img);
    BufferedImage bImage = ImageIO.read(bis);
    Image image = SwingFXUtils.toFXImage(bImage, null);
    aPeliculas[i] = image;
   } else if (listaPeliculas.get(i).getGenero().contains(buscadorInicio.getText())){
    byte [] img = listaPeliculas.get(i).getMovieImage();
    ByteArrayInputStream bis = new ByteArrayInputStream(img);
    BufferedImage bImage = ImageIO.read(bis);
    Image image = SwingFXUtils.toFXImage(bImage, null);
    aPeliculas[i] = image;
   }
  }
  final double WIDTH = 700, HEIGHT = 400;
  displayShelf.setPrefSize(WIDTH, HEIGHT);
  for (int i = 0; i <z ; i++) {
   displayShelf.getItems()[i].setOnMouseClicked(movieCrudApplication.abrirPaginaPelicula());
   displayShelf.getItems()[i].setId(String.valueOf(i));
  }
  try {
   pane.getChildren().add(displayShelf);
  } catch (Exception e) {
   e.printStackTrace();
  }

 } else {
  for (int i = 0; i < z; i++) {
    byte [] img = listaPeliculas.get(i).getMovieImage();
    ByteArrayInputStream bis = new ByteArrayInputStream(img);
    BufferedImage bImage = ImageIO.read(bis);
    Image image = SwingFXUtils.toFXImage(bImage, null);
    todas[i] = image;
   }
  DisplayShelf displayShelf2 = new DisplayShelf(todas);
  final double WIDTH = 700, HEIGHT = 400;
  displayShelf.setPrefSize(WIDTH, HEIGHT);
  for (int i = 0; i <z ; i++) {
   displayShelf.getItems()[i].setOnMouseClicked(movieCrudApplication.abrirPaginaPelicula());
   displayShelf.getItems()[i].setId(String.valueOf(i));
  }
  try {
   pane.getChildren().add(displayShelf);
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 }



}
