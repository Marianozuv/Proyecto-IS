/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.utils.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLModuloPromocionController implements Initializable {

    @FXML
    private Label lbUsuario;
    @FXML
    private TableView<?> tvPromociones;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colCuponesPromoción;
    @FXML
    private TableColumn<?, ?> colFechaInicio;
    @FXML
    private TableColumn<?, ?> colFechaTermino;
    @FXML
    private TableColumn<?, ?> colCodigoPromocion;
    @FXML
    private TableColumn<?, ?> colEstatus;
    @FXML
    private TextField tfBuscador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btRegistroPromocion(ActionEvent event) throws IOException {

        Utilidades.mostrarAlertaSimple("Formulario para registro", "Registro para promocion", Alert.AlertType.INFORMATION);

        FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormPromocion.fxml"));
        Parent vista = vistaLoad.load();

        FXMLFormPromocionController controller = vistaLoad.getController();
        controller.inicializarInformacion(null, false);
        
        Stage stage = new Stage();
        Scene scene = new Scene(vista);
        stage.setScene(scene);
        stage.setTitle("Registrar sucursal");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }

    @FXML
    private void btVerInfoPromocion(ActionEvent event) {
    }

}
