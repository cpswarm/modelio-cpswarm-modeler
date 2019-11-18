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

@objid ("2fb04113-3910-40c4-a335-5f4304deddad")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "class",
    "request",
    "response"
})
public class Service {
    /**
     * 
     * (Required)
     */
    @objid ("bca40dd4-af48-4a3d-948e-56ab2c307c98")
    @JsonProperty("name")
    private String name;

    /**
     * 
     * (Required)
     */
    @objid ("e8c2b82d-b9cb-442a-8062-89b134665bcf")
    @JsonProperty("class")
    private String _class;

    @objid ("51717b77-5e97-46dd-8a35-54f259c3509e")
    @JsonProperty("request")
    private Request request;

    @objid ("4db1d0ff-3e31-4cbe-a8fd-e3c3306608ed")
    @JsonProperty("response")
    private Response response;

    @objid ("b06520bd-0bdc-4a4a-a574-887a7d8df682")
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * (Required)
     */
    @objid ("e94fcb2c-d939-4fc9-9591-adc3cc30e062")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * (Required)
     */
    @objid ("c30597ce-b52f-413a-9f18-1d8124f357ec")
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * (Required)
     */
    @objid ("360be959-ac30-4acd-975f-722545082744")
    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    /**
     * (Required)
     */
    @objid ("6052abc2-68b2-4c2d-aae5-8c1ed932fdc1")
    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @objid ("a8373fd1-f04c-4659-9a4c-b84646d90e7c")
    @JsonProperty("request")
    public Request getRequest() {
        return request;
    }

    @objid ("a1f1dd54-c149-4d59-baa2-bef020e12baa")
    @JsonProperty("request")
    public void setRequest(Request request) {
        this.request = request;
    }

    @objid ("226c7d16-c88b-45cc-b577-5e5bb1b34dfb")
    @JsonProperty("response")
    public Response getResponse() {
        return response;
    }

    @objid ("9524dd6b-7d69-4d67-b021-99d48f6316f2")
    @JsonProperty("response")
    public void setResponse(Response response) {
        this.response = response;
    }

    @objid ("52786b09-a822-43c3-b786-2bb474d00e93")
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @objid ("30adcadd-b2f5-4ac5-ab8e-e84261beac79")
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
