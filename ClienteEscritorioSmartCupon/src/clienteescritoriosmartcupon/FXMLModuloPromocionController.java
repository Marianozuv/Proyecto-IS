package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Promocion;
import clienteescritoriosmartcupon.modelo.pojo.dao.PromocionDAO;
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

public class FXMLModuloPromocionController implements Initializable {
    
    private Promocion promocion;
    private ObservableList<Promocion> promociones;
    private FilteredList<Promocion> filteredPromocion;
    @FXML
    private Label lbUsuario;
    @FXML
    private TextField tfBuscador;
    @FXML
    private TableView tvPromocion;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNombrePromocion;
    @FXML
    private TableColumn colSucursal;
    @FXML
    private TableColumn colFechaInicio;
    @FXML
    private TableColumn colFechaFin;
    @FXML
    private TableColumn colEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerPromociones();
        cargarPromociones();
        
        filteredPromocion = new FilteredList<>(promociones, p -> true);
        tvPromocion.setItems(filteredPromocion);
        
        tfBuscador.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPromocion.setPredicate(promocion -> {
                // Si el TextField está vacío, muestra todos los resultados
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Convertir el texto a minúsculas para hacer una búsqueda no sensible a mayúsculas
                String textoBusqueda = newValue.toLowerCase();

                // Aquí define tu lógica de filtrado basada en tus criterios
                return promocion.getNombrePromocion().toLowerCase().contains(textoBusqueda);
            });
        });
    }

    void obtenerPromociones() {
        try {
            promociones = FXCollections.observableArrayList();
            List<Promocion> info = PromocionDAO.obtenerPromociones();
            promociones.addAll(info);
            tvPromocion.setItems(promociones);
            tvPromocion.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarPromociones() {
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigoPromocion"));
        colNombrePromocion.setCellValueFactory(new PropertyValueFactory("nombrePromocion"));
        colSucursal.setCellValueFactory(new PropertyValueFactory("nombreSucursal"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("fechaInicioPromocion"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory("fechaTerminoPromocion"));
        colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        
        
    }

    private void btRegistroPromocion(ActionEvent event) throws IOException{

        
        Utilidades.mostrarAlertaSimple("Formulario para registro", "Registro para promoción", Alert.AlertType.INFORMATION);

        FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormPromocion.fxml"));
        Parent vista = vistaLoad.load();

        FXMLFormSucursalController controller = vistaLoad.getController();
        controller.inicializarInformacion(null, false);

        Stage stage = new Stage();
        Scene escenaEditarPaciente = new Scene(vista);
        stage.setScene(escenaEditarPaciente);
        stage.setTitle("Registrar promocion");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        obtenerPromociones();
    }

    private void btVerInfoPromocion(ActionEvent event) throws IOException {

        int posicionSeleccionada = tvPromocion.getSelectionModel().getSelectedIndex();

        if (posicionSeleccionada != -1) {

            Promocion promocion = promociones.get(posicionSeleccionada);
            Utilidades.mostrarAlertaSimple("Ver información promoción", "Ha seleccionado la promoción: " + promocion.getNombrePromocion(), Alert.AlertType.INFORMATION);

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormPromocion.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFormPromocionController controller = vistaLoad.getController();
            controller.setModuloPromocionController(this);
            controller.inicializarInformacion(promocion, true);

            Stage stage = new Stage();
            Scene escenaEditarPaciente = new Scene(vista);
            stage.setScene(escenaEditarPaciente);
            stage.setTitle("Infromación promoción");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } else {
            Utilidades.mostrarAlertaSimple("Ver información promoción", "Para poder modificar debes seleccionar una promoción", Alert.AlertType.INFORMATION);
        }
    }

}
