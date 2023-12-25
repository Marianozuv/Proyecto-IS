/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo;

import clienteescritoriosmartcupon.modelo.pojo.Usuario;

/**
 *
 * @author andre
 */
public class JsonUtility {
    public static String createJson(Usuario usuario) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{")
                .append(addChild("idUsuario", usuario.getIdUsuario() + "", true))
                .append(addChild("idRol", usuario.getIdRol() + "", true))
                .append(addChild("idEmpresa", usuario.getIdEmpresa() + "", true))
                .append(addChild("nombre", usuario.getNombre(), true))
                .append(addChild("apellidoPaterno", usuario.getApellidoPaterno(), true))
                .append(addChild("apellidoMaterno", usuario.getApellidoMaterno(), true))
                .append(addChild("CURP", usuario.getCurp()+ "", true))
                .append(addChild("email", usuario.getEmail()+ "", true))
                .append(addChild("username", usuario.getUsername()+ "", true))
                .append(addChild("password", usuario.getPassword(), false))
                .append("}");

        return jsonBuilder.toString();
    }

    private static String addChild(String key, String val, boolean comma) {
        return String.format("\"%s\":\"%s\"%s", key, val, comma ? "," : "");
    }
}
