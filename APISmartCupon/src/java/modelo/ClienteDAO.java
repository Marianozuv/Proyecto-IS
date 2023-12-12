/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author mateo
 */
public class ClienteDAO {
    
    
    public static Mensaje registrarCliente(Cliente cliente) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                
                int filasAfectadas = sqlSession.insert("clientes.registrarCliente", cliente);
                sqlSession.commit();
                
                if(filasAfectadas > 0){
                    mensaje.setError(false);
                    mensaje.setMensaje("El cliente "+ cliente.getNombre()+" ha sido registrado");
                }else{
                    mensaje.setMensaje("No se ha podido registrar el cliente");
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
    
    public static Cliente obtenerCliente(int idCliente) {
        Cliente cliente = null;
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        try {
            
            cliente = sqlSession.selectOne("clientes.obtenerClientePorId", idCliente);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
     
        return cliente;
    }
    
    public static Mensaje editarCliente(Cliente cliente) {
        
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                int filasAfectadas = sqlSession.delete("clientes.editarCliente", cliente);
                sqlSession.commit();
                if (filasAfectadas >0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("El cliente ha sido editado");
                }else{
                    mensaje.setMensaje("No se ha podido editar el cliente");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                sqlSession.close();
            }
            
        }else{
            mensaje.setMensaje("No hay conexióna la base de datos");
        }
        
        return mensaje;
    }
    
    public static Mensaje eliminarCliente(int idCliente) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if (sqlSession != null) {
            
            try {
                
                int filasAfectadas = sqlSession.delete("clientes.eliminarClienteById", idCliente);
                
                
                if (filasAfectadas > 0) {
                    sqlSession.commit();
                    mensaje.setError(false);
                    mensaje.setMensaje("El cliente ha sido eliminado");
                }else{
                    mensaje.setMensaje("El cliente no se ha podido eliminar");
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
