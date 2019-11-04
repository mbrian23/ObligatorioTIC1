package com.example.moviecrud.ui;

import com.example.moviecrud.business.FuncionMgr;
import com.example.moviecrud.business.SalaManager;
import com.example.moviecrud.business.entities.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    private GridPane grid;



    //prueba
    private LocalDate fechainicio= LocalDate.now();

    private LocalDate fechafinal = LocalDate.of(1000,5,5);

    private Cine cine = new Cine("movie");

    private Local local = new Local("local",cine );

    private Time horario = new Time(12);

    private Pelicula pelicula = new Pelicula("tit", "gen", "act", "dur", "des");

    private Sala salapr = new Sala(4l,4l,"sale", 3l);

    private Funcion funcionElegida = new Funcion(fechainicio,fechafinal,horario,salapr, pelicula);


    //prueba

    private ImageView imageView = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        available.setFitHeight(25);
  //      available.setFitWidth(25);

        grid.setVgap(30);
        grid.setHgap(30);


        Sala sala = funcionElegida.getSala();

        addSeats(sala);

    }

    public void addSeats (Sala sala){
        grid.getChildren().clear();

        for (int i = 0; i < sala.getColumnas()  ; i++) {
            for (int j = 0; j < sala.getFilas() ; j++ ) {
                ImageView imageView = new ImageView(available);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);

                grid.add(imageView,i,j);
            }

        }

    }
}
