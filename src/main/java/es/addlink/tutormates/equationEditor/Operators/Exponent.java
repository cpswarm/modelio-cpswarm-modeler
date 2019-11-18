package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Control;

/**
 * Clase encargada de añadir un exponente.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("277ca331-3c3f-443d-a976-4e8938b9fffc")
public class Exponent extends UnaryOperator {
    /**
     * Constructor
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     */
    @objid ("77e21651-2fa7-48d9-9b6f-ffb279a32e85")
    public Exponent(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "exponent", 52);
        super.setTipoComponente(Category.UnaryComplexType);
        
        try{
            super.getCmpTodo().setLayout(new FormLayout());
            
            crearCuadriculaEdicion1("",true);
            
            setBackground(super.getColorFondo());
            
            super.getCuadriculaEdicion1().addControlListener(new ControlAdapter() {
                @Override
                public void controlResized(ControlEvent e) {
                    try{
                        ajustar();                    
                    }catch(Exception ex){}
                }
            });
            
            setMenuEliminar();
            super.setPosicionCentral(getPosicionCentral());
            ajustar();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de un exponente.",ex);
        }
    }

    /**
     * Modifica el tamaño del componente. Ajusta el tamaño del símbolo y la longitud de la línea superior.
     * @throws ComponentEditorException
     */
    @objid ("f699fd3b-5fc6-465b-95f5-7f6133453dfa")
    private void ajustar() throws ComponentEditorException {
        try{
            
            super.getCuadriculaEdicion1().pack();
            super.getCmpTodo().pack();
            super.getCuadriculaPadre().pack();
            super.setPosicionCentral(getPosicionCentral());
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el ajuste de un exponente.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CUnario#cambioPosicionCentralEnCuadricula()
     */
    @objid ("07288994-e6a0-4e7c-bf9a-5ceb2f38fb93")
    @Override
    protected void cambioPosicionCentralEnCuadricula() {
    }

/*
     * (non-Javadoc)
     * 
     * @see Components.CUnario#deseleccionar()
     */
    @objid ("f6661e8a-078f-4d57-80e6-4da90edff3b5")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.deseleccionar();
    }

/*
     * (non-Javadoc)
     * 
     * @see Components.Componente#getPosicionCentral()
     */
    @objid ("bcc680fc-1aa6-462b-a3e9-b57fbdfbe42d")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        try{
            if (super.getCuadriculaEdicion1() != null) {
                Control[] children = super.getCuadriculaEdicion1().getCuadriculaSuperior().getChildren();
                int i = 0;
                boolean encontrado = false;
            
                while (i < children.length && !encontrado) {
                    if (((Operator) children[i]) == this)
                        encontrado = true;
                    else
                        i++;
                }
            
                int devuelta = 0;
            
                if (i > 0) {
                    Operator compAnterior = (Operator) children[i - 1];
                    devuelta = this.getCmpTodo().getSize().y + (compAnterior.getPosicionCentral() - compAnterior.getLocation().y) -8;
                }
            
                return devuelta;
            }
            else {
                return 0;
            }
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el cálculo de la posición central de un exponente.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see Components.CUnario#seleccionar()
     */
    @objid ("fc16356f-c288-4964-98ea-c5b6a492a870")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.seleccionar();
        super.getCuadriculaEdicion1().seleccionar(super.getColorFondoSeleccionado());
    }

/*
     * (non-Javadoc)
     * 
     * @see Components.Componente#setAltura()
     */
    @objid ("0b47e1a3-a2b6-4de6-8c12-b3576b0ad20b")
    @Override
    public void setAltura(int altura) throws ComponentEditorException {
        try{
            if (altura != super.getCmpTodo().getLocation().y) {
                super.getCmpTodo().setLocation(0, altura);
            }
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el cálculo de la posición central de un exponente.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see Components.Componente#setMenuEliminar()
     */
    @objid ("547ac889-e763-4545-babe-9fa9d2b9339f")
    @Override
    public void setMenuEliminar() throws ComponentEditorException {
        super.setMenuEliminar();
    }

}
