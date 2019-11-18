package org.modelio.module.cpswarm.type.Abstraction;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("27daa9ce-f1be-4f80-9d93-e376cd70844a")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "class",
    "name",
    "value",
    "description"
})
public class Constant {
    /**
     * 
     * (Required)
     */
    @objid ("7f36038c-9ce8-4f01-8bb1-c96cb01cf2fa")
    @JsonProperty("class")
    private String _class;

    /**
     * 
     * (Required)
     */
    @objid ("83dbd789-e470-4ab3-8776-d97a847582f6")
    @JsonProperty("name")
    private String name;

    /**
     * 
     * (Required)
     */
    @objid ("498c15cc-4a3c-460c-adb0-81e206698f53")
    @JsonProperty("value")
    private Double value;

    @objid ("f19f9dcb-3953-4996-bb58-2991d1251362")
    @JsonProperty("description")
    private String description;

    @objid ("09e59189-4d6f-4d68-8e53-bc2c37d05876")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("caef205c-da26-41d9-9674-d8d855782ddb")
    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    /**
     * (Required)
     */
    @objid ("9b9aa089-cc46-4c8f-b716-0acf0e105ce1")
    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * (Required)
     */
    @objid ("0a4bb373-5bab-4a7d-931c-dd0a673d914d")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * (Required)
     */
    @objid ("453ae68b-d923-4692-a2ed-a2c370f2dc97")
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * (Required)
     */
    @objid ("17e2542d-7b61-4df0-a726-864fdac9207c")
    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    /**
     * (Required)
     */
    @objid ("bc70056f-be42-44d2-88e4-1e21cdc8fed3")
    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }

    @objid ("e3ff807f-b65a-460d-917a-4797f21d3463")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @objid ("00784a64-4189-4b99-810b-7831298529b2")
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("ee565357-85ec-4a88-aadb-86592caf5d6d")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("d674b819-8ab4-47d1-a138-b88cd27a86f9")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
