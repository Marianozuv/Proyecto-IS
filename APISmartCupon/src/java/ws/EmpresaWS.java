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

/**
 *
 * @author mateo
 */
@Path("empresas")
public class EmpresaWS {

    @Context
    private UriInfo context;

    public EmpresaWS() {
    }

    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje registrarEmpresa(String json) {

        Gson gson = new Gson();
        Empresa empresa = gson.fromJson(json, Empresa.class);

        if (empresa != null) {
            return EmpresaDAO.registrarEmpresa(empresa);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

    }
    
    @PUT
    @Path("editarEmpresa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEmpresa(String json) {
        
        Gson gson = new Gson();
        Empresa empresa = gson.fromJson(json, Empresa.class);
        
        if(empresa != null){
            
            return EmpresaDAO.editarEmpresa(empresa);
            
        }else{
            throw new WebApplicationException(Response.Status.BAD_GATEWAY);
        }
        
    }
    
    @DELETE
    @Path("eliminarEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEmpresa(@PathParam("idEmpresa") Integer idEmpresa) {
        
        
        if(idEmpresa > 0 && idEmpresa != null){
            
            return EmpresaDAO.eliminarEmpresa(idEmpresa);
            
        }else{
            throw  new WebApplicationException(Response.Status.BAD_REQUEST);
        }
       
        
    }
    
    @GET
    @Path("obtenerEmpresaById/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje obtenerEmpresaById(@PathParam("idEmpresa") Integer idEmpresa) {

        return null;
    }
    
    
    @GET
    @Path("obtenerEmpresas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> obtenerEmpresas() {
       
        List<Empresa> empresas = null;
        
        empresas = EmpresaDAO.obtenerEmpresas();
        
       return  empresas;
        
    }

}
