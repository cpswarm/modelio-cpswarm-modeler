package es.addlink.tutormates.equationEditor.MathEditor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase capaz de representar una variable.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
@objid ("29d534ed-c5b7-43d1-9f02-e7c16f0409ac")
public class Variable extends MathEditor {
    /**
     * Variable.
     */
    @objid ("2a68d755-ea78-4d50-b756-b847d5dfb1be")
    private String var;

    @objid ("9e9ab950-ff32-4541-9758-3933bc333783")
    private Boolean isText;

    @objid ("90a324f7-54e8-42d4-a08c-f1d72a5065a7")
    public Boolean getIsText() {
        return isText;
    }

    /**
     * Constructor.
     * @param var Variable.
     * @param parent Padre del objeto MathEditor.
     */
    @objid ("b007e63a-87fd-4855-a52b-df4dc390c85f")
    public Variable(String var, Boolean isText, MathEditor parent) {
        super("numero", "variable", 0, parent);
        this.var = var;
        this.isText = isText;
    }

    /**
     * Devuelve la variable.
     * @return La variable.
     */
    @objid ("1409075a-7ed6-4640-a6f7-3869a946dea3")
    public String getVar() {
        return var;
    }

    /**
     * Establece una nueva variable.
     * @param newvar Nueva variable.
     */
    @objid ("78fa174f-92b4-408e-912a-97c7f5bb58e3")
    public void setVar(String newvar) {
        var = newvar;
    }

/*
     * (non-Javadoc)
     * @see Tipos.MathEditor#toString()
     */
    @objid ("2c9e1a7c-c3b0-4201-ae0f-e642bf943dd4")
    @Override
    public String toString() {
        return "<Variable>" + var + "</Variable>\n";
    }

}
