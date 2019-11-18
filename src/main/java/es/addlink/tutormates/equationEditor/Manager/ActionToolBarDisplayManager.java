package es.addlink.tutormates.equationEditor.Manager;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.jdom.Document;
import org.jdom.Element;
import org.modelio.module.cpswarm.utils.ResourcesManager;

/**
 * Crea el ToolBar de acciones con sus ToolItems (deshacer,rehacer,eliminar,exportar,importar).
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("9fac1978-f96e-4d96-863c-fdc840bc5882")
public class ActionToolBarDisplayManager {
    @objid ("012767ad-b62f-42b7-ad11-a180ac53fb69")
    private Manager manager;

    @objid ("7016dba8-15df-42e3-969f-720521f08995")
    private GridLayout grid;

    @objid ("3d93416c-f43d-4292-8c7a-866d9eaee49d")
    private ToolItem exportToolItem;

    @objid ("416adbde-fb50-450d-a9be-f93428043f72")
    private ToolItem importToolItem;

    /**
     * Composite donde está situado el ToolBar.
     */
    @objid ("d1a35145-730b-4139-b448-82cb8fb461a3")
    private Composite parent;

    /**
     * @return
     */
    @objid ("b6676665-51aa-43d4-bb54-f71b0331f286")
    public ToolItem getExportToolItem() {
        return exportToolItem;
    }

    /**
     * @return
     */
    @objid ("b4e5017e-f962-4dc2-9e68-337f2829dcf0")
    public ToolItem getImportToolItem() {
        return importToolItem;
    }

    /**
     * Constructor.
     */
    @objid ("702fcd8a-a929-4fc5-9806-2ae74cf72d4b")
    public ActionToolBarDisplayManager(Manager manager) {
        this.manager = manager;
        grid = new GridLayout(6,false);
        grid.horizontalSpacing = 0;
        grid.verticalSpacing = 0;
        grid.numColumns = 1;
        grid.marginTop = 13;
        grid.marginLeft = -5;
        grid.marginRight = -5;
        grid.marginBottom = -5;
    }

    @objid ("9ae37355-5e74-4e6d-8e7e-9d78d124e07e")
    public void constructToolBar(Composite parent) throws ManagerEditorException {
        try {
            
            this.parent = parent;
            this.parent.setLayout(grid);
            
            
                
            /* Load file: toolBar.xml */
                Document toolbarDoc = ResourcesManager.getInstance().getXMLDocument(PathManager.getToolBarFileName());
                Element toolbarRoot = toolbarDoc.getRootElement();
        
            /* Get language */
                Document languageDoc = this.manager.getLanguageManager().getDocument();
                if(languageDoc != null){
                    Element languageRoot = languageDoc.getRootElement();
                    buildToolBars(toolbarRoot, languageRoot);
                }
            }
            catch (Exception e) {
                throw new ManagerEditorException("Ha ocurrido algún problema mientras se construía la ToolBar, y no ha podido ser cargada.",e);
            }
    }

    /**
     * Crea un ToolBar por cada botón.
     */
    @objid ("c78de657-c6cc-4ad4-a485-bec5c7be2d67")
    public void buildToolBars(Element toolbarRoot, Element languageRoot) {
        Element elActions = toolbarRoot.getChild("lateralPalette");
        
        Element lanToolBars = languageRoot.getChild("toolBars");
        Element lanActions = lanToolBars.getChild("actions");
        
        //Element lanActions = lRoot.getChild("actions");
        
        ToolBar toolbar;
        
        Image img;
        
        // Deshacer - Undo
        toolbar = new ToolBar(parent, SWT.NONE);
        
        Element elUndo = elActions.getChild("undo");
        img = SWTResourceManager.getImage(GUIEditor.class, elUndo.getAttributeValue("icon"));
        toolbar.setToolTipText(lanActions.getChild("undo").getValue() + " (Ctrl+Z)");
        
        ToolItem toolItemUndo = new ToolItem(toolbar, SWT.NONE);
        toolItemUndo.setImage(img);
        toolItemUndo.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                try{
                    manager.getActionManager().actionUndo();
                }catch(Exception ex){}
            }
        });
        toolItemUndo.setEnabled(false);
        
        // Rehacer - Redo
        toolbar = new ToolBar(parent, SWT.NONE);
        
        Element elRedo = elActions.getChild("redo");
        img = SWTResourceManager.getImage(GUIEditor.class, elRedo.getAttributeValue("icon"));
        toolbar.setToolTipText(lanActions.getChild("redo").getValue() + " (Ctrl+Y)");
        
        ToolItem toolItemRedo = new ToolItem(toolbar, SWT.NONE);
        toolItemRedo.setImage(img);
        toolItemRedo.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                try{
                manager.getActionManager().actionRedo();
                }catch(Exception ex){}
            }
        });
        toolbar.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1));
        toolItemRedo.setEnabled(false);
        
        // Eliminar
        toolbar = new ToolBar(parent, SWT.NONE);
        
        Element elRemove = elActions.getChild("remove");
        img = SWTResourceManager.getImage(GUIEditor.class, elRemove.getAttributeValue("icon"));
        toolbar.setToolTipText(lanActions.getChild("remove").getValue());
        
        ToolItem toolItemDelete = new ToolItem(toolbar, SWT.NONE);
        toolItemDelete.setImage(img);
        toolItemDelete.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                try{
                    manager.getActionManager().actionDelete();
                }catch(Exception ex){}
            }
        });
        toolbar.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1));
        //toolItemDelete.setEnabled(false);
        
        
        // Separador
        Label lbl = new Label(parent,SWT.NONE);
        lbl.setFont(new Font(parent.getDisplay(), "Courier New", 4, SWT.NONE));
        
        // Exportar - Export
        toolbar = new ToolBar(parent, SWT.NONE);
        
        Element elExport = elActions.getChild("export");
        img = SWTResourceManager.getImage(GUIEditor.class, elExport.getAttributeValue("icon"));
        toolbar.setToolTipText(lanActions.getChild("export").getValue() + " (Enter)");
        
        this.exportToolItem = new ToolItem(toolbar, SWT.NONE);
        if(this.manager.getTutorMatesEditor().getCorrectExpresion())
            this.exportToolItem.setEnabled(false);
        this.exportToolItem.setImage(img);
        
        GridData gd = new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1);
        toolbar.setLayoutData(gd);
        
        // Importar - Import
        toolbar = new ToolBar(parent, SWT.NONE);
        Element elImport = elActions.getChild("import");
        img = SWTResourceManager.getImage(GUIEditor.class, elImport.getAttributeValue("icon"));
        toolbar.setToolTipText(lanActions.getChild("import").getValue());
        this.importToolItem = new ToolItem(toolbar, SWT.NONE);
        this.importToolItem.setImage(img);
        if(!this.manager.getTutorMatesEditor().getCorrectExpresion())
            this.importToolItem.setEnabled(false);
        
        this.manager.getActionManager().setItems(toolItemUndo, toolItemRedo, toolItemDelete, this.exportToolItem, this.importToolItem);
    }

}
