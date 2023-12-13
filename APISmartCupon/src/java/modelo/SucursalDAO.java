/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author andre
 */
public class SucursalDAO {
    public List<Sucursal> obtenerSucursalPorIdEmpresa(Integer idEmpresa) {
        List<Sucursal> sucursal = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                sucursal = conexionDB.selectList("sucursal.obtenerPorIdEmpresa", idEmpresa);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return sucursal;
    }

    public Mensaje registrar(Sucursal sucursal){
         
        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSession();
        
        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.insert("sucursal.registrar", sucursal);
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("OK, " + numeroFilasAfectadas + "," + sucursal.getNombreSucursal());
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo registrar la información de la Sucursal.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("Error: " + e.getMessage());
            } finally {
                conexionDB.close();
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no hay conexión con la base de datos.");
        }

        return msj;
    }
    
    private HashMap<String, Object> toparam(Sucursal sucursal) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idSucursal", sucursal.getIdSucursal());
        parametros.put("idEmpresa", sucursal.getIdEmpresa());
        parametros.put("nombreSucursal", sucursal.getNombreSucursal());
        parametros.put("direccion", sucursal.getDireccion());
        parametros.put("codigoPostal", sucursal.getCodigoPostal());
        parametros.put("colonia", sucursal.getColonia());
        parametros.put("ciudad", sucursal.getCiudad());
        parametros.put("telefono", sucursal.getTelefono());
        parametros.put("latitud", sucursal.getLatitud());
        parametros.put("longitud", sucursal.getLongitud());
        parametros.put("nombreEncargado", sucursal.getNombreEncargado());

        return parametros;
    }
    
    public Mensaje editar(Sucursal sucursal) {

        Mensaje response = new Mensaje();
        HashMap<String, Object> parametros = toparam(sucursal);
        SqlSession conn = MyBatisUtil.getSession();
        response.setMensaje("OK");

        if (conn != null) {
            try {
                if (sucursal.getIdSucursal()== 0) {
                    response.setMensaje("ID necesario para actualizar");
                }
                Sucursal found = conn.selectOne("sucursal.obtenerPorId", sucursal.getIdSucursal());
                if (found != null) {
                    int count = conn.update("sucursal.editar", parametros);
                    conn.commit();
                    if (count > 0) {
                        response.setMensaje("La sucursal ha sido actualizada con éxito.");
                    } else {
                        response.setMensaje("Lo sentimos, no se pudo actualizar la información de la Sucursal.");
                    }
                }
            } catch (Exception e) {
                response.setError(true);
                response.setMensaje("Error: " + e.getMessage());
            } finally {
                conn.close();
            }
        } else {
            response.setMensaje("Por el momento no hay conexión con la base de datos.");
        }

        return response;
    }

    public Mensaje eliminar(Integer idSucursal) {

        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.delete("sucursal.eliminar", (idSucursal));
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Información de la Sucursal eliminada con éxito");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo eliminar la información de la Sucursal.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("Error: " + e.getMessage());
            } finally {
                conexionDB.close();
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no hay conexión con la base de datos.");
        }

        return msj;
    }
}
