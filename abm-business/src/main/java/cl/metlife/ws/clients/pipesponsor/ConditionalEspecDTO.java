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
 * <p>Java class for ConditionalEspecDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConditionalEspecDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.Seedwork}Dto">
 *       &lt;sequence>
 *         &lt;element name="ColumnDefinition" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}ColumnDefinitionDTO" minOccurs="0"/>
 *         &lt;element name="ColumnDefinitionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ConditionalEspecId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="FileColumnValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FkBusinessObject" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="IsEnable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LogicalOperation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LogicalSymbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Order" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConditionalEspecDTO", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", propOrder = {
    "columnDefinition",
    "columnDefinitionId",
    "conditionalEspecId",
    "fileColumnValue",
    "fkBusinessObject",
    "isEnable",
    "logicalOperation",
    "logicalSymbol",
    "order"
})
public class ConditionalEspecDTO
    extends Dto
{

    @XmlElementRef(name = "ColumnDefinition", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<ColumnDefinitionDTO> columnDefinition;
    @XmlElement(name = "ColumnDefinitionId")
    protected Long columnDefinitionId;
    @XmlElement(name = "ConditionalEspecId")
    protected Long conditionalEspecId;
    @XmlElementRef(name = "FileColumnValue", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<String> fileColumnValue;
    @XmlElement(name = "FkBusinessObject")
    protected Long fkBusinessObject;
    @XmlElement(name = "IsEnable")
    protected Boolean isEnable;
    @XmlElementRef(name = "LogicalOperation", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<String> logicalOperation;
    @XmlElementRef(name = "LogicalSymbol", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", type = JAXBElement.class)
    protected JAXBElement<String> logicalSymbol;
    @XmlElement(name = "Order")
    protected Long order;

    /**
     * Gets the value of the columnDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ColumnDefinitionDTO }{@code >}
     *     
     */
    public JAXBElement<ColumnDefinitionDTO> getColumnDefinition() {
        return columnDefinition;
    }

    /**
     * Sets the value of the columnDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ColumnDefinitionDTO }{@code >}
     *     
     */
    public void setColumnDefinition(JAXBElement<ColumnDefinitionDTO> value) {
        this.columnDefinition = ((JAXBElement<ColumnDefinitionDTO> ) value);
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
     * Gets the value of the conditionalEspecId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getConditionalEspecId() {
        return conditionalEspecId;
    }

    /**
     * Sets the value of the conditionalEspecId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setConditionalEspecId(Long value) {
        this.conditionalEspecId = value;
    }

    /**
     * Gets the value of the fileColumnValue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFileColumnValue() {
        return fileColumnValue;
    }

    /**
     * Sets the value of the fileColumnValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFileColumnValue(JAXBElement<String> value) {
        this.fileColumnValue = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the fkBusinessObject property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFkBusinessObject() {
        return fkBusinessObject;
    }

    /**
     * Sets the value of the fkBusinessObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFkBusinessObject(Long value) {
        this.fkBusinessObject = value;
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

    /**
     * Gets the value of the logicalOperation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLogicalOperation() {
        return logicalOperation;
    }

    /**
     * Sets the value of the logicalOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLogicalOperation(JAXBElement<String> value) {
        this.logicalOperation = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the logicalSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLogicalSymbol() {
        return logicalSymbol;
    }

    /**
     * Sets the value of the logicalSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLogicalSymbol(JAXBElement<String> value) {
        this.logicalSymbol = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrder(Long value) {
        this.order = value;
    }

}
