package es.addlink.tutormates.equationEditor.Translation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ExportTranslationEditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.MathEditor.Binary;
import es.addlink.tutormates.equationEditor.MathEditor.Function;
import es.addlink.tutormates.equationEditor.MathEditor.IncorrectExpression;
import es.addlink.tutormates.equationEditor.MathEditor.IntNumber;
import es.addlink.tutormates.equationEditor.MathEditor.MathEditor;
import es.addlink.tutormates.equationEditor.MathEditor.RealNumber;
import es.addlink.tutormates.equationEditor.MathEditor.RepeatingDecimal;
import es.addlink.tutormates.equationEditor.MathEditor.SequenceList;
import es.addlink.tutormates.equationEditor.MathEditor.StringNumber;
import es.addlink.tutormates.equationEditor.MathEditor.Text;
import es.addlink.tutormates.equationEditor.MathEditor.Unary;
import es.addlink.tutormates.equationEditor.MathEditor.Variable;
import es.addlink.tutormates.equationEditor.Operators.BinaryOperator;
import es.addlink.tutormates.equationEditor.Operators.Exponent;
import es.addlink.tutormates.equationEditor.Operators.Fraction;
import es.addlink.tutormates.equationEditor.Operators.GeometricPoint;
import es.addlink.tutormates.equationEditor.Operators.GridExpression;
import es.addlink.tutormates.equationEditor.Operators.MixedRepeatingDecimal;
import es.addlink.tutormates.equationEditor.Operators.NthRoot;
import es.addlink.tutormates.equationEditor.Operators.Operator;
import es.addlink.tutormates.equationEditor.Operators.PureRepeatingDecimal;
import es.addlink.tutormates.equationEditor.Operators.SimpleOperator;
import es.addlink.tutormates.equationEditor.Operators.SquareRoot;
import es.addlink.tutormates.equationEditor.Operators.TernaryOperator;
import es.addlink.tutormates.equationEditor.Operators.UnaryOperator;
import es.addlink.tutormates.equationEditor.Role.BaseFunction;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;
import org.eclipse.swt.widgets.Control;

/**
 * Esta clase es la encargada de generar un MathEditor a partir de la expresión del editor
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("4d9cbda3-89e7-45b8-b567-3ac6d871069d")
public class ExportMathEditor {
    /**
     * Primera celda de la cuadrícula a recorrer.
     */
    @objid ("9faa2725-c802-4ed4-a95f-ec7b490f700c")
    private int firstCell;

    /**
     * Primera posición del texto de la primera celda de la cuadrícula a recorrer. Si la celda no es de tipo texto, firstChar será igual a -1.
     */
    @objid ("5cf5f78b-147d-47c7-b134-d4d20096f301")
    private int firstChar;

    /**
     * Última celda de la cuadrícula a recorrer.
     */
    @objid ("868a6af8-71ab-4f63-b0ed-b0973612f29f")
    private int lastCell;

    /**
     * Última posición del texto de la última celda de la cuadrícula a recorrer. Si la celda no es de tipo texto, lastChar será igual a -1.
     */
    @objid ("ac10aa74-7fa2-46cd-9bd1-82b9c68e3bc8")
    private int lastChar;

    @objid ("2c141a58-de96-41f9-ae90-db06f46df851")
    private Manager manager;

    /**
     * Cuadrícula desde la cual se obtiene la expresión.
     */
    @objid ("22eae279-1851-4ef9-a9bc-d2488388af41")
    private GridExpression grid;

    /**
     * Raiz de donde parte el objeto MathEditor.
     */
    @objid ("c5c8397a-2146-4d70-b3d6-7c500a6b4025")
    private MathEditor meParent;

    /**
     * Constructor
     * @param manager
     */
    @objid ("e3dfaf53-9a25-416f-a055-89f95250a47c")
    public ExportMathEditor(Manager manager) {
        this.manager = manager;
    }

    /**
     * Devuelve true si el valor es entero, false en caso contrario.
     * @param num Valor que se quiere estudiar.
     * @return True si el valor es entero, false en caso contrario.
     */
    @objid ("034ce1d3-2a62-4284-baff-7662d640b96d")
    private static boolean esEntero(String num) {
        try{
            double d = Double.parseDouble(num);
            if (d % 1 == 0)    return true;
            else return false;
        }catch(NumberFormatException ex){
            return false;
        }
    }

    /**
     * Devuelve true si se trata de un paréntesis completo de principio a fin. Ej: (234+(-3)+43) -> true ; 234+(-5) -> false.
     * @return True si se trata de un paréntesis completo de principio a fin. Ej: (234+(-3)+43) -> true ; 234+(-5) -> false.
     */
    @objid ("ac4e37e3-2a29-4a2d-8740-6c9863da1635")
    private boolean esParentesisCompleto(int firstCell, int lastCell, int firstChar, int lastChar) throws ExportTranslationEditorException {
        //System.out.println("Paréntesis: "+firstCell + ", " + lastCell + ", " + firstChar + ", " + lastChar);
        try{
            Control[] c = grid.getChildren();
        
            boolean hayAlMenosUnParentesis = false;
            int contComponente = firstCell;
            int contParentesis = 0, i = firstChar, f = lastChar;
            SimpleOperator texto;
            Operator comp;
        
            while (contComponente <= lastCell) {
            
                comp = (Operator) c[contComponente];
            
                if (comp.getTipoComponente() == Category.TextType) {
                    texto = (SimpleOperator) comp;
            
                    if (contComponente == firstCell)
                        i = firstChar;
                    else
                        i = 0;
            
                    if (contComponente == lastCell)
                        f = lastChar;
                    else
                        f = texto.getTexto().length() - 1;
            
                    while (i <= f) {
                        
                        Character ch = texto.getTexto().charAt(i);
                        List<BaseOperator> opeL = this.manager.getAllowedOperatorsAndFunctions().getOperator(ch.toString());
                        BaseOperator ope = null; 
                        
                        if(opeL.size() > 0){
                            ope = opeL.get(0);
                        }
                        
                        if(ope != null){
                            if(ope.getName().equalsIgnoreCase("openGroup")){
                                hayAlMenosUnParentesis = true;
                                contParentesis++;
                            }
                            
                            if(ope.getName().equalsIgnoreCase("closeGroup"))
                                contParentesis--;
                        }
            
                        if (contParentesis == 0) {
                            if ((contComponente == lastCell) && (i == f) && (hayAlMenosUnParentesis))
                                return true;
                            else
                                return false;
                        }
                        i++;
                    }
                }
                contComponente++;
            }
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error en la detección de un paréntesis completo.",ex);
        }
        return false;
    }

    /**
     * Devuelve true si el valor es un número real, false en caso contrario.
     * @param num Valor que se quiere estudiar.
     * @return True si el valor es un número real, false en caso contrario.
     */
    @objid ("56f7a645-4ea1-4522-a09d-92faea6b6a22")
    private static boolean esReal(String num) {
        boolean esReal;
        if (num.length() == 0) esReal = false;
        else esReal = true;
        
        try { Double.parseDouble(num);}
        catch (NumberFormatException e) { esReal = false;}
        return esReal;
    }

    /**
     * Devuelve true si es una variable reconocida por el editor, false en caso contrario.
     * @param caracter Caracter que se quiere testear.
     * @return True si es una variable reconocida por el editor, false en caso contrario.
     */
    @objid ("fafc0635-0e56-4aa5-8bcb-24fb011829a1")
    private boolean esVariable(Character caracter) {
        boolean esVar;
        try{
            if(this.manager.getAllowedOperatorsAndFunctions().existsVariable(caracter.toString())) esVar = true;
            else esVar = false;
            
            return esVar;
        }catch(Exception ex){return false;}
    }

    /**
     * Devuelve true si existe el operador simple, false en caso contrario.
     * @param simple SimpleOperator donde se encuentra el operador.
     * @param firstChar Comienzo del rango de caracteres. firstChar >= 0.
     * @param lastChar Final del rango de caracteres. lastChar <= lon-1.
     * @return True si existe el operador simple, false en caso contrario.
     */
    @objid ("55cd13b7-8511-4e33-a11c-56d084a34028")
    private Boolean existOperators(SimpleOperator simple, int firstChar, int lastChar) {
        Boolean exit = false;
        
        int i = firstChar;
        String text = simple.getTexto();
        
        while(!exit && i <= lastChar){
            if(this.manager.getAllowedOperatorsAndFunctions().existsOperator(String.valueOf(text.charAt(i))))
                exit = true;
            else
                i++;
        }
        return exit;
    }

    /**
     * Devuelve la operación binaria cuyo operador sea de tipo simple.
     * @param positions Array que en su primera celda contiene el número de celda y en la segunda celda el número de carácter. La celda a la que hace referencia siempre tiene que ser de tipo SimpleOperator.
     * @param children Colección de celdas.
     * @return Operación binaria cuyo operador sea de tipo simple.
     */
    @objid ("ad9296f0-508f-48b7-bb5f-f500c015a3f2")
    private Binary getBinary(int[] positions, Control[] children) throws ExportTranslationEditorException {
        if(positions[0] != -1 && positions[1] != -1){
            try {
                SimpleOperator so = (SimpleOperator)children[positions[0]];
                String ope = String.valueOf(so.getTexto().substring(positions[1], positions[1]+1));
                List<BaseOperator> listAllowedOperator = this.manager.getAllowedOperatorsAndFunctions().getOperator(ope);
                BaseOperator allowedOperator = listAllowedOperator.get(0);
                
                ExportMathEditor export1 = new ExportMathEditor(this.manager);
                ExportMathEditor export2 = new ExportMathEditor(this.manager);
                Binary bin = new Binary(allowedOperator.getName(), allowedOperator.getId(), meParent);
                //System.out.println("Izq: " + this.firstCell + ", " + positions[0] + ", " + this.firstChar + ", " + (positions[1]-1));
                
                 /* Controla la siguiente situación:   [1/2][+1] */
                int lCell=positions[0];
                if(positions[0] > 0 && positions[1] == 0)
                    lCell = positions[0]-1;
                
                //System.out.println("      setLeftChild: " + this.firstCell + ", " + lCell + ", " + this.firstChar + ", " + (positions[1]-1));
                bin.setLeftChild(export1.getExpression(bin,grid,this.firstCell,lCell,this.firstChar,(positions[1]-1)));
                
                 /* Controla la siguiente situación:   [1+][1/2] */
                int fCell=positions[0];
                if(positions[0]+1 < children.length && positions[1]+1 == so.getNumCaracteres())
                    fCell = positions[0]+1;
            
                //System.out.println("      setRightChild: " + fCell + ", " + this.lastCell + ", " + (positions[1]+1) + ", " + this.lastChar);
                bin.setRightChild(export2.getExpression(bin,grid,fCell,this.lastCell,(positions[1]+1),this.lastChar));
                if(bin.getLeftChild() == null || bin.getRightChild() == null) return null;
                return bin;
            }
            catch (ExportTranslationEditorException e) {
                // TODO Auto-generated catch block
                throw e;
            }
        }
        return null;
    }

    /**
     * Devuelve un objeto binario que contiene la base y el exponente de una potencia.
     * @param moPadre Padre de la potencia.
     * @param base Base de la potencia traducida a MathEditor.
     * @param exponente Exponente de la potencia sin traducir a MathEditor.
     * @return Un objeto Binario que contiene la base y el exponente de una potencia.
     */
    @objid ("12de42a7-23a0-4c93-9365-bf98af02a667")
    private MathEditor getExponente(MathEditor moPadre, MathEditor base, Exponent exponente) throws ExportTranslationEditorException {
        Binary bin = null;
        try{
            bin = new Binary(exponente.getNombre(), exponente.getId(), moPadre);
            bin.setLeftChild(base);
            bin.setRightChild(this.incluirExponente(bin, exponente));
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error en la obtención del exponente.",ex);
        }
        return bin;
    }

    @objid ("312b894e-bb78-49dc-8231-aa4173256638")
    private MathEditor getTextMathEditor(SimpleOperator so) {
        IncorrectExpression ie = new IncorrectExpression(meParent);
        Text t = new Text(ie);
        t.setText(so.getTexto());
        ie.addMathEditor(t);
        return ie;
    }

    @objid ("a89ef16a-2fee-452f-a31c-bc7d53720062")
    private MathEditor getIncorrectExpression(MathEditor meParent, GridExpression grid, int firstCell, int lastCell, int firstChar, int lastChar) throws ExportTranslationEditorException {
        //System.out.println("\nRecibe getIncorrectExpression: " + firstCell + ", " + lastCell + ", " + firstChar + ", " + lastChar);
        this.meParent = meParent;
        this.grid = grid;
        this.firstCell = firstCell;
        this.lastCell = lastCell;
        this.firstChar = firstChar;
        this.lastChar = lastChar;
        
        Control[] children = this.grid.getChildren();
        
        IncorrectExpression ie = new IncorrectExpression(meParent);
        Operator operator;
        Binary exp = null;
        
        for(int i=firstCell;i<=lastCell;i++){
            
            
            operator = (Operator)children[i];    
            //System.out.println(operator);
            
            if(operator.getTipoComponente() == Category.TextType){
                
                SimpleOperator so = (SimpleOperator)operator;
                //System.out.println(children.length + " - " + (Operator)children[i]);
                if(i<lastCell && ((Operator)children[i+1]).getNombre().equalsIgnoreCase("exponent")){
                    exp = new Binary(((Operator)children[i+1]).getNombre(), ((Operator)children[i+1]).getId(), meParent);
                    exp.setLeftChild(getTextMathEditor(so));
                }else{
                    ie.addMathEditor(getTextMathEditor(so));
                }
                
                
            }else if(exp!=null && operator.getNombre().equalsIgnoreCase("exponent")){
                exp.setRightChild(incluirExponente(exp,(Exponent)operator));
                ie.addMathEditor(exp);
                exp = null;
            }else
                ie.addMathEditor(getExpression(ie,grid,i,i,-1,-1));
        }
        //System.out.println(ie);
        return ie;
    }

    @objid ("e166af69-faff-418b-bed5-400eeb3f9632")
    private Boolean existeCaracter(String cadena, char caracter) {
        int i = 0;
        while(i<cadena.length()){
            if(cadena.charAt(i) == caracter) return true;
            i++;
        }
        return false;
    }

    @objid ("9cde612e-d0c9-4f53-b756-93b2233a816d")
    private Boolean hayComas(GridExpression grid, int firstCell, int lastCell, int firstChar, int lastChar) {
        int i = firstCell;
        Control[] children = this.grid.getChildren();
        
        while(i<=lastCell){
            Operator operator = (Operator)children[i];
            if(operator.getTipoComponente() == Category.TextType){
                SimpleOperator so = (SimpleOperator)operator;
                
                int ini=0;
                int fin=so.getTexto().length();
                
                if(i==firstCell) ini=firstChar;
                if(i==lastCell) fin=lastChar+1;
                    
                if(existeCaracter(so.getTexto().substring(ini, fin),','))
                    return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Método recursivo. Devuelve el MathEditor de toda la expresión.
     * 
     * 0) Detección de comas, para poner la expresión en forma de lista.
     * 1) Control de paréntesis.
     * 2) Si encuentra un sólo operador devuelve su MathEditor.
     * 2.1) Operadores complejos.
     * 2.2) SimpleOperators.
     * 3) Exponente
     * 4) Operadores unarios simples: !,%
     * 5) Localización de operadores del tipo: +, -, =, *, /, ^. Estos operadores se encuentran en el fichero AllowedOperators.xml
     * 5.1) Detección de operadores de prioridad 3: +, -, =.
     * 5.1.1) Control de negativos
     * 5.2) Detección de operadores de prioridad 2: *, /, :.
     * 5.3) Detección de operadores de prioridad 1: ^.
     * 6) Funciones
     * @param meParent Padre del MathEditor que devuelve éste método.
     * @param grid Cuadrícula cuyo contenido hay que traducir a MathEditor.
     * @param firstCell Celda de inicio dentro de la cuadrícula.
     * @param lastCell Celda de fin dentro de la cuadrícula.
     * @param firstChar Posición de inicio del primer TextBox encontrado dentro de los límites de los componentes inicio y fin.
     * @param lastChar Posición de fin del último TextBox encontrado dentro de los límites de los componentes inicio y fin.
     * @return MathEditor de toda la expresión.
     */
    @objid ("4ecc04b6-b3a1-4700-a1ed-cfdd87472ca7")
    private MathEditor getExpression(MathEditor meParent, GridExpression grid, int firstCell, int lastCell, int firstChar, int lastChar) throws ExportTranslationEditorException {
        //System.out.println("\nRecibe getExpression: " + firstCell + ", " + lastCell + ", " + firstChar + ", " + lastChar);
        try{
            this.meParent = meParent;
            this.grid = grid;
            this.firstCell = firstCell;
            this.lastCell = lastCell;
            this.firstChar = firstChar;
            this.lastChar = lastChar;
            
            MathEditor result = null;
            Control[] children = this.grid.getChildren();
            
            if(children.length < this.firstCell+1) return null;
            
            Operator firstOperator = (Operator)children[this.firstCell];
            Operator lastOperator = (Operator)children[this.lastCell];
            
            
            /** 1) Control de paréntesis ****************************************************************************/
            if(firstOperator.getTipoComponente() == Category.TextType){
                SimpleOperator so = (SimpleOperator)firstOperator;
        
                if(so.getTexto().length() > firstChar){
                    
                    String str = so.getTexto().substring(firstChar, firstChar+1);
                    
                    List<BaseOperator> oParenList = this.manager.getAllowedOperatorsAndFunctions().getOperatorsBySymbolEditor(str);
                    BaseOperator oParen = this.manager.getAllowedOperatorsAndFunctions().getOperatorByName(oParenList, "openGroup");
                    if(oParen != null){
                        if(so.getTexto().substring(firstChar, firstChar+1).equals(oParen.getSymbolEditor())){
                            //System.out.println("Paréntesis: " + firstCell + ", " + lastCell + ", " + firstChar + ", " + lastChar);
                            Boolean parentesisCompleto = esParentesisCompleto(firstCell,lastCell,firstChar,lastChar);
                            //System.out.println("  Parentesis completo: " + parentesisCompleto);
                            if(parentesisCompleto){
                                
                                Unary un = new Unary(oParen.getName(), oParen.getId(), meParent);
                                //System.out.println("Hijo paréntesis: " + firstCell + ", " + lastCell + ", " + (firstChar+1) + ", " + (lastChar-1));
                                
                                int fCell = -1;
                                int lCell = -1;
                                int fChar = -1;
                                int lChar = -1;
                                
                                //Situación:  (1+2-6!)
                                if(firstCell == lastCell){
                                    fCell = firstCell;
                                    lCell = lastCell;
                                    fChar = firstChar + 1;
                                    lChar = lastChar - 1;
                                }
                                else{
                                    SimpleOperator sOpe1 = (SimpleOperator)children[firstCell];
                                    
                                    if(sOpe1.getNumCaracteres()-1 > firstChar){
                                        fCell = firstCell;
                                        fChar = firstChar+1;
                                    }else{
                                        fCell = firstCell + 1;
                                        fChar = 0;
                                    }
                                    
                                    if(this.lastChar > 0){
                                        lCell = lastCell;
                                        lChar = lastChar - 1;
                                    }else{
                                        lCell = lastCell - 1;
                                        if(((Operator)children[lCell]).getTipoComponente() == Category.TextType){
                                            SimpleOperator soLCell = (SimpleOperator)children[lCell];
                                            lChar = soLCell.getNumCaracteres()-1;
                                        } 
                                    }
                                }
                                
                                //System.out.println("Interior parentesis: " + fCell + "," + lCell + "," + fChar + "," + lChar);
                                un.setChild(getExpression(un,grid,fCell,lCell,fChar,lChar));
                                return un;
                            }
                        }
                    }
                }
            }
         /* Fin control paréntesis **************************************************************************/
            
            /** 6) Funciones ******************************************************************/
            
            if(firstOperator.getTipoComponente() == Category.TextType && lastOperator.getTipoComponente() == Category.TextType){
                
                SimpleOperator fso = (SimpleOperator)firstOperator;
                SimpleOperator lso = (SimpleOperator)lastOperator;
                
                String text1 = null;
                String text2 = null;
                
                if(firstOperator != lastOperator){
                    text1 = fso.getTexto().substring(firstChar, fso.getTexto().length());
                    text2 = lso.getTexto().substring(0, lastChar+1);
                }else{
                    text1 = fso.getTexto().substring(firstChar, lastChar+1);
                    text2 = text1;
                }
                
                String functionName = "";
                int i=0;
                char letter='?';
                
                if((i) < fso.getTexto().length() && text1.length() > 0)
                    letter = text1.charAt(i);
                
                functionName = String.valueOf(letter);
                while(letter >= 'a' && letter <= 'z' && i < fso.getTexto().length()){
                    i++;
                    if((i+firstChar) < fso.getTexto().length()){
                        letter = fso.getTexto().charAt(i+firstChar);
                        functionName += String.valueOf(letter);
                    }
                }
                
                functionName = functionName.substring(0, functionName.length()-1);
                
                /*System.out.println("\n\n-------------------\nRestricciones función:");
                System.out.println("- " + this.manager.getAllowedOperatorsAndFunctions().existsFunction(functionName) + " " + functionName);
                System.out.println("- " + (letter == '('));
                System.out.println("- " + text2.substring(text2.length()-1, text2.length()).equalsIgnoreCase(")"));
                System.out.println("--------------------\n\n");*/
                
                if(this.manager.getAllowedOperatorsAndFunctions().existsFunction(functionName) && letter == '(' && text2.substring(text2.length()-1, text2.length()).equalsIgnoreCase(")")){
                    
                    BaseFunction f = this.manager.getAllowedOperatorsAndFunctions().getFunction(functionName);
                    Function fMathEditor = new Function(f.getName(),-1,this.meParent);
                    
                    if(text1 == text2){
                        
                        //MathEditor me = getExpression(fMathEditor,grid,firstCell,lastCell,firstChar + functionName.length()+1,lastChar-1);
                        //if(me == null) return null;
                        return buildFunctionContent(fMathEditor,grid,firstCell,lastCell,firstChar + functionName.length()+1,lastChar-1);
                    }else{
                        
                        int fCell = firstCell;
                        int lCell = lastCell;
                        int fChar = firstChar;
                        int lChar = lastChar;
                        
                        if(text1.length() > functionName.length()+1)
                            fChar = functionName.length()+1;
                        else{
                            fCell++;
                            fChar = 0;
                        }
                        
                        if(text2.length() > 1)
                            lChar = lastChar-1;
                        else{
                            lCell--;
                            lChar = -1;
                        }
                        //System.out.println("Envia child: " + fCell+","+lCell+","+fChar+","+lChar);
                        //MathEditor me = getExpression(fMathEditor,grid,fCell,lCell,fChar,lChar);
                        
                        return buildFunctionContent(fMathEditor,grid,fCell,lCell,fChar,lChar);
                        
                        //if(me == null) return null;
                        //fMathEditor.setChild(me);
                        //return fMathEditor;
                    }
                }
            }
        
        /* Fin funciones ******************************************************************/
            
             /** 0) Detección de comas, para poner la expresión como una lista  **************************************/
                if(hayComas(this.grid,this.firstCell,this.lastCell,this.firstChar,this.lastChar)){
                    BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorByName("sequenceList");
                    if(ope != null){
                        SequenceList sl = new SequenceList(meParent,",");
                        
                        int c=firstCell;
                        int i=firstCell;
                        int cChar=firstChar;
                        
                        while(i<=lastCell){
                            Operator operator = (Operator)children[i];
                            if(operator.getTipoComponente() == Category.TextType){
                                SimpleOperator so = (SimpleOperator)operator;
                                
                                int ini=0;
                                int fin=so.getNumCaracteres()-1;
                                
                                if(i==firstCell) ini=firstChar;
                                if(i==lastCell) fin=lastChar;
                                
                                int j=ini;
                                                        
                                while(j<=fin){
                                    
                                    if(so.getTexto().substring(j,j+1).equalsIgnoreCase(",")){
                                        
                                        //Si la coma es el último caracter de la expresión...
                                        if(i==lastCell && j==so.getNumCaracteres()-1)
                                            return null;
                                        
                                        //La coma es el primer caracter del SimpleOperator?
                                        if(j==0)
                                            sl.addMathEditor(getExpression(sl,grid,c,i-1,cChar,-1));
                                        else{
                                            sl.addMathEditor(getExpression(sl,grid,c,i,cChar,j-1));
                                        }
                                        
                                        //La coma es el último carácter del SimpleOperator?
                                        if(j==so.getNumCaracteres()-1){
                                            c=i+1;
                                            cChar=-1;
                                        }else{
                                            c=i;
                                            cChar=j+1;
                                        }
                                    }
                                    
                                    //Si no es coma, y es el último caracter de la expresión...
                                    else if(i==lastCell && j==lastChar){
                                        sl.addMathEditor(getExpression(sl,grid,c,i,cChar,lastChar));
                                    }
        
                                    j++;
                                }
                            }
                            
                            //Último componente que,además, no es texto.
                            else if(i==lastCell)
                                sl.addMathEditor(getExpression(sl,grid,c,i,cChar,-1));
                            
                            
                            i++;
                        }
                        return sl;
                    }else
                        return null;
            /* Fin control listas ************************************************************************************/
                    
                }else{
            
             
                
                
             
             /** 2) Si existe un solo operador complejo de tipo unario o binario, se genera el código MathEditor y se retorna.*/
             
                if(this.firstCell == this.lastCell){
                    
                    /**
                     * 2.1) Si existe una sola celda y ésta contiene un operador puro (unario, binario o ternario), se evalúa y se retorna en la variable result.
                     */
                        if(firstOperator.getTipoComponente() != Category.TextType){
        
                            //Sin entrada: pi, e, infinito
                            if(firstOperator.getTipoComponente() == Category.WithoutEntriesType){
                                result = new Variable(firstOperator.getNombre(),false,meParent);
                            }
                            
                            //Unarios
                            if(firstOperator.getTipoComponente() == Category.UnaryType){
                                UnaryOperator unary = (UnaryOperator)firstOperator;
                                if(unary.getNombre().equalsIgnoreCase("squareRoot"))
                                    result = incluirRaiz(this.meParent,unary);
                            }
        
                            else if(firstOperator.getTipoComponente() == Category.UnaryComplexType){
                                //System.out.println("ERROR fichero ExportMathEditor.java, línea 416. Notificar.");
                            }
                            
                            //Binarios
                            else if(firstOperator.getTipoComponente() == Category.BinaryType){
                                BinaryOperator binary = (BinaryOperator)firstOperator;
                                if(binary.getNombre().equalsIgnoreCase("fraction"))
                                    result = incluirFraccion(this.meParent,binary);
                                if(binary.getNombre().equalsIgnoreCase("nthRoot"))
                                    result = incluirNthRoot(this.meParent,binary);
                                if(binary.getNombre().equalsIgnoreCase("repeatingDecimal"))
                                    result = incluirPeriodicoPuro(this.meParent,binary);
                                if(binary.getNombre().equalsIgnoreCase("geometricPoint"))
                                    result = incluirGeometricPoint(this.meParent,binary);
                            }
        
                            //Ternarios
                            else if(firstOperator.getTipoComponente() == Category.TernaryType){
                                TernaryOperator ternary = (TernaryOperator)firstOperator;
                                if(ternary.getNombre().equalsIgnoreCase("repeatingDecimal"))
                                    result = incluirPeriodicoMixto(this.meParent,ternary);
                            }
                            
                            return result;
                        }
                     /* ****************************************************************************************************************/
                    
                    /**
                     * 2.2) Si la única celda contiene un SimpleOperator sin operadores, se evalúa el contenido y se retorna.
                     * Puede tener los siguientes valores: enteros, reales, variables, números y variables (con multiplicación implícita).
                     */
                        else{
                            SimpleOperator simple = (SimpleOperator)firstOperator;
                            //System.out.println("  Existen operadores: " + existOperators(simple,this.firstChar,this.lastChar));
                            if(!existOperators(simple,this.firstChar,this.lastChar))
                                result = getProductoDeValores(this.meParent,this.grid,this.firstCell,this.lastCell,this.firstChar,this.lastChar);
                        }
                     /* ****************************************************************************************************************/
                }
             /* ************************************************************************************************************************/
        
             /** 3) Exponente **********************************************************************************************************/ 
                else if(lastOperator.getNombre().equals("exponent")){
                    //System.out.println("  Última celda es un exponente.");
                    boolean ok = false;
                    SimpleOperator so = null;
                    
                    Operator o = (Operator)children[lastCell-1];
        
                    int lChar = -1;
                    if(o.getTipoComponente() == Category.TextType){
                        so = (SimpleOperator)o;
                        lChar = so.getNumCaracteres()-1;
                    }
                    
                    /*
                     *  Este módulo, decide cuando el exponente debe ser incluído en el método "getProductoDeValores" o no.
                     *  En este método, solo se añade el exponente en los siguientes casos:
                     *  - [raiz]^[exponente]
                     *  - ([raiz])^[exponente]
                     */
                        if(firstCell + 1 == lastCell){
                            if(o.getTipoComponente() != Category.TextType)
                                ok=true;
                            
                            else{
                                SimpleOperator s = (SimpleOperator)o;
                                if(esParentesisCompleto(firstCell,lastCell-1,firstChar,s.getNumCaracteres()-1))
                                    ok=true;
                            }
                        }else{
                            if(o.getTipoComponente() == Category.TextType){
                                SimpleOperator s = (SimpleOperator)o;
                                if(esParentesisCompleto(firstCell,lastCell-1,firstChar,s.getNumCaracteres()-1))
                                    ok=true;
                            }
                        }
                    /* *****************************************************************************************/
                    
                    if(ok){
                        Binary bin = new Binary(lastOperator.getNombre(), lastOperator.getId(), meParent);
                        bin.setLeftChild(getExpression(bin,grid,firstCell,lastCell-1,firstChar,lChar));
                        
                        UnaryOperator un = (UnaryOperator)lastOperator;
                        Exponent ex = (Exponent)un;
                        bin.setRightChild(incluirExponente(bin,ex));
                        return bin;
                    }
                }
                
             /* Fin exponente ************************************************************************************************************/
        
            /** 4) Control de operadores unarios:  !, % *************************************************************************************/ 
                if(lastOperator.getTipoComponente() == Category.TextType){
                    SimpleOperator so = (SimpleOperator)lastOperator;
                    if(so.getTexto().length() > lastChar && lastChar != -1){
                        
                        String lastCharSo = so.getTexto().substring(lastChar, lastChar+1);
                        
                        List<BaseOperator> opeL = this.manager.getAllowedOperatorsAndFunctions().getOperatorsBySymbolEditor(lastCharSo);
                        if(opeL.size() > 0){
                            BaseOperator opee = opeL.get(0);
                            if(!opee.getName().equalsIgnoreCase("openGroup") && !opee.getName().equalsIgnoreCase("closeGroup")){
                                //System.out.println(opee.getName());
                                if(this.manager.getAllowedOperatorsAndFunctions().existsOperatorByTypeAndPriority(Category.UnaryType, -1, lastCharSo)){
                                    BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperator(lastCharSo).get(0);
                                    int fCell = firstCell;
                                    int lCell = lastCell;
                                    int fChar = firstChar;
                                    int lChar = lastChar;
                                    
                                    if(firstCell == lastCell)
                                        lChar--;
                                    else{
                                        if(so.getTexto().substring(0, lastChar+1).length() > 1)
                                            lChar--;
                                        else
                                            lCell--;
                                    }
                                    
                                    //System.out.println("  Contenido op unario ["+lastCharSo+"]: " + fCell + "," + lCell + "," + fChar + "," + lChar);
                                    Unary un = new Unary(ope.getName(), ope.getId(), this.meParent);
                                    un.setChild(getExpression(un,grid,fCell,lCell,fChar,lChar));
                                    return un;
                                }
                            }
                        }
                    }
                }
            /* Fin control operadores unarios ******************************************************************/    
            
            int[] positions;
            
        
            /** 5) ******************************************************************/
            
            /* 5.1) Operadores de prioridad 3: +, -, = ***************************************************/
                //System.out.println("  SE EVALUAN PRIODIDADES DE OPERADORES");
                positions = getPositionLastOperatorByPriority(3,grid,firstCell,lastCell,firstChar,lastChar);
                //System.out.println("  Entra en operadores de prioridad 3.");
                //System.out.println("1: " + positions[0] + ", " + positions[1]);
                if(positions[0] != -1 && positions[1] != -1){
                    
                    //Este if, detecta si se trata de una resta o un negativo
                    if(positions[0] == firstCell && positions[1] == firstChar){
                        
                         /* 5.1.1) Control de negativos *******************************************/
                        
                            SimpleOperator so = (SimpleOperator)firstOperator;
                            String string = so.getTexto();
                            
                            if(string.substring(firstChar, firstChar+1).equals("-")){
                                
                                Unary un = new Unary("negative", 32, meParent);
                                int fCell = firstCell;
                                int lCell = lastCell;
                                int fChar = firstChar;
                                int lChar = lastChar;
        
                                if(firstCell < lastCell){
                                    
                                    /*
                                     * Este caso controla situaciones como éstas:
                                     * [-][1/2]
                                     * [-2][^3]
                                     */
                                    if(so.getNumCaracteres()-1 > firstChar)
                                        fChar++;
                                    else{
                                        fCell++;
                                        fChar = 0;
                                    }
                                }else
                                    /*
                                     * Este otro, como ésta:
                                     * [-2]
                                     */
                                    fChar++;
                                
                                un.setChild(getExpression(un,grid,fCell,lCell,fChar,lChar));
                                return un;
                            }
                         /* Fin control negativos ************************************************/
                            
                    }else{
                        return getBinary(positions,children);
                    }
                }
            
                
            /* 5.2) Operadores de prioridad 2: *, / ********************************************************/
                
                positions = getPositionLastOperatorByPriority(2,grid,firstCell,lastCell,firstChar,lastChar);
                //System.out.println("2: " + positions[0] + ", " + positions[1]);
                if(positions[0] != -1 && positions[1] != -1)
                    return getBinary(positions,children);
                
                
            /* 5.3) Operadores de prioridad 1: ^ **********************************************************/
                
                positions = getPositionLastOperatorByPriority(1,grid,firstCell,lastCell,firstChar,lastChar);
                //System.out.println("1: " + positions[0] + ", " + positions[1]);
                if(positions[0] != -1 && positions[1] != -1)
                    return getBinary(positions,children);
            
                
            
            //Productos implícitos
                Boolean isFunction = false;
                if(firstOperator.getTipoComponente() == Category.TextType){
                    SimpleOperator so = (SimpleOperator)firstOperator;
                    String texto="";
                    
                    if(firstOperator == lastOperator)
                        texto = so.getTexto().substring(firstChar, lastChar+1);
                    else
                        texto = so.getTexto().substring(firstChar);
                        
                    isFunction = isFunction(texto);
                    
                }
                
                if(!isFunction){
                    MathEditor me = getProductoImplicito(meParent,grid,firstCell,this.lastCell,firstChar,lastChar);
                    if(me != null)
                        return me;
                }
                
                
            
                
                
            return getProductoDeValores(this.meParent,this.grid,this.firstCell,this.lastCell,this.firstChar,this.lastChar);                
                }
        }catch(Exception ex){
            //ex.printStackTrace();
            throw new ExportTranslationEditorException(ex);
        }
    }

    /**
     * Recorre el interior de una función y la devuelve entera.
     * Tiene en cuenta el número de entradas de las funciones, obteniendo cada una de ellas cuando se encuentran separadas por comas.
     * @return
     */
    @objid ("888183ee-04b3-44a6-8a8c-02c3f43166e5")
    private MathEditor buildFunctionContent(Function me, GridExpression grid, int firstCell, int lastCell, int firstChar, int lastChar) {
        //System.out.println("buildFunctionContent: " + firstCell + "," + lastCell + "," + firstChar + "," + lastChar);
        try {
            
            int i=firstCell;
            int a = -1;
            int inicioCell = firstCell;
            int inicioChar = firstChar;
            Boolean ok = false;
            int contParent = 0;
            
            while(i<=lastCell){
                
                Operator ope = (Operator)grid.getChildren()[i];
                
                //Situación 1:  funcion(1,2), funcion(4); siempre y cuando el interior de la función sea todo texto.
                    if(i == firstCell && i == lastCell && ope.getTipoComponente() == Category.TextType){
                        SimpleOperator so = (SimpleOperator)ope;
                        
                        int j = firstChar;
                        int inicio = firstChar;
                        
                        while(j<lastChar){
                            Character c = so.getTexto().charAt(j);
                            
                            if(c.equals('(')) contParent++;
                            if(c.equals(')')) contParent--;
                            
                            if(c.equals(',') && contParent==0){
                                MathEditor contMe = getExpression(me,grid,firstCell,lastCell,inicio,j-1);
                                me.addChild(contMe);
                                inicio = j+1;
                            }                    
                            j++;
                        }
            
                        MathEditor contMe = getExpression(me,grid,firstCell,lastCell,inicio,j);
                        
                        me.addChild(contMe);
                        ok=true;
                    }
                //Fin Situación 1
                
                    
                //Situación 2: en el interior de la función no solo puede haber texto
                    else{
                        
                        if(ope.getTipoComponente() == Category.TextType){
                            SimpleOperator so = (SimpleOperator)ope;
                            
                            a = firstChar;
                            if(i != firstCell)
                                a = 0;
                            
                            
                            
                            int z = lastChar;
                            if(z != lastCell)
                                z = so.getTexto().length()-1;
                            
                            Boolean salida = false;
                            
                            //System.out.println("z: " + z);
                            
                            while(a <= z && !salida){
                                if(so.getTexto().substring(a, a+1).equals(",")){
                                    
                                    //situación:  abs([raiz],...), abs([raiz]+1,...) 
                                    if(i >= inicioCell){
                                        
                                        MathEditor contMe = null;
                                        
                                        if(a == 0)
                                            contMe = getExpression(me,grid,inicioCell,i-1,inicioChar,-1);
                                        else
                                            contMe = getExpression(me,grid,inicioCell,i,inicioChar,a-1);
                                        
                                        
                                        me.addChild(contMe);
                                        
                                        if((a+1) <= z){
                                            inicioChar = a+1;
                                            inicioCell = i;
                                        }else{
                                            inicioChar = -1;
                                            inicioCell = i+1;
                                        }
                                    }
                                    
                                    salida = true;
                                }else
                                    a++;
                            }
                            
                            if(!salida)
                                a = a-2;
                        }
                        
                    }
                //Fin Situación 2
        
                i++;
            }
            
            if(!ok){
                
                Operator ope = (Operator)grid.getChildren()[i-1];
                MathEditor contMe = null;
                
                //System.out.println("wea: " + inicioCell + "," + lastCell + "," + inicioChar + "," + lastChar + "," + a + "," + finalChar);
                
                if(ope.getTipoComponente() == Category.TextType){
                    //System.out.println("a: " + inicioCell + "," + lastCell + "," + inicioChar + "," + lastChar);
                    contMe = getExpression(me,grid,inicioCell,lastCell,inicioChar,lastChar);
                }else{
                    //System.out.println("b: " + inicioCell + "," + lastCell + "," + (a+1) + "," + lastChar);
                    contMe = getExpression(me,grid,inicioCell,lastCell,a+1,lastChar);
                }
        
                me.addChild(contMe);
            }
            //System.out.println("-----------");
            //System.out.println(me);
            //System.out.println("-----------");
            
            return me;
        
        }
        catch (ExportTranslationEditorException e) {
            //e.printStackTrace();
            return null;
        }
    }

    @objid ("d31468c2-64b9-40d8-bfb6-30569ae4d984")
    private Boolean isFunction(String cadena) {
        int i=0;
        if(cadena.length() > 0){
        char letter = cadena.charAt(i);
        String functionName = "";
        while(i < cadena.length() && letter != '('){
            functionName += letter;
            i++;
            if(i < cadena.length())
                letter = cadena.charAt(i);
        }
        
        return this.manager.getAllowedOperatorsAndFunctions().existsFunction(functionName);
        }
        return false;
    }

    /**
     * Devuelve un objeto MathEditor que contiene la expresión.
     * @param cuadriculaEdicion Cuadrícula contenedora de la expresión que se quiere traducir a MathEditor.
     * @return Objeto MathEditor que contiene la expresión.
     */
    @objid ("a9482ee3-8d71-485c-a9fa-718eb68c0f30")
    private MathEditor getMathEditor(GridExpression cuadriculaEdicion) throws ExportTranslationEditorException {
        try{
        
            if (cuadriculaEdicion.getChildren().length != 0) {
                int posicionUltimoComp = cuadriculaEdicion.getChildren().length - 1;
                Control[] c = cuadriculaEdicion.getChildren();
                int posFinal = -1;
                if (((Operator) c[posicionUltimoComp]).getNombre() == Category.TextType)
                    posFinal = ((SimpleOperator) c[posicionUltimoComp]).getTexto().length() - 1;
            
                this.meParent = null;
                this.grid = cuadriculaEdicion;
                this.firstCell = 0;
                this.lastCell = posicionUltimoComp;
                this.firstChar = 0;
                this.lastChar = posFinal;
                
                SimpleOperator active = this.manager.getStateManager().getTextoActivo();
                
                if(this.manager.getStateManager().getTextoActivo().getNumCaracteres()==0){
                    if((Operator)cuadriculaEdicion.getChildren()[this.lastCell] == active)
                    this.lastCell--;
                }
                if(this.manager.getStateManager().getTextoActivo().getNumCaracteres()==0){
                    if((Operator)cuadriculaEdicion.getChildren()[0] == active)
                        this.firstCell++;
                }
                
                MathEditor moFinal = null;
                
                //System.out.println("Llamada: " + firstCell + ", " + lastCell + ", " + firstChar + ", " + lastChar);
                
                moFinal = getExpression(this.meParent, this.grid, this.firstCell, this.lastCell, this.firstChar, this.lastChar);
                
                if (MathEditor.isCorrect(moFinal))
                    return moFinal;
                else{
                    if(!this.manager.getTutorMatesEditor().getCorrectExpresion()){
                        return getIncorrectExpression(this.meParent, cuadriculaEdicion, 0, cuadriculaEdicion.getNumColumnas()-1, this.firstChar, this.lastChar);
                    }
                }
                    
            }
            else
                return null;
        
            return null;
            
        }catch(Exception ex){
            //ex.printStackTrace();
            throw new ExportTranslationEditorException("Error en la obtención de un objeto de tipo MathEditor.",ex);
        }
    }

    /**
     * Primero se eliminan los Textox vacío y luego devuelve el MathEditor con la expresión contenida en la cuadrícula pasada por parámetro.
     * @param cuadriculaEdicion Cuadrícula cuyo contenido se quiere traducir a MathEditor.
     * @return El MathEditor con la expresión contenida en la cuadrícula pasada por parámetro.
     */
    @objid ("ed2895ee-6bf5-4b44-9cd0-c03cee687acc")
    public MathEditor getMathEditorCargar(GridExpression cuadriculaEdicion) throws ExportTranslationEditorException {
        MathEditor objeto = null;
        try{
            objeto = getMathEditor(cuadriculaEdicion);
            if(MathEditor.isCorrect(objeto))
                this.manager.getCentralManager().eliminarTextosVacios(cuadriculaEdicion);
            return objeto;
            
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error en la obtención de un objeto de tipo MathEditor.",ex);
        }
    }

    /**
     * Devuelve el MathEditor con la expresión contenida en la cuadrícula pasada por parámetro.
     * @param cuadriculaEdicion Cuadrícula cuyo contenido se quiere traducir a MathEditor.
     * @return El MathEditor con la expresión contenida en la cuadrícula pasada por parámetro.
     */
    @objid ("c0c6e269-7915-42d2-8663-de49ab37d07a")
    public MathEditor getMathEditorCargarHistorial(GridExpression cuadriculaEdicion) throws ExportTranslationEditorException {
        try{
            return getMathEditor(cuadriculaEdicion);
        }catch(Exception ex){
            //ex.printStackTrace();
            throw new ExportTranslationEditorException("Error en la obtención de un objeto de tipo MathEditor en el guardado del historial. Este error puede ser mostrado de forma errónea en caso de que la expresión no esté formada de forma correcta o inacabada.",ex);
        }
    }

    @objid ("239db3ad-5c74-4df2-bc08-a92c5ea22f94")
    private int[] getPositionFirstOperatorByPriority(int priority, GridExpression grid, int firstCell, int lastCell, int firstChar, int lastChar) {
        //System.out.println("getPositionFirstOperatorByPriority: " + firstCell + ", " + lastCell + ", " + firstChar + ", " + lastChar);
        Boolean existOperators = false;
        
        int[] result = new int[2];
        
        int numCell = firstCell;
        int numChar = 0;
        int numCharCont = 0;
        Control[] children = grid.getChildren();
        String string = null; 
        
        while(numCell <= lastCell && !existOperators){
            
            Operator operator = (Operator)children[numCell];
            SimpleOperator so = null;
            
            if(operator.getTipoComponente() == Category.TextType)
                so = (SimpleOperator)operator;
            
            //Primera celda
            if(numCell == firstCell){
                if(so != null){
                    if(firstCell == lastCell)
                        string = so.getTexto().substring(firstChar, lastChar+1);
                    else
                        string = so.getTexto().substring(firstChar, so.getNumCaracteres());
                    
                    numChar = firstChar;
                }
            }
            
            //Última celda
            else if(numCell == lastCell){
                if(so != null){
                    if(firstCell == lastCell){
                        string = so.getTexto().substring(firstChar, lastChar+1);
                        numChar = firstChar;
                    }else{
                        string = so.getTexto().substring(0, lastChar+1);
                        numChar = 0;
                    }
                }
            }
            
            //Celdas del medio
            else{
                if(so != null){
                    string = so.getTexto().substring(0, so.getNumCaracteres());
                    numChar = 0;
                }
            }
            
            if(so != null){
                numCharCont = 0;
                while(numCharCont<string.length() && !existOperators){
                    String ope = String.valueOf(string.charAt(numCharCont));
                    if(this.manager.getAllowedOperatorsAndFunctions().existsOperatorByPriority(priority, ope))
                        existOperators = true;
                    else
                        numCharCont++;
                }
            }
            
            so = null;
            if(!existOperators)
                numCell++;
        }
        
        if(existOperators){
            result[0] = numCell;
            result[1] = numChar + numCharCont;
        }else{
            result[0] = -1;
            result[1] = -1;
        }
        return result;
    }

    @objid ("e1df3f79-3b01-4642-afe7-89823aea655b")
    private int[] getPositionLastOperatorByPriority(int priority, GridExpression grid, int firstCell, int lastCell, int firstChar, int lastChar) {
        //System.out.println("getPositionFirstOperatorByPriority: priority:" + priority +"; " + firstCell + ", " + lastCell + ", " + firstChar + ", " + lastChar);
        Boolean existOperators = false;
        
        int[] result = new int[2];
        
        int numCell = lastCell;
        int numChar = 0;
        int numCharCont = 0;
        Control[] children = grid.getChildren();
        String string = null; 
        int contParenthesis = 0;
        
        while(numCell >= firstCell && !existOperators){
            
            Operator operator = (Operator)children[numCell];
            SimpleOperator so = null;
            
            if(operator.getTipoComponente() == Category.TextType)
                so = (SimpleOperator)operator;
            
            //Primera celda
            if(numCell == firstCell){
                if(so != null){
                    if(firstCell == lastCell){
                        string = so.getTexto().substring(firstChar, lastChar+1);
                    }
                    else
                        string = so.getTexto().substring(firstChar, so.getNumCaracteres());
                    
                    numChar = firstChar;
                }
            }
            
            //Última celda
            else if(numCell == lastCell){
                if(so != null){
                    string = so.getTexto().substring(0, lastChar+1);
                    numChar = 0;
                }
            }
            
            //Celdas del medio
            else if(so != null){
                string = so.getTexto().substring(0, so.getNumCaracteres());
                numChar = 0;
            }
            
            if(so != null){
                numCharCont = string.length() - 1;
                
                while(numCharCont >= 0 && !existOperators){
                    
                    String symbol = String.valueOf(string.charAt(numCharCont));
                    List<BaseOperator> opeL = this.manager.getAllowedOperatorsAndFunctions().getOperatorsBySymbolEditor(symbol);
                    BaseOperator ope = null;
                    if(opeL.size() > 0){
                        ope = opeL.get(0);
                    
                        if(ope.getName().equalsIgnoreCase("openGroup"))
                        //if(symbol.equals(")"))
                            contParenthesis++;
                        
                        else if(ope.getName().equalsIgnoreCase("closeGroup"))
                        //else if(symbol.equals("("))
                            contParenthesis--;
                    }
                    
                    if(this.manager.getAllowedOperatorsAndFunctions().existsOperatorByPriority(priority, symbol) && contParenthesis == 0){
                        /* Controla que a un operador le pueda seguir un negativo. Ej: y=-4 */
                            if(symbol.equals("-") && numCharCont > 0){
                                String ope2 = String.valueOf(string.charAt(numCharCont-1));
                                if(!ope2.equals("-") && this.manager.getAllowedOperatorsAndFunctions().existsOperatorByPriority(priority, ope2)){
                                    numCharCont--;
                                    existOperators = false;
                                }else
                                    existOperators = true;
                        /* ******************************************************************/
                            }else
                                existOperators = true;
                    }else
                        numCharCont--;
                }
            }
            
            so = null;
            if(!existOperators)
                numCell--;
        }
        
        if(existOperators){
            result[0] = numCell;
            result[1] = numCharCont + numChar;
        }else{
            result[0] = -1;
            result[1] = -1;
        }
        
        //System.out.println("Resultados: " + result[0] + ", " + result[1]);
        return result;
    }

    /**
     * Devuelve un objeto MathEditor que contiene números y/o variables solos o multiplicándose entre ellos.
     * Admite que éstas estén sin operador intermedio (ej: 2xy, 2[raiz]) y también detecta cuando un exponente pertenece a un número o
     * variable. Es capaz de detectar situaciones como éstas (ej: 2x^2y^2).
     * @param moPadre Padre del MathEditor que devuelve éste método.
     * @param cuadricula Cuadrícula cuyo contenido hay que traducir a MathEditor.
     * @param posCompInicio Componente de inicio dentro de la cuadrícula.
     * @param posCompFinal Componente de fin dentro de la cuadrícula.
     * @param posInicio Posición de inicio del primer TextBox encontrado dentro de los límites de los componentes inicio y fin.
     * @param posFinal Posición de fin del último TextBox encontrado dentro de los límites de los componentes inicio y fin.
     * @return Un objeto MathEditor que contiene números y/o variables solos o multiplicándose entre ellos.
     */
    @objid ("03dfd67d-098b-495e-bfce-3b509f5760d7")
    private MathEditor getProductoDeValores(MathEditor moPadre, GridExpression cuadricula, int posCompInicio, int posCompFinal, int posInicio, int posFinal) throws ExportTranslationEditorException {
        //System.out.println("getProductoDeValores: " + posCompInicio + "," + posCompFinal + "," + posInicio + "," + posFinal);
        List<MathEditor> listaProductos = null;
        
        try{
            
            listaProductos = new ArrayList<MathEditor>();
            
            String acumulado = "";
            String tipoAnterior = "";
            MathEditor numvar;
            
            SimpleOperator texto;
            Character caracter;
            int ini,fin;
            
            // Recorre los operadores (simples y complejos)
            for(int i=posCompInicio;i<=posCompFinal;i++){
                
                acumulado = "";
                if(((Operator)cuadricula.getChildren()[i]).getTipoComponente() == Category.TextType){
                    
                    texto = (SimpleOperator)cuadricula.getChildren()[i];
                    if(i == posCompInicio)
                        ini = posInicio;
                    else
                        ini = 0;
                    
                    if(i == posCompFinal)
                        fin = posFinal;
                    else
                        fin = texto.getNumCaracteres()-1;
            
                    
                    // Recorre los caracteres de los diferentes componentes de tipo Texto que va encontrando.
                    for(int j=ini;j<=fin;j++){
            
                        caracter = texto.getTexto().charAt(j);
        
                        /**
                         * Explicación detallada del proceso que viene a continuación:
                         * 
                         * La lista llamada "listaProductos" contiene los MathEditors que se van a multiplicar entre sí.
                         * Por ejemplo, si en la lista tenemos lo siguiente:
                         *     |<intNumber>56</intNumber>|<variable>a</variable>|<variable>b</variable>
                         *     significa que al final quedará una expresión como esta: 56ab ó 56·a·b
                         * 
                         * Lo que hace el proceso es identificar grupos de caracteres para introducirlos en la lista.
                         * Si encuentra varios números seguidos, los agrupará en un sólo número entero, sin embargo, si encuentra varias
                         * variables seguidas, insertará cada una en un componente de la lista diferente.
                         * 
                         * Antes de introducir un componente dentro de la lista, se verifica si éste es la base de alguna potencia.
                         * 
                         */
                        
                        if(!esVariable(caracter)){
                            //Detecta si se ha introducido un caracter que no está registrado como variable
                            if(caracter >= 'a' && caracter <='z'){
                                return null;
                            }
                            
                            if(tipoAnterior.equalsIgnoreCase("variable")){
                                
                                //se inserta en la lista el número
                                numvar = getValor(acumulado, meParent);
                                
                                 /* Verifica si el numvar pertenece a alguna base de una potencia */
                                    if((i<posCompFinal) && (j==fin) && (((Operator)cuadricula.getChildren()[i+1]).getTipoComponente() == Category.UnaryComplexType)){
                                        UnaryOperator cun = (UnaryOperator)cuadricula.getChildren()[i+1];
                                        if(cun.getNombre().equals("exponent")){
                                            numvar = this.getExponente(moPadre, numvar, (Exponent)cuadricula.getChildren()[i+1]);
                                            i++;
                                        }
                                    }
                                 /* *************************************************************/
                                
                                listaProductos.add(numvar);
                                acumulado = caracter.toString();
                            }else{
                                acumulado += caracter;
                                
                                if(j == fin){
                                    
                                    //se inserta en la lista el número
                                    numvar = getValor(acumulado, meParent);
                                    
                                     //Verifica si el numvar pertenece a alguna base de una potencia 
                                        if((i<posCompFinal) && (j==fin) && (((Operator)cuadricula.getChildren()[i+1]).getTipoComponente() == Category.UnaryComplexType)){
                                            UnaryOperator cun = (UnaryOperator)cuadricula.getChildren()[i+1];
                                            if(cun.getNombre().equals("exponent")){
                                                numvar = this.getExponente(moPadre, numvar, (Exponent)cuadricula.getChildren()[i+1]);
                                                i++;
                                            }
                                        }
                                     /* *************************************************************/
                                    
                                    listaProductos.add(numvar);
                                    
                                    acumulado = "";
                                }
                            }
                            tipoAnterior = "numerico";
                        }
                        else{
                            
                            if(tipoAnterior.equalsIgnoreCase("numerico")){
                                
                                //se inserta en la lista el número
                                numvar = getValor(acumulado, meParent);
                                
                                if(numvar != null)
                                    listaProductos.add(numvar);
                                
                                //se inserta en la lista la variable
                                numvar = getValor(caracter.toString(), meParent);
                                
                                 //Verifica si el numvar pertenece a alguna base de una potencia 
                                    if((i<posCompFinal) && (j==fin) && (((Operator)cuadricula.getChildren()[i+1]).getTipoComponente() == Category.UnaryComplexType)){
                                        UnaryOperator cun = (UnaryOperator)cuadricula.getChildren()[i+1];
                                        if(cun.getNombre().equals("exponent")){
                                            numvar = this.getExponente(moPadre, numvar, (Exponent)cuadricula.getChildren()[i+1]);
                                            i++;
                                        }
                                    }
                                 /* *************************************************************/
                                
                                listaProductos.add(numvar);
                                
                            }else{
                                //se inserta en la lista la variable
                                numvar = getValor(caracter.toString(), meParent);
                                
                                 //Verifica si el numvar pertenece a alguna base de una potencia 
                                    if((i<posCompFinal) && (j==fin) && (((Operator)cuadricula.getChildren()[i+1]).getTipoComponente() == Category.UnaryComplexType)){
                                        UnaryOperator cun = (UnaryOperator)cuadricula.getChildren()[i+1];
                                        if(cun.getNombre().equals("exponent")){
                                            numvar = this.getExponente(moPadre, numvar, (Exponent)cuadricula.getChildren()[i+1]);
                                            i++;
                                        }
                                    }
                                 /* *************************************************************/
                                
                                listaProductos.add(numvar);
                            }
                            acumulado = "";
                            tipoAnterior = "variable";
                        }        
                    }
                    
                }else{
                    
                    if(tipoAnterior == "variable" || tipoAnterior == "numerico"){
                        
                        numvar = getExpression(moPadre,cuadricula,i,i,0,-1);
                        
                         //Verifica si el numvar pertenece a alguna base de una potencia 
                            if((i<posCompFinal) && (((Operator)cuadricula.getChildren()[i+1]).getNombre().equals("exponent"))){
                                numvar = this.getExponente(moPadre, numvar, (Exponent)cuadricula.getChildren()[i+1]);
                                i++;
                            }
                         /* *************************************************************/
                        
                         /* En este punto se guarda el operador complejo en el listado de productos.
                         * Ej:  xy1/2
                         * Se guardaría la fracción "1/2"
                         * 
                         * Se verifica si no se trata de un periódico porque la siguiente expresión sería incorrecta:
                         *          __ 
                         * x 12.342334
                         */
                         
                            
                            if(numvar.getName() != "repeatingDecimal"){
                                listaProductos.add(numvar);
                                tipoAnterior = "componente";
                                
                         /* *************************************************************/
                        }else
                            return null;
                    }else
                        return null;
                }
            }
            
            if(listaProductos.size() == 1)
                return listaProductos.get(0);
        
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error en la obtención de un producto de valores.",ex);
        }
        return multiplicaLista(moPadre,listaProductos);
    }

    @objid ("e8a2ca6d-2763-4e5e-b853-b5bc8d52d651")
    private MathEditor getProductoImplicito(MathEditor meParent, GridExpression grid, int firstCell, int lastCell, int firstChar, int lastChar) {
        //System.out.println("Recibe getProductoImplicito: " + firstCell + ", " + lastCell + ", " + firstChar + ", " + lastChar);
        try {
        
            int i = firstCell;
            Boolean exit = false;
            
            Control[] children = this.grid.getChildren();
            
            //Recorre las celdas del grid
            while(i <= lastCell && !exit){
                Operator operator = (Operator)children[i];
                String previous = "";
                
                if(operator.getTipoComponente() == Category.TextType){
                    SimpleOperator so = (SimpleOperator)operator;
                    int j;
                    int numChars;
                    
                    String now = "";
                    
                    if(i == firstCell)
                        j = firstChar;
                    else
                        j = 0;
                    
                    if(i == lastCell)
                        numChars = lastChar;
                    else
                        numChars = so.getNumCaracteres()-1;
                    
                    //Recorre los caracteres escritos en una caja de texto
                    while(j <= numChars){
                        
                        String value = so.getTexto().substring(j, j+1);
                        
                        if(esEntero(value) || value.equals("."))
                            now = "number";
                        
                        if(esVariable(value.charAt(0)))
                            now = "variable";
                        
                        if(this.manager.getAllowedOperatorsAndFunctions().existsOperator(value))
                            now = "simpleOperator";
                        
                        if((now != previous && !previous.equals("") && !now.equals("simpleOperator") && !previous.equals("simpleOperator")) ||
                                (now.equals("variable") && previous.equals("variable") && !now.equals("simpleOperator") && !previous.equals("simpleOperator"))){
                            
                            List<BaseOperator> listAllowedOperator = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName("invisibleMultiplication");
                            BaseOperator allowedOperator = listAllowedOperator.get(0);
                            
                            //System.out.println("  -Izq: " + firstCell + ", " + i + ", " + firstChar + ", " + (j-1));
                            //System.out.println("  -Der: " + i + ", " + lastCell + ", " + (j) + ", " + lastChar);
                            Binary bin = new Binary(allowedOperator.getName(), allowedOperator.getId(), meParent);
                            bin.setLeftChild(getExpression(bin,grid,firstCell,i,firstChar,(j-1)));
                            bin.setRightChild(getExpression(bin,grid,i,lastCell,j,lastChar));
                            return bin;
                        }
                        else{
                            
                            previous = now;
                            j++;
                        }
                    }
        
                }else
                    previous = "complexOperator";
                
                i++;
            }
            if(getPositionFirstOperatorByPriority(3,grid,firstCell,lastCell,firstChar,lastChar)==null)
                return getProductoDeValores(meParent, grid, firstCell, lastCell, firstChar, lastChar);
            else
                return null;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * Devuelve un objeto MathEditor según el tipo de valor pasado por parámetro. Si no se corresponde con ninguno, devuelve null.
     * @param cadena Cadena de caracteres que se quieren pasar a MathEditor.
     * @return Un objeto MathEditor según el tipo de valor pasado por parámetro. Si no se corresponde con ninguno, devuelve null.
     */
    @objid ("e1c77c72-7cdc-4148-84aa-8d386b66a6b4")
    private MathEditor getValor(String cadena, MathEditor meParent) throws ExportTranslationEditorException {
        try{
            if(cadena.length() > 0){
                int i = 0;
                while(cadena.length() > i && cadena.charAt(i) == '+')
                    i++;
                
                cadena = cadena.substring(i);
            }
            
            if (esEntero(cadena))
                return new IntNumber(new BigInteger(cadena), meParent);
            else if (esReal(cadena))
                return new RealNumber(new BigDecimal(cadena), meParent);
            else if(this.manager.getAllowedOperatorsAndFunctions().existsVariable(cadena))
                return new Variable(cadena,true, meParent);
            
            return null;
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error al construir un BigInteger.",ex);
        }
    }

    /**
     * Devuelve un objeto MathEditor que sólo contiene el exponente, sin la base.
     * @param binPadre Objeto binario donde irá alojado el exponente que devuelva éste método.
     * @param e Exponente necesario para ser traducido a MathEditor.
     * @return Objeto MathEditor que sólo contiene el exponente, sin la base.
     */
    @objid ("440b84ca-f359-417b-bf2a-bd843630fb49")
    private MathEditor incluirExponente(Binary binPadre, Exponent e) throws ExportTranslationEditorException {
        GridExpression cuadriculaEdicion = null;
        int nComponentes = 0;
        int posFinal = -1;
        
        try{
        
            cuadriculaEdicion = e.getCuadriculaEdicion1();
            Control[] chilEdicion = cuadriculaEdicion.getChildren();
            nComponentes = chilEdicion.length - 1;
            Operator compEdicion = (Operator) chilEdicion[nComponentes];
            
            if (compEdicion.getNombre() == Category.TextType) {
                posFinal = ((SimpleOperator) compEdicion).getTextBox().getText().length() - 1;
                if (chilEdicion.length == 1) {
                    if (((SimpleOperator) chilEdicion[0]).getNumCaracteres() == 0)
                        return null;
                }
            }
        
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error al incluir un exponente.",ex);
        }
        
        MathEditor child = getExpression(binPadre, cuadriculaEdicion, 0, nComponentes, 0, posFinal);
        
        
        if(!MathEditor.isCorrect(child) && !this.manager.getTutorMatesEditor().getCorrectExpresion())
            child = getIncorrectExpression(binPadre, cuadriculaEdicion, 0, nComponentes, 0, posFinal);
        return child;
    }

    /**
     * Devuelve un objeto MathEditor de tipo binario que contiene la raíz enésima.
     * @param moPadre Padre del nuevo MathEditor creado en éste método.
     * @param comp Componente que lleva la fracción para ser traducida a MathEditor.
     * @return Objeto MathEditor de tipo binario que contiene la raíz enésima al completo.
     */
    @objid ("24b7fded-bdb7-4a05-a120-2565b6ddab62")
    private MathEditor incluirNthRoot(MathEditor moPadre, BinaryOperator comp) throws ExportTranslationEditorException {
        Binary bi = null;
        
        try{
        
            NthRoot f = (NthRoot) comp;
            GridExpression cuadriculaEdicion1 = f.getCuadriculaEdicion2();
            GridExpression cuadriculaEdicion2 = f.getCuadriculaEdicion1();
            if(cuadriculaEdicion1 != null){
                Control[] chilEdicion1 = cuadriculaEdicion1.getChildren();
                int nComponentes1 = chilEdicion1.length - 1;
                Operator compEdicion1 = (Operator) chilEdicion1[nComponentes1];
                int posFinal1 = -1;
                if (compEdicion1.getNombre() == Category.TextType)
                    posFinal1 = ((SimpleOperator) compEdicion1).getTexto().length() - 1;
        
                Control[] chilEdicion2 = cuadriculaEdicion2.getChildren();
                int nComponentes2 = chilEdicion2.length - 1;
                Operator compEdicion2 = (Operator) chilEdicion2[nComponentes2];
                int posFinal2 = -1;
                if (compEdicion2.getNombre() == Category.TextType)
                    posFinal2 = ((SimpleOperator) compEdicion2).getTexto().length() - 1;
                
                MathEditor left = getExpression(bi, cuadriculaEdicion1, 0, nComponentes1, 0, posFinal1);
                MathEditor right = getExpression(bi, cuadriculaEdicion2, 0, nComponentes2, 0, posFinal2);
                
                if(!MathEditor.isCorrect(left) && !this.manager.getTutorMatesEditor().getCorrectExpresion())
                    left = getIncorrectExpression(bi, cuadriculaEdicion1, 0, nComponentes1, 0, posFinal1);
                
                if(!MathEditor.isCorrect(right) && !this.manager.getTutorMatesEditor().getCorrectExpresion())
                    right = getIncorrectExpression(bi, cuadriculaEdicion2, 0, nComponentes2, 0, posFinal2);
                
                bi = new Binary(comp.getNombre(), comp.getId(), moPadre);
                bi.setLeftChild(left);
                bi.setRightChild(right);
            }
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error al incluir una raíz enésima.",ex);
        }
        return bi;
    }

    /**
     * Devuelve un objeto MathEditor de tipo binario que contiene la fracción al completo.
     * @param moPadre Padre del nuevo MathEditor creado en éste método.
     * @param comp Componente que lleva la fracción para ser traducida a MathEditor.
     * @return Objeto MathEditor de tipo binario que contiene la fracción al completo.
     */
    @objid ("059b25c6-697c-4e02-a938-cc2b6f65427e")
    private MathEditor incluirFraccion(MathEditor moPadre, BinaryOperator comp) throws ExportTranslationEditorException {
        Binary bi = null;
        
        try{
        
            Fraction f = (Fraction) comp;
            GridExpression cuadriculaEdicion1 = f.getCuadriculaEdicion1();
            GridExpression cuadriculaEdicion2 = f.getCuadriculaEdicion2();
            if(cuadriculaEdicion1 != null){
                Control[] chilEdicion1 = cuadriculaEdicion1.getChildren();
                int nComponentes1 = chilEdicion1.length - 1;
                Operator compEdicion1 = (Operator) chilEdicion1[nComponentes1];
                int posFinal1 = -1;
                if (compEdicion1.getNombre() == Category.TextType)
                    posFinal1 = ((SimpleOperator) compEdicion1).getTexto().length() - 1;
        
                Control[] chilEdicion2 = cuadriculaEdicion2.getChildren();
                int nComponentes2 = chilEdicion2.length - 1;
                Operator compEdicion2 = (Operator) chilEdicion2[nComponentes2];
                int posFinal2 = -1;
                if (compEdicion2.getNombre() == Category.TextType)
                    posFinal2 = ((SimpleOperator) compEdicion2).getTexto().length() - 1;
                
                MathEditor left = getExpression(bi, cuadriculaEdicion1, 0, nComponentes1, 0, posFinal1);
                MathEditor right = getExpression(bi, cuadriculaEdicion2, 0, nComponentes2, 0, posFinal2);
                
                if(!MathEditor.isCorrect(left) && !this.manager.getTutorMatesEditor().getCorrectExpresion())
                    left = getIncorrectExpression(bi, cuadriculaEdicion1, 0, nComponentes1, 0, posFinal1);
                
                if(!MathEditor.isCorrect(right) && !this.manager.getTutorMatesEditor().getCorrectExpresion())
                    right = getIncorrectExpression(bi, cuadriculaEdicion2, 0, nComponentes2, 0, posFinal2);
                
                bi = new Binary(comp.getNombre(), comp.getId(), moPadre);
                bi.setLeftChild(left);
                bi.setRightChild(right);
            }
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error al incluir una fracción.",ex);
        }
        return bi;
    }

    /**
     * Devuelve un objeto MathEditor de tipo binario que contiene la fracción al completo.
     * @param moPadre Padre del nuevo MathEditor creado en éste método.
     * @param comp Componente que lleva la fracción para ser traducida a MathEditor.
     * @return Objeto MathEditor de tipo binario que contiene la fracción al completo.
     */
    @objid ("03999e8e-3f52-4fe6-a9a2-b20a318ee79b")
    private MathEditor incluirGeometricPoint(MathEditor moPadre, BinaryOperator comp) throws ExportTranslationEditorException {
        Binary bi = null;
        
        try{
        
            GeometricPoint c = (GeometricPoint) comp;
            GridExpression cuadriculaEdicion1 = c.getCuadriculaEdicion1();
            GridExpression cuadriculaEdicion2 = c.getCuadriculaEdicion2();
            
            Control[] chilEdicion1 = cuadriculaEdicion1.getChildren();
            int nComponentes1 = chilEdicion1.length - 1;
            Operator compEdicion1 = (Operator) chilEdicion1[nComponentes1];
            int posFinal1 = -1;
            if (compEdicion1.getNombre() == Category.TextType)
                posFinal1 = ((SimpleOperator) compEdicion1).getTexto().length() - 1;
            
            Control[] chilEdicion2 = cuadriculaEdicion2.getChildren();
            int nComponentes2 = chilEdicion2.length - 1;
            Operator compEdicion2 = (Operator) chilEdicion2[nComponentes2];
            int posFinal2 = -1;
            if (compEdicion2.getNombre() == Category.TextType)
                posFinal2 = ((SimpleOperator) compEdicion2).getTexto().length() - 1;
            
            MathEditor left = getExpression(bi, cuadriculaEdicion1, 0, nComponentes1, 0, posFinal1);
            MathEditor right = getExpression(bi, cuadriculaEdicion2, 0, nComponentes2, 0, posFinal2);
            
            if(!MathEditor.isCorrect(left) && !this.manager.getTutorMatesEditor().getCorrectExpresion())
                left = getIncorrectExpression(bi, cuadriculaEdicion1, 0, nComponentes1, 0, posFinal1);
            
            if(!MathEditor.isCorrect(right) && !this.manager.getTutorMatesEditor().getCorrectExpresion())
                right = getIncorrectExpression(bi, cuadriculaEdicion2, 0, nComponentes2, 0, posFinal2);
            
            bi = new Binary(comp.getNombre(), comp.getId(), moPadre);
            bi.setLeftChild(left);
            bi.setRightChild(right);
        
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error en la obtención de una coordenada.",ex);
        }
        return bi;
    }

    /**
     * Devuelve un objeto MathEditor de tipo periódico. Sólo detecta los periódicos mixtos.
     * @param moPadre Padre del MathEditor que devuelve éste método.
     * @param comp Componente que lleva el periódico para ser traducido a MathEditor.
     * @return Objeto MathEditor de tipo periódico.
     */
    @objid ("6bfd8e18-0a07-448f-9b54-ff9475dbba76")
    private MathEditor incluirPeriodicoMixto(MathEditor moPadre, TernaryOperator comp) throws ExportTranslationEditorException {
        RepeatingDecimal ped = null;
        
        try{
        
            MixedRepeatingDecimal f = (MixedRepeatingDecimal) comp;
            GridExpression cuadriculaEdicion1 = f.getCuadriculaEdicion1();
            GridExpression cuadriculaEdicion2 = f.getCuadriculaEdicion2();
            GridExpression cuadriculaEdicion3 = f.getCuadriculaEdicion3();
            String parteEntera;
            String parteDecimal;
            String partePeriodica;
            Boolean esNegativo = false;
            
            if(cuadriculaEdicion1 != null){        
                
                SimpleOperator tEntera = null;
        
                // Parte entera
                Control[] chilEdicion1 = cuadriculaEdicion1.getChildren();
                tEntera = (SimpleOperator) chilEdicion1[0];
                parteEntera = tEntera.getTexto();
                if(parteEntera.length()>0 && parteEntera.subSequence(0, 1).equals("-")){
                    esNegativo = true;
                    parteEntera = parteEntera.substring(1, parteEntera.length());
                }
        
                // Parte decimal
                Control[] chilEdicion2 = cuadriculaEdicion2.getChildren();
                SimpleOperator t = (SimpleOperator) chilEdicion2[0];
                parteDecimal = t.getTexto();
        
                
                // Parte periódica
                Control[] chilEdicion3 = cuadriculaEdicion3.getChildren();
                SimpleOperator tPeriodica = (SimpleOperator) chilEdicion3[0];
                partePeriodica = tPeriodica.getTexto();
                
        
                if (chilEdicion3.length > 0) {
                    ped = new RepeatingDecimal(comp.getNombre(), comp.getId(), moPadre);
                    try{
                        ped.setFirstChild(new IntNumber(new BigInteger(parteEntera), ped));
                        
                    }catch(Exception ex){
                        if(!this.manager.getTutorMatesEditor().getCorrectExpresion())
                            ped.setFirstChild(getTextMathEditor(tEntera));
                        else
                            ped.setFirstChild(null);
                    }
                    
                    
                    ped.setSecondChild(new StringNumber(parteDecimal, ped));
                    ped.setThirdChild(new StringNumber(partePeriodica, ped));
                }
                
                
                
                if(esNegativo){
                    Unary un = new Unary("negative", 10, moPadre);
                    un.setChild(ped);
                    return un;
                }
            }
        
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error al incluir un periódico mixto.",ex);
        }
        return ped;
    }

    /**
     * Devuelve un objeto MathEditor de tipo periódico. Sólo detecta los periódicos puros.
     * @param moPadre Padre del MathEditor que devuelve éste método.
     * @param comp Componente que lleva el periódico para ser traducido a MathEditor.
     * @return Objeto MathEditor de tipo periódico.
     */
    @objid ("9a765d2f-d019-41ae-bdac-19ed00469896")
    private MathEditor incluirPeriodicoPuro(MathEditor moPadre, BinaryOperator comp) throws ExportTranslationEditorException {
        RepeatingDecimal ped = null;
        try{
            PureRepeatingDecimal f = (PureRepeatingDecimal) comp;
            GridExpression cuadriculaEdicion1 = f.getCuadriculaEdicion1();
            GridExpression cuadriculaEdicion2 = f.getCuadriculaEdicion2();
            
            if(cuadriculaEdicion1 != null){
            
                String parteEntera;
                String partePeriodica;
                Boolean esNegativo = false;
        
                
                SimpleOperator tEntera = null;
        
                // Parte entera
                Control[] chilEdicion1 = cuadriculaEdicion1.getChildren();
                tEntera = (SimpleOperator) chilEdicion1[0];
                parteEntera = tEntera.getTexto();
                if(parteEntera.length()>0 && parteEntera.subSequence(0, 1).equals("-")){
                    esNegativo = true;
                    parteEntera = parteEntera.substring(1, parteEntera.length());
                }
        
                // Parte periódica
                Control[] chilEdicion2 = cuadriculaEdicion2.getChildren();
                SimpleOperator tPeriodica = (SimpleOperator) chilEdicion2[0];
                partePeriodica = tPeriodica.getTexto();
        
                if (chilEdicion2.length > 0) {
                    ped = new RepeatingDecimal(comp.getNombre(), comp.getId(), moPadre);
                    try{
                        ped.setFirstChild(new IntNumber(new BigInteger(parteEntera), ped));
                        
                    }catch(Exception ex){
                        if(!this.manager.getTutorMatesEditor().getCorrectExpresion())
                            ped.setFirstChild(getTextMathEditor(tEntera));
                        else
                            ped.setFirstChild(null);
                    }
                    
                    
                    ped.setSecondChild(null);
                    ped.setThirdChild(new StringNumber(partePeriodica, ped));
                }
                
                if(esNegativo){
                    List<BaseOperator> opeL = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName("negative");
                    if(opeL.size() > 0){
                        BaseOperator ope = opeL.get(0);
                        Unary un = new Unary(ope.getName(), ope.getId(), moPadre);
                        un.setChild(ped);
                        return un;
                    }
                }
            }
            
        }catch(Exception ex){
            //ex.printStackTrace();
            throw new ExportTranslationEditorException("Error al incluir un periódico puro.",ex);
        }
        //System.out.println(ped);
        return ped;
    }

    /**
     * Devuelve un objeto MathEditor de tipo unario que contiene la raíz al completo.
     * @param moPadre Padre del nuevo MathEditor creado en éste método.
     * @param comp Componente que lleva la raíz para ser traducida a MathEditor.
     * @return Objeto MathEditor de tipo unario que contiene la raíz al completo.
     */
    @objid ("46d89b0e-1757-414a-97dd-174fc7b7a183")
    private MathEditor incluirRaiz(MathEditor moPadre, UnaryOperator comp) throws ExportTranslationEditorException {
        Unary un = null;
        
        try{
            SquareRoot r = (SquareRoot) comp;
            GridExpression cuadriculaEdicion = r.getCuadriculaEdicion1();
            if(cuadriculaEdicion != null){
                Control[] chilEdicion = cuadriculaEdicion.getChildren();
                int nComponentes = chilEdicion.length - 1;
                Operator compEdicion = (Operator) chilEdicion[nComponentes];
        
                int posFinal = -1;
        
                if (compEdicion.getNombre() == Category.TextType)
                    posFinal = ((SimpleOperator) compEdicion).getTexto().length() - 1;
        
                MathEditor child = getExpression(un, cuadriculaEdicion, 0, nComponentes, 0, posFinal);
                if(!MathEditor.isCorrect(child) && !this.manager.getTutorMatesEditor().getCorrectExpresion())
                    child = getIncorrectExpression(un, cuadriculaEdicion, 0, nComponentes, 0, posFinal);
                
                un = new Unary(comp.getNombre(), comp.getId(), moPadre);
                un.setChild(child);
            }
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error al incluir una raíz.",ex);
        }
        return un;
    }

    /**
     * Devuelve un objeto MathEditor que contiene los componentes de la lista pasada por parámetro multiplicados entre sí.
     * @param moPadre Padre del objeto binario que vaya en primer nivel.
     * @param lista Lista cuyos componentes se quieren multiplicar entre sí.
     * @return Un objeto MathEditor que contiene los componentes de la lista pasada por parámetro multiplicados entre sí.
     */
    @objid ("4bb8e0f6-c833-46f2-897f-f50c7649a494")
    private MathEditor multiplicaLista(MathEditor moPadre, List<MathEditor> lista) throws ExportTranslationEditorException {
        Binary bin = null;
        try{
            BaseOperator operacion = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName("invisibleMultiplication").get(0);
            String nombre = operacion.getName();
            int id = operacion.getId();
            bin = new Binary(nombre,id,moPadre);
            
            if(lista.size() == 2){
                MathEditor objetoIzq = lista.get(0);
                MathEditor objetoDer = lista.get(1);
                bin.setLeftChild(objetoIzq);
                bin.setRightChild(objetoDer);
            }else{
                if(lista.size() == 0) return null;
                
                MathEditor objeto = lista.get(0);
                lista.remove(objeto);
                bin.setLeftChild(objeto);
                bin.setRightChild(multiplicaLista(bin,lista));
            }
        }catch(Exception ex){
            throw new ExportTranslationEditorException("Error en la obtención de una operación binaria.",ex);
        }
        return bin;
    }

}
