
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
import modelo.PromocionDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import modelo.pojo.SucursalPromocion;


@Path("promocion")
public class PromocionWS {

    @Context
    private UriInfo context;

    @POST
    @Path("registrarPromocion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje registrarPromocion(String json) {
        
        Gson gson = new Gson();
        Promocion promocion = gson.fromJson(json, Promocion.class);
        
        if(promocion != null){
            return PromocionDAO.registrarUsuario(promocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }     
        
    }
    
    @POST
    @Path("asignarSucursal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje asignarSucursalPromocion(String json){
        
        Gson gson = new Gson();
        SucursalPromocion sucursalPromocion = gson.fromJson(json, SucursalPromocion.class);
        
        if (sucursalPromocion != null) {
            return PromocionDAO.asignarSucursal(sucursalPromocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
       
    }
    
    @GET
    @Path("listaPromocionPorCategoriaId/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public static List<Promocion> obtenerListaPromocion(@PathParam("idCategoria") Integer idCategoria){
        
        if(idCategoria > 0 && idCategoria != null){
            return PromocionDAO.obtenerPromocionByCategoria(idCategoria);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
    
    
    @GET
    @Path("obtenerPromocion/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Promocion obtenerPromocionById(@PathParam("idPromocion") Integer idPromocion) {
        
        if(idPromocion > 0 && idPromocion != null){
            return PromocionDAO.obtenerPromo(idPromocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
    
    @PUT
    @Path("editarPromocion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje editarPromocionById(String json) {
        
        Gson gson = new Gson();
        Promocion promocion = gson.fromJson(json, Promocion.class);
        
        if (promocion != null) {
            return PromocionDAO.editarPromocion(promocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
    
    
    @DELETE
    @Path("eliminarPromocion/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje eliminarPromocionById(@PathParam("idPromocion") Integer idPromocion) {
       
        if (idPromocion > 0 && idPromocion != null) {
            return PromocionDAO.eliminarPromocion(idPromocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
}
