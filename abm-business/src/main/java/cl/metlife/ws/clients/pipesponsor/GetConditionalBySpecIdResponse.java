//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package cl.metlife.ws.clients.pipesponsor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="GetConditionalBySpecIdResult" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}ConditionalSpecSummaryDTO" minOccurs="0"/>
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
    "getConditionalBySpecIdResult"
})
@XmlRootElement(name = "GetConditionalBySpecIdResponse")
public class GetConditionalBySpecIdResponse {

    @XmlElementRef(name = "GetConditionalBySpecIdResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ConditionalSpecSummaryDTO> getConditionalBySpecIdResult;

    /**
     * Gets the value of the getConditionalBySpecIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ConditionalSpecSummaryDTO }{@code >}
     *     
     */
    public JAXBElement<ConditionalSpecSummaryDTO> getGetConditionalBySpecIdResult() {
        return getConditionalBySpecIdResult;
    }

    /**
     * Sets the value of the getConditionalBySpecIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ConditionalSpecSummaryDTO }{@code >}
     *     
     */
    public void setGetConditionalBySpecIdResult(JAXBElement<ConditionalSpecSummaryDTO> value) {
        this.getConditionalBySpecIdResult = ((JAXBElement<ConditionalSpecSummaryDTO> ) value);
    }

}
