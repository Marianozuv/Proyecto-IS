/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo.pojo.dao;

import clienteescritoriosmartcupon.modelo.ConexionHTTP;
import clienteescritoriosmartcupon.modelo.pojo.CodigoHTTP;
import clienteescritoriosmartcupon.modelo.pojo.Sucursal;
import clienteescritoriosmartcupon.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

/**
 *
 * @author mateo
 */
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
    
    
}
