package py.com.konecta.seguridad;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import py.com.konecta.Respuesta;

@Path("/sesion")
public class SessionResource {
	
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarSesion(Credenciales credenciales) throws Exception {

		Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
        	
            if (credenciales != null) {
            	return autenticar(credenciales);
            }
            Respuesta res = new Respuesta(false, "Debe proveer las credenciales");
        	return Response.status(401).entity(res).build();
            
        } else {
        	
        	CurrentUser usuario = SessionUtils.getCurrentUser();
            LoginResponse resp = new LoginResponse(usuario.getId(), usuario.getAlias(), "Usuario autenticado");
            return Response.ok(resp).build();
        }
    }
	
	private Response autenticar(Credenciales credenciales) throws Exception {
		
		String username = credenciales.getUsername();
    	String password = credenciales.getPassword();
    	
    	if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
    		
    		try {
    		
	    		log.info("1 - iniciarSesion : " + username + " - " + password);
	    		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	            Subject currentUser = SecurityUtils.getSubject();
	            currentUser.login(token);
	            token.setRememberMe(true);
	            
	            log.info("4 - Login exitoso");
	    		LoginResponse resp = new LoginResponse(0L, username, "Usuario autenticado");
	            return Response.ok(resp).build();
            
    		} catch (IncorrectCredentialsException e) {
    			
            	log.severe("4 Credenciales incorrectas : " + username);
                Respuesta res = new Respuesta(false, "Credenciales incorrectas");
                return Response.status(401).entity(res).build();
                
            } catch (Exception e) {
            	
            	log.severe("Error al autenticar al usuario[" + username +"] : " + e.getMessage());
                Respuesta res = new Respuesta(false, "Ocurrio un error al autenticar al usuario");
                return Response.status(500).entity(res).build();
            }
    		
    	}
    	Respuesta res = new Respuesta(false, "Debe proveer las credenciales");
    	return Response.status(401).entity(res).build();
	}

}
