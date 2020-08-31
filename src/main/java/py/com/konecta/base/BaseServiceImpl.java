package py.com.konecta.base;

import java.util.HashMap;

import py.com.konecta.error.AppException;
//import py.com.konecta.exceptions.BusinessException;

public abstract class BaseServiceImpl<G, D extends BaseDAO<G>> implements BaseService<G> {
	
	public abstract D getDao();
	
	@Override
    public ListaResponse<G> listar(Integer inicio, Integer cantidad, String orderBy, 
            String orderDir, HashMap<String, Object> filtros) {
        
        ListaResponse<G> res = getDao().listar(inicio, cantidad, orderBy, orderDir, filtros);
        return res;
    }
	
	@Override
    public G insertar(G entity) throws AppException {
		
        getDao().insert(entity);
        //validate(entity);
        return entity;
    }
	
	@Override
    public G obtener(Long id) throws AppException {
        try {
            G datos = getDao().get(id);
            return datos;
        } catch (Exception e) {
            throw new AppException(500, e.getMessage());
        }
    }
	
	@Override
    public void modificar(Long id, G entity) throws AppException {
        try {
            getDao().modify(id, entity);
        } catch (Exception e) {
            throw new AppException(500, e.getMessage());
        }
    }
	
	/*public void validate(G obj) {
        try {
            BeanValidatorUtils.validate(obj);
        } catch (IllegalArgumentException | BusinessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }*/

}
