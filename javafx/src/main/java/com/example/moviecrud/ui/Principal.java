package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.entities.Pelicula;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class Principal implements Initializable {
    @Autowired
    private PeliculaMgr peliculaMgr;

    @FXML
    private MenuItem mItemAgregarPelicula;

    @FXML
    private MenuItem mItemEliminarPelicula;

    @FXML
    private MenuItem mItemEditarPelicula;
    @FXML
    private TableView<Pelicula> tabla;

    @FXML
    private TableColumn<Pelicula, String> titulo;

    @FXML
    private TableColumn<Pelicula, String> actores;

    @FXML
    private TableColumn<Pelicula, String> duracion;

    @FXML
    private TableColumn<Pelicula, String> descripcion;

    private ObservableList<Pelicula> movieList = FXCollections.observableArrayList();

    @FXML
    void agregarPeliculaAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("AddPelicula.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void eliminarPeliculaAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("EliminarPelicula.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void editarPeliculaAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("EditarPelicula.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        actores.setCellValueFactory(new PropertyValueFactory<>("actores"));
        duracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        movieList.clear();
        movieList.addAll(peliculaMgr.getAllPeliculas());

        tabla.setItems(movieList);

    }
}
