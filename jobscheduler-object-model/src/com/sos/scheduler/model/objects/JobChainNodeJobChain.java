//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.17 at 03:00:56 PM MEZ 
//


package com.sos.scheduler.model.objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for job_chain_node.job_chain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="job_chain_node.job_chain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="state" use="required" type="{}String" />
 *       &lt;attribute name="job_chain" type="{}Path" />
 *       &lt;attribute name="next_state" type="{}String" />
 *       &lt;attribute name="error_state" type="{}String" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "job_chain_node.job_chain")
public class JobChainNodeJobChain extends JSObjBase {

    @XmlAttribute(name = "state", required = true)
    protected String state;
    @XmlAttribute(name = "job_chain")
    protected String jobChain;
    @XmlAttribute(name = "next_state")
    protected String nextState;
    @XmlAttribute(name = "error_state")
    protected String errorState;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the jobChain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobChain() {
        return jobChain;
    }

    /**
     * Sets the value of the jobChain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobChain(String value) {
        this.jobChain = value;
    }

    /**
     * Gets the value of the nextState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextState() {
        return nextState;
    }

    /**
     * Sets the value of the nextState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextState(String value) {
        this.nextState = value;
    }

    /**
     * Gets the value of the errorState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorState() {
        return errorState;
    }

    /**
     * Sets the value of the errorState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorState(String value) {
        this.errorState = value;
    }

}
