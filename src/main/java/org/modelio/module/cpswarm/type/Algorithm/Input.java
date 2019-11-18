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

@objid ("3a33fa80-5d47-42dc-a024-5fbdaae98cdf")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "topic",
    "msg_def"
})
public class Input {
    /**
     * 
     * (Required)
     */
    @objid ("483692b8-40d1-4bc0-a65b-13a286649b29")
    @JsonProperty("topic")
    private String topic;

    /**
     * 
     * (Required)
     */
    @objid ("21998db5-cd97-4a98-9513-a42e262f8b80")
    @JsonProperty("msg_def")
    private MsgDef msgDef;

    @objid ("4f334e0a-b8b8-44c0-8941-53d23cded8d3")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("a2f04771-38a6-4ccc-939a-03479926390e")
    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    /**
     * (Required)
     */
    @objid ("e4fad547-54ac-4674-8c78-3f9381f2ff93")
    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * (Required)
     */
    @objid ("9f7bb7a3-990c-4f24-8744-513a42cbe7f3")
    @JsonProperty("msg_def")
    public MsgDef getMsgDef() {
        return msgDef;
    }

    /**
     * (Required)
     */
    @objid ("2efa9110-b942-4167-9872-90a6f19608cd")
    @JsonProperty("msg_def")
    public void setMsgDef(MsgDef msgDef) {
        this.msgDef = msgDef;
    }

    @objid ("ced49369-1b68-4c19-b8e8-e9a025e88bc7")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("695699e7-cd39-4469-bbb6-76b64bfaf615")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
