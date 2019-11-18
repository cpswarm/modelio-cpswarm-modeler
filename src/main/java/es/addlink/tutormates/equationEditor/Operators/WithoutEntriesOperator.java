package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.widgets.Label;

@objid ("1b9acd8d-8d34-4e79-82d2-e0e2b347c578")
public class WithoutEntriesOperator extends Operator {
    @objid ("ed0d8210-575d-4f1a-886b-d7fd4d4f4f07")
    private Manager manager;

    @objid ("16eae3d8-f929-435f-ac24-e86901b664f1")
    private Label symbol;

    @objid ("3a83e55b-7dbc-431b-8bb0-3d16d970fa16")
    public WithoutEntriesOperator(Manager manager, GridExpression cuadriculaPadre, String nombre, int id) throws ComponentEditorException {
        super(manager, cuadriculaPadre, true, Category.WithoutEntriesType, nombre, id);
        // TODO Auto-generated constructor stub
        this.manager = manager;
        this.setFocusNextOperator();
        this.manager.getHistoryManager().guardarHistorial(false);
    }

    @objid ("2cf697fa-0eb0-4cd4-9947-f1cb068cf2b6")
    protected void setSymbol(Label lbl) {
        this.symbol = lbl;
    }

    @objid ("48147d63-efba-46a1-a874-6118bd060e5f")
    @Override
    protected void cambioPosicionCentralEnCuadricula() throws ComponentEditorException {
        // TODO Auto-generated method stub
    }

    @objid ("cb9a4615-9233-44c7-bc67-aaf6ca278844")
    @Override
    public void eliminaComponentesInternosSeleccionados() throws ComponentEditorException {
        // TODO Auto-generated method stub
    }

    @objid ("98c7a07f-35be-4fcf-be28-e1cb8860436e")
    @Override
    public void eliminaTextosVacios() throws ComponentEditorException {
        // TODO Auto-generated method stub
    }

    @objid ("9928df63-60ec-4191-a19e-b69e1d3e3601")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        // TODO Auto-generated method stub
        return 0;
    }

    @objid ("dc954ecc-f62e-4e7a-bcda-7121f9d281eb")
    @Override
    public void setFocoFinal() throws ComponentEditorException {
        // TODO Auto-generated method stub
            int pos = this.manager.getCentralManager().getOperatorPosition(this, super.getCuadriculaPadre());
            if(pos == super.getCuadriculaPadre().getNumColumnas()-1)
                this.manager.getCentralManager().insertarTextoAlFinal(super.getCuadriculaPadre());
            else
                try {
                    this.manager.getCentralManager().insertarTexto(super.getCuadriculaPadre(), pos+1);
                }
                catch (ManagerEditorException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    }

    @objid ("c7bce3b3-aba1-4c82-961f-3cf8d41b7706")
    @Override
    public void setFocoInicio() throws ComponentEditorException {
        // TODO Auto-generated method stub
            int pos = this.manager.getCentralManager().getOperatorPosition(this, super.getCuadriculaPadre());
            if(pos == 0)
                this.manager.getCentralManager().insertarTextoAlInicio(super.getCuadriculaPadre());
            else
                try {
                    this.manager.getCentralManager().insertarTexto(super.getCuadriculaPadre(), pos-1);
                }
                catch (ManagerEditorException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    }

    @objid ("5ec4d3cd-36e2-4f71-9f4a-b1a909680a95")
    public void setFocusNextOperator() {
        try {
            int pos = this.manager.getCentralManager().getOperatorPosition(this, super.getCuadriculaPadre());
            if(pos < super.getCuadriculaPadre().getChildren().length - 1){
                Operator ope = (Operator)super.getCuadriculaPadre().getChildren()[pos+1];
                ope.setFocoInicio();
            }else
                this.manager.getCentralManager().insertarTextoAlFinal(super.getCuadriculaPadre());
        }
        catch (ComponentEditorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @objid ("a9553a9b-330c-471b-a045-8815471b3585")
    public void setFocusPreviousOperator() {
        try {
            int pos = this.manager.getCentralManager().getOperatorPosition(this, super.getCuadriculaPadre());
            if(pos > 0){
                Operator ope = (Operator)super.getCuadriculaPadre().getChildren()[pos-1];
                ope.setFocoFinal();
            }else
                this.manager.getCentralManager().insertarTextoAlInicio(super.getCuadriculaPadre());
        }
        catch (ComponentEditorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @objid ("ff61bca4-27c2-4fbf-9a57-08f994ce1455")
    protected Manager getManager() {
        return this.manager;
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#seleccionar()
     */
    @objid ("7529a9ea-ae6f-4313-b794-72df8d499c4f")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.seleccionar();
        this.symbol.setMenu(super.getMenuEliminar());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#deseleccionar()
     */
    @objid ("b4049aea-17b7-4b4c-a1c2-ae9d3e9fd741")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.deseleccionar();
        this.symbol.setBackground(getColorFondo());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#setMenuEliminar()
     */
    @objid ("7f7bd9f7-1afc-4596-b5d6-6e4dd6d78b33")
    @Override
    protected void setMenuEliminar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.setMenuEliminar();
    }

}
