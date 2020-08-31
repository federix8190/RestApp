package py.com.konecta.interfaces;

import javax.enterprise.inject.Produces;

import org.jboss.resteasy.client.ProxyFactory;

import py.com.konecta.error.AppException;

public class RestfulWebServices {

	@Produces
    public RestApiInterface getNotificacionesInterface() throws AppException {
        String url = "http://localhost:5000";
        RestApiInterface object = ProxyFactory.create(RestApiInterface.class, url);
        return object;
    }
	
}
