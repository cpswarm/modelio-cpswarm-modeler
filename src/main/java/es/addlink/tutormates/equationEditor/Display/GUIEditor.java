package es.addlink.tutormates.equationEditor.Display;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ImportTranslationEditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Operators.GridExpression;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TabFolder;

/**
 * Clase encargada de construir la parte gráfica del editor.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("6075af53-a748-4dac-b833-9e3c48a7efc8")
public class GUIEditor {
    /**
     * Cuadrícula padre del editor.
     */
    @objid ("7154ed0c-bc71-410b-a693-2c9277aae0e9")
    private GridExpression gridExpression;

    /**
     * Interfaz gráfica que lanza el editor con el comprobador de MathML's.
     */
    @objid ("fd0cf77e-68d3-4017-8678-fcb3df7c6c60")
    private GUIMathMLTest guiTest;

    /**
     * Clase encargada de guardar la configuración global del editor. Solo existe una instancia por editor.
     */
    @objid ("8f9aae22-4837-47fa-9433-2d3548dd24d8")
    private Manager manager;

    /**
     * Composite que contiene todo el editor.
     */
    @objid ("c227a247-c615-4545-8d02-0746e9b6f3f6")
    private Composite editor;

    /**
     * Composite padre donde va alojado el editor.
     */
    @objid ("92e92cd1-061d-49a5-8eb4-861a2d7c5622")
    private Composite parent;

    /**
     * Constructor.
     * @param manager Configuración global.
     * @param parent Widget donde va alojado el editor.
     */
    @objid ("6d757e48-6483-4bfe-afb2-37681bbc2d92")
    public GUIEditor(Manager manager, Composite parent) {
        this.parent = parent;
        this.manager = manager;
    }

    @objid ("9e0d3a78-c511-4794-a45f-29024f951126")
    private void buildListenerCmpInferior(Composite cmp) {
        Listener listenerClick = new Listener() {
            
            public void handleEvent(Event e) {
                try{
                    manager.getCentralManager().insertarTextoAlFinal(gridExpression);
                }catch(Exception ex){}
            }
        };
        cmp.addListener(SWT.MouseDown, listenerClick);
    }

    /**
     * Crea la interfaz gráfica.
     * @param withMathMLTesting True si se desea añadir la interfaz de test de MathML's, false en caso contrario.
     */
    @objid ("3c13326a-b661-4e4b-8170-f55c45101d0f")
    public void createGUI(Boolean withMathMLTesting) {
        try{
            
            this.editor = new Composite(this.parent, SWT.NONE);
            this.editor.setLayout(new GridLayout(2, false));
            
            Composite cmpTabFolder = new Composite(editor, SWT.NONE);
            GridLayout grid = new GridLayout(1,false);
            grid.marginLeft = -5;
            grid.marginBottom = -5;
            grid.marginTop = -5;
            grid.marginRight = -5;
            cmpTabFolder.setLayout(grid);
            cmpTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            
            Composite cmpToolBars = new Composite(editor, SWT.LEFT);
            cmpToolBars.setLayout(new GridLayout(1, false));
            cmpToolBars.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
            
            /* Cada vez que se lanza el editor, se limpia el Historial */
                this.manager.getHistoryManager().cleanHistory();
            
            /* Parte Superior - Zona de botones */
                TabFolder tab = new TabFolder(cmpTabFolder, SWT.NONE);
                tab.setLocation(0, 0);
                GridData gridDataTab = new GridData(SWT.FILL, SWT.CENTER, true, false);
                tab.setLayoutData(gridDataTab);
                this.manager.buildMainToolBar(tab);
            /* ****************************************************************** */
            
            /* Parte Inferior - Zona de edición 4 */
                Color colorBlanco = new Color(this.parent.getDisplay(), 255, 255, 255);
        
                GridData gridDataCmp = new GridData(SWT.FILL);
                gridDataCmp.grabExcessHorizontalSpace = true;
                gridDataCmp.grabExcessVerticalSpace = true;
                gridDataCmp.horizontalAlignment = GridData.FILL;
                gridDataCmp.verticalAlignment = GridData.FILL;
                
                ScrolledComposite cmpInferior = new ScrolledComposite(cmpTabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
                cmpInferior.setAlwaysShowScrollBars(false);
                cmpInferior.setExpandHorizontal(false);
                cmpInferior.setExpandVertical(false);
                cmpInferior.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
                cmpInferior.setBackground(colorBlanco);
                buildListenerCmpInferior(cmpInferior);
                
                Composite cmpIntermedio = new Composite(cmpInferior, SWT.NONE);
                cmpInferior.setContent(cmpIntermedio);
                cmpIntermedio.setBackground(colorBlanco);
                this.gridExpression = new GridExpression(this.manager, cmpIntermedio);
                
                //if(!this.manager.getTutorMatesEditor().getCorrectExpresion())
                    //setAviso(cmpTabFolder,"Editor flexible para la libreta digital.\nDeshabilitado: importar, deshacer y rehacer (temporalmente).");
                
                //SelectionManager sc = new SelectionManager(cmpInferior, cmpTabFolder.getDisplay(), false);
                //sc.select();
                
                this.gridExpression.setEsCuadriculaPadre(true);
                this.gridExpression.setLayoutData(gridDataCmp);
                this.gridExpression.setBackground(colorBlanco);
                this.gridExpression.setLocation(5, 5);
            /* ****************************************************************** */
            
            /* Drop - Carga en el editor MathML's arrastrados a la zona de edición */    
                DropTarget dt = new DropTarget(cmpInferior, DND.DROP_MOVE);
                dt.setTransfer(new Transfer[] { TextTransfer.getInstance() });
                dt.addDropListener(new DropTargetAdapter() {
                  public void drop(DropTargetEvent event) {
                      try {
                        manager.getActionManager().importMathML((String)event.data);
                    }
                    catch (ImportTranslationEditorException e) {
                        // TODO Auto-generated catch block
                        //e.printStackTrace();
                    }catch(Exception ex){System.out.println("Editor [receive]: Expresión incorrecta.");}
                  }
                });
                
            /* Instancia de la clase singleton Historial. */
                this.manager.getHistoryManager().setCuadricula(this.gridExpression);
            
            /* Se incluye la barra de los botones deshacer, rehacer, cargar y editar. */
                this.manager.getActionsToolBar().constructToolBar(cmpToolBars);
                this.manager.getActionManager().setCmp(parent,cmpInferior);
                this.manager.getActionManager().setCuadriculaPadre(this.gridExpression);
            /* ****************************************************************** */
                if(withMathMLTesting){
                    this.guiTest = new GUIMathMLTest(this.editor,this.manager);
                    this.guiTest.createGUI();
                }
            
                insertFirstText();
        }catch(EditorException ex){
            MessageBox messageBox = new MessageBox(this.parent.getShell(), SWT.ICON_ERROR);
            messageBox.setMessage("\nHa ocurrido un error en el editor de expresiones. \n\n\n\nInformación técnica:\n\n" + ex.getExtendedError());
            messageBox.setText("Editor de expresiones");
            messageBox.open();
            
            Label lbl = new Label(this.editor.getParent(),SWT.NONE);
            lbl.setText(" No se muestra el editor porque existe algún error.");
            this.editor.dispose();
        }catch(Exception ex){
            MessageBox messageBox = new MessageBox(this.parent.getShell(), SWT.ICON_ERROR);
            messageBox.setMessage("\nHa ocurrido un error en el editor de expresiones.");
            messageBox.setText("Editor de expresiones");
            messageBox.open();
            
            Label lbl = new Label(this.editor.getParent(),SWT.NONE);
            lbl.setText(" No se muestra el editor porque existe algún error.");
            this.editor.dispose();
        }
    }

    /**
     * Devuelve la cuadrícula padre del editor.
     * @return La cuadrícula padre del editor.
     */
    @objid ("803a1f40-5e45-49c5-8e9a-057953d1b649")
    public GridExpression getCuadriculaPadre() {
        return this.gridExpression;
    }

    /**
     * Devuelve el composite que contiene todo el editor.
     * @return El composite que contiene todo el editor.
     */
    @objid ("fbfb798b-f249-4124-8e52-a7daf0924fdb")
    public Composite getEditor() {
        return this.editor;
    }

    @objid ("3eddcca9-73b9-4c26-b203-9ac0f7c4b6a2")
    public GUIMathMLTest getGUIMathMLTest() {
        return this.guiTest;
    }

    /**
     * Añade debajo de la zona de objetos una zona para poner avisos. Ideal cuando se entra en fases de prueba.
     * @param parent Lugar de alojamiento.
     * @param aviso Cadena a mostrar.
     */
    @objid ("1b2d01d8-8099-4104-b235-bc720d97650e")
    public void setAviso(Composite parent, String aviso) {
        Label lbl = new Label(parent,SWT.NONE);
        lbl.setFont(new Font(this.editor.getDisplay(),"Verdana",8,SWT.BOLD));
        lbl.setForeground(new Color(this.editor.getDisplay(),200,0,0));
        lbl.setText(aviso);
    }

    @objid ("20e7deac-3f17-4955-b856-a45b66c7c5b6")
    public void insertFirstText() {
        try{this.manager.getCentralManager().insertarTextoInicial(this.gridExpression);}catch(Exception ex){}
    }

    /**
     * Establece el foco en el primer componente del grid.
     */
    @objid ("66349358-7d25-41b2-9672-29d6722c63ab")
    public void setFocus() {
        try{
            this.manager.getCentralManager().setFocoPrimerComponente(this.getCuadriculaPadre());
        }catch(Exception ex){}
    }


/*
     * El siguiente código añade en la zona de abajo un listado de las funciones disponibles, al estilo de GeoGebra.
     * 
     * private void showFunctions(Composite cmpTabFolder){
        Composite cFunctions = new Composite(cmpTabFolder, SWT.NONE);
        GridLayout fill = new GridLayout(2, false);
        fill.marginBottom = -5;
        fill.marginTop = -5;
        
        cFunctions.setLayout(fill);
        
        Image img = SWTResourceManager.getImage(Main.class, "images/fx.png");
        Label lbl = new Label(cFunctions, SWT.NONE);
        lbl.setImage(img);
        
        Combo combo = new Combo (cFunctions, SWT.READ_ONLY);
        combo.setFont(new Font(cmpTabFolder.getDisplay(),"Verdana",7,SWT.NONE));
        
        if(StoreOperators.getArrayFunctionNames() != null){
            combo.setItems(StoreOperators.getArrayFunctionNames());
            combo.select(0);
        }
        
        combo.addListener (SWT.Selection, new Listener () {
            public void handleEvent (Event e) {
                try {
                    Combo b = (Combo)e.widget;
                    manager.getCentralManager().insertarFuncion(b.getText());
                }
                catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        //lbl.setEnabled(false);
        //combo.setEnabled(false);
        cFunctions.pack();
    }*/
}
