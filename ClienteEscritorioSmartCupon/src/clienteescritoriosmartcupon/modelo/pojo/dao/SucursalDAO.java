package clienteescritoriosmartcupon.modelo.pojo.dao;

import clienteescritoriosmartcupon.modelo.ConexionHTTP;
import clienteescritoriosmartcupon.modelo.pojo.CodigoHTTP;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.Sucursal;
import clienteescritoriosmartcupon.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class SucursalDAO {

    public static List<Sucursal> obtenerSucursales() {
        List<Sucursal> sucursales = null;

        String url = Constantes.URL_WS + "sucursal/lista";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoListadoSucursal = new TypeToken<List<Sucursal>>() {
            }.getType();
            sucursales = gson.fromJson(respuesta.getContenido(), tipoListadoSucursal);
        } else {
            System.out.println(respuesta.getContenido());
            System.out.println(respuesta.getCodigoRespuesta());
        }

        return sucursales;
    }

    public static Mensaje registrarSucursal(Sucursal sucursal) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "sucursal/registrar";

        Gson gson = new Gson();
        String parametros = gson.toJson(sucursal);

        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionPOSTJSON(url, parametros);

        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            mensaje = gson.fromJson(codigoRespuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al registrar la sucursal");
        }

        return mensaje;
    }

    public static Mensaje editarSucursal(Sucursal sucursal) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "sucursal/editar";

        Gson gson = new Gson();
        String parametros = gson.toJson(sucursal);

        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionPUTJSON(url, parametros);

        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            mensaje = gson.fromJson(codigoRespuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al editar la sucursal");
        }

        return mensaje;
    }

    public static Mensaje eliminarSucursal(Sucursal sucursal) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "sucursal/eliminar/" + sucursal.getIdSucursal();
        Gson gson = new Gson();

        CodigoHTTP respuesta = ConexionHTTP.peticionDELETE(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error al eliminar la sucursal");
        }
        return mensaje;
    }
}
