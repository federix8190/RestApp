package py.com.konecta.seguridad;

import static py.com.konecta.configuracion.Constantes.EJB_JNDI_USUARIO_SERVICE;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import py.com.konecta.services.UsuarioService;

public class KRealm extends AuthorizingRealm {
	
	UsuarioService userService;
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        log.info("2 - doGetAuthenticationInfo : " + username + " - " + password);
        return new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection prins) {
    	
    	SimpleAuthorizationInfo info = null;
    	String username = null;
    	
    	if (prins != null && prins.getPrimaryPrincipal() != null) {
    		
    		username = prins.getPrimaryPrincipal().toString();
    		
			try {
				
				Context ctx = new InitialContext();
				userService = (UsuarioService) ctx.lookup(EJB_JNDI_USUARIO_SERVICE);
	    		//Set<String> permisos = userService.getPermisosUsuario(username);
				Set<String> permisos = new HashSet<>();
				permisos.add("USERS");
	    		info = new SimpleAuthorizationInfo(permisos);
	    		
			} catch (NamingException e) {
				log.severe("3 - doGetAuthorizationInfo NamingException : " + e.getMessage());
			}
    	}
    	
    	log.info("3 - doGetAuthorizationInfo : " + username);
    	return info;
    }

}
