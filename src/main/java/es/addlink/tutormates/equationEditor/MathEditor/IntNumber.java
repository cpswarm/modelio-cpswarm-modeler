package es.addlink.tutormates.equationEditor.MathEditor;

import java.math.BigInteger;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase que representa a un número entero.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
@objid ("f64698cf-22c8-472c-8657-98df97dfd2af")
public class IntNumber extends MathEditor {
    /**
     * Número entero.
     */
    @objid ("da05b0a0-9915-4fd1-806a-6a1262e4945e")
    private BigInteger number;

    /**
     * Constructor
     * @param number Número entero.
     * @param parent Padre del objeto MathEditor.
     */
    @objid ("1fb0c214-de51-4c87-bec1-7eec0ada520b")
    public IntNumber(BigInteger number, MathEditor parent) {
        super("numero", "entero", -1, parent);
        this.number = number;
    }

    /**
     * Devuelve el número entero.
     * @return El número entero.
     */
    @objid ("d50ce7b1-5937-46c9-bda5-b87e7974094b")
    public BigInteger getNumber() {
        return number;
    }

    /**
     * Establece el número entero.
     * @param newNumber Número entero.
     */
    @objid ("feef4f4e-ff02-43e6-ab42-8c80d0b2b86d")
    public void setNumber(BigInteger newNumber) {
        number = newNumber;
    }

/* (non-Javadoc)
     * @see Tipos.MathEditor#toString()
     */
    @objid ("4a7bb266-0847-4fd7-8fca-61a31eff85c9")
    @Override
    public String toString() {
        String cad = "<intNumber>";
        cad += this.number;
        cad += "</intNumber>";
        return cad;
    }

}
