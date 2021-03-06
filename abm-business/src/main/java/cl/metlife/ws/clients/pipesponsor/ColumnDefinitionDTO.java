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
 * <p>Java class for ColumnDefinitionDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ColumnDefinitionDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.Seedwork}Dto">
 *       &lt;sequence>
 *         &lt;element name="ColumnAllowBlanks" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ColumnDataTypeId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ColumnDefinitionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ColumnFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ColumnIsKey" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ColumnIsMandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ColumnMappings" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}ArrayOfColumnMappingDTO" minOccurs="0"/>
 *         &lt;element name="ColumnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ColumnNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ColumnOrder" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ColumnWidth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ConditionalEspecs" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}ArrayOfConditionalEspecDTO" minOccurs="0"/>
 *         &lt;element name="FileDefinitionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="FileDescriptionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "ColumnDefinitionDTO", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", propOrder = {
    "columnAllowBlanks",
    "columnDataTypeId",
    "columnDefinitionId",
    "columnFormat",
    "columnIsKey",
    "columnIsMandatory",
    "columnMappings",
    "columnName",
    "columnNotes",
    "columnOrder",
    "columnWidth",
    "conditionalEspecs",
    "fileDefinitionId",
    "fileDescriptionName",
    "fileRelationships",
    "isEnable"
})
public class ColumnDefinitionDTO
    extends Dto
{

    @XmlElement(name = "ColumnAllowBlanks")
    protected Boolean columnAllowBlanks;
    @XmlElement(name = "ColumnDataTypeId")
    protected Integer columnDataTypeId;
    @XmlElement(name = "ColumnDefinitionId")
    protected Long columnDefinitionId;
    @XmlElementRef(name = "ColumnFormat", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<String> columnFormat;
    @XmlElement(name = "ColumnIsKey")
    protected Boolean columnIsKey;
    @XmlElement(name = "ColumnIsMandatory")
    protected Boolean columnIsMandatory;
    @XmlElementRef(name = "ColumnMappings", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<ArrayOfColumnMappingDTO> columnMappings;
    @XmlElementRef(name = "ColumnName", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<String> columnName;
    @XmlElementRef(name = "ColumnNotes", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<String> columnNotes;
    @XmlElement(name = "ColumnOrder")
    protected Long columnOrder;
    @XmlElement(name = "ColumnWidth")
    protected Integer columnWidth;
    @XmlElementRef(name = "ConditionalEspecs", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<ArrayOfConditionalEspecDTO> conditionalEspecs;
    @XmlElement(name = "FileDefinitionId")
    protected Long fileDefinitionId;
    @XmlElementRef(name = "FileDescriptionName", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<String> fileDescriptionName;
    @XmlElementRef(name = "FileRelationships", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<ArrayOfFileRelationshipDTO> fileRelationships;
    @XmlElement(name = "IsEnable")
    protected Boolean isEnable;

    /**
     * Gets the value of the columnAllowBlanks property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isColumnAllowBlanks() {
        return columnAllowBlanks;
    }

    /**
     * Sets the value of the columnAllowBlanks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setColumnAllowBlanks(Boolean value) {
        this.columnAllowBlanks = value;
    }

    /**
     * Gets the value of the columnDataTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getColumnDataTypeId() {
        return columnDataTypeId;
    }

    /**
     * Sets the value of the columnDataTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setColumnDataTypeId(Integer value) {
        this.columnDataTypeId = value;
    }

    /**
     * Gets the value of the columnDefinitionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getColumnDefinitionId() {
        return columnDefinitionId;
    }

    /**
     * Sets the value of the columnDefinitionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setColumnDefinitionId(Long value) {
        this.columnDefinitionId = value;
    }

    /**
     * Gets the value of the columnFormat property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getColumnFormat() {
        return columnFormat;
    }

    /**
     * Sets the value of the columnFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setColumnFormat(JAXBElement<String> value) {
        this.columnFormat = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the columnIsKey property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isColumnIsKey() {
        return columnIsKey;
    }

    /**
     * Sets the value of the columnIsKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setColumnIsKey(Boolean value) {
        this.columnIsKey = value;
    }

    /**
     * Gets the value of the columnIsMandatory property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isColumnIsMandatory() {
        return columnIsMandatory;
    }

    /**
     * Sets the value of the columnIsMandatory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setColumnIsMandatory(Boolean value) {
        this.columnIsMandatory = value;
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
     * Gets the value of the columnName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getColumnName() {
        return columnName;
    }

    /**
     * Sets the value of the columnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setColumnName(JAXBElement<String> value) {
        this.columnName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the columnNotes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getColumnNotes() {
        return columnNotes;
    }

    /**
     * Sets the value of the columnNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setColumnNotes(JAXBElement<String> value) {
        this.columnNotes = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the columnOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getColumnOrder() {
        return columnOrder;
    }

    /**
     * Sets the value of the columnOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setColumnOrder(Long value) {
        this.columnOrder = value;
    }

    /**
     * Gets the value of the columnWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getColumnWidth() {
        return columnWidth;
    }

    /**
     * Sets the value of the columnWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setColumnWidth(Integer value) {
        this.columnWidth = value;
    }

    /**
     * Gets the value of the conditionalEspecs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfConditionalEspecDTO }{@code >}
     *     
     */
    public JAXBElement<ArrayOfConditionalEspecDTO> getConditionalEspecs() {
        return conditionalEspecs;
    }

    /**
     * Sets the value of the conditionalEspecs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfConditionalEspecDTO }{@code >}
     *     
     */
    public void setConditionalEspecs(JAXBElement<ArrayOfConditionalEspecDTO> value) {
        this.conditionalEspecs = ((JAXBElement<ArrayOfConditionalEspecDTO> ) value);
    }

    /**
     * Gets the value of the fileDefinitionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFileDefinitionId() {
        return fileDefinitionId;
    }

    /**
     * Sets the value of the fileDefinitionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFileDefinitionId(Long value) {
        this.fileDefinitionId = value;
    }

    /**
     * Gets the value of the fileDescriptionName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFileDescriptionName() {
        return fileDescriptionName;
    }

    /**
     * Sets the value of the fileDescriptionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFileDescriptionName(JAXBElement<String> value) {
        this.fileDescriptionName = ((JAXBElement<String> ) value);
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
