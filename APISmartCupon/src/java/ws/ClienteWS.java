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
import modelo.ClienteDAO;
import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;
import validator.ClienteValidator;



@Path("cliente")
public class ClienteWS {
    
    @Path("lista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerLista() {
        ClienteDAO dao = new ClienteDAO();
        return dao.obtenerLista();
    }
    
    @Path("obtener/{idCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente obtenerCliente(@PathParam ("idCliente") Integer idCliente) {
        if(idCliente != null && idCliente > 0){
            return ClienteDAO.obtenerCliente(idCliente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregar(Cliente cliente){
        Mensaje mensaje = ClienteValidator.isValid(cliente);
        if(mensaje.isError()) {
            return mensaje;
        }
        ClienteDAO dao = new ClienteDAO();
        return dao.registrar(cliente);
    }
    
    @PUT
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editar(Cliente cliente){
        Mensaje mensaje = ClienteValidator.isValid(cliente);
        if(mensaje.isError()) {
            return mensaje;
        }
        ClienteDAO dao = new ClienteDAO();
        return dao.editar(cliente);
    }
    
    @DELETE
    @Path("eliminar/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminar(@PathParam("idCliente") Integer idCliente){
        ClienteDAO dao = new ClienteDAO();
        return dao.eliminar(idCliente);
    }
}
