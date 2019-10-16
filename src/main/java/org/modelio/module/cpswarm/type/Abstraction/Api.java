
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
    "inputs",
    "outputs",
    "services"
})
public class Api {

    @JsonProperty("inputs")
    private List<Input> inputs = null;
    @JsonProperty("outputs")
    private List<Output> outputs = null;
    @JsonProperty("services")
    private List<Service> services = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("inputs")
    public List<Input> getInputs() {
        return inputs;
    }

    @JsonProperty("inputs")
    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    @JsonProperty("outputs")
    public List<Output> getOutputs() {
        return outputs;
    }

    @JsonProperty("outputs")
    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }

    @JsonProperty("services")
    public List<Service> getServices() {
        return services;
    }

    @JsonProperty("services")
    public void setServices(List<Service> services) {
        this.services = services;
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
