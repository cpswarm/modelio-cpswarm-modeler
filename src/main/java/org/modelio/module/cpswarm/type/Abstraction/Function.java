package org.modelio.module.cpswarm.type.Abstraction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("4800e794-816d-44ea-847c-005bcc4a36dc")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "dependencies",
    "api"
})
public class Function {
    /**
     * 
     * (Required)
     */
    @objid ("7a0fdae2-477a-47e6-9e23-e8f2f15f2058")
    @JsonProperty("name")
    private String name;

    /**
     * 
     * (Required)
     */
    @objid ("f840b306-901c-45dd-bede-cd7fea550d57")
    @JsonProperty("description")
    private String description;

    @objid ("5d6037d4-bf84-47a2-a3f3-90d7a4cc5cc9")
    @JsonProperty("dependencies")
    private List<Dependency> dependencies = null;

    /**
     * 
     * (Required)
     */
    @objid ("92ab8776-376e-487d-b32f-85647f483473")
    @JsonProperty("api")
    private Api api;

    @objid ("d301e8c7-afec-4fb4-bd39-8682e94bbb1c")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("cf456769-3af2-46b2-88a1-c101772068af")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * (Required)
     */
    @objid ("2ea41ad4-d235-4731-907a-c4ce8bbda46e")
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * (Required)
     */
    @objid ("cd2c7649-27ad-483c-ac6a-a95af225bd2f")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * (Required)
     */
    @objid ("0b8bfcb4-8a6f-48a4-8c25-3d1e6a834dce")
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("3fa2b4e0-3513-402c-b955-c9a235b26b3e")
    @JsonProperty("dependencies")
    public List<Dependency> getDependencies() {
        return dependencies;
    }

    @objid ("06d2c822-2e8e-491d-ac1a-f928b3af5f05")
    @JsonProperty("dependencies")
    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    /**
     * (Required)
     */
    @objid ("be4198a0-4f2f-46bd-b75b-435beac78f85")
    @JsonProperty("api")
    public Api getApi() {
        return api;
    }

    /**
     * (Required)
     */
    @objid ("c15f3c68-5f23-4f7e-a912-da5e53735f36")
    @JsonProperty("api")
    public void setApi(Api api) {
        this.api = api;
    }

    @objid ("e33884c6-6954-4281-b9a7-63b0cb92f6ac")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("f4a84d99-3ede-425c-aa99-7cfc84121f3f")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
