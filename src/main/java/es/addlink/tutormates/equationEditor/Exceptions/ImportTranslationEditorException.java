package es.addlink.tutormates.equationEditor.Exceptions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Captura todas las excepciones producidas al traducir un MathEditor a expresión.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("4adc4eb5-4d58-4cef-a4fd-aade2adb7955")
public class ImportTranslationEditorException extends TranslationEditorException {
    @objid ("bc9c26ba-700c-41ec-aae5-b448c1e1b4b9")
     static final long serialVersionUID = 0;

    @objid ("3c63baae-3043-4a9b-b5de-885fb5098e1a")
    public ImportTranslationEditorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    @objid ("983aba84-4db9-4998-834d-5e3fc2b3b5e0")
    public ImportTranslationEditorException(Throwable arg1) {
        super(arg1);
        // TODO Auto-generated constructor stub
    }

}
