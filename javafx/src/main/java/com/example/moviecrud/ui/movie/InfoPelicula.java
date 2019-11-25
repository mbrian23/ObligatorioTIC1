package com.example.moviecrud.ui.movie;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.CineMgr;
import com.example.moviecrud.business.FuncionMgr;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.entities.Funcion;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.ui.CarteleraCines;
import com.example.moviecrud.ui.Showroom;
import com.example.moviecrud.ui.movie.CineController;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

@Component
public class InfoPelicula implements Initializable {

    @Autowired
    CineMgr cineMgr;

    @Autowired
    PeliculaMgr peliculaMgr;

    @FXML
    AnchorPane paneImg;

    @FXML
    private CustomComboBox<String> localidad;

    @FXML
    private  ComboBox<String> cadena;

    @FXML
    private ComboBox<Time> horario;

    @FXML
    private  CustomComboBox<String> sala;

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
        //setBoxesFiltered();
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
        cadena.getItems().clear();



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
                if (!cines.contains(funcionPelicula.get(i).getLocal().getName())) {
                    cines.add(funcionPelicula.get(i).getLocal().getName());
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

    private ObservableList<Funcion> filteredList = FXCollections.observableArrayList();

    public void setBoxesFiltered(){


        String loc = localidad.getValue();
        String sl = sala.getValue();

        if(loc != null){

            for (Funcion funcion : funcionPelicula) {
                if (funcion.getLocal().getName().equals(loc)) {
                    filteredList.add(funcion);
                }
            }
            int z = filteredList.size();
            int f = funcionPelicula.size();
            int min = Math.min(f,z);

            for (int i = 0; i < min; i++) {
                if (!local.contains(filteredList.get(i).getLocal().getName())) {
                    local.add(filteredList.get(i).getLocal().getName());
                }
            }
            localidad.setItems(local);

            if(sala.getValue() == null){

                sala.getItems().clear();
                tipoSala.clear();
                for (int i = 0; i < min; i++) {
                    if (!tipoSala.contains(filteredList.get(i).getSala().getTipo())) {
                        tipoSala.add(filteredList.get(i).getSala().getTipo());
                    }
                }
                sala.setItems(tipoSala);
            }
        }

        if(sl != null){
            local.clear();
            localidad.getItems().clear();

            for (Funcion funcion: funcionPelicula) {
                if (funcion.getSala().getTipo().equals(sl)){
                    filteredList.add(funcion);
                }
            }
            int z = filteredList.size();

            int f = funcionPelicula.size();

            int min = Math.min(f,z);

            for (int i = 0; i < min; i++) {
                if (!local.contains(filteredList.get(i).getLocal().getName())) {
                    local.add(filteredList.get(i).getLocal().getName());
                }
            }
            localidad.setItems(local);

            for (int i = 0; i < min; i++) {
                if (!tipoSala.contains(filteredList.get(i).getSala().getTipo())) {
                    tipoSala.add(filteredList.get(i).getSala().getTipo());
                }
            }
            sala.setItems(tipoSala);
        }
    }

//    public void sfb(){
//        localidad.setCellFactory(lv -> {
//            ListCell<String> cell = new ListCell<String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item);
//                }
//            };
//            cell.setOnMousePressed(e -> {
//                if (! cell.isEmpty()) {
//                    if(sala.getValue() == null){
//                        int z = filteredList.size();
//                        sala.getItems().clear();
//                        tipoSala.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!tipoSala.contains(filteredList.get(i).getSala().getTipo())) {
//                                tipoSala.add(filteredList.get(i).getSala().getTipo());
//                            }
//                        }
//                        sala.setItems(tipoSala);
//
//                    }
//                    if(horario.getValue() == null){
//                        int z = filteredList.size();
//                        horario.getItems().clear();
//                        hor.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!hor.contains(filteredList.get(i).getHoraFuncion())) {
//                                hor.add(filteredList.get(i).getHoraFuncion());
//                            }
//                        }
//                        horario.setItems(hor);
//                    }
//                    if(cadena.getValue() == null){
//                        int z = filteredList.size();
//                        cadena.getItems().clear();
//                        cines.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!cines.contains(filteredList.get(i).getLocal().getnCine())) {
//                                cines.add(filteredList.get(i).getLocal().getnCine());
//                            }
//                        }
//                        cadena.setItems(cines);
//                    }
//
//                }
//            });
//            return cell ;
//        });
//
//        cadena.setCellFactory(lv -> {
//            ListCell<String> cell = new ListCell<String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item);
//                }
//            };
//            cell.setOnMousePressed(e -> {
//                if (! cell.isEmpty()) {
//                    if(sala.getValue() == null){
//                        int z = filteredList.size();
//                        sala.getItems().clear();
//                        tipoSala.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!tipoSala.contains(filteredList.get(i).getSala().getTipo())) {
//                                tipoSala.add(filteredList.get(i).getSala().getTipo());
//                            }
//                        }
//                        sala.setItems(tipoSala);
//
//                    }
//                    if(horario.getValue() == null){
//                        int z = filteredList.size();
//                        horario.getItems().clear();
//                        hor.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!hor.contains(filteredList.get(i).getHoraFuncion())) {
//                                hor.add(filteredList.get(i).getHoraFuncion());
//                            }
//                        }
//                        horario.setItems(hor);
//                    }
//                    if(localidad.getValue() == null){
//                        int z = filteredList.size();
//                        localidad.getItems().clear();
//                        local.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!local.contains(filteredList.get(i).getLocal().getnCine())) {
//                                local.add(filteredList.get(i).getLocal().getnCine());
//                            }
//                        }
//                        localidad.setItems(local);
//                    }
//
//                }
//            });
//            return cell ;
//        });
//
//        sala.setCellFactory(lv -> {
//            ListCell<String> cell = new ListCell<String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item);
//                }
//            };
//            cell.setOnMousePressed(e -> {
//                if (! cell.isEmpty()) {
//                    if(localidad.getValue() == null){
//                        int z = filteredList.size();
//                        localidad.getItems().clear();
//                        local.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!local.contains(filteredList.get(i).getSala().getTipo())) {
//                                local.add(filteredList.get(i).getSala().getTipo());
//                            }
//                        }
//                        localidad.setItems(local);
//
//                    }
//                    if(horario.getValue() == null){
//                        int z = filteredList.size();
//                        horario.getItems().clear();
//                        hor.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!hor.contains(filteredList.get(i).getHoraFuncion())) {
//                                hor.add(filteredList.get(i).getHoraFuncion());
//                            }
//                        }
//                        horario.setItems(hor);
//                    }
//                    if(cadena.getValue() == null){
//                        int z = filteredList.size();
//                        cadena.getItems().clear();
//                        cines.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!cines.contains(filteredList.get(i).getLocal().getnCine())) {
//                                cines.add(filteredList.get(i).getLocal().getnCine());
//                            }
//                        }
//                        cadena.setItems(cines);
//                    }
//
//                }
//            });
//            return cell ;
//        });
//    }

    public void sbox(){
        funcionPelicula.setAll(funcionMgr.getAllFunciones());
        ArrayList<String> salasDePel = new ArrayList<>();
        localidad.setCellFactory(lv -> new ListCell<String>(){

            {
                disableProperty().bind(localidad.valueProperty().isEqualTo(itemProperty()));
                for (Funcion funcion : funcionPelicula) {
                    if (funcion.getLocal().getName().equals(localidad.getValue())) {
                        salasDePel.add(funcion.getSala().getTipo());
                        System.out.println(localidad.getValue());
                    }
                }
                int j = salasDePel.size();
                for (int i = 0; i < j ; i++) {
                    if(!tipoSala.contains(salasDePel.get(i))){
                        sala.setDisabledItems(salasDePel.get(i));
                    }
                }
            }
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
            }
        });
    }

}
