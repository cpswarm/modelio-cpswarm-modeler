package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Formats.FormatTextBox;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.widgets.Control;

/**
 * A este tipo solo pueden pertenecer componentes que sólamente admiten una entrada. Ej: raíz cuadrada.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("0e886491-9c06-46f9-a671-7e5d2127f766")
public abstract class UnaryOperator extends Operator {
    @objid ("db03281c-2979-4f2c-b73d-92b7b863f26f")
    private Manager manager;

    /**
     * Cuadrícula del componente.
     */
    @objid ("3288ea49-013c-45f6-abbf-366098821489")
    private GridExpression cuadriculaEdicion1;

    /**
     * Constructor.
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     * @param nombre Nombre del componente.
     * @param id Identificador del componente.
     */
    @objid ("92789186-c6b6-46b6-995a-f2aae5c90d73")
    public UnaryOperator(Manager manager, GridExpression cuadriculaPadre, String nombre, int id) throws ComponentEditorException {
        super(manager, cuadriculaPadre, true, Category.UnaryType, nombre, id);
        // TODO Auto-generated constructor stub
        
        this.manager = manager;
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#cambioPosicionCentralEnCuadricula()
     */
    @objid ("ede00813-7591-46a5-a9d8-64fb00dcc520")
    @Override
    protected abstract void cambioPosicionCentralEnCuadricula() throws ComponentEditorException;

    /**
     * Crea la cuadrícula del componente.
     * @throws ComponentEditorException
     * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
     */
    @objid ("9791ffb2-4fdf-4088-8d5a-a356125b6b61")
    protected void crearCuadriculaEdicion1(String textoInicial, boolean esExponente) throws ComponentEditorException {
        try{
            
            this.cuadriculaEdicion1 = new GridExpression(this.manager, super.getCmpTodo());
            this.cuadriculaEdicion1.setBackground(super.getColorFondo());
            
            SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion1, FormatTextBox.TODO, "");
            if(esExponente)
                t.setExponente();
            
            this.cuadriculaEdicion1.setMenu(super.getMenuEliminar());
            t.setMenu(super.getMenuEliminar());
            
            /* Detecta si el componente forma parte de un exponente */
                if (super.getCuadriculaPadre().esExponente()) {
                    this.cuadriculaEdicion1.setEsExponente(true);
                    t.setExponente();
                }
            /* ***************************************************** */
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la creación de la cuadrícula 1 en un componente unario.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#deseleccionar()
     */
    @objid ("c693d32f-3f1d-48a9-8f09-89771f362c58")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            
            super.deseleccionar();
            this.cuadriculaEdicion1.deseleccionarTodo();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la deselección de un componente unario.",ex);
        }
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#eliminaComponentesInternosSeleccionados()
     */
    @objid ("aad06b0c-359d-416c-b7c4-9168f5e866b0")
    @Override
    public void eliminaComponentesInternosSeleccionados() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            this.manager.getCentralManager().eliminarSeleccion(this.getCuadriculaEdicion1());
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error eliminando los componentes internos seleccionados de un componente unario.",ex);
        }
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#eliminaTextosVacios()
     */
    @objid ("7a3a1c9e-d2ac-4717-b765-d4a8f0becb84")
    @Override
    public void eliminaTextosVacios() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion1);
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error eliminando los Textox vacíos de un componente binario.",ex);
        }
    }

    /**
     * Devuelve la cuadrícula que contiene el componente.
     * @return Cuadricula que contiene el componente.
     */
    @objid ("6291ee81-de1c-4fbf-a070-51bf503b9e17")
    public GridExpression getCuadriculaEdicion1() {
        return this.cuadriculaEdicion1;
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
     */
    @objid ("abd94717-964b-4de4-b282-507224cd9144")
    @Override
    public abstract int getPosicionCentral() throws ComponentEditorException;

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#setFocoFinal()
     */
    @objid ("7997a5ea-e1a7-4814-bd45-e11a84eb7f1c")
    @Override
    public void setFocoFinal() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            
            Control[] con = getCuadriculaEdicion1().getChildren();
            Operator c = (Operator) con[con.length - 1];
            c.setFocoFinal();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error situando el cursor en la posición final de un componente binario.",ex);
        }
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#setFocoInicio()
     */
    @objid ("19c4c8d7-bdde-4bbb-9eba-799aab9e21d2")
    @Override
    public void setFocoInicio() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            
            Control[] con = getCuadriculaEdicion1().getChildren();
            Operator c = (Operator) con[0];
            c.setFocoInicio();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error situando el cursor en la primera posición de un componente unario.",ex);
        }
    }

}
