package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import modelo.pojo.SucursalPromocion;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class SucursalDAO {

    public List<Sucursal> obtenerLista() {
        List<Sucursal> usuario = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                usuario = conexionDB.selectList("sucursal.obtenerLista");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return usuario;
    }

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
    
    public List<Sucursal> buscarSucursal(String nombreSucursal, String direccion) {
        List<Sucursal> sucursales = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                Map<String, Object> parametros = new HashMap<>();

                if (nombreSucursal != null && !nombreSucursal.isEmpty()) {
                    parametros.put("nombreSucursal", nombreSucursal);
                }
                if (direccion != null && !direccion.isEmpty()) {
                    parametros.put("direccion", direccion);
                }
                sucursales = conexionDB.selectList("sucursal.buscar", parametros);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return sucursales;
    }

    public Mensaje registrar(Sucursal sucursal) {

        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.insert("sucursal.registrar", sucursal);
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Sucursal registrada con éxito!!");
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
                if (sucursal.getIdSucursal() == 0) {
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
        List<SucursalPromocion> sucursalPromociones = null;

        if (conexionDB != null) {
            try {

                sucursalPromociones = conexionDB.selectList("sucursal.obtenerPromocionBySucursal", idSucursal);

                if (sucursalPromociones != null && !sucursalPromociones.isEmpty()) {
                    msj.setMensaje("La sucursal no se puede eliminar, ya que tiene promociones asociadas");
                } else {
                    int numeroFilasAfectadas = conexionDB.delete("sucursal.eliminar", (idSucursal));
                    conexionDB.commit();
                    if (numeroFilasAfectadas > 0) {
                        msj.setError(false);
                        msj.setMensaje("Información de la Sucursal eliminada con éxito");
                    } else {
                        msj.setError(true);
                        msj.setMensaje("Lo sentimos, no se pudo eliminar la información de la Sucursal.");
                    }
                }

            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("No se puede eliminar, la promocion tiene sucursales asignadas");
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
