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

@objid ("4c0ca21f-0da2-4d9e-b5f9-504e33e6de31")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "inputs",
    "outputs",
    "services"
})
public class Api {
    @objid ("7245c3df-dda1-4b3a-bf13-fd267b740a33")
    @JsonProperty("inputs")
    private List<Input> inputs = null;

    @objid ("ae39fe79-f4d2-43c2-9690-1e693b9156e0")
    @JsonProperty("outputs")
    private List<Output> outputs = null;

    @objid ("b283a6ec-457a-42de-bf47-745494027077")
    @JsonProperty("services")
    private List<Service> services = null;

    @objid ("eb8c8bda-4365-42f9-bd50-42a97d448aae")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @objid ("0ec40a73-53e2-4a5b-9a0d-541d9fdcb6f1")
    @JsonProperty("inputs")
    public List<Input> getInputs() {
        return inputs;
    }

    @objid ("9deb5910-6b98-4bb8-8b88-1c655cd3870c")
    @JsonProperty("inputs")
    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    @objid ("cbb4c306-24cb-4cd0-8364-31ba5b51507d")
    @JsonProperty("outputs")
    public List<Output> getOutputs() {
        return outputs;
    }

    @objid ("e16d37dd-e43b-44e6-8d1b-426dda411732")
    @JsonProperty("outputs")
    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }

    @objid ("1885448e-171e-467b-800f-eb3bcd010f39")
    @JsonProperty("services")
    public List<Service> getServices() {
        return services;
    }

    @objid ("d28a1078-9f3f-4520-b8c3-8e2dfacc6431")
    @JsonProperty("services")
    public void setServices(List<Service> services) {
        this.services = services;
    }

    @objid ("b75648f5-1e0b-427b-a457-3b4e5a988562")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("24d7dfc1-9d19-465a-b819-f7d8db2efd36")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
