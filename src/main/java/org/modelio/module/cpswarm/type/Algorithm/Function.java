package org.modelio.module.cpswarm.type.Algorithm;

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

@objid ("c35b1285-826f-4473-81c5-a49075e0d5cb")
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
    @objid ("d3265b77-d456-4412-a54e-8b6972567f11")
    @JsonProperty("name")
    private String name;

    /**
     * 
     * (Required)
     */
    @objid ("1669c525-975e-455d-bd0d-5d11d2ab889d")
    @JsonProperty("description")
    private String description;

    @objid ("93035442-8561-4e1c-976a-2f8a2a07de1c")
    @JsonProperty("dependencies")
    private List<Dependency> dependencies = null;

    /**
     * 
     * (Required)
     */
    @objid ("f77a682a-3293-4295-8f84-2830ab9a5982")
    @JsonProperty("api")
    private Api api;

    @objid ("ffce07ce-2eb9-49b6-85e7-3f24a9a8392a")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("4ae438bd-29d4-4131-a7db-8a93c34a9046")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * (Required)
     */
    @objid ("1f8d014a-d49a-4f4a-bd0c-7d9f13ad4011")
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * (Required)
     */
    @objid ("0c349f3c-5104-47d5-a0af-c6c23cae66cd")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * (Required)
     */
    @objid ("42b33f2d-9e8d-4649-8d51-b741efcbf864")
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("fb9e2a26-63ff-449e-a0d6-f74841a044ab")
    @JsonProperty("dependencies")
    public List<Dependency> getDependencies() {
        return dependencies;
    }

    @objid ("8409fef7-5176-46a7-b61d-652d2659cc53")
    @JsonProperty("dependencies")
    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    /**
     * (Required)
     */
    @objid ("8b3fff5e-cb27-4f06-a15e-ac8c68656191")
    @JsonProperty("api")
    public Api getApi() {
        return api;
    }

    /**
     * (Required)
     */
    @objid ("a68deaee-95bd-4419-8e21-cacfcaacacd8")
    @JsonProperty("api")
    public void setApi(Api api) {
        this.api = api;
    }

    @objid ("f47b308c-92bc-423a-9569-a5a220a3daf8")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("7d3ed092-67ea-4824-a606-f6cbb5a12435")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
