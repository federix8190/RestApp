package py.com.konecta.seguridad;

import static py.com.konecta.Constantes.EJB_JNDI_USUARIO_SERVICE;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

import py.com.konecta.services.UsuarioService;

public class KCredentialsMatcher extends SimpleCredentialsMatcher {

	UsuarioService userService;
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	@Override
    public boolean doCredentialsMatch(AuthenticationToken tok, AuthenticationInfo info) {
		
        try {
        	
	    	if (tok != null && tok.getPrincipal() != null && tok.getCredentials() != null) {
	        	
	        	String username = tok.getPrincipal().toString();
	            String password = new String((char[]) tok.getCredentials());
	        	String encryptedToken = new Md5Hash(new String((char[]) tok.getCredentials()), username).toString();
	            log.info("3 - doCredentialsMatch : " + username + " - " + password + " - " + encryptedToken);
	            Context ctx = new InitialContext();
	            userService = (UsuarioService) ctx.lookup(EJB_JNDI_USUARIO_SERVICE);	            
	            return userService.esUsuarioValido(username, password);
	        }
	    	
        } catch (Exception e) {
        	log.severe("Error en metodo doCredentialsMatch : " + e.getMessage());
        }
        return false;

    }
	
}
