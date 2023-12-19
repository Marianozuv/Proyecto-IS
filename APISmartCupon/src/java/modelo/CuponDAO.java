package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import modelo.pojo.Cupon;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class CuponDAO {
    public static Mensaje crearCupon(Cupon cupon) {
        
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if(sqlSession != null){
            
            try {
                
                int filasAfectadas = sqlSession.delete("cupones.crearCupon", cupon);
                
                
                if (filasAfectadas > 0) {
                    sqlSession.commit();
                    mensaje.setError(false);
                    mensaje.setMensaje(" Se han creado ${Promocion.getcuponesMaximos()} cupones");
                }else{
                    mensaje.setMensaje("No se pudieron crear los cupones colicitados");
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
    
    
     
    public static Cupon obtenerCupon(int idCupon) {
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        Cupon cupon = null;
        
        try {
            cupon = sqlSession.selectOne("cupones.obtenerCupones", idCupon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return cupon;
    }
    
    private HashMap<String, Object> toparam(Cupon cupon) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idCupon", cupon.getIdCupon());
        parametros.put("idCupon", cupon.getCodigoPromocion());
        parametros.put("idCupon", cupon.getFechaCanje());
        return parametros;
    }
    
    public List<Cupon> obtenerListaCupones() {
        List<Cupon> cupon = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                cupon = conexionDB.selectList("cupon.obtenerListaCupones");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return cupon;
    }
    
    
    public static Mensaje eliminarCupon(int idCupon) {
     
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession sqlSession = MyBatisUtil.getSession();
        
        if (sqlSession != null) {
            
            try {
                
                int filasAfectadas = sqlSession.delete("cupones.eliminarCupon", idCupon);
                
                if (filasAfectadas > 0) {
                    sqlSession.commit();
                    mensaje.setError(false);
                    mensaje.setMensaje("El cupon ha sido utilizado");
                }else{
                    mensaje.setMensaje("El cupon no ha sido utilizado");
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
