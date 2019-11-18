package es.addlink.tutormates.equationEditor.Exceptions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Captura todas las excepciones producidas en las clases del paquete Manager (motor del editor).
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("bec1621b-53d7-44ae-bd07-d3a92ea20f93")
public class FileEditorException extends EditorException {
    @objid ("493e321c-359e-46da-bf62-a7ba35872d9f")
     static final long serialVersionUID = 0;

    @objid ("bfa3c00f-d06d-4c69-a890-4ef146195516")
    public FileEditorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    @objid ("33e779af-3745-4533-a9f2-d1f04f2c616a")
    public FileEditorException(Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

}
