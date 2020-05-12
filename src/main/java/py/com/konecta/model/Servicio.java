package py.com.konecta.model;

public class Servicio {
	
	private Long id;
    private String descripcion;
    private int comision;
    private String estado;
    
    public Servicio() {
    }
    
    public Servicio(Long id, String desc, int com, String estado) {
    	this.id = id;
    	this.descripcion = desc;
    	this.comision = com;
    	this.estado = estado;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getComision() {
		return comision;
	}

	public void setComision(int comision) {
		this.comision = comision;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
