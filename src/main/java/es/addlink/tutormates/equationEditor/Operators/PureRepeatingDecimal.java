package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Formats.FormatTextBox;
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
 * Es la clase encargada de construir un decimal periódico.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("219ca2b6-b1fe-4af8-aa3c-7fd94e6df9bd")
public class PureRepeatingDecimal extends BinaryOperator {
    /**
     * Fuente y tamaño del punto por defecto.
     */
    @objid ("87f67383-f8a6-4a94-9931-faf0fd29f88f")
    private final Font fontTexto = new Font(super.getDisplay(), "Courier New", 12, SWT.BOLD);

    /**
     * Punto decimal.
     */
    @objid ("bc647898-6b96-41c5-bc9e-4fda43ba4b53")
    private Label lblPunto;

    /**
     * Linea superior del decimal periódico.
     */
    @objid ("9359cab2-0ba3-4aa3-8d89-c86feb287852")
    private LineOperator lineaSuperior;

    /**
     * Constructor
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     */
    @objid ("c1e82add-840a-48c4-8f35-f5a882ab378a")
    public PureRepeatingDecimal(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "repeatingDecimal", 53);
        // TODO Auto-generated constructor stub
        
        try{
            setBackground(super.getColorFondo());
            super.getCmpTodo().setLayout(new FormLayout());
            
            this.lineaSuperior = new LineOperator(super.getCmpTodo());
            this.lineaSuperior.setBackground(super.getColorFondo());
            super.crearCuadriculaEdicion1("",false);
            super.crearCuadriculaEdicion2("",false);
            
            SimpleOperator t1 = (SimpleOperator) super.getCuadriculaEdicion1().getChildren()[0];
            t1.setFormato(FormatTextBox.NUM_NEG);
            
            SimpleOperator t2 = (SimpleOperator) super.getCuadriculaEdicion2().getChildren()[0];
            t2.setFormato(FormatTextBox.NUMERICO);
            
            this.lblPunto = new Label(super.getCmpTodo(), SWT.NONE);
            this.lblPunto.setText(".");
            this.lblPunto.setFont(this.fontTexto);
            this.lblPunto.setBackground(super.getColorFondo());
            
            /* Composición de las partes del componente ******************************* */
            
                // Colocación de la cuadrícula1 con el Texto en la primera columna
                FormData fd = new FormData();
                fd.left = new FormAttachment(0, 0);
                fd.top = new FormAttachment(0, 2);
                super.getCuadriculaEdicion1().setLayoutData(fd);
        
                // Colocación del punto
                fd = new FormData();
                fd.left = new FormAttachment(super.getCuadriculaEdicion1(), -3);
                fd.top = new FormAttachment(super.getCuadriculaEdicion1(), -16);
                this.lblPunto.setLayoutData(fd);
        
                // Colocación de la línea superior
                fd = new FormData();
                fd.left = new FormAttachment(this.lblPunto, -2);
                fd.top = new FormAttachment(0, 0);
                this.lineaSuperior.setLayoutData(fd);
                
                // Colocación de la cuadrícula2
                fd = new FormData();
                fd.left = new FormAttachment(lblPunto, -3);
                fd.top = new FormAttachment(this.lineaSuperior, 1);
                super.getCuadriculaEdicion2().setLayoutData(fd);
            
            /* ************************************************************************* */
            
            listeners();
            setMenuEliminar();
            
            super.setFocoInicio();
            
            super.getCmpTodo().pack();
            super.getCmpTodo().setSize(super.getCmpTodo().getSize().x, super.getCmpTodo().getSize().y);
            super.getCuadriculaPadre().pack();
            super.setPosicionCentral(getPosicionCentral());
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de un periódico puro.",ex);
        }
    }

    /**
     * Ajusta el tamaño y longitud de la línea superior del periódico.
     * @throws ComponentEditorException
     */
    @objid ("1057a892-0a8e-4c7d-8fa4-a4005ba8a075")
    private void ajustar() throws ComponentEditorException {
        try{
            super.getCuadriculaEdicion2().pack();
            
            int tamX = super.getCuadriculaEdicion2().getSize().x;
            lineaSuperior.setNumCaracteres(tamX - 5);
            
            super.getCmpTodo().pack();
            super.getCuadriculaPadre().pack();
            super.setPosicionCentral(getPosicionCentral());
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de un periódico puro.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.ComponenteBinario#cambioPosicionCentralEnCuadricula()
     */
    @objid ("c30b7c78-ae4f-4669-a8cb-dd77333bd934")
    @Override
    protected void cambioPosicionCentralEnCuadricula() {
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#deseleccionar()
     */
    @objid ("0a0391eb-34d6-497b-9717-e3ad76c0b154")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.deseleccionar();
        this.lineaSuperior.setBackground(super.getColorFondo());
        this.lblPunto.setBackground(super.getColorFondo());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
     */
    @objid ("91f2d0e9-6ea2-436e-8035-ddf1c47b18aa")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            if (super.getCuadriculaEdicion1() == null)
                return 0;
            return (super.getCuadriculaEdicion1().getSize().y / 2) + super.getCuadriculaEdicion1().getLocation().y;
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de un periódico puro.",ex);
        }
    }

    /**
     * Listener que controla el tamaño de las cuadrículas de PeriodicoPuro
     */
    @objid ("106ecd92-591d-4f9f-b108-21331fdfe433")
    private void listeners() {
        super.getCuadriculaEdicion1().addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(ControlEvent e) {
                try{
                    ajustar();
                    lineaSuperior.pack();
                    pack();
                    getParent().pack();
                }catch(Exception ex){}
            }
        });
        
        super.getCuadriculaEdicion2().addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(ControlEvent e) {
                try{
                    ajustar();
                    lineaSuperior.pack();
                    pack();
                    getParent().pack();
                }catch(Exception ex){}
            }
        });
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#seleccionar()
     */
    @objid ("14bda06b-ec9f-4f91-bf0a-4aad911ca113")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.seleccionar();
        this.lineaSuperior.setBackground(super.getColorFondoSeleccionado());
        this.lblPunto.setBackground(super.getColorFondoSeleccionado());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#setMenuEliminar()
     */
    @objid ("1089f12b-1d3b-4595-8297-ab716f46c9d3")
    @Override
    protected void setMenuEliminar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.setMenuEliminar();
        this.lineaSuperior.setMenu(super.getMenuEliminar());
        this.lblPunto.setMenu(super.getMenuEliminar());
    }

}
