//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package cl.metlife.ws.clients.pipesponsor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfColumnDefinitionLzDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfColumnDefinitionLzDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ColumnDefinitionLzDTO" type="{http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO}ColumnDefinitionLzDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfColumnDefinitionLzDTO", namespace = "http://schemas.datacontract.org/2004/07/SmartGear.MetLife.Srp.Application.MainBoundedContext.DTO", propOrder = {
    "columnDefinitionLzDTO"
})
public class ArrayOfColumnDefinitionLzDTO {

    @XmlElement(name = "ColumnDefinitionLzDTO", nillable = true)
    protected List<ColumnDefinitionLzDTO> columnDefinitionLzDTO;

    /**
     * Gets the value of the columnDefinitionLzDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the columnDefinitionLzDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColumnDefinitionLzDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ColumnDefinitionLzDTO }
     * 
     * 
     */
    public List<ColumnDefinitionLzDTO> getColumnDefinitionLzDTO() {
        if (columnDefinitionLzDTO == null) {
            columnDefinitionLzDTO = new ArrayList<ColumnDefinitionLzDTO>();
        }
        return this.columnDefinitionLzDTO;
    }

}
