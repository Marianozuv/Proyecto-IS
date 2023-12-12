/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
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
import modelo.ClienteDAO;
import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;

/**
 *
 * @author mateo
 * 
 */

@Path("clientes")
public class ClienteWS {
    
    @Context
    private UriInfo context;
    
    
    @POST
    @Path("registrarCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje registrarCliente(String json) {
        
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(json, Cliente.class);
        
        if(cliente != null){
            return ClienteDAO.registrarCliente(cliente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        
    }
    
    
    @GET
    @Path("obtenerCliente/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Cliente obtenerClienteById(@PathParam("idCliente")Integer idCliente) {
        
        if(idCliente > 0 && idCliente != null){
            return ClienteDAO.obtenerCliente(idCliente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
    
    
    @PUT
    @Path("editarCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje editarCliente(String json) {
        
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(json, Cliente.class);
        
        if (cliente != null) {
            return ClienteDAO.editarCliente(cliente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
    
    @DELETE
    @Path("eliminarCliente/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje eliminarClienteById(@PathParam("idCliente")Integer idCliente) {
        
        if(idCliente > 0 && idCliente != null){
            return ClienteDAO.eliminarCliente(idCliente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
    
}
