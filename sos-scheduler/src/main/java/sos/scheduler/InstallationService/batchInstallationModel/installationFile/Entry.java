//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, vJAXB 2.1.3 in JDK 1.6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2011.03.29 at 11:06:03 AM MESZ
//

package sos.scheduler.InstallationService.batchInstallationModel.installationFile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/** <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="key" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="licence"/>
 *             &lt;enumeration value="licenceOptions"/>
 *             &lt;enumeration value="schedulerAllowedHost"/>
 *             &lt;enumeration value="schedulerHost"/>
 *             &lt;enumeration value="schedulerId"/>
 *             &lt;enumeration value="schedulerPort"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="value" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="4445"/>
 *             &lt;enumeration value="GPL"/>
 *             &lt;enumeration value="SOS-UR-1-L2M-7-22-JQBYQH4"/>
 *             &lt;enumeration value="localhost"/>
 *             &lt;enumeration value="scheduler_id"/>
 *             &lt;enumeration value="ur"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre> */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "entry")
public class Entry {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String key;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String value;

    /** Gets the value of the key property.
     * 
     * @return possible object is {@link String } */
    public String getKey() {
        return key;
    }

    /** Sets the value of the key property.
     * 
     * @param value allowed object is {@link String } */
    public void setKey(String value) {
        this.key = value;
    }

    /** Gets the value of the value property.
     * 
     * @return possible object is {@link String } */
    public String getValue() {
        return value;
    }

    /** Sets the value of the value property.
     * 
     * @param value allowed object is {@link String } */
    public void setValue(String value) {
        this.value = value;
    }

}
