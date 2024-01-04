/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Promocion;
import clienteescritoriosmartcupon.modelo.pojo.dao.PromocionDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
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
public class FXMLModuloPromocionController implements Initializable {

    private Promocion promocion;
    private ObservableList<Promocion> promociones;
    private FilteredList<Promocion> filteredPromocion;

    @FXML
    private Label lbUsuario;
    @FXML
    private TableView<Promocion> tvPromociones;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colCuponesPromoción;
    @FXML
    private TableColumn colFechaInicio;
    @FXML
    private TableColumn colFechaTermino;
    @FXML
    private TableColumn colCodigoPromocion;
    @FXML
    private TableColumn colEstatus;
    @FXML
    private TextField tfBuscador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerPromociones();
        cargarPromociones();

        filteredPromocion = new FilteredList<>(promociones, p -> true);
        tvPromociones.setItems(filteredPromocion);

        tfBuscador.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPromocion.setPredicate(promocion -> {
                // Si el TextField está vacío, muestra todos los resultados
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Convertir el texto a minúsculas para hacer una búsqueda no sensible a mayúsculas
                String textoBusqueda = newValue.toLowerCase();
                // Aquí define tu lógica de filtrado basada en tus criterios
                return promocion.getNombrePromocion().toLowerCase().contains(textoBusqueda)
                        || promocion.getFechaInicioPromocion().toLowerCase().contains(textoBusqueda)
                        || promocion.getFechaTerminoPromocion().toLowerCase().contains(textoBusqueda);
            });
        });
    }

    void obtenerPromociones() {
        try {
            promociones = FXCollections.observableArrayList();
            List<Promocion> info = PromocionDAO.obtenerPromociones();
            promociones.addAll(info);
            tvPromociones.setItems(promociones);
            tvPromociones.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarPromociones() {
        colNombre.setCellValueFactory(new PropertyValueFactory("nombrePromocion"));
        colCuponesPromoción.setCellValueFactory(new PropertyValueFactory("cuponesMaximos"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("fechaInicioPromocion"));
        colFechaTermino.setCellValueFactory(new PropertyValueFactory("fechaTerminoPromocion"));
        colCodigoPromocion.setCellValueFactory(new PropertyValueFactory("codigoPromocion"));

    }

    @FXML
    private void btRegistroPromocion(ActionEvent event) throws IOException {

        Utilidades.mostrarAlertaSimple("Formulario para registro", "Registro para promocion", Alert.AlertType.INFORMATION);

        FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormPromocion.fxml"));
        Parent vista = vistaLoad.load();

        FXMLFormPromocionController controller = vistaLoad.getController();
        controller.inicializarInformacion(null, false);

        Stage stage = new Stage();
        Scene scene = new Scene(vista);
        stage.setScene(scene);
        stage.setTitle("Registrar sucursal");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        obtenerPromociones();
    }

    @FXML
    private void btVerInfoPromocion(ActionEvent event) throws IOException {

        int posicionSeleccionada = tvPromociones.getSelectionModel().getSelectedIndex();

        if (posicionSeleccionada != -1) {

            Promocion promocion = promociones.get(posicionSeleccionada);
            Utilidades.mostrarAlertaSimple("Ver información promocion", "Ha seleccionado la promocion: " + promocion.getNombrePromocion(), Alert.AlertType.INFORMATION);

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormPromocion.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFormPromocionController controller = vistaLoad.getController();
            controller.setModuloPromocionController(this);
            controller.inicializarInformacion(promocion, true);

            Stage stage = new Stage();
            Scene escenaEditarPaciente = new Scene(vista);
            stage.setScene(escenaEditarPaciente);
            stage.setTitle("Información promoción");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } else {
            Utilidades.mostrarAlertaSimple("Ver información promoción", "Para poder modificar debes seleccionar una promoción", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void btAsignarSucursal(ActionEvent event) {

        int posicionSeleccionada = tvPromociones.getSelectionModel().getSelectedIndex();

        if (posicionSeleccionada != -1) {
            try {
                Promocion promocion = promociones.get(posicionSeleccionada);
                
                FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLAsignarSucursal.fxml"));
                Parent vista = vistaLoad.load();

                FXMLAsignarSucursalController controller = vistaLoad.getController();
                controller.inicializarInfomracion(promocion);

                Stage stage = new Stage();
                Scene scene = new Scene(vista);
                stage.setScene(scene);
                stage.setTitle("Asignar sucursales");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

            } catch (Exception e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Utilidades.mostrarAlertaSimple("Asignar sucursal", "Para poder asignar sucursales debes seleccionar una promoción", Alert.AlertType.INFORMATION);
        }
    }

}
