
package pe.gob.miraflores.mobile.service.mapaincidenciasws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para incidenciaVoxiva complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="incidenciaVoxiva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCaso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fechaHoraCreacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoCaso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="subTipocaso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="detalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idTipoVia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idVia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numCuadra" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idDispositivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idSituacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaHoraAtencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreReportaIncidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccionIncidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accionesIncidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreRecepcionaIncidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dniRecepcionaIncidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nroCasoVoxiva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefReportaIncidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incidenciaPresentada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "incidenciaVoxiva", propOrder = {
    "idCaso",
    "fechaHoraCreacion",
    "tipoCaso",
    "subTipocaso",
    "detalle",
    "idTipoVia",
    "idVia",
    "numCuadra",
    "longitud",
    "latitud",
    "userName",
    "securityKey",
    "idDispositivo",
    "idSituacion",
    "fechaHoraAtencion",
    "nombreReportaIncidencia",
    "direccionIncidencia",
    "accionesIncidencia",
    "nombreRecepcionaIncidencia",
    "dniRecepcionaIncidencia",
    "nroCasoVoxiva",
    "telefReportaIncidencia",
    "incidenciaPresentada"
})
public class IncidenciaVoxiva {

    protected Integer idCaso;
    protected String fechaHoraCreacion;
    protected Integer tipoCaso;
    protected Integer subTipocaso;
    protected String detalle;
    protected Integer idTipoVia;
    protected Integer idVia;
    protected Integer numCuadra;
    protected String longitud;
    protected String latitud;
    protected String userName;
    protected String securityKey;
    protected String idDispositivo;
    protected String idSituacion;
    protected String fechaHoraAtencion;
    protected String nombreReportaIncidencia;
    protected String direccionIncidencia;
    protected String accionesIncidencia;
    protected String nombreRecepcionaIncidencia;
    protected String dniRecepcionaIncidencia;
    protected String nroCasoVoxiva;
    protected String telefReportaIncidencia;
    protected String incidenciaPresentada;

    /**
     * Obtiene el valor de la propiedad idCaso.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdCaso() {
        return idCaso;
    }

    /**
     * Define el valor de la propiedad idCaso.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdCaso(Integer value) {
        this.idCaso = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHoraCreacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    /**
     * Define el valor de la propiedad fechaHoraCreacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaHoraCreacion(String value) {
        this.fechaHoraCreacion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCaso.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoCaso() {
        return tipoCaso;
    }

    /**
     * Define el valor de la propiedad tipoCaso.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoCaso(Integer value) {
        this.tipoCaso = value;
    }

    /**
     * Obtiene el valor de la propiedad subTipocaso.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSubTipocaso() {
        return subTipocaso;
    }

    /**
     * Define el valor de la propiedad subTipocaso.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSubTipocaso(Integer value) {
        this.subTipocaso = value;
    }

    /**
     * Obtiene el valor de la propiedad detalle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * Define el valor de la propiedad detalle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetalle(String value) {
        this.detalle = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoVia.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdTipoVia() {
        return idTipoVia;
    }

    /**
     * Define el valor de la propiedad idTipoVia.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdTipoVia(Integer value) {
        this.idTipoVia = value;
    }

    /**
     * Obtiene el valor de la propiedad idVia.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdVia() {
        return idVia;
    }

    /**
     * Define el valor de la propiedad idVia.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdVia(Integer value) {
        this.idVia = value;
    }

    /**
     * Obtiene el valor de la propiedad numCuadra.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumCuadra() {
        return numCuadra;
    }

    /**
     * Define el valor de la propiedad numCuadra.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumCuadra(Integer value) {
        this.numCuadra = value;
    }

    /**
     * Obtiene el valor de la propiedad longitud.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * Define el valor de la propiedad longitud.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitud(String value) {
        this.longitud = value;
    }

    /**
     * Obtiene el valor de la propiedad latitud.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * Define el valor de la propiedad latitud.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitud(String value) {
        this.latitud = value;
    }

    /**
     * Obtiene el valor de la propiedad userName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Define el valor de la propiedad userName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Obtiene el valor de la propiedad securityKey.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityKey() {
        return securityKey;
    }

    /**
     * Define el valor de la propiedad securityKey.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityKey(String value) {
        this.securityKey = value;
    }

    /**
     * Obtiene el valor de la propiedad idDispositivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Define el valor de la propiedad idDispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDispositivo(String value) {
        this.idDispositivo = value;
    }

    /**
     * Obtiene el valor de la propiedad idSituacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSituacion() {
        return idSituacion;
    }

    /**
     * Define el valor de la propiedad idSituacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSituacion(String value) {
        this.idSituacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHoraAtencion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaHoraAtencion() {
        return fechaHoraAtencion;
    }

    /**
     * Define el valor de la propiedad fechaHoraAtencion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaHoraAtencion(String value) {
        this.fechaHoraAtencion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreReportaIncidencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreReportaIncidencia() {
        return nombreReportaIncidencia;
    }

    /**
     * Define el valor de la propiedad nombreReportaIncidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreReportaIncidencia(String value) {
        this.nombreReportaIncidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad direccionIncidencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionIncidencia() {
        return direccionIncidencia;
    }

    /**
     * Define el valor de la propiedad direccionIncidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionIncidencia(String value) {
        this.direccionIncidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad accionesIncidencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccionesIncidencia() {
        return accionesIncidencia;
    }

    /**
     * Define el valor de la propiedad accionesIncidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccionesIncidencia(String value) {
        this.accionesIncidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreRecepcionaIncidencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreRecepcionaIncidencia() {
        return nombreRecepcionaIncidencia;
    }

    /**
     * Define el valor de la propiedad nombreRecepcionaIncidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreRecepcionaIncidencia(String value) {
        this.nombreRecepcionaIncidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad dniRecepcionaIncidencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDniRecepcionaIncidencia() {
        return dniRecepcionaIncidencia;
    }

    /**
     * Define el valor de la propiedad dniRecepcionaIncidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDniRecepcionaIncidencia(String value) {
        this.dniRecepcionaIncidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad nroCasoVoxiva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroCasoVoxiva() {
        return nroCasoVoxiva;
    }

    /**
     * Define el valor de la propiedad nroCasoVoxiva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroCasoVoxiva(String value) {
        this.nroCasoVoxiva = value;
    }

    /**
     * Obtiene el valor de la propiedad telefReportaIncidencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefReportaIncidencia() {
        return telefReportaIncidencia;
    }

    /**
     * Define el valor de la propiedad telefReportaIncidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefReportaIncidencia(String value) {
        this.telefReportaIncidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad incidenciaPresentada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidenciaPresentada() {
        return incidenciaPresentada;
    }

    /**
     * Define el valor de la propiedad incidenciaPresentada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidenciaPresentada(String value) {
        this.incidenciaPresentada = value;
    }

}
