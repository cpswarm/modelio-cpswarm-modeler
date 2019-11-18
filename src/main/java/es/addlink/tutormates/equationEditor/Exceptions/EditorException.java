package es.addlink.tutormates.equationEditor.Exceptions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase padre que engloba a todas las excepciones propias del editor.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("e2df0c7a-51ed-4334-ba56-0d3cf7897cd9")
public class EditorException extends Exception {
    @objid ("ec8ef555-6de6-4066-b31b-e468fd77e4d7")
     static final long serialVersionUID = 0;

    @objid ("7f140615-1e18-4736-b499-7c33f9102375")
    private Throwable ex;

    /**
     * @param arg0
     * @param arg1
     */
    @objid ("8715a80d-389b-41c4-9f94-72a84da1ad13")
    public EditorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
        
        this.ex = arg1;
    }

    /**
     * @param arg0
     */
    @objid ("f7d36e03-a715-4aea-a884-13fd6bfd00df")
    public EditorException(Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * Muestra la excepción por consola de forma resumida.
     */
    @objid ("61865ca7-0016-4af5-8c72-e5b6a323343a")
    public void showError() {
        System.err.println("# Equation Editor [Handled Exception]: Excepción capturada en el método '" + this.getStackTrace()[1].getMethodName() + "' de la clase '" + this.getStackTrace()[1].getClassName() + "'. Para más información, ejecutar 'showExtendedError()'.");
    }

    /**
     * Muestra la excepción por consola de forma extendida.
     */
    @objid ("2ec76830-a888-460c-8cc6-00a50986f7ba")
    public void showExtendedError() {
        System.err.println(getExtendedError());
    }

    @objid ("19906cc4-c8c7-4b31-9eb9-4f5d28a87ec9")
    public String getExtendedError() {
        String str="";
        
        str += "# Equation Editor [Handled Exception] *******************************************************\n";
        str += "    ·Message 1:  " + this.ex.getMessage() +"\n";
        str += "    ·Message 2:  " + this.getMessage() +"\n";
        str += "    ·Exception:  " + this.getClass().getSimpleName() +"\n";
        str += "    ·Cause:      " + this.ex.getCause() +"\n";
        str += "    ·Class:      " + this.getStackTrace()[0].toString() +"\n";
        str += "    ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        str += "    Nota: Si ve este error notifíquelo a <ignacio.celaya@tutormates.es> por favor. Explique detallademente (si lo sabe) como ha saltado.\n";
        str += "  *******************************************************************************************";
        return str;
    }

}
