
package com.agazin.myapplication.ExchangeModel;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class EUR {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("NumCode")
    @Expose
    private String numCode;
    @SerializedName("CharCode")
    @Expose
    private String charCode;
    @SerializedName("Nominal")
    @Expose
    private Integer nominal;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Value")
    @Expose
    private Double value;
    @SerializedName("Previous")
    @Expose
    private Double previous;

    /**
     * 
     * @return
     *     The iD
     */
    public String getID() {
        return iD;
    }

    /**
     * 
     * @param iD
     *     The ID
     */
    public void setID(String iD) {
        this.iD = iD;
    }

    /**
     * 
     * @return
     *     The numCode
     */
    public String getNumCode() {
        return numCode;
    }

    /**
     * 
     * @param numCode
     *     The NumCode
     */
    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    /**
     * 
     * @return
     *     The charCode
     */
    public String getCharCode() {
        return charCode;
    }

    /**
     * 
     * @param charCode
     *     The CharCode
     */
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    /**
     * 
     * @return
     *     The nominal
     */
    public Integer getNominal() {
        return nominal;
    }

    /**
     * 
     * @param nominal
     *     The Nominal
     */
    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The value
     */
    public Double getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The Value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The previous
     */
    public Double getPrevious() {
        return previous;
    }

    /**
     * 
     * @param previous
     *     The Previous
     */
    public void setPrevious(Double previous) {
        this.previous = previous;
    }

}
