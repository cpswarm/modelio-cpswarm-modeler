package es.addlink.tutormates.equationEditor.Exceptions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Captura todas las excepciones producidas al traducir una expresión a MathObject.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("e018b56f-e830-41a5-9c2c-516e8a733c54")
public class ExportTranslationEditorException extends TranslationEditorException {
    @objid ("fd9c0fa7-4942-4248-9503-c4a0774ce575")
     static final long serialVersionUID = 0;

    @objid ("96b40317-4e38-475c-b8ab-f640bec97846")
    public ExportTranslationEditorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    @objid ("f7c38809-3d3c-4efe-87ff-e62f2877f924")
    public ExportTranslationEditorException(Throwable arg1) {
        super(arg1);
        // TODO Auto-generated constructor stub
    }

}
