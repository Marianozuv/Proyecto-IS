/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
    
}
