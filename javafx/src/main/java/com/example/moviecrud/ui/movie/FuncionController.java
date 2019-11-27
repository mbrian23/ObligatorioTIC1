package com.example.moviecrud.ui.movie;


import com.example.moviecrud.business.*;
import com.example.moviecrud.business.entities.Cine;
import com.example.moviecrud.business.entities.Local;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.ui.CarteleraFunciones;
import com.example.moviecrud.ui.CarteleraLocales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class FuncionController implements Initializable {

    public FuncionController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBoxes();
    }

    @Autowired
    FuncionMgr funcionMgr;

    @Autowired
    CarteleraFunciones carteleraFunciones;

    @Autowired
    LocalMgr localMgr;

    @Autowired
    SalaManager salaManager;

    @Autowired
    PeliculaMgr peliculaMgr;

    @Autowired
    CineMgr cineMgr;

    @FXML
    private ComboBox local;

    @FXML
    private ComboBox sala;

    @FXML
    private ComboBox pelicula;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private ComboBox hora;

    @FXML
    private ComboBox min;

    @FXML
    private ComboBox cadena;

    @FXML
    private Button add;

    public void fltrLocales(){
        ObservableList<String> locales  = FXCollections.observableArrayList();
        int l = localMgr.getAllLocales().size();
        for (int i = 0; i < l; i++) {
            if(localMgr.getAllLocales().get(i).getCine().getNombre().equals(cadena.getValue())){
                locales.add(localMgr.getAllLocales().get(i).getName());
            }
        }
        local.setItems(locales);
    }

    public void fltrSalas(){
        ObservableList<Long> salas  = FXCollections.observableArrayList();
        int j = salaManager.getAllSalas().size();
        for (int i = 0; i <j ; i++) {
            if(salaManager.getAllSalas().get(i).getLocal().getCine().getNombre().equals(cadena.getValue()) && salaManager.getAllSalas().get(i).getLocal().getName().equals(local.getValue())){

                salas.add(salaManager.getAllSalas().get(i).getNumeroSala());
            }
        }
        sala.setItems(salas);
    }

    public void setBoxes(){
        ObservableList<String> cadenas = FXCollections.observableArrayList();
        int c = cineMgr.getAllCine().size();
        for (int i = 0; i <c ; i++) {
            cadenas.add(cineMgr.getAllCine().get(i).getNombre());
        }
        cadena.setItems(cadenas);


        ObservableList<String> peliculas  = FXCollections.observableArrayList();
        int z = peliculaMgr.getAllPeliculas().size();
        for (int i = 0; i <z ; i++) {
            peliculas.add(peliculaMgr.getAllPeliculas().get(i).getTitulo());
        }
        pelicula.setItems(peliculas);
    }

    @FXML
    void addFuncion(ActionEvent event) throws IOException, YaExiste {
        if (local.getValue() == null || local.getValue().equals("") || sala.getValue() == null || sala.getValue().equals("") || pelicula.getValue() == null || pelicula.getValue().equals("") || hora.getValue() == null || hora.getValue().equals("") || min.getValue() == null || min.getValue().equals("") || fechaInicio.getValue() == null || fechaInicio.getValue().equals("") || fechaFin.getValue() == null || fechaFin.getValue().equals("")){

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {


                String locl = (String) local.getValue();
                Long sla = (Long) sala.getValue();
                String pelicla = (String) pelicula.getValue();
                int hs = Integer.parseInt((String) hora.getValue());
                int mn = Integer.parseInt((String) min.getValue());
                LocalDate fInicio = fechaInicio.getValue();
                LocalDate fFin = fechaFin.getValue();
                Time time = new Time(hs,mn, 0);

                try {


                    Local loc = localMgr.getLocalById(locl);
                    Sala sal = salaManager.getSalaByNumSala(sla);
                    Pelicula pel =peliculaMgr.getPeliculaByName(pelicla);
                    funcionMgr.addFuncion(fInicio, fFin, time, sal, pel, loc);

                    showAlert("Funcion agregada", "Se agrego con exito la funcion!");

                    carteleraFunciones.actualizaCart();
                    close(event);
                } catch (InformacionInvalida informacionInvalida) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                }

            }
            catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "Datos incorrectos, skere");

            }
        }
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
