package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.CineMgr;
import com.example.moviecrud.business.entities.Cine;
import com.example.moviecrud.ui.movie.CineController;
import com.example.moviecrud.ui.movie.SalaController;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

@Controller
public class CarteleraCines implements Initializable {

    @Autowired
    CineMgr cineMgr;

    @FXML
    private AnchorPane root;

    @FXML
    private MenuItem mItemAgregarCine;

    @FXML
    private MenuItem mItemEliminarCine;

    @FXML
    private MenuItem mItemEditarCine;

    @FXML
    private TextField buscador;

    @FXML
    private TableView<Cine> tabla;

    @FXML
    private TableColumn<Cine, String> cine;

    @FXML
    private Button btnpeliculas;

    @FXML
    private Button btnsalas;

    @FXML
    private Button btnfunciones;

    @FXML
    private Button btnlocales;

    @FXML
    private Button btncines;

    @FXML
    private Button btninicio;

    private ObservableList<Cine> cineList = FXCollections.observableArrayList();

    @FXML
    void agregarCineAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CineController.class.getResourceAsStream("AddCine.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }



    @FXML
    public void cargaCartPeliculas (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("Cartelera.fxml"));
        Scene inicioScene = new Scene(root, 1000,750);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void cargaCartCines (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraCines.class.getResourceAsStream("CarteleraCines.fxml"));
        Scene inicioScene = new Scene(root, 1000,750);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void cargaCartLocales (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraLocales.class.getResourceAsStream("CarteleraLocales.fxml"));
        Scene inicioScene = new Scene(root, 1000,750);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }



    @FXML
    public void cargaCartSalas (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraSalas.class.getResourceAsStream("CarteleraSalas.fxml"));
        Scene inicioScene = new Scene(root, 600,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void cargaCartFunciones (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraFunciones.class.getResourceAsStream("CarteleraFunciones.fxml"));
        Scene inicioScene = new Scene(root, 1000,750);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void cargaInicio (ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Scene inicioScene = new Scene(root, 1000,750);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cine.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        actualizaCartCine();

        buscador.textProperty().addListener((((observable, oldValue, newValue) -> {
            FilteredList<Cine> filteredList = new FilteredList<>(cineList, s -> true);
            filteredList.setPredicate((Predicate<? super Cine>) (Cine cine) ->{
                if (newValue.isEmpty() || newValue==null){
                    return true;
                } else if (cine.getNombre().contains(newValue)){
                    return true;
                }
                return false;
            });

            SortedList sortedList = new SortedList(filteredList);
            sortedList.comparatorProperty().bind(tabla.comparatorProperty());
            tabla.setItems(sortedList);

        })));
        FilteredList<Cine> filteredList = new FilteredList<>(cineList, s -> true);
        SortedList sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedList);
    }

    public void actualizaCartCine(){
        cineList.clear();
        cineList.addAll(cineMgr.getAllCine());
        tabla.setItems(cineList);
    }

    //EDICION DE CINES
    @FXML
    private TextField nombrecine;

    @FXML
    private Button btnagregar;



}
