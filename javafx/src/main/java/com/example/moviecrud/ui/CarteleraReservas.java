package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.FuncionMgr;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.TicketMgr;
import com.example.moviecrud.business.entities.*;
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
public class CarteleraReservas implements Initializable {

    @Autowired
    TicketMgr ticketMgr;

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
    private TableView<Ticket> tabla;

    @FXML
    private TableColumn<Ticket, LocalDate> fechaInicio;

    @FXML
    private TableColumn<Ticket, Time> hora;

    @FXML
    private TableColumn<Ticket, Sala> sala;

    @FXML
    private TableColumn<Ticket, Pelicula> pelicula;

    @FXML
    private TableColumn<Ticket, Local> local;

    @FXML
    private TableColumn<Ticket, Usuario> usuario;

    @FXML
    private TableColumn<Ticket, String> asiento;

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
    private Button btnReservas;

    @FXML
    void agregarFuncionAction(ActionEvent event) {

    }

    private ObservableList<Ticket> ticketList = FXCollections.observableArrayList();

    public void actualizaCart(){
        ticketList.clear();
        ticketList.addAll(ticketMgr.getAllTickets());
        tabla.setItems(ticketList);
    }

    @FXML
    void cargaCartCines(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraCines.class.getResourceAsStream("CarteleraCines.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void cargaCartFunciones(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraFunciones.class.getResourceAsStream("CarteleraFunciones.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void cargaCartLocales(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraLocales.class.getResourceAsStream("CarteleraLocales.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void cargaCartPeliculas(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("Cartelera.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void cargaCartSalas(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraSalas.class.getResourceAsStream("CarteleraSalas.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void cargaCartReservas (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraReservas.class.getResourceAsStream("CarteleraReservas.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void cargaInicio(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void editarFuncionAction(ActionEvent event) {

    }

    @FXML
    void eliminarFuncionAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fechaInicio.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        hora.setCellValueFactory(new PropertyValueFactory<>("horaFuncion"));
        sala.setCellValueFactory(new PropertyValueFactory<>("numeroSala"));
        pelicula.setCellValueFactory(new PropertyValueFactory<>("nombrePelicula"));
        local.setCellValueFactory(new PropertyValueFactory<>("nombreLocal"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        asiento.setCellValueFactory(new PropertyValueFactory<>("ascientos"));

        actualizaCart();



        buscador.textProperty().addListener((((observable, oldValue, newValue) -> {
            FilteredList<Ticket> filteredList = new FilteredList<>(ticketList, s -> true);
            filteredList.setPredicate((Predicate<? super Ticket>) (Ticket ticket) ->{
                if (newValue.isEmpty() || newValue==null){
                    return true;
                } else if (ticket.getUsuario().getUsername().contains(newValue)){
                    return true;
                } else if (ticket.getFuncion().getPelicula().getTitulo().contains(newValue)){
                    return true;
                }
                return false;
            });

            SortedList sortedList = new SortedList(filteredList);
            sortedList.comparatorProperty().bind(tabla.comparatorProperty());
            tabla.setItems(sortedList);

        })));
        FilteredList<Ticket> filteredList = new FilteredList<>(ticketList, s -> true);
        SortedList sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedList);
    }
}
