package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.FuncionMgr;
import com.example.moviecrud.business.SalaManager;
import com.example.moviecrud.business.entities.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Controller
public class Showroom implements Initializable {

    @Autowired
    FuncionMgr funcionMgr;

    @Autowired
    SalaManager salaManager;

    @FXML
    private AnchorPane root;


    @FXML
    private Button compra;

    private Image available = new Image("com/example/moviecrud/ui/images/available.png");

    private Image selected = new Image("com/example/moviecrud/ui/images/selectedSeat.png");

    private Image unavailable = new Image("com/example/moviecrud/ui/images/unavailable.png");

    @FXML
    private ImageView disponible;

    @FXML
    private ImageView elegido;

    @FXML
    private ImageView ocupado;

    @FXML
    private GridPane grid;



    //prueba
    private LocalDate fechainicio = LocalDate.now();

    private LocalDate fechafinal = LocalDate.of(1000, 5, 5);

    private Cine cine = new Cine("movie");

    private Local local = new Local("local", cine);

    private Time horario = new Time(12);

    private Pelicula pelicula = new Pelicula("tit", "gen", "act", "dur", "des");

    private Sala salapr = new Sala(6l, 6l, "sale", 3l);

    private Funcion funcionElegida = new Funcion(fechainicio, horario);



    //prueba

    private ImageView imageView = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        funcionElegida.setSala(salapr);
        funcionElegida.setPelicula(pelicula);
        funcionElegida.setLocal(local);

        disponible.setImage(available);
        elegido.setImage(selected);
        ocupado.setImage(unavailable);


        disponible.setFitHeight(25);
        disponible.setFitWidth(25);
        elegido.setFitWidth(25);
        elegido.setFitHeight(25);
        ocupado.setFitHeight(25);
        ocupado.setFitWidth(25);

        grid.setVgap(30);
        grid.setHgap(30);


        Sala sala = funcionElegida.getSala();

        addSeats(sala);

    }

    public void addSeats(Sala sala) {
        grid.getChildren().clear();

        for (int i = 0; i < sala.getFilas(); i++) {
            for (int j = 0; j < sala.getColumnas(); j++) {
//                ImageView imageView = new ImageView(available);
//                imageView.setFitHeight(25);
//                imageView.setFitWidth(25);
//
//                grid.add(imageView,i,j);

                if (!sala.matriz(sala.getFilas().intValue(), sala.getColumnas().intValue())[i][j]) {
                    ImageView imageView = new ImageView(available);
                    imageView.setFitHeight(25);
                    imageView.setFitWidth(25);

                    grid.add(imageView, i, j);
                    imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (imageView.getImage().equals(unavailable)) {
                                Alert alert = new Alert(Alert.AlertType.WARNING, "El asciento ya esta ocupado", ButtonType.OK);
                                alert.showAndWait();
                                if (alert.getResult() == ButtonType.OK) {
                                    alert.close();
                                }

                            } else {
                                if (imageView.getImage().equals(selected)) {
                                    imageView.setImage(available);
                                } else {
                                    imageView.setImage(selected);
                                }
                            }
                        }
                    });
                } else {
                    ImageView imageView = new ImageView(unavailable);
                    imageView.setFitHeight(25);
                    imageView.setFitWidth(25);

                    grid.add(imageView, i, j);
                    imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (imageView.getImage().equals(unavailable)) {
                                Alert alert = new Alert(Alert.AlertType.WARNING, "El asciento ya esta ocupado", ButtonType.OK);
                                alert.showAndWait();
                                if (alert.getResult() == ButtonType.OK) {
                                    alert.close();
                                }

                            } else {
                                if (imageView.getImage().equals(selected)) {
                                    imageView.setImage(available);
                                } else {
                                    imageView.setImage(selected);
                                }
                            }
                        }
                    });
                }

            }
        }

    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane){
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node: childrens){
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column){
                result = node;
                break;
            }
        }
        return result;
    }


    @FXML
    public void cargaTicket (ActionEvent event) throws Exception {
        for (int i = 0; i < salapr.getFilas() ; i++) {
            for (int j = 0; j < salapr.getColumnas(); j++){
                ImageView imageView = (ImageView) getNodeByRowColumnIndex(i,j,grid);
                if (imageView.getImage().equals(selected)){
                    ImageView ocupado = (ImageView) getNodeByRowColumnIndex(i,j,grid);
                    ocupado.setImage(unavailable);
                    // operacion que cambie este lugar de la matriz a no dispoble
                    funcionElegida.reservaButaca(i,j);
                    funcionMgr.save(funcionElegida);
                }
            }
        }



        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(TicketController.class.getResourceAsStream("ticket.fxml"));
        Scene inicioScene = new Scene(root, 600, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void comprar (ActionEvent event) throws IOException{

    }


}
