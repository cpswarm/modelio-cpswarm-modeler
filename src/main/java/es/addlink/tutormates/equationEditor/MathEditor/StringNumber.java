package es.addlink.tutormates.equationEditor.MathEditor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase que representa texto, útil para los periódicos cuando hay que representar cantidades con ceros a la izquierda.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
@objid ("35ba4aa0-23aa-4d09-b78e-f86e9d06dcb9")
public class StringNumber extends MathEditor {
    /**
     * Texto
     */
    @objid ("d465f3bc-a79c-4373-8d69-5a2a041195b0")
    private String text;

    /**
     * Constructor
     * @param text Texto.
     * @param parent Padre del objeto MathEditor.
     */
    @objid ("c0d3c1f1-28fb-4900-8998-46c6896b8f92")
    public StringNumber(String text, MathEditor parent) {
        super("", "", 0, parent);
        this.text = text;
    }

    /**
     * @return Texto
     */
    @objid ("ce526dd7-0c89-4a5d-a0d5-6305a5ded836")
    public String getText() {
        return text;
    }

    /**
     * @param newText Nuevo texto.
     */
    @objid ("b17e2756-186e-4d63-975e-a7482532983b")
    public void setText(String newText) {
        text = newText;
    }

/*
     * (non-Javadoc)
     * @see Tipos.MathEditor#toString()
     */
    @objid ("f45d3afe-6182-4298-88cc-2ae587c20950")
    @Override
    public String toString() {
        String cad = "<Texto>";
        cad += this.text;
        cad += "</Texto>\n";
        return cad;
    }

}
