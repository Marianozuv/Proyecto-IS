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
import javafx.stage.Stage;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLFormPromocionController implements Initializable {

    private boolean isEdicion;
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
            this.isEdicion = isEdicion;
            this.promocion = promocion;
            cargarDatos(promocion, isEdicion);
        } else {
            this.isEdicion = isEdicion;
            this.promocion = new Promocion();
            cargarDatos(null, isEdicion);
        }

    }

    private void cargarDatos(Promocion promocion, boolean isEdicion) {

        if (isEdicion) {
            habilitarComponentes(isEdicion);
            rellenarInputs(promocion);
        } else {
            habilitarComponentes(isEdicion);
        }

    }

    private void rellenarInputs(Promocion promocion) {
        tfNombre.setText(promocion.getNombrePromocion());
        tfDesc.setText(promocion.getDescripcion());

        tfRestricciones.setText(promocion.getRestricciones());

        tfPorcentajeCosto.setText(promocion.getPorcentaje_Costo().toString());

        tfCupones.setText("" + promocion.getCuponesMaximos());
        tfCodigoPromo.setText(promocion.getCodigoPromocion());

    }

    private void habilitarComponentes(boolean isEdicion) {

        if (isEdicion) {
            vbBotones.getChildren().clear();
            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);
            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarPromocion);
            habilitarInputs(false);
            habilitarFoto(true);
        } else {
            vbBotones.getChildren().clear();
            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuradarInfo);
            vbBotones.getChildren().remove(btEliminar);
            vbBotones.getChildren().remove(btEditarPromocion);
            habilitarInputs(true);
            habilitarFoto(true);
        }

    }

    private void habilitarInputs(Boolean editable) {
        tfNombre.setEditable(editable);
        tfDesc.setEditable(editable);
        dpFechaInicio.setEditable(editable);
        dpFechaTermino.setEditable(editable);
        tfRestricciones.setEditable(editable);
        cbTipoPromocion.setEditable(editable);
        tfPorcentajeCosto.setEditable(editable);
        cbCategorias.setEditable(editable);
        tfCupones.setEditable(editable);
        tfCodigoPromo.setEditable(editable);
        cbEmpresas.setEditable(editable);
        tfEstatus.setEditable(editable);
    }

    private void habilitarFoto(Boolean editable) {
        vbFoto.setDisable(editable);
    }

    private void habilitarFotoEdit(Boolean editable) {
        vbFoto.setDisable(editable);
    }

    @FXML
    private void btEliminarPromocion(ActionEvent event) {
    }

    @FXML
    private void btCancelar(ActionEvent event) {

        if (isEdicion) {
            habilitarComponentes(isEdicion);
            cargarDatos(promocion, isEdicion);
        } else {
            cerrarVentana();
        }
    }

    @FXML
    private void btSubirInfromacionPromocion(ActionEvent event) {
    }

    @FXML
    private void btEditarInfromacionPromocion(ActionEvent event) {
        habilitarComponentes(false);
        habilitarFotoEdit(false);
    }

    @FXML
    private void btCargarImagen(ActionEvent event) {
    }

    @FXML
    private void btSubirImagen(ActionEvent event) {
    }

    private void cerrarVentana() {
        Stage stage = (Stage) tfNombre.getScene().getWindow();
        stage.close();
    }
}
