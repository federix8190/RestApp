package py.com.konecta.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.konecta.model.nucleo.Usuario;

@Stateless
public class UsuarioService {
	
	@PersistenceContext(unitName = "TEST_DS")
    protected EntityManager em;
	
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	public List<Usuario> getAll() {
		
		String sql = "SELECT u FROM Usuario";
		Query q = em.createQuery(sql);
		List<Usuario> users = q.getResultList();
		return users;
	}
	
	public Usuario getUsuario(String userName) {
		
		String sql = "SELECT u FROM Usuario u WHERE u.alias = :user";
		Query q = em.createQuery(sql);
		q.setParameter("user", userName);
		Usuario usuario = (Usuario) q.getSingleResult();
		return usuario;
	}
	
	public Set<String> getPermisosUsuario(String userName) {
    	
		Usuario usuario = getUsuario(userName);
		
		if (usuario != null) {
			Long rol = usuario.getRol();
			String sql = "SELECT r FROM RolPermiso r WHERE r.rolPermisoPK.idRol = :rol";
			Query q = em.createQuery(sql);
			q.setParameter("rol", rol);
		}
		
		Set<String> res = new HashSet<>(); 
		
		res.add("CHATBOT");
		res.add("DEUDAS");
		return res;
	}
	
	public boolean esUsuarioValido(String user, String password) {
		
		try {
			
			String sql = "SELECT u FROM Usuario u WHERE u.alias = :user AND u.password = :password";
			Query q = em.createQuery(sql);
			q.setParameter("user", user);
			q.setParameter("password", password);
			
			Usuario usuario = (Usuario) q.getSingleResult();
			if (usuario != null) {
				return true;
			}
		} catch (NoResultException e) {
			log.info("Credenciales invalidas");
		}
		return false;
	}

}
