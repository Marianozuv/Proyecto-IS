/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo;

import clienteescritoriosmartcupon.modelo.pojo.CodigoHTTP;
import clienteescritoriosmartcupon.utils.Constantes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author mateo
 */
public class ConexionHTTP {

    public static CodigoHTTP peticionPOST(String url, String parametros) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {

            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("POST");
            conexionHttp.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCondigoRespuesta(codigoRespuesta);
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            } else {
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }

        } catch (MalformedURLException ex) {

            respuesta.setCondigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error" + ex.getMessage());

        } catch (IOException iox) {

            respuesta.setCondigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error" + iox.getMessage());

        }

        return respuesta;
    }

    private static String convertirContenido(InputStream contenido) throws IOException {

        InputStreamReader inputLectura = new InputStreamReader(contenido);
        BufferedReader buffer = new BufferedReader(inputLectura);
        String cadenaEntrada;
        StringBuffer cadenaBuffer = new StringBuffer();

        while ((cadenaEntrada = buffer.readLine()) != null) {
            cadenaBuffer.append(cadenaEntrada);
        }
        buffer.close();

        return cadenaBuffer.toString();

    }
}
