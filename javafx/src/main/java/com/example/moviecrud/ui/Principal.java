package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.ui.movie.MovieController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

@Controller
public class Principal implements Initializable {
    @Autowired
    PeliculaMgr peliculaMgr;

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
    private TableColumn<Pelicula, String> genero;

    @FXML
    private TableColumn<Pelicula, String> actores;

    @FXML
    private TableColumn<Pelicula, String> duracion;

    @FXML
    private TableColumn<Pelicula, String> descripcion;

    private ObservableList<Pelicula> movieList = FXCollections.observableArrayList();


    @FXML
    private TextField buscador;

    @FXML
    private Button btnfunciones;

    @FXML
    private Button btnVolver;

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
       Pelicula pelicula = tabla.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("AddPelicula.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        MovieController movieController = fxmlLoader.getController();
        movieController.loadMovieData(pelicula);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        genero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        actores.setCellValueFactory(new PropertyValueFactory<>("actores"));
        duracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        actualizaCart();



       buscador.textProperty().addListener((((observable, oldValue, newValue) -> {
           FilteredList<Pelicula> filteredList = new FilteredList<>(movieList, s -> true);
           filteredList.setPredicate((Predicate<? super Pelicula>) (Pelicula pelicula) ->{
               if (newValue.isEmpty() || newValue==null){
                   return true;
               } else if (pelicula.getTitulo().contains(newValue)){
                   return true;
               } else if (pelicula.getGenero().contains(newValue)){
                   return true;
               }
               return false;
           });

           SortedList sortedList = new SortedList(filteredList);
           sortedList.comparatorProperty().bind(tabla.comparatorProperty());
           tabla.setItems(sortedList);

       })));
        FilteredList<Pelicula> filteredList = new FilteredList<>(movieList, s -> true);
        SortedList sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedList);


    }

    public void actualizaCart(){
        movieList.clear();
        movieList.addAll(peliculaMgr.getAllPeliculas());
        tabla.setItems(movieList);
    }

    @FXML
    private AnchorPane root;


    @FXML
    private Button btnpeliculas;

    @FXML
    private Button btnsalas;

    @FXML
    private Button btnlocales;

    @FXML
    private Button btnCines;


    @FXML
    public void cargaCartPeliculas (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("Cartelera.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void cargaCartSalas (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraSalas.class.getResourceAsStream("CarteleraSalas.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void cargaCartLocales (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraLocales.class.getResourceAsStream("CarteleraLocales.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void cargaInicio (ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void cargaCartCines (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraCines.class.getResourceAsStream("CarteleraCines.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }


    @FXML
    public void cargaCartFunciones (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraFunciones.class.getResourceAsStream("CarteleraFunciones.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }




}
