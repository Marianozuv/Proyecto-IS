package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Empresa;
import clienteescritoriosmartcupon.modelo.pojo.dao.EmpresaDAO;
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

public class FXMLModuloEmpresaController implements Initializable {
    
    private Empresa empresa;
    private ObservableList<Empresa> empresas;
    private FilteredList<Empresa> filteredEmpresa;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerEmpresas();
        cargarTablaEmpresas();
        
        filteredEmpresa = new FilteredList<>(empresas, p -> true);
        tvEmpresas.setItems(filteredEmpresa);
        
        tfBuscador.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredEmpresa.setPredicate(empresa -> {
                // Si el TextField está vacío, muestra todos los resultados
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Convertir el texto a minúsculas para hacer una búsqueda no sensible a mayúsculas
                String textoBusqueda = newValue.toLowerCase();

                // Aquí define tu lógica de filtrado basada en tus criterios
                return empresa.getNombre().toLowerCase().contains(textoBusqueda)
                        || empresa.getRFC().toLowerCase().contains(textoBusqueda)
                        || empresa.getNombreRepresentanteLegal().toLowerCase().contains(textoBusqueda);
            });
        });
    }

    void obtenerEmpresas() {

        try {
            empresas = FXCollections.observableArrayList();
            List<Empresa> info = EmpresaDAO.obtenerEmpresas();
            empresas.addAll(info);
            tvEmpresas.setItems(empresas);
            tvEmpresas.refresh();
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
        //colEstatus.setCellValueFactory(new PropertyValueFactory("estatus"));
    }

    @FXML
    private void btVerInfoEmpresa(ActionEvent event) throws IOException {

        int posicionSeleccionada = tvEmpresas.getSelectionModel().getSelectedIndex();

        if (posicionSeleccionada != -1) {

            Empresa empresa = empresas.get(posicionSeleccionada);
            Utilidades.mostrarAlertaSimple("Ver información empresa", "Ha seleccionado la empresa: " + empresa.getNombre(), Alert.AlertType.INFORMATION);

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormEmpresa.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFormEmpresaController controller = vistaLoad.getController();
            controller.setModuloEmpresaController(this);
            controller.inicializarInformacion(empresa, true);

            Stage stage = new Stage();
            Scene escenaEditarPaciente = new Scene(vista);
            stage.setScene(escenaEditarPaciente);
            stage.setTitle("Información empresa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } else {
            Utilidades.mostrarAlertaSimple("Ver información empresa", "Para poder modificar debes seleccionar una empresa", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void btRegistroEmpresa(ActionEvent event) throws IOException {

        FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormEmpresa.fxml"));
        Parent vista = vistaLoad.load();

        FXMLFormEmpresaController controller = vistaLoad.getController();
        controller.inicializarInformacion(null, false);

        Stage stage = new Stage();
        Scene escenaEditarPaciente = new Scene(vista);
        stage.setScene(escenaEditarPaciente);
        stage.setTitle("Registrar empresa");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        obtenerEmpresas();
    }
}
