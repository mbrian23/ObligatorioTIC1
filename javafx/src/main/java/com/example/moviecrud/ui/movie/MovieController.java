package com.example.moviecrud.ui.movie;

import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.exceptions.InformacionPeliculaInvalida;
import com.example.moviecrud.business.exceptions.PeliculaYaExiste;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.event.*;


@Component
public class MovieController {

    @Autowired
    private PeliculaMgr peliculaMgr;

    @FXML
    private Button mItemAgregarCliente;

    @FXML
    private Button btnClose;

    @FXML
    private TextField titulo;

    @FXML
    private TextField actores;

    @FXML
    private ComboBox<String> genero;

    @FXML
    private TextField duracion;

    @FXML
    private TextArea descripcion;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addPelicula(ActionEvent event) {
            if (titulo.getText() == null || titulo.getText().equals("") || actores.getText() == null || actores.getText().equals("")
            || duracion.getText() == null || duracion.getText().equals("") || descripcion.getText() == null || descripcion.getText().equals("")){

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

            } else {
                try {

                String tituloAgregar = titulo.getText();
                String generoAgregar = genero.getEditor().getText();
                String actoresAgregar = actores.getText();
                String duracionAgregar = duracion.getText();
                String descripcionAgregar = descripcion.getText();


                try {

                    peliculaMgr.addPelicula(tituloAgregar,generoAgregar,actoresAgregar,duracionAgregar,descripcionAgregar);

                    showAlert("Cliente agregado", "Se agrego con exito el cliente!");

                    close(event);
                } catch (InformacionPeliculaInvalida informacionPeliculaInvalida) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (PeliculaYaExiste peliculaYaExiste) {
                    showAlert(
                            "Documento ya registrado !",
                            "El documento indicado ya ha sido registrado en el sistema).");
                }

                } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "El documento no tiene el formato esperado (numerico).");

                 }
             }
        }



    private void clean (){
        titulo.setText(null);
        actores.setText(null);
        genero.setVisibleRowCount(1);
        duracion.setText(null);
        descripcion.setText(null);
    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


}
