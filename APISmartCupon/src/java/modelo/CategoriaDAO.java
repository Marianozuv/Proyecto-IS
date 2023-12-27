/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import modelo.pojo.Categoria;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author mateo
 */
public class CategoriaDAO {
    
    public static List<Categoria> obtenerCateogrias() {
        List<Categoria> categorias = null;
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if (sqlSession != null) {
            try {
                
                categorias = sqlSession.selectList("cateogrias.obtenerCategorias");
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                sqlSession.close();
            }
        }else{
            System.out.println("No hay conexión a la base de datos");
        }
        
        
        return categorias;
    }
    
}
