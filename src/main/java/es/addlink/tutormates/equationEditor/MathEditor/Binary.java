package es.addlink.tutormates.equationEditor.MathEditor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase que representa objetos con dos entradas. Ej: suma, resta, fracción, etc.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
@objid ("2b962010-c453-46b9-87da-b058ef44a8b4")
public class Binary extends MathEditor {
    /**
     * Parte izquierda del objeto binario.
     */
    @objid ("24062e84-7a9c-4372-b866-f1e0e6218ed1")
    private MathEditor leftChild = null;

    /**
     * Parte derecha del objeto binario.
     */
    @objid ("b3d8466d-3d2e-4bc8-bc86-8606fad8dd9c")
    private MathEditor rightChild = null;

    /**
     * Constructor
     * @param name Nombre del componente.
     * @param id Identificador del componente.
     * @param parent Padre del objeto MathEditor.
     */
    @objid ("d076060b-4549-4e58-a879-d86925ec3019")
    public Binary(String name, int id, MathEditor parent) {
        super("binario", name, id, parent);
    }

    /**
     * Devuelve la parte izquierda del objeto binario.
     * @return Parte izquierda del objeto binario.
     */
    @objid ("4e2e056d-6a48-484b-be12-e9316ad942d5")
    public MathEditor getLeftChild() {
        return leftChild;
    }

    /**
     * Devuelve la parte derecha del objeto binario.
     * @return Parte derecha del objeto binario.
     */
    @objid ("1bebe042-f312-4b48-bbda-e49ec50bcb8d")
    public MathEditor getRightChild() {
        return rightChild;
    }

    /**
     * Establece la parte izquierda del objeto binario.
     * @param newLeft
     */
    @objid ("d73de9cc-82ae-41e4-a11d-ae6e8f1c86d6")
    public void setLeftChild(MathEditor newLeft) {
        leftChild = newLeft;
    }

    /**
     * Establece la parte derecha del objeto binario.
     * @param newRight
     */
    @objid ("eb5901ec-251c-4a98-ad9d-00c5ef5a4c96")
    public void setRightChild(MathEditor newRight) {
        rightChild = newRight;
    }

/* (non-Javadoc)
     * @see Tipos.MathEditor#toString()
     */
    @objid ("c6054844-1684-4877-bb75-45edfbe0c1c7")
    @Override
    public String toString() {
        String cad = "<binario name=" + super.getName() + " id=" + super.getID() + ">\n";
        
        cad += "<leftChild>\n";
        if (this.leftChild != null) {
            cad += this.leftChild.toString();
        }
        cad += "</leftChild>\n";
        
        cad += "<rightChild>\n";
        if (this.rightChild != null) {
            cad += this.rightChild.toString();
        }
        cad += "</rightChild>\n";
        
        cad += "</binario>";
        return cad;
    }

}
