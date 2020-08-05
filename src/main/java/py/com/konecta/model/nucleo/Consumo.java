package py.com.konecta.model.nucleo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "V_TRAF_CONSUMO_3G_BAM")
public class Consumo {

    @EmbeddedId
    private ConsumoId id;

    @Transient
    private Double mensajesEntrante;

    @Transient
    private Double mensajesSaliente;

    @Transient
    private Double llamadasEntrante;

    @Transient
    private Double llamadasSaliente;

    @Transient
    private Double minutosEntrante;

    @Transient
    private Double minutosSaliente;

    @Transient
    private Double datosEntrante;

    @Transient
    private Double datosSaliente;

    @Column(name = "SMS_ENTRANTES_MAN")
    private Integer mensajesEntrantesManana;

    @Column(name = "SMS_ENTRANTES_TAR")
    private Integer mensajesEntrantesTarde;

    @Column(name = "SMS_ENTRANTES_NOC")
    private Integer mensajesEntrantesNoche;

    @Column(name = "SMS_SALIENTES_MAN")
    private Integer mensajesSalientesManana;

    @Column(name = "SMS_SALIENTES_TAR")
    private Integer mensajesSalientesTarde;

    @Column(name = "SMS_SALIENTES_NOC")
    private Integer mensajesSalientesNoche;

    @Column(name = "LLAM_ENTRANTES_MAN")
    private Integer llamadasEntrantesManana;

    @Column(name = "LLAM_ENTRANTES_TAR")
    private Integer llamadasEntrantesTarde;

    @Column(name = "LLAM_ENTRANTES_NOC")
    private Integer llamadasEntrantesNoche;

    @Column(name = "LLAM_SALIENTES_MAN")
    private Integer llamadasSalientesManana;

    @Column(name = "LLAM_SALIENTES_TAR")
    private Integer llamadasSalientesTarde;

    @Column(name = "LLAM_SALIENTES_NOC")
    private Integer llamadasSalientesNoche;

    @Column(name = "MIN_ENTRANTES_MAN")
    private Double minutosEntrantesManana;

    @Column(name = "MIN_ENTRANTES_TAR")
    private Double minutosEntrantesTarde;

    @Column(name = "MIN_ENTRANTES_NOC")
    private Double minutosEntrantesNoche;

    @Column(name = "MIN_SALIENTES_MAN")
    private Double minutosSalientesManana;

    @Column(name = "MIN_SALIENTES_TAR")
    private Double minutosSalientesTarde;

    @Column(name = "MIN_SALIENTES_NOC")
    private Double minutosSalientesNoche;

    @Column(name = "KB_ENTRANTES_MAN")
    private Double datosEntrantesManana;

    @Column(name = "KB_ENTRANTES_TAR")
    private Double datosEntrantesTarde;

    @Column(name = "KB_ENTRANTES_NOC")
    private Double datosEntrantesNoche;

    @Column(name = "KB_SALIENTES_MAN")
    private Double datosSalientesManana;

    @Column(name = "KB_SALIENTES_TAR")
    private Double datosSalientesTarde;

    @Column(name = "KB_SALIENTES_NOC")
    private Double datosSalientesNoche;

    @Column(name = "MONTO_DESC_LLA")
    private Double montoDescontadoLlamadas;

    @Column(name = "MONTO_DESC_SMS")
    private Double montoDescontadoMensajes;

    @Column(name = "MONTO_DESC_DAT")
    private Double montoDescontadoDatos;

    @Column(name = "MONTO_ULTIMA_CARGA")
    private Double montoUltimaCarga;

    @Column(name = "CANTIDAD_PACKS")
    private Integer cantidadPacks;

    @Column(name = "INTERNET_GRAL")
    private Double internetGeneral;

    @Column(name = "CONSUMO_REDESSOC")
    private Double consumoRedesSociales;

    @Column(name = "CONSUMO_NUBE")
    private Double consumoNube;

    @Column(name = "CONSUMO_MENSAJERIA")
    private Double consumoMensajeria;

    @Column(name = "CONSUMO_P2P")
    private Double consumoP2P;

    public Consumo() {
    }

    public ConsumoId getId() {
        return id;
    }

    public void setId(ConsumoId id) {
        this.id = id;
    }

    public Double getMensajesEntrante() {
        return mensajesEntrante;
    }

    public void setMensajesEntrante(Double mensajesEntrante) {
        this.mensajesEntrante = mensajesEntrante;
    }

    public Double getMensajesSaliente() {
        return mensajesSaliente;
    }

    public void setMensajesSaliente(Double mensajesSaliente) {
        this.mensajesSaliente = mensajesSaliente;
    }

    public Double getLlamadasEntrante() {
        return llamadasEntrante;
    }

    public void setLlamadasEntrante(Double llamadasEntrante) {
        this.llamadasEntrante = llamadasEntrante;
    }

    public Double getLlamadasSaliente() {
        return llamadasSaliente;
    }

    public void setLlamadasSaliente(Double llamadasSaliente) {
        this.llamadasSaliente = llamadasSaliente;
    }

    public Double getMinutosEntrante() {
        return minutosEntrante;
    }

    public void setMinutosEntrante(Double minutosEntrante) {
        this.minutosEntrante = minutosEntrante;
    }

    public Double getMinutosSaliente() {
        return minutosSaliente;
    }

    public void setMinutosSaliente(Double minutosSaliente) {
        this.minutosSaliente = minutosSaliente;
    }

    public Double getDatosEntrante() {
        return datosEntrante;
    }

    public void setDatosEntrante(Double datosEntrante) {
        this.datosEntrante = datosEntrante;
    }

    public Double getDatosSaliente() {
        return datosSaliente;
    }

    public void setDatosSaliente(Double datosSaliente) {
        this.datosSaliente = datosSaliente;
    }

    public Integer getMensajesEntrantesManana() {
        return mensajesEntrantesManana;
    }

    public void setMensajesEntrantesManana(Integer mensajesEntrantesManana) {
        this.mensajesEntrantesManana = mensajesEntrantesManana;
    }

    public Integer getMensajesEntrantesTarde() {
        return mensajesEntrantesTarde;
    }

    public void setMensajesEntrantesTarde(Integer mensajesEntrantesTarde) {
        this.mensajesEntrantesTarde = mensajesEntrantesTarde;
    }

    public Integer getMensajesEntrantesNoche() {
        return mensajesEntrantesNoche;
    }

    public void setMensajesEntrantesNoche(Integer mensajesEntrantesNoche) {
        this.mensajesEntrantesNoche = mensajesEntrantesNoche;
    }

    public Integer getMensajesSalientesManana() {
        return mensajesSalientesManana;
    }

    public void setMensajesSalientesManana(Integer mensajesSalientesManana) {
        this.mensajesSalientesManana = mensajesSalientesManana;
    }

    public Integer getMensajesSalientesTarde() {
        return mensajesSalientesTarde;
    }

    public void setMensajesSalientesTarde(Integer mensajesSalientesTarde) {
        this.mensajesSalientesTarde = mensajesSalientesTarde;
    }

    public Integer getMensajesSalientesNoche() {
        return mensajesSalientesNoche;
    }

    public void setMensajesSalientesNoche(Integer mensajesSalientesNoche) {
        this.mensajesSalientesNoche = mensajesSalientesNoche;
    }

    public Integer getLlamadasEntrantesManana() {
        return llamadasEntrantesManana;
    }

    public void setLlamadasEntrantesManana(Integer llamadasEntrantesManana) {
        this.llamadasEntrantesManana = llamadasEntrantesManana;
    }

    public Integer getLlamadasEntrantesTarde() {
        return llamadasEntrantesTarde;
    }

    public void setLlamadasEntrantesTarde(Integer llamadasEntrantesTarde) {
        this.llamadasEntrantesTarde = llamadasEntrantesTarde;
    }

    public Integer getLlamadasEntrantesNoche() {
        return llamadasEntrantesNoche;
    }

    public void setLlamadasEntrantesNoche(Integer llamadasEntrantesNoche) {
        this.llamadasEntrantesNoche = llamadasEntrantesNoche;
    }

    public Integer getLlamadasSalientesManana() {
        return llamadasSalientesManana;
    }

    public void setLlamadasSalientesManana(Integer llamadasSalientesManana) {
        this.llamadasSalientesManana = llamadasSalientesManana;
    }

    public Integer getLlamadasSalientesTarde() {
        return llamadasSalientesTarde;
    }

    public void setLlamadasSalientesTarde(Integer llamadasSalientesTarde) {
        this.llamadasSalientesTarde = llamadasSalientesTarde;
    }

    public Integer getLlamadasSalientesNoche() {
        return llamadasSalientesNoche;
    }

    public void setLlamadasSalientesNoche(Integer llamadasSalientesNoche) {
        this.llamadasSalientesNoche = llamadasSalientesNoche;
    }

    public Double getMinutosEntrantesManana() {
        return minutosEntrantesManana;
    }

    public void setMinutosEntrantesManana(Double minutosEntrantesManana) {
        this.minutosEntrantesManana = minutosEntrantesManana;
    }

    public Double getMinutosEntrantesTarde() {
        return minutosEntrantesTarde;
    }

    public void setMinutosEntrantesTarde(Double minutosEntrantesTarde) {
        this.minutosEntrantesTarde = minutosEntrantesTarde;
    }

    public Double getMinutosEntrantesNoche() {
        return minutosEntrantesNoche;
    }

    public void setMinutosEntrantesNoche(Double minutosEntrantesNoche) {
        this.minutosEntrantesNoche = minutosEntrantesNoche;
    }

    public Double getMinutosSalientesManana() {
        return minutosSalientesManana;
    }

    public void setMinutosSalientesManana(Double minutosSalientesManana) {
        this.minutosSalientesManana = minutosSalientesManana;
    }

    public Double getMinutosSalientesTarde() {
        return minutosSalientesTarde;
    }

    public void setMinutosSalientesTarde(Double minutosSalientesTarde) {
        this.minutosSalientesTarde = minutosSalientesTarde;
    }

    public Double getMinutosSalientesNoche() {
        return minutosSalientesNoche;
    }

    public void setMinutosSalientesNoche(Double minutosSalientesNoche) {
        this.minutosSalientesNoche = minutosSalientesNoche;
    }

    public Double getDatosEntrantesManana() {
        return datosEntrantesManana;
    }

    public void setDatosEntrantesManana(Double datosEntrantesManana) {
        this.datosEntrantesManana = datosEntrantesManana;
    }

    public Double getDatosEntrantesTarde() {
        return datosEntrantesTarde;
    }

    public void setDatosEntrantesTarde(Double datosEntrantesTarde) {
        this.datosEntrantesTarde = datosEntrantesTarde;
    }

    public Double getDatosEntrantesNoche() {
        return datosEntrantesNoche;
    }

    public void setDatosEntrantesNoche(Double datosEntrantesNoche) {
        this.datosEntrantesNoche = datosEntrantesNoche;
    }

    public Double getDatosSalientesManana() {
        return datosSalientesManana;
    }

    public void setDatosSalientesManana(Double datosSalientesManana) {
        this.datosSalientesManana = datosSalientesManana;
    }

    public Double getDatosSalientesTarde() {
        return datosSalientesTarde;
    }

    public void setDatosSalientesTarde(Double datosSalientesTarde) {
        this.datosSalientesTarde = datosSalientesTarde;
    }

    public Double getDatosSalientesNoche() {
        return datosSalientesNoche;
    }

    public void setDatosSalientesNoche(Double datosSalientesNoche) {
        this.datosSalientesNoche = datosSalientesNoche;
    }

    @Override
    public String toString() {
        return "Consumo{" + "id=" + id + ", mensajesEntrante=" + mensajesEntrante + ", mensajesSaliente=" + mensajesSaliente + ", llamadasEntrante=" + llamadasEntrante + ", llamadasSaliente=" + llamadasSaliente + ", minutosEntrante=" + minutosEntrante + ", minutosSaliente=" + minutosSaliente + ", datosEntrante=" + datosEntrante + ", datosSaliente=" + datosSaliente + ", mensajesEntrantesManana=" + mensajesEntrantesManana + ", mensajesEntrantesTarde=" + mensajesEntrantesTarde + ", mensajesEntrantesNoche=" + mensajesEntrantesNoche + ", mensajesSalientesManana=" + mensajesSalientesManana + ", mensajesSalientesTarde=" + mensajesSalientesTarde + ", mensajesSalientesNoche=" + mensajesSalientesNoche + ", llamadasEntrantesManana=" + llamadasEntrantesManana + ", llamadasEntrantesTarde=" + llamadasEntrantesTarde + ", llamadasEntrantesNoche=" + llamadasEntrantesNoche + ", llamadasSalientesManana=" + llamadasSalientesManana + ", llamadasSalientesTarde=" + llamadasSalientesTarde + ", llamadasSalientesNoche=" + llamadasSalientesNoche + ", minutosEntrantesManana=" + minutosEntrantesManana + ", minutosEntrantesTarde=" + minutosEntrantesTarde + ", minutosEntrantesNoche=" + minutosEntrantesNoche + ", minutosSalientesManana=" + minutosSalientesManana + ", minutosSalientesTarde=" + minutosSalientesTarde + ", minutosSalientesNoche=" + minutosSalientesNoche + ", datosEntrantesManana=" + datosEntrantesManana + ", datosEntrantesTarde=" + datosEntrantesTarde + ", datosEntrantesNoche=" + datosEntrantesNoche + ", datosSalientesManana=" + datosSalientesManana + ", datosSalientesTarde=" + datosSalientesTarde + ", datosSalientesNoche=" + datosSalientesNoche + ", montoDescontadoLlamadas=" + montoDescontadoLlamadas + ", montoDescontadoMensajes=" + montoDescontadoMensajes + ", montoDescontadoDatos=" + montoDescontadoDatos + ", montoUltimaCarga=" + montoUltimaCarga + ", cantidadPacks=" + cantidadPacks + ", internetGeneral=" + internetGeneral + ", consumoRedesSociales=" + consumoRedesSociales + ", consumoNube=" + consumoNube + ", consumoMensajeria=" + consumoMensajeria + ", consumoP2P=" + consumoP2P + '}';
    }
}
