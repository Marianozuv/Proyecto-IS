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
import modelo.CuponDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Cupon;

/**
 *
 * @author mateo
 */
@Path("cupon")
public class CuponWS {

    @Context
    private UriInfo context;

    @POST
    @Path("crearCupon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje crearCupon(String json) {
        
        Gson gson = new Gson();
        Cupon cupon = gson.fromJson(json, Cupon.class);
        
        if(cupon != null){
            return CuponDAO.crearCupon(cupon);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }     
        
    }
    
    
    @GET
    @Path("obtenerCupon/{idCupon}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Cupon obtenerCuponById(@PathParam("idCupon") Integer idCupon) {
        
        if(idCupon > 0 && idCupon != null){
            return CuponDAO.obtenerCupon(idCupon);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
    
    @Path("lista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cupon> obtenerListaCupones() {
        CuponDAO dao = new CuponDAO();
        return dao.obtenerListaCupones();
    }
    
    @DELETE
    @Path("eliminarPromocion/{idCupon}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje eliminarCuponById(@PathParam("idCupon") Integer idCupon) {
       
        if (idCupon > 0 && idCupon != null) {
            return CuponDAO.eliminarCupon(idCupon);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
}
