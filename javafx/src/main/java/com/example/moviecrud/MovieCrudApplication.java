package com.example.moviecrud;



import com.example.moviecrud.ui.InfoPelicula;
import com.example.moviecrud.ui.Inicio;
import com.example.moviecrud.ui.Principal;
import com.example.moviecrud.ui.movie.MovieController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.stream.Stream;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.image.Image;

@SpringBootApplication
public class MovieCrudApplication extends Application {

    private static ConfigurableApplicationContext context;

    private Parent root;

    @Override
    public void init() throws Exception {
        MovieCrudApplication.context = SpringApplication.run(MovieCrudApplication.class);
    }

    private static final double WIDTH = 450, HEIGHT = 480;
   // private Timeline animation;

    public Parent createContent()throws Exception {
        // load images
        Image[] images = new Image[3];
        images[0] = new Image(MovieCrudApplication.class.getResource("/com/example/moviecrud/ui/images/termi.jpg").toExternalForm(), false);
        images[1] = new Image(MovieCrudApplication.class.getResource("/com/example/moviecrud/ui/images/termi2.jpg").toExternalForm(), false);
        images[2] = new Image(MovieCrudApplication.class.getResource("/com/example/moviecrud/ui/images/logo2.png").toExternalForm(), false);

        // create display shelf
        DisplayShelf displayShelf = new DisplayShelf(images);
        displayShelf.setPrefSize(WIDTH, HEIGHT);
        displayShelf.getItems()[0].setOnMouseClicked(abrirPaginaPelicula());

        return displayShelf;
    }

    EventHandler abrirPaginaPelicula() throws Exception {
        EventHandler handler = new EventHandler() {
            @Override
            public void handle(Event event) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

                Parent root = null;
                try {
                    root = fxmlLoader.load(MovieController.class.getResourceAsStream("InfoPelicula.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        };
        return handler;
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        root = fxmlLoader.load(Principal.class.getResourceAsStream("Cartelera.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //primaryStage.setResizable(true);


//        primaryStage.setResizable(true);
//        primaryStage.setScene(new Scene(createContent()));
//        primaryStage.show();


    }


    @Override
    public void stop() {
        MovieCrudApplication.getContext().close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }


}

