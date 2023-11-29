/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.utils;

import javafx.scene.control.Alert;

/**
 *
 * @author mateo
 */
public class Utilidades {
  
    public static void  mostrarAlertaSimple(String titulo, String msj, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(msj);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
   
    
}
