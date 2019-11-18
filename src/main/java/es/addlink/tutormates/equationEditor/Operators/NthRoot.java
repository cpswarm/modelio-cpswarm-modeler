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
@objid ("0af46ac0-a777-4a27-a83a-d57a7a010110")
public class NthRoot extends BinaryOperator {
    /**
     * Linea superior de la raíz.
     */
    @objid ("743828ee-314c-4ece-a52f-763da6753960")
    private LineOperator lineaSuperior;

    /**
     * Símbolo de la raíz.
     */
    @objid ("34d32f87-334e-4222-88ed-f5629527534b")
    private Label simboloRaiz;

    /**
     * Constructor.
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     */
    @objid ("4cf3f8de-e23f-416a-b756-08afe409ffcd")
    public NthRoot(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "nthRoot", 57);
        // TODO Auto-generated constructor stub
        
        try{
            super.getCmpTodo().setLayout(new FormLayout());
            super.crearCuadriculaEdicion1("",true);
            super.crearCuadriculaEdicion2("",false);
            this.lineaSuperior = new LineOperator(super.getCmpTodo());
            this.simboloRaiz = new Label(super.getCmpTodo(), SWT.NONE);
            this.simboloRaiz.setBackground(super.getColorFondo());
            this.simboloRaiz.setText("\u221A");
            
            /* Composición de las partes del componente Raiz */
            
            
            FormData fd;
            fd = new FormData();
            fd.top = new FormAttachment(0,0);
            super.getCuadriculaEdicion1().setEsExponente(true);
            super.getCuadriculaEdicion1().setLayoutData(fd);
                
        
                int i1 = 1;
                int i2 = -2;
                int i3 = 0;
                int i4 = 2;
                int i5 = -10;
                int i6 = -7;
                
                if(Manager.isMac()){
                    i1 = 1;
                    i2 = -2;
                    i3 = 0;
                    i4 = 0;
                    i5 = -8;
                    i6 = -5;
                }
                
                // Colocación del símbolo
                fd = new FormData();
                fd.left = new FormAttachment(super.getCuadriculaEdicion1(), i5);
                fd.top = new FormAttachment(super.getCuadriculaEdicion1(), i6);
                this.simboloRaiz.setLayoutData(fd);
                
                // Colocación de la línea superior
                fd = new FormData();
                fd.bottom = new FormAttachment(this.simboloRaiz, i1);
                fd.left = new FormAttachment(this.simboloRaiz, i2);
                this.lineaSuperior.setLayoutData(fd);
        
                // Colocación de la cuadrícula con el Texto en la primera columna
                fd = new FormData();
                fd.left = new FormAttachment(this.simboloRaiz, i3);
                fd.top = new FormAttachment(this.lineaSuperior, i4);
                super.getCuadriculaEdicion2().setLayoutData(fd);
                    
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
    @objid ("9a1a5b8b-65ec-4d6a-99ec-5d209dc73c14")
    private void ajustar() throws ComponentEditorException {
        try{
            if (super.getCuadriculaEdicion2() != null && this.simboloRaiz != null) {
            
                super.getCuadriculaEdicion1().pack();
                super.getCuadriculaEdicion2().pack();
            
                // Cálculo del alto y ancho de la cuadrícula
                int tamY = super.getCuadriculaEdicion2().getSize().y;
                int tamX = super.getCuadriculaEdicion2().getSize().x;
                lineaSuperior.setNumCaracteres(tamX);
            
                int tam = (((tamY + 12) * 16) / 23) - 4;
                simboloRaiz.setFont(new Font(super.getCuadriculaPadre().getDisplay(), "Courier New", tam, SWT.BOLD));
                simboloRaiz.setSize(simboloRaiz.getSize().x, 1);
            
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
    @objid ("f07a8e29-1aeb-4351-ae83-1d736574a017")
    @Override
    protected void cambioPosicionCentralEnCuadricula() {
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CUnario#deseleccionar()
     */
    @objid ("260b2452-726e-4d25-ae40-24b4304a8346")
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
    @objid ("dda87a67-633b-4918-86d3-226a7cc4471a")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        // TODO Auto-generated method stub
        try{
            
            int inc = 0;
            
            if(Manager.isMac())
                inc = 1;
            
            return this.getCmpTodo().getSize().y / 2 - calculoPosicionCentral()-8 + inc;
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una raíz.",ex);
        }
    }

    @objid ("b8a4846f-de2b-4196-a873-849f2e7b4368")
    private int calculoPosicionCentral() {
        try{
            Double d = super.getCuadriculaEdicion1().getSize().y*(-0.5)-4;
            int i = (int) Math.round(d);
            //System.out.println("tamaño: " + super.getCuadriculaEdicion1().getSize().y + "; double: " + d);
            
        return i;
        }catch(Exception ex){
            //ex.printStackTrace();
            return -11;
        }
    }

    /**
     * Enlaza listeners a ambas cuadrículas para realizar ajustes cuando éstas cambian de tamaño.
     */
    @objid ("c97120d6-ba5d-4050-85d2-02e3729eaa7e")
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
     * @see es.addlink.tutormates.editor.Components.CUnario#seleccionar(boolean)
     */
    @objid ("a3fbd7f2-7992-4867-a1e8-b28c457e363f")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.seleccionar();
        this.simboloRaiz.setBackground(super.getColorFondoSeleccionado());
        this.lineaSuperior.setBackground(super.getColorFondoSeleccionado());
        super.getCuadriculaEdicion1().seleccionar(super.getColorFondoSeleccionado());
        super.getCuadriculaEdicion2().seleccionar(super.getColorFondoSeleccionado());
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.CUnario#setMenuEliminar()
     */
    @objid ("c404150b-3d16-46e8-a716-14a81019d6b2")
    @Override
    protected void setMenuEliminar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.setMenuEliminar();
        this.simboloRaiz.setMenu(super.getMenuEliminar());
        this.lineaSuperior.setMenu(super.getMenuEliminar());
    }

}
