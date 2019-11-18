package org.modelio.module.cpswarm.type.Algorithm;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("62bfc66d-d5eb-49b8-860f-85e95ce598eb")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "topic",
    "msg_def"
})
public class Dependency {
    /**
     * 
     * (Required)
     */
    @objid ("fe2839aa-2d4f-4431-8819-a161c3e65e89")
    @JsonProperty("topic")
    private String topic;

    /**
     * 
     * (Required)
     */
    @objid ("a9111b4b-ee7b-4bf7-99cf-1dcb7e3eb5c9")
    @JsonProperty("msg_def")
    private MsgDef msgDef;

    @objid ("f3d38a9b-cff4-4c55-87bc-f02416125059")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("0e3c96e3-ce68-46af-9d4b-671b5c86b887")
    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    /**
     * (Required)
     */
    @objid ("78d00528-e5dc-4d28-a0bd-092edf5cb9d6")
    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * (Required)
     */
    @objid ("be7ec647-12dd-4c4f-bbe3-88551355f87a")
    @JsonProperty("msg_def")
    public MsgDef getMsgDef() {
        return msgDef;
    }

    /**
     * (Required)
     */
    @objid ("b647db2a-f665-4724-b942-9df34d18be42")
    @JsonProperty("msg_def")
    public void setMsgDef(MsgDef msgDef) {
        this.msgDef = msgDef;
    }

    @objid ("8ae0f356-09cd-4f5c-8d2b-77297fbd4afc")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("882af2f7-ede7-4a92-9267-3e9246eb5e53")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
