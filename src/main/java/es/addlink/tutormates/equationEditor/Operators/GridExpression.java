package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Una cuadrícula únicamente sirve para albergar componentes. Una cuadrícula puede o no, estar dentro de un componente para albergar a otros.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("42006162-1f15-4b51-be43-fce7b24f9f3e")
public class GridExpression extends Composite {
    /**
     * Si se trata de la primera cuadrícula (la del nivel superior), este atributo es true, false en caso contrario.
     */
    @objid ("acf4138d-97fe-4823-b9d3-c4646eefb2f5")
    private boolean esCuadriculaPadre;

    /**
     * Si contiene una expresión que actúa como exponente, este atributo es true, false en caso contrario.
     */
    @objid ("b8c24747-4f03-46f8-9245-0dc638c397c5")
    private boolean esExponente;

    /**
     * Posición central para todos los componentes de la cuadrícula.
     */
    @objid ("5eaf678f-272a-4c55-a1a5-e95090d9471e")
    private int posicionCentral;

    @objid ("70c4e385-1634-4370-85a0-a5c692ecedb6")
    private Manager manager;

    /**
     * Composite donde se aloja la cuadrícula. El composite siempre será cmpTodo.
     */
    @objid ("b22b5e20-b31a-49f6-8eef-c6a5714ae551")
    private Composite compositePadre;

    /**
     * Tabla de la cuadrícula.
     */
    @objid ("6b1b57c4-7687-442f-8bc5-a054f2c4197f")
    private GridLayout grid;

    /**
     * Constructor
     * @throws ComponentEditorException
     * @param parent composite donde se aloja la cuadrícula.
     */
    @objid ("a8a1a849-b4dd-411d-8c8d-84bdd6814bb7")
    public GridExpression(Manager manager, Composite parent) throws ComponentEditorException {
        super(parent, SWT.NONE);
        // TODO Auto-generated constructor stub
        
        try{
            this.manager = manager;
            this.compositePadre = parent;
            this.esCuadriculaPadre = false;
            this.esExponente = false;
            
            this.grid = new GridLayout();
            this.grid.numColumns = 0;
            this.grid.makeColumnsEqualWidth = false;
            this.grid.horizontalSpacing = 0;
            this.grid.verticalSpacing = 0;
            this.grid.marginHeight = 0;
            this.grid.marginWidth = 0;
            this.grid.marginBottom = 0;
            
            this.posicionCentral = this.getSize().y / 2;
            
            setLayout(this.grid);
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una cuadrícula.",ex);
        }
    }

    /**
     * Añade una columna al grid asociado a la cuadrícula.
     */
    @objid ("a42f28e7-4387-4ed0-9acb-a18c87a7d302")
    public void aniadirColumna() {
        this.grid.numColumns = this.grid.numColumns + 1;
    }

    /**
     * Elimina la selección de todos los componentes alojados en la cuadrícula.
     * @throws ComponentEditorException
     */
    @objid ("0ae888c6-4121-4672-b292-ea7437784fe1")
    public void deseleccionarTodo() throws ComponentEditorException {
        try{
            this.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
            Control[] c = this.getChildren();
            for (int i = 0; i < c.length; i++)
                ((Operator) c[i]).deseleccionar();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
        }
    }

    /**
     * Elimina una columna del grid asociado a la cuadrícula.
     */
    @objid ("5fe4a117-d5f9-40d9-a73f-16155dcdbed2")
    public void eliminarColumna() {
        this.grid.numColumns = this.grid.numColumns - 1;
    }

    /**
     * Devuelve true si se trata de la cuadrícula padre, false en caso contrario.
     * @return true si se trata de la cuadrícula padre, false en caso contrario.
     */
    @objid ("6b4c1452-bc54-453c-8fda-85b1fa14fdb3")
    public boolean esCuadriculaPadre() {
        return esCuadriculaPadre;
    }

    /**
     * Devuelve true si se trata de un exponente, false en caso contrario.
     * @return true si se trata de un exponente, false en caso contrario.
     */
    @objid ("098803af-3aa8-4ec0-ab76-1b5d781fba88")
    protected boolean esExponente() {
        return esExponente;
    }

    /**
     * Devuelve el componente al que pertenece la cuadrícula. No confundir con el padre de la cuadrícula.
     * @throws ComponentEditorException
     * @return Componente al que pertenece la cuadrícula. No confundir con el padre de la cuadrícula.
     */
    @objid ("b4d348f3-8762-47ef-b21d-60bc80958b49")
    public Operator getComponentePadre() throws ComponentEditorException {
        try{
            /*
             * Si la cuadrícula es la padre de todas, el padre de ésta no es de tipo Componente. 
             * Si se intenta hacer un cast(Componente), saltaría una excepción de tipo ClassCastExcepcion.
             */
            if (this.esCuadriculaPadre)
                return null;
            else
                return (Operator) this.compositePadre.getParent();
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
        }
    }

    /**
     * Devuelve la siguiente cuadrícula superior a la actual.
     * @throws ComponentEditorException
     * @return Siguiente cuadrícula superior a la actual.
     */
    @objid ("209568dc-434c-4762-a36f-de921db6e5ae")
    public GridExpression getCuadriculaSuperior() throws ComponentEditorException {
        try{
            
            if(!this.isDisposed() && !this.esCuadriculaPadre){
                return (GridExpression) this.getParent().getParent().getParent();
            }else{
                if(this.isDisposed())
                    return null;
                else
                    return this;
            }
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
        }
    }

    /**
     * Devuelve, una vez calculado, la altura del componente cuya posición central es más baja.
     * @throws ComponentEditorException
     * @return Altura del componente cuya posición central es más baja.
     */
    @objid ("59e41eaf-5d8f-49ce-a4dc-12c80a4b3a14")
    private int getMayorPosicionCentral() throws ComponentEditorException {
        try{
            int max = 0;
            
            Control[] c = this.getChildren();
            Operator comp;
            for (int i = 0; i < c.length; i++) {
                comp = (Operator) c[i];
                if (comp.getPosicionCentral() > max)
                    max = comp.getPosicionCentral();
            }
            return max;
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
        }
    }

    /**
     * Devuelve el número de columnas que tiene actualmente el grid de la cuadrícula.
     * @return el número de columnas que tiene actualmente el grid de la cuadrícula.
     */
    @objid ("d5b1e4dc-4aeb-46d1-844d-27fa4e370a55")
    public int getNumColumnas() {
        return this.grid.numColumns;
    }

    /**
     * Devuelve la posición central de la cuadrícula en el momento de la petición.
     * @return Posición central de la cuadrícula en el momento de la petición.
     */
    @objid ("0ed5d6b8-47db-44f7-aaeb-7417ede9e8bf")
    public int getPosicionCentral() {
        return this.posicionCentral;
    }

    /**
     * Devuelve true si algún componente alojado en la cuadrícula se encuentra seleccionado, false en caso contrario.
     * @throws ComponentEditorException
     * @return True si algún componente alojado en la cudrícula se encuentra seleccionado, false en caso contrario.
     */
    @objid ("4ba681a0-6930-4a75-a188-ed1bf1885a57")
    public boolean haySeleccion() throws ComponentEditorException {
        try{
            
            boolean haySeleccion = false;
            int i = 0;
            Control[] children = this.getChildren();
            
            while ((i < children.length) && (!haySeleccion)) {
                if (((Operator) children[i]).estaSeleccionado())
                    haySeleccion = true;
                i++;
            }
            
            return haySeleccion;
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
        }
    }

    /**
     * Selecciona todos los componentes de la cuadrícula.
     * @throws ComponentEditorException
     * @param colorSeleccion Color de la selección.
     */
    @objid ("42160eeb-4080-4d0f-9f84-afb5837500f3")
    protected void seleccionar(Color colorSeleccion) throws ComponentEditorException {
        try{
            
            this.setBackground(colorSeleccion);
            Control[] c = this.getChildren();
            for (int i = 0; i < c.length; i++)
                ((Operator) c[i]).seleccionar();
        
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
        }
    }

    @objid ("c8104b3d-3a0d-43d9-b988-91bf89d7bd56")
    public void pack() {
        super.pack();
        if(this.esCuadriculaPadre())
            this.getParent().pack();
    }

    /**
     * Modifica el valor del atributo.
     * @param esCuadriculaPadre si es la cuadrícula padre true, false en caso contrario.
     */
    @objid ("5f7c09c3-fb3b-4cd6-9db6-7d20d2def1aa")
    public void setEsCuadriculaPadre(boolean esCuadriculaPadre) {
        this.esCuadriculaPadre = esCuadriculaPadre;
    }

    /**
     * Modifica el valor del atributo.
     * @param esExponente Si es un exponente true, false en caso contrario.
     */
    @objid ("5952db45-c315-4ce8-ba10-fbe09927fa1a")
    protected void setEsExponente(boolean esExponente) {
        this.esExponente = esExponente;
    }

    /**
     * Establece la posición central de la cuadrícula.
     * @throws ComponentEditorException
     */
    @objid ("5d73e178-2dc3-4a3e-b19e-776dd9d68d85")
    public void setPosicionCentral() throws ComponentEditorException {
        try{
            
            this.posicionCentral = getMayorPosicionCentral();
            
            Control[] c = this.getChildren();
            
            Operator comp=null;
            int alt;
            
            for (int i = 0; i < c.length; i++) {
                comp = (Operator) c[i];
                alt = this.posicionCentral - comp.getPosicionCentral();
                comp.setAltura(alt);
            }
            if(comp.getComponentePadre() != null)
                comp.getComponentePadre().cambioPosicionCentralEnCuadricula();
        
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
        }
    }

    /**
     * Elimina todos los componentes que contiene la cuadrícula y el número de columnas se establece a cero.
     * @throws ComponentEditorException
     */
    @objid ("9900a5f8-e06a-4f98-8a46-c00d92cffb3b")
    public void vaciar() throws ComponentEditorException {
        try{
            
            Control[] c = this.getChildren();
            for (int i = 0; i < c.length; i++)
                c[i].dispose();
            
            this.grid.numColumns = 0;
            
            this.manager.getStateManager().setTextoActivo(null);
            this.manager.getStateManager().setPosicionCursor(-1);
            this.manager.getStateManager().setHaySeleccion(false);
            this.posicionCentral = 0;
            this.pack();
            
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
        }
    }

}
