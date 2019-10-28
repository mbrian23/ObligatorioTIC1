package com.example.moviecrud.ui.movie;

import com.example.moviecrud.business.CineMgr;
import com.example.moviecrud.business.LocalMgr;
import com.example.moviecrud.business.SalaManager;
import com.example.moviecrud.business.entities.Cine;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.ui.CarteleraLocales;
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
public class LocalController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Autowired
    CarteleraLocales carteleraLocales;

    @Autowired
    LocalMgr localMgr;

    @Autowired
    CineMgr cineMgr;

    @FXML
    private AnchorPane root;

    @FXML
    private Button addBtn;

    @FXML
    private TextField nombrelocal;

    @FXML
    private TextField cineLocal;


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
    void addLocal(ActionEvent event) throws IOException{
        if (nombrelocal.getText() == null || nombrelocal.getText().equals("") || cineLocal.getText() == null || cineLocal.getText().equals("")){

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {


                String nombreLocalValue = nombrelocal.getText();
                String cineLocalValue = cineLocal.getText();

                try {
                    Cine cine= cineMgr.getCineById(cineLocalValue);
                    localMgr.addLocal(nombreLocalValue,cine);

                    showAlert("Local agregado", "Se agrego con exito el local!");

                    carteleraLocales.actualizaCartLocales();
                    close(event);
                } catch (InformacionInvalida informacionInvalida) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (YaExiste salaYaExiste) {
                    showAlert(
                            "Numero de sala ya registrado !",
                            "La cartelera indicada ya ha sido registrado en el sistema).");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "Datos incorrectos, skere");

            }
        }
    }
}
