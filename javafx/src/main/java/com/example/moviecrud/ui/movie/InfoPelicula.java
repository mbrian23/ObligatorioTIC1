package com.example.moviecrud.ui.movie;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.CineMgr;
import com.example.moviecrud.business.FuncionMgr;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.entities.Funcion;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

    @FXML
    private  ComboBox<String> cadena;

    @FXML
    private ComboBox<Time> horario;

    @FXML
    private  ComboBox<String> sala;

    @FXML
    private DatePicker fecha;

    private ObservableList<Funcion> funcionPelicula = FXCollections.observableArrayList();

    private ObservableList<String> tipoSala = FXCollections.observableArrayList();

    private ObservableList<String> local = FXCollections.observableArrayList();

    private ObservableList<String> cines = FXCollections.observableArrayList();

    private ObservableList<Time> hor = FXCollections.observableArrayList();

    @Autowired
    CarteleraCines carteleraCines;

    @FXML
    private Button btncompra;

    @FXML
    private Text titulo;


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

    }

    @Autowired
    FuncionMgr funcionMgr;


    public void setBox() {

        int f = funcionMgr.getAllFunciones().size();
        String tit = titulo.getText();

        funcionPelicula.clear();
        local.clear();
        cines.clear();
        tipoSala.clear();
        hor.clear();
        localidad.getItems().clear();
        horario.getItems().clear();
        sala.getItems().clear();



        for (int i = 0; i < f; i++) {
            if (funcionMgr.getAllFunciones().get(i).getPelicula().getTitulo().equals(tit)) {
                funcionPelicula.add(funcionMgr.getAllFunciones().get(i));
            }
        }


        int z = funcionPelicula.size();

        if (z != 0) {

            for (int i = 0; i < z; i++) {
                if (!local.contains(funcionPelicula.get(i).getLocal().getName())) {
                    local.add(funcionPelicula.get(i).getLocal().getName());
                }
            }
            localidad.setItems(local);

            for (int i = 0; i < z; i++) {
                if (!cines.contains(funcionPelicula.get(i).getLocal().getnCine())) {
                    cines.add(funcionPelicula.get(i).getLocal().getnCine());
                }
            }
            cadena.setItems(cines);

            for (int i = 0; i < z; i++) {
                if (!tipoSala.contains(funcionPelicula.get(i).getSala().getTipo())) {
                    tipoSala.add(funcionPelicula.get(i).getSala().getTipo());
                }
            }
            sala.setItems(tipoSala);

            for (int i = 0; i < z; i++) {
                if (!hor.contains(funcionPelicula.get(i).getHoraFuncion())) {
                    hor.add(funcionPelicula.get(i).getHoraFuncion());
                }
            }
            horario.setItems(hor);

            fecha.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
            });
        }
    }
}
