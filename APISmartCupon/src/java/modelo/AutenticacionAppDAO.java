package modelo;

import java.util.HashMap;
import modelo.pojo.Cliente;
import modelo.pojo.RespuestaLoginApp;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AutenticacionAppDAO {
    public static RespuestaLoginApp verificarSesionApp(String email, String password) {
        RespuestaLoginApp respuesta = new RespuestaLoginApp();
        respuesta.setError(true);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {

            if (conexionDB != null) {
                try {
                    HashMap<String, String> parametros = new HashMap<>();
                    parametros.put("email", email);
                    parametros.put("password", password);
                    Cliente cliente = conexionDB.selectOne("autenticacionApp.loginApp", parametros);

                    if (cliente != null) {
                        respuesta.setError(false);
                        respuesta.setContenido("Bienvenido(a) " + cliente.getNombre() + " a la App Móvil para Clientes.");
                        respuesta.setClienteSesion(cliente);
                    } else {
                        respuesta.setContenido("Correo electronico y/o contraseña incorrectos, porfavor de verificar los datos.");
                    }
                } catch (Exception e) {
                    respuesta.setContenido("Error: " + e.getMessage());
                } finally {
                    conexionDB.close();
                }
            } else {
                respuesta.setContenido("Error: Por el momento no hay conexion a la base de datos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}