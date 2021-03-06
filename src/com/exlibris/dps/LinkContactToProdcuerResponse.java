
package com.exlibris.dps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for linkContactToProdcuerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="linkContactToProdcuerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="linkContactToProdcuer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "linkContactToProdcuerResponse", propOrder = {
    "linkContactToProdcuer"
})
public class LinkContactToProdcuerResponse {

    protected String linkContactToProdcuer;

    /**
     * Gets the value of the linkContactToProdcuer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkContactToProdcuer() {
        return linkContactToProdcuer;
    }

    /**
     * Sets the value of the linkContactToProdcuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkContactToProdcuer(String value) {
        this.linkContactToProdcuer = value;
    }

}
