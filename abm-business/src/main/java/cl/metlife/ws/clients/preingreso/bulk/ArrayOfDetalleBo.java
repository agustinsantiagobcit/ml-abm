//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package cl.metlife.ws.clients.preingreso.bulk;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDetalleBo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDetalleBo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetalleBo" type="{http://preingreso.metlife.cl/}DetalleBo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDetalleBo", namespace = "http://preingreso.metlife.cl/", propOrder = {
    "detalleBo"
})
public class ArrayOfDetalleBo {

    @XmlElement(name = "DetalleBo", nillable = true)
    protected List<DetalleBo> detalleBo;

    /**
     * Gets the value of the detalleBo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleBo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleBo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleBo }
     * 
     * 
     */
    public List<DetalleBo> getDetalleBo() {
        if (detalleBo == null) {
            detalleBo = new ArrayList<DetalleBo>();
        }
        return this.detalleBo;
    }

}
