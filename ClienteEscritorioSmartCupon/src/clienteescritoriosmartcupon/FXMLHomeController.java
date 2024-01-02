package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Usuario;
import clienteescritoriosmartcupon.utils.Roles;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLHomeController implements Initializable {

    private Usuario usuarioSesion;
    @FXML
    private Label lbUsuario;
    @FXML
    private Pane pModuloEmpresa;
    @FXML
    private Pane pModuloSucursales;
    @FXML
    private Pane pModuloCupones;
    @FXML
    private Pane pModuloUsuarios;
    @FXML
    private Pane pModuloPromociones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarHome(Usuario usuarioSesion) {

        pModuloCupones.setVisible(false);
        pModuloEmpresa.setVisible(false);
        pModuloSucursales.setVisible(false);
        pModuloPromociones.setVisible(false);
        pModuloUsuarios.setVisible(false);

        this.usuarioSesion = usuarioSesion;
        lbUsuario.setText(usuarioSesion.getNombre() + " " + usuarioSesion.getApellidoPaterno());

        if (usuarioSesion.getRol().equals(Roles.ADMIN_GENERAL)) {
            pModuloCupones.setVisible(true);
            pModuloEmpresa.setVisible(true);
            pModuloSucursales.setVisible(true);
            pModuloPromociones.setVisible(true);
            pModuloUsuarios.setVisible(true);
        }

        if (usuarioSesion.getRol().equals(Roles.ADMIN_COMERCIAL)) {
            pModuloSucursales.setVisible(true);
            pModuloCupones.setVisible(true);
            pModuloPromociones.setVisible(true);
        }
    }

    @FXML
    private void btModuloEmpresas(ActionEvent event) {

        try {

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLModuloEmpresa.fxml"));
            Parent vista = vistaLoad.load();

            FXMLModuloEmpresaController controller = vistaLoad.getController();

            Stage stage = new Stage();
            Scene escenaEditarPaciente = new Scene(vista);
            stage.setScene(escenaEditarPaciente);
            stage.setTitle("Infromación empresa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    private void btModuloSucursales(ActionEvent event) {

        try {

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLModuloSucursal.fxml"));
            Parent vista = vistaLoad.load();

            FXMLModuloSucursalController controller = vistaLoad.getController();

            Stage stage = new Stage();
            Scene escena = new Scene(vista);
            stage.setScene(escena);
            stage.setTitle("Infromación de sucursales");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btModuloCupones(ActionEvent event) {
    }

    @FXML
    private void btModuloUsuarios(ActionEvent event) {
        try {

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLModuloUsuario.fxml"));
            Parent vista = vistaLoad.load();

            FXMLModuloUsuarioController controller = vistaLoad.getController();

            Stage stage = new Stage();
            Scene escenaEditarPaciente = new Scene(vista);
            stage.setScene(escenaEditarPaciente);
            stage.setTitle("Infromación de Usuarios");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btModuloPromociones(ActionEvent event) {
    }

}
