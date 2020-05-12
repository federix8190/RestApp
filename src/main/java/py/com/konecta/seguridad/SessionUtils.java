package py.com.konecta.seguridad;

import org.apache.shiro.SecurityUtils;

public class SessionUtils {

public static CurrentUser getCurrentUser() {
		
		CurrentUser user = (CurrentUser) SecurityUtils.getSubject().getSession()
        		.getAttribute("currentUserSession");
        return user;
    }
}
