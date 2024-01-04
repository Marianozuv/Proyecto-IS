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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.PromocionDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import modelo.pojo.PromocionSucursal;
import modelo.pojo.Sucursal;
import modelo.pojo.SucursalPromocion;
import modelo.pojo.TipoPromocion;
import validator.PromocionValidator;

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

        if (promocion != null) {
            return PromocionDAO.registrarPromocion(promocion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("obtenerSucursalesByPromocion/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> obtenerSucursales(@PathParam("idPromocion") Integer idPromocion) {

        if (idPromocion != null && idPromocion > 0) {
            return PromocionDAO.obtenerSucursalesAsociadas(idPromocion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("lista")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> obtenerPromociones() {

        return PromocionDAO.obtenerPromociones();

    }

    @GET
    @Path("tipoPromociones")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoPromocion> obtenerTipos() {

        return PromocionDAO.obtenerTiposPromociones();

    }
    
    @GET
    @Path("obtenerByEstatus/{estatus}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> obtenerPromocionByEstatus(@PathParam("estatus") boolean estatus) {

        if (estatus = true) {
            return PromocionDAO.obtenerPromocionByEstatus(estatus);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    @GET
    @Path("buscar")
    public List<Promocion> buscarPromocion(
            @QueryParam("fechaInicioPromocion") String fechaInicioPromocion,
            @QueryParam("fechaTerminoPromocion") String fechaTerminoPromocion,
            @QueryParam("nombrePromocion") String nombrePromocion) {
        PromocionDAO dao = new PromocionDAO();
        return dao.buscarPromocion(fechaInicioPromocion, fechaTerminoPromocion, nombrePromocion);
    }

    @POST
    @Path("asignarSucursal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje asignarSucursalPromocion(String json) {

        Gson gson = new Gson();
        PromocionSucursal promocionSucursal = gson.fromJson(json, PromocionSucursal.class);

        if (promocionSucursal != null) {
            return PromocionDAO.asignarSucursal(promocionSucursal);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

    }

    @GET
    @Path("listaPromocionPorCategoriaId/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public static List<Promocion> obtenerListaPromocion(@PathParam("idCategoria") Integer idCategoria) {

        if (idCategoria > 0 && idCategoria != null) {
            return PromocionDAO.obtenerPromocionByCategoria(idCategoria);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

    }

    @GET
    @Path("obtenerPromocion/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Promocion obtenerPromocionById(@PathParam("idPromocion") Integer idPromocion) {

        if (idPromocion > 0 && idPromocion != null) {
            return PromocionDAO.obtenerPromo(idPromocion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

    }

    /*@PUT
    @Path("editarPromocion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje editarPromocionById(String json) {

        Gson gson = new Gson();
        Promocion promocion = gson.fromJson(json, Promocion.class);

        if (promocion != null) {
            return PromocionDAO.editarPromocion(promocion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }*/
    
    @PUT
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editar(Promocion promocion){
        Mensaje mensaje = PromocionValidator.isValid(promocion);
        if(mensaje.isError()) {
            return mensaje;
        }
        PromocionDAO dao = new PromocionDAO();
        return dao.editar(promocion);
    }

    @DELETE
    @Path("eliminarPromocion/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje eliminarPromocionById(@PathParam("idPromocion") Integer idPromocion) {

        if (idPromocion > 0 && idPromocion != null) {
            return PromocionDAO.eliminarPromocion(idPromocion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

    }

    @Path("registrarImagen/{idPromocion}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarLogo(@PathParam("idPromocion") Integer idPromocion, byte[] logo) {
        Mensaje msj = null;
        if (idPromocion != null && idPromocion > 0 && logo != null) {
            msj = PromocionDAO.subirImagen(idPromocion, logo);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return msj;
    }

    @Path("obtenerImgen/{idPromocion}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Promocion obtenerLogo(@PathParam("idPromocion") Integer idPromocion) {
        Promocion promocion = null;

        if (idPromocion != null && idPromocion > 0) {
            promocion = PromocionDAO.obtenerImagenPromocion(idPromocion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return promocion;
    }

    @PUT
    @Path("canjearCupon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Mensaje canjearCupon(String json) {
        Gson gson = new Gson();
        Promocion promocion = gson.fromJson(json, Promocion.class);

        if (promocion != null) {
            return PromocionDAO.canjearCupon(promocion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
