/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Empresa;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.Rol;
import clienteescritoriosmartcupon.modelo.pojo.Usuario;
import clienteescritoriosmartcupon.modelo.pojo.dao.EmpresaDAO;
import clienteescritoriosmartcupon.modelo.pojo.dao.UsuarioDAO;
import static clienteescritoriosmartcupon.modelo.pojo.dao.UsuarioDAO.add;
import static clienteescritoriosmartcupon.modelo.pojo.dao.UsuarioDAO.update;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLFormUsuarioController implements Initializable {
    
    private Usuario usuario;
    private Rol rol;
    private Empresa empresa;
    private boolean isEdicion;
    private ObservableList<Rol> roles;
    private ObservableList<Empresa> empresas;
    private boolean isEleminar;

    @FXML
    private Label lbUsuario;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNombre;
    @FXML
    private HBox vbBotones;
    @FXML
    private Button btEliminar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btGuradarInfo;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCURP;
    @FXML
    private Button btEditarUsuario;
    @FXML
    private ComboBox<Rol> cbRol;
    @FXML
    private ComboBox<Empresa> cbEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarInformacionRoles();
        cargarInformacionEmpresas();
        configurarSeleccionRol();
        configurarSeleccionEmpresa();
    }    
    
    public void inicializarInformacion(Usuario usuario, boolean isEdicion) {

        tfNombre.setEditable(false);
        tfApellidoPaterno.setEditable(false);
        tfApellidoMaterno.setEditable(false);
        tfCURP.setEditable(false);
        tfEmail.setEditable(false);
        tfUsername.setEditable(false);
        tfPassword.setEditable(false);
        cbRol.setEditable(false);
        cbEmpresa.setEditable(false);

        this.usuario = usuario;
        this.isEdicion = isEdicion;

        if (isEdicion) {

            vbBotones.getChildren().clear();

            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);

            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarUsuario);

            tfNombre.setText(usuario.getNombre());
            tfApellidoPaterno.setText(usuario.getApellidoPaterno());
            tfApellidoMaterno.setText(usuario.getApellidoMaterno());
            tfCURP.setText(usuario.getCurp());
            tfEmail.setText(usuario.getEmail());
            tfUsername.setText(usuario.getUsername());
            tfPassword.setText(usuario.getPassword());
            /*int posicionRol = buscarIdRol(rol.getIdRol());
            cbRol.getSelectionModel().select(posicionRol);
            int posicionEmpresa = buscarIdEmpresa(empresa.getIdEmpresa());
            cbEmpresa.getSelectionModel().select(posicionEmpresa);*/
            

        } else {

            vbBotones.getChildren().clear();

            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuradarInfo);

            vbBotones.getChildren().remove(btEliminar);
            vbBotones.getChildren().remove(btEditarUsuario);

            tfNombre.setEditable(true);
            tfApellidoPaterno.setEditable(true);
            tfApellidoMaterno.setEditable(true);
            tfCURP.setEditable(true);
            tfEmail.setEditable(true);
            tfUsername.setEditable(true);
            tfPassword.setEditable(true);
            cbRol.setEditable(true);
            cbEmpresa.setEditable(true);
            cbRol.setEditable(true);
            cbEmpresa.setEditable(true);
        }
    }

    @FXML
    private void btCancelar(ActionEvent event) {
        if (isEdicion) {

            vbBotones.getChildren().clear();

            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);

            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarUsuario);
            
            tfNombre.setEditable(false);
            tfApellidoPaterno.setEditable(false);
            tfApellidoMaterno.setEditable(false);
            tfCURP.setEditable(false);
            tfEmail.setEditable(false);
            tfNombre.setEditable(false);
            tfUsername.setEditable(false);
            tfPassword.setEditable(false);
            cbRol.setEditable(false);
            cbEmpresa.setEditable(false);
            
            tfNombre.setText(usuario.getNombre());
            tfApellidoPaterno.setText(usuario.getApellidoPaterno());
            tfApellidoMaterno.setText(usuario.getApellidoMaterno());
            tfCURP.setText(usuario.getCurp());
            tfEmail.setText(usuario.getEmail());
            tfUsername.setText(usuario.getUsername());
            tfPassword.setText(usuario.getPassword());
            
        } else {

            try {

                Stage stagePrincipal = (Stage) tfNombre.getScene().getWindow();
                FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLModuloUsuario.fxml"));
                Parent vista = loadVista.load();
                FXMLModuloUsuarioController controladorHome = loadVista.getController();
                Scene scene = new Scene(vista);
                stagePrincipal.setScene(scene);
                stagePrincipal.show();

            } catch (Exception e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    private void btEliminarUsuario(ActionEvent event) {
        if (Utilidades.mostrarAlertaConfirmacion("Eliminar Usuario", "¿Está seguro de eliminar a este Usuario?")) {
            Mensaje mensaje = UsuarioDAO.delete(usuario);

            if (!mensaje.isError()) {
                Utilidades.mostrarAlertaSimple("Eliminar usuario", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                cerrarVentana();
            }else {
                Utilidades.mostrarAlertaSimple("Eliminar usuario", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            }

        } else {
            Utilidades.mostrarAlertaSimple("Operacion eliminar", "La operación eliminar se ha cancelado", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void btSubirInfromacionUsuario(ActionEvent event) {
        if (isEdicion) {

            usuario.setNombre(tfNombre.getText());
            usuario.setApellidoPaterno(tfApellidoPaterno.getText());
            usuario.setApellidoMaterno(tfApellidoMaterno.getText());
            usuario.setCurp(tfCURP.getText());
            usuario.setEmail(tfEmail.getText());
            usuario.setUsername(tfUsername.getText());
            usuario.setPassword(tfPassword.getText());
            Rol rolSeleccion = roles.get(cbRol.getSelectionModel().getSelectedIndex());
            usuario.setIdRol(rolSeleccion.getIdRol());
            usuario.setIdRol(this.usuario.getIdUsuario());
            Empresa empresaSeleccion = empresas.get(cbEmpresa.getSelectionModel().getSelectedIndex());
            usuario.setIdEmpresa(empresaSeleccion.getIdEmpresa());
            usuario.setIdEmpresa(this.usuario.getIdUsuario());
            
            update(usuario);

        } else {
            usuario = new Usuario();
            usuario.setNombre(tfNombre.getText());
            usuario.setApellidoPaterno(tfApellidoPaterno.getText());
            usuario.setApellidoMaterno(tfApellidoMaterno.getText());
            usuario.setCurp(tfCURP.getText());
            usuario.setEmail(tfEmail.getText());
            usuario.setUsername(tfUsername.getText());
            usuario.setPassword(tfPassword.getText());
            Rol rolSeleccion = roles.get(cbRol.getSelectionModel().getSelectedIndex());
            usuario.setIdRol(rolSeleccion.getIdRol());
            usuario.setIdRol(this.usuario.getIdUsuario());
            Empresa empresaSeleccion = empresas.get(cbEmpresa.getSelectionModel().getSelectedIndex());
            usuario.setIdEmpresa(empresaSeleccion.getIdEmpresa());
            usuario.setIdEmpresa(this.usuario.getIdUsuario());

            add(usuario);
        }
    }

    @FXML
    private void btEditarInfromacionUsuario(ActionEvent event) {
        vbBotones.getChildren().clear();

        vbBotones.getChildren().remove(btCancelar);
        vbBotones.getChildren().remove(btGuradarInfo);

        vbBotones.getChildren().add(btEliminar);
        vbBotones.getChildren().add(btEditarUsuario);

        tfNombre.setEditable(true);
        tfApellidoPaterno.setEditable(true);
        tfApellidoMaterno.setEditable(true);
        tfCURP.setEditable(true);
        tfEmail.setEditable(true);
        tfNombre.setEditable(true);
        tfUsername.setEditable(true);
        tfPassword.setEditable(true);
        cbRol.setEditable(true);
        cbEmpresa.setEditable(true);
    }
    
    private void cargarInformacionRoles() {
        roles = FXCollections.observableArrayList();
        List<Rol> info = UsuarioDAO.obtenerRol();
        roles.addAll(info);
        cbRol.setItems(roles);
    }
    
    private void cargarInformacionEmpresas() {
        empresas = FXCollections.observableArrayList();
        List<Empresa> info = EmpresaDAO.obtenerEmpresas();
        empresas.addAll(info);
        cbEmpresa.setItems(empresas);
    }
    
    private void configurarSeleccionRol() {
        cbRol.valueProperty().addListener(new ChangeListener<Rol>() {
            @Override
            public void changed(ObservableValue<? extends Rol> observable, Rol oldValue, Rol newValue) {
                cargarInformacionRoles();
            }
        });
    }
    
    private void configurarSeleccionEmpresa() {
        cbEmpresa.valueProperty().addListener(new ChangeListener<Empresa>(){
            @Override
            public void changed(ObservableValue<? extends Empresa> observable, Empresa oldValue, Empresa newValue) {
                cargarInformacionEmpresas();
            }
        });
    }
    
    private void cerrarVentana() {
        Stage stage = (Stage) tfNombre.getScene().getWindow();
        stage.close();
    }
    
    private int buscarIdRol(int idRol) {
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getIdRol()== idRol) {
                return i;
            }
        }
        return 0;
    }
    
    private int buscarIdEmpresa(int idEmpresa) {
        for (int i = 0; i < empresas.size(); i++) {
            if (empresas.get(i).getIdEmpresa()== idEmpresa) {
                return i;
            }
        }
        return 0;
    }
}
