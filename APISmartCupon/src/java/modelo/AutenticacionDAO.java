package modelo;

import java.util.HashMap;
import modelo.pojo.RespuestaLoginEscritorio;
import modelo.pojo.Rol;
import modelo.pojo.Usuario;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


public class AutenticacionDAO {
    
    public static RespuestaLoginEscritorio verificarSesionEscritorio(String username, String password) {
        RespuestaLoginEscritorio respuesta = new RespuestaLoginEscritorio();
        respuesta.setError(true);
        
        SqlSession dbSqlSession = MyBatisUtil.getSession();
        
        if(dbSqlSession != null){
             
            try {
                HashMap<String, String> parametros = new HashMap<>();
                parametros.put("username", username);
                parametros.put("password", password);
                
                Usuario usuario = dbSqlSession.selectOne("autenticacion.loginEscritorio", parametros);
                if(usuario != null && usuario.getIdRol() != null){
                    if(usuario.getIdRol() == 1){
                        respuesta.setError(false);
                        respuesta.setContenido("Bienvenido " + usuario.getNombre() + "al sistema de Administración General");
                        respuesta.setUsuarioSesion(usuario);
                    }
                    
                    if(usuario.getIdRol() == 2){
                        respuesta.setError(false);
                        respuesta.setContenido("Bienvenido " + usuario.getNombre() + "al sistema de Administración Comercial");
                        respuesta.setUsuarioSesion(usuario);
                    }
                }else{
                    respuesta.setContenido("Email y/o contraseña incorrectos");
                }
                
                
            } catch (Exception e) {
                respuesta.setContenido("Error: "+ e.getMessage());
            }finally{
                dbSqlSession.close();
            }
        }else{
            respuesta.setContenido("Por el momento no hay conexión");
        }
        
        
        return  respuesta;
    }
}
