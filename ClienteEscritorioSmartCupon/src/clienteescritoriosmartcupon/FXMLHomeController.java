/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mateo
 */
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

            Stage stagePrincipal = (Stage) lbUsuario.getScene().getWindow();
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLModuloEmpresa.fxml"));
            Parent vista = loadVista.load();
            FXMLModuloEmpresaController moduloEmpresaController = loadVista.getController();
            Scene scene = new Scene(vista);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();

        } catch (Exception e) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }

    @FXML
    private void btModuloSucursales(ActionEvent event) {
    }

    @FXML
    private void btModuloCupones(ActionEvent event) {
    }

    @FXML
    private void btModuloUsuarios(ActionEvent event) {
    }

    @FXML
    private void btModuloPromociones(ActionEvent event) {
    }

}
