package es.addlink.tutormates.equationEditor.Formats;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * La utilidad de esta clase no es otra más que dotar a los objetos de tipo Texto de un formato determinado e impedir que se introduzcan carácteres o componentes no deseados.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("1530dcf7-70eb-4a16-a02d-af3e0af31a14")
public class FormatTextBox {
    /**
     * Indica que se permite la insercción de números, puntos y comas.
     */
    @objid ("231fc3c3-a1e8-4a9a-8a56-55c7cfb8f881")
    public static final int NUM_DEC = 2;

    /**
     * Indica que se permite la insercción de un símbolo negativo al comienzo y números.
     */
    @objid ("1afbdc60-b68d-424b-adee-51c0e1dedd03")
    public static final int NUM_NEG = 4;

    /**
     * Indica que solo se permite la insercción únicamente de números.
     */
    @objid ("c079f5ec-9d36-4538-a4ba-de4c81ab5566")
    public static final int NUMERICO = 1;

    /**
     * Indica que se permite la insercción de cualquier número, variable, paréntesis, punto, coma, operador, operador especial y componente.
     */
    @objid ("71bbee6b-7ef1-46d5-8fc4-643909ece88a")
    public static final int TODO = 0;

    /**
     * Indica que se permite la insercción de variables.
     */
    @objid ("f5e99ce5-e768-4936-a382-c6162d1f438b")
    public static final int VARIABLE = 3;

    @objid ("a2c2623e-9292-4a62-acf8-0ac23e5b8e6e")
    private Manager manager;

    @objid ("76ffa29b-7629-499e-abec-fc0e828cbbe5")
    public FormatTextBox(Manager manager) {
        this.manager = manager;
    }

    /**
     * Devuelve true si el carácter se puede insertar de acuerdo a las políticas del formato, false en caso contrario.
     * @param cad Carácter que se desea insertar.
     * @param formato Formato del Texto activo.
     * @return True si el carácter se puede insertar de acuerdo a las políticas del formato, false en caso contrario.
     */
    @objid ("998807dd-1818-4ed5-bc30-c33239ef915a")
    public boolean esValido(String cad, int formato) {
        boolean valido = false;
        
        /* Verificación del formato "Todo" */
            if (formato == FormatTextBox.TODO) {
            
                // Número
                try {
                    Integer.parseInt(cad.toString());
                    valido = true;
                }
                catch (NumberFormatException e) {
                    valido = false;
                }
            
                // punto, coma, paréntesis
                if (!valido) {
                    if (cad.equals(",") || cad.equals("."))
                        valido = true;
                }
            
                // variable
                if(this.manager.getAllowedOperatorsAndFunctions().existsVariable(cad))
                    valido = true;
            
                // operador
                if (this.manager.getAllowedOperatorsAndFunctions().existsOperator(cad)){
                    valido = true;
                }
            }
            /* ********************************************** */
            
            /* Verificación del formato "Numerico" */
            else if (formato == FormatTextBox.NUMERICO) {
                try {
                    Integer.parseInt(cad);
                    valido = true;
                }
                catch (NumberFormatException e) {
                    valido = false;
                }
            }
        /* ********************************************** */
        
        /* Verificación del formato "Num_Dec" */
            else if (formato == FormatTextBox.NUM_DEC) {
                try {
                    Integer.parseInt(cad);
                    valido = true;
                }
                catch (NumberFormatException e) {
                    valido = false;
                }
            
                if (!valido) {
                    if (cad.equals(",") || cad.equals("."))
                        valido = true;
                }
            }
        /* ********************************************** */
        
        /* Verificación del formato "Variable" */
            else if (formato == FormatTextBox.VARIABLE) {
                if (this.manager.getAllowedOperatorsAndFunctions().existsVariable(cad))
                    valido = true;
            }
        /* ********************************************** */
            
        /* Verificación del formato "Num_Dec" */
            else if (formato == FormatTextBox.NUM_NEG) {
                
                try {
                    Integer.parseInt(cad);
                    valido = true;
                }
                catch (NumberFormatException e) {
                    valido = false;
                }
        
                if(cad.substring(0, 1).equals("-") && this.manager.getStateManager().getPosicionCursor()==0){
                    if(this.manager.getStateManager().getTextoActivo().getNumCaracteres()>0){
                         if(!this.manager.getStateManager().getTextoActivo().getTexto().substring(0, 1).equals("-"))
                         valido = true;
                    }else
                        valido = true;
                }
        
                if (cad.equals(",") || cad.equals("."))
                    valido = false;
            }
        /* ********************************************** */
        return valido;
    }

    /**
     * Devuelve true si el formato permite insertar componentes, false en caso contrario.
     * @param formato Formato del Texto activo.
     * @return True si el formato permite insertar componentes, false en caso contrario.
     */
    @objid ("b4e8afd1-0947-4059-82ab-a397a2854750")
    public static boolean sePermitenComponentes(int formato) {
        if (formato == FormatTextBox.TODO)
            return true;
        else
            return false;
    }

}
