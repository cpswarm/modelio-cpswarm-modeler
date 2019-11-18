package es.addlink.tutormates.equationEditor.Formats.MathML;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Almacena las características MathML de cada operador.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("64d8842e-3424-4ae9-9884-76a6f7cb79f7")
public class MathML {
    @objid ("8e05361f-b9e8-41cc-8bc2-97c3704d610c")
    private String mathMLLabel;

    @objid ("f5696210-5240-4c35-acd9-74e3b8f0edb6")
    private Boolean functionType;

    @objid ("9cc5f539-2f67-4ac5-b3d7-873358327f8b")
    private Boolean withinLabel;

    @objid ("92c67206-ab77-4940-92af-ad548866a68c")
    private List<MathMLAttribute> attributesList;

    @objid ("e33a0a74-5d9c-47a6-a2ef-c4b6c79e1857")
    public MathML(List<MathMLAttribute> attributesList, String mathMLLabel, Boolean functionType, Boolean withinLabel) {
        this.attributesList = attributesList;
        this.mathMLLabel = mathMLLabel;
        this.functionType = functionType;
        this.withinLabel = withinLabel;
    }

    @objid ("a286834e-ef18-4f5c-8bb6-f34fdff29511")
    public Boolean getFunctionType() {
        return functionType;
    }

    @objid ("d0e13853-5976-49bf-bf67-d1dda338ad19")
    public Boolean getWithinLabel() {
        return withinLabel;
    }

    @objid ("774f4119-5fa2-4cec-87d2-4cc2e505c9d6")
    public String getMathMLLabel() {
        return mathMLLabel;
    }

    @objid ("18f36688-4f49-4fbc-8c74-998cf545ebeb")
    public void setMathMLLabel(String mathMLLabel) {
        this.mathMLLabel = mathMLLabel;
    }

    @objid ("82214cda-c6da-4818-96e3-4588b50c74b1")
    public List<MathMLAttribute> getAttributesList() {
        return attributesList;
    }

    @objid ("f68e4e99-b836-4aa4-86e9-1eaae3375f60")
    public MathMLAttribute getAttribute(int i) {
        return this.attributesList.get(i);
    }

    @objid ("c42d6c5a-8913-4ef6-8ea9-7fe58d52532b")
    public String toString() {
        return this.mathMLLabel + " : " + this.functionType + " : " + this.withinLabel + " : " + this.attributesList;
    }

    @objid ("5971f7f3-c98f-454f-9a8a-7ac385080f1f")
    public Boolean hasAttributes() {
        if(this.attributesList != null)
            return this.attributesList.size() > 0;
        else
            return false;
    }

}
