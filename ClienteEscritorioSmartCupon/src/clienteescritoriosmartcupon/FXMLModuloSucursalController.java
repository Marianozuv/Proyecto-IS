/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Sucursal;
import clienteescritoriosmartcupon.modelo.pojo.dao.SucursalDAO;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLModuloSucursalController implements Initializable {

    private ObservableList<Sucursal> sucursales;
    @FXML
    private Label lbUsuario;
    @FXML
    private TableView<Sucursal> tvSucursale;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colDireccion;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TextField tfBuscador;
    @FXML
    private ComboBox<?> cbFiltroBuscado;
    @FXML
    private TableColumn colNombreEncargado;
    @FXML
    private TableColumn colCodigoPostal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerSucursales();
        cargarSucursales();
    } 
    
    private void obtenerSucursales(){
        try {
            sucursales = FXCollections.observableArrayList();
            List<Sucursal> info = SucursalDAO.obtenerSucursales();
            sucursales.addAll(info);
            tvSucursale.setItems(sucursales);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cargarSucursales(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreSucursal"));
        colNombreEncargado.setCellValueFactory(new PropertyValueFactory("nombreEncargado"));
        colCodigoPostal.setCellValueFactory(new PropertyValueFactory("codigoPostal"));
        colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
    }

    @FXML
    private void btRegistroSucursal(ActionEvent event) {
    }

    @FXML
    private void btVerInfoSucursal(ActionEvent event) {
    }
    
}
