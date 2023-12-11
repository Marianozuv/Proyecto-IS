/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author mateo
 */
public class EmpresaDAO {
    
    public static Mensaje registrarEmpresa(Empresa empresa) {
        
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                
                int filasAfectasas = sqlSession.insert("empresas.registrarEmpresa", empresa);
                sqlSession.commit();
                
                if(filasAfectasas > 0){
                    mensaje.setError(false);
                    mensaje.setMensaje("La empresa se ha registrado");
                }else{
                    mensaje.setMensaje("La empresa no se ha podido registrar");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setMensaje(e.getMessage());
            }finally{
                sqlSession.close();
            }
            
        }else{
            mensaje.setMensaje("Por el momento no hay conexión a la base de datos");
        }
        
        
        return mensaje;
    }
    
    public static Mensaje editarEmpresa(Empresa empresa) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                
                int filasAfectadas = sqlSession.update("empresas.editarEmpresa", empresa);
                sqlSession.commit();
                
                if(filasAfectadas > 0){
                    mensaje.setError(false);
                    mensaje.setMensaje("La empresa ha sido editada");
                }else{
                    mensaje.setMensaje("No se pudo editar la empresa");
                }
                
                
                
            } catch (Exception e) {
            }
            
        }else{
            mensaje.setMensaje("No hay conexión a la base de datos");
        }
        
        return mensaje;
        
    }
    
    public static Empresa obtenerEmpresa(String[] args) {
        return null;
    }
    
    public static List<Empresa> obtenerEmpresas() {
        List<Empresa> empresas = null;
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            try {
                
                empresas = sqlSession.selectList("empresas.obtenerEmpresas");
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                sqlSession.close();
            }
        }else{
            System.out.println("No hay conexión a la base de datos");
        }
        
        
        
        return  empresas;
    }
    
    public static Mensaje eliminarEmpresa(int idEmpresa) {
        
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                
                int filasAfectadas = sqlSession.delete("empresas.eliminarEmpresaById", idEmpresa);
                
                if(filasAfectadas > 0){
                    sqlSession.commit();
                    mensaje.setError(false);
                    mensaje.setMensaje("La empresa ha sido eliminada");
                    
                }else{
                   mensaje.setMensaje("No se pudo eliminar la empresa");
                }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                sqlSession.close();
            }
            
        }else{
            mensaje.setMensaje("Error en la conexión de la base de datos");
        }
        
        return mensaje;
    }
}
