//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package cl.metlife.ws.clients.preingreso.bulk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FileNetCodigoGrabarResult" type="{http://preingreso.metlife.cl/}ErrorBo" minOccurs="0"/>
 *         &lt;element name="errorNumero" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="errorDescripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fileNetCodigoGrabarResult",
    "errorNumero",
    "errorDescripcion"
})
@XmlRootElement(name = "FileNetCodigoGrabarResponse")
public class FileNetCodigoGrabarResponse {

    @XmlElement(name = "FileNetCodigoGrabarResult")
    protected ErrorBo fileNetCodigoGrabarResult;
    protected int errorNumero;
    protected String errorDescripcion;

    /**
     * Gets the value of the fileNetCodigoGrabarResult property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorBo }
     *     
     */
    public ErrorBo getFileNetCodigoGrabarResult() {
        return fileNetCodigoGrabarResult;
    }

    /**
     * Sets the value of the fileNetCodigoGrabarResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorBo }
     *     
     */
    public void setFileNetCodigoGrabarResult(ErrorBo value) {
        this.fileNetCodigoGrabarResult = value;
    }

    /**
     * Gets the value of the errorNumero property.
     * 
     */
    public int getErrorNumero() {
        return errorNumero;
    }

    /**
     * Sets the value of the errorNumero property.
     * 
     */
    public void setErrorNumero(int value) {
        this.errorNumero = value;
    }

    /**
     * Gets the value of the errorDescripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDescripcion() {
        return errorDescripcion;
    }

    /**
     * Sets the value of the errorDescripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDescripcion(String value) {
        this.errorDescripcion = value;
    }

}
