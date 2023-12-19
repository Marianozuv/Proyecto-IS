package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


public class ClienteDAO {
    
    public List<Cliente> obtenerLista() {
        List<Cliente> cliente = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                cliente = conexionDB.selectList("cliente.obtenerLista");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return cliente;
    }
    
    public static Cliente obtenerCliente(int idCliente){
        Cliente cliente = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        
        if (conexionDB != null) {
            try {
                cliente = conexionDB.selectOne("cliente.obtenerPorId", idCliente);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionDB.close();
            }
        }
        return cliente;
    }

    public Mensaje registrar(Cliente cliente){
         
        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSession();
        
        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.insert("cliente.registrar", cliente);
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("OK, " + numeroFilasAfectadas + "," + cliente.getNombre());
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo registrar la información del Cliente.");
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
    
    private HashMap<String, Object> toparam(Cliente cliente) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idCliente", cliente.getIdCliente());
        parametros.put("nombre", cliente.getNombre());
        parametros.put("apellidoPaterno", cliente.getApellidoPaterno());
        parametros.put("apellidoMaterno", cliente.getApellidoMaterno());
        parametros.put("telefono", cliente.getTelefono());
        parametros.put("email", cliente.getEmail());
        parametros.put("direccion", cliente.getDireccion());
        parametros.put("fechaNacimiento", cliente.getFechaNacimiento());
        parametros.put("password", cliente.getPassword());

        return parametros;
    }

    public Mensaje editar(Cliente cliente) {

        Mensaje response = new Mensaje();
        HashMap<String, Object> parametros = toparam(cliente);
        SqlSession conn = MyBatisUtil.getSession();
        response.setMensaje("OK");

        if (conn != null) {
            try {
                if (cliente.getIdCliente()== 0) {
                    response.setMensaje("ID necesario para actualizar");
                }
                Cliente found = conn.selectOne("cliente.obtenerPorId", cliente.getIdCliente());
                if (found != null) {
                    int count = conn.update("cliente.editar", parametros);
                    conn.commit();
                    if (count > 0) {
                        response.setMensaje("Cliente actualizado con éxito.");
                    } else {
                        response.setMensaje("Lo sentimos, no se pudo actualizar la información del Cliente.");
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

    public Mensaje eliminar(Integer idCliente) {

        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.delete("cliente.eliminarClienteById", (idCliente));
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Información del Cliente eliminada con éxito");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo eliminar la información del Cliente.");
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
