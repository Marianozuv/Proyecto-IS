/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Usuario;
import clienteescritoriosmartcupon.modelo.pojo.dao.UsuarioDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author andre
 */
public class FXMLModuloUsuarioController implements Initializable {
    
    private Usuario usuario;
    private ObservableList<Usuario> usuariosEmpresas;

    @FXML
    private Label lbUsuario;
    @FXML
    private TextField tfBuscador;
    @FXML
    private ComboBox<?> cbFiltroBuscado;
    @FXML
    private TableView<Usuario> tvUsuario;
    @FXML
    private TableColumn colNombre;
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
        consultarInformacionUsuario();
        configurarColumnasTabla();
    }

    @FXML
    private void btVerInfoUsuario(ActionEvent event) throws IOException {
        int posicionSelecionada = tvUsuario.getSelectionModel().getSelectedIndex();

        if (posicionSelecionada != -1) {

            Usuario usuario = usuariosEmpresas.get(posicionSelecionada);
            Utilidades.mostrarAlertaSimple("Ver información empresa", "Ha selecionado al usuario: " + usuario.getNombre(), Alert.AlertType.INFORMATION);

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormUsuario.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFormUsuarioController controller = vistaLoad.getController();
            controller.inicializarInformacion(usuario, true);

            Stage stage = new Stage();
            Scene escenaEditarPaciente = new Scene(vista);
            stage.setScene(escenaEditarPaciente);
            stage.setTitle("Infromación Usuario");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } else {
            Utilidades.mostrarAlertaSimple("Ver información usuario", "Para poder modificar debes seleccionar una empresa", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void btRegistroUsuario(ActionEvent event) throws IOException {
        FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormUsuario.fxml"));
        Parent vista = vistaLoad.load();

        FXMLFormUsuarioController controller = vistaLoad.getController();
        controller.inicializarInformacion(null, false);

        Stage stage = new Stage();
        Scene ecenaRegistrarUsuario = new Scene(vista);
        stage.setScene(ecenaRegistrarUsuario);
        stage.setTitle("Registrar usuario");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    public void inicializarInformacion(Usuario usuario) {
        this.usuario = usuario;
        consultarInformacionUsuario();
    }
    
    private void consultarInformacionUsuario() {
        try {
            usuariosEmpresas = FXCollections.observableArrayList();
            List<Usuario> info = UsuarioDAO.get();
            usuariosEmpresas.addAll(info);
            tvUsuario.setItems(usuariosEmpresas);
        } catch (NullPointerException e) {
            e.printStackTrace();
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
