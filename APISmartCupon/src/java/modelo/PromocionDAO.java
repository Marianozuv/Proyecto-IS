/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author mateo
 */
public class PromocionDAO {
    
    public static Mensaje registrarUsuario(Promocion promocion) {
        
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                
                int filasAfectadas = sqlSession.delete("promociones.registrarPromocion", promocion);
                
                
                if (filasAfectadas > 0) {
                    sqlSession.commit();
                    mensaje.setError(false);
                    mensaje.setMensaje("La promocion se ha registrado");
                }else{
                    mensaje.setMensaje("No se pudo registrar la promocion");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                sqlSession.close();
            }
            
        }else {
            mensaje.setMensaje("No hay conexión a la base de datos");
        }        
        
        
        return mensaje;
    }
    
    public static Promocion obtenerPromo(int idPromocion) {
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        Promocion promocion = null;
        
        try {
            promocion = sqlSession.selectOne("promociones.obtenerPromocion", idPromocion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return promocion;
    }
    
    public static Mensaje editarPromocion(Promocion promocion) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if (sqlSession != null) {
            
            try {
                
                int filasAfectadas = sqlSession.update("promociones.editarPromocion", promocion);
                
                if (filasAfectadas > 0) {
                    sqlSession.commit();
                    mensaje.setError(false);
                    mensaje.setMensaje("Se ha editado la promocion");
                }else{
                    mensaje.setMensaje("No se pudo editar la promocion");
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
    
    public static Mensaje eliminarPromocion(int idPromocion) {
     
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if (sqlSession != null) {
            
            try {
                
                int filasAfectadas = sqlSession.delete("promociones.eliminarPromocion", idPromocion);
                
                if (filasAfectadas > 0) {
                    sqlSession.commit();
                    mensaje.setError(false);
                    mensaje.setMensaje("La promocion se ha eliminado");
                }else{
                    mensaje.setMensaje("La promocion no se ha podido eliminar");
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
}
