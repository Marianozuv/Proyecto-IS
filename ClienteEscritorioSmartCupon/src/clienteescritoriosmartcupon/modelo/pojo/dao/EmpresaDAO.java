/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo.pojo.dao;

import clienteescritoriosmartcupon.modelo.ConexionHTTP;
import clienteescritoriosmartcupon.modelo.pojo.CodigoHTTP;
import clienteescritoriosmartcupon.modelo.pojo.Empresa;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author mateo
 */
public class EmpresaDAO {

    public static List<Empresa> obtenerEmpresas() {
        List<Empresa> empresas = null;

        String url = Constantes.URL_WS + "empresa/lista";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoListadoEmpresa = new TypeToken<List<Empresa>>() {
            }.getType();
            empresas = gson.fromJson(respuesta.getContenido(), tipoListadoEmpresa);
        } else {
            System.out.println(respuesta.getContenido());
            System.out.println(respuesta.getCodigoRespuesta());
        }

        return empresas;
    }

    public static Mensaje registrarEmpresa(Empresa empresa) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "empresa/registrar";

        Gson gson = new Gson();
        String parametros = gson.toJson(empresa);

        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionPOSTJSON(url, parametros);

        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            mensaje = gson.fromJson(codigoRespuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al registrar la empresa");
        }

        return mensaje;
    }

    public static Mensaje editarEmpresa(Empresa empresa) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "empresa/editar";

        Gson gson = new Gson();
        String parametros = gson.toJson(empresa);

        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionPUTJSON(url, parametros);

        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            mensaje = gson.fromJson(codigoRespuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al editar la empresa");
        }

        return mensaje;
    }

    public static Mensaje subirLogoEmpresa(File fotoFile, int idEmpresa) {

        Mensaje msj = new Mensaje();

        try {

            String url = Constantes.URL_WS + "empresa/registrarLogoEmpresa/" + idEmpresa;
            byte[] imagen = Files.readAllBytes(fotoFile.toPath());
            CodigoHTTP respuesta = ConexionHTTP.peticionPUTImagen(url, imagen);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                Gson gson = new Gson();
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                msj.setError(true);
                msj.setMensaje("Hubo un error al intentar subir el logo");
            }

        } catch (IOException ex) {
            msj.setError(true);
            msj.setMensaje("El archivo seleccionado no puede ser enviado para su almacenamiento");
        }

        return msj;
    }

    public static Empresa obtenerLogoEmpresa(int idEmpresa) {
        Empresa empresa = null;
        String url = Constantes.URL_WS + "empresa/obtenerLogoEmpresa/" + idEmpresa;
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            empresa = gson.fromJson(respuesta.getContenido(), Empresa.class);
        }

        return empresa;
    }
    
    
    
    public static Mensaje eliminarEmpresa(Empresa empresa) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "empresa/eliminar/" +empresa.getIdEmpresa();
        Gson gson = new Gson();
        
        CodigoHTTP respuesta = ConexionHTTP.peticionDELETE(url);
        if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al registrar la empresa");
        }
        return mensaje;
    }
    
    
}
