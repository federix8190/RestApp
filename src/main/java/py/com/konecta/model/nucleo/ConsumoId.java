package py.com.konecta.model.nucleo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author federico.torres
 */
@Embeddable
public class ConsumoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "LINEA")
    private String linea;

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ConsumoDWHId{" + "fecha=" + fecha + ", linea=" + linea + '}';
    }

}
