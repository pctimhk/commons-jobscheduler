//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.07 at 12:07:04 PM CET 
//


package com.sos.auth.rest.permission.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element ref="{}SOSPermissionJoe"/>
 *         &lt;element ref="{}SOSPermissionJoc"/>
 *         &lt;element ref="{}SOSPermissionDashboard"/>
 *         &lt;element ref="{}SOSPermissionEvents"/>
 *         &lt;element ref="{}SOSPermissionJobnet"/>
 *         &lt;element ref="{}SOSPermissionWorkingplan"/>
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{}SOSPermission"/>
 *         &lt;/sequence>
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
    "sosPermissionJoe",
    "sosPermissionJoc",
    "sosPermissionDashboard",
    "sosPermissionEvents",
    "sosPermissionJobnet",
    "sosPermissionWorkingplan",
    "sosPermission"
})
@XmlRootElement(name = "SOSPermissionJid")
public class SOSPermissionJid {

    @XmlElement(name = "SOSPermissionJoe", required = true)
    protected SOSPermissionJoe sosPermissionJoe;
    @XmlElement(name = "SOSPermissionJoc", required = true)
    protected SOSPermissionJoc sosPermissionJoc;
    @XmlElement(name = "SOSPermissionDashboard", required = true)
    protected SOSPermissionDashboard sosPermissionDashboard;
    @XmlElement(name = "SOSPermissionEvents", required = true)
    protected SOSPermissionEvents sosPermissionEvents;
    @XmlElement(name = "SOSPermissionJobnet", required = true)
    protected SOSPermissionJobnet sosPermissionJobnet;
    @XmlElement(name = "SOSPermissionWorkingplan", required = true)
    protected SOSPermissionWorkingplan sosPermissionWorkingplan;
    @XmlElement(name = "SOSPermission")
    protected List<String> sosPermission;

    /**
     * Gets the value of the sosPermissionJoe property.
     * 
     * @return
     *     possible object is
     *     {@link SOSPermissionJoe }
     *     
     */
    public SOSPermissionJoe getSOSPermissionJoe() {
        return sosPermissionJoe;
    }

    /**
     * Sets the value of the sosPermissionJoe property.
     * 
     * @param value
     *     allowed object is
     *     {@link SOSPermissionJoe }
     *     
     */
    public void setSOSPermissionJoe(SOSPermissionJoe value) {
        this.sosPermissionJoe = value;
    }

    /**
     * Gets the value of the sosPermissionJoc property.
     * 
     * @return
     *     possible object is
     *     {@link SOSPermissionJoc }
     *     
     */
    public SOSPermissionJoc getSOSPermissionJoc() {
        return sosPermissionJoc;
    }

    /**
     * Sets the value of the sosPermissionJoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link SOSPermissionJoc }
     *     
     */
    public void setSOSPermissionJoc(SOSPermissionJoc value) {
        this.sosPermissionJoc = value;
    }

    /**
     * Gets the value of the sosPermissionDashboard property.
     * 
     * @return
     *     possible object is
     *     {@link SOSPermissionDashboard }
     *     
     */
    public SOSPermissionDashboard getSOSPermissionDashboard() {
        return sosPermissionDashboard;
    }

    /**
     * Sets the value of the sosPermissionDashboard property.
     * 
     * @param value
     *     allowed object is
     *     {@link SOSPermissionDashboard }
     *     
     */
    public void setSOSPermissionDashboard(SOSPermissionDashboard value) {
        this.sosPermissionDashboard = value;
    }

    /**
     * Gets the value of the sosPermissionEvents property.
     * 
     * @return
     *     possible object is
     *     {@link SOSPermissionEvents }
     *     
     */
    public SOSPermissionEvents getSOSPermissionEvents() {
        return sosPermissionEvents;
    }

    /**
     * Sets the value of the sosPermissionEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link SOSPermissionEvents }
     *     
     */
    public void setSOSPermissionEvents(SOSPermissionEvents value) {
        this.sosPermissionEvents = value;
    }

    /**
     * Gets the value of the sosPermissionJobnet property.
     * 
     * @return
     *     possible object is
     *     {@link SOSPermissionJobnet }
     *     
     */
    public SOSPermissionJobnet getSOSPermissionJobnet() {
        return sosPermissionJobnet;
    }

    /**
     * Sets the value of the sosPermissionJobnet property.
     * 
     * @param value
     *     allowed object is
     *     {@link SOSPermissionJobnet }
     *     
     */
    public void setSOSPermissionJobnet(SOSPermissionJobnet value) {
        this.sosPermissionJobnet = value;
    }

    /**
     * Gets the value of the sosPermissionWorkingplan property.
     * 
     * @return
     *     possible object is
     *     {@link SOSPermissionWorkingplan }
     *     
     */
    public SOSPermissionWorkingplan getSOSPermissionWorkingplan() {
        return sosPermissionWorkingplan;
    }

    /**
     * Sets the value of the sosPermissionWorkingplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link SOSPermissionWorkingplan }
     *     
     */
    public void setSOSPermissionWorkingplan(SOSPermissionWorkingplan value) {
        this.sosPermissionWorkingplan = value;
    }

    /**
     * Gets the value of the sosPermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sosPermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSOSPermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSOSPermission() {
        if (sosPermission == null) {
            sosPermission = new ArrayList<String>();
        }
        return this.sosPermission;
    }

}