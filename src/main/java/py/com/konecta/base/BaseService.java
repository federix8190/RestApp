package py.com.konecta.base;

import java.util.HashMap;

import py.com.konecta.error.AppException;

public interface BaseService<G> {
	
	public ListaResponse<G> listar(Integer inicio, Integer cantidad, String orderBy, String orderDir,
            HashMap<String, Object> filtros);
	
	public G insertar(G entity) throws AppException;
	
	public void modificar(Long id, G entity) throws AppException;
	
	public G obtener(Long id) throws AppException;

}
