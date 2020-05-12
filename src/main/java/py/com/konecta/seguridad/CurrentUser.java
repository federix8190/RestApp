package py.com.konecta.seguridad;

public class CurrentUser {
	
	private Long id;
	private String alias;
	private Long rol;
	
	public CurrentUser(Long id, String alias, Long rol) {
		this.id = id;
		this.alias = alias;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Long getRol() {
		return rol;
	}

	public void setRol(Long rol) {
		this.rol = rol;
	}

}
