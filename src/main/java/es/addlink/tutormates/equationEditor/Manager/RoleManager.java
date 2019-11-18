package es.addlink.tutormates.equationEditor.Manager;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;
import es.addlink.tutormates.equationEditor.Role.BaseFunction;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;
import es.addlink.tutormates.equationEditor.Role.ItemToolBar;
import es.addlink.tutormates.equationEditor.Role.Role;
import es.addlink.tutormates.equationEditor.Role.TabToolBar;
import org.jdom.Document;
import org.jdom.Element;
import org.modelio.module.cpswarm.utils.ResourcesManager;

/**
 * Obtiene y guarda todos los botones de la toolbar que pueden ser cargados en el editor.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("c50e7f69-3551-4ad6-af83-205bb99658ce")
public class RoleManager {
    @objid ("249b7cf6-8beb-4abc-9e83-035dd624438f")
    private String profile;

    @objid ("cb2c4e81-68f7-4a02-ad5e-7f1510bc5da1")
    private String course;

    @objid ("ea59f937-6e85-4958-93fb-1f7b9c463bae")
    private String unit;

    @objid ("a065682c-719f-44cc-b297-7ed4a8936909")
    private String fileName;

    @objid ("e67f3895-9210-4360-a1f3-73a391e9ca54")
    private Role role;

    @objid ("303c6e21-4024-4936-b170-2c6698049ebe")
    private Document docXMLRole;

    @objid ("7fc12c4d-a299-4b14-b484-f4d083a2120d")
    private MainToolBarBaseManager toolBarManager;

    @objid ("e9f63002-ef53-4068-a5d0-b6f029fcc73a")
    private OperatorsBaseManager operatorsBaseManager;

    @objid ("c8d88a02-f509-4e90-a01e-45d58abbceba")
    private Manager manager;

    @objid ("f685a1c4-a9e9-490e-8287-a7aad1b214b3")
    private Element toolBarLanguage;

    @objid ("6f8f16b0-4863-48bc-9407-f187d607f20e")
    public RoleManager(Manager manager, String profile, String course, String unit) {
        super();
        try {
            this.manager = manager;
            this.operatorsBaseManager = new OperatorsBaseManager();
            this.toolBarManager = new MainToolBarBaseManager();
            this.profile = profile;
            this.course = course;
            this.unit = unit;
            this.fileName = getFileName();
            
            Document docLanguage = this.manager.getLanguageManager().getDocument();
            Element rootLanguage = docLanguage.getRootElement();
            this.toolBarLanguage = rootLanguage.getChild("toolBars");
            loadToolBarXML();
            
            this.role = new Role(this.profile,this.course,this.unit,getOperatorsList(),getFunctionsList(),getVariablesList(),getTabsToolBar());
        }
        catch (FileEditorException e) {
            e.printStackTrace();
        }
    }

    @objid ("260ce652-2dac-4eae-8572-bc5eac9a47fc")
    private List<BaseOperator> getOperatorsList() {
        try {
            List<BaseOperator> result = new Vector<>();
            
            Document doc = ResourcesManager.getInstance().getXMLDocument(this.fileName);
            Element root = doc.getRootElement();
            Element operators = root.getChild("operators");
            
            @SuppressWarnings("unchecked")
            List<Element> nodeList = operators.getChildren();
            
            Iterator<Element> ite = nodeList.iterator();
            while(ite.hasNext()){
                Element el = (Element)ite.next();
                int id = Integer.valueOf(el.getValue());
                BaseOperator ope = this.operatorsBaseManager.getOperator(id);
                if(ope != null)
                    result.add(ope);
            }
            
            return result;
        }
        catch (FileEditorException e) {
            // TODO Auto-gnerated catch block
            e.printStackTrace();
            return null;
        }
    }

    @objid ("4154b579-706d-43df-86a0-c3ed6dc3bfc1")
    private List<BaseFunction> getFunctionsList() {
        try {
            List<BaseFunction> result = new Vector<>();
            
            Document doc = ResourcesManager.getInstance().getXMLDocument(this.fileName);
            Element root = doc.getRootElement();
            Element operators = root.getChild("functions");
            
            @SuppressWarnings("unchecked")
            List<Element> nodeList = operators.getChildren();
            
            Iterator<Element> ite = nodeList.iterator();
            while(ite.hasNext()){
                Element el = (Element)ite.next();
                String f = el.getValue();
                BaseFunction function = this.operatorsBaseManager.getFunction(f);
                if(function != null)
                    result.add(function);
            }
            
            return result;
        }
        catch (FileEditorException e) {
            // TODO Auto-gnerated catch block
            e.printStackTrace();
            return null;
        }
    }

    @objid ("03f49794-61b1-4ff3-bbe3-ef571dbee86c")
    private List<BaseOperator> getVariablesList() {
        try {
            List<BaseOperator> result = new Vector<BaseOperator>();
            
            Document doc = ResourcesManager.getInstance().getXMLDocument("role_all.xml");
            Element root = doc.getRootElement();
            Element operators = root.getChild("variables");
            
            @SuppressWarnings("unchecked")
            List<Element> nodeList = operators.getChildren();
            
            Iterator<Element> ite = nodeList.iterator();
            while(ite.hasNext()){
                Element el = (Element)ite.next();
                String name = el.getValue();
                BaseOperator ope = this.operatorsBaseManager.getVariableOrNumber(name);
                if(ope != null)
                    result.add(ope);
            }
            
            Element numbers = root.getChild("numbers");
            @SuppressWarnings("unchecked")
            List<Element> node = numbers.getChildren();
            
            Iterator<Element> i = node.iterator();
            while(i.hasNext()){
                Element el = (Element)i.next();
                String name = el.getValue();
                BaseOperator ope = this.operatorsBaseManager.getVariableOrNumber(name);
                if(ope != null)
                    result.add(ope);
            }
            
            return result;
        }
        catch (FileEditorException e) {
            // TODO Auto-gnerated catch block
            e.printStackTrace();
            return null;
        }
    }

    @objid ("0bcccbd7-84ec-4334-8500-febf33762e5a")
    private void loadToolBarXML() {
        try {
            
            this.docXMLRole = ResourcesManager.getInstance().getXMLDocument(this.fileName);
            
            if(this.profile.equalsIgnoreCase(""))
                System.out.print("# Editor: Loading profile. ");
            else{
                if(!this.course.equalsIgnoreCase(""))
                    System.out.print("# Editor: Loading profile ["+ profile + "|" + course);
                else
                    System.out.print("# Editor: Loading profile ["+ profile);
                    
                if(!this.unit.equalsIgnoreCase(""))
                    System.out.print("|" + unit + "]. ");
                else
                    System.out.print("]. ");
            }
            System.out.println("Language '" + this.manager.getLanguageManager().getLanguage() + "'. Profile file '" + this.fileName + "'.");
            
        }catch (FileEditorException e) {
            findNewProfile();
        }
    }

    @objid ("52ca2417-36da-4d53-b058-8039342d3d6a")
    private void findNewProfile() {
        if(this.profile.equalsIgnoreCase("")){
            this.profile = "";
            this.course = "";
            this.unit = "";
            this.fileName = getFileName();
            loadToolBarXML();
        }
        else{
            if(!this.course.equalsIgnoreCase("")){
                this.fileName = "role_" + this.profile + "_" + this.course + ".xml";
                this.unit = "";
                this.fileName = getFileName();
                loadToolBarXML();
            }
            else{
                this.profile = "";
                this.course = "";
                this.unit = "";
                this.fileName = getFileName();
                loadToolBarXML();
            }
        }
    }

    @objid ("4c6378a8-d8d8-44de-98bd-55c9ed7c1755")
    public Role getRole() {
        return this.role;
    }

    @objid ("ce3d7493-95b8-4d40-a6e3-cbf3d8d04f70")
    private List<TabToolBar> getTabsToolBar() {
        List<TabToolBar> listTabs = new Vector<TabToolBar>();
        List<ItemToolBar> listItems;
        
        Element root = this.docXMLRole.getRootElement();
        Element toolbar = root.getChild("toolBar");
        
        @SuppressWarnings("unchecked")
        List<Element> nodeList = toolbar.getChildren();
        
        Iterator<Element> iterator = nodeList.iterator();
        while(iterator.hasNext()){
            Element el = (Element)iterator.next();
            
            // Tabs  toolbar
            String name = el.getAttributeValue("name");
            
            TabToolBar tabBase = this.toolBarManager.getTabToolBar(name);
            
            if(tabBase != null){
                int columns = Integer.valueOf(el.getAttributeValue("columns"));
                Boolean visible = Boolean.valueOf(el.getAttributeValue("visible"));
                Boolean enabled = Boolean.valueOf(el.getAttributeValue("enabled"));
                
                // Items toolbar
                @SuppressWarnings("unchecked")
                List<Element> nodeButton = el.getChildren();
                Iterator<Element> ite = nodeButton.iterator();
                listItems = new Vector<ItemToolBar>();
                while(ite.hasNext()){
                    Element e = (Element)ite.next();
                    String name2 = e.getValue();
                    
                    ItemToolBar itemBase = this.toolBarManager.getItemToolBar(name2);
                    
                    if(itemBase != null){
                        Boolean visible2 = Boolean.valueOf(e.getAttributeValue("visible"));
                        Boolean enabled2 = Boolean.valueOf(e.getAttributeValue("enabled"));
                        if(visible2 == true)
                            listItems.add(new ItemToolBar(itemBase.getIcon(),itemBase.isText(),itemBase.getOperator(),name2,itemBase.getShortcut(),getButtonTraduction(name2),visible2,enabled2));
                    }
                }
                
                listTabs.add(new TabToolBar(listItems,name,getTabTraduction(name),tabBase.getIcon(),columns,visible,enabled));
            }
        }
        return listTabs;
    }

    @objid ("f5815ff0-f4b4-4f6e-9c9a-eb89fa6b9190")
    private String getTabTraduction(String name) {
        Element tabsLanguage = toolBarLanguage.getChild("tabs");
        Element el = tabsLanguage.getChild(name);
        
        if(el != null) return el.getValue();
        else return "s/n";
    }

    @objid ("f8679721-3c71-4b30-92a1-8cf49825b4a9")
    private String getButtonTraduction(String name) {
        Element buttonsLanguage = toolBarLanguage.getChild("buttons");
        Element el = buttonsLanguage.getChild(name);
        
        if(el != null) return el.getValue();
        else return "";
    }

    @objid ("d40fafcc-3dda-465d-97a7-23ee7c456826")
    public String getProfile() {
        return profile;
    }

    @objid ("8c3d99d0-5009-4cef-99d1-4f4e4e3f9df5")
    public String getCourse() {
        return course;
    }

    @objid ("60fde57e-0a0f-49f7-a377-231173216751")
    public String getUnit() {
        return unit;
    }

    @objid ("587662a1-4e37-49f6-bdbf-c14f0674fbf7")
    private String getFileName() {
        String fileName = "role";
        
        if(!profile.equalsIgnoreCase(""))
            fileName += "_" + profile;
        if(!course.equalsIgnoreCase(""))
            fileName += "_" + course;
        if(!unit.equalsIgnoreCase(""))
            fileName += "_" + unit;
        
        fileName += ".xml";
        
        if(profile.equalsIgnoreCase("profesor") || profile.equalsIgnoreCase(""))
            fileName = "role_Profesor.xml";
        
        if(profile.equalsIgnoreCase("") && course.equalsIgnoreCase("") && unit.equalsIgnoreCase(""))
            fileName = "role_Profesor.xml";
        
        if(profile.equalsIgnoreCase("all"))
            fileName = "role_all.xml";
        return fileName;
    }

}
