/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Promocion;
import clienteescritoriosmartcupon.modelo.pojo.Sucursal;
import clienteescritoriosmartcupon.modelo.pojo.dao.SucursalDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLAsignarSucursalController implements Initializable {

    private ObservableList<Sucursal> sucursales;
    private Promocion promocion;
    @FXML
    private Label lbUsuario;
    @FXML
    private VBox vbFoto;
    @FXML
    private TextField tfNombre;
    @FXML
    private ComboBox<Sucursal> cbSucursales;
    @FXML
    private TextField tfCupones;
    @FXML
    private TextField tfCodigo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void inicializarInfomracion(Promocion promocion) {
        this.promocion = promocion;
        cargarDatos(promocion);
        cargarSucursales();
    }

    private void cargarDatos(Promocion promocion) {
        tfNombre.setText(promocion.getNombrePromocion());
        tfCodigo.setText(promocion.getCodigoPromocion());
        tfCupones.setText("" + promocion.getCuponesMaximos());
    }

    @FXML
    private void btAsignarSucursal(ActionEvent event) {
    }

    @FXML
    private void btConcluir(ActionEvent event) {
        cerrarVentana();
    }

    private void cargarSucursales() {
        sucursales = FXCollections.observableArrayList();
        List<Sucursal> info = SucursalDAO.obtenerSucursales();
        sucursales.addAll(info);
        cbSucursales.setItems(sucursales);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) tfNombre.getScene().getWindow();
        stage.close();
    }

}
