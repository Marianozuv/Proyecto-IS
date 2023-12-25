/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo.pojo.dao;

import clienteescritoriosmartcupon.modelo.ConexionHTTP;
import clienteescritoriosmartcupon.modelo.pojo.CodigoHTTP;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.Usuario;
import clienteescritoriosmartcupon.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author andre
 */
public class UsuarioDAO {
    public static HashMap<String, Object> get(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String url = Constantes.URL_WS + "usuario/lista";
        CodigoHTTP  codigoRespuesta = ConexionHTTP.peticionGET(url);
        
        if(codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            respuesta.put("error", false);
            Type tipoListaUsuario = new TypeToken<List<Usuario>>(){}.getType();
            Gson gson = new Gson();
            List<Usuario> usuarios = gson.fromJson(codigoRespuesta.getContenido(), tipoListaUsuario);
            respuesta.put("usuarios", usuarios);
        }else{
            respuesta.put("error", true);
            respuesta.put("mensaje", "Hubo un error al obtener la información de los usuarios, " + "porfavor intentelo más tarde.");
        }
        return respuesta;
    }
    
    public static Mensaje add(Usuario usuario) {
        Mensaje msj = new Mensaje();
        CodigoHTTP respuestaWS = ConexionHTTP.postRequest(Constantes.URL_WS + "usuario/registrar", usuario);

        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            msj = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la petición para el registro del Usuario.");
        }
        return msj;
    }

    public static Mensaje update(Usuario usuario) {
        Mensaje msj = new Mensaje();
        CodigoHTTP respuestaWS = ConexionHTTP.putRequest(Constantes.URL_WS + "usuario/editar", usuario);

        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            msj = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la petición para el editar al usuario.");
        }
        return msj;
    }
    
    public static Mensaje delete(Usuario usuario) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "usuario/eliminar/" + usuario.getIdUsuario();
        Gson gson = new Gson();
        
        CodigoHTTP respuesta = ConexionHTTP.peticionDELETE(url);
        if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al eliminar al Usuario");
        }
        return mensaje;
    }
}
