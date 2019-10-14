package com.example.moviecrud.ui.movie;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.exceptions.InformacionPeliculaInvalida;
import com.example.moviecrud.business.exceptions.PeliculaNoExiste;
import com.example.moviecrud.business.exceptions.PeliculaYaExiste;
import com.example.moviecrud.ui.Principal;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.LockInfo;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class MovieController implements Initializable {

    Principal principal;

    @Autowired
    public MovieController(Principal principal){
        this.principal=principal;
    }

    @Autowired
    private PeliculaMgr peliculaMgr;

    @FXML
    private Button btnAdd;

    @FXML
    private AnchorPane anchorPane;

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
    private Button addImage;


    @FXML
    private File img;


    @FXML
    public String  saveImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elija Imagen");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg"));
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        img = fileChooser.showOpenDialog(stage);
        return img.getPath();
    }

    //parte del editar
    private boolean editando = false;
    private Long idTemp;


    //Filtrado
    @FXML
    private ListView<String> listaBusqueda;

    private ObservableList<Pelicula> movieLista = FXCollections.observableArrayList();

    private ObservableList<String> movieListaString = FXCollections.observableArrayList();

    @FXML
    private TextField buscador;

    FilteredList<Pelicula> filteredData = new FilteredList<>(movieLista, s -> true);







    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addPelicula(ActionEvent event) throws IOException {
            if (titulo.getText() == null || titulo.getText().equals("") || actores.getText() == null || actores.getText().equals("")
            || duracion.getText() == null || duracion.getText().equals("") || descripcion.getText() == null || descripcion.getText().equals("")){

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

            } else {
                try {

                String tituloAgregar = titulo.getText();
                String generoAgregar = genero.getValue();
                String actoresAgregar = actores.getText();
                String duracionAgregar = duracion.getText();
                String descripcionAgregar = descripcion.getText();





                try {
                    if(!editando) {
                        peliculaMgr.addPelicula(tituloAgregar, generoAgregar, actoresAgregar, duracionAgregar, descripcionAgregar);

                        showAlert("Pelicula agregada", "Se agrego con exito la Pelicula!");


                        close(event);
                        principal.actualizaCart();
                    } else {


                            Pelicula peliculaActualizada = new Pelicula(tituloAgregar,generoAgregar ,actoresAgregar,duracionAgregar ,descripcionAgregar);
                            peliculaMgr.update(idTemp,peliculaActualizada);

                            showAlert("Pelicula Editada", "Se edito con exito la Pelicula!");
                            close(event);
                            principal.actualizaCart();


                    }
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
                        principal.actualizaCart();

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
                String generoAgregar = genero.getValue();
                String actoresEditado = actoresNew.getText();
                String duracionEditado = duracionNew.getText();
                String descripcionEditado = descripcionNew.getText();


                try {

                    peliculaMgr.editarPelicula(tituloAEdi, tituloEditado,generoAgregar ,actoresEditado,duracionEditado,descripcionEditado);

                    showAlert("Pelicula agregada", "Se agrego con exito la Pelicula!");
                    principal.actualizaCart();

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
//
//
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);
//
//        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("AddPelicula.fxml"));
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
//
//
//
//        MovieController movieController = fxmlLoader.getController();
//        //movieController.loadMovieData();

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

    public void loadMovieData (Pelicula pelicula){
        try {
            titulo.setText(pelicula.getTitulo());
            actores.setText(pelicula.getActores());
            descripcion.setText(pelicula.getDescripcion());
            duracion.setText(pelicula.getDuracion());
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        idTemp = pelicula.getId();
        editando = true;
    }

    public void actualizarLista (){
        movieLista.clear();
        movieLista.addAll(peliculaMgr.getAllPeliculas());
        //listaBusqueda.setItems(movieLista);
        for (Pelicula pelicula: movieLista
             ) {
            movieListaString.add(pelicula.getTitulo());
        }

        listaBusqueda.setItems(movieListaString);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //actualizarLista();
//       buscador.textProperty().addListener((observable, oldValue, newValue) -> {
//           filteredData.setPredicate(pelicula -> {
//               // If filter text is empty, display all persons.
//               if (newValue == null || newValue.isEmpty()) {
//                   return true;
//               }
//
//               // Compare first name and last name of every person with filter text.
//               String lowerCaseFilter = newValue.toLowerCase();
//
//               if (pelicula.getTitulo().toLowerCase().contains(lowerCaseFilter)) {
//                   return true; // Filter matches first name.
//               } else if (pelicula.getGenero().toLowerCase().contains(lowerCaseFilter)) {
//                   return true; // Filter matches last name.
//               }
//               return false; // Does not match.
//           });
//       });
//
//        // 3. Wrap the FilteredList in a SortedList.
//        ObservableList<Pelicula> sortedData = new FilteredList<>(filteredData);
//        movieListaString.clear();
//        for (Pelicula pelicula: sortedData
//             ) {
//            movieListaString.add(pelicula.getTitulo());
//        }
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//      //  sortedData.comparatorProperty().bind(listaBusqueda.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        listaBusqueda.setItems(movieListaString);
    }



}
