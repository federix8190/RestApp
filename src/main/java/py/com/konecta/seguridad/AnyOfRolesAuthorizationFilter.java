package py.com.konecta.seguridad;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.google.gson.JsonObject;

public class AnyOfRolesAuthorizationFilter extends RolesAuthorizationFilter {
	
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		
		final Subject subject = getSubject(request, response);
		final String[] rolesArray = (String[]) mappedValue;
		Subject currentUser = SecurityUtils.getSubject();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getRequestURI();
		String metodo = httpRequest.getMethod();
		String header = httpRequest.getHeader("Authorization");
		
		log.info("1 - AnyOfRolesAuthorizationFilter : " + path + " - " + currentUser.isAuthenticated() + " - " + rolesArray.length);
		
		if (!currentUser.isAuthenticated()) {
			if (header != null && !header.isEmpty()) {
				return true;
			}
		}
		
		for (String roleName : rolesArray) {
			String[] par = roleName.split("-");
			if (par[0].equals(metodo) && subject.hasRole(par[1])) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected boolean onAccessDenied(final ServletRequest request, final ServletResponse response) 
			throws IOException {
		
		HttpServletResponse httpResponse;
		
		try {
			
			JsonObject json = new JsonObject();
			json.addProperty("exitoso", false);
			
			httpResponse = WebUtils.toHttp(response);
			httpResponse.setContentType(MediaType.APPLICATION_JSON);
			
			json.addProperty("mensaje", "Usuario no autenticado");
			httpResponse.setStatus(401);
			httpResponse.getWriter().write(json.toString());
			httpResponse.getWriter().flush();
			httpResponse.getWriter().close();
			
		} catch (ClassCastException ex) {
			return super.onAccessDenied(request, response);
		}

		return false;
		
	}

}
