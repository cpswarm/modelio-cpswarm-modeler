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

@objid ("8da5dfb5-4c15-497c-9dfa-d223c82b5ec8")
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
    @objid ("4ff3cc9d-f28d-45fb-95f8-bea97e2a9245")
    @JsonProperty("class")
    private String _class;

    /**
     * 
     * (Required)
     */
    @objid ("5171062b-c8af-4f99-9c5f-d5d7715b2c25")
    @JsonProperty("name")
    private String name;

    @objid ("1b654db2-8ee1-4728-840b-6ef016e2514d")
    @JsonProperty("description")
    private String description;

    @objid ("761efffe-9c2c-4ea7-bc76-6e335cd2e143")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("5acb75e4-c1ee-4672-ad9a-926a64ec942d")
    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    /**
     * (Required)
     */
    @objid ("b4ec918f-527e-4970-b255-a5a516f2ca2c")
    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * (Required)
     */
    @objid ("541e7d48-cd69-4f13-aded-c2adf9e9472b")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * (Required)
     */
    @objid ("c12340eb-2196-4abf-8dfb-1c0520370d1f")
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @objid ("01b25cbe-57e2-4c21-9a27-ab98f3808b00")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @objid ("369acdf4-8eef-47c6-9a9a-b865119d2d4d")
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("dc18f674-838d-4ce0-a217-f6ad2096e2aa")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("b3e79ec9-7ab6-49b3-816e-fab39c0a089d")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
