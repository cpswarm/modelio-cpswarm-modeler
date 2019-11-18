package es.addlink.tutormates.equationEditor.MathEditor;

import java.math.BigDecimal;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase que representa a un número de tipo real.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
@objid ("fe5bf71a-d89f-4cea-9997-be6f2265d681")
public class RealNumber extends MathEditor {
    /**
     * Número de tipo real.
     */
    @objid ("4b18bf97-f8e0-4c50-a54c-bedde06d2ab5")
    private BigDecimal number;

    /**
     * Constructor
     * @param number Número de tipo real.
     * @param parent Padre del objeto MathEditor.
     */
    @objid ("35632c51-8cf9-41cd-a206-13f7bfa94e15")
    public RealNumber(BigDecimal number, MathEditor parent) {
        super("numero", "real", -1, parent);
        this.number = number;
    }

    /**
     * Devuelve un número de tipo real.
     * @return Número de tipo real.
     */
    @objid ("73b7f050-dc5b-4d60-be75-77962787791f")
    public BigDecimal getNumber() {
        return number;
    }

    /**
     * Establece el número de tipo real.
     * @param newNumber Número de tipo real.
     */
    @objid ("56684530-15d5-4a78-b631-b17bb67607cb")
    public void setNumber(BigDecimal newNumber) {
        number = newNumber;
    }

/*
     * (non-Javadoc)
     * @see Tipos.MathEditor#toString()
     */
    @objid ("40b4f4d0-7f05-4cc3-ac7a-955469adfb21")
    @Override
    public String toString() {
        String cad = "<realNumber>";
        cad += this.number;
        cad += "</realNumber>";
        return cad;
    }

}
