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

@objid ("8ea6f86b-4b75-4c3a-878a-ebe11d14280c")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "constants",
    "fields"
})
public class Request {
    @objid ("368dd018-ac9d-4997-835a-b7536ae89e63")
    @JsonProperty("constants")
    private List<Constant> constants = null;

    /**
     * 
     * (Required)
     */
    @objid ("a3c2e53a-63d8-438a-b137-3f6bb928cd7b")
    @JsonProperty("fields")
    private List<Field> fields = null;

    @objid ("22321404-34c8-482a-88e9-10118dc51b93")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @objid ("0c14416f-caab-40f3-b68c-f02274252782")
    @JsonProperty("constants")
    public List<Constant> getConstants() {
        return constants;
    }

    @objid ("9d6e88c2-194d-4320-920a-205489a00e83")
    @JsonProperty("constants")
    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }

    /**
     * (Required)
     */
    @objid ("c918f689-b431-4bcb-aa56-d2225741eff1")
    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    /**
     * (Required)
     */
    @objid ("f2a5e1d6-f24c-4a21-a8f0-d57674080fa7")
    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @objid ("60185ba3-a4f9-4e32-91b7-f79df075c70e")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("27798ac5-0d4b-4a4f-97a4-203bb79f1585")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
