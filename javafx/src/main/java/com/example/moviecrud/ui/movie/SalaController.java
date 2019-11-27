package com.example.moviecrud.ui.movie;

import com.example.moviecrud.business.CineMgr;
import com.example.moviecrud.business.LocalMgr;
import com.example.moviecrud.business.SalaManager;
import com.example.moviecrud.business.entities.Cine;
import com.example.moviecrud.business.entities.Local;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Sala;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.ui.CarteleraSalas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SalaController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBoxes();
    }

    @Autowired
    CarteleraSalas carteleraSalas;

    @Autowired
    LocalMgr localMgr;

    @Autowired
    CineMgr cineMgr;

    @Autowired
    SalaManager salaManager;

    @FXML
    private AnchorPane root;

    @FXML
    private Button addBtn;

    @FXML
    private TextField nroColumnas;

    @FXML
    private TextField nroFilas;

    @FXML
    private TextField nroSala;

    @FXML
    private ComboBox<String> tipoSala;

    @FXML
    private ComboBox<String> cadenaCine;

    @FXML
    private ComboBox<String> local;

    public void setBoxes() {
        ObservableList<String> locales = FXCollections.observableArrayList();
        int l = localMgr.getAllLocales().size();
        for (int i = 0; i < l; i++) {
            locales.add(localMgr.getAllLocales().get(i).getName());
        }
        local.setItems(locales);

        ObservableList<String> cadena = FXCollections.observableArrayList();
        int c = cineMgr.getAllCine().size();
        for (int i = 0; i < c; i++) {
            cadena.add(cineMgr.getAllCine().get(i).getNombre());
        }
        cadenaCine.setItems(cadena);
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




    @FXML
    void addSala(ActionEvent event) throws IOException{



        if (nroFilas.getText() == null || nroFilas.getText().equals("") || nroColumnas.getText() == null || nroColumnas.getText().equals("") || nroSala.getText() == null || nroSala.getText().equals("")){

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {

                Long numeroSala = Long.parseLong(nroSala.getText());
                String tipoSalaValue = tipoSala.getValue();
                Long numeroColumnas = Long.parseLong(nroColumnas.getText());
                Long numeroFilas = Long.parseLong(nroFilas.getText());
                Local localAgregar = localMgr.getLocalById(local.getValue()) ;
                      //  localMgr.getLocalById(cadenaCine.getValue());

                try {
                    if(!editando) {


                        salaManager.addSala(tipoSalaValue, numeroSala, numeroFilas, numeroColumnas, localAgregar);

                        showAlert("Sala agregada", "Se agrego con exito la Sala!");

                        carteleraSalas.actualizaCartSala();
                        close(event);
                    }else{
                        Sala salaActulizada = new Sala(numeroFilas, numeroColumnas, tipoSalaValue, numeroSala, localAgregar);
                        salaActulizada.setId(idTemp);
                        salaManager.update(salaActulizada);

                        showAlert("Sala Editada", "Se edito con exito la Sala!");
                        close(event);
                        carteleraSalas.actualizaCartSala();
                        editando = false;
                    }
                } catch (InformacionInvalida informacionInvalida) {
                showAlert(
                        "Informacion invalida !",
                        "Se encontro un error en los datos ingresados.");
            } catch (YaExiste salaYaExiste) {
                showAlert(
                        "Numero de sala ya registrado !",
                        "El numero de sala indicado ya ha sido registrado en el sistema).");
            }

        } catch (NumberFormatException e) {

            showAlert(
                    "Datos incorrectos !",
                    "El numero de sala o el numero de lugares no tienen el formato esperado (numerico).");

            }
        }
    }

    private Long idTemp;
    private boolean editando = false;

    public void loadSalaData (Sala sala){

        nroSala.setText(String.valueOf(sala.getNumeroSala()));
        nroFilas.setText(String.valueOf(sala.getFilas()));
        nroFilas.setDisable(true);
        nroColumnas.setText(String.valueOf(sala.getColumnas()));
        nroColumnas.setDisable(true);

        idTemp = sala.getId();
        editando = true;
    }
}
