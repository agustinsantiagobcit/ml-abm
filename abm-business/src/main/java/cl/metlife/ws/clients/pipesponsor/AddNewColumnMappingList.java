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
 *         &lt;element name="columnMappings" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}ArrayOfColumnMappingDTO" minOccurs="0"/>
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
    "columnMappings"
})
@XmlRootElement(name = "AddNewColumnMappingList")
public class AddNewColumnMappingList {

    @XmlElementRef(name = "columnMappings", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfColumnMappingDTO> columnMappings;

    /**
     * Gets the value of the columnMappings property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfColumnMappingDTO }{@code >}
     *     
     */
    public JAXBElement<ArrayOfColumnMappingDTO> getColumnMappings() {
        return columnMappings;
    }

    /**
     * Sets the value of the columnMappings property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfColumnMappingDTO }{@code >}
     *     
     */
    public void setColumnMappings(JAXBElement<ArrayOfColumnMappingDTO> value) {
        this.columnMappings = ((JAXBElement<ArrayOfColumnMappingDTO> ) value);
    }

}
