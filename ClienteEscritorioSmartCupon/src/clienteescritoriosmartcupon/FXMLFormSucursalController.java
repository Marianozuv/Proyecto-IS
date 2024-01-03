package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Empresa;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.Sucursal;
import clienteescritoriosmartcupon.modelo.pojo.dao.EmpresaDAO;
import clienteescritoriosmartcupon.modelo.pojo.dao.SucursalDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.Observable;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FXMLFormSucursalController implements Initializable {

    private Sucursal sucursal;
    private ObservableList<Empresa> empresas;
    private FXMLModuloSucursalController moduloSucursalController;
    
    private boolean isEdicion;
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
    private TextField tfNombre;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfLatitud;
    @FXML
    private TextField tfLongitud;
    @FXML
    private TextField tfEncargado;
    @FXML
    private Button btEditarSucursal;
    @FXML
    private ComboBox<Empresa> cbEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setModuloSucursalController(FXMLModuloSucursalController controller) {
        this.moduloSucursalController = controller;
    }

    public void inicializarInformacion(Sucursal sucursal, boolean isEdicion) {

        if (isEdicion) {
            this.sucursal = sucursal;
            this.isEdicion = isEdicion;
            cargarDatos(sucursal, isEdicion);
            cargarEmpresas();
            seleccionarEmpresa(sucursal.getIdEmpresa());
        } else {
            this.sucursal = new Sucursal();
            cargarDatos(null, isEdicion);
            cargarEmpresas();
        }

    }

    @FXML
    private void btCancelar(ActionEvent event) {

        if (isEdicion) {
            habilitarComonentes(isEdicion);
            cargarDatos(sucursal, isEdicion);
        } else {

            try {

                Stage stagePrincipal = (Stage) tfCiudad.getScene().getWindow();
                FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLModuloSucursal.fxml"));
                Parent vista = loadVista.load();
                FXMLModuloSucursalController controladorHome = loadVista.getController();
                Scene scene = new Scene(vista);
                stagePrincipal.setScene(scene);
                stagePrincipal.show();

            } catch (Exception e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    private void btEliminarSucursal(ActionEvent event) {
        
        if (Utilidades.mostrarAlertaConfirmacion("Eliminar sucursal", "¿Está seguro de eliminar la sucursal?")) {
            Mensaje mensaje = SucursalDAO.eliminarSucursal(sucursal);

            if (!mensaje.isError()) {
                Utilidades.mostrarAlertaSimple("Eliminar sucursal", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                Utilidades.mostrarAlertaSimple("Eliminar sucursal", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            }

        } else {
            Utilidades.mostrarAlertaSimple("Operacion eliminar", "La operación eliminar se ha cancelado", Alert.AlertType.INFORMATION);
        }
        
        if (moduloSucursalController != null) {
            moduloSucursalController.obtenerSucursales();
        }
    }

    @FXML
    private void btSubirInfromacionSucursal(ActionEvent event) {

        if (isEdicion) {
            recuperarDatos();
            editarSucursal(sucursal);
        } else {
            recuperarDatos();
            registrarSucursal(sucursal);

        }

    }

    private void registrarSucursal(Sucursal sucursal) {
        Mensaje mensaje = SucursalDAO.registrarSucursal(sucursal);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Sucursal guardada", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al registrar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarSucursal(Sucursal sucursal) {
        Mensaje mensaje = SucursalDAO.editarSucursal(sucursal);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Sucursal editada", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al editar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btEditarInfromacionSucursal(ActionEvent event) {
        habilitarComonentes(false);
    }

    private void habilitarComonentes(boolean isEdicion) {

        if (isEdicion) {

            vbBotones.getChildren().clear();
            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);
            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarSucursal);
            habilitarTextEdit(false);

        } else {

            vbBotones.getChildren().clear();
            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuradarInfo);
            vbBotones.getChildren().remove(btEliminar);
            vbBotones.getChildren().remove(btEditarSucursal);
            habilitarTextEdit(true);

        }

    }

    private void cargarDatos(Sucursal sucursal, boolean isEdicion) {

        if (isEdicion) {

            habilitarComonentes(isEdicion);
            tfNombre.setText(sucursal.getNombreSucursal());

            tfDireccion.setText(sucursal.getDireccion());
            tfCodigoPostal.setText(sucursal.getCodigoPostal());
            tfColonia.setText(sucursal.getColonia());
            tfCiudad.setText(sucursal.getCiudad());
            tfTelefono.setText(sucursal.getTelefono());
            tfLatitud.setText(sucursal.getLatitud());
            tfLongitud.setText(sucursal.getLongitud());
            tfEncargado.setText(sucursal.getNombreEncargado());
        } else {
            habilitarComonentes(isEdicion);
        }
    }

    private void habilitarTextEdit(Boolean editable) {

        tfNombre.setEditable(editable);
        tfDireccion.setEditable(editable);
        tfCodigoPostal.setEditable(editable);
        tfColonia.setEditable(editable);
        tfCiudad.setEditable(editable);
        tfTelefono.setEditable(editable);
        tfLatitud.setEditable(editable);
        tfLongitud.setEditable(editable);
        tfEncargado.setEditable(editable);
        cbEmpresa.setDisable(!editable);

    }

    private void cargarEmpresas() {
        empresas = FXCollections.observableArrayList();
        List<Empresa> info = EmpresaDAO.obtenerEmpresas();
        empresas.addAll(info);
        cbEmpresa.setItems(empresas);
        
    }

    private void recuperarDatos() {

        sucursal.setNombreSucursal(tfNombre.getText());
        Empresa empresaSeleccion = empresas.get(cbEmpresa.getSelectionModel().getSelectedIndex());
        sucursal.setIdEmpresa(empresaSeleccion.getIdEmpresa());
        sucursal.setDireccion(tfDireccion.getText());
        sucursal.setCodigoPostal(tfCodigoPostal.getText());
        sucursal.setColonia(tfColonia.getText());
        sucursal.setCiudad(tfCiudad.getText());
        sucursal.setTelefono(tfTelefono.getText());
        sucursal.setLatitud(tfLatitud.getText());
        sucursal.setLongitud(tfLongitud.getText());
        sucursal.setNombreEncargado(tfEncargado.getText());

    }

    private void seleccionarEmpresa(int idEmpresa) {

        for (Empresa empresa : cbEmpresa.getItems()) {

            if (empresa.getIdEmpresa() == idEmpresa) {

                cbEmpresa.getSelectionModel().select(empresa);
                break;
            }
        }
    }

    private int buscarIdEmpresa(int idEmpresa) {
        for (int i = 0; i < empresas.size(); i++) {
            if (empresas.get(i).getIdEmpresa() == idEmpresa);
            break;
        }
        return 0;
    }

    private void cerrarVentana() {
        Stage stage = (Stage) tfCiudad.getScene().getWindow();
        stage.close();
    }

}
