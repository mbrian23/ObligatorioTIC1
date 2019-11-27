package com.example.moviecrud.ui.movie;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.*;
import com.example.moviecrud.business.entities.*;
import com.example.moviecrud.ui.CarteleraCines;
import com.example.moviecrud.ui.Inicio;
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
    TicketController ticketController;

    @Autowired
    UsuarioMgr usuarioMgr;

    @Autowired
    Inicio inicio;





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

    private ObservableList<LocalDate> fechas = FXCollections.observableArrayList();



    private Image available = new Image("com/example/moviecrud/ui/images/available.png");

    private Image selected = new Image("com/example/moviecrud/ui/images/selectedSeat.png");

    private Image unavailable = new Image("com/example/moviecrud/ui/images/unavailable.png");


    @Autowired
    CarteleraCines carteleraCines;

    @FXML
    private Button btncompra;

    @FXML
    private Text titulo;

    @FXML
    private Text userActivo;


    private Usuario usuarioActivo;
    private String usNameActivo;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usNameActivo =  inicio.getUs();
        loadUsData(usNameActivo);

    }

    public String getUsNameActivo() {
        return usNameActivo;
    }

    public void setUsNameActivo(String usNameActivo) {
        this.usNameActivo = usNameActivo;
    }

    public void loadUsData (String us){
        try {
            userActivo.setText(us);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }



    public void setBox() {

        int f = funcionMgr.getAllFunciones().size();
        String tit = titulo.getText();

//        funcionPelicula.clear();
//        local.clear();
//        cines.clear();
//        tipoSala.clear();
//        hor.clear();
//        localidad.getItems().clear();
//        horario.getItems().clear();
//        sala.getItems().clear();
//        cadena.getItems().clear();



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




//            for (int i = 0; i < z; i++) {
//                if (!local.contains(funcionPelicula.get(i).getLocal().getName())) {
//                    local.add(funcionPelicula.get(i).getLocal().getName());
//                }
//            }
//            localidad.setItems(local);
//
//            for (int i = 0; i < z; i++) {
//                if (!tipoSala.contains(funcionPelicula.get(i).getSala().getTipo())) {
//                    tipoSala.add(funcionPelicula.get(i).getSala().getTipo());
//                }
//            }
//            sala.setItems(tipoSala);
//
//            for (int i = 0; i < z; i++) {
//                if (!hor.contains(funcionPelicula.get(i).getHoraFuncion())) {
//                    hor.add(funcionPelicula.get(i).getHoraFuncion());
//
//                }
//            }
//            horario.setItems(hor);
//
//            fecha.setDayCellFactory(picker -> new DateCell() {
//                @Override
//                public void updateItem(LocalDate date, boolean empty) {
//                    super.updateItem(date, empty);
//                    LocalDate today = LocalDate.now();
//                    setDisable(empty || date.compareTo(today) < 0);
//                }
//            });
        }


    }
    @FXML
    public void enableLoc (ActionEvent event){

        int tamano1 = funcionPelicula.size();
        int u = 0;
        for (int i = 0; i < tamano1 - u ; i++) {
            if(!funcionPelicula.get(i).getLocal().getCine().getNombre().equals(cadena.getValue())){
                funcionPelicula.remove(i);
                u++;
            }
        }
        local.clear();
        for (int i = 0; i <funcionPelicula.size() ; i++) {
            if(!local.contains(funcionPelicula.get(i).getLocal().getName()) && funcionPelicula.get(i).getLocal().getCine().getNombre().equals(cadena.getValue())) {
                local.add(funcionPelicula.get(i).getLocal().getName());
            }
        }

//        int tamano = localMgr.getAllLocales().size();
//        local.clear();
//        localidad.getItems().clear();
//        ArrayList<Local> locales = (ArrayList<Local>) localMgr.getAllLocales();
//
//        for (int i = 0; i < tamano ; i++) {
//            if (cadena.getValue().equals(locales.get(i).getCine().getNombre()) && !local.contains(locales.get(i).getName())){
//                local.add(locales.get(i).getName());
//            }
//        }
        localidad.setItems(local);

        localidad.setDisable(false);
        cadena.setDisable(true);

    }

    @FXML
    public void enableSala (ActionEvent event){
        int tamano1 = funcionPelicula.size();
        int u = 0;
        for (int i = 0; i < tamano1 - u ; i++) {
            if(!funcionPelicula.get(i).getSala().getTipo().equals(sala.getValue())){
                funcionPelicula.remove(i);
                u++;
            }
        }
        System.out.println(funcionPelicula.size());
        tipoSala.clear();
        for (int i = 0; i <funcionPelicula.size() ; i++) {
            if(!tipoSala.contains(funcionPelicula.get(i).getSala().getTipo()) && funcionPelicula.get(i).getLocal().getName().equals(localidad.getValue()) ) {
                tipoSala.add(funcionPelicula.get(i).getSala().getTipo());
            }
        }


//        int tamano = salaManager.getAllSalas().size();
//        sala.getItems().clear();
//        tipoSala.clear();
//        ArrayList<Sala> salas = (ArrayList<Sala>) salaManager.getAllSalas();
//
//        for (int i = 0; i < tamano ; i++) {
//            if (sala.getValue().equals(salas.get(i).getTipo()) ){
//                tipoSala.add(salas.get(i).getTipo());
//            }
//        }
        sala.setItems(tipoSala);

        sala.setDisable(false);
        localidad.setDisable(true);
    }
    @FXML
    public void enableHorario (ActionEvent event){
        int tamano1 = funcionPelicula.size();
        int u = 0;
        for (int i = 0; i < tamano1 - u ; i++) {
            if(!(funcionPelicula.get(i).getHoraFuncion()).equals(horario.getValue())){
                funcionPelicula.remove(i);
                u++;
            }
        }
        hor.clear();
        for (int i = 0; i <funcionPelicula.size() ; i++) {
            if(!hor.contains(funcionPelicula.get(i).getHoraFuncion()) /* && funcionPelicula.get(i).getFecha().equals(fecha.getValue()) */  ) {
                hor.add(funcionPelicula.get(i).getHoraFuncion());
            }
        }

//        ArrayList<Funcion> funciones = (ArrayList<Funcion>) funcionMgr.getAllFunciones();
//       horario.getItems().clear();
//       hor.clear();
//        for (int i = 0; i < funciones.size() ; i++) {
//            if (funciones.get(i).getLocal().getName().equals(localidad.getValue()) && funciones.get(i).getFecha().equals(fecha.getValue()) && funciones.get(i).getSala().getTipo().equals(sala.getValue())
//                    && !hor.contains(funciones.get(i).getHoraFuncion()) ){
//                hor.add(funciones.get(i).getHoraFuncion());
//            }
//        }
//        // problema de que quedan cargadas
        horario.setItems(hor);
        horario.setDisable(false);

    }
    @FXML
    public void enableFecha (ActionEvent event){
        fechas.clear();
        funcionPelicula.clear();

        for (int i = 0; i < funcionMgr.getAllFunciones().size(); i++) {
                funcionPelicula.add(funcionMgr.getAllFunciones().get(i));
        }
        int u = 0;
        for (int j = 0; j < funcionPelicula.size() - u ; j++) {
            if(!funcionPelicula.get(j).getPelicula().getTitulo().equals(titulo.getText())){
                funcionPelicula.remove(j);
                u++;
            }
        }

        System.out.println(funcionPelicula.size());

        for (int i = 0; i < funcionPelicula.size() ; i++) {
            if (!fechas.contains(funcionPelicula.get(i).getFecha()) && funcionPelicula.get(i).getSala().getTipo().equals(sala.getValue())){
                fechas.add(funcionPelicula.get(i).getFecha());
            }
        }

        fecha.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);

                   // LocalDate today = LocalDate.now();

                        if (!fechas.contains(date)){
                        setDisable(true);
                        } else {
                            setDisable(false);
                        }

                   // setDisable(empty || date.compareTo(today) < 0);
                }
            });

        fecha.setDisable(false);
        sala.setDisable(true);
    }

    private ObservableList<Funcion> filteredList = FXCollections.observableArrayList();

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

    public Funcion getFuncionElegida() {
        return funcionElegida;
    }

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

    private Long idTemp;


    static {

        System.setProperty("java.awt.headless", "false");
    }



    @FXML
    public void loadShowroom (ActionEvent event) throws Exception{

      //  salaAgregar = salaManager.getSalaByNumSala(1l);
        peliculaAgregar = peliculaMgr.getPeliculaByName(titulo.getText());
        fechaAgregar = fecha.getValue();
        horarioAgr = horario.getValue();
        localAgr = localMgr.getLocalById(localidad.getValue());
        cineAgr = cineMgr.getCineById(cadena.getValue());

        List<Funcion> lista = funcionMgr.getAllFunciones();

        for (int i = 0; i < lista.size(); i++) {
            if (sala.getValue().equals(lista.get(i).getSala().getTipo()) && localAgr.getName().equals(lista.get(i).getLocal().getName()) && peliculaAgregar.getTitulo().equals(lista.get(i).getPelicula().getTitulo()) && fechaAgregar.equals(lista.get(i).getFecha()) ){
                funcionElegida = lista.get(i);
                idTemp = lista.get(i).getId();
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

        if (funcionElegida != null){
            addSeats(funcionElegida.getSala());
        } else{
            showAlert("Le erraste a la funcion", "LE ERRASTE A LA FUNCION.");
        }

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
            if (gridPane.getRowIndex(node)+1 == row && gridPane.getColumnIndex(node)+1 == column){
                result = node;
                break;
            }
        }
        return result;
    }


    @FXML
    public void cargaTicket (ActionEvent event) throws Exception {
       int contador =0;

        for (int i = 0; i <  funcionElegida.getSala().getFilas() ; i++) {
            for (int j = 0; j < funcionElegida.getSala().getColumnas(); j++){
                ImageView ids = (ImageView)grid.getChildren().get((int) (funcionElegida.getSala().getFilas()*j+i));
                Image im = ids.getImage();

                if (im.equals(selected)){
                    contador++;
                }
            }
        }
        System.out.println(usNameActivo);
        usuarioActivo =  usuarioMgr.getUsuarioByUsername(usNameActivo);


        if (contador != 0) {
            if (usuarioActivo != null) {

                for (int i = 0; i < funcionElegida.getSala().getFilas(); i++) {
                    for (int j = 0; j < funcionElegida.getSala().getColumnas(); j++) {
                        ImageView ids = (ImageView)grid.getChildren().get((int) (funcionElegida.getSala().getFilas()*j+i));
                        Image im = ids.getImage();
                        if (im.equals(selected)) {
                            ((ImageView) grid.getChildren().get((int) (funcionElegida.getSala().getFilas()*j+i))).setImage(unavailable);
                            funcionElegida.reservaButaca(i, j);
                            funcionElegida.setFecha(fecha.getValue().plusDays(1));
                        }
                    }
                }
                funcionMgr.update(funcionElegida);

                Ticket ticket = new Ticket(funcionElegida, usuarioActivo, "asiento", 10);

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

                Parent root = fxmlLoader.load(TicketController.class.getResourceAsStream("ticket2.fxml"));
                Scene inicioScene = new Scene(root, 800, 550);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(inicioScene);
                window.show();

                TicketController ticketController = fxmlLoader.getController();
                ticketController.loadTicketData(ticket);
            } else {
                showAlert("No hay usuario Activo", "Inicie Sesion");
            }
        } else {
            showAlert("Ningun asiento elegido", "Elija");
        }


    }    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
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
