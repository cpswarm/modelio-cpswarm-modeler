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

@objid ("cd5ff549-5340-4e12-9a4c-4c4db5fee36a")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "runtime-env",
    "functions"
})
public class Algorithm {
    /**
     * 
     * (Required)
     */
    @objid ("ae2f358f-202f-4738-8cab-96a1b69e171e")
    @JsonProperty("runtime-env")
    private Object runtimeEnv;

    @objid ("41fcf379-c5cc-45c4-8ddd-d44e8b673d37")
    @JsonProperty("functions")
    private List<Function> functions = null;

    @objid ("445ed982-c911-46c1-909a-64d48c50236c")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("0af7f51b-5e1c-4560-8cad-f77a94677a4b")
    @JsonProperty("runtime-env")
    public Object getRuntimeEnv() {
        return runtimeEnv;
    }

    /**
     * (Required)
     */
    @objid ("263b8a66-0199-468f-9e4b-09f9a6523644")
    @JsonProperty("runtime-env")
    public void setRuntimeEnv(Object runtimeEnv) {
        this.runtimeEnv = runtimeEnv;
    }

    @objid ("969619b0-1869-4f61-b89c-f20bd121cb38")
    @JsonProperty("functions")
    public List<Function> getFunctions() {
        return functions;
    }

    @objid ("2e8d21f7-b356-446d-b616-6116ffe91df6")
    @JsonProperty("functions")
    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    @objid ("a1ccb1d5-782f-4608-b599-7813dbbee2f4")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("14ba4afb-e8e4-487e-b9c3-405fcfc72dd6")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
