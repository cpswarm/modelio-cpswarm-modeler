package es.addlink.tutormates.equationEditor.MathEditor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase que representa objetos con una entrada. Ej: negativo, raíz, factorial, etc.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
@objid ("1d48c419-2f5b-443b-aa38-d4e3eef47079")
public class Unary extends MathEditor {
    /**
     * Contenido del objeto Unario.
     */
    @objid ("0ad8a978-b40e-423a-b62e-31083942766d")
    private MathEditor child = null;

    /**
     * Constructor
     * @param name Nombre del componente.
     * @param id Identificador del componente.
     * @param parent Padre del objeto MathEditor.
     */
    @objid ("928f0510-9882-4e49-aa02-437ccc6d7df1")
    public Unary(String name, int id, MathEditor parent) {
        super("unario", name, id, parent);
    }

    /**
     * Devuelve el contenido del objeto Unario.
     * @return El contenido del objeto Unario.
     */
    @objid ("b9ac0da8-7a18-4ba7-930b-432f1208e1ad")
    public MathEditor getChild() {
        return child;
    }

    /**
     * Establece el contenido del objeto Unario.
     * @param newChild
     */
    @objid ("bedc7333-ae8b-48d2-9b03-26182e3434b5")
    public void setChild(MathEditor newChild) {
        child = newChild;
    }

/* (non-Javadoc)
     * @see Tipos.MathEditor#toString()
     */
    @objid ("c4e68315-ada5-404e-a5f4-858ffc718c39")
    @Override
    public String toString() {
        String cad = "<unario name=" + super.getName() + " id=" + super.getID() + ">\n<child>\n";
        if (this.child != null)
            cad += this.child.toString();
        cad += "</child>\n</unario>\n";
        return cad;
    }

}
