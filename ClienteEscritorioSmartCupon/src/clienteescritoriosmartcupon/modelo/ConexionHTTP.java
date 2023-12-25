/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo;

import clienteescritoriosmartcupon.modelo.pojo.CodigoHTTP;
import clienteescritoriosmartcupon.modelo.pojo.Usuario;
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

    public static CodigoHTTP peticionGET(String url) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {

            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("GET");
            int cRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(cRespuesta);

            if (cRespuesta == HttpURLConnection.HTTP_OK) {

                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));

            } else {

                respuesta.setContenido("CODE ERROR: " + cRespuesta);

            }

        } catch (MalformedURLException ex) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error" + ex.getMessage());

        } catch (IOException iox) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error" + iox.getMessage());

        }

        return respuesta;
    }
    
        public static CodigoHTTP peticionDELETE(String url) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {

            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("DELETE");
            int cRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(cRespuesta);

            if (cRespuesta == HttpURLConnection.HTTP_OK) {

                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));

            } else {

                respuesta.setContenido("CODE ERROR: " + cRespuesta);

            }

        } catch (MalformedURLException ex) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error" + ex.getMessage());

        } catch (IOException iox) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error" + iox.getMessage());

        }

        return respuesta;
    }


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
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            } else {
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }

        } catch (MalformedURLException ex) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error" + ex.getMessage());

        } catch (IOException iox) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error" + iox.getMessage());

        }

        return respuesta;
    }

    public static CodigoHTTP peticionPOSTJSON(String url, String json) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {

            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("POST");
            conexionHttp.setRequestProperty("Content-Type", "application/json");
            conexionHttp.setDoOutput(true);
            //Escribir datos en el cuerpo de la petición
            OutputStream os = conexionHttp.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();//Termina la escritura
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            } else {
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }

        } catch (MalformedURLException ex) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error" + ex.getMessage());

        } catch (IOException iox) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error" + iox.getMessage());

        }

        return respuesta;
    }

    public static CodigoHTTP peticionPUTJSON(String url, String json) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {

            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("PUT");
            conexionHttp.setRequestProperty("Content-Type", "application/json");
            conexionHttp.setDoOutput(true);
            //Escribir datos en el cuerpo de la petición
            OutputStream os = conexionHttp.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();//Termina la escritura
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            } else {
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }

        } catch (MalformedURLException ex) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error" + ex.getMessage());

        } catch (IOException iox) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
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
    
        public static CodigoHTTP peticionPUTImagen(String url, byte[] imagen) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {

            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("PUT");
            conexionHttp.setDoOutput(true);
            //Escribir datos en el cuerpo de la petición
            OutputStream os = conexionHttp.getOutputStream();
            os.write(imagen);
            os.flush();
            os.close();//Termina la escritura
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            } else {
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }

        } catch (MalformedURLException ex) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error" + ex.getMessage());

        } catch (IOException iox) {

            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error" + iox.getMessage());

        }

        return respuesta;

    }
        
    public static CodigoHTTP postRequest(String url, Usuario usuario) {

        CodigoHTTP respuesta = new CodigoHTTP();
        String jsonBody = JsonUtility.createJson(usuario);
        System.out.println(jsonBody);
        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("POST");
            conexionHttp.setRequestProperty("Content-Type", "application/json");
            conexionHttp.setDoOutput(true);

            // Escribir el cuerpo de la solicitud
            try (OutputStream os = conexionHttp.getOutputStream()) {
                byte[] input = jsonBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                System.out.println("OK, Saved");
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            } else {
                System.out.println("Fail" + codigoRespuesta);
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }
        } catch (MalformedURLException ex) {
            System.out.println("Fail mal formed");
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error: " + ex.getMessage());
        } catch (IOException iox) {
            System.out.println("Fail IOException");
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: " + iox.getMessage());
        }
        return respuesta;
    }

     public static CodigoHTTP putRequest(String url, Usuario usuario) {

        CodigoHTTP respuesta = new CodigoHTTP();
        String jsonBody = JsonUtility.createJson(usuario);
        System.out.println(jsonBody);
        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("PUT");
            conexionHttp.setRequestProperty("Content-Type", "application/json");
            conexionHttp.setDoOutput(true);

            // Escribir el cuerpo de la solicitud
            try (OutputStream os = conexionHttp.getOutputStream()) {
                byte[] input = jsonBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                System.out.println("OK, updated");
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            } else {
                System.out.println("Fail" + codigoRespuesta);
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }
        } catch (MalformedURLException ex) {
            System.out.println("Fail mal formed");
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error: " + ex.getMessage());
        } catch (IOException iox) {
            System.out.println("Fail IOException");
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: " + iox.getMessage());
        }
        return respuesta;
    }
}
