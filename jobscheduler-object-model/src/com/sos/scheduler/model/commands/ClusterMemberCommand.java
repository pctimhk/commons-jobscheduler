//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.17 at 03:00:56 PM MEZ 
//


package com.sos.scheduler.model.commands;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sos.scheduler.model.answers.JSCmdBase;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="terminate">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="timeout" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="restart" type="{}Yes_no" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "terminate"
})
@XmlRootElement(name = "cluster_member_command")
public class ClusterMemberCommand extends JSCmdBase {

    protected ClusterMemberCommand.Terminate terminate;

    /**
     * Gets the value of the terminate property.
     * 
     * @return
     *     possible object is
     *     {@link ClusterMemberCommand.Terminate }
     *     
     */
    public ClusterMemberCommand.Terminate getTerminate() {
        return terminate;
    }

    /**
     * Sets the value of the terminate property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClusterMemberCommand.Terminate }
     *     
     */
    public void setTerminate(ClusterMemberCommand.Terminate value) {
        this.terminate = value;
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
     *       &lt;attribute name="timeout" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="restart" type="{}Yes_no" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Terminate {

        @XmlAttribute(name = "timeout")
        protected BigInteger timeout;
        @XmlAttribute(name = "restart")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String restart;

        /**
         * Gets the value of the timeout property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTimeout() {
            return timeout;
        }

        /**
         * Sets the value of the timeout property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTimeout(BigInteger value) {
            this.timeout = value;
        }

        /**
         * Gets the value of the restart property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRestart() {
            return restart;
        }

        /**
         * Sets the value of the restart property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRestart(String value) {
            this.restart = value;
        }

    }

}
