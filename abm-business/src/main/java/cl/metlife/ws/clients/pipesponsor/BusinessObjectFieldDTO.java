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
 * <p>Java class for BusinessObjectFieldDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessObjectFieldDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.Seedwork}Dto">
 *       &lt;sequence>
 *         &lt;element name="BusinessFieldType" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}BusinessFieldTypeDTO" minOccurs="0"/>
 *         &lt;element name="BusinessFieldTypeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="BusinessObject" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}BusinessObjectDTO" minOccurs="0"/>
 *         &lt;element name="BusinessObjectFieldId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="BusinessObjectId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="BusinessObjectMappings" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}ArrayOfBusinessObjectMappingDTO" minOccurs="0"/>
 *         &lt;element name="ColumnMappings" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}ArrayOfColumnMappingDTO" minOccurs="0"/>
 *         &lt;element name="FieldDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FieldMandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FieldOrder" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
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
@XmlType(name = "BusinessObjectFieldDTO", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", propOrder = {
    "businessFieldType",
    "businessFieldTypeId",
    "businessObject",
    "businessObjectFieldId",
    "businessObjectId",
    "businessObjectMappings",
    "columnMappings",
    "fieldDescription",
    "fieldMandatory",
    "fieldName",
    "fieldOrder",
    "isEnable"
})
public class BusinessObjectFieldDTO
    extends Dto
{

    @XmlElementRef(name = "BusinessFieldType", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<BusinessFieldTypeDTO> businessFieldType;
    @XmlElement(name = "BusinessFieldTypeId")
    protected Long businessFieldTypeId;
    @XmlElementRef(name = "BusinessObject", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<BusinessObjectDTO> businessObject;
    @XmlElement(name = "BusinessObjectFieldId")
    protected Long businessObjectFieldId;
    @XmlElement(name = "BusinessObjectId")
    protected Long businessObjectId;
    @XmlElementRef(name = "BusinessObjectMappings", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<ArrayOfBusinessObjectMappingDTO> businessObjectMappings;
    @XmlElementRef(name = "ColumnMappings", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<ArrayOfColumnMappingDTO> columnMappings;
    @XmlElementRef(name = "FieldDescription", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<String> fieldDescription;
    @XmlElement(name = "FieldMandatory")
    protected Boolean fieldMandatory;
    @XmlElementRef(name = "FieldName", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<String> fieldName;
    @XmlElement(name = "FieldOrder")
    protected Long fieldOrder;
    @XmlElement(name = "IsEnable")
    protected Boolean isEnable;

    /**
     * Gets the value of the businessFieldType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BusinessFieldTypeDTO }{@code >}
     *     
     */
    public JAXBElement<BusinessFieldTypeDTO> getBusinessFieldType() {
        return businessFieldType;
    }

    /**
     * Sets the value of the businessFieldType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BusinessFieldTypeDTO }{@code >}
     *     
     */
    public void setBusinessFieldType(JAXBElement<BusinessFieldTypeDTO> value) {
        this.businessFieldType = ((JAXBElement<BusinessFieldTypeDTO> ) value);
    }

    /**
     * Gets the value of the businessFieldTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBusinessFieldTypeId() {
        return businessFieldTypeId;
    }

    /**
     * Sets the value of the businessFieldTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBusinessFieldTypeId(Long value) {
        this.businessFieldTypeId = value;
    }

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
     * Gets the value of the businessObjectFieldId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBusinessObjectFieldId() {
        return businessObjectFieldId;
    }

    /**
     * Sets the value of the businessObjectFieldId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBusinessObjectFieldId(Long value) {
        this.businessObjectFieldId = value;
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
     * Gets the value of the businessObjectMappings property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBusinessObjectMappingDTO }{@code >}
     *     
     */
    public JAXBElement<ArrayOfBusinessObjectMappingDTO> getBusinessObjectMappings() {
        return businessObjectMappings;
    }

    /**
     * Sets the value of the businessObjectMappings property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBusinessObjectMappingDTO }{@code >}
     *     
     */
    public void setBusinessObjectMappings(JAXBElement<ArrayOfBusinessObjectMappingDTO> value) {
        this.businessObjectMappings = ((JAXBElement<ArrayOfBusinessObjectMappingDTO> ) value);
    }

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

    /**
     * Gets the value of the fieldDescription property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFieldDescription() {
        return fieldDescription;
    }

    /**
     * Sets the value of the fieldDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFieldDescription(JAXBElement<String> value) {
        this.fieldDescription = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the fieldMandatory property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFieldMandatory() {
        return fieldMandatory;
    }

    /**
     * Sets the value of the fieldMandatory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFieldMandatory(Boolean value) {
        this.fieldMandatory = value;
    }

    /**
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFieldName(JAXBElement<String> value) {
        this.fieldName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the fieldOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFieldOrder() {
        return fieldOrder;
    }

    /**
     * Sets the value of the fieldOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFieldOrder(Long value) {
        this.fieldOrder = value;
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
