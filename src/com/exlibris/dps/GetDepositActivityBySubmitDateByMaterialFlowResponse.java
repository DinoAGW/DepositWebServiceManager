
package com.exlibris.dps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDepositActivityBySubmitDateByMaterialFlowResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDepositActivityBySubmitDateByMaterialFlowResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="submitDateResultByMF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDepositActivityBySubmitDateByMaterialFlowResponse", propOrder = {
    "submitDateResultByMF"
})
public class GetDepositActivityBySubmitDateByMaterialFlowResponse {

    protected String submitDateResultByMF;

    /**
     * Gets the value of the submitDateResultByMF property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmitDateResultByMF() {
        return submitDateResultByMF;
    }

    /**
     * Sets the value of the submitDateResultByMF property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmitDateResultByMF(String value) {
        this.submitDateResultByMF = value;
    }

}
