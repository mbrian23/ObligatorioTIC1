package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.SalaManager;
import com.example.moviecrud.business.entities.Cine;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.ui.movie.MovieController;
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
import java.util.ResourceBundle;
import java.util.function.Predicate;

@Controller
public class CarteleraSalas implements Initializable {

    @Autowired
    SalaManager salaManager;

    @FXML
    private MenuItem mItemAgregarSala;

    @FXML
    private MenuItem mItemEliminarSala;

    @FXML
    private MenuItem mItemEditarSala;

    @FXML
    private TextField buscador;

    @FXML
    private TableView<Sala> tabla;

    @FXML
    private TableColumn<Sala, String> tipo;

    @FXML
    private TableColumn<Sala, String> local;

    @FXML
    private TableColumn<Sala, Long> columnas;

    @FXML
    private TableColumn<Sala, Long> filas;






    @FXML
    private TableColumn<Sala, Cine> cine;

    private ObservableList<Sala> salaList = FXCollections.observableArrayList();


    @FXML
    void agregarSalaAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(SalaController.class.getResourceAsStream("AddSala.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void actualizaCartSala(){
        salaList.clear();
        salaList.addAll(salaManager.getAllSalas());
        tabla.setItems(salaList);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        cine.setCellValueFactory(new PropertyValueFactory<>("cine"));
        local.setCellValueFactory(new PropertyValueFactory<>("locales"));
        columnas.setCellValueFactory(new PropertyValueFactory<>("columnas"));
        filas.setCellValueFactory(new PropertyValueFactory<>("filas"));

        actualizaCartSala();



        buscador.textProperty().addListener((((observable, oldValue, newValue) -> {
            FilteredList<Sala> filteredList = new FilteredList<>(salaList, s -> true);
            filteredList.setPredicate((Predicate<? super Sala>) (Sala sala) ->{
                if (newValue.isEmpty() || newValue==null){
                    return true;
                } else if (sala.getTipo().contains(newValue)){
                    return true;
                } else if (sala.getLocal().getCine().getNombre().contains(newValue)){
                    return true;
                }
                return false;
            });

            SortedList sortedList = new SortedList(filteredList);
            sortedList.comparatorProperty().bind(tabla.comparatorProperty());
            tabla.setItems(sortedList);

        })));
        FilteredList<Sala> filteredList = new FilteredList<>(salaList, s -> true);
        SortedList sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedList);

    }

    @FXML
    private AnchorPane root;


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
    private Button volver;


    @FXML
    public void cargaCartPeliculas (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("Cartelera.fxml"));
        Scene inicioScene = new Scene(root, 600,500);
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
    void cargaInicio (ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Scene inicioScene = new Scene(root, 600, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void cargaCartLocales (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraLocales.class.getResourceAsStream("CarteleraLocales.fxml"));
        Scene inicioScene = new Scene(root, 600,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void cargaCartCines (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraCines.class.getResourceAsStream("CarteleraCines.fxml"));
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
        Scene inicioScene = new Scene(root, 600,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }


}
