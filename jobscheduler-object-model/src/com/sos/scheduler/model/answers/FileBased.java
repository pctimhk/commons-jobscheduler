//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.20 at 05:15:15 PM MEZ 
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
 *       &lt;sequence>
 *         &lt;element ref="{}includes" minOccurs="0"/>
 *         &lt;element ref="{}requisites" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="file" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="last_write_time" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="state" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "includes",
    "requisites"
})
@XmlRootElement(name = "file_based")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
public class FileBased implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 810733898721646837L;
	@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected Includes includes;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected Requisites requisites;
    @XmlAttribute(name = "file")
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String file;
    @XmlAttribute(name = "last_write_time")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String lastWriteTime;
    @XmlAttribute(name = "state", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String state;

    /**
     * Gets the value of the includes property.
     * 
     * @return
     *     possible object is
     *     {@link Includes }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public Includes getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Includes }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setIncludes(Includes value) {
        this.includes = value;
    }

    /**
     * Gets the value of the requisites property.
     * 
     * @return
     *     possible object is
     *     {@link Requisites }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public Requisites getRequisites() {
        return requisites;
    }

    /**
     * Sets the value of the requisites property.
     * 
     * @param value
     *     allowed object is
     *     {@link Requisites }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setRequisites(Requisites value) {
        this.requisites = value;
    }

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setFile(String value) {
        this.file = value;
    }

    /**
     * Gets the value of the lastWriteTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getLastWriteTime() {
        return lastWriteTime;
    }

    /**
     * Sets the value of the lastWriteTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setLastWriteTime(String value) {
        this.lastWriteTime = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-01-20T05:15:15+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setState(String value) {
        this.state = value;
    }

}
