package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * Clase abstracta que representa un sólo componente. Ejemplo de componente: Raiz, Fracción, Texto, etc.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("f69b7dd6-5cde-4676-9c34-4a61221518b3")
public abstract class Operator extends Composite {
    /**
     * True si el componente está seleccionado, false en caso contrario.
     */
    @objid ("84ecd75b-a6b9-4d0d-8eea-596ec26e474d")
    private boolean estaSeleccionado;

    /**
     * Identificador del componente.
     */
    @objid ("413e841f-304b-4367-ae0e-4ba48a42d7b4")
    private int id;

    /**
     * Nombre del componente.
     */
    @objid ("28dafe62-d4f4-4b05-b71e-931be4c99ced")
    private String nombre;

    /**
     * Posición central (vertical) del componente.
     */
    @objid ("f0b16031-d7dc-450a-88f4-5312f7690fbb")
    private int posicionCentral;

    /**
     * Nombre del tipo heredado del componente.
     */
    @objid ("40370413-14f7-42ad-8f1c-dc288dafa29c")
    private String tipoComponente;

    @objid ("2be65954-f4a1-4011-97e0-9818624a4d73")
    private Manager manager;

    /**
     * Composite alojado dentro del componente y que contiene todas sus partes. Puede vascular verticalmente por el componente, para colocar sus partes a la altura correcta.
     */
    @objid ("b92a1112-f3a2-4858-a0a7-99b815695c22")
    private Composite cmpTodo;

    /**
     * Color utilizado para el background de los componentes cuando no se encuentran seleccionados.
     */
    @objid ("a07fe2d7-35a9-47df-8503-8bbcc5b19f83")
    private final Color colorFondo = super.getDisplay().getSystemColor(SWT.COLOR_WHITE);

    /**
     * Color gris, utilizado para el background de los componentes cuando se encuentran seleccionados.
     */
    @objid ("b738edff-c2d9-4e20-a6df-af14221c0857")
    private final Color colorFondoSeleccionado = new Color(super.getDisplay(), 200, 200, 200);

    /**
     * Cuadrícula donde se aloja el componente.
     */
    @objid ("925e229d-375d-4064-bc28-ac56424017d4")
    private GridExpression grid;

    @objid ("40e35ebd-f73b-4eac-b4d9-a01a646e239d")
    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    /**
     * Constructor
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     * @param posicionarComponente Debe ser true si se quiere que el componente se inserte dependiendo de la posición del cursor, false en caso contrario.
     * @param tipoComponente Nombre del tipo heredado del componente.
     * @param nombre Nombre del componente.
     * @param id Identificador del componente.
     */
    @objid ("7fc4c34c-acb4-450f-86c9-8c89b54af630")
    public Operator(Manager manager, GridExpression cuadriculaPadre, boolean posicionarComponente, String tipoComponente, String nombre, int id) throws ComponentEditorException {
        super(cuadriculaPadre, SWT.NONE);
        // TODO Auto-generated constructor stub
        
        try{
            this.manager = manager;        
            this.grid = cuadriculaPadre;
            this.tipoComponente = tipoComponente;
            this.nombre = nombre;
            this.id = id;
            this.estaSeleccionado = false;
            
            this.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1));
            
            this.cmpTodo = new Composite(this, SWT.NONE);
            this.cmpTodo.setCursor(new Cursor(super.getDisplay(), SWT.CURSOR_HAND));
            this.cmpTodo.setBackground(this.colorFondo);
            
            //SelectionManager sc = new SelectionManager(cmpTodo,super.getDisplay(),true);
            //sc.select();
            
            this.setBackground(this.colorFondo);
            
            
            
            if (posicionarComponente)
                this.manager.getCentralManager().colocarComponente(this);
            else
                this.grid.aniadirColumna();
            
            controlTextosVacios();
            
            
            
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción genérica del componente." + this.nombre,ex); //$NON-NLS-1$
        }
    }

    /**
     * Método que se ejecuta cada vez que hay un cambio en la posición central de la cuadrícula.
     * @throws ComponentEditorException
     */
    @objid ("a7af0f8b-daf8-46e1-a129-c0cdc241e581")
    protected abstract void cambioPosicionCentralEnCuadricula() throws ComponentEditorException;

    /**
     * Si en la cuadrícula donde se inserta el nuevo componente existe algún Texto vacío, éste se elimina de acuerdo a unas políticas.
     * @throws ComponentEditorException
     */
    @objid ("9c0469e1-0e17-4a48-81ca-f89be144eb13")
    private void controlTextosVacios() throws ComponentEditorException {
        try{
        
            /* primero se controla si el textbox anterior está vacío y es posible eliminarlo */
                if ((this.manager.getStateManager().getTextoActivo() != null) && (!this.manager.getStateManager().getTextoActivo().isDisposed())) {
                    SimpleOperator textoAnterior = this.manager.getStateManager().getTextoActivo();
                    GridExpression cAnterior = textoAnterior.getCuadriculaPadre();
                    if ((textoAnterior != null) && (textoAnterior.getNumCaracteres() == 0)) {
                        if (cAnterior.getNumColumnas() > 1)
                            this.manager.getCentralManager().eliminarComponente(textoAnterior);
                    }
                }
            /* **************************************************************************** */
        
            if (getNombre() != "texto") {
            
                GridExpression cua = (GridExpression) getParent();
            
                if (cua.getNumColumnas() > 0) {
            
                    Control[] children = cua.getChildren();
                    int i = 0, numCar = 0;
                    boolean salida = false;
            
                    while ((!salida) && (i < children.length)) {
                        Operator comp = (Operator) children[i];
                        if (comp.getNombre() == "texto") {
                            numCar = ((SimpleOperator) comp).getNumCaracteres();
                            if (numCar == 0) {
                                this.manager.getCentralManager().eliminarComponente(comp);
                                salida = true;
                            }
                        }
                        i++;
                    }
                }
            }
        }catch(Exception ex){
            throw new ComponentEditorException("Error eliminando Textos vacíos.",ex); //$NON-NLS-1$
        }
    }

    /**
     * Elimina la selección del componente.
     * @throws ComponentEditorException
     */
    @objid ("f8b265ce-c40f-4be2-ac5c-dcc47564edcb")
    public void deseleccionar() throws ComponentEditorException {
        try{
            this.estaSeleccionado = false;
            this.manager.getActionManager().setEstadoBtnEliminar(false);
            this.setBackground(this.getColorFondo());
            this.cmpTodo.setBackground(this.getColorFondo());
        }catch(Exception ex){
            throw new ComponentEditorException("Error deseleccionando un componente.",ex); //$NON-NLS-1$
        }
    }

    /**
     * Elimina los componentes que se encuentran seleccionados dentro de éste componente.
     * @throws ComponentEditorException
     */
    @objid ("aa5f71f5-5913-4641-8206-b1ac1253e6e0")
    public abstract void eliminaComponentesInternosSeleccionados() throws ComponentEditorException;

    /**
     * Elimina todos los Textos vacíos que haya dentro del componente.
     * @throws ComponentEditorException
     */
    @objid ("a43258d1-597f-4347-a2b7-bd28ac5515c7")
    public abstract void eliminaTextosVacios() throws ComponentEditorException;

    /**
     * Devuelve true si el componente se encuentra seleccionado, false en caso contrario.
     * @return True si el componente se encuentra seleccionado, false en caso contrario.
     */
    @objid ("a3468211-6cd7-4953-b4f8-1e03052cd702")
    public boolean estaSeleccionado() {
        return this.estaSeleccionado;
    }

    /**
     * Devuelve el composite que contiene las partes internas del componente.
     * @return Composite que contiene las partes internas del componente.
     */
    @objid ("40945c8a-b4d4-45af-b1f0-45e3f49e1415")
    protected Composite getCmpTodo() {
        return this.cmpTodo;
    }

    /**
     * Devuelve el color de fondo por defecto cuando no existe selección.
     * @return Color blanco.
     */
    @objid ("d6639b7a-7ef9-437b-82e4-1d9a54076578")
    protected Color getColorFondo() {
        return colorFondo;
    }

    /**
     * Devuelve el color de selección para el fondo.
     * @return Color de selección.
     */
    @objid ("729de0b7-15c6-454d-a3d3-722cca6d9268")
    protected Color getColorFondoSeleccionado() {
        return colorFondoSeleccionado;
    }

    /**
     * Devuelve el componente padre de éste componente.
     * @throws ComponentEditorException
     * @return El componente padre de éste componente.
     */
    @objid ("6a0dc682-d056-4cfd-92d5-53004f6513b9")
    public Operator getComponentePadre() throws ComponentEditorException {
        try{
            
            if (!this.getCuadriculaPadre().esCuadriculaPadre())
                return this.getCuadriculaPadre().getComponentePadre();
            return null;
            
        }catch(Exception ex){
            throw new ComponentEditorException("Error al obtener el componente padre de un componente.",ex); //$NON-NLS-1$
        }
    }

    /**
     * Devuelve la cuadrícula en la que se aloja el componente.
     * @return Cuadrícula en la que se aloja el componente.
     */
    @objid ("314e305a-6516-42a9-8109-7c5da2b51b47")
    public GridExpression getCuadriculaPadre() {
        return this.grid;
    }

    /**
     * Devuelve el identificador del componente.
     * @return Identificador del componente.
     */
    @objid ("96216f77-2f90-4e5d-847b-bacb75265a24")
    public int getId() {
        return this.id;
    }

    /**
     * Devuelve el listener para deseleccionar el componente.
     * @throws ComponentEditorException
     * @return Listener para deseleccionar el componente.
     */
    @objid ("05d311f9-a268-4a15-b440-3b20f9cb1227")
    public Listener getListenerDeseleccion() throws ComponentEditorException {
        try{
            
            Listener mouseListener = new Listener() {
                public void handleEvent(Event e) {
                    try{
                        cmpTodo.setBackground(getColorFondo());
                        Operator.this.deseleccionar();
                    }catch(Exception ex){}
                }
            };
            return mouseListener;
            
        }catch(Exception ex){
            throw new ComponentEditorException(ex);
        }
    }

    /**
     * Devuelve el listener para seleccionar el componente.
     * @throws ComponentEditorException
     * @return Listener para seleccionar el componente.
     */
    @objid ("a7650ef3-216f-4418-a9c9-911cf3380a1d")
    public Listener getListenerSeleccion() {
        Listener mouseListener = new Listener() {
            public void handleEvent(Event e) {
                if (!estaSeleccionado) {
                    cmpTodo.setBackground(getColorFondoSeleccionado());
                }
            }
        };
        return mouseListener;
    }

    /**
     * Devuelve el menú contextual de eliminación de componente.
     * @throws ComponentEditorException
     * @return Menú contextual de eliminación de componente.
     */
    @objid ("b112d5e5-a831-44d0-8fe5-3e98fd0166a4")
    public Menu getMenuEliminar() throws ComponentEditorException {
        try{
            Image img = SWTResourceManager.getImage(GUIEditor.class, "images/icons/eliminar.png"); //$NON-NLS-1$
            Menu menu = new Menu(this.grid);
            MenuItem item = new MenuItem(menu, SWT.PUSH);
            item.setText("Eliminar");
            item.setImage(img);
            
            SelectionListener mouseListener = new SelectionListener() {
                public void widgetDefaultSelected(SelectionEvent event) {}
            
                public void widgetSelected(SelectionEvent event) {
                    try{
                        manager.getCentralManager().eliminarComponente(Operator.this);
                    }catch(Exception ex){}
                }
            };
            
            item.addSelectionListener(mouseListener);
            
            return menu;
        }catch(Exception ex){
            throw new ComponentEditorException("Error creando el menú contextual de eliminación de componentes.",ex); //$NON-NLS-1$
        }
    }

    /**
     * Devuelve el nombre del componente.
     * @return Nombre del tipo heredado del componente.
     */
    @objid ("7c87a774-581d-46e0-83c1-3538e2a813c9")
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve la posición central del componente.
     * @throws ComponentEditorException
     * @return Posición central del componente.
     */
    @objid ("1836f666-982e-4425-b6e0-deabfc5a3928")
    public abstract int getPosicionCentral() throws ComponentEditorException;

    /**
     * Devuelve el nombre del tipo heredado del componente.
     * @return Nombre del tipo heredado del componente.
     */
    @objid ("fdbf3100-ab77-4b02-b5d2-13406c74c5a0")
    public String getTipoComponente() {
        return this.tipoComponente;
    }

    /**
     * Selecciona el componente.
     * @throws ComponentEditorException
     */
    @objid ("a005d0a0-715b-4eb9-b5a9-d21311d9fed9")
    public void seleccionar() throws ComponentEditorException {
        try{
            this.estaSeleccionado = true;
            this.manager.getActionManager().setEstadoBtnEliminar(true);
            this.cmpTodo.setBackground(this.getColorFondoSeleccionado());
            this.setBackground(this.getColorFondoSeleccionado());
        }catch(Exception ex){
            throw new ComponentEditorException("Error seleccionando un componente.",ex); //$NON-NLS-1$
        }
    }

    /**
     * Pack del operador.
     */
    @objid ("bab4b2ef-5a50-4de6-b884-fbc18e80b4b6")
    @Override
    public void pack() {
        super.pack();
        
        if(this.getCuadriculaPadre().esCuadriculaPadre()){
            this.getCuadriculaPadre().getParent().pack();
        }
    }

    /**
     * Establece la localización del componente, dependiendo del parámetro de entrada.
     * @throws ComponentEditorException
     * @param altura Entero que indica la altura a la que se debe colocar el componente.
     */
    @objid ("8cc35f40-911f-41e6-9938-fe3df23f5f1c")
    public void setAltura(int altura) throws ComponentEditorException {
        try{
            this.cmpTodo.setLocation(0, altura);
        }catch(Exception ex){
            throw new ComponentEditorException("Error aplicando una altura de " +altura+ " px a un componente.",ex); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Establece el foco en el componente y el cursor al final del mismo.
     * @throws ComponentEditorException
     */
    @objid ("173e8951-96db-4d7a-bf92-8b076e64a0bf")
    public abstract void setFocoFinal() throws ComponentEditorException;

    /**
     * Establece el foco en el componente y el cursor al principio del mismo.
     */
    @objid ("02bf6111-e11e-4708-a1ad-8db946da296a")
    public abstract void setFocoInicio() throws ComponentEditorException;

    /**
     * Establece el menú de "eliminar componente" en el composite cmpTodo.
     * @throws ComponentEditorException
     */
    @objid ("1e59243e-c84a-4a05-bd0d-813ea7031be7")
    protected void setMenuEliminar() throws ComponentEditorException {
        this.setMenu(this.getMenuEliminar());
        this.cmpTodo.setMenu(this.getMenuEliminar());
    }

    /**
     * Establece la posición central del componente.
     * @throws ComponentEditorException
     * @param posicion Posición central del componente.
     */
    @objid ("486e6e8a-67d7-4b86-8d75-322ea52e292e")
    public void setPosicionCentral(int posicion) throws ComponentEditorException {
        try{
            this.posicionCentral = posicion;
            if (this.posicionCentral != this.getCuadriculaPadre().getPosicionCentral()) {
                this.getCuadriculaPadre().setPosicionCentral();
            }
        }catch(Exception ex){
             throw new ComponentEditorException("Error aplicando una posición central a un componente.",ex); //$NON-NLS-1$
        }
    }

}
