//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3-
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2011.01.17 at 03:00:56 PM MEZ
//

package com.sos.scheduler.model.commands;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.sos.scheduler.model.answers.JSCmdBase;

/** <p>
 * Java class for Show_state complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Show_state">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="what" type="{}String" />
 *       &lt;attribute name="subsystems" type="{}String" />
 *       &lt;attribute name="max_orders" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="max_order_history" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="max_task_history" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="path" type="{}Path" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre> */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Show_state")
@XmlRootElement(name = "show_state")
public class ShowState extends JSCmdBase {

    @XmlAttribute(name = "what")
    protected String what;
    @XmlAttribute(name = "subsystems")
    protected String subsystems;
    @XmlAttribute(name = "max_orders")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxOrders;
    @XmlAttribute(name = "max_order_history")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxOrderHistory;
    @XmlAttribute(name = "max_task_history")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxTaskHistory;
    @XmlAttribute(name = "path")
    protected String path;

    /** Gets the value of the what property.
     * 
     * @return possible object is {@link String } */
    public String getWhat() {
        return what;
    }

    /** Sets the value of the what property.
     * 
     * @param value allowed object is {@link String } */
    public void setWhat(String value) {
        this.what = value;
    }

    /** Gets the value of the subsystems property.
     * 
     * @return possible object is {@link String } */
    public String getSubsystems() {
        return subsystems;
    }

    /** Sets the value of the subsystems property.
     * 
     * @param value allowed object is {@link String } */
    public void setSubsystems(String value) {
        this.subsystems = value;
    }

    /** Gets the value of the maxOrders property.
     * 
     * @return possible object is {@link BigInteger } */
    public BigInteger getMaxOrders() {
        return maxOrders;
    }

    /** Sets the value of the maxOrders property.
     * 
     * @param value allowed object is {@link BigInteger } */
    public void setMaxOrders(BigInteger value) {
        this.maxOrders = value;
    }

    /** Gets the value of the maxOrderHistory property.
     * 
     * @return possible object is {@link BigInteger } */
    public BigInteger getMaxOrderHistory() {
        return maxOrderHistory;
    }

    /** Sets the value of the maxOrderHistory property.
     * 
     * @param value allowed object is {@link BigInteger } */
    public void setMaxOrderHistory(BigInteger value) {
        this.maxOrderHistory = value;
    }

    /** Gets the value of the maxTaskHistory property.
     * 
     * @return possible object is {@link BigInteger } */
    public BigInteger getMaxTaskHistory() {
        return maxTaskHistory;
    }

    /** Sets the value of the maxTaskHistory property.
     * 
     * @param value allowed object is {@link BigInteger } */
    public void setMaxTaskHistory(BigInteger value) {
        this.maxTaskHistory = value;
    }

    /** Gets the value of the path property.
     * 
     * @return possible object is {@link String } */
    public String getPath() {
        return path;
    }

    /** Sets the value of the path property.
     * 
     * @param value allowed object is {@link String } */
    public void setPath(String value) {
        this.path = value;
    }

}
