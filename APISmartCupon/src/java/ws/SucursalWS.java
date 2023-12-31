package ws;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import modelo.SucursalDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import validator.SucursalValidator;

@Path("sucursal")
public class SucursalWS {
    
    @Context
    private UriInfo context;
    
    @Path("lista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> obtenerLista() {
        SucursalDAO dao = new SucursalDAO();
        return dao.obtenerLista();
    }

    @GET
    @Path("obtenerPorEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> obtenerSucursalPorIdEmpresa(@PathParam("idEmpresa") Integer idEmpresa){
      SucursalDAO dao = new SucursalDAO();
      return dao.obtenerSucursalPorIdEmpresa(idEmpresa);
    }
    
    @GET
    @Path("buscar")
    public List<Sucursal> buscarSucursal(
            @QueryParam("nombreSucursal") String nombreSucursal,
            @QueryParam("direccion") String direccion) {
        SucursalDAO dao = new SucursalDAO();
        return dao.buscarSucursal(nombreSucursal, direccion);
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarSucursal(Sucursal sucursal){
        Mensaje mensaje = SucursalValidator.isValid(sucursal);
        if(mensaje.isError()) {
            return mensaje;
        }
        SucursalDAO dao = new SucursalDAO();
        return dao.registrar(sucursal);
    }
    
    @PUT
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarSucursal(Sucursal sucursal){
        Mensaje mensaje = SucursalValidator.isValid(sucursal);
        if(mensaje.isError()) {
            return mensaje;
        }
        SucursalDAO dao = new SucursalDAO();
        return dao.editar(sucursal);
    }
    
    @DELETE
    @Path("eliminar/{idSucursal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarSucursal(@PathParam("idSucursal") Integer idSucursal){
        SucursalDAO dao = new SucursalDAO();
        return dao.eliminar(idSucursal);
    }
}
