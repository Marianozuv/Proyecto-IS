/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLHomeController implements Initializable {

    private Usuario usuarioSesion;
    @FXML
    private Label lbUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void inicializarHome(Usuario usuarioSesion) {  
        this.usuarioSesion = usuarioSesion;
        lbUsuario.setText(usuarioSesion.getNombre()+ " "+usuarioSesion.getApellidoPaterno());
    }

    @FXML
    private void btModuloEmpresas(ActionEvent event) {
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
