package py.com.konecta.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import py.com.konecta.model.csj.Departamento;
import py.com.konecta.model.nucleo.Usuario;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
	
	@EJB
	UsuarioService service;
	
	@GET
	public List<Usuario> getUsuarios() {
		
		return service.getAll();
	}

}
