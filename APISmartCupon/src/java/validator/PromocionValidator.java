/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;

/**
 *
 * @author andre
 */
public class PromocionValidator {
    private static boolean isNullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }
    
    public static Mensaje isValid(Promocion promocion) {

        Mensaje response = new Mensaje();
        response.setError(false);
        response.setMensaje("OK");
        
        if (isNullOrEmpty("" + promocion.getIdEmpresa())) {
            response.setMensaje("ID Empresa");
        }
        
        if (isNullOrEmpty(promocion.getNombrePromocion())) {
            response.setMensaje("Nombre");
        }

        if (isNullOrEmpty(promocion.getDescripcion())) {
            response.setMensaje("Descripcion");
        }

        if (isNullOrEmpty(promocion.getFechaInicioPromocion())) {
            response.setMensaje("Fecha Inicio");
        }

        if (isNullOrEmpty(promocion.getFechaTerminoPromocion())) {
            response.setMensaje("Fecha Termino");
        }

        if (isNullOrEmpty(promocion.getRestricciones())) {
            response.setMensaje("Restricciones");
        }

        if (isNullOrEmpty("" + promocion.getIdTipoPromocion())) {
            response.setMensaje("Tipo Promocion");
        }

        if (isNullOrEmpty("" + promocion.getPorcentaje_Costo())) {
            response.setMensaje("Procentaje o Costo");
        }

        if (isNullOrEmpty("" + promocion.getIdCategoria())) {
            response.setMensaje("Categoria");
        }
        
        if (isNullOrEmpty("" + promocion.getCuponesMaximos())) {
            response.setMensaje("Cupones");
        }
        
        if (isNullOrEmpty(promocion.getCodigoPromocion())) {
            response.setMensaje("Codigo");
        } else {
            // Validar si el código es alfanumérico y tiene 8 caracteres
            if (!promocion.getCodigoPromocion().matches("[a-zA-Z0-9]{8}")) {
                response.setMensaje("Codigo debe ser alfanumérico y tener 8 caracteres");
            }
        }

        if (!response.getMensaje().equals("OK")) {
            response.setError(true);
            response.setMensaje(response.getMensaje() + " no puede ser vacio");
        }

        return response;
    }
}
