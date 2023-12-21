/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Empresa;
import clienteescritoriosmartcupon.modelo.pojo.dao.EmpresaDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLModuloEmpresaController implements Initializable {

    private ObservableList<Empresa> empresas;

    @FXML
    private Label lbUsuario;
    @FXML
    private TableView<Empresa> tvEmpresas;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colRepresentanteLegal;
    @FXML
    private TableColumn colRFC;
    @FXML
    private TableColumn colEstatus;
    @FXML
    private TableColumn colDireccion;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TextField tfBuscador;
    @FXML
    private ComboBox<?> cbFiltroBuscado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerEmpresas();
        cargarTablaEmpresas();

    }

    private void obtenerEmpresas() {

        try {
            empresas = FXCollections.observableArrayList();
            List<Empresa> info = EmpresaDAO.obtenerEmpresas();
            empresas.addAll(info);
            tvEmpresas.setItems(empresas);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void cargarTablaEmpresas() {
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colRepresentanteLegal.setCellValueFactory(new PropertyValueFactory("nombreRepresentanteLegal"));
        colRFC.setCellValueFactory(new PropertyValueFactory("RFC"));
        colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        colEstatus.setCellValueFactory(new PropertyValueFactory("estatus"));
    }

    @FXML
    private void btVerInfoEmpresa(ActionEvent event) throws IOException {

        int posicionSelecionada = tvEmpresas.getSelectionModel().getSelectedIndex();

        if (posicionSelecionada != -1) {

            Empresa empresa = empresas.get(posicionSelecionada);
            Utilidades.mostrarAlertaSimple("Ver información empresa", "Ha selecionado la empresa: " + empresa.getNombre(), Alert.AlertType.INFORMATION);

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFromEmpresa.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFromEmpresaController controller = vistaLoad.getController();
            controller.inicializarInformacion(empresa, true);

            Stage stage = new Stage();
            Scene escenaEditarPaciente = new Scene(vista);
            stage.setScene(escenaEditarPaciente);
            stage.setTitle("Infromación empresa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } else {
            Utilidades.mostrarAlertaSimple("Ver información empresa", "Para poder modificar debes seleccionar una empresa", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void btRegistroEmpresa(ActionEvent event) throws IOException {

        FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFromEmpresa.fxml"));
        Parent vista = vistaLoad.load();

        FXMLFromEmpresaController controller = vistaLoad.getController();
        controller.inicializarInformacion(null, false);

        Stage stage = new Stage();
        Scene escenaEditarPaciente = new Scene(vista);
        stage.setScene(escenaEditarPaciente);
        stage.setTitle("Registrar empresa");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }



}
