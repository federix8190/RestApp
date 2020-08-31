package py.com.konecta.services;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestResource {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GET
	public HashMap<String, String> test() {
		
		HashMap<String, String> res = new HashMap<String, String>();
		res.put("status", "0");
		res.put("mensaje", "Hola");
		logger.debug("holsaaaaaa");
		return res;
	}

}
