package py.com.konecta.model.nucleo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "rol_permiso")
public class RolPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected RolPermisoPK rolPermisoPK;
    
    @Column(name = "activo")
	protected Boolean activo;
	
	@Column(name = "usuario_creacion")
	protected Long usuarioCreacion;
	
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaCreacion;
	
	@Column(name = "usuario_modificacion")
	protected Long usuarioModificacion;
	
	@Column(name = "fecha_modificacion")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaModificacion;
	
	@Column(name = "usuario_eliminacion")
	protected Long usuarioEliminacion;
	
	@Column(name = "fecha_eliminacion")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaEliminacion;
    
    public RolPermiso() {
    }

    public RolPermiso(RolPermisoPK rolPermisoPK) {
        this.rolPermisoPK = rolPermisoPK;
    }

    public RolPermiso(long rol, long permiso) {
        this.rolPermisoPK = new RolPermisoPK(rol, permiso);
    }

    public RolPermisoPK getRolPermisoPK() {
        return rolPermisoPK;
    }

    public void setRolPermisoPK(RolPermisoPK rolPermisoPK) {
        this.rolPermisoPK = rolPermisoPK;
    }

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Long getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Long usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(Long usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getUsuarioEliminacion() {
		return usuarioEliminacion;
	}

	public void setUsuarioEliminacion(Long usuarioEliminacion) {
		this.usuarioEliminacion = usuarioEliminacion;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	} 

}
