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
 * Es la clase encargada de construir una raíz cuadrada.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("161485cd-b9c4-4c8d-8cfa-c3ace148aac1")
public class SquareRoot extends UnaryOperator {
    /**
     * Linea superior de la raíz.
     */
    @objid ("ef7d8aa6-17d5-41ba-aeaa-3e8d2c4e0565")
    private LineOperator lineaSuperior;

    /**
     * Símbolo de la raíz.
     */
    @objid ("af57d936-8f7d-48b3-a790-2f0d6ea23dc8")
    private Label simboloRaiz;

    /**
     * Constructor.
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     */
    @objid ("720bc3ef-1044-41f0-9af3-0512a2a233e9")
    public SquareRoot(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "squareRoot", 50);
        // TODO Auto-generated constructor stub
        
        try{
            super.getCmpTodo().setLayout(new FormLayout());
            super.crearCuadriculaEdicion1("",false);
            this.lineaSuperior = new LineOperator(super.getCmpTodo());
            this.simboloRaiz = new Label(super.getCmpTodo(), SWT.NONE);
            this.simboloRaiz.setBackground(super.getColorFondo());
            this.simboloRaiz.setText("\u221A");
            
            /* Composición de las partes del componente Raiz */
            
                // Colocación del símbolo
                FormData fd = new FormData();
                fd.left = new FormAttachment(1, 1);
                fd.top = new FormAttachment(1, 1);
                this.simboloRaiz.setLayoutData(fd);
        
                int i1 = 1;
                int i2 = -2;
                int i3 = 0;
                int i4 = 2;
                
                if(Manager.isMac()){
                    i1 = 1;
                    i2 = -2;
                    i3 = 0;
                    i4 = 0;
                }
        
                // Colocación de la línea superior
                fd = new FormData();
                fd.bottom = new FormAttachment(this.simboloRaiz, i1);
                fd.left = new FormAttachment(this.simboloRaiz, i2);
                this.lineaSuperior.setLayoutData(fd);
        
                // Colocación de la cuadrícula con el Texto en la primera columna
                fd = new FormData();
                fd.left = new FormAttachment(this.simboloRaiz, i3);
                fd.top = new FormAttachment(this.lineaSuperior, i4);
                super.getCuadriculaEdicion1().setLayoutData(fd);
            
            /* ************************************************************************* */
            
            super.getCmpTodo().pack();
                
            setMenuEliminar();        
            listeners();
            
            super.setFocoInicio();
            
            super.getCuadriculaPadre().pack();
            super.setPosicionCentral(getPosicionCentral());
            
            ajustar();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una raíz.",ex);
        }
    }

    /**
     * Modifica el tamaño del componente. Ajusta el tamaño del símbolo y la longitud de la línea superior.
     * @throws ComponentEditorException
     */
    @objid ("3e083d45-df34-48ff-95b4-e49409877e95")
    private void ajustar() throws ComponentEditorException {
        try{
            if (super.getCuadriculaEdicion1() != null && this.simboloRaiz != null) {
            
                super.getCuadriculaEdicion1().pack();
            
                // Cálculo del alto y ancho de la cuadrícula
                int tamY = super.getCuadriculaEdicion1().getSize().y;
                int tamX = super.getCuadriculaEdicion1().getSize().x;
                lineaSuperior.setNumCaracteres(tamX);
            
                int tam = (((tamY + 14) * 16) / 23) - 4;
                simboloRaiz.setFont(new Font(super.getCuadriculaPadre().getDisplay(), "Courier New", tam, SWT.BOLD));
                simboloRaiz.setSize(simboloRaiz.getSize().x, 1);
            
                this.simboloRaiz.pack();
                super.getCmpTodo().pack();
                super.getCuadriculaPadre().pack();
                super.setPosicionCentral(getPosicionCentral());
            }
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el ajuste de una raíz.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CUnario#cambioPosicionCentralEnCuadricula()
     */
    @objid ("f85e3610-a119-40fc-900c-dade4f0cef58")
    @Override
    protected void cambioPosicionCentralEnCuadricula() {
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CUnario#deseleccionar()
     */
    @objid ("287f5644-57e7-4e48-8daa-e1f516d3c617")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.deseleccionar();
        this.lineaSuperior.setBackground(super.getColorFondo());
        this.simboloRaiz.setBackground(super.getColorFondo());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
     */
    @objid ("36776e8f-6642-4750-8107-dc7d4a19250d")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            int inc = 0;
            
            if(Manager.isMac())
                inc = 1;
            
            return this.getCmpTodo().getSize().y / 2 + inc;
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una raíz.",ex);
        }
    }

    /**
     * Enlaza listeners a ambas cuadrículas para realizar ajustes cuando éstas cambian de tamaño.
     */
    @objid ("a8c7df0b-8523-43bc-bc0e-df99de452f11")
    private void listeners() {
        super.getCuadriculaEdicion1().addControlListener(new ControlAdapter() {
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
     * @see es.addlink.tutormates.editor.Components.CUnario#seleccionar(boolean)
     */
    @objid ("942d7466-e55e-46ad-92da-e89b8b01cd52")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.seleccionar();
        this.simboloRaiz.setBackground(super.getColorFondoSeleccionado());
        this.lineaSuperior.setBackground(super.getColorFondoSeleccionado());
        super.getCuadriculaEdicion1().seleccionar(super.getColorFondoSeleccionado());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CUnario#setMenuEliminar()
     */
    @objid ("f1e876d2-eb2c-4c96-89fc-c5564b006934")
    @Override
    protected void setMenuEliminar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.setMenuEliminar();
        this.simboloRaiz.setMenu(super.getMenuEliminar());
        this.lineaSuperior.setMenu(super.getMenuEliminar());
    }

}
