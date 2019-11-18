package es.addlink.tutormates.equationEditor.Exceptions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Captura todas las excepciones producidas en las clases del paquete Manager (motor del editor).
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("8971f96b-acb1-4490-8ef1-b17948459df0")
public class ManagerEditorException extends EditorException {
    @objid ("df92cb37-5523-4984-a089-da99ab65483e")
     static final long serialVersionUID = 0;

    @objid ("3c285a18-fd6c-4c72-bafb-ff933431724f")
    public ManagerEditorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    @objid ("4123d9b8-ef92-428f-a001-8578cae6d8c0")
    public ManagerEditorException(Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

}
