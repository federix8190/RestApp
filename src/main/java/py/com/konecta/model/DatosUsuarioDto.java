package py.com.konecta.model;

import java.util.List;

public class DatosUsuarioDto {
	
	private Long id;
	private String alias;
	private String nombre;
	private String correo;
	private String estado;
	private String telefono;
	private String numeroDocumento;
	private String imagen;
	private Boolean activo;
	private Long idRol;
	private String rolName;
	private Long idRematadora;
	private String rematadora;
	private List<String> permisos;
	private String empresa;
    private String ruc;
    private String codigoEstablecimiento;
	
	public DatosUsuarioDto() {
		
		this.id = 1L;
		this.alias = "jperez";
		this.nombre = "Juan Perez";
		this.correo = "jperez@test.com";
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public Long getIdRematadora() {
		return idRematadora;
	}

	public void setIdRematadora(Long idRematadora) {
		this.idRematadora = idRematadora;
	}

	public String getRematadora() {
		return rematadora;
	}

	public void setRematadora(String rematadora) {
		this.rematadora = rematadora;
	}

	public List<String> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<String> permisos) {
		this.permisos = permisos;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getCodigoEstablecimiento() {
		return codigoEstablecimiento;
	}

	public void setCodigoEstablecimiento(String codigoEstablecimiento) {
		this.codigoEstablecimiento = codigoEstablecimiento;
	}

}
