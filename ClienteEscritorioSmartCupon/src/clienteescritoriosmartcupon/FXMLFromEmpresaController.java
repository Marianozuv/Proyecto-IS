/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Empresa;
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
public class FXMLFromEmpresaController implements Initializable {

    private Empresa empresa;

    private boolean isEdicion;

    private boolean isEleminar;

    @FXML
    private Label lbUsuario;
    private Button btSwitch;
    @FXML
    private Button btGuradarInfo;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfPaginaWeb;
    @FXML
    private TextField tfRFC;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfRepresentante;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private Button btEliminar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btEditarEmpresa;
    @FXML
    private HBox vbBotones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void inicializarInformacion(Empresa empresa, boolean isEdicion) {

        tfCiudad.setEditable(false);
        tfTelefono.setEditable(false);
        tfPaginaWeb.setEditable(false);
        tfRFC.setEditable(false);
        tfEmail.setEditable(false);
        tfNombre.setEditable(false);
        tfNombreComercial.setEditable(false);
        tfRepresentante.setEditable(false);
        tfDireccion.setEditable(false);
        tfCodigoPostal.setEditable(false);

        this.empresa = empresa;
        this.isEdicion = isEdicion;

        if (isEdicion) {

            vbBotones.getChildren().clear();

            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);

            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarEmpresa);

            tfCiudad.setText(empresa.getCiudad());
            tfTelefono.setText(empresa.getTelefono());
            tfPaginaWeb.setText(empresa.getPaginaWeb());
            tfRFC.setText(empresa.getRFC());
            tfEmail.setText(empresa.getEmail());
            tfNombre.setText(empresa.getNombre());
            tfNombreComercial.setText(empresa.getNombreComercial());
            tfRepresentante.setText(empresa.getNombreRepresentanteLegal());
            tfDireccion.setText(empresa.getDireccion());
            tfCodigoPostal.setText(empresa.getCodigoPostal());

        } else {

            vbBotones.getChildren().clear();

            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuradarInfo);

            vbBotones.getChildren().remove(btEliminar);
            vbBotones.getChildren().remove(btEditarEmpresa);

            tfCiudad.setEditable(true);
            tfTelefono.setEditable(true);
            tfPaginaWeb.setEditable(true);
            tfRFC.setEditable(true);
            tfEmail.setEditable(true);
            tfNombre.setEditable(true);
            tfNombreComercial.setEditable(true);
            tfRepresentante.setEditable(true);
            tfDireccion.setEditable(true);
            tfCodigoPostal.setEditable(true);
        }

    }

    @FXML
    private void btModuloEmpresas(ActionEvent event) {
    }

    @FXML
    private void btSubirInfromacionEmpresa(ActionEvent event) {

        if (isEdicion) {


        }

    }

    @FXML
    private void btEliminarEmpresa(ActionEvent event) {
    }

    @FXML
    private void btCancelar(ActionEvent event) {

        if (isEdicion) {

            vbBotones.getChildren().clear();

            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);

            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarEmpresa);

            tfCiudad.setEditable(false);
            tfTelefono.setEditable(false);
            tfPaginaWeb.setEditable(false);
            tfRFC.setEditable(false);
            tfEmail.setEditable(false);
            tfNombre.setEditable(false);
            tfNombreComercial.setEditable(false);
            tfRepresentante.setEditable(false);
            tfDireccion.setEditable(false);
            tfCodigoPostal.setEditable(false);
        }

    }

    @FXML
    private void btEditarInfromacionEmpresa(ActionEvent event) {

        vbBotones.getChildren().clear();

        vbBotones.getChildren().add(btCancelar);
        vbBotones.getChildren().add(btGuradarInfo);

        vbBotones.getChildren().remove(btEliminar);
        vbBotones.getChildren().remove(btEditarEmpresa);

        tfCiudad.setEditable(true);
        tfTelefono.setEditable(true);
        tfPaginaWeb.setEditable(true);
        tfRFC.setEditable(true);
        tfEmail.setEditable(true);
        tfNombre.setEditable(true);
        tfNombreComercial.setEditable(true);
        tfRepresentante.setEditable(true);
        tfDireccion.setEditable(true);
        tfCodigoPostal.setEditable(true);
    }

}
