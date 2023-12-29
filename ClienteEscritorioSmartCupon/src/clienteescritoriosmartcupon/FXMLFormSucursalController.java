/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Sucursal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLFormSucursalController implements Initializable {

    private Sucursal sucursal;

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
    @FXML
    private Button btEditarSucursal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarInformacion(Sucursal sucursal, boolean isEdicion) {

        if (isEdicion) {
            this.sucursal = sucursal;
            this.isEdicion = isEdicion;
            cargarDatos(sucursal, isEdicion);
        } else {
            cargarDatos(null, !isEdicion);
        }

    }

    @FXML
    private void btCancelar(ActionEvent event) {

        if (isEdicion) {
            habilitarComonentes(isEdicion);
            cargarDatos(sucursal, isEdicion);
        } else {

            try {

                Stage stagePrincipal = (Stage) tfCiudad.getScene().getWindow();
                FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLModuloSucursal.fxml"));
                Parent vista = loadVista.load();
                FXMLModuloSucursalController controladorHome = loadVista.getController();
                Scene scene = new Scene(vista);
                stagePrincipal.setScene(scene);
                stagePrincipal.show();

            } catch (Exception e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    private void btEliminarSucursal(ActionEvent event) {
    }

    @FXML
    private void btSubirInfromacionSucursal(ActionEvent event) {
        
    }

    @FXML
    private void btEditarInfromacionSucursal(ActionEvent event) {
        habilitarComonentes(false);
    }

    private void habilitarComonentes(boolean isEdicion) {

        if (isEdicion) {

            vbBotones.getChildren().clear();
            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);
            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarSucursal);
            habilitarTextEdit(false);

        } else {

            vbBotones.getChildren().clear();
            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuradarInfo);
            vbBotones.getChildren().remove(btEliminar);
            vbBotones.getChildren().remove(btEditarSucursal);
            habilitarTextEdit(true);

        }

    }

    private void cargarDatos(Sucursal sucursal, boolean isEdicion) {

        if (isEdicion) {

            habilitarComonentes(isEdicion);
            tfNombre.setText(sucursal.getNombreSucursal());

            tfDireccion.setText(sucursal.getDireccion());
            tfCodigoPostal.setText(sucursal.getCodigoPostal());
            tfColonia.setText(sucursal.getColonia());
            tfCiudad.setText(sucursal.getCiudad());
            tfTelefono.setText(sucursal.getTelefono());
            tfLatitud.setText(sucursal.getLatitud());
            tfLongitud.setText(sucursal.getLongitud());
            tfEncargado.setText(sucursal.getNombreEncargado());
        } else {
            habilitarComonentes(!isEdicion);
        }
    }

    private void habilitarTextEdit(Boolean editable) {
        
        tfNombre.setEditable(editable);
        tfNombreEmpresa.setEditable(editable);
        tfDireccion.setEditable(editable);
        tfCodigoPostal.setEditable(editable);
        tfColonia.setEditable(editable);
        tfCiudad.setEditable(editable);
        tfTelefono.setEditable(editable);
        tfLatitud.setEditable(editable);
        tfLongitud.setEditable(editable);
        tfEncargado.setEditable(editable);
        
    }

}
