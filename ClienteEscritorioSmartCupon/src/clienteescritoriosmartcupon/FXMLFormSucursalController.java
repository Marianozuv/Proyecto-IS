/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLFormSucursalController implements Initializable {

    private boolean isEdicion;
    @FXML
    private Label lbUsuario;
    @FXML
    private HBox vbBotones;
    @FXML
    private Button btEliminar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btGuradarInfo;
    @FXML
    private Button btEditarEmpresa;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNombreEmpresa;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfLatitud;
    @FXML
    private TextField tfLongitud;
    @FXML
    private TextField tfEncargado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btEliminarEmpresa(ActionEvent event) {
    }

    @FXML
    private void btCancelar(ActionEvent event) {
    }

    @FXML
    private void btSubirInfromacionEmpresa(ActionEvent event) {
    }

    @FXML
    private void btEditarInfromacionEmpresa(ActionEvent event) {
    }
    
    
    private void habilitarComonentes(boolean isEdicion){
        
    }
    
}
