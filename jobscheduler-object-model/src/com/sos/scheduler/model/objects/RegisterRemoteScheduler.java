//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.17 at 03:00:56 PM MEZ 
//


package com.sos.scheduler.model.objects;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="ERROR" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{}String" />
 *       &lt;attribute name="scheduler_id" type="{}String" />
 *       &lt;attribute name="tcp_port" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="udp_port" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="is_cluster_member" type="{}String" />
 *       &lt;attribute name="logoff" type="{}Yes_no" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "error"
})
@XmlRootElement(name = "register_remote_scheduler")
public class RegisterRemoteScheduler extends JSObjBase {

    @XmlElement(name = "ERROR")
    protected RegisterRemoteScheduler.ERROR error;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "scheduler_id")
    protected String schedulerId;
    @XmlAttribute(name = "tcp_port")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger tcpPort;
    @XmlAttribute(name = "udp_port")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger udpPort;
    @XmlAttribute(name = "is_cluster_member")
    protected String isClusterMember;
    @XmlAttribute(name = "logoff")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String logoff;

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterRemoteScheduler.ERROR }
     *     
     */
    public RegisterRemoteScheduler.ERROR getERROR() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterRemoteScheduler.ERROR }
     *     
     */
    public void setERROR(RegisterRemoteScheduler.ERROR value) {
        this.error = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the schedulerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchedulerId() {
        return schedulerId;
    }

    /**
     * Sets the value of the schedulerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchedulerId(String value) {
        this.schedulerId = value;
    }

    /**
     * Gets the value of the tcpPort property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTcpPort() {
        return tcpPort;
    }

    /**
     * Sets the value of the tcpPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTcpPort(BigInteger value) {
        this.tcpPort = value;
    }

    /**
     * Gets the value of the udpPort property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUdpPort() {
        return udpPort;
    }

    /**
     * Sets the value of the udpPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUdpPort(BigInteger value) {
        this.udpPort = value;
    }

    /**
     * Gets the value of the isClusterMember property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsClusterMember() {
        return isClusterMember;
    }

    /**
     * Sets the value of the isClusterMember property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsClusterMember(String value) {
        this.isClusterMember = value;
    }

    /**
     * Gets the value of the logoff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoff() {
        return logoff;
    }

    /**
     * Sets the value of the logoff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoff(String value) {
        this.logoff = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ERROR {


    }

}
