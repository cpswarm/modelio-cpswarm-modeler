package es.addlink.tutormates.equationEditor.MathEditor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Esta clase es la encargada de representar texto. Utilizada para expresiones matemáticas incorrectas.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("b94eece1-a60e-4736-b6a9-8594adcf26e2")
public class Text extends MathEditor {
    @objid ("c22d39e1-d720-4dc4-be00-47a2a354a8a3")
    private String text;

    @objid ("e8f68dc6-c7c0-43b8-9561-13185bb9f5d9")
    public String getText() {
        return text;
    }

    @objid ("c0e4eb59-002c-40e2-94db-5b09b8306428")
    public void setText(String text) {
        this.text = text;
    }

    @objid ("a2cf9eb8-9e24-469e-8a8a-44d45269bd83")
    public Text(MathEditor parent) {
        super("Text", "Text", -1, parent);
        // TODO Auto-generated constructor stub
    }

    @objid ("029eefbb-5460-4855-9dfb-f70b65ffa5be")
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "<texto>"+this.text+"</texto>";
    }

}
