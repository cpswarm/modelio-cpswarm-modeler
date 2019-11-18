package es.addlink.tutormates.equationEditor.Formats.MathML;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Atributo de un MathML.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("e33f041d-5d6f-4639-9a14-b5085e458e19")
public class MathMLAttribute {
    @objid ("0bfbdf2e-5c01-462b-b0b8-8771d1c83203")
    private String attribute;

    @objid ("c6b0be1f-6c4d-4b5e-adb6-7c69470d577f")
    private String value;

    @objid ("3ed1a697-fb7d-4517-8dad-b643a311b0c3")
    public MathMLAttribute(String attribute, String value) {
        super();
        this.attribute = attribute;
        this.value = value;
    }

    @objid ("e319ade7-68ce-4d78-b946-bcff003fe47d")
    public String getAttribute() {
        return attribute;
    }

    @objid ("d86ca18b-df88-452e-a25f-6cda4428662e")
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @objid ("cfc379e6-88f3-4675-86b9-9eac389ed367")
    public String getValue() {
        return value;
    }

    @objid ("4645e121-b281-4598-bdfe-d258c58543c2")
    public void setValue(String value) {
        this.value = value;
    }

    @objid ("75bd5e1e-2af8-45f8-88b8-46098abea808")
    public String toString() {
        String out = this.attribute + "=" + this.value + " ~ ";
        return out;
    }

}
