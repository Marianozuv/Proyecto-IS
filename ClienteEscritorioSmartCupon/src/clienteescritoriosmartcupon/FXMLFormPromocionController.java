package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Empresa;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.Promocion;
import clienteescritoriosmartcupon.modelo.pojo.dao.PromocionDAO;
import clienteescritoriosmartcupon.modelo.pojo.Sucursal;
import clienteescritoriosmartcupon.modelo.pojo.dao.SucursalDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;



public class FXMLFormPromocionController implements Initializable {

    private boolean isEdicion;
    private boolean isEliminar;
    private Promocion promocion;
    private ObservableList<Sucursal> sucursales;
    private FXMLModuloPromocionController moduloPromocionController;
    private File imagen;
    private ObservableList<Promocion> categorias;
    
    @FXML
    private Label lbUsuario;
    @FXML
    private ComboBox<Sucursal> cbAsignarSucursal;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private TextField tfNombrePromocion;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private TextField tfRestricciones;
    @FXML
    private TextField tfNumCupones;
    @FXML
    private ComboBox<Promocion> cbCategoria;
    @FXML
    private ImageView ivImagenPromocion;
    @FXML
    private HBox vbBotones;
    @FXML
    private Button btEliminarPromocion;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btGuardarPromocion;
    @FXML
    private Button btEditarPromocion;
    @FXML
    private Button btVerImagen;
    @FXML
    private Button btSubirImagen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setModuloPromocionController(FXMLModuloPromocionController controller) {
        this.moduloPromocionController = controller;
    }
 
    public void inicializarInformacion(Promocion promocion, boolean isEdicion) {

        if (isEdicion) {
            this.promocion = promocion;
            this.isEdicion = isEdicion;
            cargarDatos(promocion, isEdicion);
            cargarSucursales();
            cargarCategorias();
            //seleccionarSucursal(promocion.getIdSucursal);
        } else {
            this.promocion = new Promocion();
            cargarDatos(null, isEdicion);
            cargarSucursales();
        }

    }
     
     
    @FXML
    private void cbAsignarSucursal(ActionEvent event) {
    }

    @FXML
    private void dpFechaInicio(ActionEvent event) {
    }

    @FXML
    private void dpFechafin(ActionEvent event) {
    }

    @FXML
    private void cbCategoria(ActionEvent event) {
    }

    @FXML
    private void btVerImagen(ActionEvent event) {
    }

    @FXML
    private void btSubirImagen(ActionEvent event) {
    }

    @FXML
    private void btEliminarPromocion(ActionEvent event) {
        if (Utilidades.mostrarAlertaConfirmacion("Eliminar promoción", "¿Está seguro de eliminar la promoción?")) {
            Mensaje mensaje = PromocionDAO.eliminarPromocion(promocion);

            if (!mensaje.isError()) {
                Utilidades.mostrarAlertaSimple("Eliminar promoción", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                Utilidades.mostrarAlertaSimple("Eliminar promoción", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            }

        } else {
            Utilidades.mostrarAlertaSimple("Operacion eliminar", "La operación eliminar se ha cancelado", Alert.AlertType.INFORMATION);
        }
        
        if (moduloPromocionController != null) {
            moduloPromocionController.obtenerPromociones();
        }
    }

    @FXML
    private void btCancelar(ActionEvent event) {
        if (isEdicion) {
            habilitarComponentes(isEdicion);
            cargarDatos(promocion, isEdicion);
        } else {

            try {

                Stage stagePrincipal = (Stage) tfNombrePromocion.getScene().getWindow();
                FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLModuloPromocion.fxml"));
                Parent vista = loadVista.load();
                FXMLModuloPromocionController controladorHome = loadVista.getController();
                Scene scene = new Scene(vista);
                stagePrincipal.setScene(scene);
                stagePrincipal.show();

            } catch (Exception e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
    }
    
    
     @FXML
    private void btSubirInformacionPromocion(ActionEvent event) {

        if (isEdicion) {
            recuperarDatos();
            editarPromocion(promocion);
        } else {
            recuperarDatos();
            registrarPromocion(promocion);

        }

    }
    
    
     private void registrarPromocion(Promocion promocion) {
        Mensaje mensaje = PromocionDAO.registrarPromocion(promocion);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Promoción guardada", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al registrar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarPromocion(Promocion promocion) {
        Mensaje mensaje = PromocionDAO.editarPromocion(promocion);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Promoción editada", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al editar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    

    @FXML
    private void btEditarInformacionPromocion(ActionEvent event) {
        habilitarComponentes(false);
    }
    
    
    
    
    private void cargarDatos(Promocion promocion, boolean isEdicion) {

        if (isEdicion) {

            habilitarComponentes(isEdicion);
            tfNombrePromocion.setText(promocion.getNombrePromocion());
            tfDescripcion.setText(promocion.getDescripcion());
            tfRestricciones.setText(promocion.getRestricciones());
            tfNumCupones.setText(String.valueOf(promocion.getCuponesMaximos()));
        } else {
            habilitarComponentes(isEdicion);
        }
    }
    
     private void recuperarDatos() {
         
        promocion.setNombrePromocion(tfNombrePromocion.getText());
        promocion.setDescripcion(tfDescripcion.getText());
        promocion.setRestricciones(tfRestricciones.getText());
        promocion.setCuponesMaximos(Integer.valueOf(tfNumCupones.getText()));
        Sucursal sucursalSeleccion = sucursales.get(cbAsignarSucursal.getSelectionModel().getSelectedIndex());
        
        
        //FALTA CARGAR CATEGORIAS Y LAS DE LAS FECHAS     
        
    }
    
    private void cargarSucursales() {
        sucursales = FXCollections.observableArrayList();
        List<Sucursal> info = SucursalDAO.obtenerSucursales();
        sucursales.addAll(info);
        cbAsignarSucursal.setItems(sucursales);
    }
    
     private void cargarCategorias() {
       //NO SE BIEN COMO CARGAR LAS CATEGORIAS
    }
   
    private void habilitarComponentes(boolean isEdicion) {

        if (isEdicion) {
            
            vbBotones.getChildren().clear();
            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuardarPromocion);
            vbBotones.getChildren().remove(btEliminarPromocion);
            vbBotones.getChildren().remove(btEditarPromocion);
            habilitarTextEdit(false);

        } else {

            vbBotones.getChildren().clear();
            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuardarPromocion);
            vbBotones.getChildren().remove(btEliminarPromocion);
            vbBotones.getChildren().remove(btEditarPromocion);
            habilitarTextEdit(true);

        }

    }
 
    private void habilitarTextEdit(Boolean editable) {

        tfNombrePromocion.setEditable(editable);
        tfDescripcion.setEditable(editable);
        tfRestricciones.setEditable(editable);
        cbAsignarSucursal.setDisable(!editable);
        cbCategoria.setEditable(editable);
        dpFechaInicio.setEditable(editable);
        dpFechaFin.setEditable(editable);
        
    }
    
    private void seleccionarSucursal(int idSucursal) {

        for (Sucursal sucursal : cbAsignarSucursal.getItems()) {

            if (sucursal.getIdSucursal() == idSucursal) {

                cbAsignarSucursal.getSelectionModel().select(sucursal);
                break;
            }
        }
    }

    private int buscarIdSucursal(int idSucursal) {
        for (int i = 0; i < sucursales.size(); i++) {
            if (sucursales.get(i).getIdSucursal() == idSucursal);
            break;
        }
        return 0;
    }

    private void cerrarVentana() {
        Stage stage = (Stage) tfNombrePromocion.getScene().getWindow();
        stage.close();
    }
    
    
}
