//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package cl.metlife.ws.clients.preingreso.bulk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BulkDetalleBo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BulkDetalleBo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Error" type="{http://preingreso.metlife.cl/}ErrorBo" minOccurs="0"/>
 *         &lt;element name="Detalles" type="{http://preingreso.metlife.cl/}ArrayOfDetalleBo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkDetalleBo", namespace = "http://preingreso.metlife.cl/", propOrder = {
    "error",
    "detalles"
})
public class BulkDetalleBo {

    @XmlElement(name = "Error")
    protected ErrorBo error;
    @XmlElement(name = "Detalles")
    protected ArrayOfDetalleBo detalles;

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorBo }
     *     
     */
    public ErrorBo getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorBo }
     *     
     */
    public void setError(ErrorBo value) {
        this.error = value;
    }

    /**
     * Gets the value of the detalles property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDetalleBo }
     *     
     */
    public ArrayOfDetalleBo getDetalles() {
        return detalles;
    }

    /**
     * Sets the value of the detalles property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDetalleBo }
     *     
     */
    public void setDetalles(ArrayOfDetalleBo value) {
        this.detalles = value;
    }

}
