//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package cl.metlife.ws.clients.preingreso.bulk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MovtoBajaBo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MovtoBajaBo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://preingreso.metlife.cl/}MovtoBo">
 *       &lt;sequence>
 *         &lt;element name="FechaBaja" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MovtoBajaBo", namespace = "http://preingreso.metlife.cl/", propOrder = {
    "fechaBaja"
})
public class MovtoBajaBo
    extends MovtoBo
{

    @XmlElement(name = "FechaBaja", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaBaja;

    /**
     * Gets the value of the fechaBaja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Sets the value of the fechaBaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaBaja(XMLGregorianCalendar value) {
        this.fechaBaja = value;
    }

}
