package com.example.moviecrud.ui.movie;

import com.example.moviecrud.business.CineMgr;
import com.example.moviecrud.business.SalaManager;
import com.example.moviecrud.business.UsuarioMgr;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.ui.CarteleraCines;
import com.example.moviecrud.ui.CarteleraSalas;
import com.example.moviecrud.ui.Inicio;
import com.example.moviecrud.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class CineController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    CarteleraCines carteleraCine;

    @Autowired
    public CineController(CarteleraCines carteleraCine){
        this.carteleraCine=carteleraCine;
    }

    @Autowired
    CineMgr cineMgr;

    @Autowired
    UsuarioMgr usuarioMgr;

    @Autowired
    Inicio inicio;



    @FXML
    private TextField nombrecine;

    @FXML
    private Button btnagregar;

    @FXML
    private TextField email;

    @FXML
    private TextField password;


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
    void addCine(ActionEvent event) throws InformacionInvalida, IOException,YaExiste {
        if (nombrecine.getText() == null || "".equals(nombrecine.getText()) || email.getText() == null || "".equals(email.getText()) || password.getText() == null || "".equals(password.getText())){

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            String cine = nombrecine.getText();
            String mail = email.getText();
            String contra = password.getText();

            try {

                cineMgr.addCine(cine);

                showAlert("Cine agregado", "Se agrego con exito el cine!");

                close(event);
                carteleraCine.actualizaCartCine();

            }
            catch (InformacionInvalida informacionInvalida) {
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
