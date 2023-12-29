/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.CategoriaDAO;
import modelo.pojo.Categoria;

/**
 *
 * @author mateo
 */

@Path("categorias")
public class CategoriaWS {
    
    
    @GET
    @Path("obtenerCategorias")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> obtenerlistaCategorias(){
        return CategoriaDAO.obtenerCateogrias();
    }
}
