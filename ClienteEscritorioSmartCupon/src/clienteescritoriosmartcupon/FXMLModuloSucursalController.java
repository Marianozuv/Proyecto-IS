package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Sucursal;
import clienteescritoriosmartcupon.modelo.pojo.dao.SucursalDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLModuloSucursalController implements Initializable {
    
    private Sucursal sucursal;
    private ObservableList<Sucursal> sucursales;
    private FilteredList<Sucursal> filteredSucursal;
    @FXML
    private Label lbUsuario;
    private TableView<Sucursal> tvSucursale;
    private TableColumn colNombre;
    private TableColumn colDireccion;
    private TableColumn colTelefono;
    @FXML
    private TextField tfBuscador;
    private TableColumn colNombreEncargado;
    private TableColumn colCodigoPostal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerSucursales();
        cargarSucursales();
        
        filteredSucursal = new FilteredList<>(sucursales, p -> true);
        tvSucursale.setItems(filteredSucursal);
        
        tfBuscador.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredSucursal.setPredicate(sucursal -> {
                // Si el TextField está vacío, muestra todos los resultados
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Convertir el texto a minúsculas para hacer una búsqueda no sensible a mayúsculas
                String textoBusqueda = newValue.toLowerCase();

                // Aquí define tu lógica de filtrado basada en tus criterios
                return sucursal.getNombreSucursal().toLowerCase().contains(textoBusqueda)
                        || sucursal.getDireccion().toLowerCase().contains(textoBusqueda);
            });
        });
    }

    void obtenerSucursales() {
        try {
            sucursales = FXCollections.observableArrayList();
            List<Sucursal> info = SucursalDAO.obtenerSucursales();
            sucursales.addAll(info);
            tvSucursale.setItems(sucursales);
            tvSucursale.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarSucursales() {
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreSucursal"));
        colNombreEncargado.setCellValueFactory(new PropertyValueFactory("nombreEncargado"));
        colCodigoPostal.setCellValueFactory(new PropertyValueFactory("codigoPostal"));
        colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
    }

    private void btRegistroSucursal(ActionEvent event) throws IOException{

        
        Utilidades.mostrarAlertaSimple("Formulario para registro", "Registro para sucursal", Alert.AlertType.INFORMATION);

        FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormSucursal.fxml"));
        Parent vista = vistaLoad.load();

        FXMLFormSucursalController controller = vistaLoad.getController();
        controller.inicializarInformacion(null, false);

        Stage stage = new Stage();
        Scene escenaEditarPaciente = new Scene(vista);
        stage.setScene(escenaEditarPaciente);
        stage.setTitle("Registrar sucursal");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        obtenerSucursales();
    }

    private void btVerInfoSucursal(ActionEvent event) throws IOException {

        int posicionSeleccionada = tvSucursale.getSelectionModel().getSelectedIndex();

        if (posicionSeleccionada != -1) {

            Sucursal sucursal = sucursales.get(posicionSeleccionada);
            Utilidades.mostrarAlertaSimple("Ver información sucursal", "Ha seleccionado la sucursal: " + sucursal.getNombreSucursal(), Alert.AlertType.INFORMATION);

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormSucursal.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFormSucursalController controller = vistaLoad.getController();
            controller.setModuloSucursalController(this);
            controller.inicializarInformacion(sucursal, true);

            Stage stage = new Stage();
            Scene escenaEditarPaciente = new Scene(vista);
            stage.setScene(escenaEditarPaciente);
            stage.setTitle("Información sucursal");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } else {
            Utilidades.mostrarAlertaSimple("Ver información sucursal", "Para poder modificar debes seleccionar una sucursal", Alert.AlertType.INFORMATION);
        }
    }
}
