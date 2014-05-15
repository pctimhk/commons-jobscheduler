//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.22 at 05:41:11 PM MEZ 
//


package com.sos.scheduler.model.answers;

import java.io.Serializable;
import java.math.BigInteger;

import javax.annotation.Generated;
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
 *         &lt;element ref="{}file_based"/>
 *         &lt;element ref="{}lock.requestor" minOccurs="0"/>
 *         &lt;element ref="{}tasks"/>
 *         &lt;element ref="{}queued_tasks"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element ref="{}ERROR"/>
 *           &lt;element ref="{}order_queue"/>
 *         &lt;/choice>
 *         &lt;element ref="{}log"/>
 *       &lt;/sequence>
 *       &lt;attribute name="all_no_of_tasks" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="all_steps" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="enabled" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="has_description" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="in_period" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="job" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="job_chain_priority" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="log_file" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="next_start_time" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="no_of_no_of_tasks" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="no_of_tasks" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="order" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="process_class" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="state" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fileBased",
    "lockRequestor",
    "tasks",
    "queuedTasks",
    "error",
    "orderQueue",
    "log"
})
@XmlRootElement(name = "job")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
public class Job implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2060617465829352295L;
	@XmlElement(name = "file_based", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected FileBased fileBased;
    @XmlElement(name = "lock.requestor")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected LockRequestor lockRequestor;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected Tasks tasks;
    @XmlElement(name = "queued_tasks", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected QueuedTasks queuedTasks;
    @XmlElement(name = "ERROR")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected ERROR error;
    @XmlElement(name = "order_queue")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected OrderQueue orderQueue;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected Log log;
    @XmlAttribute(name = "all_no_of_tasks", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected BigInteger allNoOfTasks;
    @XmlAttribute(name = "all_steps", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected BigInteger allSteps;
    @XmlAttribute(name = "enabled", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String enabled;
    @XmlAttribute(name = "has_description")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String hasDescription;
    @XmlAttribute(name = "in_period", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String inPeriod;
    @XmlAttribute(name = "job", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String job;
    @XmlAttribute(name = "job_chain_priority")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected BigInteger jobChainPriority;
    @XmlAttribute(name = "log_file", required = true)
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String logFile;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String name;
    @XmlAttribute(name = "next_start_time")
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String nextStartTime;
    @XmlAttribute(name = "no_of_no_of_tasks")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected BigInteger noOfNoOfTasks;
    @XmlAttribute(name = "no_of_tasks")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected BigInteger noOfTasks;
    @XmlAttribute(name = "order", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String order;
    @XmlAttribute(name = "path", required = true)
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String path;
    @XmlAttribute(name = "process_class")
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String processClass;
    @XmlAttribute(name = "state", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String state;
    @XmlAttribute(name = "title")
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    protected String title;

    /**
     * Gets the value of the fileBased property.
     * 
     * @return
     *     possible object is
     *     {@link FileBased }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public FileBased getFileBased() {
        return fileBased;
    }

    /**
     * Sets the value of the fileBased property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileBased }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setFileBased(FileBased value) {
        this.fileBased = value;
    }

    /**
     * Gets the value of the lockRequestor property.
     * 
     * @return
     *     possible object is
     *     {@link LockRequestor }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public LockRequestor getLockRequestor() {
        return lockRequestor;
    }

    /**
     * Sets the value of the lockRequestor property.
     * 
     * @param value
     *     allowed object is
     *     {@link LockRequestor }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setLockRequestor(LockRequestor value) {
        this.lockRequestor = value;
    }

    /**
     * Gets the value of the tasks property.
     * 
     * @return
     *     possible object is
     *     {@link Tasks }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public Tasks getTasks() {
        return tasks;
    }

    /**
     * Sets the value of the tasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tasks }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setTasks(Tasks value) {
        this.tasks = value;
    }

    /**
     * Gets the value of the queuedTasks property.
     * 
     * @return
     *     possible object is
     *     {@link QueuedTasks }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public QueuedTasks getQueuedTasks() {
        return queuedTasks;
    }

    /**
     * Sets the value of the queuedTasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueuedTasks }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setQueuedTasks(QueuedTasks value) {
        this.queuedTasks = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link ERROR }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public ERROR getERROR() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link ERROR }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setERROR(ERROR value) {
        this.error = value;
    }

    /**
     * Gets the value of the orderQueue property.
     * 
     * @return
     *     possible object is
     *     {@link OrderQueue }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public OrderQueue getOrderQueue() {
        return orderQueue;
    }

    /**
     * Sets the value of the orderQueue property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderQueue }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setOrderQueue(OrderQueue value) {
        this.orderQueue = value;
    }

    /**
     * Gets the value of the log property.
     * 
     * @return
     *     possible object is
     *     {@link Log }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public Log getLog() {
        return log;
    }

    /**
     * Sets the value of the log property.
     * 
     * @param value
     *     allowed object is
     *     {@link Log }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setLog(Log value) {
        this.log = value;
    }

    /**
     * Gets the value of the allNoOfTasks property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public BigInteger getAllNoOfTasks() {
        return allNoOfTasks;
    }

    /**
     * Sets the value of the allNoOfTasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setAllNoOfTasks(BigInteger value) {
        this.allNoOfTasks = value;
    }

    /**
     * Gets the value of the allSteps property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public BigInteger getAllSteps() {
        return allSteps;
    }

    /**
     * Sets the value of the allSteps property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setAllSteps(BigInteger value) {
        this.allSteps = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setEnabled(String value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the hasDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getHasDescription() {
        return hasDescription;
    }

    /**
     * Sets the value of the hasDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setHasDescription(String value) {
        this.hasDescription = value;
    }

    /**
     * Gets the value of the inPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getInPeriod() {
        return inPeriod;
    }

    /**
     * Sets the value of the inPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setInPeriod(String value) {
        this.inPeriod = value;
    }

    /**
     * Gets the value of the job property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getJob() {
        return job;
    }

    /**
     * Sets the value of the job property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setJob(String value) {
        this.job = value;
    }

    /**
     * Gets the value of the jobChainPriority property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public BigInteger getJobChainPriority() {
        return jobChainPriority;
    }

    /**
     * Sets the value of the jobChainPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setJobChainPriority(BigInteger value) {
        this.jobChainPriority = value;
    }

    /**
     * Gets the value of the logFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getLogFile() {
        return logFile;
    }

    /**
     * Sets the value of the logFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setLogFile(String value) {
        this.logFile = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the nextStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getNextStartTime() {
        return nextStartTime;
    }

    /**
     * Sets the value of the nextStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setNextStartTime(String value) {
        this.nextStartTime = value;
    }

    /**
     * Gets the value of the noOfNoOfTasks property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public BigInteger getNoOfNoOfTasks() {
        return noOfNoOfTasks;
    }

    /**
     * Sets the value of the noOfNoOfTasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setNoOfNoOfTasks(BigInteger value) {
        this.noOfNoOfTasks = value;
    }

    /**
     * Gets the value of the noOfTasks property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public BigInteger getNoOfTasks() {
        return noOfTasks;
    }

    /**
     * Sets the value of the noOfTasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setNoOfTasks(BigInteger value) {
        this.noOfTasks = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setOrder(String value) {
        this.order = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setPath(String value) {
        this.path = value;
    }

    /**
     * Gets the value of the processClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getProcessClass() {
        return processClass;
    }

    /**
     * Sets the value of the processClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setProcessClass(String value) {
        this.processClass = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-11-22T05:41:11+01:00", comments = "JAXB RI v2.2.3-hudson-jaxb-ri-2.2.3-3-")
    public void setTitle(String value) {
        this.title = value;
    }

}
