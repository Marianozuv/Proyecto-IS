/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.UsuarioDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Usuario;
import validator.UsuarioValidator;

/**
 *
 * @author mateo
 */

@Path("usuario")
public class UsuarioWS {
    
    @Context
    private UriInfo context;
    
    @Path("lista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> obtenerLista() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.obtenerListaUsuarios();
    }
    
    @GET
    @Path("obtenerUsuarioPorIdEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> obtenerUsuarioPorIdEmpresa(@PathParam("idEmpresa") Integer idEmpresa){
      UsuarioDAO dao = new UsuarioDAO();
      return dao.obtenerUsuarioPorIdEmpresa(idEmpresa);
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregar(Usuario usuario){
        Mensaje mensaje = UsuarioValidator.isValid(usuario);
        if(mensaje.isError()) {
            return mensaje;
        }
        UsuarioDAO dao = new UsuarioDAO();
        return dao.registrar(usuario);
    }
    
    @PUT
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editar(Usuario usuario){
        Mensaje mensaje = UsuarioValidator.isValid(usuario);
        if(mensaje.isError()) {
            return mensaje;
        }
        UsuarioDAO dao = new UsuarioDAO();
        return dao.editar(usuario);
    }
    
    @DELETE
    @Path("eliminar/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaciente(@PathParam("idUsuario") Integer idUsuario){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.eliminar(idUsuario);
    }
}
