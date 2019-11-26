package com.example.moviecrud.ui;

import com.example.moviecrud.business.CineMgr;
import org.springframework.stereotype.Controller;


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

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

@Controller
public class CarteleraReservasDelCine implements Initializable {

    @Autowired
    TicketMgr ticketMgr;

    @Autowired
    CineMgr cineMgr;

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
    private Button btnsalas;

    @FXML
    private Button btnfunciones;

    @FXML
    private Button btnReservas;

    @FXML
    private Button btnVolver;

    @FXML
    void agregarFuncionAction(ActionEvent event) {

    }

    @FXML
    void cargaCartFunciones(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraFuncionesDelCine.class.getResourceAsStream("CarteleraFuncionesDelCine.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void cargaCartSalas(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraSalasDelCine.class.getResourceAsStream("CarteleraSalasDelCine.fxml"));
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
    void cargaCartReservasCine(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(CarteleraReservasDelCine.class.getResourceAsStream("CarteleraReservasDelCine.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    private ObservableList<Ticket> ticketList = FXCollections.observableArrayList();

    public void actualizaCart(){
        ticketList.clear();
        ticketList.addAll(ticketMgr.getAllTickets());
        tabla.setItems(ticketList);
    }

    @FXML
    void editarFuncionAction(ActionEvent event) {

    }

    @FXML
    void eliminarFuncionAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaFuncion"));
        hora.setCellValueFactory(new PropertyValueFactory<>("horaFuncion"));
        sala.setCellValueFactory(new PropertyValueFactory<>("numeroSalaFuncion"));
        pelicula.setCellValueFactory(new PropertyValueFactory<>("peliculaFuncion"));
        local.setCellValueFactory(new PropertyValueFactory<>("localFuncion"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usernameUsuario"));
        asiento.setCellValueFactory(new PropertyValueFactory<>("asientos"));

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
