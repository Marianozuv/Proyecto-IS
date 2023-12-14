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
import modelo.EmpresaDAO;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import validator.EmpresaValidator;

/**
 *
 * @author mateo
 */
@Path("empresa")
public class EmpresaWS {

    @Context
    private UriInfo context;

    public EmpresaWS() {
    }

    @Path("lista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> obtenerListaEmpresas() {
        EmpresaDAO dao = new EmpresaDAO();
        return dao.obtenerListaEmpresas();
    }
    
    @GET
    @Path("obtenerEmpresaPorId/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> obtenerEmpresaPorId(@PathParam("idEmpresa") Integer idEmpresa){
      EmpresaDAO dao = new EmpresaDAO();
      return dao.obtenerEmpresaById(idEmpresa);
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrar(Empresa empresa){
        Mensaje mensaje = EmpresaValidator.isValid(empresa);
        if(mensaje.isError()) {
            return mensaje;
        }
        EmpresaDAO dao = new EmpresaDAO();
        return dao.registrar(empresa);
    }
    
    @PUT
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editar(Empresa empresa){
        Mensaje mensaje = EmpresaValidator.isValid(empresa);
        if(mensaje.isError()) {
            return mensaje;
        }
        EmpresaDAO dao = new EmpresaDAO();
        return dao.editar(empresa);
    }
    
    @DELETE
    @Path("eliminar/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminar(@PathParam("idEmpresa") Integer idEmpresa){
        EmpresaDAO dao = new EmpresaDAO();
        return dao.eliminar(idEmpresa);
    }
    
    @Path("registrarLogo/{idEmpresa}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarLogo(@PathParam("idEmpresa") Integer idEmpresa, byte[] logo){
        Mensaje msj = null;
        if (idEmpresa != null && idEmpresa > 0 && logo != null) {
            msj = EmpresaDAO.subirLogoEmpresa(idEmpresa, logo);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return msj;
    }
    
    @Path("obtenerLogo/{idEmpresa}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Empresa obtenerLogo(@PathParam("idEmpresa") Integer idEmpresa){
        Empresa empresa = null;
        
        if (idEmpresa != null && idEmpresa > 0 ) {
            empresa = EmpresaDAO.obtenerLogo(idEmpresa);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empresa;
    }
}
