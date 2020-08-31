package py.com.konecta.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.konecta.model.Persona;

@Stateless
public class UsuarioService {
	
	public List<Persona> getAll() {
		
		List<Persona> lista = new ArrayList<Persona>();
		lista.add(new Persona(1L, "Juan Perez", "jperez@gmail.com"));
		lista.add(new Persona(1L, "Carlos Lopez", "clopez@gmail.com"));
		return lista;
	}

}
