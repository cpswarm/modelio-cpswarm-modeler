
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
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    private String description;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("category")
    private SensorsActuator.Category category;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("api")
    private Api api;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("category")
    public SensorsActuator.Category getCategory() {
        return category;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("category")
    public void setCategory(SensorsActuator.Category category) {
        this.category = category;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("api")
    public Api getApi() {
        return api;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("api")
    public void setApi(Api api) {
        this.api = api;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public enum Category {

        SENSOR("Sensor"),
        ACTUATOR("Actuator"),
        VIRTUAL("Virtual");
        private final String value;
        private final static Map<String, SensorsActuator.Category> CONSTANTS = new HashMap<String, SensorsActuator.Category>();

        static {
            for (SensorsActuator.Category c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Category(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static SensorsActuator.Category fromValue(String value) {
            SensorsActuator.Category constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
