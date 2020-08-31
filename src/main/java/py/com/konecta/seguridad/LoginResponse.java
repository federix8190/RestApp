package py.com.konecta.seguridad;

import py.com.konecta.base.Respuesta;

public class LoginResponse extends Respuesta {
	
	private Long userId;
	private String usuario;
	private String fechaUltimoAcceso;
	
	public LoginResponse() {
	}
	
	public LoginResponse(Long userId, String usuario) {
		super(true, null);
		this.userId = userId;
		this.usuario = usuario;
	}
	
	public LoginResponse(Long userId, String usuario, String mensaje) {
		super(true, mensaje);
		this.userId = userId;
		this.usuario = usuario;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	public void setFechaUltimoAcceso(String fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
	}

}
