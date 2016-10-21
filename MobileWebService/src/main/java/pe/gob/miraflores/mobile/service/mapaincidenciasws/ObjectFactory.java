
package pe.gob.miraflores.mobile.service.mapaincidenciasws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pe.gob.miraflores.mobile.service.mapaincidenciasws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateIncidenciaVoxivaResponse_QNAME = new QName("http://mapaincidenciasws.service.mobile.miraflores.gob.pe/", "createIncidenciaVoxivaResponse");
    private final static QName _CreateIncidenciaInfoCambistaResponse_QNAME = new QName("http://mapaincidenciasws.service.mobile.miraflores.gob.pe/", "createIncidenciaInfoCambistaResponse");
    private final static QName _CreateIncidenciaInfoCambista_QNAME = new QName("http://mapaincidenciasws.service.mobile.miraflores.gob.pe/", "createIncidenciaInfoCambista");
    private final static QName _CreateIncidenciaVoxiva_QNAME = new QName("http://mapaincidenciasws.service.mobile.miraflores.gob.pe/", "createIncidenciaVoxiva");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pe.gob.miraflores.mobile.service.mapaincidenciasws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateIncidenciaInfoCambistaResponse }
     * 
     */
    public CreateIncidenciaInfoCambistaResponse createCreateIncidenciaInfoCambistaResponse() {
        return new CreateIncidenciaInfoCambistaResponse();
    }

    /**
     * Create an instance of {@link CreateIncidenciaVoxivaResponse }
     * 
     */
    public CreateIncidenciaVoxivaResponse createCreateIncidenciaVoxivaResponse() {
        return new CreateIncidenciaVoxivaResponse();
    }

    /**
     * Create an instance of {@link CreateIncidenciaInfoCambista }
     * 
     */
    public CreateIncidenciaInfoCambista createCreateIncidenciaInfoCambista() {
        return new CreateIncidenciaInfoCambista();
    }

    /**
     * Create an instance of {@link CreateIncidenciaVoxiva }
     * 
     */
    public CreateIncidenciaVoxiva createCreateIncidenciaVoxiva() {
        return new CreateIncidenciaVoxiva();
    }

    /**
     * Create an instance of {@link IncidenciaVoxiva }
     * 
     */
    public IncidenciaVoxiva createIncidenciaVoxiva() {
        return new IncidenciaVoxiva();
    }

    /**
     * Create an instance of {@link IncidenciaCambista }
     * 
     */
    public IncidenciaCambista createIncidenciaCambista() {
        return new IncidenciaCambista();
    }

    /**
     * Create an instance of {@link MessageResult }
     * 
     */
    public MessageResult createMessageResult() {
        return new MessageResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateIncidenciaVoxivaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mapaincidenciasws.service.mobile.miraflores.gob.pe/", name = "createIncidenciaVoxivaResponse")
    public JAXBElement<CreateIncidenciaVoxivaResponse> createCreateIncidenciaVoxivaResponse(CreateIncidenciaVoxivaResponse value) {
        return new JAXBElement<CreateIncidenciaVoxivaResponse>(_CreateIncidenciaVoxivaResponse_QNAME, CreateIncidenciaVoxivaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateIncidenciaInfoCambistaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mapaincidenciasws.service.mobile.miraflores.gob.pe/", name = "createIncidenciaInfoCambistaResponse")
    public JAXBElement<CreateIncidenciaInfoCambistaResponse> createCreateIncidenciaInfoCambistaResponse(CreateIncidenciaInfoCambistaResponse value) {
        return new JAXBElement<CreateIncidenciaInfoCambistaResponse>(_CreateIncidenciaInfoCambistaResponse_QNAME, CreateIncidenciaInfoCambistaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateIncidenciaInfoCambista }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mapaincidenciasws.service.mobile.miraflores.gob.pe/", name = "createIncidenciaInfoCambista")
    public JAXBElement<CreateIncidenciaInfoCambista> createCreateIncidenciaInfoCambista(CreateIncidenciaInfoCambista value) {
        return new JAXBElement<CreateIncidenciaInfoCambista>(_CreateIncidenciaInfoCambista_QNAME, CreateIncidenciaInfoCambista.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateIncidenciaVoxiva }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mapaincidenciasws.service.mobile.miraflores.gob.pe/", name = "createIncidenciaVoxiva")
    public JAXBElement<CreateIncidenciaVoxiva> createCreateIncidenciaVoxiva(CreateIncidenciaVoxiva value) {
        return new JAXBElement<CreateIncidenciaVoxiva>(_CreateIncidenciaVoxiva_QNAME, CreateIncidenciaVoxiva.class, null, value);
    }

}
