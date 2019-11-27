package com.example.moviecrud.ui;

import com.example.moviecrud.MovieCrudApplication;
import com.example.moviecrud.business.FuncionMgr;
import com.example.moviecrud.business.TicketMgr;
import com.example.moviecrud.business.UsuarioMgr;
import com.example.moviecrud.business.entities.Funcion;
import com.example.moviecrud.business.entities.Pelicula;
import com.example.moviecrud.business.entities.Ticket;
import com.example.moviecrud.business.entities.Usuario;
import com.example.moviecrud.business.exceptions.InformacionInvalida;
import com.example.moviecrud.business.exceptions.YaExiste;
import com.example.moviecrud.ui.movie.InfoPelicula;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Controller
public class TicketController implements Initializable {
    @Autowired
    TicketMgr ticketMgr;

    @Autowired
    InfoPelicula infoPelicula;

    @Autowired
    FuncionMgr funcionMgr;

    @Autowired
    UsuarioMgr usuarioMgr;



    @FXML
    private AnchorPane root;

    @FXML
    private GridPane grid;

    @FXML
    private Button confirmarbtn;

    private Integer idTemp;

    @FXML
    private TextField user;

    @FXML
    private TextField peli;
    @FXML
    private TextField local;
    @FXML
    private TextField cine;
    @FXML
    private TextField sala;

    @FXML
    private TextField fecha;
    @FXML
    private TextField hora;

    private boolean editando;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void loadTicketData (Ticket ticket){
        try {
            user.setText(ticket.getUsuario().getUsername());
            peli.setText(ticket.getFuncion().getPelicula().getTitulo());
            local.setText(ticket.getFuncion().getLocal().getName());
            cine.setText(ticket.getFuncion().getLocal().getNombreCine());
            sala.setText(ticket.getFuncion().getSala().getNumeroSala().toString());
            fecha.setText(ticket.getFuncion().getFecha().toString());
            hora.setText(ticket.getFuncion().getHoraFuncion().toString());
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        idTemp = ticket.getId();

    }



    @FXML
    void confirmarComprar(ActionEvent event) throws IOException {
        if (user.getText() == null || user.getText().equals("") || peli.getText() == null || peli.getText().equals("")
                || cine.getText() == null || cine.getText().equals("") || local.getText() == null || local.getText().equals("")){

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {

                String userAgregar = user.getText();
                String peliAgregar = peli.getText();
                String cineAgregar = cine.getText();
                String localAgregar = local.getText();
                String salaAgregar = sala.getText();
                String fechaAgregar = fecha.getText();
                String horaAgregar = hora.getText();

                Funcion funcionAgregar = new Funcion();






                try {
                    if(!editando) {
                        Usuario user = usuarioMgr.getUsuarioByUsername(userAgregar);
                        String asiento = "bp";
                        Integer precio = 5;

                        ArrayList<Funcion> funciones = (ArrayList<Funcion>) funcionMgr.getAllFunciones();

//                        for (int i = 0; i < funciones.size() ; i++) {
//                            if (funciones.get(i).getPelicula().getTitulo().equals(peliAgregar) && funciones.get(i).getLocal().getNombreCine().equals(cineAgregar)&&
//                            funciones.get(i).getLocal().getName().equals(localAgregar)&& funciones.get(i).getSala().getNumeroSala().toString().equals(salaAgregar) &&
//                            funciones.get(i).getFecha().toString().equals(fechaAgregar)){
//                                funcionAgregar = funciones.get(i);
//                            }
//                        }


                        ticketMgr.addTicket(infoPelicula.getFuncionElegida(),user,asiento,precio);


                        showAlert("Ticket agregado", "Se agrego con exito el Ticket!");
                        close(event);
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

                        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
                        Scene inicioScene = new Scene(root, 800, 550);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(inicioScene);
                        window.show();

                    } else {


//                        Pelicula peliculaActualizada = new Pelicula(tituloAgregar,generoAgregar ,actoresAgregar,duracionAgregar ,descripcionAgregar);
//                        peliculaMgr.update(idTemp,peliculaActualizada);
//
//                        showAlert("Pelicula Editada", "Se edito con exito la Pelicula!");
//                        close(event);
//                        principal.actualizaCart();
//                        editando = false;

                    }
                } catch (InformacionInvalida informacionInvalida) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (YaExiste yaExiste) {
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

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
    @FXML
    void close(javafx.event.ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
