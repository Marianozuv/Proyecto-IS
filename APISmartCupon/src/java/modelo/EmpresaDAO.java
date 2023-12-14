/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
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

    public List<Empresa> obtenerListaEmpresas() {
        List<Empresa> empresa = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                empresa = conexionDB.selectList("empresa.obtenerEmpresas");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return empresa;
    }
    
    public List<Empresa> obtenerEmpresaById(Integer idEmpresa) {
        List<Empresa> empresa = null;
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                empresa = conexionDB.selectList("empresa.obtenerEmpresaById", idEmpresa);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return empresa;
    }
    
    public Mensaje registrar(Empresa empresa){
         
        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSession();
        
        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.insert("empresa.registrar", empresa);
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("OK, " + numeroFilasAfectadas + "," + empresa.getNombre());
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo registrar la información del Paciente.");
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
    
    private HashMap<String, Object> toparam(Empresa empresa) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idEmpresa", empresa.getIdEmpresa());
        parametros.put("nombre", empresa.getNombre());
        parametros.put("nombreComercial", empresa.getNombreComercial());
        //parametros.put("logoEmpresa", empresa.getLogoEmpresa());
        parametros.put("nombreRepresentanteLegal", empresa.getNombreRepresentanteLegal());
        parametros.put("telefono", empresa.getTelefono());
        parametros.put("email", empresa.getEmail());
        parametros.put("direccion", empresa.getDireccion());
        parametros.put("codigoPostal", empresa.getCodigoPostal());
        parametros.put("ciudad", empresa.getCiudad());
        parametros.put("paginaWeb", empresa.getPaginaWeb());
        parametros.put("RFC", empresa.getRFC());

        return parametros;
    }

    public Mensaje editar(Empresa empresa) {

        Mensaje response = new Mensaje();
        HashMap<String, Object> parametros = toparam(empresa);
        SqlSession conn = MyBatisUtil.getSession();
        response.setMensaje("OK");

        if (conn != null) {
            try {
                if (empresa.getIdEmpresa()== 0) {
                    response.setMensaje("ID necesario para actualizar");
                }
                Empresa found = conn.selectOne("paciente.obtenerPacientePorId", empresa.getIdEmpresa());
                if (found != null) {
                    int count = conn.update("paciente.editarPaciente", parametros);
                    conn.commit();
                    if (count > 0) {
                        response.setMensaje("Paciente actualizado con éxito.");
                    } else {
                        response.setMensaje("Lo sentimos, no se pudo actualizar la información del Paciente.");
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

    public Mensaje eliminar(Integer idEmpresa) {

        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.delete("paciente.eliminarPaciente", (idEmpresa));
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Información de la Empresa eliminada con éxito");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo eliminar la información de la Empresa.");
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
