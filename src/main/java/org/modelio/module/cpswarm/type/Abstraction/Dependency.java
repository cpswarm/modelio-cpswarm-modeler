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

@objid ("3be6e5d8-bdbc-4675-a7be-bbca5619d4e9")
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
    @objid ("1fbec340-077d-425c-b11c-c3f719514fcc")
    @JsonProperty("topic")
    private String topic;

    /**
     * 
     * (Required)
     */
    @objid ("653bfc97-4bcd-449c-ab5a-9961d91abdb1")
    @JsonProperty("msg_def")
    private MsgDef msgDef;

    @objid ("4b4cf271-72b5-4da0-a8f1-25c852163a04")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("77bf6903-8eb7-4d3a-9d47-d60779558ff6")
    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    /**
     * (Required)
     */
    @objid ("205efb82-ad6c-43ab-9362-bb497c531106")
    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * (Required)
     */
    @objid ("980d64c6-a85f-45a0-b3b7-ba17c0ee6c08")
    @JsonProperty("msg_def")
    public MsgDef getMsgDef() {
        return msgDef;
    }

    /**
     * (Required)
     */
    @objid ("77de0c06-61ef-4b5a-91b7-129dc8cbfc15")
    @JsonProperty("msg_def")
    public void setMsgDef(MsgDef msgDef) {
        this.msgDef = msgDef;
    }

    @objid ("9f682554-c55f-461b-bcc0-14588230cb83")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("b7f282a9-ab15-427b-a95e-9fa967056a98")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
