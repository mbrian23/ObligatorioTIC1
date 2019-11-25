package com.example.moviecrud.ui.movie;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.*;
import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.ui.TicketController;
import com.example.moviecrud.ui.movie.InfoPelicula;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class Showroom implements Initializable {

    @Autowired
    FuncionMgr funcionMgr;

    @Autowired
    SalaManager salaManager;

    @Autowired
    LocalMgr localMgr;

    @Autowired
    PeliculaMgr peliculaMgr;
    @Autowired
    CineMgr cineMgr;

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

    private Cine cine;

    private Local local ;
    private Time horario;

    private Pelicula pelicula;


    private Sala salapr;

    private Funcion funcionElegida;



    //prueba

    private ImageView imageView = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getInfoFunc();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING,  "error en la carga de los datos de la pelicula");
            alert.show();
        }

//        funcionElegida.setSala(salapr);
//        funcionElegida.setLocal(local);
//        funcionElegida.setPelicula(pelicula);
//        funcionElegida.setFechaInicio(fechainicio);
//        funcionElegida.setHoraFuncion(horario);

        List<Funcion> lista = funcionMgr.getAllFunciones();

        for (int i = 0; i < lista.size(); i++) {
            if (salapr == lista.get(i).getSala() && local == lista.get(i).getLocal() && pelicula == lista.get(i).getPelicula() && fechainicio == lista.get(i).getFechaInicio() ){
                funcionElegida = lista.get(i);
            }
        }


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

     //  System.out.println(local.getName());
        addSeats(salapr);

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

                if (!funcionElegida.getMatriz()[i][j]) {
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
        Scene inicioScene = new Scene(root, 800, 550);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    public void comprar (ActionEvent event) throws IOException{
        ImageView imageView = new ImageView();
        for (int i = 0; i < salapr.getFilas() ; i++) {
            for (int j = 0; j < salapr.getColumnas() ; j++){
                imageView = (ImageView) getNodeByRowColumnIndex(i,j,grid);
                if (imageView.getImage().equals(selected)){
                    funcionElegida.reservaButaca(i,j);
                    funcionMgr.update(funcionElegida.getId(),funcionElegida);
                }
            }
        }
    }

    public void getInfoFunc() throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);


        Parent parent = (Parent)fxmlLoader.load(InfoPelicula.class.getResourceAsStream("InfoPelicula2.fxml"));

        System.out.println("xdcvbvnmnbfvdz");

        ComboBox sala = (ComboBox)fxmlLoader.getNamespace().get("sala");

        System.out.println(sala.getValue());
        ComboBox localAgregar = (ComboBox)fxmlLoader.getNamespace().get("localidad");
        ComboBox cadena = (ComboBox)fxmlLoader.getNamespace().get("cadena");
        Text peliculaFuncion = (Text) fxmlLoader.getNamespace().get("titulo");
        ComboBox horarioAgregar = (ComboBox)fxmlLoader.getNamespace().get("horario");
        DatePicker fecha = (DatePicker)fxmlLoader.getNamespace().get("fecha");


        salapr = salaManager.getSalaByNumSala( (Long) sala.getValue());
        local = localMgr.getLocalById((String) localAgregar.getValue());
        cine = cineMgr.getCineById((String) cadena.getValue());
        pelicula = peliculaMgr.getPeliculaByName( peliculaFuncion.getText());
        horario = (Time) horarioAgregar.getValue();
        fechainicio = fecha.getValue();

    }

}
