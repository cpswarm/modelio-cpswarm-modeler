package org.modelio.module.cpswarm.type.Abstraction;

import java.util.HashMap;
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

@objid ("61036d00-96b5-40fa-b9ed-6ec08a84824e")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "category",
    "api"
})
public class SensorsActuator {
    /**
     * 
     * (Required)
     */
    @objid ("80dc91e0-42c7-4593-8f01-0ba6661c88fb")
    @JsonProperty("name")
    private String name;

    /**
     * 
     * (Required)
     */
    @objid ("603faa2f-dcca-49fe-a74e-246c3f524a98")
    @JsonProperty("description")
    private String description;

    /**
     * 
     * (Required)
     */
    @objid ("e7e20b8b-01b7-4d0b-a545-877ed0010c46")
    @JsonProperty("category")
    private org.modelio.module.cpswarm.type.Abstraction.SensorsActuator.Category category;

    /**
     * 
     * (Required)
     */
    @objid ("55ee5449-5e71-48f7-b1f6-a751f754a11d")
    @JsonProperty("api")
    private Api api;

    @objid ("5575c3f2-b183-4f71-8aa0-5920c65fbb7b")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("df8c7428-bfc8-4511-bfc4-5b3a1424c284")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * (Required)
     */
    @objid ("f9f75bdc-7c85-4f01-9f1e-f249a1859ebb")
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * (Required)
     */
    @objid ("c786cf49-a2d6-4442-98b3-3e534727c3f5")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * (Required)
     */
    @objid ("073b9e52-bd77-429a-b7a1-081a36d8c71b")
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * (Required)
     */
    @objid ("11e1dafd-777f-4a94-be1f-f70e83142297")
    @JsonProperty("category")
    public org.modelio.module.cpswarm.type.Abstraction.SensorsActuator.Category getCategory() {
        return category;
    }

    /**
     * (Required)
     */
    @objid ("83a42115-f6a2-4dd4-9038-db8478cf99b1")
    @JsonProperty("category")
    public void setCategory(org.modelio.module.cpswarm.type.Abstraction.SensorsActuator.Category category) {
        this.category = category;
    }

    /**
     * (Required)
     */
    @objid ("da90e3f4-229c-4c7e-a70b-e412e0ce2fb2")
    @JsonProperty("api")
    public Api getApi() {
        return api;
    }

    /**
     * (Required)
     */
    @objid ("5ace9b52-1f88-4ed9-bc3e-f4c4b5cd1c0f")
    @JsonProperty("api")
    public void setApi(Api api) {
        this.api = api;
    }

    @objid ("25498bce-43b0-4246-adbc-debdde3c20aa")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("147937dc-0866-45ce-a861-2cd0e2d938eb")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @objid ("fb0cec68-fedf-443d-a7b5-db37c941e4a6")
    public enum Category {
        SENSOR ("Sensor"),
        ACTUATOR ("Actuator"),
        VIRTUAL ("Virtual");

        @objid ("8af23c42-3d29-4db2-8a44-ba224366980a")
        private final String value;

        @objid ("ca0b0f5e-c259-4fe7-a089-e0b7640ac273")
        private static final Map<String, org.modelio.module.cpswarm.type.Abstraction.SensorsActuator.Category> CONSTANTS = new HashMap<String, SensorsActuator.Category>();

        @objid ("8f9285f4-3253-4c51-8c56-77182e68fdeb")
        private Category(String value) {
            this.value = value;
        }

        @objid ("f08df611-4f97-4f24-a060-1a6a08dfe5c3")
        @Override
        public String toString() {
            return this.value;
        }

        @objid ("737072ee-89de-45a3-b188-75eb05e6cbcb")
        @JsonValue
        public String value() {
            return this.value;
        }

        @objid ("88293b26-8aa1-42a5-a55a-5260baa92bc9")
        @JsonCreator
        public static org.modelio.module.cpswarm.type.Abstraction.SensorsActuator.Category fromValue(String value) {
            SensorsActuator.Category constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }


static {
    for (SensorsActuator.Category c: values()) {
        CONSTANTS.put(c.value, c);
    }
}
    }

}
