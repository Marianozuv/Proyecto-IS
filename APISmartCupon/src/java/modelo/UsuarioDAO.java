package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Usuario;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


public class UsuarioDAO {
    
    public List<Usuario> obtenerListaUsuarios() {
        List<Usuario> usuario = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                usuario = conexionDB.selectList("usuarios.obtenerUsuarios");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return usuario;
    }
    
    public List<Usuario> obtenerUsuarioPorIdEmpresa(Integer idEmpresa) {
        List<Usuario> usuario = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                usuario = conexionDB.selectList("usuarios.obtenerUsuarioByEmpresa", idEmpresa);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return usuario;
    }
    
    public Mensaje registrar(Usuario usuario){
         
        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSession();
        
        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.insert("usuarios.registrar", usuario);
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("OK, " + numeroFilasAfectadas + "," + usuario.getNombre());
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo registrar la información del Usuario.");
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
    
    private HashMap<String, Object> toparam(Usuario usuario) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idUsuario", usuario.getIdUsuario());
        parametros.put("idRol", usuario.getIdRol());
        parametros.put("idEmpresa", usuario.getIdEmpresa());
        parametros.put("nombre", usuario.getNombre());
        parametros.put("apellidoPaterno", usuario.getApellidoPaterno());
        parametros.put("apellidoMaterno", usuario.getApellidoMaterno());
        parametros.put("CURP", usuario.getCURP());
        parametros.put("email", usuario.getEmail());
        parametros.put("username", usuario.getUsername());
        parametros.put("password", usuario.getPassword());

        return parametros;
    }

    public Mensaje editar(Usuario usuario) {

        Mensaje response = new Mensaje();
        HashMap<String, Object> parametros = toparam(usuario);
        SqlSession conn = MyBatisUtil.getSession();
        response.setMensaje("OK");

        if (conn != null) {
            try {
                if (usuario.getIdUsuario()== 0) {
                    response.setMensaje("ID necesario para actualizar");
                }
                Usuario found = conn.selectOne("usuarios.obtenerUsuarioById", usuario.getIdUsuario());
                if (found != null) {
                    int count = conn.update("usuarios.editar", parametros);
                    conn.commit();
                    if (count > 0) {
                        response.setMensaje("Usuario actualizado con éxito.");
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
    }
    
    public Mensaje eliminar(Integer idUsuario) {

        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.delete("usuarios.eliminarUsuarioById", (idUsuario));
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Información del Usuario eliminada con éxito");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo eliminar la información del Usuario.");
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
