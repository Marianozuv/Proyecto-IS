/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import modelo.pojo.Mensaje;
import modelo.pojo.Usuario;

/**
 *
 * @author andre
 */
public class UsuarioValidator {
    private static boolean isNullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }
    
    public static Mensaje isValid(Usuario usuario) {

        Mensaje response = new Mensaje();
        response.setError(false);
        response.setMensaje("OK");
        
        if (isNullOrEmpty("" + usuario.getIdRol())) {
            response.setMensaje("ID Rol");
        }
        
        if (isNullOrEmpty("" + usuario.getIdEmpresa())) {
            response.setMensaje("ID Empresa");
        }

        if (isNullOrEmpty(usuario.getNombre())) {
            response.setMensaje("Nombre");
        }

        if (isNullOrEmpty(usuario.getApellidoPaterno())) {
            response.setMensaje("Apellido Paterno");
        }

        if (isNullOrEmpty(usuario.getApellidoMaterno())) {
            response.setMensaje("Apellido Materno");
        }

        if (isNullOrEmpty(usuario.getCURP())) {
            response.setMensaje("CURP");
        }

        if (isNullOrEmpty(usuario.getEmail())) {
            response.setMensaje("Email");
        }

        if (isNullOrEmpty(usuario.getUsername())) {
            response.setMensaje("Username");
        }

        if (isNullOrEmpty(usuario.getPassword())) {
            response.setMensaje("Password");
        }

        if (!response.getMensaje().equals("OK")) {
            response.setError(true);
            response.setMensaje(response.getMensaje() + " no puede ser vacio");
        }

        return response;
    }
}
