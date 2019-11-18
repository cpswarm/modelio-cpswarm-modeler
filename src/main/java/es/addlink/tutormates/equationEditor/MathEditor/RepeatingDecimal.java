package es.addlink.tutormates.equationEditor.MathEditor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase capaz de representar números decimales, periódicos puros o periódicos mixtos.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
@objid ("87518b24-85f9-4cdb-bc18-ae2bf05d1cc6")
public class RepeatingDecimal extends MathEditor {
    /**
     * Parte decimal.
     */
    @objid ("45ba806a-0b5e-4d63-b796-3788576ae07f")
    private StringNumber secondChild = null;

    /**
     * Parte periódica.
     */
    @objid ("d156f9c0-f509-4ab8-9f03-b4343ac55eec")
    private StringNumber thirdChild = null;

    /**
     * Parte entera.
     */
    @objid ("d4a0d7b8-2187-41aa-8733-ffb1a8f51277")
    private MathEditor firstChild = null;

    /**
     * Constructor
     * @param name Nombre del componente.
     * @param id Identificador del componente.
     * @param parent Padre del objeto MathEditor.
     */
    @objid ("3dcfd557-5f56-4481-a8f9-f0be42806248")
    public RepeatingDecimal(String name, int id, MathEditor parent) {
        super("especial", name, id, parent);
    }

    /**
     * Devuelve la parte entera.
     * @return Parte entera.
     */
    @objid ("0682ccac-c55c-4e2c-84f5-a67bd641c7ca")
    public MathEditor getFirstChild() {
        return firstChild;
    }

    /**
     * Devuelve la parte decimal.
     * @return Parte decimal.
     */
    @objid ("25b26bf4-44ab-48e0-8f67-2ecf69434bcc")
    public MathEditor getSecondChild() {
        return secondChild;
    }

    /**
     * Devuelve la parte periódica.
     * @return La parte periódica.
     */
    @objid ("b531636d-15f5-43bb-a3d8-95024820597a")
    public MathEditor getThirdChild() {
        return thirdChild;
    }

    /**
     * Establece la parte entera.
     * @param newFirst Parte entera.
     */
    @objid ("48e106d4-10fa-4f0e-a0af-9e2712ffc207")
    public void setFirstChild(MathEditor newFirst) {
        firstChild = newFirst;
    }

    /**
     * Establece la parte decimal.
     * @param newSecond Parte decimal.
     */
    @objid ("c6652a04-19dd-4778-97af-3050cb371a48")
    public void setSecondChild(StringNumber newSecond) {
        secondChild = newSecond;
    }

    /**
     * Establece la parte periódica.
     * @param newThird Parte periódica.
     */
    @objid ("d112055c-c6e8-4d57-baa7-b3a1cf6852ce")
    public void setThirdChild(StringNumber newThird) {
        thirdChild = newThird;
    }

/*
     * (non-Javadoc)
     * @see Tipos.MathEditor#toString()
     */
    @objid ("39603164-e057-4458-b9e0-41af19874c4a")
    @Override
    public String toString() {
        String cad = "<periodico name=" + super.getName() + " id=" + super.getID() + ">\n";
        
        cad += "<firstChild>\n";
        if (this.firstChild != null) {
            cad += this.firstChild.toString();
        }
        cad += "</firstChild>\n";
        
        cad += "<secondChild>\n";
        if (this.secondChild != null) {
            cad += this.secondChild.toString();
        }
        cad += "</secondChild>\n";
        
        cad += "<thirdChild>\n";
        if (this.thirdChild != null) {
            cad += this.thirdChild.toString();
        }
        cad += "</thirdChild>\n";
        
        cad += "</periodico>";
        return cad;
    }

}
