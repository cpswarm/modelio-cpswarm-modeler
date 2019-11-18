package es.addlink.tutormates.equationEditor.Manager;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase que controla la acción de seleccionar componentes en el editor. Ejemplo de componente: Raiz, Fracción, Texto, etc.
 * Actualmente la funcionalidad que controlo esta clase, se encuentra desactivada.
 * 
 * @author Nuria García Santa
 */
@objid ("6eeac73c-b87b-432f-acbe-80c2531017e2")
public class SelectionManager {

/**
     * Composite sobre el que se controlarán los listeners de arrastrado y movimiento de ratón
     */
/*
    private Composite comp;

    */
/**
     * Color gris, utilizado para el background de los componentes cuando se encuentran seleccionados.
     */
/*
    private Color colorFondoSeleccionado;

    */
/**
     * Color utilizado para el background de los componentes cuando no se encuentran seleccionados.
     */
/*
    private final Color colorFondo;

    */
/**
     * Booleano que nos indica si se arrastra
     */
/*
    private boolean dragging;

    */
/**
     * Booleano que nos indica si el componente sobre el que se produce el evento también se selecciona
     */
/*
    private boolean add;

    */
/**
     * Punto que localiza la posición anterior en la que se encontraba el ratón respecto al estado actual
     */
/*
    private Point raton;

    */
/**
     * Punto que localiza la posición inicial del ratón al arrastrar
     */
/*
    private Point raton_inicial;

    */
/**
     * Entero que referencia la dirección que se sigue a la hora de seleccionar componentes. {0:posición origen, 1:hacia delante, 2:hacia atrás}
     */
/*
    private int dir;

    */
/**
     * Constructor.
     * 
     * @param comp en el que se contralarán los listeners de movimiento.
     * @param dis display del editor.
     * @param add booleano que indica si seleccionar o no componente que lanza el evento.
     */
/*
    public SelectionManager(Composite comp, Display dis, boolean add) {
        this.comp = comp;
        this.colorFondoSeleccionado = new Color(dis, 200, 200, 200);
        this.colorFondo = new Color(dis, 250, 250, 250);
        this.dragging = false;
        this.add = add;
        this.raton = null;
        this.dir = 0;
    }

    */
/**
     * Método que realiza la acción de seleccionar/deseleccionar componente controlando los listeners de movimiento 
     * @throws ManagerEditorException
     */
/*
    public void select() throws ManagerEditorException {
        try{
            comp.addDragDetectListener(new DragDetectListener() {
                @Override
                public void dragDetected(DragDetectEvent e) {
                    dragging = true;
                    raton_inicial = new Point(e.x, e.y);
                    raton = raton_inicial;
                    dir = 0;
                }
            });
            comp.addMouseMoveListener(new MouseMoveListener() {
                @Override
                public void mouseMove(final MouseEvent e) {
                    try{
                        if (!dragging)
                            return;
                        if (!hay_seleccion(Arrays.asList(comp.getChildren()))) {
                            ActionManager.getInstance().setEstadoBtnEliminar(false);
                        }
                        Point nuevo_raton = new Point(e.x, e.y);
                        boolean action = true;
                        if (dir == 0) {
                            if (raton.x < nuevo_raton.x)
                                dir = 1;
                            else {
                                dir = 2;
                            }
                        }
                        if (dir == 1) {
                            if (raton_inicial.x > nuevo_raton.x) {
                                action = true;
                                dir = 2;
                            }
                            else {
                                if (raton.x > nuevo_raton.x)
                                    action = false;
                                else {
                                    action = true;
                                }
                            }
                        }
                        if (dir == 2) {
                            if (raton_inicial.x < nuevo_raton.x) {
                                action = true;
                                dir = 1;
                            }
                            else {
                                if (raton.x < nuevo_raton.x)
                                    action = false;
                                else {
                                    action = true;
                                }
                            }
                        }
                        raton = nuevo_raton;
                        select_aux(Arrays.asList(comp.getChildren()), raton, action);
        
                        if (add) {
                            if (action) {
                                comp.setBackground(colorFondoSeleccionado);
                                ActionManager.getInstance().setEstadoBtnEliminar(true);
                            }
                            else {
                                comp.setBackground(colorFondo);
                                // ManagerAction.getInstance().setEstadoBtnEliminar(false);
                            }
                        }
                    }catch(Exception ex){}
                }
            });
            comp.addMouseListener(new MouseAdapter() {
                public void mouseUp(final MouseEvent e) {
                    if (!dragging || e.button != 1)
                        return;
                    dragging = false;
                    
                     * System.out.println("Dropped on" + new Point(e.x, e.y).toString());
                     
                }
            });
            comp.addMouseListener(new MouseAdapter() {
                public void mouseDown(final MouseEvent e) {
                    try{
                        if (!dragging) {
                            deseleccionar_todo(Arrays.asList(comp.getChildren()));
                            ActionManager.getInstance().setEstadoBtnEliminar(false);
                        }
                    }catch(Exception ex){}
                }
            });
            
        }catch(Exception ex){
            throw new ManagerEditorException("Error al seleccionar la expresión.",ex);
        }
    }

    */
/**
     * Método auxiliar que selecciona/deselecciona los componentes recursivamente
     * 
     * @param lista Lista con todos los componentes hijos del componente actual.
     * @param raton Posición del ratón en el estado actual.
     * @param action Indica si se selecciona{true} o deselecciona{false}.
     * @throws ManagerEditorException
     */
/*
    private void select_aux(List<Control> lista, Point raton, boolean action) throws ManagerEditorException {
        try{
            Control item = null;
            int i = 0;
            while (i < lista.size()) {
                item = lista.get(i);
                Point locItem = item.getLocation();
                // System.out.println(item.getClass().getName());
                if (raton.x > locItem.x && raton.x < locItem.x + item.getSize().x  && raton.y > locItem.y && raton.y < locItem.y + item.getSize().y ) {
    
                    
                     * if (item.getClass().getName().equals("org.eclipse.swt.widgets.Composite")) { Composite cmp = (Composite) item; if (action) { cmp.setBackground(colorFondoSeleccionado); } else { cmp.setBackground(colorFondo); } select_aux(Arrays.asList(cmp.getChildren()), raton, action); }
                     
                    if (item.getClass().getName().equals("es.addlink.tutormates.editor.Operators.GridExpression")) {
                        GridExpression cuad = (GridExpression) item;
                        
                         * if (action) { cuad.setBackground(colorFondoSeleccionado); ManagerAction.getInstance().setEstadoBtnEliminar(true); } else { cuad.setBackground(colorFondo); }
                         
                        select_aux(Arrays.asList(cuad.getChildren()), raton, action);
                    }
                    else {
                        try {
                            if (item.getClass().getName().substring(0, item.getClass().getName().lastIndexOf('.')).equals("es.addlink.tutormates.editor.Operators")) {
                                Operator comp = (Operator) item;
                                if (action) {
                                    comp.seleccionar();
                                    ActionManager.getInstance().setEstadoBtnEliminar(true);
                                }
                                else {
                                    comp.deseleccionar();
                                }
                                select_aux(Arrays.asList(comp.getChildren()), raton, action);
                            }
                            else {
                                if (action) {
                                    item.setBackground(colorFondoSeleccionado);
                                    ActionManager.getInstance().setEstadoBtnEliminar(true);
                                }
                                else {
                                    item.setBackground(colorFondo);
                                }
                            }
                        }
                        catch (Exception ex) {
                        }
                    }
                    
                     * if (item.getClass().getName().equals("es.addlink.tutormates.editor.Components.Texto")) { Componente texto = (Componente) item; if (action) { texto.seleccionar(false); } else { texto.deseleccionar(); } select_aux(Arrays.asList(texto.getChildren()), raton, action); } if (item.getClass().getName().equals("es.addlink.tutormates.editor.Components.Raiz")) { Raiz raiz = (Raiz) item; if (action) { raiz.seleccionar(false); } else { raiz.deseleccionar(); }
                     * select_aux(Arrays.asList(raiz.getChildren()), raton, action); } if (item.getClass().getName().equals("es.addlink.tutormates.editor.Components.Fraccion")) { Fraccion frac = (Fraccion) item; if (action) { frac.seleccionar(false); } else { frac.deseleccionar(); } select_aux(Arrays.asList(frac.getChildren()), raton, action); } if (item.getClass().getName().equals("es.addlink.tutormates.editor.Components.Exponente")) { Exponente ex = (Exponente) item; if (action) {
                     * ex.seleccionar(false); } else { ex.deseleccionar(); } select_aux(Arrays.asList(ex.getChildren()), raton, action); } if (item.getClass().getName().equals("es.addlink.tutormates.editor.Components.PeriodicoMixto")) { PeriodicoMixto dp = (PeriodicoMixto) item; if (action) { dp.seleccionar(false); } else { dp.deseleccionar(); } select_aux(Arrays.asList(dp.getChildren()), raton, action); } if (item.getClass().getName().equals("es.addlink.tutormates.editor.Components.PeriodicoPuro")) {
                     * PeriodicoPuro dp = (PeriodicoPuro) item; if (action) { dp.seleccionar(false); } else { dp.deseleccionar(); } select_aux(Arrays.asList(dp.getChildren()), raton, action); }
                     
                }
                comprobar_select(item, lista, action);
                i++;
            }
            
        }catch(EditorException ex){
            throw new ManagerEditorException(ex);
        }catch(Exception ex){
            throw new ManagerEditorException("Error al seleccionar recursivamente una expresión.",ex);
        }
    }

    public void comprobar_select(Control item, List<Control> lista, boolean action) {
        if (lista.indexOf(item) > 0 && lista.indexOf(item) < lista.size() - 1) {
            List<Control> anterior = lista.subList(0, lista.indexOf(item));
            // System.out.println(anterior.toString());
            List<Control> posterior = lista.subList(lista.indexOf(item) + 1, lista.size());
            // System.out.println(posterior.toString());
            try {
                if (item.getClass().getName().substring(0, item.getClass().getName().lastIndexOf('.')).equals("es.addlink.tutormates.editor.Operators")) {
                    Operator comp = (Operator) item;                    
                    if (!comp.estaSeleccionado()) {
                        if (action) {
                            //if ((dir == 1 && raton_inicial.x < comp.getLocation().x && raton.x > comp.getLocation().x) || (dir == 2 && raton_inicial.x > comp.getLocation().x && raton.x < comp.getLocation().x)) {
                                if(hay_seleccion(anterior) && hay_seleccion(posterior)){
                                    comp.seleccionar();
                                }
                            //}
                        }
                    }
                    else {
                        if(!action){
                            //if ((dir == 1 && raton_inicial.x < comp.getLocation().x && raton.x > comp.getLocation().x) || (dir == 2 && raton_inicial.x > comp.getLocation().x && raton.x < comp.getLocation().x)) {
                                if(!hay_seleccion(anterior) && !hay_seleccion(posterior)){
                                    //System.out.println(item.getClass().getName());
                                    comp.deseleccionar();
                                }
                            //}
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    */
/**
     * Método que deselecciona todos los componentes seleccionados del editor recursivamente
     * 
     * @param lista Lista con todos los componentes hijos del componente actual.
     * @throws ManagerEditorException
     */
/*
    public void deseleccionar_todo(List<Control> lista) throws ManagerEditorException {
        try{
            Control item = null;
            int i = 0;
            while (i < lista.size()) {
                item = lista.get(i);
                if (item.getClass().getName().equals("es.addlink.tutormates.editor.Operators.GridExpression")) {
                    GridExpression cuad = (GridExpression) item;
                    cuad.setBackground(colorFondo);
                    deseleccionar_todo(Arrays.asList(cuad.getChildren()));
                }
                else {
                    try {
                        if (item.getClass().getName().substring(0, item.getClass().getName().lastIndexOf('.')).equals("es.addlink.tutormates.editor.Operators")) {
                            Operator comp = (Operator) item;
                            comp.deseleccionar();
                            deseleccionar_todo(Arrays.asList(comp.getChildren()));
                        }
                        else {
                            item.setBackground(colorFondo);
                        }
                    }
                    catch (Exception ex) {
                    }
                }
                i++;
            }
            
        }catch(EditorException ex){
            throw new ManagerEditorException(ex);
        }catch(Exception ex){
            throw new ManagerEditorException("Error al deseleccionar toda la expresión.",ex);
        }
    }

    */
/**
     * Método que comprueba si algún componente está seleccionado
     * 
     * @param lista Lista con todos los componentes hijos del componente actual.
     * @return True si hay algo seleccionado, false en caso contrario.
     * @throws ManagerEditorException
     */
/*
    public boolean hay_seleccion(List<Control> lista) throws ManagerEditorException {
        try{
            Control item = null;
            boolean select_on = false;
            int i = 0;
            while (i < lista.size() && !select_on) {
                item = lista.get(i);
                if (item.getClass().getName().equals("es.addlink.tutormates.editor.Operators.GridExpression")) {
                    GridExpression cuad = (GridExpression) item;
                    try {
                        if (cuad.haySeleccion()) {
                            select_on = true;
                        }
                        else
                            select_on = hay_seleccion(Arrays.asList(cuad.getChildren()));
                    }
                    catch (Exception ex) {
                    }
                }
                else {
                    if (item.getClass().getName().substring(0, item.getClass().getName().lastIndexOf('.')).equals("es.addlink.tutormates.editor.Operators")) {
                        Operator comp = (Operator) item;
                        if (comp.estaSeleccionado()) {
                            select_on = true;
                        }
                        else
                            select_on = hay_seleccion(Arrays.asList(comp.getChildren()));
                    }
                    else {
                        if (item.getBackground().equals(colorFondoSeleccionado)) {
                            select_on = true;
                        }
                    }
                }
                i++;
            }
            return select_on;
            
        }catch(EditorException ex){
            throw new ManagerEditorException(ex);
        }catch(Exception ex){
            throw new ManagerEditorException("Error al detectar si existe alguna selección.",ex);
        }
    }*/
}
