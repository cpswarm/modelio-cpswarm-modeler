package es.addlink.tutormates.equationEditor.Manager;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;
import es.addlink.tutormates.equationEditor.Role.ItemToolBar;
import es.addlink.tutormates.equationEditor.Role.TabToolBar;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;
import org.eclipse.swt.graphics.Image;
import org.jdom.Document;
import org.jdom.Element;
import org.modelio.module.cpswarm.utils.ResourcesManager;

/**
 * Obtiene y guarda todos los botones de la toolbar que pueden ser cargados en el editor.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("d706dd26-667c-491e-a6bc-0efb623b4660")
public class MainToolBarBaseManager {
    @objid ("c80394de-ef00-43e0-9c4b-1f0493b7dbca")
    private Document docToolBarBase;

    @objid ("d7e28c78-07ca-4f48-bbdf-65da1fc28ff4")
    private List<TabToolBar> tabToolBarList;

    @objid ("af9b5633-3c9d-4327-bcd5-739ad6b4189c")
    private List<ItemToolBar> itemToolBarList;

    @objid ("3ccb39a7-4918-4599-8a69-169df973384a")
    public MainToolBarBaseManager() {
        this.tabToolBarList = new Vector<>();
        this.itemToolBarList = new Vector<>();
        
        buildXMLFiles();
        buildToolBarBase();
    }

    @objid ("2bbf58ed-cf8d-4729-85dc-caa13e5db864")
    private void buildXMLFiles() {
        try {
            this.docToolBarBase = ResourcesManager.getInstance().getXMLDocument(PathManager.getToolBarFileName());
        }
        catch (FileEditorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @objid ("e751c14a-f6cc-44ea-a00d-43d32203a785")
    private void buildToolBarBase() {
        try {
            
            Element root = docToolBarBase.getRootElement();
            
            Element mainPalette = root.getChild("mainPalette");
            Element tabs = mainPalette.getChild("tabs");
            Element buttons = mainPalette.getChild("buttons");
            
            @SuppressWarnings("unchecked")
            List<Element> tabsList = tabs.getChildren();
            @SuppressWarnings("unchecked")
            List<Element> buttonsList = buttons.getChildren();
            
            Iterator<Element> iteTabs = tabsList.iterator();
            while(iteTabs.hasNext()){
                Element el = (Element)iteTabs.next();
                String name = el.getValue();
                Image icon = SWTResourceManager.getImage(GUIEditor.class, el.getAttributeValue("icon"));
                this.tabToolBarList.add(new TabToolBar(null,name,"",icon,-1,true,true));
            }
            
            Iterator<Element> iteButtons = buttonsList.iterator();
            while(iteButtons.hasNext()){
                Element el = (Element)iteButtons.next();
                
                String name = el.getValue();
                Image icon = SWTResourceManager.getImage(GUIEditor.class, el.getAttributeValue("icon"));
                Boolean isText = Boolean.valueOf(el.getAttributeValue("isText"));
                String operator = el.getAttributeValue("operator");
                String shortcut = el.getAttributeValue("shortcut");
                this.itemToolBarList.add(new ItemToolBar(icon, isText, operator, name, shortcut,"", true, true));
            }
            
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @objid ("4c696050-dd56-4531-8760-102fb3015490")
    public TabToolBar getTabToolBar(String name) {
        Iterator<TabToolBar> ite = this.tabToolBarList.iterator();
        while(ite.hasNext()){
            TabToolBar t = (TabToolBar)ite.next();
            if(t.getName().equalsIgnoreCase(name))
                return t;
        }
        return null;
    }

    @objid ("2f9da8e6-1122-4464-9732-dbcfe70903e9")
    public ItemToolBar getItemToolBar(String name) {
        Iterator<ItemToolBar> ite = this.itemToolBarList.iterator();
        while(ite.hasNext()){
            ItemToolBar i = (ItemToolBar)ite.next();
            if(i.getName().equalsIgnoreCase(name))
                return i;
        }
        return null;
    }


/**
     * La paleta lateral de acciones no se carga mediante xml. Ya que es una barra fija, se carga de forma manual en Display/ActionsToolBar.java.
     */
}
