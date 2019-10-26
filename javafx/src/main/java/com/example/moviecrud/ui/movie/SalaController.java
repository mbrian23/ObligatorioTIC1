package com.example.moviecrud.ui.movie;

import com.example.moviecrud.business.SalaManager;
import com.example.moviecrud.business.exceptions.InformacionPeliculaInvalida;
import com.example.moviecrud.business.exceptions.InformacionSalaInvalida;
import com.example.moviecrud.business.exceptions.PeliculaYaExiste;
import com.example.moviecrud.business.exceptions.SalaYaExiste;
import com.example.moviecrud.ui.CarteleraSalas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    }

    @Autowired
    CarteleraSalas carteleraSalas;

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

                try {
                    salaManager.addSala(tipoSalaValue, numeroSala, numeroFilas, numeroColumnas);

                    showAlert("Sala agregada", "Se agrego con exito la Sala!");

                    carteleraSalas.actualizaCartSala();
                    close(event);
                } catch (InformacionSalaInvalida informacionSalaInvalida) {
                showAlert(
                        "Informacion invalida !",
                        "Se encontro un error en los datos ingresados.");
            } catch (SalaYaExiste salaYaExiste) {
                showAlert(
                        "Numero de sla ya registrado !",
                        "El numero de sala indicado ya ha sido registrado en el sistema).");
            }

        } catch (NumberFormatException e) {

            showAlert(
                    "Datos incorrectos !",
                    "El numero de sala o el numero de lugares no tienen el formato esperado (numerico).");

        }
    }
}
}
