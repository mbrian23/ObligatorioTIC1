package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.FuncionMgr;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.entities.Funcion;
import com.example.moviecrud.business.entities.Local;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
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

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

@Controller
public class CarteleraFunciones implements Initializable {


    @Autowired
    FuncionMgr funcionMgr;

    @FXML
    private AnchorPane root;

    @FXML
    private MenuItem mItemAgregarFuncion;

    @FXML
    private MenuItem mItemEliminarFuncion;

    @FXML
    private MenuItem mItemEditarFuncion;

    @FXML
    private TextField buscador;

    @FXML
    private TableView<Funcion> tabla;

    @FXML
    private TableColumn<Funcion, LocalDate> fechaInicio;

    @FXML
    private TableColumn<Funcion, LocalDate> fechaFinal;

    @FXML
    private TableColumn<Funcion, Time> hora;

    @FXML
    private TableColumn<Funcion, Long> sala;

    @FXML
    private TableColumn<Funcion, Pelicula> pelicula;

    @FXML
    private TableColumn<Funcion, Local> local;

    @FXML
    private Button btnpeliculas;

    @FXML
    private Button btnsalas;

    @FXML
    private Button btnfunciones;

    @FXML
    private Button btnlocales;

    @FXML
    private Button btnCines;

    @FXML
    private Button btnVolver;


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
    public void cargaCartSalas (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraSalas.class.getResourceAsStream("CarteleraSalas.fxml"));
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
    public void cargaCartFunciones (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraFunciones.class.getResourceAsStream("CarteleraFunciones.fxml"));
        Scene inicioScene = new Scene(root, 1000,750);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    private ObservableList<Funcion> funcionesList = FXCollections.observableArrayList();


    public void actualizaCart(){
        funcionesList.clear();
        funcionesList.addAll(funcionMgr.getAllFunciones());
        tabla.setItems(funcionesList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fechaInicio.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        fechaFinal.setCellValueFactory(new PropertyValueFactory<>("fecha Final"));
        hora.setCellValueFactory(new PropertyValueFactory<>("horaFuncion"));
        sala.setCellValueFactory(new PropertyValueFactory<>("numeroSala"));
        pelicula.setCellValueFactory(new PropertyValueFactory<>("nombrePelicula"));
        local.setCellValueFactory(new PropertyValueFactory<>("nombreLocal"));

        actualizaCart();



        buscador.textProperty().addListener((((observable, oldValue, newValue) -> {
            FilteredList<Funcion> filteredList = new FilteredList<>(funcionesList, s -> true);
            filteredList.setPredicate((Predicate<? super Funcion>) (Funcion funcion) ->{
                if (newValue.isEmpty() || newValue==null){
                    return true;
                } else if (funcion.getPelicula().getTitulo().contains(newValue)){
                    return true;
                } else if (funcion.getLocal().getName().contains(newValue)){
                    return true;
                }
                return false;
            });

            SortedList sortedList = new SortedList(filteredList);
            sortedList.comparatorProperty().bind(tabla.comparatorProperty());
            tabla.setItems(sortedList);

        })));
        FilteredList<Funcion> filteredList = new FilteredList<>(funcionesList, s -> true);
        SortedList sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedList);

    }

    @FXML
    void agregarFuncionAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(SalaController.class.getResourceAsStream("AddFuncion.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void eliminarFuncionAction(){

    }

    @FXML
    void editarFuncionAction(){

    }
}
