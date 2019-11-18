package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Formats.FormatTextBox;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.widgets.Control;

/**
 * A este tipo solo pueden pertenecer componentes que admiten únicamente tres entradas. Ej: periódico mixto.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("22fd09d6-0796-40f3-9422-0eb7885d18db")
public abstract class TernaryOperator extends Operator {
    @objid ("cfb9585a-c27a-4dec-859a-af4fce034005")
    private Manager manager;

    /**
     * Primera cuadrícula del componente.
     */
    @objid ("65691acd-c3bc-461f-975e-d34a9dffae55")
    private GridExpression cuadriculaEdicion1;

    /**
     * Segunda cuadrícula del componente.
     */
    @objid ("6fb14c4d-2a8f-4841-a0d8-bc5219fd0169")
    private GridExpression cuadriculaEdicion2;

    /**
     * Tercera cuadrícula del componente.
     */
    @objid ("2178cc96-1dea-4ffe-97d8-ce582a9d4ab6")
    private GridExpression cuadriculaEdicion3;

    /**
     * Constructor.
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     * @param nombre Nombre del componente.
     * @param id Identificador del componente.
     */
    @objid ("a1c72ab3-4357-4f5b-93a0-12771bd5e841")
    public TernaryOperator(Manager manager, GridExpression cuadriculaPadre, String nombre, int id) throws ComponentEditorException {
        super(manager, cuadriculaPadre, true, Category.TernaryType, nombre, id);
        // TODO Auto-generated constructor stub
        
        this.manager = manager;
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#cambioPosicionCentralEnCuadricula()
     */
    @objid ("e501847c-f08b-4603-b236-5ed660c3f774")
    @Override
    protected abstract void cambioPosicionCentralEnCuadricula() throws ComponentEditorException;

    /**
     * Crea la primera cuadrícula del componente.
     * @throws ComponentEditorException
     * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
     */
    @objid ("06237d9d-ae55-4ae9-9da1-92e3b02c2cb3")
    protected void crearCuadriculaEdicion1(String textoInicial) throws ComponentEditorException {
        try{
            this.cuadriculaEdicion1 = new GridExpression(this.manager, super.getCmpTodo());
            this.cuadriculaEdicion1.setBackground(super.getColorFondo());
            SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion1, FormatTextBox.TODO, textoInicial);
            
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
            throw new ComponentEditorException("Error creando la cuadrícula 1 de un componente ternario.",ex);
        }
    }

    /**
     * Crea la segunda cuadrícula del componente.
     * @throws ComponentEditorException
     * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
     */
    @objid ("e2fa01d1-fec1-4140-b0f0-e077c5335968")
    protected void crearCuadriculaEdicion2(String textoInicial) throws ComponentEditorException {
        try{
            this.cuadriculaEdicion2 = new GridExpression(this.manager, super.getCmpTodo());
            this.cuadriculaEdicion2.setBackground(super.getColorFondo());
            SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion2, FormatTextBox.TODO, textoInicial);
            
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
            throw new ComponentEditorException("Error creando la cuadrícula 2 de un componente ternario.",ex);
        }
    }

    /**
     * Crea la tercera cuadrícula del componente.
     * @throws ComponentEditorException
     * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
     */
    @objid ("9c60aba3-b457-4a23-b33d-ce03aff0fe26")
    protected void crearCuadriculaEdicion3(String textoInicial) throws ComponentEditorException {
        try{
            this.cuadriculaEdicion3 = new GridExpression(this.manager, super.getCmpTodo());
            this.cuadriculaEdicion3.setBackground(super.getColorFondo());
            SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion3, FormatTextBox.TODO, textoInicial);
            
            this.cuadriculaEdicion3.setMenu(super.getMenuEliminar());
            t.setMenu(super.getMenuEliminar());
            
            /* Detecta si el componente forma parte de un exponente */
                if (super.getCuadriculaPadre().esExponente()) {
                    this.cuadriculaEdicion3.setEsExponente(true);
                    t.setExponente();
                }
            /* ***************************************************** */
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error creando la cuadrícula 3 de un componente ternario.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#deseleccionar()
     */
    @objid ("30a64ab0-c5d0-4b07-b748-20efd23881f1")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            super.deseleccionar();
            this.cuadriculaEdicion1.deseleccionarTodo();
            this.cuadriculaEdicion2.deseleccionarTodo();
            this.cuadriculaEdicion3.deseleccionarTodo();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error deseleccionando un componente ternario",ex);
        }
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#eliminaComponentesInternosSeleccionados()
     */
    @objid ("0af387f1-4b0a-4aa5-803e-c5a3be167203")
    @Override
    public void eliminaComponentesInternosSeleccionados() throws ComponentEditorException {
        // TODO Auto-generated method stub
        
        /*
         * En este instante no existe ningún componente ternario que pueda albergar a otros componentes.
         * Por eso se deja el método vacío.
         */
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#eliminaTextosVacios()
     */
    @objid ("3658ba4a-e9f0-4ea2-951f-168ebe44d17b")
    @Override
    public void eliminaTextosVacios() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion1);
            this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion2);
            this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion3);
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error mientras se eliminaban Textos vacíos de un componente ternario.",ex);
        }
    }

    /**
     * Devuelve la primera cuadricula padre del componente.
     * @return Primera cuadricula padre del componente.
     */
    @objid ("eabe3471-0c5f-4149-b492-20161e6f6e8f")
    public GridExpression getCuadriculaEdicion1() {
        return this.cuadriculaEdicion1;
    }

    /**
     * Devuelve la segunda cuadricula padre del componente.
     * @return Segunda cuadricula padre del componente.
     */
    @objid ("49553a57-bf76-44c1-945b-3c43309262d4")
    public GridExpression getCuadriculaEdicion2() {
        return this.cuadriculaEdicion2;
    }

    /**
     * Devuelve la tercera cuadricula padre del componente.
     * @return Segunda cuadricula padre del componente.
     */
    @objid ("e1a63a50-97d4-4ed1-afba-26062d697794")
    public GridExpression getCuadriculaEdicion3() {
        return this.cuadriculaEdicion3;
    }

/*
     * (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
     */
    @objid ("0b7e722b-1a39-4e03-9de5-7e0ac271a1e0")
    @Override
    public abstract int getPosicionCentral() throws ComponentEditorException;

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#seleccionar()
     */
    @objid ("6793281b-4a4b-4198-aae7-eafc180420f5")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            
            super.seleccionar();
            this.cuadriculaEdicion1.seleccionar(super.getColorFondoSeleccionado());
            this.cuadriculaEdicion2.seleccionar(super.getColorFondoSeleccionado());
            this.cuadriculaEdicion3.seleccionar(super.getColorFondoSeleccionado());
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error seleccionando un componente binario.",ex);
        }
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#setFocoFinal()
     */
    @objid ("cb16a522-2083-4466-b911-1eddbd07598a")
    @Override
    public void setFocoFinal() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            Control[] con = getCuadriculaEdicion3().getChildren();
            Operator c = (Operator) con[con.length - 1];
            c.setFocoFinal();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error situando el cursor en la posición final de un componente ternario.",ex);
        }
    }

/* (non-Javadoc)
     * @see es.addlink.tutormates.editor.Components.Componente#setFocoInicio()
     */
    @objid ("3fd3b6f1-6938-4b93-be35-d4b380a46675")
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
            throw new ComponentEditorException("Error situando el cursor en la primera posición de un componente ternario.",ex);
        }
    }

}
