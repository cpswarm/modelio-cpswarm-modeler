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

@objid ("c8c423bf-6516-4c61-9f7f-6ffa82ebb2a6")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "topic",
    "msg_def"
})
public class Output {
    /**
     * 
     * (Required)
     */
    @objid ("864755f8-85f3-43bf-90c0-3a0bf0fbd5af")
    @JsonProperty("topic")
    private String topic;

    /**
     * 
     * (Required)
     */
    @objid ("e8423f4f-7cbe-4c58-a31d-3072b09d9381")
    @JsonProperty("msg_def")
    private MsgDef msgDef;

    @objid ("af10be54-edbd-42d9-9c2c-09420272786d")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("549015b7-9162-4153-a5fe-72d9cfef61e2")
    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    /**
     * (Required)
     */
    @objid ("32cb8375-00b7-4505-b001-7a593b57b4a7")
    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * (Required)
     */
    @objid ("7a2e939d-b9b1-4154-a0a2-ac727a2fa6b1")
    @JsonProperty("msg_def")
    public MsgDef getMsgDef() {
        return msgDef;
    }

    /**
     * (Required)
     */
    @objid ("c6f7f3b0-5e51-4729-bc8e-b600c080cdd9")
    @JsonProperty("msg_def")
    public void setMsgDef(MsgDef msgDef) {
        this.msgDef = msgDef;
    }

    @objid ("4d4ce2df-b974-4efd-9f8d-77f0e88a2e1c")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("42bb0ea8-e19f-47a5-8eff-62a0a8d24640")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
