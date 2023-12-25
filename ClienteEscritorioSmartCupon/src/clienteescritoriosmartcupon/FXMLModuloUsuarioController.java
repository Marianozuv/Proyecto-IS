/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Usuario;
import clienteescritoriosmartcupon.modelo.pojo.dao.UsuarioDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLModuloUsuarioController implements Initializable {
    
    private Usuario usuario;
    private ObservableList<Usuario> usuariosEmpresas;

    @FXML
    private Label lbUsuario;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TextField tfBuscador;
    @FXML
    private ComboBox<?> cbFiltroBuscado;
    @FXML
    private TableView<Usuario> tvUsuario;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn colCURP;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colRol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usuariosEmpresas = FXCollections.observableArrayList();
        configurarColumnasTabla();
    }

    @FXML
    private void btVerInfoUsuario(ActionEvent event) {
    }

    @FXML
    private void btRegistroUsuario(ActionEvent event) {
    }
    
    public void inicializarInformacion(Usuario usuario) {
        this.usuario = usuario;
        consultarInformacionUsuario();
    }
    
    private void consultarInformacionUsuario() {
        HashMap<String, Object> respuesta = UsuarioDAO.get();
        if (!(boolean) respuesta.get("error")) {
            List<Usuario> usuarios = (List<Usuario>) respuesta.get("usuarios");
            usuariosEmpresas.addAll(usuarios);
            tvUsuario.setItems(usuariosEmpresas);
        } else {
            Utilidades.mostrarAlertaSimple("Error", (String) respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }
    
    private void configurarColumnasTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colCURP.setCellValueFactory(new PropertyValueFactory("curp"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colRol.setCellValueFactory(new PropertyValueFactory("rol"));
    }
}
