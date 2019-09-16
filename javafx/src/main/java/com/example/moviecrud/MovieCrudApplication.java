package com.example.moviecrud;



import com.example.moviecrud.ui.Principal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MovieCrudApplication extends Application {

    private static ConfigurableApplicationContext context;

    private Parent root;

    @Override
    public void init() throws Exception {
        MovieCrudApplication.context = SpringApplication.run(MovieCrudApplication.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);


        root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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

