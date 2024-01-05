
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Empresa;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.Rol;
import clienteescritoriosmartcupon.modelo.pojo.Usuario;
import clienteescritoriosmartcupon.modelo.pojo.dao.EmpresaDAO;
import clienteescritoriosmartcupon.modelo.pojo.dao.UsuarioDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FXMLFormUsuarioController implements Initializable {
    
    private Usuario usuario;
    private Rol rol;
    private Empresa empresa;
    private boolean isEdicion;
    private ObservableList<Rol> roles;
    private ObservableList<Empresa> empresas;
    private boolean isEleminar;
    private FXMLModuloUsuarioController moduloUsuarioController;

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
    }   
    
    public void setModuloUsuarioController(FXMLModuloUsuarioController controller) {
        this.moduloUsuarioController = controller;
    }
    
    public void inicializarInformacion(Usuario usuario, boolean isEdicion) {
        
        if (isEdicion) {
            this.usuario = usuario;
            this.isEdicion = isEdicion;
            cargarDatos(usuario, isEdicion);
            cargarInformacionRoles();
            seleccionarRol(usuario.getIdRol());
            cargarInformacionEmpresas();
            seleccionarEmpresa(usuario.getIdEmpresa());
        } else {
            this.usuario = new Usuario();
            cargarDatos(null, isEdicion);
            cargarInformacionRoles();
            cargarInformacionEmpresas();
            if (!roles.isEmpty()) {
                cbRol.setValue(roles.get(0)); // Esto seleccionará el primer rol
            }

            if (!empresas.isEmpty()) {
                cbEmpresa.setValue(empresas.get(0)); // Esto seleccionará el primer rol
            }
        }
    }
    
    private void seleccionarRol(int idRol) {
        if (!cbRol.getItems().isEmpty()) {
            for (Rol rol : cbRol.getItems()) {
                if (rol.getIdRol() == idRol) {
                    cbRol.getSelectionModel().select(rol);
                    return;
                }
            }
        }else{
            Utilidades.mostrarAlertaSimple("Seleccion de Rol", "El Rol no puede ser vacio", Alert.AlertType.ERROR);
        }
    }
    
    private void seleccionarEmpresa(int idEmpresa) {
        if (!cbEmpresa.getItems().isEmpty()) {
            for (Empresa empresa : cbEmpresa.getItems()) {
                if (empresa.getIdEmpresa() == idEmpresa) {
                    cbEmpresa.getSelectionModel().select(empresa);
                    return;
                }
            }
        }else{
            Utilidades.mostrarAlertaSimple("Seleccion de Empresa", "Empresa no puede ser vacio", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btSubirInfromacionUsuario(ActionEvent event) {
        
        if (isEdicion) {
            recuperarDatos();
            editarUsuario(usuario);
        } else {
            recuperarDatos();
            registrarUsuario(usuario);
        }
    }

    @FXML
    private void btEditarInfromacionUsuario(ActionEvent event) {
        habilitarComponentes(false);
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
        
        if (moduloUsuarioController != null) {
            moduloUsuarioController.consultarInformacionUsuario();
        }
    }
    
    @FXML
    private void btCancelar(ActionEvent event) {
        if (isEdicion) {
            habilitarComponentes(isEdicion);
            cargarDatos(usuario, isEdicion);
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
    
    private void registrarUsuario(Usuario usuario) {
        Mensaje mensaje = UsuarioDAO.add(usuario);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Usuario registrado", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al registrar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarUsuario(Usuario usuario) {
        Mensaje mensaje = UsuarioDAO.update(usuario);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Usuario editado", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al editar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void habilitarComponentes(boolean isEdicion) {

        if (isEdicion) {

            vbBotones.getChildren().clear();
            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);
            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarUsuario);
            habilitarTextEdit(false);

        } else {

            vbBotones.getChildren().clear();
            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuradarInfo);
            vbBotones.getChildren().remove(btEliminar);
            vbBotones.getChildren().remove(btEditarUsuario);
            habilitarTextEdit(true);

        }

    }
    
    private void cargarDatos(Usuario usuario, boolean isEdicion) {

        if (isEdicion) {

            habilitarComponentes(isEdicion);
            tfNombre.setText(usuario.getNombre());
            tfApellidoPaterno.setText(usuario.getApellidoPaterno());
            tfApellidoMaterno.setText(usuario.getApellidoMaterno());
            tfCURP.setText(usuario.getCURP());
            tfEmail.setText(usuario.getEmail());
            tfUsername.setText(usuario.getUsername());
            tfPassword.setText(usuario.getPassword());
            
        } else {
            habilitarComponentes(isEdicion);
        }
    }
    
    private void habilitarTextEdit(Boolean editable) {

        tfNombre.setEditable(editable);
        tfApellidoPaterno.setEditable(editable);
        tfApellidoMaterno.setEditable(editable);
        tfCURP.setEditable(editable);
        tfEmail.setEditable(editable);
        tfNombre.setEditable(editable);
        tfUsername.setEditable(editable);
        tfPassword.setEditable(editable);
        cbRol.setDisable(!editable);
        cbEmpresa.setDisable(!editable);
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
    
    private void recuperarDatos() {
        usuario.setNombre(tfNombre.getText());
        usuario.setApellidoPaterno(tfApellidoPaterno.getText());
        usuario.setApellidoMaterno(tfApellidoMaterno.getText());
        usuario.setCURP(tfCURP.getText());
        usuario.setEmail(tfEmail.getText());
        usuario.setUsername(tfUsername.getText());
        usuario.setPassword(tfPassword.getText());
        Rol rolSeleccion = roles.get(cbRol.getSelectionModel().getSelectedIndex());
        usuario.setIdRol(rolSeleccion.getIdRol());
        Empresa empresaSeleccion = empresas.get(cbEmpresa.getSelectionModel().getSelectedIndex());
        usuario.setIdEmpresa(empresaSeleccion.getIdEmpresa());
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

    private void cerrarVentana() {
        Stage stage = (Stage) tfNombre.getScene().getWindow();
        stage.close();
    }
}
