
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
     * 
     */
    @JsonProperty("class")
    private String _class;
    @JsonProperty("constants")
    private List<Constant> constants = null;
    @JsonProperty("fields")
    private List<Field> fields = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @JsonProperty("constants")
    public List<Constant> getConstants() {
        return constants;
    }

    @JsonProperty("constants")
    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }

    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
