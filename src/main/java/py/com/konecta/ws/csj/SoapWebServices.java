package py.com.konecta.ws.csj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Qualifier;
import py.com.konecta.ws.csj.autenticacion.Autenticacion;
import py.com.konecta.ws.csj.autenticacion.AutenticacionSoap;

public class SoapWebServices {
    
    /*
     * Qualifier utilizado para inyectar instancias en las clases del paquete wrapper.
     */
    @Qualifier
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Wrapped {
    }
    
    @Produces
    public AutenticacionSoap getAutenticacionSoap() throws Exception {
        URL url = new URL("https://www.csj.gov.py/appseguridad/seguridad/sistema/autenticacion.asmx?wsdl");
        Autenticacion service = new Autenticacion(url);
        AutenticacionSoap port = service.getAutenticacionSoap();        
        return port;
    }
    
}
