package org.modelio.module.cpswarm.type.Algorithm;

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

@objid ("9f0d64e7-3285-4604-9087-29d41a7df6e7")
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
    @objid ("4f712f17-25a7-4a32-8392-c86b3f00a996")
    @JsonProperty("class")
    private String _class;

    @objid ("79ca5e40-76fd-49cb-b72e-44b7575b124a")
    @JsonProperty("constants")
    private List<Constant> constants = null;

    @objid ("ce6f009b-59ad-495a-a396-5ed9ad63a6ef")
    @JsonProperty("fields")
    private List<Field> fields = null;

    @objid ("779d4588-1321-47fd-ba91-0d9e93312926")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("96dc2e16-c8d4-4913-9c76-3f556c8bc019")
    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    /**
     * (Required)
     */
    @objid ("c79d0ec4-02ba-4eba-8a60-9e16580af8a0")
    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @objid ("c766cc8a-ee6b-4f18-98d0-cbbba4c7883f")
    @JsonProperty("constants")
    public List<Constant> getConstants() {
        return constants;
    }

    @objid ("dd932ffb-6479-4742-8bee-0f393a4eb63c")
    @JsonProperty("constants")
    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }

    @objid ("f557cc40-f449-4f0e-85ea-4e146e50fcd0")
    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @objid ("c325cb05-bf2b-4e9a-bad4-387c281875b6")
    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @objid ("bdeb2953-041a-4326-baa5-0428c29b3fe1")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("6bcad3a6-cfaa-4ddc-b266-ca8d91e4f016")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
