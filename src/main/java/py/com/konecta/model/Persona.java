package py.com.konecta.model;

public class Persona {
	
	private Long id;
	private String nombre;
	private String correo;
	private boolean activo;
	
	public Persona() {
	}
	
	public Persona(Long id, String nombre, String correo) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.activo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	

}
