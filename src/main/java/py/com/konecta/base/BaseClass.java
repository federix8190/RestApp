package py.com.konecta.base;

import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;

import py.com.konecta.model.nucleo.Usuario;

public class BaseClass {

	/*public static Usuario obtenerUsuarioAutenticado() {
        Usuario usuario = null;
        Object user = SecurityUtils.getSubject().getSession().getAttribute("usuario");
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        usuario = gson.fromJson(userJson, Usuario.class);
        return usuario;
    }*/
	
	protected Response ok(Object resp) {
        return Response.ok().entity(resp).build();
    }

    protected Response unauthorized(Object resp) {
        return Response.status(401).entity(resp).build();
    }
    
    protected Response badRequest(String mensaje) {
        Respuesta resp = new Respuesta(false, mensaje);
        return Response.status(400).entity(resp).build();
    }
}
