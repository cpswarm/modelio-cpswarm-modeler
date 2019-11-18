package org.modelio.module.cpswarm.type.Algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("034ed05a-efb4-42ea-915e-54e9ed16a3ff")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "inputs",
    "outputs",
    "comm_paradigm"
})
public class Api {
    /**
     * 
     * (Required)
     */
    @objid ("72c8ca16-be01-402f-978d-d5a83c97dc1a")
    @JsonProperty("comm_paradigm")
    private org.modelio.module.cpswarm.type.Algorithm.Api.CommParadigm commParadigm;

    @objid ("5e13db92-8d11-4521-b01c-bbeb85cde96a")
    @JsonProperty("inputs")
    private List<Input> inputs = null;

    @objid ("dfc9bf69-c989-4129-aaad-24d4289bc486")
    @JsonProperty("outputs")
    private List<Output> outputs = null;

    @objid ("173aac00-cb74-4f2d-8d05-c44559d245c1")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @objid ("ff08d8df-84b7-4918-98f5-9b570dcbb8bb")
    @JsonProperty("inputs")
    public List<Input> getInputs() {
        return inputs;
    }

    @objid ("4ad41543-bb67-4aa4-ba7d-12f17558c276")
    @JsonProperty("inputs")
    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    @objid ("e86b37d6-17e8-4ede-bacd-09f27c4310b0")
    @JsonProperty("outputs")
    public List<Output> getOutputs() {
        return outputs;
    }

    @objid ("287b2010-5321-4bf7-b7d3-1bfeb6fbf27b")
    @JsonProperty("outputs")
    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }

    /**
     * (Required)
     */
    @objid ("94fa48f3-1f0d-4e0c-a9a5-06e094821565")
    @JsonProperty("comm_paradigm")
    public org.modelio.module.cpswarm.type.Algorithm.Api.CommParadigm getCommParadigm() {
        return commParadigm;
    }

    /**
     * (Required)
     */
    @objid ("1b41ce6c-7f4d-4e11-b841-94afbc257328")
    @JsonProperty("comm_paradigm")
    public void setCommParadigm(org.modelio.module.cpswarm.type.Algorithm.Api.CommParadigm commParadigm) {
        this.commParadigm = commParadigm;
    }

    @objid ("c349d249-8f08-4ced-9a7e-0bbab2188455")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("0128fd37-ffad-4cc7-b58a-9032cc92d8d5")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @objid ("b083d181-22bd-4b91-9417-baeec9acb954")
    public enum CommParadigm {
        ROSACTION ("rosaction"),
        ROSSERVICE ("rosservice");

        @objid ("75aa6c64-8ccd-4ccf-bdc1-d3bcdacd81ed")
        private final String value;

        @objid ("82ec8181-bec1-47c1-b8fd-80b94c8658e5")
        private static final Map<String, org.modelio.module.cpswarm.type.Algorithm.Api.CommParadigm> CONSTANTS = new HashMap<String, Api.CommParadigm>();

        @objid ("5b41be1a-f0f2-4d38-b85b-a1f2b4f83b58")
        private CommParadigm(String value) {
            this.value = value;
        }

        @objid ("f8ea4544-3e3d-4737-8631-73e2da7a1021")
        @Override
        public String toString() {
            return this.value;
        }

        @objid ("cf2e6509-9da1-45bb-ad4a-fb6e495425c1")
        @JsonValue
        public String value() {
            return this.value;
        }

        @objid ("c6655fce-93d7-4664-806b-86c583bc023f")
        @JsonCreator
        public static org.modelio.module.cpswarm.type.Algorithm.Api.CommParadigm fromValue(String value) {
            Api.CommParadigm constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }


static {
    for (Api.CommParadigm c: values()) {
        CONSTANTS.put(c.value, c);
    }
}
    }

}
