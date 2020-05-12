package py.com.konecta.seguridad;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;

@Provider
@ServerInterceptor
@Precedence("SECURITY")
public class SecurityInterceptor implements ContainerRequestFilter {
	
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		String url = requestContext.getUriInfo().getPath();
		String method = requestContext.getMethod();
		log.info("2 - Filtro de seguridad : " + method + " " + url);
		
	}

}
