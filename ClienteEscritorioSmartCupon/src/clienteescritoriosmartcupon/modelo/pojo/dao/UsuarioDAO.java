/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo.pojo.dao;

import clienteescritoriosmartcupon.modelo.ConexionHTTP;
import clienteescritoriosmartcupon.modelo.pojo.CodigoHTTP;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.Rol;
import clienteescritoriosmartcupon.modelo.pojo.Usuario;
import clienteescritoriosmartcupon.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author andre
 */
public class UsuarioDAO {
    public static List<Usuario> get(){
        List<Usuario> usuarios = null;
        String url = Constantes.URL_WS + "usuario/lista";
        CodigoHTTP  codigoRespuesta = ConexionHTTP.peticionGET(url);
        
        if(codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Type tipoListaUsuario = new TypeToken<List<Usuario>>(){}.getType();
            Gson gson = new Gson();
            usuarios = gson.fromJson(codigoRespuesta.getContenido(), tipoListaUsuario);
        }else{
            System.out.println(codigoRespuesta.getContenido());
            System.out.println(codigoRespuesta.getCodigoRespuesta());
        }
        return usuarios;
    }
    
    public static List<Rol> obtenerRol(){
        List<Rol> roles = new ArrayList<>();
        String url = Constantes.URL_WS + "usuario/listaRol";
        CodigoHTTP  respuesta = ConexionHTTP.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type tipoListaRoles = new TypeToken<List<Rol>>(){}.getType();
            roles = gson.fromJson(respuesta.getContenido(), tipoListaRoles);
        }
        return roles;
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
