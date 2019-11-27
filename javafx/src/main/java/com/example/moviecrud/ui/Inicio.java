package com.example.moviecrud.ui;

import com.example.moviecrud.DisplayShelf;
import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.PeliculaMgr;
import com.example.moviecrud.business.UsuarioMgr;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Usuario;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.ui.movie.MovieController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

@Component
public class Inicio implements Initializable {

    MovieCrudApplication movieCrudApplication;

    @Autowired
    public Inicio (MovieCrudApplication movieCrudApplication){ this.movieCrudApplication=movieCrudApplication;}

    @Autowired
    PeliculaMgr peliculaMgr;

    @Autowired
    UsuarioMgr usuarioMgr;

    @FXML
    private AnchorPane root;

    @FXML
    private Pane pane;

    @FXML
    private Button buscador;


    @FXML
    private ImageView logo;

    @FXML
    private Text userActivo;

    public static void setUsStatic(String usStatic) {
        Inicio.usStatic = usStatic;
    }

    public static String getUsStatic() {
        return usStatic;
    }

    private static String usStatic;



    @FXML
    private TextField buscadorInicio;


    private Image logoAgregar = new Image("com/example/moviecrud/ui/images/logo2.png");

    private boolean bool1 = false;
    private boolean bool2 = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            logo.setImage(logoAgregar);
            pane.getChildren().add(movieCrudApplication.createContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String us;

    @FXML
    private TextField userIngreso;

    @FXML
    private TextField userPass;

    @FXML
    void ingresoNormal (ActionEvent event) {
        us = userIngreso.getText();
        String pass= userPass.getText();

        try {
            Usuario usuario = (Usuario) usuarioMgr.getUsuarioByUsername(us);
            if (BCrypt.checkpw(pass, usuario.getPassword())) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);
                Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
                Scene inicioScene = new Scene(root, 800, 550);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(inicioScene);
                window.show();
                Inicio inicio = fxmlLoader.getController();
                setUsStatic(us);
                inicio.loadUsData(us);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Contrasena incorrecta");
                alert.showAndWait();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage() + "Algun dato ingresado no es correcto o no esta asociado a nigun usuario del sistema");
            alert.showAndWait();
        }
    }

    @FXML
    void cargaInicio (ActionEvent event) throws Exception {
        us = userIngreso.getText();
//        String pass= userPass.getText();
//        try {
//            Usuario usuario = (Usuario) usuarioMgr.getUsuarioByUsername(us);
//            if (BCrypt.checkpw(pass, usuario.getPassword())) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();

        Inicio inicio = fxmlLoader.getController();
//        setUsStatic(us);
        inicio.loadUsData(us);
//    } else {
//        Alert alert = new Alert(Alert.AlertType.WARNING, "Contrasena incorrecta");
//        alert.showAndWait();
//    }
//
//} catch (Exception e) {
//        Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage() + "Algun dato ingresado no es correcto o no esta asociado a nigun usuario del sistema");
//        alert.showAndWait();
//        }
    }

    public void loadUsData (String us){
        try {
            userActivo.setText(us);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void adminEmpresa (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("Cartelera.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void iniciarSesion (ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("iniciarSesion.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void iniciarSesionAdmin (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(InicioAdmiController.class.getResourceAsStream("iniciarSesionAdmin.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void cargaRegistroUsuario (ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("RegistroUsuario.fxml"));
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }



    @FXML
    public void filtrado() throws Exception{
        int z = peliculaMgr.getAllPeliculas().size();
        ObservableList<Pelicula> listaPeliculas = FXCollections.observableArrayList();
        listaPeliculas.clear();
        listaPeliculas.addAll(peliculaMgr.getAllPeliculas());

        Image[] aPeliculas = new Image[z];
        DisplayShelf displayShelf = null;
        int posFin=0;
        ArrayList<Integer> claves = new ArrayList<>();


        if (buscadorInicio.getText() != null) {
            bool1 = false;
            bool2 = false;
            for (int i = 0; i < z; i++) {
                if (listaPeliculas.get(i).getTitulo().toLowerCase().contains(buscadorInicio.getText().toLowerCase())) {
                    claves.add(i);
                    byte [] img = listaPeliculas.get(i).getMovieImage();
                    ByteArrayInputStream bis = new ByteArrayInputStream(img);
                    BufferedImage bImage = ImageIO.read(bis);
                    Image image = SwingFXUtils.toFXImage(bImage, null);
                    aPeliculas[posFin] = image;
                    posFin++;
                    displayShelf = new DisplayShelf(aPeliculas);
                    final double WIDTH = 700, HEIGHT = 400;
                    displayShelf.setPrefSize(WIDTH, HEIGHT);
                    bool1 = true;

                }else if (listaPeliculas.get(i).getGenero().toLowerCase().contains(buscadorInicio.getText().toLowerCase())){
                    claves.add(i);
                    byte [] img = listaPeliculas.get(i).getMovieImage();
                    ByteArrayInputStream bis = new ByteArrayInputStream(img);
                    BufferedImage bImage = ImageIO.read(bis);
                    Image image = SwingFXUtils.toFXImage(bImage, null);
                    aPeliculas[posFin] = image;
                    posFin++;
                    displayShelf = new DisplayShelf(aPeliculas);
                    final double WIDTH = 700, HEIGHT = 400;
                    displayShelf.setPrefSize(WIDTH, HEIGHT);
                    bool2 = true;
                }
            }

            if (!bool1 && !bool2){
                Image[] todasImg = new Image[z];
                listaPeliculas.clear();
                listaPeliculas.addAll(peliculaMgr.getAllPeliculas());
                for (int j = 0; j <z ; j++) {
                    byte [] bi = listaPeliculas.get(j).getMovieImage();
                    ByteArrayInputStream bais = new ByteArrayInputStream(bi);
                    BufferedImage bImage = ImageIO.read(bais);
                    Image image = SwingFXUtils.toFXImage(bImage, null);
                    todasImg[j] = image;
                }
                showAlert("No hay coincidencias", "No se ha encontrado una pelicula con esas especificaciones");
                displayShelf = new DisplayShelf(todasImg);
                final double WIDTH = 700, HEIGHT = 400;
                displayShelf.setPrefSize(WIDTH, HEIGHT);
                for (int i = 0; i <z ; i++) {
                    displayShelf.getItems()[i].setId(String.valueOf(i));
                    displayShelf.getItems()[i].setOnMouseClicked(movieCrudApplication.abrirPaginaPelicula());
                }
                bool1=false;
                bool2=false;
            }
            for (int j = 0; j <claves.size() ; j++) {
                int key = claves.get(j);
                displayShelf.getItems()[j].setId(String.valueOf(key));
                displayShelf.getItems()[j].setOnMouseClicked(movieCrudApplication.abrirPaginaPelicula());
            }
            claves.clear();
            try {
                pane.getChildren().add(displayShelf);
            } catch (Exception e) {
                e.printStackTrace();
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

    private boolean sesionIniciada;
    // Si hay una sesion debemos hacer desaparecer el iniciar sesion por un salir y poner por algun lado el username

    @FXML
    Button prueba;

    @FXML
    Button registro;

    @FXML
    Button volver;

    @FXML
    private TextField usernameRegistro;

    @FXML
    private TextField emailRegistro;

    @FXML
    private TextField passwordRegistro;

    @FXML
    private TextField passwordRepeatRegistro;




    public void crearClienteFinal(ActionEvent event) throws InformacionInvalida, YaExiste, IOException {
        String usernameNuevo = usernameRegistro.getText();
        String emailNuevo = emailRegistro.getText();
        String passwordNueva = passwordRegistro.getText();

        if(passwordRegistro != passwordRepeatRegistro){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Contrasena y repetir contrasena son diferentes");
            alert.showAndWait();
        }

        if (usernameNuevo == null || passwordNueva==null || emailNuevo== null ||
                usernameNuevo.equals("") || passwordNueva.equals("") || emailNuevo.equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING, "Algun dato ya existe!");
            alert.showAndWait();

        } else {
            usuarioMgr.addUsuario(usernameNuevo, passwordNueva, emailNuevo, "cliente");


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

            Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
            Scene inicioScene = new Scene(root, 800, 550);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(inicioScene);
            window.show();
        }

    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }
}
