package es.addlink.tutormates.equationEditor.Exceptions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Captura todas las excepciones producidas en los componentes (paquete Components).
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("20999d70-f913-44f6-b145-ef5214c357bc")
public class ComponentEditorException extends EditorException {
    @objid ("af8219a5-81ed-4089-8cae-365cf0407737")
     static final long serialVersionUID = 0;

    @objid ("b108f653-f2ed-4fb9-a3f2-c8cea75a6f18")
    public ComponentEditorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    @objid ("4540af89-73c9-4537-8ad1-afe7ed2e0ea3")
    public ComponentEditorException(Throwable arg1) {
        super(arg1);
        // TODO Auto-generated constructor stub
    }

}
