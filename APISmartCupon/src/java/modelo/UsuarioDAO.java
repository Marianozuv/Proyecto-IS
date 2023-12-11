/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.pojo.Mensaje;
import modelo.pojo.Usuario;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author mateo
 */
public class UsuarioDAO {
    
    
    public static Mensaje registrarUsuario(Usuario usuario) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                
                int filasAfectadas = sqlSession.insert("usuarios.registrarUsuario", usuario);
                sqlSession.commit();
                
                if(filasAfectadas > 0){
                    mensaje.setError(false);
                    mensaje.setMensaje("El usuario ha sido registrado");
                }else{
                    mensaje.setMensaje("No se ha podido registrar el usuario");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                sqlSession.close();
            }
            
            
        }else{
            mensaje.setMensaje("No hay conexión a la base de datos");
        }
        
        
        return mensaje;
    }
    
    public static Mensaje editarUsuario(Usuario usuario) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                
                int filasAfectadas = sqlSession.update("usuarios.editarUsuario", usuario);
                sqlSession.commit();
                if (filasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("El usuario ha diso editado");
                }else{
                    mensaje.setMensaje("El usuario no se ha podido editar");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                sqlSession.close();
            }
            
            
        }else{
            mensaje.setMensaje("No hay conexión a la base de datos");
        }
        
        
        return mensaje;
    }
    
    
    public static Mensaje eliminarUsuario(int idUsuario) {
        
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                
                int filasAfectadas = sqlSession.delete("usuarios.eliminarUsuarioById", idUsuario);
                sqlSession.commit();
                
                if (filasAfectadas > 0 ) {
                    mensaje.setError(false);
                    mensaje.setMensaje("El usuario ha sido eliminado");
                }else{
                    mensaje.setMensaje("No se pudo eliminar al usuario");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                sqlSession.close();
            }
            
        }else{
            mensaje.setMensaje("No hay conexión a la base de datos");
        }
        
        return mensaje;
        
    }
    
    public static Usuario obtenerUsuarioById(int idUsuario) {
        Usuario usuario = null;
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        try {
            
            usuario = sqlSession.selectOne("usuarios.obtenerUsuarioById", idUsuario);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usuario;
    }
}
