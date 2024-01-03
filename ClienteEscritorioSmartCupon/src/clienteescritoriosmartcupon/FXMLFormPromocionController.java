/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Promocion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLFormPromocionController implements Initializable {

    private Promocion promocion;
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
    private Button btEditarPromocion;
    @FXML
    private VBox vbFoto;
    @FXML
    private ImageView ivImagenPromocion;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfDesc;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaTermino;
    @FXML
    private TextField tfRestricciones;
    @FXML
    private ComboBox<?> cbTipoPromocion;
    @FXML
    private TextField tfPorcentajeCosto;
    @FXML
    private ComboBox<?> cbCategorias;
    @FXML
    private TextField tfCupones;
    @FXML
    private TextField tfCodigoPromo;
    @FXML
    private ComboBox<?> cbEmpresas;
    @FXML
    private TextField tfEstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarInformacion(Promocion promocion, boolean isEdicion) {

        if (isEdicion) {
            this.promocion = promocion;
            cargarDatos(promocion, isEdicion);
        } else {
            this.promocion = new Promocion();
            cargarDatos(null, isEdicion);
        }

    }

    private void cargarDatos(Promocion promocion, boolean isEdicion) {

        if (isEdicion) {
            habilitarComponentes(isEdicion);
        } else {
            habilitarComponentes(isEdicion);
        }

    }

    private void habilitarComponentes(boolean isEdicion) {

        if (isEdicion) {
            vbBotones.getChildren().clear();
            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);
            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarPromocion);
        } else {
            vbBotones.getChildren().clear();
            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuradarInfo);
            vbBotones.getChildren().remove(btEliminar);
            vbBotones.getChildren().remove(btEditarPromocion);
        }

    }

    @FXML
    private void btEliminarPromocion(ActionEvent event) {
    }

    @FXML
    private void btCancelar(ActionEvent event) {
    }

    @FXML
    private void btSubirInfromacionPromocion(ActionEvent event) {
    }

    @FXML
    private void btEditarInfromacionPromocion(ActionEvent event) {
    }

    @FXML
    private void btCargarImagen(ActionEvent event) {
    }

    @FXML
    private void btSubirImagen(ActionEvent event) {
    }

}
