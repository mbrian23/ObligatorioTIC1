package com.example.moviecrud.ui.movie;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.exceptions.InformacionPeliculaInvalida;
import com.example.moviecrud.business.exceptions.PeliculaNoExiste;
import com.example.moviecrud.business.exceptions.PeliculaYaExiste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class MovieController {

    @Autowired
    private PeliculaMgr peliculaMgr;

    @FXML
    private Button btnAdd;

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


    //eliminar
    @FXML
    private Button btnDelete;

    @FXML
    private TextField tituloAEliminar;

    //editar
    @FXML
    private Button btnEdit;

    @FXML
    private TextField tituloAEditar;

    @FXML
    private TextField tituloNew;

    @FXML
    private TextField actoresNew;

    @FXML
    private TextField duracionNew;

    @FXML
    private TextArea descripcionNew;





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
               // String generoAgregar = genero.getEditor().getText();
                String actoresAgregar = actores.getText();
                String duracionAgregar = duracion.getText();
                String descripcionAgregar = descripcion.getText();


                try {

                    peliculaMgr.addPelicula(tituloAgregar/*,generoAgregar*/,actoresAgregar,duracionAgregar,descripcionAgregar);

                    showAlert("Pelicula agregada", "Se agrego con exito la Pelicula!");


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



        @FXML
        void eliminarPelicula (ActionEvent event){
            if (tituloAEliminar.getText() == null || tituloAEliminar.equals("")){

                showAlert(
                        "Datos faltantes!",
                        "No se ingresaron los datos necesarios para completar el ingreso.");

            } else {
                try {

                    String tituloEliminar = tituloAEliminar.getText();


                    try {

                        peliculaMgr.eliminarPelicula(tituloEliminar);

                        showAlert("Pelicula eliminada", "Se elimino con exito la Pelicula!");

                        close(event);
                    } catch (InformacionPeliculaInvalida informacionPeliculaInvalida) {
                        showAlert(
                                "Informacion invalida !",
                                "Se encontro un error en los datos ingresados.");
                    } catch (PeliculaNoExiste peliculaNoExiste) {
                        showAlert(
                                "Pelicula no registrada !",
                                "La pelicula no esta registrada en el sistema.");
                    }

                } catch (NumberFormatException e) {

                    showAlert(
                            "Datos incorrectos !",
                            "El documento no tiene el formato esperado (numerico).");

                }
            }

        }


    @FXML
    void editarPeliculaAction (ActionEvent event){
        if (tituloNew.getText() == null || tituloNew.getText().equals("") || actoresNew.getText() == null || actoresNew.getText().equals("")
                || duracionNew.getText() == null || duracionNew.getText().equals("") || descripcionNew.getText() == null || descripcionNew.getText().equals("")){

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {

                String tituloAEdi = tituloAEditar.getText();
                String tituloEditado = tituloNew.getText();
                // String generoAgregar = genero.getEditor().getText();
                String actoresEditado = actoresNew.getText();
                String duracionEditado = duracionNew.getText();
                String descripcionEditado = descripcionNew.getText();


                try {

                    peliculaMgr.editarPelicula(tituloAEdi, tituloEditado/*,generoAgregar*/,actoresEditado,duracionEditado,descripcionEditado);

                    showAlert("Pelicula agregada", "Se agrego con exito la Pelicula!");

                    close(event);
                } catch (InformacionPeliculaInvalida informacionPeliculaInvalida) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (PeliculaNoExiste peliculaNoExiste) {
                    showAlert(
                            "La pelicula no existe !",
                            "El documento indicado no ha sido registrado en el sistema).");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "El documento no tiene el formato esperado (numerico).");

            }
        }
    }

    @FXML
    void editarPeliculaFinalAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("EditarFinal.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

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
