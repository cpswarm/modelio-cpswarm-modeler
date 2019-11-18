package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Formats.FormatTextBox;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.widgets.Control;

/**
 * A este tipo solo pueden pertenecer componentes que admiten únicamente dos entradas. Ej: fracción.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("bcf368bf-50f5-4419-9703-26cc8158cd64")
public abstract class BinaryOperator extends Operator {
    /**
     * Primera cuadrícula del componente.
     */
    @objid ("f9b99726-9608-4988-801b-91ebb2edd898")
    private GridExpression cuadriculaEdicion1;

    /**
     * Segunda cuadrícula del componente.
     */
    @objid ("70d41d1e-3c6a-4b2d-9500-6996b5bf3900")
    private GridExpression cuadriculaEdicion2;

    @objid ("74beffd4-367a-41a7-90f5-b27cd0ed7763")
    private Manager manager;

    /**
     * Constructor.
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     * @param nombre Nombre del componente.
     * @param id Identificador del componente.
     */
    @objid ("59982cf7-83d7-49ee-9282-897a36e4f519")
    public BinaryOperator(Manager manager, GridExpression cuadriculaPadre, String nombre, int id) throws ComponentEditorException {
        super(manager, cuadriculaPadre, true, Category.BinaryType, nombre, id);
        // TODO Auto-generated constructor stub
        
        this.manager = manager;
    }

/*
     * (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#cambioPosicionCentralEnCuadricula()
     */
    @objid ("fa7ec365-ccd4-4eb7-aae3-4951c583a789")
    @Override
    protected abstract void cambioPosicionCentralEnCuadricula() throws ComponentEditorException;

    /**
     * Crea la primera cuadrícula del componente.
     * @throws ComponentEditorException
     * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
     */
    @objid ("2ba4170b-6efd-4469-ba43-09fdf4d10446")
    protected void crearCuadriculaEdicion1(String textoInicial, boolean esExponente) throws ComponentEditorException {
        try{
            this.cuadriculaEdicion1 = new GridExpression(this.manager, super.getCmpTodo());
            this.cuadriculaEdicion1.setBackground(super.getColorFondo());
            SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion1, FormatTextBox.TODO, textoInicial);
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
            throw new ComponentEditorException("Error en la creación de la cuadrícula 1.",ex);
        }
    }

    /**
     * Crea la segunda cuadrícula del componente.
     * @throws ComponentEditorException
     * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
     */
    @objid ("913cbfeb-0e7d-4cd7-be4a-2e9aabd5139b")
    protected void crearCuadriculaEdicion2(String textoInicial, boolean esExponente) throws ComponentEditorException {
        try{
            this.cuadriculaEdicion2 = new GridExpression(this.manager, super.getCmpTodo());
            this.cuadriculaEdicion2.setBackground(super.getColorFondo());
            SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion2, FormatTextBox.TODO, textoInicial);
            if(esExponente)
                t.setExponente();
            
            this.cuadriculaEdicion2.setMenu(super.getMenuEliminar());
            t.setMenu(super.getMenuEliminar());
            
            /* Detecta si el componente forma parte de un exponente */
                if (super.getCuadriculaPadre().esExponente()) {
                    this.cuadriculaEdicion2.setEsExponente(true);
                    t.setExponente();
                }
            /* ***************************************************** */
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la creación de la cuadrícula 2.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#deseleccionar()
     */
    @objid ("5407db33-e0ef-48dc-ab17-e3b6c6d40d5a")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            
            super.deseleccionar();
            this.cuadriculaEdicion1.deseleccionarTodo();
            this.cuadriculaEdicion2.deseleccionarTodo();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la deselección de un componente binario.",ex);
        }
    }

/*
     * (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#eliminaComponentesInternosSeleccionados()
     */
    @objid ("4794a200-c2de-4b68-916e-cf437cb67000")
    @Override
    public void eliminaComponentesInternosSeleccionados() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            this.manager.getCentralManager().eliminarSeleccion(this.getCuadriculaEdicion1());
            this.manager.getCentralManager().eliminarSeleccion(this.getCuadriculaEdicion2());
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error eliminando los componentes internos seleccionados de un componente binario.",ex);
        }
    }

/*
     * (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#eliminaTextosVacios()
     */
    @objid ("ce293fab-dbe4-4982-b582-63e0ebb6a3b6")
    @Override
    public void eliminaTextosVacios() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion1);
            this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion2);
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error eliminando los Textox vacíos de un componente binario.",ex);
        }
    }

    /**
     * Devuelve la primera cuadricula padre del componente.
     * @return Primera cuadricula padre del componente.
     */
    @objid ("01e95901-26ab-459b-8982-279b05d52faf")
    public GridExpression getCuadriculaEdicion1() {
        return this.cuadriculaEdicion1;
    }

    /**
     * Devuelve la segunda cuadricula padre del componente.
     * @return Segunda cuadricula padre del componente.
     */
    @objid ("bc7ebeb9-ec61-4f14-8787-1449687b84fb")
    public GridExpression getCuadriculaEdicion2() {
        return this.cuadriculaEdicion2;
    }

/*
     * (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
     */
    @objid ("d7d499de-3b78-4179-bcdf-6709e340240e")
    @Override
    public abstract int getPosicionCentral() throws ComponentEditorException;

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#seleccionar()
     */
    @objid ("0179262a-3969-47ee-b1b3-25c4c31e6e2c")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            
            super.seleccionar();
            this.cuadriculaEdicion1.seleccionar(super.getColorFondoSeleccionado());
            this.cuadriculaEdicion2.seleccionar(super.getColorFondoSeleccionado());
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error seleccionando un componente binario.",ex);
        }
    }

/*
     * (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#setFocoFinal()
     */
    @objid ("e9c12ad2-25ee-4206-a71c-5ddfe5999748")
    @Override
    public void setFocoFinal() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            Control[] con = getCuadriculaEdicion2().getChildren();
            Operator c = (Operator) con[con.length - 1];
            c.setFocoFinal();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error situando el cursor en la posición final de un componente binario.",ex);
        }
    }

/*
     * (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#setFocoInicio()
     */
    @objid ("abe17d1d-69ac-47e8-9a42-c5afa1185f7f")
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
            throw new ComponentEditorException("Error situando el cursor en la primera posición de un componente binario.",ex);
        }
    }

}
