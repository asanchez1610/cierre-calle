
package pe.gob.miraflores.mobile.service.mapaincidenciasws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createIncidenciaVoxiva complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createIncidenciaVoxiva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="incidencia" type="{http://mapaincidenciasws.service.mobile.miraflores.gob.pe/}incidenciaVoxiva" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createIncidenciaVoxiva", propOrder = {
    "incidencia"
})
public class CreateIncidenciaVoxiva {

    protected IncidenciaVoxiva incidencia;

    /**
     * Obtiene el valor de la propiedad incidencia.
     * 
     * @return
     *     possible object is
     *     {@link IncidenciaVoxiva }
     *     
     */
    public IncidenciaVoxiva getIncidencia() {
        return incidencia;
    }

    /**
     * Define el valor de la propiedad incidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link IncidenciaVoxiva }
     *     
     */
    public void setIncidencia(IncidenciaVoxiva value) {
        this.incidencia = value;
    }

}
