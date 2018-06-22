//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package cl.metlife.ws.clients.pipesponsor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FileMappingDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileMappingDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.Seedwork}Dto">
 *       &lt;sequence>
 *         &lt;element name="BusinessObject" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}BusinessObjectDTO" minOccurs="0"/>
 *         &lt;element name="BusinessObjectId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ConditionalEspecId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="FileMappingId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="FileRelationships" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}ArrayOfFileRelationshipDTO" minOccurs="0"/>
 *         &lt;element name="IsEnable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileMappingDTO", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", propOrder = {
    "businessObject",
    "businessObjectId",
    "conditionalEspecId",
    "fileMappingId",
    "fileRelationships",
    "isEnable"
})
public class FileMappingDTO
    extends Dto
{

    @XmlElementRef(name = "BusinessObject", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<BusinessObjectDTO> businessObject;
    @XmlElement(name = "BusinessObjectId")
    protected Long businessObjectId;
    @XmlElementRef(name = "ConditionalEspecId", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<Long> conditionalEspecId;
    @XmlElement(name = "FileMappingId")
    protected Long fileMappingId;
    @XmlElementRef(name = "FileRelationships", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<ArrayOfFileRelationshipDTO> fileRelationships;
    @XmlElement(name = "IsEnable")
    protected Boolean isEnable;

    /**
     * Gets the value of the businessObject property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BusinessObjectDTO }{@code >}
     *     
     */
    public JAXBElement<BusinessObjectDTO> getBusinessObject() {
        return businessObject;
    }

    /**
     * Sets the value of the businessObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BusinessObjectDTO }{@code >}
     *     
     */
    public void setBusinessObject(JAXBElement<BusinessObjectDTO> value) {
        this.businessObject = ((JAXBElement<BusinessObjectDTO> ) value);
    }

    /**
     * Gets the value of the businessObjectId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBusinessObjectId() {
        return businessObjectId;
    }

    /**
     * Sets the value of the businessObjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBusinessObjectId(Long value) {
        this.businessObjectId = value;
    }

    /**
     * Gets the value of the conditionalEspecId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getConditionalEspecId() {
        return conditionalEspecId;
    }

    /**
     * Sets the value of the conditionalEspecId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setConditionalEspecId(JAXBElement<Long> value) {
        this.conditionalEspecId = ((JAXBElement<Long> ) value);
    }

    /**
     * Gets the value of the fileMappingId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFileMappingId() {
        return fileMappingId;
    }

    /**
     * Sets the value of the fileMappingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFileMappingId(Long value) {
        this.fileMappingId = value;
    }

    /**
     * Gets the value of the fileRelationships property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfFileRelationshipDTO }{@code >}
     *     
     */
    public JAXBElement<ArrayOfFileRelationshipDTO> getFileRelationships() {
        return fileRelationships;
    }

    /**
     * Sets the value of the fileRelationships property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfFileRelationshipDTO }{@code >}
     *     
     */
    public void setFileRelationships(JAXBElement<ArrayOfFileRelationshipDTO> value) {
        this.fileRelationships = ((JAXBElement<ArrayOfFileRelationshipDTO> ) value);
    }

    /**
     * Gets the value of the isEnable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsEnable() {
        return isEnable;
    }

    /**
     * Sets the value of the isEnable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsEnable(Boolean value) {
        this.isEnable = value;
    }

}