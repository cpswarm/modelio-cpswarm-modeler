package org.modelio.module.cpswarm.type.Abstraction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("1d258990-6776-401d-8b7c-ff1adeda1046")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "class",
    "constants",
    "fields"
})
public class MsgDef {
    /**
     * 
     * (Required)
     */
    @objid ("7319fd48-610b-4fb0-8952-455f409c9435")
    @JsonProperty("class")
    private String _class;

    @objid ("fd1efe58-bf37-427b-a473-c45bb703e287")
    @JsonProperty("constants")
    private List<Constant> constants = null;

    @objid ("d5de2397-614d-41e8-9452-7bf35739ea8e")
    @JsonProperty("fields")
    private List<Field> fields = null;

    @objid ("228a9b1b-8442-42be-a855-31f8b7e2aefb")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("23586137-06fd-40da-9b88-3f5854ccc953")
    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    /**
     * (Required)
     */
    @objid ("923ce44f-8193-4444-be79-f7f548b329f6")
    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @objid ("08073485-4e4e-44c7-9348-b961d153d851")
    @JsonProperty("constants")
    public List<Constant> getConstants() {
        return constants;
    }

    @objid ("1c172293-7060-46ad-a2b7-03b1f5b23076")
    @JsonProperty("constants")
    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }

    @objid ("4bf25032-5447-4f26-b4d7-403cbdb22f3d")
    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @objid ("89ac6ad0-0022-4db0-b43b-7bd9f4348970")
    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @objid ("817926d3-d5e8-4863-9b78-245d5cc3aa31")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("9d06dcb6-684e-4066-a9ae-841a68a3cfa6")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
