package es.addlink.tutormates.equationEditor.Translation;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

@objid ("0618c288-071f-4a95-9214-d1e4f52f1d82")
public class MathML2GeoGebra {
    @objid ("fb0112fa-48db-4eb0-9060-4ebb34762f01")
    public static String MATHML_NS = "http://www.w3.org/1998/Math/MathML";

    @objid ("0b2912d5-ac45-41b9-bda9-256e40c5f9fd")
     static Namespace ns = Namespace.getNamespace(MATHML_NS);

/*public static void main(String[] args) throws ParserConfigurationException {
        //System.out.println(traducir("<mrow><mfenced open=\"(\" close=\")\" separators=\";\"><mrow><mn>2</mn></mrow><mrow><mn>3</mn></mrow></mfenced></mrow>"));
        //System.out.println(traducir("<math><mrow><mn>2</mn><mo>&#183;</mo><msup><mrow><mi>x</mi></mrow><mrow><mn>2</mn></mrow></msup></mrow></math>"));    
        //System.out.println(traducirParaGG("<mrow><mo>-</mo><msup><mrow><mi>x</mi></mrow><mrow><mn>2</mn></mrow></msup><mo>-</mo><mn>3</mn><mo>&#xb7;</mo><mi>x</mi><mo>-</mo><mn>2</mn></mrow>"));    
        //System.out.println(traducir("<math xmlns=\"http://www.w3.org/1998/Math/MathML\"><mn>2</mn><mo>+</mo><mn>4</mn></math>"));    
    }*/
    @objid ("e85e57b7-f626-400a-9317-3bed5abfc4a0")
    public static String traducir(String mathml) throws ParserConfigurationException {
        mathml=mathml.replace("&#183;","*");
        mathml=mathml.replace("&#8290;","*");
        mathml=mathml.replaceAll("&#xb7;","*");
        //cambiamos la cadena de la potencia de 10 cuando el exponente lleva el simbolo '+' (en los demás casos no falla)
        mathml=mathml.replaceAll("E+","E");
        mathml=mathml.replaceAll("sen","sin");
        String resultado="";
        String interior="";
        Document doc;
        doc = String2Mathml(mathml);
        java.util.List nodeList = doc.getContent();
        Element nod= (Element) nodeList.get(0);
        while(nod.getName()=="math"){
                nodeList=nod.getChildren();
                nod=(Element) nodeList.get(0);
        }
        Iterator iterator = nodeList.iterator();
        while (iterator.hasNext()) {
            Element child = (Element) iterator.next();
            //System.out.println(child.getName());
            if((child.getName()=="mn")||(child.getName()=="mo")||(child.getName()=="mi")) {
                if(child.getName()=="mo" && child.getValue().toString()==":"){
                    interior = interior+"/";
                }else{
                    interior = interior + child.getValue().toString(); 
                }
            }
            if(child.getName()=="mrow"){
                interior =leerMrow(interior,child);
            }
            if(child.getName()=="msup"){
                interior =leerMsup(interior,child);
            }
            if(child.getName()=="mfrac"){
                interior =leerMfrac(interior,child);
            }
            if(nod.getName()=="mroot"){
                interior = leerMroot(interior,nod);
            }
            if(nod.getName()=="mfenced"){
                interior = leerMfenced(interior,nod);
            }
            if(nod.getName()=="msqrt"){
                interior = leerMsqrt(interior,nod);
            }
        }
        return resultado+interior;
    }

//Por el convenio la traduccion añade "[" "]" cuando son necesarios los paréntesis.
//esta función los cambia por "(" ")" para que GG pueda reconocer la expresión.
    @objid ("e462e21c-224e-46cc-9743-1e52c1a8e344")
    public static String traducirParaGG(String mathml) {
        String resultado = null;
        try {
            resultado = traducir(mathml);
            
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        while(resultado.indexOf("[")!=-1){
            resultado =resultado.replace("[", "(");
        }
        
        while(resultado.indexOf("]")!=-1){
            resultado = resultado.replace("]", ")");
        }
        return resultado;
    }

    @objid ("11e5eb17-9eb2-438e-95c6-bd6d2f843780")
    private static String leerMroot(String cadena, Element node) {
        List nodeList = node.getChildren();
        String radicando="";
        String indice="";
        for(int j=0;j<2;j++){
            if(j==0){
                Element nod=(Element) nodeList.get(j);
                if((nod.getName()=="mn")||(nod.getName()=="mo")||(nod.getName()=="mi")) {
                    if(nod.getName()=="mo" && nod.getValue().toString()==":"){
                        radicando = radicando+"/";
                    }else{
                        radicando = radicando + nod.getValue().toString(); 
                    }
                } 
                if(nod.getName()=="mrow"){
                    radicando = leerMrow(radicando,nod);
                }
                if(nod.getName()=="msup"){
                    radicando=leerMsup(radicando,nod);
                }
                if(nod.getName()=="mfrac"){
                    radicando=leerMfrac(radicando,nod);
                }
                if(nod.getName()=="mroot"){
                    radicando=leerMroot(radicando,nod);
                }
                if(nod.getName()=="mfenced"){
                    radicando=leerMfenced(radicando,nod);
                }
                if(nod.getName()=="msqrt"){
                    radicando=leerMsqrt(radicando,nod);
                }
            }
            if(j==1){
                Element nod=(Element) nodeList.get(j);
                if((nod.getName()=="mn")||(nod.getName()=="mo")||(nod.getName()=="mi")) {
                    if(nod.getName()=="mo" && nod.getValue().toString()==":"){
                        indice = indice+"/";
                    }else{
                        indice = indice + nod.getValue().toString();
                       }
                }
                if(nod.getName()=="mrow"){
                    indice = leerMrow(indice,nod);
                }
                if(nod.getName()=="msup"){
                    indice=leerMsup(indice,nod);
                }
                if(nod.getName()=="mfrac"){
                    indice=leerMfrac(indice,nod);
                }
                if(nod.getName()=="mroot"){
                    indice=leerMroot(indice,nod);
                }
                if(nod.getName()=="mfenced"){
                    indice=leerMfenced(indice,nod);
                }
                if(nod.getName()=="msqrt"){
                    indice=leerMsqrt(indice,nod);
                }
            }
        }
        
        if(ParentesisNecesario(radicando)){
            radicando=AnadirParentesisConvenidos(radicando);
        }
        if(ParentesisNecesario(indice)){
            indice=AnadirParentesisConvenidos(indice);
        }
        cadena=cadena + radicando+"^(1/"+indice+")";
        return cadena;
    }

    @objid ("087d35c9-3940-41c5-bef8-1dc29f2546ad")
    private static String leerMfrac(String cadena, Element node) {
        List nodeList = node.getChildren();
        String dividendo="";
        String divisor="";
        for(int j=0;j<2;j++){
            if(j==0){
                Element nod=(Element) nodeList.get(j);
                if((nod.getName()=="mn")||(nod.getName()=="mo")||(nod.getName()=="mi")) {
                    if(nod.getName()=="mo" && nod.getValue().toString()==":"){
                        dividendo = dividendo+"/";
                    }else{
                        dividendo = nod.getValue().toString(); 
                    }
                }
                if(nod.getName()=="mrow"){
                    dividendo = leerMrow(dividendo,nod);
                }
                if(nod.getName()=="msup"){
                    dividendo=leerMsup(dividendo,nod);
                }
                if(nod.getName()=="mfrac"){
                    dividendo=leerMfrac(dividendo,nod);
                }
                if(nod.getName()=="mroot"){
                    dividendo=leerMfrac(dividendo,nod);
                }
                if(nod.getName()=="mfenced"){
                    dividendo=leerMfenced(dividendo,nod);
                }
                if(nod.getName()=="msqrt"){
                    dividendo=leerMsqrt(dividendo,nod);
                }
            }
            if(j==1){
                Element nod=(Element) nodeList.get(j);
                if((nod.getName()=="mn")||(nod.getName()=="mo")||(nod.getName()=="mi")) {
                    if(nod.getName()=="mo" && nod.getValue().toString()==":"){
                        divisor = divisor+"/";
                    }else{
                        divisor= divisor + nod.getValue().toString(); 
                    }
                }
                if(nod.getName()=="mrow"){
                    divisor = leerMrow(divisor,nod);
                }
                if(nod.getName()=="msup"){
                    divisor=leerMsup(divisor,nod);
                }
                if(nod.getName()=="mfrac"){
                    divisor=leerMfrac(divisor,nod);
                }
                if(nod.getName()=="mroot"){
                    divisor=leerMroot(divisor,nod);
                }
                if(nod.getName()=="mfenced"){
                    divisor=leerMfenced(divisor,nod);
                }
                if(nod.getName()=="msqrt"){
                    divisor=leerMfenced(divisor,nod);
                }
            }
        }
        
        if(ParentesisNecesario(dividendo)){
            dividendo=AnadirParentesisConvenidos(dividendo);
        }
        if(ParentesisNecesario(divisor)){
            divisor=AnadirParentesisConvenidos(divisor);
        }
        
        cadena= cadena + dividendo + "/" + divisor;
        return cadena;
    }

    @objid ("cdacc1b4-f4bc-47bd-b762-c2c3d44c51c6")
    public static boolean isNumeric(String str) {
        try{  
          double d = Double.parseDouble(str);  
        }catch(NumberFormatException nfe){  
          return false;  
        }
        return true;
    }

    @objid ("6ed21015-ac31-426d-b0d3-50afa408a86e")
    private static String leerMsup(String cadena, Element node) {
        List nodeList = node.getChildren();
        String base="";
        String exponente="";
        for(int j=0;j<2;j++){
            if(j==0){
                Element nod=(Element) nodeList.get(j);
                if((nod.getName()=="mn")||(nod.getName()=="mo")||(nod.getName()=="mi")) {
                    if(nod.getName()=="mo" && nod.getValue().toString()==":"){
                        base = base+"/";
                    }else{
                        base = base + nod.getValue().toString(); 
                    }
                }
                if(nod.getName()=="mrow"){
                    base = leerMrow(base,nod);
                }
                if(nod.getName()=="msup"){
                    base=leerMsup(base,nod);
                   }
                   if(nod.getName()=="mfrac"){
                       base=leerMfrac(base,nod);
                   }
                   if(nod.getName()=="mroot"){
                       base=leerMroot(base,nod);
                   }
                   if(nod.getName()=="mfenced"){
                       base=leerMfenced(base,nod);
                   }
                   if(nod.getName()=="msqrt"){
                       base=leerMsqrt(base,nod);
                   }
            }
            if(j==1){
                Element nod=(Element) nodeList.get(j);
                if((nod.getName()=="mn")||(nod.getName()=="mo")||(nod.getName()=="mi")) {
                    if(nod.getName()=="mo" && nod.getValue().toString()==":"){
                        exponente = exponente+"/";
                    }else{
                        exponente = exponente + nod.getValue().toString(); 
                    } 
                } 
                if(nod.getName()=="mrow"){
                    exponente = leerMrow(exponente,nod);
                }
                if(nod.getName()=="msup"){
                    exponente=leerMsup(exponente,nod);
                }
                if(nod.getName()=="mfrac"){
                    exponente=leerMfrac(exponente,nod);
                }
                if(nod.getName()=="mroot"){
                    exponente=leerMroot(exponente,nod);
                }
                if(nod.getName()=="mfenced"){
                    exponente = leerMfenced(exponente,nod);
                }
                if(nod.getName()=="msqrt"){
                    exponente = leerMsqrt(exponente,nod);
                }
            }    
        }
        
        if(ParentesisNecesario(base)){
            base=AnadirParentesisConvenidos(base);
        }
        if(ParentesisNecesario(exponente)){
            exponente=AnadirParentesisConvenidos(exponente);
        }
        cadena=cadena + base+"^"+exponente;
        return cadena;
    }

    @objid ("a894603e-6020-4788-8002-53f3a9ec4954")
    private static String leerMrow(String cadena, Element node) {
        List nodeList = node.getChildren();
        String interior="";
        for(int j=0;j<nodeList.size();j++){
            Element nod=(Element) nodeList.get(j);
            if((nod.getName()=="mn")||(nod.getName()=="mo")||(nod.getName()=="mi")) {
                if(nod.getName()=="mo" && nod.getValue().toString()==":"){
                    interior = interior+"/";
                }else{
                    interior = interior + nod.getValue().toString(); 
                }
            } 
            if(nod.getName()=="mrow"){
                interior = leerMrow(interior,nod);
            }
            if(nod.getName()=="msup"){
                interior = leerMsup(interior,nod);
            }
            if(nod.getName()=="mfrac"){
                interior = leerMfrac(interior,nod);
            }
            if(nod.getName()=="mroot"){
                interior = leerMroot(interior,nod);
            }
            if(nod.getName()=="mfenced"){
                interior = leerMfenced(interior,nod);
            }
            if(nod.getName()=="msqrt"){
                interior = leerMsqrt(interior,nod);
            }
        }
        cadena=cadena+interior;
        return cadena;
    }

    @objid ("5278ad14-99f5-4747-af69-1384de21de87")
    private static String leerMfenced(String cadena, Element node) {
        List nodeList = node.getChildren();
        /*
         * Detecta cuando se trata de una coordenada e introduce una coma para que quede de la siguiente forma:
         * (1,2)
         */
        if(node.getAttributeValue("separators") != null){
            if(node.getAttributeValue("separators").equalsIgnoreCase(";")){
                    Element elementForCoordenada=new Element("mo",ns);
                    elementForCoordenada.setText(",");
                    node.addContent(1, elementForCoordenada);
            }
        }
        /* ************************************************************************************************************/
        String interior="";
        for(int j=0;j<nodeList.size();j++){
            Element nod=(Element) nodeList.get(j);
            if((nod.getName()=="mn")||(nod.getName()=="mo")||(nod.getName()=="mi")) {
                 if(nod.getName()=="mo" && nod.getValue().toString()==":"){
                     interior = interior+"/";
                 }else{
                     interior=interior+nod.getValue().toString();
                 }  
            } 
            if(nod.getName()=="mrow"){
                interior = leerMrow(interior,nod);
            }
            if(nod.getName()=="msup"){
                interior = leerMsup(interior,nod);
            }
            if(nod.getName()=="mfrac"){
                interior = leerMfrac(interior,nod);
            }
            if(nod.getName()=="mroot"){
                interior = leerMroot(interior,nod);
            }
            if(nod.getName()=="mfenced"){
                interior = leerMfenced(interior,nod);
            }
            if(nod.getName()=="msqrt"){
                interior = leerMsqrt(interior,nod);
            }
        }
        cadena=cadena+"("+interior+")";
        return cadena;
    }

    @objid ("0caf98f1-b5fe-43c6-965e-cff28aab641b")
    private static String leerMsqrt(String cadena, Element node) {
        List nodeList = node.getChildren();
        String interior = "";
        for(int j=0;j<nodeList.size();j++){
            Element nod=(Element) nodeList.get(j);
            if((nod.getName()=="mn")||(nod.getName()=="mo")||(nod.getName()=="mi")) {
                 if(nod.getName()=="mo" && nod.getValue().toString()==":"){
                     interior = interior+"/";
                 }else{
                     interior = interior + nod.getValue().toString(); 
                 }  
            } 
            if(nod.getName()=="mrow"){
                interior = leerMrow(interior,nod);
            }
               if(nod.getName()=="msup"){
                   interior = leerMsup(interior,nod);
            }
            if(nod.getName()=="mfrac"){
                interior = leerMfrac(interior,nod);
            }
            if(nod.getName()=="mroot"){
                interior = leerMroot(interior,nod);
            }
            if(nod.getName()=="mfenced"){
                interior = leerMfenced(interior,nod);
            }
            if(nod.getName()=="msqrt"){
                interior = leerMsqrt(interior,nod);
            }
        }
        cadena= cadena + "sqrt("+interior+")";
        return cadena;
    }

    @objid ("4c2eb8bd-31b3-4564-8ace-16a10cd52209")
    public static Document String2Mathml(String mathml) {
        mathml = mathml.replace("&nbsp;", "&#xa0;");
        //Se establece el parser XML        
          //Con JDom
          SAXBuilder builder = new SAXBuilder();
        org.jdom.Document doc;
        try {
            //builder.setIgnoringBoundaryWhitespace(true);
            doc = builder.build(new StringReader(mathml));
            return doc;
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @objid ("e7d398a2-acbc-448f-a4c0-f9583b13d0b5")
    public static String Mathml2String(Document mathml) {
        //Jdom
        XMLOutputter out = new XMLOutputter();
        Format format = Format.getRawFormat();
        //Format format = Format.getCompactFormat();
        //format.setIndent(" ");
        //format.setLineSeparator("\n");
        format.setOmitEncoding(true) ;
        format.setOmitDeclaration(true);
        format.setEncoding("US-ASCII");
        //format.setTextMode(Format.TextMode.TRIM_FULL_WHITE);
        //format.setIgnoreTrAXEscapingPIs(false);
        out.setFormat(format);
        //System.out.println(out.outputString(mathml));
        String xml = (out.outputString(mathml));
        return (xml);
    }

    @objid ("6e8e04ab-390d-4347-a746-e6a91a33e8c5")
    public static boolean ParentesisNecesario(String cadena) {
        boolean respuesta=true;
        
        if(cadena.indexOf("(")==0 && posicion_final_parentesis(cadena)==cadena.length()-1)
        {respuesta=false;}
        
        if(isNumeric(cadena) || cadena.length()==1){
            respuesta=false;
        }
        return respuesta;
    }

//Al traducir a sentencia normal, a veces es nesario incluir paréntesis, por ejemplo para 2/(2-5).
//Para diferencia de los aádidos por el usuario o los añadidos por necesidas incluiremos '['
    @objid ("bc31b68a-6dda-4c89-ab40-2680f50cc9c0")
    public static String AnadirParentesisConvenidos(String cadena) {
        return "["+ cadena +"]";
    }

/*
         * Devuelve la posicion del final del primer parentesis de la cadena
         * La cadena debe empezar por '(', por ejemplo "(x+3)+..."
         */
    @objid ("7188ebae-cdcc-40c2-ab98-678d4abf5af7")
    public static int posicion_final_parentesis(String cadena) {
        int posicion_final_parentesis = 0;
        char caracter;
        int parentesis_abiertos = 0;
        int parentesis_cerrados = 0;
        int i = 0;
        while (i < cadena.length()) {
            caracter = cadena.charAt(i);
        
            switch (caracter) {
            case '(':
                parentesis_abiertos = parentesis_abiertos + 1;
                break;
            case '[':
                parentesis_abiertos = parentesis_abiertos + 1;
                break;
            case ')':
                parentesis_cerrados = parentesis_cerrados + 1;
                break;
            case ']':
                parentesis_cerrados = parentesis_cerrados + 1;
                break;
            }
        
            if (parentesis_abiertos == parentesis_cerrados) {
                posicion_final_parentesis = i;
                i = cadena.length();
            } else {
                i = i + 1;
            }
        
        }
        return posicion_final_parentesis;
    }

}
