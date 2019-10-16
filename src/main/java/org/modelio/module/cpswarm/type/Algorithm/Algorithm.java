
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "runtime-env",
    "functions"
})
public class Algorithm {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("runtime-env")
    private Object runtimeEnv;
    @JsonProperty("functions")
    private List<Function> functions = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("runtime-env")
    public Object getRuntimeEnv() {
        return runtimeEnv;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("runtime-env")
    public void setRuntimeEnv(Object runtimeEnv) {
        this.runtimeEnv = runtimeEnv;
    }

    @JsonProperty("functions")
    public List<Function> getFunctions() {
        return functions;
    }

    @JsonProperty("functions")
    public void setFunctions(List<Function> functions) {
        this.functions = functions;
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
