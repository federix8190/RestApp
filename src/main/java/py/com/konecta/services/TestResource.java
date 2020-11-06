package py.com.konecta.services;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestResource {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Context
	private HttpServletRequest httpRequest;
	
	@GET
	public HashMap<String, String> test() {
		
		String ip = httpRequest.getRemoteAddr();
		HashMap<String, String> res = new HashMap<String, String>();
		res.put("status", "0");
		res.put("IP", ip);
		return res;
	}

}
