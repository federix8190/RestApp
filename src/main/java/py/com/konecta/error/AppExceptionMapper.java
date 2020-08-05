package py.com.konecta.error;

import java.util.logging.Logger;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper implements ExceptionMapper<Exception> {
	
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	@Override
	public Response toResponse(Exception e) {
		
		log.info("Ocurrio un error : " + e.getMessage());
		
		if (e instanceof NotFoundException) {
			return Response.status(404).build();
		}
		
		return Response.status(500).build();
	}

}
