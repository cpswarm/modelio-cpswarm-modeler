package es.addlink.tutormates.equationEditor.Manager;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Operators.BinaryOperator;
import es.addlink.tutormates.equationEditor.Operators.GridExpression;
import es.addlink.tutormates.equationEditor.Operators.Operator;
import es.addlink.tutormates.equationEditor.Operators.SimpleOperator;
import es.addlink.tutormates.equationEditor.Operators.TernaryOperator;
import es.addlink.tutormates.equationEditor.Operators.UnaryOperator;
import es.addlink.tutormates.equationEditor.Operators.WithoutEntriesOperator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Gestiona los eventos que se producen en un componente Texto.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("cde7797d-94d8-4c30-91a9-5932cfa61a46")
public class SimpleOperatorManager {
    /**
     * En caso de que esté seleccionado todo el texto y se pulse una tecla de borrado, este atributo se establecerá a True.
     */
    @objid ("795eb3a0-76cf-4c76-8006-470fefcefcc7")
    private Boolean borrarTodo = false;

    /**
     * Indica si se ha pulsado una tecla de borrado (back space o del) o no.
     */
    @objid ("3c62f9be-9066-4361-a7a9-f041e94068ea")
    private Boolean teclaPulsadaBorrado = false;

    /**
     * Componente de tipo Texto sobre el que se realiza el control de eventos.
     */
    @objid ("f62cd7c0-1935-4bee-8e06-2434e6f0f94e")
    private SimpleOperator texto;

    /**
     * Listener que controla los clicks con el ratón sobre el TextBox.
     */
    @objid ("4be20e48-709f-4433-8ded-2b642b454c5d")
    private Listener listenerClick;

    /**
     * Listener que controla si el TextBox recibe o pierde el foco.
     */
    @objid ("c14e2310-c766-4775-93ed-9b487a35b245")
    private FocusListener listenerFocus;

    /**
     * Listener que controla las pulsaciones con el teclado cuando el foco está en el TextBox.
     */
    @objid ("8d7466d9-673b-44b5-b568-c089cae60e53")
    private KeyAdapter listenerKey;

    /**
     * Listener que controla las pulsaciones con el teclado cuando el foco está en el TextBox.
     */
    @objid ("fd4ee71f-b3de-4b75-bd27-c4c3a822a890")
    private KeyAdapter listenerEnterKey;

//private MenuDetectListener listenerMenu;
    /**
     * Listener que controla la carga de menús contextuales al pulsar el botón derecho del ratón sobre un TextBox.
     * Listener que controla todos los cambios del texto de un TextBox.
     */
    @objid ("2a627a00-76a7-42ca-b550-6c78d1eeea02")
    private ModifyListener listenerModify;

    /**
     * Controlador de los cambios de tamaño del componente Texto.
     */
    @objid ("c57c91bd-5638-4d36-b4a3-c63535840b01")
    private ControlAdapter listenerResized;

    /**
     * Listener que controla el texto introducido en un TextBox.
     */
    @objid ("3e44ab1b-ec16-40f2-82d0-eab8b0753c04")
    private VerifyListener listenerVerify;

    @objid ("a28bc878-95ea-4020-98ad-ef623b1f7935")
    private Manager manager;

    /**
     * Constructor
     * @param texto Texto sobre el que se realiza el control de eventos.
     */
    @objid ("ce873c6a-0f20-4da0-a257-58e98659f763")
    public SimpleOperatorManager(Manager manager, SimpleOperator texto) throws ManagerEditorException {
        this.manager = manager;
        this.texto = texto;
        //this.manager.getStateManager().setTextoActivo(texto);
        //this.manager.getStateManager().setPosicionCursor(0);
        buildListeners();
    }

    /**
     * Ajusta el TextBox según la longitud de caracteres que contenga.
     * @param seHaInsertadoTexto Si se ha introducido texto por teclado, esta variable está a true.
     */
    @objid ("d7b5f078-23e2-46e7-9d3e-7b729e4ddcbd")
    public void ajustar(boolean seHaInsertadoTexto) throws ManagerEditorException {
        try{
            int caracteres = this.texto.getNumCaracteres();
            
            int columnas;
            if (seHaInsertadoTexto)
                columnas = caracteres;
            else
                columnas = caracteres + 1;
            
            if (teclaPulsadaBorrado) {
            
                String seleccion = this.texto.getCaracteresSeleccionados();
                int numC = seleccion.length();
            
                if (numC != 0)
                    columnas = columnas - numC - 1;
            }
            
            if (columnas <= 0)
                columnas = 1;
            else if ((columnas == 1) && (teclaPulsadaBorrado))
                columnas = 1;
            
            if ((borrarTodo) && (teclaPulsadaBorrado))
                columnas = 1;
            
            GC gc = this.texto.getGC();
            FontMetrics fm = gc.getFontMetrics();
            
            int widthSpace=0;
            int heightSpace=0;
            
            if(Manager.isMac()){
                widthSpace = 4;
                heightSpace=5;
            }
            
            int width = columnas * (fm.getAverageCharWidth())+widthSpace;
            int height = fm.getHeight()+heightSpace;
            
            gc.dispose();
            this.texto.setSizeTextBox(this.texto.getComputeSize(width, height - 2));
            
            
            
            if (this.texto.estaSeleccionado())
                this.texto.setColorLlenoSeleccionado();
            else {
                if (this.texto.getNumCaracteres() > 0)
                    this.texto.setColorLleno();
                else
                    this.texto.setColorVacio();
            }
            
            if (caracteres == 0) {
                borrarTodo = false;
                teclaPulsadaBorrado = false;
            }
            
            if ((caracteres == 0 && teclaPulsadaBorrado) || borrarTodo) {
                if ((this.texto.estaSeleccionado()) && (this.texto.getCambiaColorSiEstaVacio()))
                    this.texto.setColorVacioSeleccionado();
                else if (this.texto.getCambiaColorSiEstaVacio())
                    this.texto.setColorVacio();
            }
            
            teclaPulsadaBorrado = false;
            borrarTodo = false;
            texto.packSinTextBox();
            
        }catch(Exception ex){
            throw new ManagerEditorException("Error al ajustar el tamaño de un componente Texto.",ex);
        }
    }

    /**
     * Reajusta las variables teclaPulsadaBorrado y borrarTodo, según el código de tecla.
     * @param keyCode Código de tecla.
     */
    @objid ("4909b598-4576-45c3-971b-d42782068f79")
    private void borrado(int keyCode) throws ManagerEditorException {
        try{
            int caracteres = this.texto.getNumCaracteres();
            int seleccion = this.texto.getNumCaracteresSeleccionados();
            teclaPulsadaBorrado = true;
            
            if ((caracteres == this.texto.getPosicionCursor()) && (keyCode == SWT.DEL))
                teclaPulsadaBorrado = false;
            
            if ((0 == this.texto.getPosicionCursor()) && (keyCode == SWT.BS))
                teclaPulsadaBorrado = false;
            
            if (seleccion == caracteres)
                borrarTodo = true;
        
        }catch(Exception ex){
            throw new ManagerEditorException("Error al ajustar el tamaño de un componente Texto.",ex);
        }
    }

    /**
     * Inicializa todos los listeners que posee un TextBox y un Texto.
     */
    @objid ("15b138d2-0680-4fdc-9a1a-6237dd0424c7")
    private void buildListeners() throws ManagerEditorException {
        this.listenerResized = new ControlAdapter() {
            
            @Override
            public void controlMoved(ControlEvent e) {
                try{
                texto.setPosicionCentral(texto.getPosicionCentral());
                }catch(Exception ex){}
            }
            
            @Override
            public void controlResized(ControlEvent e) {
                try{
                texto.setPosicionCentral(texto.getPosicionCentral());
                }catch(Exception ex){}
            }
        };
            
        /*this.listenerMenu = new MenuDetectListener() {
            public void menuDetected(MenuDetectEvent e) {
                e.doit = false;
            }
        };*/
        
        this.listenerClick = new Listener() {
            
            public void handleEvent(Event e) {
                try{
                manager.getStateManager().setTextoActivo(texto);
                manager.getStateManager().setPosicionCursor(manager.getStateManager().getTextoActivo().getPosicionCursor());
            
                GridExpression cua = manager.getStateManager().getTextoActivo().getCuadriculaPadre();
                while (!cua.esCuadriculaPadre())
                    cua = cua.getCuadriculaSuperior();
            
                cua.deseleccionarTodo();
                }catch(Exception ex){}
            }
        };
            
        this.listenerVerify = new VerifyListener() {
            
            public void verifyText(VerifyEvent e) {
                try{
                if (e.text.trim() != "") {
            
                    /*if (!FormatTextBox.esValido(e.text, texto.getFormato()) && e.text.length() == 1)
                        e.doit = false;
                    else {*/
                        ajustar(false);
                        texto.packSinTextBox();
                    //}
                }
                }catch(Exception ex){}
            }
        };
        
        this.listenerModify = new ModifyListener() {
        
            public void modifyText(ModifyEvent e) {
                try{
                    manager.getStateManager().setTextoActivo(texto);
                    manager.getStateManager().setPosicionCursor(texto.getPosicionCursor());
        
                    // Cuando cambia el contenido, se guarda el historial (para las acciones de deshacer/rehacer)
                    if (manager.getHistoryManager().getGuardar())
                        manager.getHistoryManager().guardarHistorial(false);
                
                    manager.getActionManager().cambiarEstado();
                    ajustar(true);
                }catch(Exception ex){}
            }
        };
            
        this.listenerFocus = new FocusListener() {
            
            // Recibe el foco
            public void focusGained(FocusEvent e){
                try{
                if ((manager.getStateManager().getTextoActivo() != null) && (!manager.getStateManager().getTextoActivo().isDisposed())) {
            
                    SimpleOperator textoAnterior = manager.getStateManager().getTextoActivoAnterior();
                    GridExpression cuaAnterior = textoAnterior.getCuadriculaPadre();
            
                    if ((textoAnterior != null) && (textoAnterior.getNumCaracteres() == 0)) {
                        if (cuaAnterior.getNumColumnas() > 1)
                            manager.getCentralManager().eliminarComponente(textoAnterior);
                    }
                }
                
                }catch(Exception ex){}
                
                manager.getStateManager().setTextoActivo(texto);
                if (!manager.getStateManager().getTextoActivo().isDisposed())
                    manager.getStateManager().setPosicionCursor(manager.getStateManager().getTextoActivo().getPosicionCursor());
            
            };
            
            // Pierde el foco
            public void focusLost(FocusEvent e) {};
        };
        
        
        try{
            this.listenerKey = new KeyAdapter() {
                
                @Override
                public void keyPressed(KeyEvent event) {                        
                    try{
                        /* Flecha derecha, flecha abajo */
                            if ((event.keyCode == SWT.ARROW_RIGHT) || (event.keyCode == SWT.ARROW_DOWN)) {
            
                                manager.getStateManager().setTextoActivo(texto);
                                manager.getStateManager().setPosicionCursor(texto.getPosicionCursor());
            
                                if (manager.getStateManager().getTextoActivo() != null) {
                                    SimpleOperator textoActual = manager.getStateManager().getTextoActivo();
                                    GridExpression cua = textoActual.getCuadriculaPadre();
                                    cua.deseleccionarTodo();
                                    int pos = manager.getStateManager().getPosicionCursor();
                                    try{
                                    if (textoActual.getTexto().length() == pos)
                                        setFocusNextComponente(textoActual);                                        
                                    else
                                        manager.getStateManager().setPosicionCursor(manager.getStateManager().getTextoActivo().getPosicionCursor() + 1);
                                    }catch(Exception ex){}
                                }
                            }
                        /* ****************************************************************/
        
                            
                        /* Flecha izquierda, flecha arriba */
                            else if ((event.keyCode == SWT.ARROW_LEFT) || (event.keyCode == SWT.ARROW_UP)) {
            
                                manager.getStateManager().setTextoActivo(texto);
                                manager.getStateManager().setPosicionCursor(texto.getPosicionCursor());
            
                                if (manager.getStateManager().getTextoActivo() != null) {
                                    SimpleOperator textoActual = manager.getStateManager().getTextoActivo();
                                    GridExpression cua = textoActual.getCuadriculaPadre();
                                    cua.deseleccionarTodo();
                                    int pos = manager.getStateManager().getPosicionCursor();
            
                                    if (0 == pos)
                                        setFocusPrevComponente(textoActual);
                                    else
                                        manager.getStateManager().setPosicionCursor(manager.getStateManager().getTextoActivo().getPosicionCursor() - 1);
                                }
                            }
                        /* ****************************************************************/
        
                        /* Back space */
                            else if (event.keyCode == SWT.BS) {
                                borrado(SWT.BS);
                                if (manager.getStateManager().getTextoActivo() != null) {
                                    SimpleOperator textoActual = manager.getStateManager().getTextoActivo();
                                    GridExpression cua = textoActual.getCuadriculaPadre();
                                    try{
                                    if ((cua.haySeleccion()) && (manager.getStateManager().getPosicionCursor() == 0)) {
                                        manager.getActionManager().setEstadoBtnEliminar(false);
                                        manager.getCentralManager().eliminarSeleccion(cua);
                                    }
                                    else {
            
                                        // si no hay selección, el cursor está en la
                                        // posición 0, y hay un componente antes que él, el
                                        // componente queda seleccionado
                                        if (manager.getStateManager().getPosicionCursor() == 0) {
                                            Control[] children = cua.getChildren();
                                            int i = 0;
                                            int posicionGrid = -1;
            
                                            // Obtengo la posición actual de texto dentro de
                                            // la cuadricula
                                            while ((i < children.length) && (posicionGrid == -1)) {
                                                if (textoActual == children[i])
                                                    posicionGrid = i;
                                                i++;
                                            }
            
                                            if (posicionGrid > 0) {
                                                if (((Operator) children[posicionGrid - 1]).getTipoComponente() != Category.TextType) {
                                                    Operator comp = (Operator) children[posicionGrid - 1];
                                                    comp.seleccionar();
                                                }
                                            }
                                        }
                                    }}catch(Exception ex){}
                                }
                            }
                        /* ****************************************************************/
        
                        /* Suprimir */
                            else if (event.keyCode == SWT.DEL) {
                                borrado(SWT.DEL);
                                if (manager.getStateManager().getTextoActivo() != null) {
                                    SimpleOperator textoActual = manager.getStateManager().getTextoActivo();
                                    GridExpression cua = textoActual.getCuadriculaPadre();
            
                                    //Si existe alguna selección
                                    if ((cua.haySeleccion()) && (manager.getStateManager().getPosicionCursor() == textoActual.getNumCaracteres())) {
                                        manager.getActionManager().setEstadoBtnEliminar(false);
                                        manager.getActionManager().actionDelete();
                                        event.doit = false;
                                    }
                                    else {
            
                                        if ((manager.getStateManager().getPosicionCursor() == textoActual.getNumCaracteres())) {
            
                                            Control[] children = cua.getChildren();
                                            int i = 0;
                                            int posicionGrid = -1;
            
                                            // Obtengo la posición actual de texto dentro de
                                            // la cuadricula
                                            while ((i < children.length) && (posicionGrid == -1)) {
                                                if (textoActual == children[i])
                                                    posicionGrid = i;
                                                i++;
                                            }
            
                                            if ((posicionGrid > -1) && (children.length > posicionGrid + 1) && (children.length > 1)) {
                                                if (((Operator) children[posicionGrid + 1]).getTipoComponente() != Category.TextType) {
                                                    Operator comp = (Operator) children[posicionGrid + 1];
                                                    comp.seleccionar();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        /* ****************************************************************/
        
                        /* Escape */
                            else if (event.keyCode == SWT.ESC) {
                                SimpleOperator textoActual = manager.getStateManager().getTextoActivo();
                                GridExpression cua = textoActual.getCuadriculaPadre();
            
                                if (cua.haySeleccion())
                                    manager.getCentralManager().getCuadriculaPadre().deseleccionarTodo();
                            }
                        /* ****************************************************************/
        
                        /* Enter, intro */
                            /*else if ((event.keyCode == SWT.CR) || (event.keyCode == SWT.KEYPAD_CR))
                                manager.getActionManager().actionExportMathML();*/
                        /* ****************************************************************/
        
                        /* Home, inicio */
                            else if (event.keyCode == SWT.HOME) { 
                                // hay que colocar el cursor en el primer Texto que haya en toda la expresión.
                                
                                // comprueba si está en la "cuadricula padre"
                                if (manager.getStateManager().getTextoActivo().getCuadriculaPadre().esCuadriculaPadre()) {
                                    Control[] children = manager.getStateManager().getTextoActivo().getCuadriculaPadre().getChildren();
                                    Operator c = (Operator) children[0];
                                    c.setFocoInicio();
                                }
                                else {
            
                                    GridExpression cua = manager.getStateManager().getTextoActivo().getCuadriculaPadre();
            
                                    while (!cua.esCuadriculaPadre())
                                        cua = cua.getCuadriculaSuperior();
            
                                    ((Operator) cua.getChildren()[0]).setFocoInicio();
                                }
                            }
                        /* ****************************************************************/
        
                        /* End, fin */
                            else if (event.keyCode == SWT.END) {
                                // hay que colocar el cursor en el último Texto que haya en toda la expresión.
            
                                // comprueba si está en la "cuadricula padre"
                                if (manager.getStateManager().getTextoActivo().getCuadriculaPadre().esCuadriculaPadre()) {
                                    Control[] children = manager.getStateManager().getTextoActivo().getCuadriculaPadre().getChildren();
                                    Operator c = (Operator) children[children.length - 1];
                                    c.setFocoFinal();
                                }
                                else {
                                    GridExpression cua = manager.getStateManager().getTextoActivo().getCuadriculaPadre();
                                    
                                    while (!cua.esCuadriculaPadre())
                                        cua = cua.getCuadriculaSuperior();
                                    
                                    Control[] children = cua.getChildren();
                                    ((Operator) children[children.length - 1]).setFocoFinal();
                                }
                            }
                        /* ****************************************************************/
                    
                        /* Ctrl + Z -> Deshacer */
                            else if (event.keyCode == 'z' && event.stateMask == SWT.CONTROL) {
                                event.doit = false;
                                manager.getActionManager().actionUndo();
                            }
                        /* ****************************************************************/
        
                        /* Ctrl + Y -> Rehacer */
                            else if (event.keyCode == 'y' && event.stateMask == SWT.CONTROL) {
                                event.doit = false;
                                manager.getActionManager().actionRedo();
                            }
                        /* ****************************************************************/
        
                        /* Ctrl + X -> (deshabilitado) */
                            else if (event.keyCode == 'x' && event.stateMask == SWT.CONTROL)
                                event.doit = false;
                        /* ****************************************************************/
        
                        /* Ctrl + V -> (deshabilitado paste) */
                            else if (event.keyCode == 'v' && event.stateMask == SWT.CONTROL)
                                event.doit = false;
                        /* ****************************************************************/
        
                        /* Ctrl + C -> Coordenada (deshabilitado copy) */
                            else if (event.keyCode == 'g' && event.stateMask == SWT.CONTROL){
                                manager.getCentralManager().insertarComponenteNoTexto("geometricPoint");
                            }
                        /* ****************************************************************/
        
                        /* Ctrl + R -> Raíz cuadrada */
                            else if (event.keyCode == 'r' && event.stateMask == SWT.CONTROL)
                                manager.getCentralManager().insertarComponenteNoTexto("squareRoot");
                        /* ****************************************************************/
        
                        /* Ctrl + F -> Fracción */
                            else if (event.keyCode == 'f' && event.stateMask == SWT.CONTROL){
                                manager.getCentralManager().insertarComponenteNoTexto("fraction");
                            }
                        /* ****************************************************************/
        
                        /* Ctrl + Q -> Exponente */
                            else if (event.keyCode == 'q' && event.stateMask == SWT.CONTROL){
                                
                                manager.getCentralManager().insertarComponenteNoTexto("exponent");
                            }
                        /* ****************************************************************/
        
                        /* Ctrl + P -> Decimal Periódico */
                            else if (event.keyCode == 'p' && event.stateMask == SWT.CONTROL)
                                manager.getCentralManager().insertarComponenteNoTexto("mixedRepeatingDecimal");
                        /* ****************************************************************/
                            
                            else{
                                //Cualquier caracter se inserta como minúscula
                                /*event.doit = false;
                                String newChar = String.valueOf(event.character).toLowerCase();
                                this.manager.getCentralManager().insertarCadena(newChar);*/
                            }
                            
                    }catch(Exception ex){}
                }
                
            };
        }catch(Exception ex){}
        
                /*}catch(Exception ex){
        System.out.println("Error");
        throw new ManagerEditorException("Error al detectar o ejecutar una acción desencadenada por una pulsación de tecla sobre un Texto.",ex);
                }*/
    }

    /**
     * Devuelve el listener que controla los clicks con el ratón sobre el TextBox.
     * @return Listener que controla los clicks con el ratón sobre el TextBox.
     */
    @objid ("77c417b3-34d1-4cfb-9fa5-bb60015008ed")
    public Listener getListenerClick() {
        return this.listenerClick;
    }

    /**
     * Devuelve el listener que controla si el TextBox recibe o pierde el foco.
     * @return Listener que controla si el TextBox recibe o pierde el foco.
     */
    @objid ("a7fc9f5c-76ca-4456-9736-bf7f4fed3ec9")
    public FocusListener getListenerFocus() {
        return this.listenerFocus;
    }

    /**
     * Devuelve el listener que controla las pulsaciones con el teclado cuando el foco está en el TextBox.
     * @return Listener que controla las pulsaciones con el teclado cuando el foco está en el TextBox.
     */
    @objid ("b640f9bd-36a1-42bd-bfde-2c3c59db882d")
    public KeyAdapter getListenerKey() {
        return this.listenerKey;
    }

    @objid ("16adf2ed-712d-45c7-adee-48a5b02af3a2")
    public KeyAdapter getListenerEnterKey() {
        return this.listenerEnterKey;
    }

/*public MenuDetectListener getListenerMenu() {
        return this.listenerMenu;
    }*/
    /**
     * Devuelve el listener que controla la ejecución de un menú contextual en el TextBox.
     * @return Listener que controla todos los cambios del texto de un TextBox.
     */
    @objid ("28783d2e-7f16-4cb0-9e0c-c9d52b53f1b8")
    public ModifyListener getListenerModify() {
        return this.listenerModify;
    }

    /**
     * Devuelve el controlador de los cambios de tamaño del componente Texto.
     * @return Controlador de los cambios de tamaño del componente Texto.
     */
    @objid ("4100738e-02af-44ad-a92b-7228207c1e5d")
    public ControlAdapter getListenerResized() {
        return this.listenerResized;
    }

    /**
     * Devuelve el listener que controla el texto introducido en un TextBox.
     * @return Listener que controla el texto introducido en un TextBox.
     */
    @objid ("c2c31b0d-c611-473e-98cf-204f2dc32782")
    public VerifyListener getListenerVerify() {
        return this.listenerVerify;
    }

    /**
     * Busca y establece el foco en el siguiente Texto que exista.
     * @param compInicial Componente sobre el que se encuentra el foco actual.
     */
    @objid ("59029221-ce5d-4d98-8f88-2327af5e7f32")
    private void setFocusNextComponente(Operator compInicial) throws ManagerEditorException {
        try{
            GridExpression cua = compInicial.getCuadriculaPadre();
            Control[] children = cua.getChildren();
            
            int i = 0;
            boolean salida = false;
            
            // ¿En qué columna del grid se encuentra el componente?
            while ((i < children.length) && (salida == false)) {
                if (compInicial == children[i])
                    salida = true;
                else
                    i++;
            }
            
            // ¿El componente se encuentra en el final de la cuadricula?
            if (children.length == i + 1) {
                // se encuentra al final
            
                boolean protocoloNormal = true;
            
                if (!cua.esCuadriculaPadre()) {
                    Operator compPadre = cua.getComponentePadre();
            
                    // si es binario y el cursor está al final de la primera cuadricula, el foco pasará a la segunda
                    if (compPadre.getTipoComponente() == Category.BinaryType) {
                        BinaryOperator bin = (BinaryOperator) compPadre;
            
                        if (cua == bin.getCuadriculaEdicion1()) {
                            Control[] con = bin.getCuadriculaEdicion2().getChildren();
                            if (con.length > 0) {
                                Operator cPrimero = (Operator) con[0];
            
                                if (cPrimero.getNombre() != Category.TextType)
                                    this.manager.getCentralManager().insertarTexto(bin.getCuadriculaEdicion2(), 0);
                                else
                                    cPrimero.setFocoInicio();
            
                                protocoloNormal = false;
                            }
                        }
                    }
            
                    if (compPadre.getTipoComponente() == Category.TernaryType) {
                        TernaryOperator ter = (TernaryOperator) compPadre;
            
                        if (cua == ter.getCuadriculaEdicion1()) {
                            Control[] con = ter.getCuadriculaEdicion2().getChildren();
                            if (con.length > 0) {
                                Operator cPrimero = (Operator) con[0];
            
                                if (cPrimero.getNombre() != Category.TextType)
                                    this.manager.getCentralManager().insertarTexto(ter.getCuadriculaEdicion2(), 0);
                                else
                                    cPrimero.setFocoInicio();
            
                                protocoloNormal = false;
                            }
                        }
            
                        if (cua == ter.getCuadriculaEdicion2()) {
                            Control[] con = ter.getCuadriculaEdicion3().getChildren();
                            if (con.length > 0) {
                                Operator cPrimero = (Operator) con[0];
            
                                if (cPrimero.getNombre() != Category.TextType)
                                    this.manager.getCentralManager().insertarTexto(ter.getCuadriculaEdicion3(), 0);
                                else
                                    cPrimero.setFocoInicio();
            
                                protocoloNormal = false;
                            }
                        }
            
                    }
            
                }
            
                // si el foco ha sido procesado por el módulo anterior, no entrara en éste
                if (protocoloNormal) {
            
                    // ¿la cuadricula es la "cuadricula padre"?
                    if (!cua.esCuadriculaPadre()) {
                        // no es cuadrícula padre
            
                        Operator compPadre = cua.getComponentePadre();
                        GridExpression cuaDelCompPadre = compPadre.getCuadriculaPadre();
                        Control[] childrenDeCuaDelPadre = cuaDelCompPadre.getChildren();
            
                        i = 0;
                        salida = false;
            
                        // ¿En que columna de la cuadricula se encuentra el padre? en i
                        while ((i < childrenDeCuaDelPadre.length) && (salida == false)) {
                            if (compPadre == childrenDeCuaDelPadre[i])
                                salida = true;
                            else
                                i++;
                        }
            
                        if ((compPadre.getNombre() != Category.TextType) && (i <= childrenDeCuaDelPadre.length - 1)) {
                            if (i < childrenDeCuaDelPadre.length - 1) {
            
                                if (((Operator) childrenDeCuaDelPadre[i + 1]).getNombre() != Category.TextType)
                                    this.manager.getCentralManager().insertarTexto(cuaDelCompPadre, i + 1);
                                else
                                    setFocusNextComponente(compPadre);
            
                            }
                            else
                                this.manager.getCentralManager().insertarTexto(cuaDelCompPadre, i + 1);
            
                        }
                        else
                            setFocusNextComponente(compPadre);
                    }
                    else {
                        /*
                         * Es la cuadrícula padre.
                         * Esto significa que el cursor se encuentra en el último componente de la expresión
                         * y no se puede recorrer más hacia la derecha.
                         * 
                         * Conclusión: No se hace nada.
                         */
                    }
                }
            
            }
            else {
                // no se encuentra al final
            
                if (salida) {
            
                    Operator compSig = (Operator) children[i + 1];
                    
                    try{
                    if (compSig.getNombre() != Category.TextType) {
            
                        Control[] childSigComp = null;
                        GridExpression cuaSigComp = null;
            
                        /*if(compSig.getTipoComponente() == Category.WithoutEntries){
                            
                            if(children.length > i+2){
                                compSig = (Operator) children[i + 2];
                                compSig.setFocoInicio();
                            }
                            else
                                this.manager.getCentralManager().insertarTexto(compSig.getCuadriculaPadre(), i+2);
                            
                        }
                        
                        else*/ if (compSig.getTipoComponente() == Category.TernaryType) {
                            cuaSigComp = ((TernaryOperator) compSig).getCuadriculaEdicion1();
                            childSigComp = cuaSigComp.getChildren();
                        }
                        else if (compSig.getTipoComponente() == Category.BinaryType) {
                            cuaSigComp = ((BinaryOperator) compSig).getCuadriculaEdicion1();
                            childSigComp = cuaSigComp.getChildren();
                        }
                        else if (compSig.getTipoComponente() == Category.UnaryType || compSig.getTipoComponente() == Category.UnaryComplexType) {
                            cuaSigComp = ((UnaryOperator) compSig).getCuadriculaEdicion1();
                            childSigComp = cuaSigComp.getChildren();
                        }
                        else if (compSig.getTipoComponente() == Category.UnaryType || compSig.getTipoComponente() == Category.WithoutEntriesType) {
                            ((WithoutEntriesOperator) compSig).setFocusNextOperator();
                        }
                        
                        
            
                        if (((Operator) childSigComp[0]).getNombre() != Category.TextType)
                            this.manager.getCentralManager().insertarTexto(cuaSigComp, 0);
                        else
                            compSig.setFocoInicio();
                    }
                    else
                        compSig.setFocoInicio();
                    }catch(Exception ex){}
                }
            
            }
        }catch(EditorException ex){
            throw new ManagerEditorException(ex);
        }catch(Exception ex){
            throw new ManagerEditorException("Error al poner el foco en el siguiente componente.",ex);
        }
    }

    /**
     * Busca y establece el foco en el anterior Texto que exista.
     * @param compInicial Componente sobre el que se encuentra el foco actual.
     */
    @objid ("2040de2f-1bea-42ca-904f-e32ee329bb11")
    private void setFocusPrevComponente(Operator compInicial) throws ManagerEditorException {
        try {
            GridExpression cua = compInicial.getCuadriculaPadre();
            Control[] children = cua.getChildren();
        
            int i = 0;
            boolean salida = false;
        
            // ¿En qué columna del grid se encuentra el cursor?
            while ((i < children.length) && (salida == false)) {
                if (compInicial == children[i])
                    salida = true;
                else
                    i++;
            }
        
            // ¿El componente se encuentra en el inicio de la cuadricula?
            if ((0 == i) && (salida)) {
                // se encuentra al inicio
        
                boolean protocoloNormal = true;
        
                if (!cua.esCuadriculaPadre()) {
                    Operator compPadre = cua.getComponentePadre();
        
                    // si es binario y el cursor está al inicio de la segunda cuadricula, el foco pasará al final de la primera
                    if (compPadre.getTipoComponente() == Category.BinaryType) {
                        BinaryOperator bin = (BinaryOperator) compPadre;
        
                        if (cua == bin.getCuadriculaEdicion2()) {
        
                            Control[] con = bin.getCuadriculaEdicion1().getChildren();
                            Operator cUltimo = (Operator) con[bin.getCuadriculaEdicion1().getChildren().length - 1];
                            if (cUltimo.getNombre() != Category.TextType)
                                this.manager.getCentralManager().insertarTexto(bin.getCuadriculaEdicion1(), bin.getCuadriculaEdicion1().getNumColumnas());
                            else {
                                cUltimo.setFocoFinal();
                            }
                            protocoloNormal = false;
                        }
                    }
        
                    // Ternario
                    if (compPadre.getTipoComponente() == Category.TernaryType) {
                        TernaryOperator ter = (TernaryOperator) compPadre;
        
                        if (cua == ter.getCuadriculaEdicion2()) {
        
                            Control[] con = ter.getCuadriculaEdicion1().getChildren();
                            Operator cUltimo = (Operator) con[ter.getCuadriculaEdicion1().getChildren().length - 1];
                            if (cUltimo.getNombre() != Category.TextType)
                                this.manager.getCentralManager().insertarTexto(ter.getCuadriculaEdicion1(), ter.getCuadriculaEdicion1().getNumColumnas());
                            else {
                                cUltimo.setFocoFinal();
                            }
                            protocoloNormal = false;
                        }
        
                        if (cua == ter.getCuadriculaEdicion3()) {
        
                            Control[] con = ter.getCuadriculaEdicion2().getChildren();
                            Operator cUltimo = (Operator) con[ter.getCuadriculaEdicion1().getChildren().length - 1];
                            if (cUltimo.getNombre() != Category.TextType)
                                this.manager.getCentralManager().insertarTexto(ter.getCuadriculaEdicion2(), ter.getCuadriculaEdicion2().getNumColumnas());
                            else {
                                cUltimo.setFocoFinal();
                            }
                            protocoloNormal = false;
                        }
        
                    }
                }
        
                // si el foco ha sido procesado por el módulo anterior, no entrara en éste
                if (protocoloNormal) {
        
                    // ¿la cuadricula es la "cuadricula padre"?
                    if (!cua.esCuadriculaPadre()) {
                        // no es cuadrícula padre
        
                        Operator compPadre = cua.getComponentePadre();
                        GridExpression cuaDelCompPadre = compPadre.getCuadriculaPadre();
        
                        Control[] childrenDeCuaDelPadre = cuaDelCompPadre.getChildren();
        
                        i = 0;
                        salida = false;
        
                        // ¿En qué columna de la cuadricula se encuentra el padre? en i
                        while ((i < childrenDeCuaDelPadre.length) && (salida == false)) {
                            if (compPadre == childrenDeCuaDelPadre[i])
                                salida = true;
                            else
                                i++;
                        }
        
                        if ((compPadre.getNombre() != Category.TextType) && (i >= 0)) {
                            if (i > 0) {
        
                                if (((Operator) childrenDeCuaDelPadre[i - 1]).getNombre() != Category.TextType)
                                    this.manager.getCentralManager().insertarTexto(cuaDelCompPadre, i);
                                else
                                    setFocusPrevComponente(compPadre);
                            }
                            else
                                this.manager.getCentralManager().insertarTexto(cuaDelCompPadre, i);
                        }
                        else
                            setFocusPrevComponente(compPadre);
        
                    }
                    else {
                        /*
                         * Es la cuadrícula padre.
                         * Esto significa que el cursor se encuentra en el último componente de la expresión
                         * y no se puede recorrer más hacia la derecha.
                         * 
                         * Conclusión: No se hace nada.
                         */
                    }
                }
            }
            else {
                // no se encuentra al inicio
        
                if ((salida) && (i != 0)) {
                    Operator compPrev = (Operator) children[i - 1];
        
                    if (compPrev.getNombre() != Category.TextType) {
        
                        Control[] childPrevComp = null;
                        GridExpression cuaPrevComp = null;
        
                        if (compPrev.getTipoComponente() == Category.TernaryType) {
                            cuaPrevComp = ((TernaryOperator) compPrev).getCuadriculaEdicion3();
                            childPrevComp = cuaPrevComp.getChildren();
                        }
                        else if (compPrev.getTipoComponente() == Category.BinaryType) {
                            cuaPrevComp = ((BinaryOperator) compPrev).getCuadriculaEdicion2();
                            childPrevComp = cuaPrevComp.getChildren();
                        }
                        else if (compPrev.getTipoComponente() == Category.UnaryType || compPrev.getTipoComponente() == Category.UnaryComplexType) {
                            cuaPrevComp = ((UnaryOperator) compPrev).getCuadriculaEdicion1();
                            childPrevComp = cuaPrevComp.getChildren();
                        }
                        else if (compPrev.getTipoComponente() == Category.UnaryType || compPrev.getTipoComponente() == Category.WithoutEntriesType) {
                            ((WithoutEntriesOperator) compPrev).setFocusPreviousOperator();
                        }
        
                        if (((Operator) childPrevComp[childPrevComp.length - 1]).getNombre() != Category.TextType)
                            this.manager.getCentralManager().insertarTexto(cuaPrevComp, childPrevComp.length);
                        else 
                            compPrev.setFocoFinal();
        
                    }
                    else
                        compPrev.setFocoFinal();
                }
            }
        }catch(EditorException ex){
            throw new ManagerEditorException(ex);
        }catch(Exception ex){
            throw new ManagerEditorException("Error al poner el foco en el componente anterior.",ex);
        }
    }

}
