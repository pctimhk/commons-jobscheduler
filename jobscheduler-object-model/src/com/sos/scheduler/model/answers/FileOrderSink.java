//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.20 at 04:00:28 PM MEZ 
//


package com.sos.scheduler.model.answers;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="remove" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "file_order_sink")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
public class FileOrderSink  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3004366144784109967L;
	@XmlAttribute(name = "remove", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String remove;

    /**
     * Gets the value of the remove property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getRemove() {
        return remove;
    }

    /**
     * Sets the value of the remove property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T04:00:28+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setRemove(final String value) {
        remove = value;
    }

}
