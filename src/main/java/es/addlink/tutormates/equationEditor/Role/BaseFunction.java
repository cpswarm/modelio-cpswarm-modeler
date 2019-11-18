package es.addlink.tutormates.equationEditor.Role;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Formats.MathML.MathML;

@objid ("7a1e2609-d2d7-45bc-a3c5-4bc46049fd92")
public class BaseFunction {
    @objid ("6bcd2144-fd85-483e-af4b-bef2902312e2")
    private String name;

    @objid ("e95ca9a8-42e8-4ba8-8e5f-209cfccc780d")
    private int entries;

    @objid ("9f7fd27f-1897-4731-b727-d5c09a1c1cfc")
    private BaseOperator allowedOperator;

    @objid ("ed3619b6-91c1-4380-ac39-d6fb617f7f5b")
    private MathML mathML;

    @objid ("758163cd-4fe4-4611-8298-323ddab46a71")
    public BaseFunction(String name, int entries, BaseOperator allowedOperator, MathML mathML) {
        super();
        this.name = name;
        this.entries = entries;
        this.allowedOperator = allowedOperator;
        this.mathML = mathML;
    }

    @objid ("ce34e0fc-7731-465f-a5b8-0de8ddd05373")
    public String getName() {
        return name;
    }

    @objid ("2c76b58b-82c5-4fd9-98a8-11e5695ceffe")
    public int getEntries() {
        return entries;
    }

    @objid ("ae5d743a-e933-410a-981a-7c2aebdb312c")
    public BaseOperator getAllowedOperator() {
        return allowedOperator;
    }

    @objid ("d2a78321-a77b-4d42-b6af-fda13108de42")
    public MathML getMathML() {
        return mathML;
    }

    @objid ("ccd47f14-dff9-4ce9-99ec-f50f429b1f77")
    public String toString() {
        String output;
        output = name + "|" + entries + "|" + mathML + "|" + allowedOperator + "|";
        return output;
    }

}
