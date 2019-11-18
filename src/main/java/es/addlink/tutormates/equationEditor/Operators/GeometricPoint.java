package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;

/**
 * Clase encargada de añadir una coordenada para su representación en un gráfico.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("c90a37b2-5f9d-43c9-b59d-d02cbf96fb1b")
public class GeometricPoint extends BinaryOperator {
    /**
     * Posición central en la que se encuentra el componente.
     */
    @objid ("f05b5934-98ea-4647-813f-052256ed26b8")
    private int posicionCentral;

    /**
     * Separador de la coordenada.
     */
    @objid ("6a869f20-0aa8-4a0f-8168-74fccffca40b")
    private final String separador = ";";

    /**
     * Label que contiene el paréntesis izquierdo.
     */
    @objid ("b9791ff4-683b-42ad-a066-b317b6196b98")
    private Label lblParentesis1;

    /**
     * Label que contiene el paréntesis derecho.
     */
    @objid ("2225ce2a-582d-462b-af45-9912a4937153")
    private Label lblParentesis2;

    /**
     * Label en el que se muestra el separador de la coordenada.
     */
    @objid ("b961c0a2-b9bf-4e47-a6ef-d75c8c77ba60")
    private Label lblSeparador;

    /**
     * Constructor.
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     */
    @objid ("a36c10f9-4d2d-49ef-8dda-4b173b537094")
    public GeometricPoint(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "geometricPoint", 56);
        // TODO Auto-generated constructor stub
        
        try{
            this.setBackground(super.getColorFondo());
            super.getCmpTodo().setLayout(new FormLayout());
            
            super.crearCuadriculaEdicion2("",false);
            super.crearCuadriculaEdicion1("",false);
            
            this.lblParentesis1 = new Label(super.getCmpTodo(), SWT.NONE);
            this.lblParentesis2 = new Label(super.getCmpTodo(), SWT.NONE);
            this.lblSeparador = new Label(super.getCmpTodo(), SWT.NONE);
            
            this.lblParentesis1.setFont(new Font(this.getDisplay(), "Courier New", 15, SWT.BOLD));
            this.lblParentesis2.setFont(new Font(this.getDisplay(), "Courier New", 15, SWT.BOLD));
            this.lblSeparador.setFont(new Font(this.getDisplay(), "Courier New", 12, SWT.BOLD));
            
            this.lblParentesis1.setBackground(super.getColorFondo());
            this.lblParentesis2.setBackground(super.getColorFondo());
            this.lblSeparador.setBackground(super.getColorFondo());
            
            this.lblParentesis1.setText("(");
            this.lblParentesis2.setText(")");
            this.lblSeparador.setText(separador);
            
            /* Colocación de las partes */
            
                // Colocación del paréntesis izquierdo
                FormData fd = new FormData();
                fd.left = new FormAttachment(0, 0);
                this.lblParentesis1.setLayoutData(fd);
        
                // Colocación de la cuadrícula izquierda
                fd = new FormData();
                fd.left = new FormAttachment(this.lblParentesis1, 0);
                super.getCuadriculaEdicion1().setLayoutData(fd);
        
                // Colocación del separador
                fd = new FormData();
                fd.left = new FormAttachment(super.getCuadriculaEdicion1(), 0);
                this.lblSeparador.setLayoutData(fd);
        
                // Colocación de la cuadrícula derecha
                fd = new FormData();
                fd.left = new FormAttachment(this.lblSeparador, 1);
                super.getCuadriculaEdicion2().setLayoutData(fd);
        
                // Colocación del paréntesis derecho
                fd = new FormData();
                fd.left = new FormAttachment(super.getCuadriculaEdicion2(), -1);
                this.lblParentesis2.setLayoutData(fd);
            
            /* ************************************************************************* */
            
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
            
            setMenuEliminar();
            super.setPosicionCentral(getPosicionCentral());
            ajustar();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
        }
    }

    /**
     * Modifica el tamaño del componente y la altura de su contenido a medida que la expresión interior va cambiando.
     * @throws ComponentEditorException
     */
    @objid ("393a8cc8-0d0c-4edf-8aa9-b2356d315bb3")
    private void ajustar() throws ComponentEditorException {
        try{
            if (super.getCuadriculaEdicion1() != null && this.lblSeparador != null) {
            
                int diferencia = -1;
            
                if (super.getCuadriculaEdicion1().getPosicionCentral() > super.getCuadriculaEdicion2().getPosicionCentral()) {
                    this.posicionCentral = super.getCuadriculaEdicion1().getPosicionCentral();
                    diferencia = super.getCuadriculaEdicion1().getPosicionCentral() - (super.getCuadriculaEdicion2().getSize().y - (super.getCuadriculaEdicion2().getSize().y - super.getCuadriculaEdicion2().getPosicionCentral()));
                    if (diferencia != super.getCuadriculaEdicion2().getLocation().y) {
                        super.getCmpTodo().pack();
                        super.getCuadriculaEdicion2().setLocation(super.getCuadriculaEdicion2().getLocation().x, diferencia);
                    }
                    else {
                        super.getCmpTodo().pack();
                        super.getCuadriculaEdicion2().setLocation(super.getCuadriculaEdicion2().getLocation().x, diferencia);
                        super.getCuadriculaEdicion1().pack();
                        super.getCuadriculaEdicion2().pack();
                    }
                }
            
                else if (super.getCuadriculaEdicion2().getPosicionCentral() > super.getCuadriculaEdicion1().getPosicionCentral()) {
            
                    this.posicionCentral = super.getCuadriculaEdicion2().getPosicionCentral();
                    diferencia = super.getCuadriculaEdicion2().getPosicionCentral() - (super.getCuadriculaEdicion1().getSize().y - (super.getCuadriculaEdicion1().getSize().y - super.getCuadriculaEdicion1().getPosicionCentral()));
                    if (diferencia != super.getCuadriculaEdicion1().getLocation().y) {
                        super.getCmpTodo().pack();
                        super.getCuadriculaEdicion1().setLocation(super.getCuadriculaEdicion1().getLocation().x, diferencia);
                    }
                    else {
                        super.getCmpTodo().pack();
                        super.getCuadriculaEdicion1().setLocation(super.getCuadriculaEdicion1().getLocation().x, diferencia);
                        super.getCuadriculaEdicion1().pack();
                        super.getCuadriculaEdicion2().pack();
                    }
                }
            
                else {
                    this.posicionCentral = super.getCuadriculaEdicion2().getPosicionCentral();
                    diferencia = super.getCuadriculaEdicion2().getPosicionCentral() - (super.getCuadriculaEdicion1().getSize().y - (super.getCuadriculaEdicion1().getSize().y - super.getCuadriculaEdicion1().getPosicionCentral()));
                    if (diferencia != super.getCuadriculaEdicion1().getLocation().y) {
                        super.getCmpTodo().pack();
                        super.getCuadriculaEdicion1().setLocation(super.getCuadriculaEdicion1().getLocation().x, diferencia);
                        super.getCuadriculaEdicion2().setLocation(super.getCuadriculaEdicion2().getLocation().x, diferencia);
                    }
                    else {
                        super.getCmpTodo().pack();
                        super.getCuadriculaEdicion1().setLocation(super.getCuadriculaEdicion1().getLocation().x, diferencia);
                        super.getCuadriculaEdicion2().setLocation(super.getCuadriculaEdicion2().getLocation().x, diferencia);
                        super.getCuadriculaEdicion1().pack();
                        super.getCuadriculaEdicion2().pack();
                    }
                    this.posicionCentral = super.getCuadriculaEdicion1().getSize().y / 2 + super.getCuadriculaEdicion1().getLocation().y;
                }
            
                super.getCuadriculaPadre().pack();
                super.setPosicionCentral(getPosicionCentral());
            
                this.lblSeparador.setLocation(this.lblSeparador.getLocation().x, this.posicionCentral - (this.lblSeparador.getSize().y / 2));
                this.lblParentesis1.setLocation(this.lblParentesis1.getLocation().x, this.posicionCentral - (this.lblParentesis1.getSize().y / 2));
                this.lblParentesis2.setLocation(this.lblParentesis2.getLocation().x, this.posicionCentral - (this.lblParentesis2.getSize().y / 2));
            }
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el ajuste de una coordenada.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#cambioPosicionCentralEnCuadricula()
     */
    @objid ("7a3d9ac6-2ed5-4d03-8c3b-29bd8ae0481f")
    @Override
    protected void cambioPosicionCentralEnCuadricula() throws ComponentEditorException {
        ajustar();
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#deseleccionar()
     */
    @objid ("39578918-82c7-401f-bf4b-0cfd855e8881")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.deseleccionar();
        this.lblSeparador.setBackground(super.getColorFondo());
        this.lblParentesis1.setBackground(super.getColorFondo());
        this.lblParentesis2.setBackground(super.getColorFondo());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
     */
    @objid ("596d84ef-cb7b-4022-9cc1-026b7f4b2595")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            return this.getCmpTodo().getSize().y / 2;
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el cáculo de la posición central de una coordenada.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#seleccionar(boolean)
     */
    @objid ("9a53e937-5097-43ab-96d0-50cb2b6b1f2f")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.seleccionar();
        this.lblSeparador.setBackground(super.getColorFondoSeleccionado());
        this.lblParentesis1.setBackground(super.getColorFondoSeleccionado());
        this.lblParentesis2.setBackground(super.getColorFondoSeleccionado());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#setMenuEliminar()
     */
    @objid ("7073f770-5245-45f5-9204-e8f176ca1abe")
    @Override
    protected void setMenuEliminar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.setMenuEliminar();
        this.lblParentesis1.setMenu(super.getMenuEliminar());
        this.lblParentesis2.setMenu(super.getMenuEliminar());
        this.lblSeparador.setMenu(super.getMenuEliminar());
    }

}
