//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package cl.metlife.ws.clients.preingreso.bulk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MovtoErrorBo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MovtoErrorBo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Titular" type="{http://preingreso.metlife.cl/}TitularBo" minOccurs="0"/>
 *         &lt;element name="Carga" type="{http://preingreso.metlife.cl/}CargaBo" minOccurs="0"/>
 *         &lt;element name="Error" type="{http://preingreso.metlife.cl/}ErrorBo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MovtoErrorBo", namespace = "http://preingreso.metlife.cl/", propOrder = {
    "titular",
    "carga",
    "error"
})
public class MovtoErrorBo {

    @XmlElement(name = "Titular")
    protected TitularBo titular;
    @XmlElement(name = "Carga")
    protected CargaBo carga;
    @XmlElement(name = "Error")
    protected ErrorBo error;

    /**
     * Gets the value of the titular property.
     * 
     * @return
     *     possible object is
     *     {@link TitularBo }
     *     
     */
    public TitularBo getTitular() {
        return titular;
    }

    /**
     * Sets the value of the titular property.
     * 
     * @param value
     *     allowed object is
     *     {@link TitularBo }
     *     
     */
    public void setTitular(TitularBo value) {
        this.titular = value;
    }

    /**
     * Gets the value of the carga property.
     * 
     * @return
     *     possible object is
     *     {@link CargaBo }
     *     
     */
    public CargaBo getCarga() {
        return carga;
    }

    /**
     * Sets the value of the carga property.
     * 
     * @param value
     *     allowed object is
     *     {@link CargaBo }
     *     
     */
    public void setCarga(CargaBo value) {
        this.carga = value;
    }

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

}
