/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.Promocion;
import clienteescritoriosmartcupon.modelo.pojo.dao.PromocionDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLModuloCuponesController implements Initializable {

    private Promocion promocion;
    
    @FXML
    private Label lbCupones;
    @FXML
    private Label lbCodigo;
    @FXML
    private TextField tfCodigo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        promocion = new Promocion();
    }    

    @FXML
    private void btCanjearCupon(ActionEvent event) {
        promocion.setCodigoPromocion(tfCodigo.getText());
        canjearCupon(promocion);
    }
    
    private void canjearCupon(Promocion promocion) {
        Mensaje mensaje = PromocionDAO.canjearCupon(promocion);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Cupon Canjeado", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al canjear el Cupon", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana() {
        Stage stage = (Stage) tfCodigo.getScene().getWindow();
        stage.close();
    }
}
