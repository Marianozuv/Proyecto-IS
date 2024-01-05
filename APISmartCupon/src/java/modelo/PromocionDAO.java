package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import modelo.pojo.PromocionSucursal;
import modelo.pojo.Sucursal;
import modelo.pojo.SucursalPromocion;
import modelo.pojo.TipoPromocion;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class PromocionDAO {

    public static Mensaje registrarPromocion(Promocion promocion) {

        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);

        SqlSession sqlSession = MyBatisUtil.getSession();

        if (sqlSession != null) {

            try {

                int filasAfectadas = sqlSession.delete("promociones.registrarPromocion", promocion);

                if (filasAfectadas > 0) {
                    sqlSession.commit();
                    mensaje.setError(false);
                    mensaje.setMensaje("La promocion se ha registrado");
                } else {
                    mensaje.setMensaje("No se pudo registrar la promocion");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sqlSession.close();
            }

        } else {
            mensaje.setMensaje("No hay conexión a la base de datos");
        }

        return mensaje;
    }

    public static List<TipoPromocion> obtenerTiposPromociones() {
        SqlSession sqlSession = MyBatisUtil.getSession();
        List<TipoPromocion> tipoPromociones = null;

        try {
            tipoPromociones = sqlSession.selectList("promociones.obtenerTiposPromociones");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return tipoPromociones;
    }

    public static List<Promocion> obtenerPromociones() {
        SqlSession sqlSession = MyBatisUtil.getSession();
        List<Promocion> promociones = null;

        try {
            promociones = sqlSession.selectList("promociones.listaPromociones");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return promociones;
    }

    public static List<Promocion> obtenerPromocionByCategoria(int idCategoria) {

        SqlSession sqlSession = MyBatisUtil.getSession();
        List<Promocion> promociones = null;

        try {
            promociones = sqlSession.selectList("promociones.obtenerPromocionByIdCategoria", idCategoria);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return promociones;
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
    
    public static List<Promocion> obtenerPromocionByEstatus(boolean estatus) {

        SqlSession sqlSession = MyBatisUtil.getSession();
        List<Promocion> promociones = null;

        try {
            promociones = sqlSession.selectList("promociones.obtenerPromocionByEstatus", estatus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return promociones;
    }

    public List<Promocion> buscarPromocion(String fechaInicioPromocion, String fechaTerminoPromocion, String nombrePromocion) {
        List<Promocion> promociones = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                Map<String, Object> parametros = new HashMap<>();

                if (fechaInicioPromocion != null && !fechaInicioPromocion.isEmpty()) {
                    parametros.put("fechaInicioPromocion", fechaInicioPromocion);
                }
                if (fechaTerminoPromocion != null && !fechaTerminoPromocion.isEmpty()) {
                    parametros.put("fechaTerminoPromocion", fechaTerminoPromocion);
                }
                if (nombrePromocion != null && !nombrePromocion.isEmpty()) {
                    parametros.put("nombrePromocion", nombrePromocion);
                }

                promociones = conexionDB.selectList("promociones.buscar", parametros);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return promociones;
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
                } else {
                    mensaje.setMensaje("No se pudo editar la promocion");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sqlSession.close();
            }

        } else {
            mensaje.setMensaje("No hay conexión a la base de datos");
        }

        return mensaje;
    }
    
    /*private HashMap<String, Object> toparam(Promocion promocion) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idPromocion", promocion.getIdPromocion());
        parametros.put("idEmpresa", promocion.getIdEmpresa());
        parametros.put("nombrePromocion", promocion.getNombrePromocion());
        parametros.put("descripcion", promocion.getDescripcion());
        parametros.put("fechaInicioPromocion", promocion.getFechaInicioPromocion());
        parametros.put("fechaTerminoPromocion", promocion.getFechaTerminoPromocion());
        parametros.put("restricciones", promocion.getRestricciones());
        parametros.put("idTipoPromocion", promocion.getIdTipoPromocion());
        parametros.put("porcentaje_Costo", promocion.getPorcentaje_Costo());
        parametros.put("idCategoria", promocion.getIdCategoria());
        parametros.put("cuponesMaximos", promocion.getCuponesMaximos());
        parametros.put("codigoPromocion", promocion.getCodigoPromocion());

        return parametros;
    }

    public Mensaje editar(Promocion promocion) {

        Mensaje response = new Mensaje();
        HashMap<String, Object> parametros = toparam(promocion);
        SqlSession conn = MyBatisUtil.getSession();
        response.setMensaje("OK");

        if (conn != null) {
            try {
                if (promocion.getIdPromocion()== 0) {
                    response.setMensaje("ID necesario para actualizar");
                }
                Promocion found = conn.selectOne("promociones.obtenerPromocion", promocion.getIdPromocion());
                if (found != null) {
                    int count = conn.update("promociones.editarPromocion", parametros);
                    conn.commit();
                    if (count > 0) {
                        response.setMensaje("Promocion actualizada con éxito.");
                    } else {
                        response.setMensaje("Lo sentimos, no se pudo actualizar la información del Usuario.");
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
    }*/

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
                } else {
                    mensaje.setMensaje("La promocion no se ha podido eliminar");
                }

            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setMensaje("No se puede eliminar, ya que esta asociada a una sucursal");
            } finally {
                sqlSession.close();
            }

        } else {
            mensaje.setMensaje("No hay conexión a la base de datos");
        }

        return mensaje;
    }

    public static List<Sucursal> obtenerSucursalesAsociadas(int idPromocion) {

        List<Sucursal> sucursales = null;

        SqlSession sqlSession = mybatis.MyBatisUtil.getSession();

        try {

            sucursales = sqlSession.selectList("promociones.sucursalesAsociadas", idPromocion);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
        }

        return sucursales;
    }

    public static Mensaje asignarSucursal(PromocionSucursal promocionSucursal) {

        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);

        SqlSession sqlSession = MyBatisUtil.getSession();

        if (sqlSession != null) {

            try {

                int filasAfectadas = sqlSession.insert("promociones.asignarPromocionSucursal", promocionSucursal);

                if (filasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Se ha asignado la sucursal correctamente");
                    sqlSession.commit();
                } else {
                    mensaje.setMensaje("No se pudo asignar la sucursal");
                }

            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setMensaje("La sucursal seleccionada ya esta asignada");
            } finally {
                sqlSession.close();
            }

        } else {
            mensaje.setMensaje("No hay conexión a la base de datos");
        }

        return mensaje;
    }

    public static Mensaje subirImagen(int idPromocion, byte[] foto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);

        SqlSession sqlSession = MyBatisUtil.getSession();

        if (sqlSession != null) {

            try {
                Promocion promocionImagen = new Promocion();
                promocionImagen.setIdPromocion(idPromocion);
                promocionImagen.setImagenPromocion(foto);
                int filasAfectadas = sqlSession.update("promociones.subirImagenPromocion", promocionImagen);
                sqlSession.commit();

                if (filasAfectadas > 0) {

                    mensaje.setError(false);
                    mensaje.setMensaje("La imagen de la promocion ha sido guardada");

                } else {

                    mensaje.setMensaje("Hubo un error al intentar guardar la imagen de la promocion");
                }
            } catch (Exception e) {
            }

        } else {

            mensaje.setMensaje("Lo sentimos, no hay conexión para subir la imagen");

        }

        return mensaje;
    }

    public static Promocion obtenerImagenPromocion(int idPromocion) {

        Promocion promocion = new Promocion();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                promocion = conexionBD.selectOne("promociones.obtenerImagenPromocion", idPromocion);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }

        return promocion;
    }

    public static Mensaje canjearCupon(Promocion promocion) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);

        SqlSession sqlSession = MyBatisUtil.getSession();

        if (sqlSession != null) {
            try {
                // Obtener la promoción actual antes de intentar el canje
                String codigoPromocion = promocion.getCodigoPromocion();
                Promocion promocionActual = sqlSession.selectOne("promociones.obtenerPromocionByCodigo", codigoPromocion);

                if (promocionActual != null) {
                    // Verificar si hay cupones disponibles o si el estatus permite el canje
                    if (promocionActual.getCuponesMaximos() > 0 && promocionActual.isEstatus() == true) {

                        int filasAfectadas = sqlSession.update("promociones.canjearCupon", promocion);

                        if (filasAfectadas > 0) {
                            sqlSession.commit();
                            mensaje.setError(false);
                            mensaje.setMensaje("Se ha canjeado el Cupon");
                        } else {
                            mensaje.setMensaje("No se pudo canjear el Cupon");
                        }
                    } else {
                        mensaje.setMensaje("Ya no hay cupones disponibles o la promoción está inactiva");
                    }
                } else {
                    mensaje.setMensaje("No se encontró la promoción");
                }

            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setMensaje(e.getMessage());
            } finally {
                sqlSession.close();
            }

        } else {
            mensaje.setMensaje("No hay conexión a la base de datos");
        }

        return mensaje;
    }

    public static List<Promocion> obtenerPromocionByEstatus(boolean estatus) {

        SqlSession sqlSession = MyBatisUtil.getSession();
        List<Promocion> promociones = null;

        try {
            promociones = sqlSession.selectList("promociones.obtenerPromocionByEstatus", estatus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return promociones;
    }

    public static Mensaje desvincularSucursal(PromocionSucursal promocionSucursal) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);

        SqlSession sqlSession = MyBatisUtil.getSession();

        if (sqlSession != null) {

            try {

                int filasAfectadas = sqlSession.delete("promociones.desvincularSucursal", promocionSucursal);

                if (filasAfectadas > 0) {
                    sqlSession.commit();
                    mensaje.setError(false);
                    mensaje.setMensaje("La sucursal se ha desvinculado");
                } else {
                    mensaje.setMensaje("La sucursal no se ha podido desvincular");
                }

            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setMensaje("No se puede eliminar");
            } finally {
                sqlSession.close();
            }

        } else {
            mensaje.setMensaje("No hay conexión a la base de datos");
        }

        return mensaje;
    }

}
