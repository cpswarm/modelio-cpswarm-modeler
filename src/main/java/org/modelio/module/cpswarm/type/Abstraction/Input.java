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

@objid ("a15a6ddd-2820-4733-9aba-f0a5232e248e")
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
    @objid ("0028fe19-4af6-401a-aa91-085284229003")
    @JsonProperty("topic")
    private String topic;

    /**
     * 
     * (Required)
     */
    @objid ("b5c06000-c0de-4544-b61d-685fc957bf5b")
    @JsonProperty("msg_def")
    private MsgDef msgDef;

    @objid ("890970e1-128a-445b-8f00-d0d34db8a890")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("da31f880-f3ca-4013-8ef0-2567a802c11a")
    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    /**
     * (Required)
     */
    @objid ("7c3fa8d2-ca91-4e23-b600-88db14f2979a")
    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * (Required)
     */
    @objid ("b7ecb3e0-9d13-4f14-9302-ccbe0ee61916")
    @JsonProperty("msg_def")
    public MsgDef getMsgDef() {
        return msgDef;
    }

    /**
     * (Required)
     */
    @objid ("851b3e5a-8b35-424e-a33c-c18fda63f805")
    @JsonProperty("msg_def")
    public void setMsgDef(MsgDef msgDef) {
        this.msgDef = msgDef;
    }

    @objid ("6b8140a1-c868-4cd0-b01c-bf56ee683d44")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("9300f4fa-599e-4c03-845d-3508b0d65032")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
