package py.com.konecta.model;

public class Deuda {
	
	private Long index;
	private Long servicioId;
	private String vencimiento;
	private String factura;
	private String importe;
	private String cliente;
	private String comision;

	public Deuda() {
	}
	
	public Deuda(Long index, String vencimiento, String factura, String importe) {
		
		this.index = index;
		this.vencimiento = vencimiento;
		this.factura = factura;
		this.importe = importe;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public Long getServicioId() {
		return servicioId;
	}

	public void setServicioId(Long servicioId) {
		this.servicioId = servicioId;
	}

	public String getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}
	
	
}
