//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package cl.metlife.ws.clients.pipesponsor;

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
 *         &lt;element name="AddNewBusinessObjectFieldListResult" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "addNewBusinessObjectFieldListResult"
})
@XmlRootElement(name = "AddNewBusinessObjectFieldListResponse")
public class AddNewBusinessObjectFieldListResponse {

    @XmlElement(name = "AddNewBusinessObjectFieldListResult")
    protected Integer addNewBusinessObjectFieldListResult;

    /**
     * Gets the value of the addNewBusinessObjectFieldListResult property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAddNewBusinessObjectFieldListResult() {
        return addNewBusinessObjectFieldListResult;
    }

    /**
     * Sets the value of the addNewBusinessObjectFieldListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAddNewBusinessObjectFieldListResult(Integer value) {
        this.addNewBusinessObjectFieldListResult = value;
    }

}
