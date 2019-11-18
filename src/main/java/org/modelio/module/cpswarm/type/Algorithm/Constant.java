package org.modelio.module.cpswarm.type.Algorithm;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7ecf368f-3250-4bd5-a2ca-b1854892d8f3")
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
    @objid ("cbe31352-bcbb-4a8e-902c-a24612fa5f52")
    @JsonProperty("class")
    private String _class;

    /**
     * 
     * (Required)
     */
    @objid ("fab5ff5d-9598-42e1-afea-aebe48332f30")
    @JsonProperty("name")
    private String name;

    /**
     * 
     * (Required)
     */
    @objid ("2e4a6d83-1baa-4e9b-89ab-324250a6143a")
    @JsonProperty("value")
    private Double value;

    @objid ("22fe6bab-3c4f-4d73-9c7c-f9aa5476db49")
    @JsonProperty("description")
    private String description;

    @objid ("ea10ee7f-aa36-4c97-9338-1a0e63060fd4")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("080c0896-f093-4258-acd8-15f6076d7593")
    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    /**
     * (Required)
     */
    @objid ("84a57208-4d5a-4045-bd2f-e1f7f3ff85d1")
    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * (Required)
     */
    @objid ("ee78f00b-1975-43e9-a955-9395fe36d293")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * (Required)
     */
    @objid ("1fa48011-8468-4945-8bc3-e706ea977246")
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * (Required)
     */
    @objid ("c3afb468-5a8c-4898-a39e-f24aaf6ddce7")
    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    /**
     * (Required)
     */
    @objid ("a9db4efe-f189-415c-a5d2-5e7fc3593f58")
    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }

    @objid ("23055288-1d4e-4b3c-952c-c71f26a15680")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @objid ("f625c0a0-25b4-4057-a782-211f11ad5849")
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("34c989f1-70c1-4775-b20a-5a8cf274b041")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("f5bd6c2c-a0dc-4c8a-8827-a699e6fc0bc4")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
