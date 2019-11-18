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

@objid ("dacd468a-c976-4b3d-9733-b80467859fb4")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "runtime-env",
    "sensors/actuators",
    "functions"
})
public class Abstraction {
    /**
     * 
     * (Required)
     */
    @objid ("b9c5bfc6-9c9a-4eda-98f0-1a2b79f1d92f")
    @JsonProperty("runtime-env")
    private Object runtimeEnv;

    @objid ("8542e263-e739-4ddb-ac5a-3ae849d9e4ec")
    @JsonProperty("sensors/actuators")
    private List<SensorsActuator> sensorsActuators = null;

    @objid ("6debdf3e-795a-4137-9d2e-83e0f56704b5")
    @JsonProperty("functions")
    private List<Function> functions = null;

    @objid ("c73fcb66-5fc6-4178-94f4-a7f2361d7f10")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("296dd706-f833-4231-aa98-af174652cbd8")
    @JsonProperty("runtime-env")
    public Object getRuntimeEnv() {
        return runtimeEnv;
    }

    /**
     * (Required)
     */
    @objid ("50a4f525-c609-4b3e-a1f2-02aacd94e170")
    @JsonProperty("runtime-env")
    public void setRuntimeEnv(Object runtimeEnv) {
        this.runtimeEnv = runtimeEnv;
    }

    @objid ("40574ccf-ab49-4689-b606-6bfc50e4fda3")
    @JsonProperty("sensors/actuators")
    public List<SensorsActuator> getSensorsActuators() {
        return sensorsActuators;
    }

    @objid ("4ac40db4-040e-4e1d-b7d4-eb577e659070")
    @JsonProperty("sensors/actuators")
    public void setSensorsActuators(List<SensorsActuator> sensorsActuators) {
        this.sensorsActuators = sensorsActuators;
    }

    @objid ("46b36f2e-078c-4dcf-b0f9-2638ff4bc1e7")
    @JsonProperty("functions")
    public List<Function> getFunctions() {
        return functions;
    }

    @objid ("9aa1ef56-be72-4721-a61a-10099da386f6")
    @JsonProperty("functions")
    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    @objid ("269808b8-4151-43d7-ae2a-baa9769bdc1a")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("a4cb93cc-8091-43ff-902b-90dc517c9255")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
