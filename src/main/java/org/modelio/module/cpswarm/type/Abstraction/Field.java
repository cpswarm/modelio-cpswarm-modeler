package org.modelio.module.cpswarm.type.Abstraction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("fb41ff9e-333a-4828-a6d4-52c8e2060e02")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "class",
    "name",
    "description"
})
public class Field {
    /**
     * 
     * (Required)
     */
    @objid ("62050099-e679-4545-b665-e89bd0f33959")
    @JsonProperty("class")
    private String _class;

    /**
     * 
     * (Required)
     */
    @objid ("78d27476-eedc-4acc-9ca2-405f69da0518")
    @JsonProperty("name")
    private String name;

    @objid ("8e7808e4-9326-4d1c-a53b-63bd48f8dc4f")
    @JsonProperty("description")
    private String description;

    /**
     * (Required)
     */
    @objid ("277ae4a1-64bc-41c4-858c-9f166b975b12")
    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    /**
     * (Required)
     */
    @objid ("d905ec0d-b66b-42a3-a960-ec2400ae194c")
    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * (Required)
     */
    @objid ("9972b2dd-b821-49f9-afd7-80f8b37a8c2e")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * (Required)
     */
    @objid ("723f5cbc-2921-4ad7-86e4-8997d1483ece")
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @objid ("ffd5ecb8-6b73-49c8-993c-d4896a155f08")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @objid ("c03a7a94-e9f3-4cb0-8d56-35c832d0b5d7")
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

}
