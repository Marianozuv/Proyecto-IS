/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author mateo
 */
public class Utilidades {

    public static void mostrarAlertaSimple(String titulo, String msj, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(msj);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }

    public static boolean mostrarAlertaConfirmacion(String titulo, String msj) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(msj);
        ButtonType resultado = alerta.showAndWait().orElse(ButtonType.CANCEL);
        return resultado == ButtonType.OK;
    }

}
