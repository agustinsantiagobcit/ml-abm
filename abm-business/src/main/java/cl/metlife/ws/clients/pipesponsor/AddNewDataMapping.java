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
 *         &lt;element name="dataMapping" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}DataMappingDTO" minOccurs="0"/>
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
    "dataMapping"
})
@XmlRootElement(name = "AddNewDataMapping")
public class AddNewDataMapping {

    @XmlElementRef(name = "dataMapping", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<DataMappingDTO> dataMapping;

    /**
     * Gets the value of the dataMapping property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DataMappingDTO }{@code >}
     *     
     */
    public JAXBElement<DataMappingDTO> getDataMapping() {
        return dataMapping;
    }

    /**
     * Sets the value of the dataMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DataMappingDTO }{@code >}
     *     
     */
    public void setDataMapping(JAXBElement<DataMappingDTO> value) {
        this.dataMapping = ((JAXBElement<DataMappingDTO> ) value);
    }

}
