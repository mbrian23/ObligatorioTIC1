package com.example.moviecrud.ui.movie;

import com.example.moviecrud.business.CineMgr;
import com.example.moviecrud.business.SalaManager;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.ui.CarteleraCines;
import com.example.moviecrud.ui.CarteleraSalas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CineController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Autowired
    CarteleraCines carteleraCine;

    @Autowired
    CineMgr cineMgr;

    @FXML
    TextField nombreCine;


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
    void addCine(ActionEvent event) throws IOException {
        if (nombreCine == null || "".equals(nombreCine)){

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            String cine = nombreCine.getAccessibleText();


            try {
                cineMgr.addCine(cine);

                showAlert("Cine agregado", "Se agrego con exito el cine!");

                carteleraCine.actualizaCartCine();
                close(event);
            } catch (InformacionInvalida informacionInvalida) {
                showAlert(
                        "Informacion invalida !",
                        "Se encontro un error en los datos ingresados.");
            } catch (YaExiste cineYaExiste) {
                showAlert(
                        "Nombre de Cine ya registrado !",
                        "La cadena de cine ya ha sido registrada en el sistema).");
            }

        }
    }
}
