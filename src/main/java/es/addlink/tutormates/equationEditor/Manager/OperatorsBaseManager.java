package es.addlink.tutormates.equationEditor.Manager;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;
import es.addlink.tutormates.equationEditor.Formats.MathML.MathML;
import es.addlink.tutormates.equationEditor.Formats.MathML.MathMLAttribute;
import es.addlink.tutormates.equationEditor.Role.BaseFunction;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;
import org.jdom.Document;
import org.jdom.Element;
import org.modelio.module.cpswarm.utils.ResourcesManager;

/**
 * Obtiene y guarda todos los operadores que pueden ser cargados en el editor.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("f9baedeb-2d26-4d44-9d4a-b44965d56866")
public class OperatorsBaseManager {
    @objid ("4ea9f369-018d-41d4-90ee-9b6a765cdc29")
    private List<BaseOperator> numbersVariablesList;

    @objid ("f28dd37e-3a66-4b06-9b3c-640b1e056d19")
    private List<BaseOperator> operatorsList;

    @objid ("3be3e507-e459-49b7-b843-24cf3f970f13")
    private List<BaseFunction> functionsList;

    @objid ("bd9cda68-9a40-4ae4-85a7-7389262369a5")
    private Document doc;

    @objid ("93fc23f6-7ff2-4a16-9490-a731f84f4d62")
    public OperatorsBaseManager() {
        try {
            this.operatorsList = new Vector<>();
            this.functionsList = new Vector<>();
            this.numbersVariablesList = new Vector<>();
            
            this.doc = ResourcesManager.getInstance().getXMLDocument("allowedOperators.xml");
            buildOperators();
            buildFunctions();
            buildNumbersVariables("numbers");
            buildNumbersVariables("variables");            
        }
        catch (FileEditorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @objid ("6c9fc5df-22bd-4de2-a2b0-d77615d2dfd0")
    private void buildOperators() {
        Element root = this.doc.getRootElement();
        Element unaryOperators = root.getChild("operators");
        @SuppressWarnings("unchecked")
        List<Element> nodeList = unaryOperators.getChildren();
        
        /* Carga todos los operadores con sus MathML incluídos *********************************/
        
            Iterator<Element> iterator = nodeList.iterator();
            while(iterator.hasNext()){
                Element el = iterator.next();
                @SuppressWarnings("unchecked")
                List<Element> lis = el.getChildren();
                
                MathML mathML = null;
                
                if(lis.get(0).getChildren().size() > 0){
                    List<MathMLAttribute> lisAt = new Vector<MathMLAttribute>();
                    
                    /* Carga los atributos de cada MathML de cada operador *********************/
                        @SuppressWarnings("unchecked")
                        Iterator<Element> ite = lis.get(0).getChildren().iterator();
                        while(ite.hasNext()){
                            Element elAt = ite.next();
                            
                            @SuppressWarnings("unchecked")
                            Iterator<Element> itera = elAt.getChildren().iterator();
                            while(itera.hasNext()){
                                Element element = itera.next();
                                lisAt.add(new MathMLAttribute(element.getName(),element.getValue()));
                            }
                            
                        }
                        mathML = new MathML(lisAt,lis.get(0).getAttributeValue("label"),false,false);
                    /* *************************************************************************/
                        
                }else
                    mathML = new MathML(null,lis.get(0).getAttributeValue("label"),false,false);
                
                BaseOperator ope = new BaseOperator(Integer.valueOf(el.getAttributeValue("id")),Boolean.valueOf(el.getAttributeValue("isText")),el.getAttributeValue("name"),mathML,el.getAttributeValue("symbolEditor"),el.getAttributeValue("symbolMathML"),el.getAttributeValue("type"),el.getAttributeValue("pos"),Integer.valueOf(el.getAttributeValue("priority")));
                operatorsList.add(ope);                
            }
    }

    @objid ("2ecd514d-2fff-4a20-b2a2-e25ca2f805df")
    private void buildFunctions() {
        try{
            Element root = this.doc.getRootElement();
            Element functions = root.getChild("functions");
            
            @SuppressWarnings("unchecked")
            List<Element> nodeList = functions.getChildren();
            
            /* Carga todos los operadores con sus MathML incluídos *********************************/
            
                Iterator<Element> iterator = nodeList.iterator();
                while(iterator.hasNext()){
                    Element el = iterator.next();
                    
                    @SuppressWarnings("unchecked")
                    List<Element> lis = el.getChildren();
                    
                    String name = el.getAttributeValue("name");
                    int entries = Integer.valueOf(el.getAttributeValue("entries"));
                    /*int idOperator = -1;
                    if(!el.getAttributeValue("idOperator").equals(""))
                        idOperator = Integer.valueOf(el.getAttributeValue("idOperator"));
                    */
                    
                    MathML mathML = new MathML(null,lis.get(0).getAttributeValue("label"),Boolean.valueOf(lis.get(0).getAttributeValue("functionType")),Boolean.valueOf(lis.get(0).getAttributeValue("withinLabel")));
                    
                    BaseFunction af = new BaseFunction(name,entries,null,mathML);
                    this.functionsList.add(af);
                }
            /* ************************************************************************************/
            }catch(Exception ex){
                ex.printStackTrace();
            }
    }

    @objid ("4a802518-57ce-4739-8aa5-4c0940f1069e")
    private void buildNumbersVariables(String group) {
        try{
            Element root = this.doc.getRootElement();
            Element rootNumbersVariables = root.getChild("others");
            Element unaryOperators = rootNumbersVariables.getChild(group);
            
            @SuppressWarnings("unchecked")
            List<Element> nodeList = unaryOperators.getChildren();
            
            /* Carga todos los operadores con sus MathML incluídos *********************************/
                
                Iterator<Element> iterator = nodeList.iterator();
                while(iterator.hasNext()){
                    Element el = iterator.next();
                    
                    @SuppressWarnings("unchecked")
                    List<Element> lis = el.getChildren();
                    
                    MathML mathML = null;
                    
                    if(lis.get(0).getChildren().size() > 0){
                        List<MathMLAttribute> lisAt = new Vector<MathMLAttribute>();
                        
                        /* Carga los atributos de cada MathML de cada operador *********************/
                            @SuppressWarnings("unchecked")
                            Iterator<Element> ite = lis.get(0).getChildren().iterator();
                            while(ite.hasNext()){
                                Element elAt = ite.next();
                                lisAt.add(new MathMLAttribute(elAt.getName(),elAt.getValue()));
                            }
                            mathML = new MathML(lisAt,lis.get(0).getAttributeValue("label"),false,false);
                        /* *************************************************************************/
                            
                    }else
                        mathML = new MathML(null,lis.get(0).getAttributeValue("label"),false,false);
            
                    BaseOperator ope = new BaseOperator(Integer.valueOf(el.getAttributeValue("id")),Boolean.valueOf(el.getAttributeValue("isText")),el.getAttributeValue("name"),mathML,el.getAttributeValue("symbolEditor"),el.getAttributeValue("symbolMathML"),el.getAttributeValue("type"),el.getAttributeValue("pos"),Integer.valueOf(el.getAttributeValue("priority")));
                    this.numbersVariablesList.add(ope);                
                }
                
            /* ************************************************************************************/
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @objid ("29181ca9-4ada-4f3a-9f5b-88a2dc35497b")
    public BaseOperator getOperator(int id) {
        BaseOperator result = null;
        
        Iterator<BaseOperator> ite = this.operatorsList.iterator();
        while(ite.hasNext() && result == null){
            BaseOperator ao = (BaseOperator)ite.next();
            if(ao.getId() == id)
                result = ao;
        }
        return result;
    }

    @objid ("9be06117-a7c5-4075-8213-271e8786b79b")
    public BaseOperator getVariableOrNumber(String name) {
        BaseOperator result = null;
        
        Iterator<BaseOperator> ite = this.numbersVariablesList.iterator();
        while(ite.hasNext() && result == null){
            BaseOperator ao = (BaseOperator)ite.next();
            if(ao.getName().equalsIgnoreCase(name))
                result = ao;
        }
        return result;
    }

    @objid ("0af979e5-8519-4f63-a559-0aafed6716e1")
    public BaseFunction getFunction(String function) {
        BaseFunction result = null;
        
        Iterator<BaseFunction> ite = this.functionsList.iterator();
        while(ite.hasNext() && result == null){
            BaseFunction af = (BaseFunction)ite.next();
            if(af.getName().equalsIgnoreCase(function))
                result = af;
        }
        return result;
    }

    @objid ("ea75136e-7389-468b-8a03-1f6062e1100c")
    public List<BaseOperator> getNumbersVariablesList() {
        return numbersVariablesList;
    }

    @objid ("a61643ce-86ab-4413-a484-6d13d926f3ea")
    public List<BaseOperator> getOperatorsList() {
        return operatorsList;
    }

    @objid ("e2c890d2-e02f-42c2-aafc-215d9624a954")
    public List<BaseFunction> getFunctionsList() {
        return functionsList;
    }

    @objid ("2ce1c63d-0ee2-4c05-bdcb-6dca7d60b22d")
    public static void main(String[] args) {
        OperatorsBaseManager o = new OperatorsBaseManager();
        
        Iterator<BaseFunction> ite = o.getFunctionsList().iterator();
        while(ite.hasNext()){
            BaseFunction ao = (BaseFunction)ite.next();
            System.out.println(ao);
        }
    }

}
