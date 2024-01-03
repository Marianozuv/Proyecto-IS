package clienteescritoriosmartcupon.modelo.pojo.dao;

import clienteescritoriosmartcupon.modelo.ConexionHTTP;
import clienteescritoriosmartcupon.modelo.pojo.CodigoHTTP;
import clienteescritoriosmartcupon.modelo.pojo.Promocion;
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

public class PromocionDAO {

    public static List<Promocion> obtenerPromociones() {
        List<Promocion> promociones = null;

        String url = Constantes.URL_WS + "promocion/lista";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoListadoPromocion = new TypeToken<List<Promocion>>() {
            }.getType();
            promociones = gson.fromJson(respuesta.getContenido(), tipoListadoPromocion);
        } else {
            System.out.println(respuesta.getContenido());
            System.out.println(respuesta.getCodigoRespuesta());
        }

        return promociones;
    }
    

    public static Mensaje registrarPromocion(Promocion promocion) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "promocion/registrar";

        Gson gson = new Gson();
        String parametros = gson.toJson(promocion);

        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionPOSTJSON(url, parametros);

        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            mensaje = gson.fromJson(codigoRespuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al registrar la promoción");
        }

        return mensaje;
    }

    public static Mensaje editarPromocion(Promocion promocion) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "promocion/editar";

        Gson gson = new Gson();
        String parametros = gson.toJson(promocion);

        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionPUTJSON(url, parametros);

        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            mensaje = gson.fromJson(codigoRespuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al editar la promocion");
        }

        return mensaje;
    }

    public static Mensaje subirImagenPromocion(File fotoFile, int idPromocion) {

        Mensaje msj = new Mensaje();

        try {

            String url = Constantes.URL_WS + "empresa/subirImagenPromocion/" + idPromocion;
            byte[] imagen = Files.readAllBytes(fotoFile.toPath());
            CodigoHTTP respuesta = ConexionHTTP.peticionPUTImagen(url, imagen);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                Gson gson = new Gson();
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                msj.setError(true);
                msj.setMensaje("Hubo un error al intentar subir la imagen");
            }

        } catch (IOException ex) {
            msj.setError(true);
            msj.setMensaje("El archivo seleccionado no puede ser enviado para su almacenamiento");
        }

        return msj;
    }

    public static Promocion obtenerImagenPromocion(int idPromocion) {
        Promocion promocion = null;
        String url = Constantes.URL_WS + "empresa/obtenerImagenPromocion/" + idPromocion;
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            promocion = gson.fromJson(respuesta.getContenido(), Promocion.class);
        }

        return promocion;
    }
    
    
    
    public static Mensaje eliminarPromocion(Promocion promocion) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "promocion/eliminar/" +promocion.getIdPromocion();
        Gson gson = new Gson();
        
        CodigoHTTP respuesta = ConexionHTTP.peticionDELETE(url);
        if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al eliminar la promoción");
        }
        return mensaje;
    }
    
    public static Mensaje canjearCupon(Promocion promocion) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "promocion/canjearCupon";

        Gson gson = new Gson();
        String parametros = gson.toJson(promocion);

        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionPUTJSON(url, parametros);

        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            mensaje = gson.fromJson(codigoRespuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al editar la promocion");
        }
        return mensaje;
    }
}
