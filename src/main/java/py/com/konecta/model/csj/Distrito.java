package py.com.konecta.model.csj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "distrito")
@NamedStoredProcedureQuery(
	name = "replicar_distrito", 
	procedureName = "replicar_distrito", 
	parameters = { 
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "id"), 
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "codigo"), 
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "nombre"), 
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "departamento"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "userId")
	}
)
public class Distrito implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_version")
    private Long id;

    @Column(name = "codigo")
	protected String codigo;
    
    @Column(name = "nombre")
    private String nombre;
    
    @JoinColumn(name = "departamento", referencedColumnName = "id_version")
    @ManyToOne
    private Departamento departamento;
    
    public Distrito() {
    }

    public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}
