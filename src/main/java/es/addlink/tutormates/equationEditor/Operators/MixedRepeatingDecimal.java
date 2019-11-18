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
@objid ("981b3226-14bc-4482-9c54-ebf9390fc35e")
public class MixedRepeatingDecimal extends TernaryOperator {
    /**
     * Fuente y tamaño del punto por defecto.
     */
    @objid ("4155467c-2ca0-4472-b45a-6476401e24f0")
    private final Font fontTexto = new Font(super.getDisplay(), "Courier New", 12, SWT.BOLD);

    /**
     * Punto decimal.
     */
    @objid ("ab2d0f35-c5ca-411d-b7c6-1b59474e2892")
    private Label lblPunto;

    /**
     * Linea superior del decimal periódico.
     */
    @objid ("06653b42-9727-49e6-b63c-9d2032923333")
    private LineOperator lineaSuperior;

    /**
     * Constructor
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     */
    @objid ("f2856fc1-c470-4a05-b0ee-2fdecb02c325")
    public MixedRepeatingDecimal(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "repeatingDecimal", 53);
        // TODO Auto-generated constructor stub
        
        try{
            setBackground(super.getColorFondo());
            super.getCmpTodo().setLayout(new FormLayout());
            
            this.lineaSuperior = new LineOperator(super.getCmpTodo());
            this.lineaSuperior.setBackground(super.getColorFondo());
            super.crearCuadriculaEdicion1("");
            super.crearCuadriculaEdicion2("");
            super.crearCuadriculaEdicion3("");
            
            SimpleOperator t1 = (SimpleOperator) super.getCuadriculaEdicion1().getChildren()[0];
            t1.setFormato(FormatTextBox.NUM_NEG);
            
            SimpleOperator t2 = (SimpleOperator) super.getCuadriculaEdicion2().getChildren()[0];
            t2.setFormato(FormatTextBox.NUMERICO);
            
            SimpleOperator t3 = (SimpleOperator) super.getCuadriculaEdicion3().getChildren()[0];
            t3.setFormato(FormatTextBox.NUMERICO);
            
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
                lblPunto.setLayoutData(fd);
        
                // Colocación de la cuadrícula2
                fd = new FormData();
                fd.left = new FormAttachment(lblPunto, -3);
                fd.top = new FormAttachment(this.lineaSuperior, 1);
                super.getCuadriculaEdicion2().setLayoutData(fd);
        
                // Colocación de la línea superior
                fd = new FormData();
                fd.left = new FormAttachment(super.getCuadriculaEdicion2(), 2);
                fd.top = new FormAttachment(0, 0);
                this.lineaSuperior.setLayoutData(fd);
        
                // Colocación de la cuadrícula3
                fd = new FormData();
                fd.left = new FormAttachment(super.getCuadriculaEdicion2(), 1);
                fd.top = new FormAttachment(this.lineaSuperior, 1);
                super.getCuadriculaEdicion3().setLayoutData(fd);
            
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
            throw new ComponentEditorException("Error en la construcción de un periódico mixto.",ex);
        }
    }

    /**
     * Ajusta el tamaño y longitud de la línea superior del periódico.
     * @throws ComponentEditorException
     */
    @objid ("9f265d68-579f-4c23-a424-7b57f17a27c4")
    private void ajustar() throws ComponentEditorException {
        try{
            super.getCuadriculaEdicion3().pack();
            
            int tamX = super.getCuadriculaEdicion3().getSize().x;
            lineaSuperior.setNumCaracteres(tamX - 5);
            
            super.getCmpTodo().pack();
            super.getCuadriculaPadre().pack();
            super.setPosicionCentral(getPosicionCentral());
        
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el ajuste de un periódico mixto.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.ComponenteTernario#cambioPosicionCentralEnCuadricula()
     */
    @objid ("95490cc4-d1f2-4186-a8d5-6a070c35249a")
    @Override
    protected void cambioPosicionCentralEnCuadricula() {
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CBinario#deseleccionar()
     */
    @objid ("78f30afe-6a44-4bd3-ae2a-2e2dd78d6264")
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
    @objid ("29ef6e8f-39b7-406b-a55b-7f9666400fc0")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        try{
            
            // TODO Auto-generated method stub
            if (super.getCuadriculaEdicion1() == null)
                return 0;
            return (super.getCuadriculaEdicion1().getSize().y / 2) + super.getCuadriculaEdicion1().getLocation().y;
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el ajuste de un periódico mixto.",ex);
        }
    }

    /**
     * Listener que controla el tamaño de las cuadrículas de PeriodicoMixto.
     */
    @objid ("fe4fa72b-2679-4019-88cc-6489b8356e66")
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
        
        // Cambia el tamaño de la línea de periódico
        super.getCuadriculaEdicion3().addControlListener(new ControlAdapter() {
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
    @objid ("fb5684ec-bc59-44d7-913f-8beab658f78f")
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
    @objid ("7bc4a1a5-af31-46b6-9200-479d65359c8d")
    @Override
    protected void setMenuEliminar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.setMenuEliminar();
        this.lineaSuperior.setMenu(super.getMenuEliminar());
        this.lblPunto.setMenu(super.getMenuEliminar());
    }

}
