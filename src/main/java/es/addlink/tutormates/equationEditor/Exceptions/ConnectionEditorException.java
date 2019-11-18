package es.addlink.tutormates.equationEditor.Exceptions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Captura todas las excepciones producidas durante la conexión del editor con la aplicación a la que se le pasan los MathML.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("385b23dc-de93-44fc-8ab3-7a4f89b7422d")
public class ConnectionEditorException extends EditorException {
    @objid ("f7910b48-259b-4634-b0ed-2e8ae2c9d328")
     static final long serialVersionUID = 0;

    @objid ("38fe0b6d-6518-4a7d-b822-e42e503239ba")
    public ConnectionEditorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    @objid ("6c5563a7-31f8-4429-af0d-e13f5ee8880a")
    public ConnectionEditorException(Throwable arg1) {
        super(arg1);
        // TODO Auto-generated constructor stub
    }

}
