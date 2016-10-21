
package pe.gob.miraflores.mobile.service.mapaincidenciasws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para incidenciaCambista complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="incidenciaCambista">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="latitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numPlaca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numDocIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="velocidad" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orientacion" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="idAlerta" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="desAlerta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecHoraRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "incidenciaCambista", propOrder = {
    "latitud",
    "longitud",
    "numPlaca",
    "numDocIdent",
    "imei",
    "velocidad",
    "direccion",
    "orientacion",
    "idAlerta",
    "desAlerta",
    "fecHoraRegistro"
})
public class IncidenciaCambista {

    protected String latitud;
    protected String longitud;
    protected String numPlaca;
    protected String numDocIdent;
    protected String imei;
    protected BigDecimal velocidad;
    protected String direccion;
    protected BigDecimal orientacion;
    protected Integer idAlerta;
    protected String desAlerta;
    protected String fecHoraRegistro;

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
     * Obtiene el valor de la propiedad numPlaca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumPlaca() {
        return numPlaca;
    }

    /**
     * Define el valor de la propiedad numPlaca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumPlaca(String value) {
        this.numPlaca = value;
    }

    /**
     * Obtiene el valor de la propiedad numDocIdent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocIdent() {
        return numDocIdent;
    }

    /**
     * Define el valor de la propiedad numDocIdent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocIdent(String value) {
        this.numDocIdent = value;
    }

    /**
     * Obtiene el valor de la propiedad imei.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImei() {
        return imei;
    }

    /**
     * Define el valor de la propiedad imei.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImei(String value) {
        this.imei = value;
    }

    /**
     * Obtiene el valor de la propiedad velocidad.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVelocidad() {
        return velocidad;
    }

    /**
     * Define el valor de la propiedad velocidad.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVelocidad(BigDecimal value) {
        this.velocidad = value;
    }

    /**
     * Obtiene el valor de la propiedad direccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Define el valor de la propiedad direccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Obtiene el valor de la propiedad orientacion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOrientacion() {
        return orientacion;
    }

    /**
     * Define el valor de la propiedad orientacion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOrientacion(BigDecimal value) {
        this.orientacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idAlerta.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdAlerta() {
        return idAlerta;
    }

    /**
     * Define el valor de la propiedad idAlerta.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdAlerta(Integer value) {
        this.idAlerta = value;
    }

    /**
     * Obtiene el valor de la propiedad desAlerta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesAlerta() {
        return desAlerta;
    }

    /**
     * Define el valor de la propiedad desAlerta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesAlerta(String value) {
        this.desAlerta = value;
    }

    /**
     * Obtiene el valor de la propiedad fecHoraRegistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecHoraRegistro() {
        return fecHoraRegistro;
    }

    /**
     * Define el valor de la propiedad fecHoraRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecHoraRegistro(String value) {
        this.fecHoraRegistro = value;
    }

}
