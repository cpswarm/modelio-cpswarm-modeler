package org.modelio.module.cpswarm.type.Abstraction;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("fb3fd594-ace9-4afe-80f1-050ae7866227")
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
    @objid ("68f676c0-fe78-4644-add7-139d32fb8d87")
    @JsonProperty("topic")
    private String topic;

    /**
     * 
     * (Required)
     */
    @objid ("ca1daa9d-f9dc-4ab9-8fee-490cded867a4")
    @JsonProperty("msg_def")
    private MsgDef msgDef;

    @objid ("42d17e53-6d45-47b6-a037-3b81a1512caf")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("34665395-bed6-47da-bc92-8c815c75e61b")
    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    /**
     * (Required)
     */
    @objid ("92deb916-4df6-4221-8521-08b44cd423f2")
    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * (Required)
     */
    @objid ("4caefd05-85c6-4ff7-b997-bc5f75abdb3f")
    @JsonProperty("msg_def")
    public MsgDef getMsgDef() {
        return msgDef;
    }

    /**
     * (Required)
     */
    @objid ("a6a4162b-e949-4c4d-a452-4cec629c45ce")
    @JsonProperty("msg_def")
    public void setMsgDef(MsgDef msgDef) {
        this.msgDef = msgDef;
    }

    @objid ("eabe8dea-c7ea-4989-a114-30ba36c38e1e")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("099ce588-98aa-4c9c-ac2f-7c96648175af")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
