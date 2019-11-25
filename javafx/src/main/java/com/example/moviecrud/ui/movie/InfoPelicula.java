package com.example.moviecrud.ui.movie;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.*;
import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.ui.CarteleraCines;
import com.example.moviecrud.ui.TicketController;
import javafx.collections.FXCollections;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class InfoPelicula implements Initializable {

    @Autowired
    FuncionMgr funcionMgr;

    @Autowired
    SalaManager salaManager;

    @Autowired
    LocalMgr localMgr;





    @Autowired
    CineMgr cineMgr;

    @Autowired
    PeliculaMgr peliculaMgr;

    @FXML
    AnchorPane paneImg;

    @FXML
    SplitPane root;

    @FXML
    private CustomComboBox<String> localidad;

    @FXML
    private  ComboBox<String> cadena;

    @FXML
    private ComboBox<Time> horario;

    @FXML
    private  CustomComboBox<String> sala;

    @FXML
    private DatePicker fecha;

    private ObservableList<Funcion> funcionPelicula = FXCollections.observableArrayList();

    private ObservableList<String> tipoSala = FXCollections.observableArrayList();

    private ObservableList<String> local = FXCollections.observableArrayList();

    private ObservableList<String> cines = FXCollections.observableArrayList();

    private ObservableList<Time> hor = FXCollections.observableArrayList();

    private Image available = new Image("com/example/moviecrud/ui/images/available.png");

    private Image selected = new Image("com/example/moviecrud/ui/images/selectedSeat.png");

    private Image unavailable = new Image("com/example/moviecrud/ui/images/unavailable.png");


    @Autowired
    CarteleraCines carteleraCines;

    @FXML
    private Button btncompra;

    @FXML
    private Text titulo;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }



    public void setBox() {

        int f = funcionMgr.getAllFunciones().size();
        String tit = titulo.getText();

        funcionPelicula.clear();
        local.clear();
        cines.clear();
        tipoSala.clear();
        hor.clear();
        localidad.getItems().clear();
        horario.getItems().clear();
        sala.getItems().clear();
        cadena.getItems().clear();



        for (int i = 0; i < f; i++) {
            if (funcionMgr.getAllFunciones().get(i).getPelicula().getTitulo().equals(tit)) {
                funcionPelicula.add(funcionMgr.getAllFunciones().get(i));
            }
        }


        int z = funcionPelicula.size();

        if (z != 0) {
            localidad.setDisable(true);
            sala.setDisable(true);
            horario.setDisable(true);
            fecha.setDisable(true);


                for (int i = 0; i < z; i++) {
                    if (!cines.contains(funcionPelicula.get(i).getLocal().getCine().getNombre())) {
                        cines.add(funcionPelicula.get(i).getLocal().getCine().getNombre());
                    }
                }
                cadena.setItems(cines);




            for (int i = 0; i < z; i++) {
                if (!local.contains(funcionPelicula.get(i).getLocal().getName())) {
                    local.add(funcionPelicula.get(i).getLocal().getName());
                }
            }
            localidad.setItems(local);

            for (int i = 0; i < z; i++) {
                if (!tipoSala.contains(funcionPelicula.get(i).getSala().getTipo())) {
                    tipoSala.add(funcionPelicula.get(i).getSala().getTipo());
                }
            }
            sala.setItems(tipoSala);

            for (int i = 0; i < z; i++) {
                if (!hor.contains(funcionPelicula.get(i).getHoraFuncion())) {
                    hor.add(funcionPelicula.get(i).getHoraFuncion());

                }
            }
            horario.setItems(hor);

            fecha.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
            });
        }


    }
    @FXML
    public void enableLoc (ActionEvent event){
        int tamano = localMgr.getAllLocales().size();
        localidad.getItems().clear();
        ArrayList<Local> locales = (ArrayList<Local>) localMgr.getAllLocales();

        for (int i = 0; i < tamano ; i++) {
            if (cadena.getValue().equals(locales.get(i).getCine().getNombre())){
                local.add(locales.get(i).getName());
            }
        }
        localidad.setItems(local);
        localidad.setDisable(false);
    }

    @FXML
    public void enableSala (ActionEvent event){
        int tamano = salaManager.getAllSalas().size();
        sala.getItems().clear();
        ArrayList<Sala> salas = (ArrayList<Sala>) salaManager.getAllSalas();

        for (int i = 0; i < tamano ; i++) {
            if (sala.getValue().equals(salas.get(i).getTipo())){
                tipoSala.add(salas.get(i).getTipo());
            }
        }
        sala.setItems(tipoSala);

        sala.setDisable(false);
    }
    @FXML
    public void enableHorario (ActionEvent event){
       ArrayList<Funcion> funciones = (ArrayList<Funcion>) funcionMgr.getAllFunciones();
        for (int i = 0; i < funciones.size() ; i++) {
            if (funciones.get(i).getLocal().getName().equals(localidad.getValue()) && funciones.get(i).getFecha().equals(fecha.getValue()) && funciones.get(i).getSala().getTipo().equals(sala.getValue())){
                hor.add(funciones.get(i).getHoraFuncion());
            }
        }
        // problema de que quedan cargadas
        horario.setItems(hor);
        horario.setDisable(false);
    }
    @FXML
    public void enableFecha (ActionEvent event){
        fecha.setDisable(false);
    }

    private ObservableList<Funcion> filteredList = FXCollections.observableArrayList();

    public void setBoxesFiltered(){


        String loc = localidad.getValue();
        String sl = sala.getValue();

        if(loc != null){

            for (Funcion funcion : funcionPelicula) {
                if (funcion.getLocal().getName().equals(loc)) {
                    filteredList.add(funcion);
                }
            }
            int z = filteredList.size();
            int f = funcionPelicula.size();
            int min = Math.min(f,z);

            for (int i = 0; i < min; i++) {
                if (!local.contains(filteredList.get(i).getLocal().getName())) {
                    local.add(filteredList.get(i).getLocal().getName());
                }
            }
            localidad.setItems(local);

            if(sala.getValue() == null){

                sala.getItems().clear();
                tipoSala.clear();
                for (int i = 0; i < min; i++) {
                    if (!tipoSala.contains(filteredList.get(i).getSala().getTipo())) {
                        tipoSala.add(filteredList.get(i).getSala().getTipo());
                    }
                }
                sala.setItems(tipoSala);
            }
        }

        if(sl != null){
            local.clear();
            localidad.getItems().clear();

            for (Funcion funcion: funcionPelicula) {
                if (funcion.getSala().getTipo().equals(sl)){
                    filteredList.add(funcion);
                }
            }
            int z = filteredList.size();

            int f = funcionPelicula.size();

            int min = Math.min(f,z);

            for (int i = 0; i < min; i++) {
                if (!local.contains(filteredList.get(i).getLocal().getName())) {
                    local.add(filteredList.get(i).getLocal().getName());
                }
            }
            localidad.setItems(local);

            for (int i = 0; i < min; i++) {
                if (!tipoSala.contains(filteredList.get(i).getSala().getTipo())) {
                    tipoSala.add(filteredList.get(i).getSala().getTipo());
                }
            }
            sala.setItems(tipoSala);
        }
    }

//    public void sfb(){
//        localidad.setCellFactory(lv -> {
//            ListCell<String> cell = new ListCell<String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item);
//                }
//            };
//            cell.setOnMousePressed(e -> {
//                if (! cell.isEmpty()) {
//                    if(sala.getValue() == null){
//                        int z = filteredList.size();
//                        sala.getItems().clear();
//                        tipoSala.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!tipoSala.contains(filteredList.get(i).getSala().getTipo())) {
//                                tipoSala.add(filteredList.get(i).getSala().getTipo());
//                            }
//                        }
//                        sala.setItems(tipoSala);
//
//                    }
//                    if(horario.getValue() == null){
//                        int z = filteredList.size();
//                        horario.getItems().clear();
//                        hor.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!hor.contains(filteredList.get(i).getHoraFuncion())) {
//                                hor.add(filteredList.get(i).getHoraFuncion());
//                            }
//                        }
//                        horario.setItems(hor);
//                    }
//                    if(cadena.getValue() == null){
//                        int z = filteredList.size();
//                        cadena.getItems().clear();
//                        cines.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!cines.contains(filteredList.get(i).getLocal().getnCine())) {
//                                cines.add(filteredList.get(i).getLocal().getnCine());
//                            }
//                        }
//                        cadena.setItems(cines);
//                    }
//
//                }
//            });
//            return cell ;
//        });
//
//        cadena.setCellFactory(lv -> {
//            ListCell<String> cell = new ListCell<String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item);
//                }
//            };
//            cell.setOnMousePressed(e -> {
//                if (! cell.isEmpty()) {
//                    if(sala.getValue() == null){
//                        int z = filteredList.size();
//                        sala.getItems().clear();
//                        tipoSala.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!tipoSala.contains(filteredList.get(i).getSala().getTipo())) {
//                                tipoSala.add(filteredList.get(i).getSala().getTipo());
//                            }
//                        }
//                        sala.setItems(tipoSala);
//
//                    }
//                    if(horario.getValue() == null){
//                        int z = filteredList.size();
//                        horario.getItems().clear();
//                        hor.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!hor.contains(filteredList.get(i).getHoraFuncion())) {
//                                hor.add(filteredList.get(i).getHoraFuncion());
//                            }
//                        }
//                        horario.setItems(hor);
//                    }
//                    if(localidad.getValue() == null){
//                        int z = filteredList.size();
//                        localidad.getItems().clear();
//                        local.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!local.contains(filteredList.get(i).getLocal().getnCine())) {
//                                local.add(filteredList.get(i).getLocal().getnCine());
//                            }
//                        }
//                        localidad.setItems(local);
//                    }
//
//                }
//            });
//            return cell ;
//        });
//
//        sala.setCellFactory(lv -> {
//            ListCell<String> cell = new ListCell<String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item);
//                }
//            };
//            cell.setOnMousePressed(e -> {
//                if (! cell.isEmpty()) {
//                    if(localidad.getValue() == null){
//                        int z = filteredList.size();
//                        localidad.getItems().clear();
//                        local.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!local.contains(filteredList.get(i).getSala().getTipo())) {
//                                local.add(filteredList.get(i).getSala().getTipo());
//                            }
//                        }
//                        localidad.setItems(local);
//
//                    }
//                    if(horario.getValue() == null){
//                        int z = filteredList.size();
//                        horario.getItems().clear();
//                        hor.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!hor.contains(filteredList.get(i).getHoraFuncion())) {
//                                hor.add(filteredList.get(i).getHoraFuncion());
//                            }
//                        }
//                        horario.setItems(hor);
//                    }
//                    if(cadena.getValue() == null){
//                        int z = filteredList.size();
//                        cadena.getItems().clear();
//                        cines.clear();
//                        for (int i = 0; i < z; i++) {
//                            if (!cines.contains(filteredList.get(i).getLocal().getnCine())) {
//                                cines.add(filteredList.get(i).getLocal().getnCine());
//                            }
//                        }
//                        cadena.setItems(cines);
//                    }
//
//                }
//            });
//            return cell ;
//        });
//    }

    public void sbox(){
        funcionPelicula.setAll(funcionMgr.getAllFunciones());
        ArrayList<String> salasDePel = new ArrayList<>();
        localidad.setCellFactory(lv -> new ListCell<String>(){

            {
                disableProperty().bind(localidad.valueProperty().isEqualTo(itemProperty()));
                for (Funcion funcion : funcionPelicula) {
                    if (funcion.getLocal().getName().equals(localidad.getValue())) {
                        salasDePel.add(funcion.getSala().getTipo());
                        System.out.println(localidad.getValue());
                    }
                }
                int j = salasDePel.size();
                for (int i = 0; i < j ; i++) {
                    if(!tipoSala.contains(salasDePel.get(i))){
                        sala.setDisabledItems(salasDePel.get(i));
                    }
                }
            }
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
            }
        });
        sala.setDisable(false);
    }


    /// PARTE DE SHOWROOM:

    private Sala salaAgregar;
    private Pelicula peliculaAgregar;
    private LocalDate fechaAgregar;
    private Time horarioAgr;
    private Local localAgr;
    private Cine cineAgr;

    private Funcion funcionElegida;

    @FXML
    private Button compra;
    @FXML
    private AnchorPane root2;

    @FXML
    private ImageView disponible;

    @FXML
    private ImageView elegido;

    @FXML
    private ImageView ocupado;

    @FXML
    private GridPane grid;


    static {

        System.setProperty("java.awt.headless", "false");
    }



    @FXML
    public void loadShowroom (ActionEvent event) throws Exception{
        salaAgregar = salaManager.getSalaByNumSala(3l);
        peliculaAgregar = peliculaMgr.getPeliculaByName(titulo.getText());
        fechaAgregar = fecha.getValue();
        horarioAgr = horario.getValue();
        localAgr = localMgr.getLocalById(localidad.getValue());
        cineAgr = cineMgr.getCineById(cadena.getValue());

        List<Funcion> lista = funcionMgr.getAllFunciones();

        for (int i = 0; i < lista.size(); i++) {
            if (salaAgregar.getId().equals(lista.get(i).getSala().getId()) && localAgr.getName().equals(lista.get(i).getLocal().getName()) && peliculaAgregar.getTitulo().equals(lista.get(i).getPelicula().getTitulo()) && fechaAgregar.equals(lista.get(i).getFechaInicio()) ){
                funcionElegida = lista.get(i);
            }
        }

        Stage stage = null;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(InfoPelicula.class.getResourceAsStream("Showroom.fxml"));
        Scene inicioScene = new Scene(root, 1200, 1200);
        stage = new Stage();
        stage.setScene(inicioScene);
        stage.show();


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

        addSeats(funcionElegida.getSala());
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
        for (int i = 0; i <  funcionElegida.getSala().getFilas() ; i++) {
            for (int j = 0; j < funcionElegida.getSala().getColumnas(); j++){
                ImageView imageView = (ImageView) getNodeByRowColumnIndex(i,j,grid);
                if (imageView.getImage().equals(selected)){
//                    ImageView ocupado = (ImageView) getNodeByRowColumnIndex(i,j,grid);
                    ((ImageView) getNodeByRowColumnIndex(i,j,grid)).setImage(unavailable);
                    // operacion que cambie este lugar de la matriz a no dispoble
                    funcionElegida.reservaButaca(i,j);
                    funcionMgr.update(funcionElegida.getId(),funcionElegida);
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
    public void comprar (ActionEvent event) throws Exception {
//        ImageView imageView = new ImageView();
//        for (int i = 0; i < salaAgregar.getFilas() ; i++) {
//            for (int j = 0; j < salaAgregar.getColumnas() ; j++){
//                imageView = (ImageView) getNodeByRowColumnIndex(i,j,grid);
//                if (imageView.getImage().equals(selected)){
//                    funcionElegida.reservaButaca(i,j);
//                    funcionMgr.update(funcionElegida.getId(),funcionElegida);
//                }
//            }
//        }
//        cargaTicket(event);
    }

    public CustomComboBox<String> getLocalidad() {
        return localidad;
    }

    public void setLocalidad(CustomComboBox<String> localidad) {
        this.localidad = localidad;
    }

    public ComboBox<String> getCadena() {
        return cadena;
    }

    public void setCadena(ComboBox<String> cadena) {
        this.cadena = cadena;
    }

    public ComboBox<Time> getHorario() {
        return horario;
    }

    public void setHorario(ComboBox<Time> horario) {
        this.horario = horario;
    }

    public CustomComboBox<String> getSala() {
        return sala;
    }

    public void setSala(CustomComboBox<String> sala) {
        this.sala = sala;
    }

    public DatePicker getFecha() {
        return fecha;
    }

    public void setFecha(DatePicker fecha) {
        this.fecha = fecha;
    }
}
