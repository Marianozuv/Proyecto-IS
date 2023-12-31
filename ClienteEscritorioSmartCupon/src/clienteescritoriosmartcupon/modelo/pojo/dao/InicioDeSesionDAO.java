package clienteescritoriosmartcupon.modelo.pojo.dao;

import clienteescritoriosmartcupon.modelo.ConexionHTTP;
import clienteescritoriosmartcupon.modelo.pojo.CodigoHTTP;
import clienteescritoriosmartcupon.modelo.pojo.RespuestaLogin;
import clienteescritoriosmartcupon.utils.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;

public class InicioDeSesionDAO {
    
    public static RespuestaLogin validarLogin(String username, String password) {
        
        RespuestaLogin respuesta = new RespuestaLogin();
        String url = Constantes.URL_WS+"autenticacion/loginEscritorio";
        String parametros = String.format("username=%s&password=%s", username, password);
        
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionPOST(url, parametros);
        
        if(codigoRespuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), RespuestaLogin.class);
        } else {
            respuesta.setError(true);
            respuesta.setContenido("Hubo un error al procesar al realizar la petición");
        }
        
        return respuesta;
    }
    
}
