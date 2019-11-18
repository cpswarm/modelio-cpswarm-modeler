package es.addlink.tutormates.equationEditor.Role;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Formats.MathML.MathML;
import es.addlink.tutormates.equationEditor.Role.BaseFunction;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;

@objid ("6dfae0ab-7daa-42df-98a6-9a0ca24742cb")
public class AllowedOperatorsAndFunctions {
    @objid ("e48eff30-7a66-45aa-acf7-6453249e8773")
    private List<BaseFunction> functionsList;

    @objid ("da71ae4b-c5a5-49a4-bc5a-759f7352c4d4")
    private List<BaseOperator> numbersVariablesList;

    @objid ("5256a101-9050-44e7-a0e8-b06bcfff4207")
    private List<BaseOperator> operatorsList;

    @objid ("8f17a6c9-8f21-4785-84e2-75217f27a4ae")
    public AllowedOperatorsAndFunctions() {
        //TODO Auto-generated constructor stub
    }

    @objid ("34520a6e-0ce1-4f78-8396-972e0059a847")
    public Boolean existsFunction(String nameFunction) {
        Iterator<BaseFunction> iterator = functionsList.iterator();
        while(iterator.hasNext()){
            BaseFunction af = iterator.next();
            if(af.getName().equalsIgnoreCase(nameFunction))
                return true;
        }
        return false;
    }

    @objid ("f2f15493-d733-4536-b3b9-3c427c2869cc")
    public int getPriority(String value) {
        int priority = -1;
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getSymbolEditor().equalsIgnoreCase(value)){
                exists = true;
                priority = ope.getPriority();
            }
        }
        return priority;
    }

    @objid ("6cc7b33e-97de-49bd-93f1-3d72057fbff5")
    public Boolean existsOperator(String value) {
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getSymbolEditor().equalsIgnoreCase(value))
                exists = true;
        }
        if(!exists && value.equalsIgnoreCase("neg")) exists=true;
        return exists;
    }

    @objid ("48d68895-2c14-4826-a265-45c54b5ed2e2")
    public Boolean existsOperatorById(int id) {
        Boolean exists = false;
        
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getId() == id)
                exists = true;
        }
        return exists;
    }

    @objid ("f6ddfb70-7f22-4abc-904b-a964edae704b")
    public Boolean existsOperatorByMathML(String mathML) {
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            MathML mathml = ope.getMathML();
            if(mathml.getMathMLLabel().equalsIgnoreCase(mathML))
                exists = true;
        }
        return exists;
    }

    @objid ("7e3963b4-720c-4b7f-a26c-4881de89ff19")
    public Boolean existsOperatorByName(String name) {
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getName().equalsIgnoreCase(name))
                exists = true;
        }
        return exists;
    }

    @objid ("e64b97f8-4c05-4f90-b22c-26d7d5c2a5c8")
    public Boolean existsOperatorByPriority(int priority, String operator) {
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getPriority() == priority && ope.getSymbolEditor().equals(operator)){
                exists = true;
                
            }
        }
        return exists;
    }

    /**
     * Obtiene los operadores del grupo y tipo especificados.
     * Ejemplo de grupos:
     * > simpleOperators
     * > complexOperators
     * > textOperators
     * > variables
     * 
     * Ejemplo de tipos:
     * > unary
     * > binary
     * > variable
     * @param group
     * @param type @return
     */
    @objid ("ec8e269f-66b9-48cf-a668-73b26590c9a5")
    public Boolean existsOperatorByType(String type, String operator) {
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getType().equalsIgnoreCase(type) && ope.getSymbolEditor().equals(operator)){
                exists = true;
            }
        }
        return exists;
    }

    @objid ("370db3a8-72a0-440b-9eb0-d18649242093")
    public Boolean existsOperatorByTypeAndId(String type, int id) {
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getType().equalsIgnoreCase(type) && ope.getId() == id)
                exists = true;
        }
        return exists;
    }

/*public static String[] getArrayFunctionNames(){
        try{
        arrayFunctionNames = new String[listFunctions.size()];
        int i=0;
        if(listFunctions != null){
            Iterator<AllowedFunction> iterator = listFunctions.iterator();
            while(iterator.hasNext()){
                AllowedFunction af = iterator.next();
                arrayFunctionNames[i] = af.getName();
                i++;
            }
            return arrayFunctionNames;
        }
        return null;
        }catch(Exception e){e.printStackTrace(); return null;}
    }*/
    @objid ("63148225-b980-4a2a-91a0-d8f58e2f5df2")
    public Boolean existsOperatorByTypeAndName(String type, String name) {
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getType().equalsIgnoreCase(type) && ope.getName().equalsIgnoreCase(name))
                exists = true;
        }
        return exists;
    }

    @objid ("7c587b62-e84d-473c-868a-2e5db2d99649")
    public Boolean existsOperatorByTypeAndPriority(String type, int priority, String operator) {
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getType().equalsIgnoreCase(type) && ope.getSymbolEditor().equals(operator) && ope.getPriority() == priority && !ope.getName().equals("parenthesis")){
                exists = true;
            }
        }
        return exists;
    }

    @objid ("51145342-a347-4ac9-876c-f79f82e333db")
    public Boolean existsOperatorByTypeAndSymbol(String type, String symbol) {
        Boolean exists = false;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext() && !exists){
            ope = ite.next();
            if(ope.getType().equalsIgnoreCase(type) && ope.getSymbolEditor().equalsIgnoreCase(symbol))
                exists = true;
        }
        return exists;
    }

    @objid ("d39ca1c7-a795-44d8-8a3b-4b1fdc3921db")
    public Boolean existsVariable(String variable) {
        try{
            Boolean exists = false;
            
            Iterator<BaseOperator> ite = numbersVariablesList.iterator();
            
            BaseOperator ope = null;
            while(ite.hasNext() && !exists){
                ope = ite.next();                
                if(ope.getSymbolMathML().equals(variable) && ope.getType().equals("variable"))
                    exists = true;
            }
            return exists;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @objid ("a00a13f9-0402-42f5-9b7a-2685ce242754")
    public BaseOperator getVariable(String variable) {
        try{
            Boolean exists = false;
            
            
            Iterator<BaseOperator> ite = numbersVariablesList.iterator();
            
            BaseOperator ope = null;
            while(ite.hasNext() && !exists){
                ope = ite.next();                
                if(ope.getSymbolMathML().equals(variable) && ope.getType().equals("variable"))
                    exists = true;
                
                    
            }
            return ope;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @objid ("77a5b676-5f96-4882-a0a7-9d8fea176f17")
    public int getEntriesFunction(String nameFunction) {
        Iterator<BaseFunction> iterator = functionsList.iterator();
        while(iterator.hasNext()){
            BaseFunction af = iterator.next();
            if(af.getName().equalsIgnoreCase(nameFunction))
                return af.getEntries();
        }
        return -1;
    }

    @objid ("e29be0b8-93e4-4492-8eee-20a03b195d69")
    public BaseFunction getFunction(String name) {
        Iterator<BaseFunction> ite = functionsList.iterator();
        BaseFunction f = null;
        while(ite.hasNext()){
            f = ite.next();
            if(f.getName().equalsIgnoreCase(name))
                return f;
        }
        return null;
    }

    @objid ("207a512f-2aaa-4efd-8526-809d21d7cbea")
    public List<BaseFunction> getFunctionsList() {
        return functionsList;
    }

    @objid ("db59c386-4a3b-426a-94b6-8299542d8ea8")
    public List<BaseOperator> getNumbersVariablesByName(String name) {
        List<BaseOperator> outList = new Vector<BaseOperator>();
        Iterator<BaseOperator> ite = numbersVariablesList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getName().equalsIgnoreCase(name))
                outList.add(ope);
        }
        return outList;
    }

    @objid ("f8e0714f-2be4-462c-86c6-1c2396fd4115")
    public List<BaseOperator> getNumbersVariablesList() {
        return numbersVariablesList;
    }

    /**
     * Obtiene los operadores del valor especificado.<br>
     * Ejemplo de valores:<br>
     * > msqrt<br>
     * > a<br>
     * > +<br>
     * > *<br>
     * @param value @return
     */
    @objid ("ed49c088-e2ba-4de3-9309-2c641959afd0")
    public List<BaseOperator> getOperator(String value) {
        List<BaseOperator> outList = new Vector<BaseOperator>();
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getSymbolEditor().equalsIgnoreCase(value))
                outList.add(ope);
        }
        return outList;
    }

    /**
     * Obtiene los operadores del id especificado.
     * @param id @return
     */
    @objid ("dd8216ef-451d-4eb3-83b8-91dc5e12d8bb")
    public BaseOperator getOperatorById(int id) {
        BaseOperator outAllowedOperator = null;
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getId() == id)
                outAllowedOperator = ope;
        }
        return outAllowedOperator;
    }

    @objid ("866c3b7f-e10a-4494-973c-838929389cd1")
    public BaseOperator getOperatorByName(String name) {
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getName().equalsIgnoreCase(name)){
                return ope;
            }
        }
        return null;
    }

    @objid ("17e4d453-b547-40a5-9540-8583bf36e15c")
    public BaseOperator getOperatorByName(List<BaseOperator> l, String name) {
        Iterator<BaseOperator> ite = l.iterator();
        BaseOperator ope = null;
        
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getName().equalsIgnoreCase(name)){
                return ope;
            }
        }
        return null;
    }

    /**
     * Obtiene los operadores del nombre especificado.
     * Ejemplo de mathML's:
     * > mo
     * > mi
     * > mfrac
     * @param mathML @return
     */
    @objid ("b8e2c14e-57f5-4f31-9e81-9d0ba4367421")
    public List<BaseOperator> getOperatorsByMathML(String mathML) {
        List<BaseOperator> outList = new Vector<BaseOperator>();
        
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getMathML().getMathMLLabel().equalsIgnoreCase(mathML)){
                outList.add(ope);
            }
        }
        return outList;
    }

    /**
     * Obtiene los operadores del nombre especificado.
     * Ejemplo de nombres:
     * > subtraction
     * > multiplication
     * > squareRoot
     * > var_a
     * @param name @return
     */
    @objid ("e2d29320-1f1a-419a-b3ba-a7b4f45fef3a")
    public List<BaseOperator> getOperatorsByName(String name) {
        List<BaseOperator> outList = new Vector<BaseOperator>();
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getName().equalsIgnoreCase(name))
                outList.add(ope);
        }
        return outList;
    }

    @objid ("75f7b15f-683b-463a-882b-f6d6fed7de6c")
    public List<BaseOperator> getOperatorsBySymbolEditor(String symbol) {
        List<BaseOperator> outList = new Vector<BaseOperator>();
        
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getSymbolEditor().equalsIgnoreCase(symbol)){
                outList.add(ope);
            }
        }
        return outList;
    }

    /**
     * Obtiene los operadores del tipo especificado.
     * Ejemplo de tipos:
     * > unary
     * > binary
     * > variable
     * @param type @return
     */
    @objid ("5a29dd7e-5cc2-46fc-b832-304f10911ed6")
    public List<BaseOperator> getOperatorsByType(String type) {
        List<BaseOperator> outList = new Vector<BaseOperator>();
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getType().equalsIgnoreCase(type))
                outList.add(ope);
        }
        return outList;
    }

    /**
     * Obtiene los operadores del grupo y tipo especificados.
     * Ejemplo de grupos:
     * > simpleOperators
     * > complexOperators
     * > textOperators
     * > variables
     * 
     * Ejemplo de tipos:
     * > unary
     * > binary
     * > variable
     * @param group
     * @param type @return
     */
    @objid ("7176bd56-1280-4e28-a11b-c8cbd6f02ba3")
    public List<BaseOperator> getOperatorsByTypeAndName(String type, String name) {
        List<BaseOperator> outList = new Vector<BaseOperator>();
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getType().equalsIgnoreCase(type) && ope.getName().equalsIgnoreCase(name))
                outList.add(ope);
        }
        return outList;
    }

    @objid ("dc3b61f0-7a11-40a0-949e-da2bd08bad21")
    public List<BaseOperator> getOperatorsByTypeAndSymbol(String type, String symbol) {
        List<BaseOperator> outList = new Vector<BaseOperator>();
        Iterator<BaseOperator> ite = operatorsList.iterator();
        BaseOperator ope = null;
        while(ite.hasNext()){
            ope = ite.next();
            if(ope.getType().equalsIgnoreCase(type) && ope.getSymbolEditor().equalsIgnoreCase(symbol))
                outList.add(ope);
        }
        return outList;
    }

    @objid ("921dd58c-50c8-43bc-b444-487775bb65e7")
    public List<BaseOperator> getOperatorsList() {
        return operatorsList;
    }

    @objid ("9dad63a1-efbc-4f1d-b866-7d743cd61c1f")
    public void setFunctionsList(List<BaseFunction> functionsList) {
        this.functionsList = functionsList;
    }

    @objid ("7a2c292f-5c4a-4a8e-9236-5e8ec8cbc90f")
    public void setNumbersVariablesList(List<BaseOperator> numbersVariablesList) {
        this.numbersVariablesList = numbersVariablesList;
    }

    @objid ("51078d14-a1ce-4033-9f42-a38727f5fc6e")
    public void setOperatorsList(List<BaseOperator> operatorsList) {
        this.operatorsList = operatorsList;
    }

}
