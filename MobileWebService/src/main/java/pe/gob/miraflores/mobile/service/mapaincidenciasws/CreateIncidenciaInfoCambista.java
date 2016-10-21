
package pe.gob.miraflores.mobile.service.mapaincidenciasws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createIncidenciaInfoCambista complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createIncidenciaInfoCambista">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="record" type="{http://mapaincidenciasws.service.mobile.miraflores.gob.pe/}incidenciaCambista" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createIncidenciaInfoCambista", propOrder = {
    "record"
})
public class CreateIncidenciaInfoCambista {

    protected IncidenciaCambista record;

    /**
     * Obtiene el valor de la propiedad record.
     * 
     * @return
     *     possible object is
     *     {@link IncidenciaCambista }
     *     
     */
    public IncidenciaCambista getRecord() {
        return record;
    }

    /**
     * Define el valor de la propiedad record.
     * 
     * @param value
     *     allowed object is
     *     {@link IncidenciaCambista }
     *     
     */
    public void setRecord(IncidenciaCambista value) {
        this.record = value;
    }

}
