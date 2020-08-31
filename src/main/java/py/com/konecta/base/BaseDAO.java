package py.com.konecta.base;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.konecta.error.AppException;

public abstract class BaseDAO<G extends Object> {
	
	@PersistenceContext(unitName = "TestPU")
    protected EntityManager em;
	
	public abstract Class<G> getEntity();
	
	public void modify(Long id, G dto) throws AppException {
		
        G entity = (G) em.find(getEntity(), id);
        if (entity == null) {
            throw new AppException(404, "Not Found");
        }
        em.merge(dto);
    }

    public void insert(G entity) throws AppException {
    	
        em.persist(entity);
    }
    
    public G get(Long id) throws AppException {
    	
        try {
            return (G) em.find(getEntity(), id);
        } catch (Exception e) {
            throw new AppException(500, e.getMessage());
        }
    }

	
	public ListaResponse<G> listar(int inicio, int cantidad, String orderBy, 
            String odrerDir, HashMap<String, Object> filtros) {
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT c FROM ")
                .append(getEntity().getCanonicalName())
                .append(" c");
        

        buildWhere(query, filtros);
        buildOrder(query, orderBy, odrerDir);
        System.err.println(query.toString());
        Query q = em.createQuery(query.toString());
        setParametrers(q, filtros);
        q.setFirstResult(inicio);
        if (cantidad != -1) {            
            q.setMaxResults(cantidad);
        }
        
        @SuppressWarnings("unchecked")
		List<G> list = q.getResultList();
        int total = count(filtros);
        
        //se construye la respuesta 
        ListaResponse<G> res = new ListaResponse<G>();
        res.setRows(list);
        res.setCount(total);
        return res;
    }
	
	/**
    *
    * @param filtros
    * @return
    */
	private int count(HashMap<String, Object> filtros) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(c) FROM ")
               .append(getEntity().getCanonicalName())
               .append(" c");
		buildWhere(query, filtros);
		Query q = em.createQuery(query.toString());
		setParametrers(q, filtros);
		return ((Long) q.getSingleResult()).intValue();
   	}
	    
   	public void buildWhere(StringBuilder sb, HashMap<String, Object> filtros) {
   		
        if (filtros == null || filtros.isEmpty()) {
            return;
        }
        int tokens = filtros.keySet().size();
        int token = 1;
        sb.append(" WHERE ");
        for (String key : filtros.keySet()) {
            if (filtros.get(key) instanceof String) {
                sb.append(" LOWER(c.")
                        .append(key)
                        .append(") LIKE LOWER(:")
                        .append(key)
                        .append(")");
            } else {
                sb.append(key)
                        .append(" = :")
                        .append(key);
            }
            //se añade el 'AND' si hay más caracteres.
            if (token < tokens) {
                sb.append(" AND ");
            }
            token++;
        }
    }
    
    public void buildOrder(StringBuilder sb, String orderBy, String orderDir) {
        if (orderBy != null && !orderBy.isEmpty()) {
            sb.append(" ORDER BY c.").append(orderBy).append(" ").append(orderDir);
        }
    }

    /**
     *
     * @param q
     * @param filtros
     */
    public void setParametrers(Query q, HashMap<String, Object> filtros) {
        if (filtros == null) {
            return;
        }
        for (String key : filtros.keySet()) {
            Object value = filtros.get(key);
            if (filtros.get(key) instanceof String) {
                value = "%" + value + "%";
            }
            q.setParameter(key, value);
        }
    }

}
