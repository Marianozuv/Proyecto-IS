package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Usuario;
import clienteescritoriosmartcupon.utils.Roles;
import java.io.IOException;
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

    private String tipoAdmin;
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
        tipoAdmin = usuarioSesion.getRol();

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

        if (tipoAdmin.equals(Roles.ADMIN_GENERAL)) {
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
        } else {
            try {

                FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLModuloSucursalComercial.fxml"));
                Parent vista = vistaLoad.load();

                FXMLModuloSucursalComercialController controller = vistaLoad.getController();
                controller.inicializarInformaciom(usuarioSesion.getIdEmpresa());

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

    }

    @FXML
    private void btModuloCupones(ActionEvent event) {
        try {

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLModuloCupones.fxml"));
            Parent vista = vistaLoad.load();

            FXMLModuloCuponesController controller = vistaLoad.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(vista);
            stage.setScene(scene);
            stage.setTitle("Canje Cupones");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btModuloUsuarios(ActionEvent event) {
        try {

            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLModuloUsuario.fxml"));
            Parent vista = vistaLoad.load();

            FXMLModuloUsuarioController controller = vistaLoad.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(vista);
            stage.setScene(scene);
            stage.setTitle("Infromación de Usuarios");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btModuloPromociones(ActionEvent event) {

        if (tipoAdmin.equals(Roles.ADMIN_GENERAL)) {
            try {

                FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLModuloPromocion.fxml"));
                Parent vista = vistaLoad.load();

                FXMLModuloPromocionController controller = vistaLoad.getController();

                Stage stage = new Stage();
                Scene scene = new Scene(vista);
                stage.setScene(scene);
                stage.setTitle("Información promociones");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

            } catch (Exception e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }

        } else {
            try {

                FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLModuloPromocionComercial.fxml"));
                Parent vista = vistaLoad.load();

                FXMLModuloPromocionComercialController controller = vistaLoad.getController();
                controller.inicializarInformaciom(usuarioSesion.getIdEmpresa());
                
                Stage stage = new Stage();
                Scene scene = new Scene(vista);
                stage.setScene(scene);
                stage.setTitle("Información promociones");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

            } catch (Exception e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    @FXML
    private void btCerrarSesion(ActionEvent event) {

        try {
            
            Stage stagePrincipal = (Stage) lbUsuario.getScene().getWindow();
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
            Parent vista = loadVista.load();
            FXMLLoginController controller = loadVista.getController();
            Scene scene = new Scene(vista);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
            
        } catch (Exception e) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
