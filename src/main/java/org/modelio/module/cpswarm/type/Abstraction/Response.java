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

@objid ("9b7a03bf-eb7c-449c-86ac-b70d704d533c")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "constants",
    "fields"
})
public class Response {
    @objid ("e54b7393-d36b-4132-a206-033bde2f0604")
    @JsonProperty("constants")
    private List<Constant> constants = null;

    /**
     * 
     * (Required)
     */
    @objid ("54b33f44-b547-42ff-bc9b-782b172dcfbb")
    @JsonProperty("fields")
    private List<Field> fields = null;

    @objid ("44ea8bc4-88ff-48e5-985e-f456156998cc")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @objid ("13081236-a066-4cb0-9587-72b9e521a39c")
    @JsonProperty("constants")
    public List<Constant> getConstants() {
        return constants;
    }

    @objid ("af617149-6a6e-4b9f-9e4b-2a25d0350602")
    @JsonProperty("constants")
    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }

    /**
     * (Required)
     */
    @objid ("94baa39e-ab31-4105-9da7-8eb576ad146a")
    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    /**
     * (Required)
     */
    @objid ("b324ca0e-c235-4050-9dbb-9a34efb3d817")
    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @objid ("2e7c4932-0ecb-49ae-a62b-345902dfd20c")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("1520d484-d0db-4376-a9d9-67358733be8b")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
