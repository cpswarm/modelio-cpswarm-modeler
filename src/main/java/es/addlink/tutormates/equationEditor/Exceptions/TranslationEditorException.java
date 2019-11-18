package es.addlink.tutormates.equationEditor.Exceptions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Captura todas las excepciones producidas al convertir una expresión a MathEditor o viceversa.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("30582354-ed9a-4047-964a-5ed9a815c83d")
public class TranslationEditorException extends EditorException {
    @objid ("2ffa55ff-85f9-4f5a-8017-e4d58a031159")
     static final long serialVersionUID = 0;

    @objid ("539a404f-3840-4810-8be8-f28dac45b695")
    public TranslationEditorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    @objid ("4143b6ff-3b9c-4917-ba5e-75806b102034")
    public TranslationEditorException(Throwable arg1) {
        super(arg1);
        // TODO Auto-generated constructor stub
    }

}
