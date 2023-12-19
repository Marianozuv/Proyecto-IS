package ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.AutenticacionAppDAO;
import modelo.pojo.RespuestaLoginApp;


@Path("autenticacion")
public class AutenticacionAppWS {
    @Context
    private UriInfo context;

    public AutenticacionAppWS() {
    }
    
    @POST
    @Path("loginApp")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLoginApp iniciarSesionApp(@FormParam("email") String email, @FormParam("password") String password) {
        RespuestaLoginApp respuesta = null;
        if (!email.isEmpty() && !password.isEmpty()) {
            respuesta = AutenticacionAppDAO.verificarSesionApp(email, password);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuesta;
    }
}
