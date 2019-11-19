package es.addlink.tutormates.equationEditor.Translation;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.MathEditor.Binary;
import es.addlink.tutormates.equationEditor.MathEditor.Function;
import es.addlink.tutormates.equationEditor.MathEditor.IncorrectExpression;
import es.addlink.tutormates.equationEditor.MathEditor.MathEditor;
import es.addlink.tutormates.equationEditor.MathEditor.RepeatingDecimal;
import es.addlink.tutormates.equationEditor.MathEditor.SequenceList;
import es.addlink.tutormates.equationEditor.MathEditor.StringNumber;
import es.addlink.tutormates.equationEditor.MathEditor.Text;
import es.addlink.tutormates.equationEditor.MathEditor.Unary;
import es.addlink.tutormates.equationEditor.MathEditor.Variable;
import es.addlink.tutormates.equationEditor.Role.BaseFunction;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Clase que traduce un String MathML a MathEditor.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("90afdd3e-8f2b-4de7-a7ac-741849a6ebc3")
public class MathML2MathEditor {
    /**
     * MathML que se desea traducir a MathEditor.
     */
    @objid ("e5fd56d5-a05a-452b-b6b8-4d2e28111b9a")
    private String mathml;

    /**
     * Document del mathML.
     */
    @objid ("c72c7d5c-5234-4fab-b92c-e0ea13430804")
    private Document doc;

    /**
     * List de componentes Element que pasará de estado normal a invertido (para su procesamiento).
     */
    @objid ("a98ce2b5-40f0-43a4-a64d-cd4e49f2b249")
    private List<Element> mathmlList;

    /**
     * Manager global del editor de expresiones.
     */
    @objid ("e28e40fd-c287-4a2c-9aa8-4242675e9f2e")
    private Manager manager;

    /**
     * MathEditor asociado al mathML.
     */
    @objid ("7776dc23-4ed5-4dc6-ac15-a7733278d51d")
    private MathEditor mathEditor;

    /**
     * Invierte un List de componentes Element. También invierte sus hojas.
     * @param list Lista que se desea invertir.
     * @return Devuelve el list invertido.
     */
    @objid ("dcd6d36a-11e9-4dee-b8e2-ec2523107a31")
    @SuppressWarnings("unchecked")
    private static List<Element> invertirList(List<Element> list) {
        if(list == null) return new Vector<Element>();
        
        List<Element> invList = new Vector<Element>();
        
        int tam = list.size();
        for(int i=tam-1;i>=0;i--){
            Element el = list.get(i);
            if(el.getChildren().size() > 0){
                List<Element> lChildren = invertirList(el.getChildren());
                el.removeContent();
                el.addContent(lChildren);
            }
            invList.add(el);
        }
        return invList;
    }

    /**
     * Traduce a String un Document.
     * @param mathml Document que se desea traducir a String.
     * @return Devuelve el String generado a partir del Document pasado por parámetro.
     */
    @objid ("87997246-9aea-4e41-b070-fac91c138320")
    public static String Mathml2String(Document mathml) {
        //Jdom
        XMLOutputter out = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        //Format format = Format.getCompactFormat();
        format.setOmitEncoding(true) ;
        format.setOmitDeclaration(true);
        format.setEncoding("US-ASCII");
        out.setFormat(format);
        String xml = (out.outputString(mathml));
        xml = xml.replaceAll("&amp;", "&");
        xml = xml.replaceAll("neg", "-");
        return (xml);
    }

    /**
     * Constructor.
     * @param mathml Cadena mathML.
     * @param manager Manager común del editor de expresiones.
     */
    @objid ("138fde0d-905f-4936-b78d-8e68886e575e")
    public MathML2MathEditor(String mathml, Manager manager) {
        super();
        try{
            this.manager = manager;
            this.mathEditor = null;
            
            this.mathml = filtroReplaces(mathml);
                        
            buildDocument();
            buildMathMLList();
            
            mathmlList = invertirList(mathmlList);
            
            if(!hayAlgoInvalido(mathml))
                this.mathEditor = recorridoInvertido(this.mathmlList,null);
            else
                this.mathEditor = null;
        
        }catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    /**
     * Añade los productos necesarios para que una expresión sea correcta. Si el editor recibe <mn>5</mn><mi>x</mn>, este método, añade en medio un producto invisible.
     * @param lIn Lista de los nodos mathML INVERTIDA.
     * @return Devuelve la lista recibida (manteniendo la inversión de sus nodos) pero con los productos invisibles necesarios.
     */
    @objid ("c6357bdc-f85e-4ce9-8794-e832cdcae280")
    private List<Element> aniadirProductos(List<Element> lIn) {
        List<Element> list = new Vector<Element>();
        Boolean modificado = false;
        Element act=null, next=null;
        
        if(lIn.size() > 1){
            act=lIn.get(0);
            next=lIn.get(1);
        }
        
        for(int i=0;i<lIn.size();i++){
            act = lIn.get(i);
        
            if(i+1 < lIn.size()) next = lIn.get(i+1);
            else  next = null;
            
            //5x
            if(next != null && act.getName().equalsIgnoreCase("mi") && next.getName().equalsIgnoreCase("mn")){
                list.add(act);
                list.add(getInvisibleProduct());
                modificado = true;
            
            //ab
            }else if(next != null && act.getName().equalsIgnoreCase("mi") && next.getName().equalsIgnoreCase("mi")){
                list.add(act);
                list.add(getInvisibleProduct());
                modificado = true;
            
            }else{
                list.add(act);
            }
        }
        if(!modificado) return lIn;
        return list;
    }

    /**
     * Genera el Document XML a partir de una cadena mathML.
     */
    @objid ("3b709f4f-4bfe-43a9-b9b7-23913fb82de1")
    private void buildDocument() {
        try{
            SAXBuilder sb = new SAXBuilder();
            this.doc = sb.build(new StringReader(mathml));
        }catch (JDOMException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    /**
     * Ignora el que haya o no etiqueta <mrow> después de <math>
     */
    @objid ("0649136e-8898-41ba-871e-9c1838280b76")
    @SuppressWarnings("unchecked")
    private void buildMathMLList() {
        this.mathmlList = new Vector<Element>();
        Element root = doc.getRootElement();
        
        if(root.getChildren().size() > 0){
            this.mathmlList = root.getChildren();
            while((this.mathmlList.get(0)).getName().equalsIgnoreCase("mrow") && this.mathmlList.size() == 1){
                Element mainMrow = (Element)this.mathmlList.get(0);
                this.mathmlList = mainMrow.getChildren();
            }
            
            if(!((Element)root.getChildren().get(0)).getName().equalsIgnoreCase("mrow"))
                this.mathmlList = root.getChildren();
        }
    }

    /**
     * Añade los productos que sean necesarios en el mathml para que el editor pueda entender la expresión a cargar.
     * @param mathml @return
     */
    @objid ("5b41fe22-a46c-4820-a85b-d73f81785a18")
    private String filtroReplaces(String mathml) {
        String mathmlFiltrado = mathml;
        
        /**
         * Hay que tener cuidado con los filtros que se ponen aquí.
         * Un claro filtro sería el reconocimiento de esta expresión: 5x
         * 
         * Si llega un numero con una variable, añadir el producto en medio. CUIDADO!!
         * 
         * En esta situación, sería correcto:
         * <math><mn>5</mn><mi>x</mi></math>
         * 
         * Sin embargo, en ésta otra no:
         * <math><msup><mn>5</mn><mi>x</mi></msup></math>
         * 
         * Por eso el uso de replaces no siempre es correcto.
         */
        
        // Símbolo de producto
        mathmlFiltrado = mathmlFiltrado.replace("&#8290;", "&#xb7;");
        
        // Símbolo de resta
        mathmlFiltrado = mathmlFiltrado.replace("&#x2212;", "-");
        
        // <mo>(</mo> -> <mfenced...><mrow>
        mathmlFiltrado = mathmlFiltrado.replace("<mo>(</mo>", "<mfenced open=\"(\" close=\")\" separators=\"\">");
        
        // <mo>[</mo> -> <mfenced...><mrow>
        mathmlFiltrado = mathmlFiltrado.replace("<mo>[</mo>", "<mfenced open=\"[\" close=\"]\" separators=\"\">");
        
        // <mo>{</mo> -> <mfenced...><mrow>
        mathmlFiltrado = mathmlFiltrado.replace("<mo>{</mo>", "<mfenced open=\"{\" close=\"{\" separators=\"\">");
        
        // <mo>)</mo> -> </mfenced>
        //mathmlFiltrado = mathmlFiltrado.replace("</mrow><mo>)</mo>", "</mfenced>");
        mathmlFiltrado = mathmlFiltrado.replace("<mo>)</mo>", "</mfenced>");
        
        // <mo>]</mo> -> </mfenced>
        mathmlFiltrado = mathmlFiltrado.replace("<mo>]</mo>", "</mfenced>");
        
        // <mo>}</mo> -> </mfenced>
        mathmlFiltrado = mathmlFiltrado.replace("<mo>}</mo>", "</mfenced>");
        return mathmlFiltrado;
    }

    /**
     * Devuelve un Element de tipo <mo>...</mo> que contiene dentro el código del producto invisible.
     * @return Un Element de tipo <mo>...</mo> que contiene dentro el código del producto invisible.
     */
    @objid ("57b3e1e6-f941-4698-81e7-21526de9d819")
    private Element getInvisibleProduct() {
        BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName("invisibleMultiplication").get(0);
        Element el = new Element("mo");
        el.setText(ope.getSymbolMathML());
        return el;
    }

    /**
     * Devuelve el mathEditor.
     * @return MathEditor obtenido a partir de una cadena mathML pasada como parámetro en el constructor.
     */
    @objid ("dbe4c2d8-2549-41c2-bf63-422df9345413")
    public MathEditor getMathEditor() {
        return this.mathEditor;
    }

    /**
     * Detecta si hay operadores de prioridad "priority" en el list pasado por parámetro.
     * @param mathMLList Lista invertida que se desea evaluar.
     * @param priority Prioridad que se desea buscar en el list.
     * @return Devuelve true si ha encontrado algún operador de prioridad "priority", false en caso contrario.
     */
    @objid ("51a3c150-798b-45c0-83f0-cbc48d87d302")
    private Boolean hayAlgunOperadorNoNegativo(List<Element> mathMLList, int priority) {
        try{
            Iterator<Element> ite = mathMLList.iterator();
            int i=0;
            while(ite.hasNext()){
                Element el = ite.next();
                if(el.getName().equalsIgnoreCase("mo")){
                    String operatorValue = el.getValue();
                    
                    //Detecta los negativos
                    if(operatorValue.equalsIgnoreCase("-") && i==mathMLList.size()-1)
                        return false;
                    
                    int priority2 = this.manager.getAllowedOperatorsAndFunctions().getPriority(operatorValue);
                    if(priority2 == priority)
                        return true;
                }
                i++;
            }
            
            return false;
        }catch(Exception ex){
            //ex.printStackTrace();
            return null;
        }
    }

    /**
     * Detecta si hay operadores de prioridad 1, 2 o 3 en el list pasado por parámetro.
     * @param mathMLList Lista invertida que se desea evaluar.
     * @return Devuelve true si ha encontrado algún operador de prioridad 1, 2 o 3, false en caso contrario.
     */
    @objid ("14c9fb34-43b7-4a6c-8431-029ff28f3870")
    private Boolean hayOperadores(List<Element> mathMLList) {
        return hayAlgunOperadorNoNegativo(mathMLList,3) || hayAlgunOperadorNoNegativo(mathMLList,2) || hayAlgunOperadorNoNegativo(mathMLList,1);
    }

    @objid ("642d1412-60de-4cb6-b7ef-d2d8ac0c5194")
    private Boolean hayMText(List<Element> list) {
        int i=0;
        while(i<list.size()){
            Element el = list.get(i);
            if(el.getName().equalsIgnoreCase("mtext"))
                return true;
            i++;
        }
        return false;
    }

    @objid ("7b8d1a91-6052-402f-bca7-35d57106776a")
    private Boolean hayAlgoInvalido(String mathml) {
        Boolean inv = false;
        if(mathml.split("&#x21d2;").length > 1) inv=true;
        if(mathml.split("⇒").length > 1) inv=true;
        return inv;
    }

    /**
     * Recorre recursivamente un MathML.
     * @param mathMLList Lista de nodos, puestos de forma invertida.
     * @param parent El padre del nodo. La primera llamada, null, las demás dependiendo del padre.
     * @return Devuelve un MathEditor que se corresponde al MathML.
     */
    @objid ("111e62b5-b670-411d-9586-d22fe36bb7e5")
    @SuppressWarnings("unchecked")
    private MathEditor recorridoInvertido(List<Element> mathMLList, MathEditor parent) {
        try{
            
            if(mathMLList.size() == 1 && mathMLList.get(0).getName().equalsIgnoreCase("mrow"))            
                return recorridoInvertido(mathMLList.get(0).getChildren(),parent);
        
            
            int longList = mathMLList.size();
            
            
            if(hayMText(mathMLList) && longList > 1){
                
                IncorrectExpression me = new IncorrectExpression(parent);
                
                for(int i=longList-1;i>=0;i--){
                    Element el = (Element)mathMLList.get(i);
                    List<Element> l = new Vector<Element>();
                    l.add(el);
                    me.addMathEditor(recorridoInvertido(l,me));
                }
                
                return me;
                
            }else{
                mathMLList = aniadirProductos(mathMLList);
                Iterator<Element> ite = mathMLList.iterator();
                Element last = mathMLList.get(longList-1);
                int i=0;            
                while(ite.hasNext()){
                    Element el = ite.next();
                    Element next=null;
                    if(i+1 <= longList-1)
                        next = mathMLList.get(i+1);
                    
                    //System.out.println("Elemento actual: " + el.getName());
                    //System.out.println("Total elementos: " + mathMLList.size());
        
                    // Control de negativos
                    if(last.getName().equalsIgnoreCase("mo") && last.getValue().equalsIgnoreCase("-") && !hayOperadores(mathMLList.subList(i, mathMLList.size()-1))){
                        i++;
                        BaseOperator neg = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName("negative").get(0);
                        List<Element> contentNeg = new Vector<Element>();
                        contentNeg.add(el);
                        
                        while(ite.hasNext() && i<longList-1){
                            Element elN = ite.next();
                            contentNeg.add(elN);
                            i++;
                        }
                        
                        Unary negative = new Unary(neg.getName(),neg.getId(),parent);
                        negative.setChild(recorridoInvertido(contentNeg,negative));
                        return negative;
                    }
                    /*if(i+1 <= longList-1){
                        next = mathMLList.get(i+1);
                        if(next.getName().equalsIgnoreCase("mo")){
                            
                            String operatorValue = next.getValue();
                            
                            int priority = this.manager.getAllowedOperatorsAndFunctions().getPriority(operatorValue);
                            if(priority == 3){
                                if(operatorValue.equalsIgnoreCase("-") && !hayAlgunOperadorNoNegativo(mathMLList.subList(i+1, mathMLList.size()),3)){
                                    BaseOperator neg = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName("negative").get(0);
                                    List<Element> contentNeg = new Vector<Element>();
                                    contentNeg.add(el);
                                    ite.next();
                                    Unary negative = new Unary(neg.getName(),neg.getId(),parent);
                                    negative.setChild(recorridoInvertido(contentNeg,negative));
                                    return negative;
                                }
                            }
                        }
                    }*/
                    
                    
                    //Operadores simples
                    if(el.getName().equalsIgnoreCase("mo")){
                        String operatorValue = el.getValue();
                        int priority = this.manager.getAllowedOperatorsAndFunctions().getPriority(operatorValue);
                        
                        //Prioridad = 3, +, -
                        if(priority == 3){
                            
                            //Este if, detecta situaciones como esta:  y=-3, detector de negativos o restas
                            if((next != null && !next.getName().equalsIgnoreCase("mo") && !next.getName().equalsIgnoreCase("mrow"))){
                                
                                BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperator(operatorValue).get(0);
                                
                                Binary bin = new Binary(ope.getName(),ope.getId(),parent);
                                bin.setRightChild(recorridoInvertido(mathMLList.subList(0, i),bin));
                                bin.setLeftChild(recorridoInvertido(mathMLList.subList(i+1, mathMLList.size()),bin));
                                return bin;
                            }else{
                                if(next != null){
                                    
                                    String operatorValueNext = next.getValue();
                                    int priorityNext = this.manager.getAllowedOperatorsAndFunctions().getPriority(operatorValueNext);
                                    
                                    if(priorityNext == -1){
                                        //El siguiente operador es ! o %
                                        BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperator(operatorValue).get(0);
                                        Binary bin = new Binary(ope.getName(),ope.getId(),parent);
                                        bin.setRightChild(recorridoInvertido(mathMLList.subList(0, i),bin));
                                        bin.setLeftChild(recorridoInvertido(mathMLList.subList(i+1, mathMLList.size()),bin));
                                        return bin;
                                    }
                                    
                                }
                            }
                        }
                        
                        //Prioridad = 2, *, :
                        else if(priority == 2){
                            Boolean hayOp = hayAlgunOperadorNoNegativo(mathMLList.subList(i+1, mathMLList.size()),3);
                            
                            if(!hayOp){
                                BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperator(operatorValue).get(0);
                                Binary bin = new Binary(ope.getName(),ope.getId(),parent);
                                bin.setRightChild(recorridoInvertido(mathMLList.subList(0, i),bin));
                                bin.setLeftChild(recorridoInvertido(mathMLList.subList(i+1, mathMLList.size()),bin));
                                return bin;
                            }
                        }
                        
                        //Prioridad = 1, ^, /
                        else if(priority == 1){
                            Boolean hayOp = hayAlgunOperadorNoNegativo(mathMLList.subList(i+1, mathMLList.size()),3) || hayAlgunOperadorNoNegativo(mathMLList.subList(i+1, mathMLList.size()),2);
                            if(!hayOp){
                                BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperator(operatorValue).get(0);
                                Binary bin = new Binary(ope.getName(),ope.getId(),parent);
                                bin.setRightChild(recorridoInvertido(mathMLList.subList(0, i),bin));
                                bin.setLeftChild(recorridoInvertido(mathMLList.subList(i+1, mathMLList.size()),bin));
                                return bin;
                            }
                        }
                        
                        //Prioridad = -1, !, %
                        else if(priority == -1){
                            if(!hayOperadores(mathMLList.subList(i+1, mathMLList.size()))){
                                BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperator(operatorValue).get(0);
                                Unary un = new Unary(ope.getName(),ope.getId(),parent);
                                un.setChild(recorridoInvertido(mathMLList.subList(i+1, mathMLList.size()),un));
                                return un;
                            }
                        }
                    }
                    
                    //mfenced: parentesis, corchetes, llaves, ...
                    else if(el.getName().equalsIgnoreCase("mfenced") && (mathMLList.size() == 1 || (next != null && next.getName().equalsIgnoreCase("mi")))){
                        String open = el.getAttributeValue("open");
                        //String close = el.getAttributeValue("close");
                        String separator = el.getAttributeValue("separators");
                        
                        //Es una coordenada si tiene separadores ';'
                        if(separator.equalsIgnoreCase(";")){
                            BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorByName("geometricPoint");
                            Binary bin = new Binary(ope.getName(),ope.getId(),parent);
                            
                            List<Element> l = el.getChildren();
                            Iterator<Element> iteMrows = l.iterator();
                            if(iteMrows.hasNext()){
                                Element elMrow1 = iteMrows.next();
                                bin.setRightChild(recorridoInvertido(elMrow1.getChildren(),parent));
                                
                                if(iteMrows.hasNext()){
                                    Element elMrow2 = iteMrows.next();
                                    bin.setLeftChild(recorridoInvertido(elMrow2.getChildren(),parent));
                                }
                                return bin;
                            }
                        }
                        else{
                            
                            if(open.equalsIgnoreCase("")){
                                //Es una lista
                                SequenceList sl = new SequenceList(parent,separator);
                                
                                List<Element> l = el.getChildren();
                                int iL=l.size()-1;
                                
                                while(iL>=0){
                                    Element elL = l.get(iL);
                                    List<Element> lItem = new Vector<Element>();
                                    lItem.add(elL);
                                    sl.addMathEditor(recorridoInvertido(lItem,sl));
                                    iL--;
                                }
                                //System.out.println(sl);
                                return sl;
                            }else{
                            
                                Boolean hayOp = hayAlgunOperadorNoNegativo(mathMLList.subList(i+1, mathMLList.size()),3) || hayAlgunOperadorNoNegativo(mathMLList.subList(i+1, mathMLList.size()),2)  || hayAlgunOperadorNoNegativo(mathMLList.subList(i+1, mathMLList.size()),1);
                                if(!hayOp){
                                //No hay operadores, por tanto, se puede terminar con una función o un paréntesis (o corchete o llave).
                                    
                                    if(next != null && next.getName().equalsIgnoreCase("mi")){
        
                                        //Es función:  fun(x)
                                        BaseFunction f = this.manager.getAllowedOperatorsAndFunctions().getFunction(next.getValue());
                                        Function funct = new Function(f.getName(),-1,parent);
                                        
                                        List<Element> functionParams = el.getChildren();
                                        for(int j=0;j<functionParams.size();j++){
                                            Element param = functionParams.get(j);
                                            List<Element> convertList = new Vector<Element>();
                                            
                                            for(int z=0;z<param.getChildren().size();z++)
                                                convertList.add((Element)param.getChildren().get(z));
                                            
                                            //System.out.println(recorridoInvertido(convertList,funct));
                                            funct.addChild(recorridoInvertido(convertList,funct));
                                        }
                                        
                                        ite.next();
                                        return funct;
                                        
                                    }else{
                                        
                                        //Se centra en un solo paréntesis, corchete o llave
                                        BaseOperator p = this.manager.getAllowedOperatorsAndFunctions().getOperatorsBySymbolEditor(open).get(0);
                                        Unary un = new Unary(p.getName(),p.getId(),parent);
                                        List<Element> lChild = el.getChildren();
                                        //System.out.println(Element2String(lChild));
                                        un.setChild(recorridoInvertido(lChild,un));
                                        return un;
                                    }
                                }
                            }
                        }
                    }
                    
                    //Números
                    else if(el.getName().equalsIgnoreCase("mn") && mathMLList.size() == 1)
                        return MathEditor.getValue(el.getValue());
            
                    //Variables
                    else if(el.getName().equalsIgnoreCase("mi") && mathMLList.size() == 1){
                        if(this.manager.getAllowedOperatorsAndFunctions().existsVariable(el.getValue())){
                            BaseOperator variable = this.manager.getAllowedOperatorsAndFunctions().getVariable(el.getValue());
                            if(variable.isText())
                                return new Variable(el.getValue(),true,parent);
                            else{
                                Variable  v = new Variable(variable.getName(),false,parent); 
                                return v;
                            }
                        }
                    }
                    
                    //Operadores complejos
                    else if(!el.getName().equalsIgnoreCase("mn") && !el.getName().equalsIgnoreCase("mi") && !el.getName().equalsIgnoreCase("mo") && !el.getName().equalsIgnoreCase("mover") && !el.getName().equalsIgnoreCase("mtext")){
                        if(!hayOperadores(mathMLList.subList(i+1, mathMLList.size()))){
                            
                            BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByMathML(el.getName()).get(0);
                            
                            //Binarios
                            if(ope.getType().equalsIgnoreCase("binary")){
                                
                                Binary bin = new Binary(ope.getName(),ope.getId(),parent);
                                
                                int l = 1;
                                int r = 0;
                                
                                if(ope.getName().equalsIgnoreCase("nthroot")){
                                    l=0;
                                    r=1;
                                }
                                
                                List<Element> left = new Vector<Element>();
                                left.add((Element)el.getChildren().get(l));
                                bin.setLeftChild(recorridoInvertido(left,bin));
                                
                                List<Element> right = new Vector<Element>();
                                right.add((Element)el.getChildren().get(r));
                                bin.setRightChild(recorridoInvertido(right,bin));
                                
                                return bin;
                            }
                            
                            //Unarios
                            else if(ope.getType().equalsIgnoreCase("unary")){
                                Unary un = new Unary(ope.getName(),ope.getId(),parent);
                                
                                List<Element> childList = new Vector<Element>();
                                Iterator<Element> iteUn = el.getChildren().iterator();
                                while(iteUn.hasNext())
                                    childList.add(iteUn.next());
                                
                                un.setChild(recorridoInvertido(childList,un));
                                
                                return un;
                            }
                        }
                    }
                    
                    //Periódicos
                    else if(el.getName().equalsIgnoreCase("mover") && mathMLList.size() == 2){
                        
                        el = ite.next();
                        String enteroYmixto = el.getValue();
                        String periodico = el.getAttributeValue("oculto").split("dec", 2)[1];
                        
                        String entero = "";
                        String mixto = "";
                    
                        if(enteroYmixto.split("\\.").length == 1){
                            
                            //Periódico puro
                            entero = enteroYmixto.split("\\.")[0];
                            BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorByName("repeatingDecimal");
                            RepeatingDecimal rd = new RepeatingDecimal(ope.getName(),ope.getId(),parent);
                            rd.setFirstChild(MathEditor.getValue(entero));
                            rd.setThirdChild(new StringNumber(periodico,rd));
                            return rd;
                            
                        }else{
                            
                            //Periódico mixto
                            entero = enteroYmixto.split("\\.")[0];
                            mixto = enteroYmixto.split("\\.")[1];
                            BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorByName("repeatingDecimal");
                            RepeatingDecimal rd = new RepeatingDecimal(ope.getName(),ope.getId(),parent);
                            rd.setFirstChild(MathEditor.getValue(entero));
                            rd.setSecondChild(new StringNumber(mixto,rd));
                            rd.setThirdChild(new StringNumber(periodico,rd));
                            return rd;
            
                        }
                    }
                    
                    else if(el.getName().equalsIgnoreCase("mtext") && mathMLList.size() == 1){
                        String str = el.getValue();
                        Text txt = new Text(parent);
                        txt.setText(str);
                        return txt;
                        
                    }
                    
                    i++;
                }
            }
        
            return null;
        
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

}
