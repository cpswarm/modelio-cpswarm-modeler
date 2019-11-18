package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

/**
 * Es la clase encargada de construir una fracción.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("eef7f7c8-1825-4285-87d4-234e3eb56a1e")
public class Fraction extends BinaryOperator {
    /**
     * Linea central de la fracción
     */
    @objid ("ea4f34ca-940d-4bc0-bbbd-bece7dea9772")
    private LineOperator lineaCentral;

    /**
     * Constructor
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     */
    @objid ("77a285c8-28ac-4396-b751-af84adc5b219")
    public Fraction(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "fraction", 51);
        // TODO Auto-generated constructor stub
        
        try{
            GridLayout grid = new GridLayout();
            grid.numColumns = 1;
            grid.makeColumnsEqualWidth = false;
            grid.horizontalSpacing = 0;
            grid.verticalSpacing = 1;
            grid.marginHeight = 0;
            grid.marginWidth = 0;
            grid.marginBottom = 0;
            grid.marginTop = 0;
            
            super.getCmpTodo().setLayout(grid);
            
            super.crearCuadriculaEdicion1("",false);
            this.lineaCentral = new LineOperator(super.getCmpTodo());
            super.crearCuadriculaEdicion2("",false);
            
            GridExpression c1 = super.getCuadriculaEdicion1();
            GridExpression c2 = super.getCuadriculaEdicion2();
            
            c1.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1));
            c2.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1));
            
            super.getCmpTodo().pack();
            
            setMenuEliminar();
            listeners();
            
            super.setFocoInicio();
            super.setPosicionCentral(getPosicionCentral());
            
            if (super.getComponentePadre() != null)
                super.getComponentePadre().pack();
            
        }catch(EditorException ex){
            System.out.println("Error");
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una fracción.",ex);
        }
    }

    /**
     * Mantiene en el centro del componente a ambas cuadrículas, y modifica la longitud de la línea central en función de la anchura global del componente.
     * @throws ComponentEditorException
     */
    @objid ("93f38bc2-0e29-4148-bd18-a32cb27bfa40")
    private void ajustar() throws ComponentEditorException {
        try{
            int anchoCuadriculaEdicion1 = super.getCuadriculaEdicion1().getSize().x;
            int anchoCuadriculaEdicion2 = super.getCuadriculaEdicion2().getSize().x;
            
            if (anchoCuadriculaEdicion1 > anchoCuadriculaEdicion2)
                lineaCentral.setNumCaracteres(super.getCuadriculaEdicion1().getSize().x + 1);
            else if (anchoCuadriculaEdicion2 > anchoCuadriculaEdicion1)
                lineaCentral.setNumCaracteres(super.getCuadriculaEdicion2().getSize().x + 1);
            else
                lineaCentral.setNumCaracteres(super.getCuadriculaEdicion2().getSize().x + 1);
            
            int anchoCmpTodo = super.getCmpTodo().getSize().x;
            int posicionCua1 = (anchoCmpTodo - anchoCuadriculaEdicion1) / 2;
            int posicionCua2 = (anchoCmpTodo - anchoCuadriculaEdicion2) / 2;
            
            super.getCuadriculaEdicion1().setLocation(posicionCua1, super.getCuadriculaEdicion1().getLocation().y);
            super.getCuadriculaEdicion2().setLocation(posicionCua2, super.getCuadriculaEdicion2().getLocation().y);
            
            super.getCmpTodo().pack();
            super.getCuadriculaPadre().pack();
            super.setPosicionCentral(getPosicionCentral());
            
            if (this.getComponentePadre() != null)
                this.getComponentePadre().cambioPosicionCentralEnCuadricula();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el ajuste de una fracción.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#cambioPosicionCentralEnCuadricula()
     */
    @objid ("70138aac-6439-4bb0-94bd-8cd2ad0100bb")
    @Override
    protected void cambioPosicionCentralEnCuadricula() {
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#deseleccionar()
     */
    @objid ("ba81a921-57b4-43e3-b091-2b7766cbc4b5")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.deseleccionar();
        this.lineaCentral.setBackground(super.getColorFondo());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
     */
    @objid ("60a47911-8f25-4350-86c7-faed72cb0053")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        // TODO Auto-generated method stub
        
        try{
            
            if (this.lineaCentral != null)
                return this.lineaCentral.getLocation().y;
            else
                return 0;
            
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el ajuste de una fracción.",ex);
        }
    }

    /**
     * Enlaza listeners a ambas cuadrículas para realizar ajustes cuando éstas cambian de tamaño.
     */
    @objid ("fa476fee-ce7b-475c-81a1-70ff2748207d")
    private void listeners() {
        super.getCuadriculaEdicion1().addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(ControlEvent e) {
                try{
                    ajustar();
                }catch(Exception ex){}
            }
        });
        
        super.getCuadriculaEdicion2().addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(ControlEvent e) {
                try{
                    ajustar();
                }catch(Exception ex){}
            }
        });
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#seleccionar()
     */
    @objid ("a7dbaff7-8087-4cb0-83d1-6c32e55aa15c")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.seleccionar();
        this.lineaCentral.setBackground(super.getColorFondoSeleccionado());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#setMenuEliminar()
     */
    @objid ("df45f06f-3527-4212-bb6e-21046b766b7a")
    @Override
    protected void setMenuEliminar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.setMenuEliminar();
        this.lineaCentral.setMenu(super.getMenuEliminar());
    }

}
