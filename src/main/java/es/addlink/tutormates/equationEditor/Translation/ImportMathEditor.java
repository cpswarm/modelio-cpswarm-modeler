package es.addlink.tutormates.equationEditor.Translation;

import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ImportTranslationEditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Formats.FormatTextBox;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Manager.PathManager;
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
import es.addlink.tutormates.equationEditor.Operators.EulerNumber;
import es.addlink.tutormates.equationEditor.Operators.Exponent;
import es.addlink.tutormates.equationEditor.Operators.Fraction;
import es.addlink.tutormates.equationEditor.Operators.GeometricPoint;
import es.addlink.tutormates.equationEditor.Operators.GridExpression;
import es.addlink.tutormates.equationEditor.Operators.Infinity;
import es.addlink.tutormates.equationEditor.Operators.MixedRepeatingDecimal;
import es.addlink.tutormates.equationEditor.Operators.NthRoot;
import es.addlink.tutormates.equationEditor.Operators.Pi;
import es.addlink.tutormates.equationEditor.Operators.PureRepeatingDecimal;
import es.addlink.tutormates.equationEditor.Operators.SimpleOperator;
import es.addlink.tutormates.equationEditor.Operators.SquareRoot;
import es.addlink.tutormates.equationEditor.Operators.UnaryOperator;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;
import org.eclipse.swt.widgets.Control;

/**
 * Esta clase carga la expresión recibida en MathEditor en el editor.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("c8239c60-02ac-48d8-9462-8a065d23d60a")
public class ImportMathEditor {
    @objid ("acd192eb-43e1-47b0-a7a0-c93d1843fc88")
    private Manager manager;

    /**
     * Constructor
     */
    @objid ("12fd5b61-cac4-47e0-a7cf-575a9b9a442a")
    public ImportMathEditor(Manager manager) {
        this.manager = manager;
    }

    /**
     * Carga el MathEditor pasado por parámetro en el editor.
     * @param arbol MathEditor que se cargará en el editor.
     * @param cuadriculaPadre Cuadrícula donde se cargará la expresión.
     */
    @objid ("c1890583-c0e7-49f7-8afb-6f55bdb02795")
    public void loadMathEditor(MathEditor arbol, GridExpression cuadriculaPadre) throws ImportTranslationEditorException {
        if (MathEditor.isCorrect(arbol)) {
            
            /* Expresión con textos intercalados. MathEditor formado correctamente, siendo expresión matemática incorrecta */
                if(arbol.getClass().getName().equalsIgnoreCase(PathManager.getMathEditorClasses() + ".IncorrectExpression")){
                    IncorrectExpression ie = (IncorrectExpression)arbol;
                    Iterator<MathEditor> ite = ie.getList().iterator();
                    while(ite.hasNext())
                        loadMathEditor(ite.next(),cuadriculaPadre);
                }
            /* *******************************************************/
            
                
            /* Texto */
                else if (arbol.getClass().getName().equals(PathManager.getMathEditorClasses()+".Text")) {
                    try{
                        Text txt = (Text) arbol;
                        insertarTexto(txt.getText(), cuadriculaPadre);
                    }catch(ImportTranslationEditorException ex){
                        throw new ImportTranslationEditorException(ex.getMessage(),ex);
                    }catch(Exception ex){
                        throw new ImportTranslationEditorException("Error en la importación de un texto.",ex);
                    }
                }
            /* *******************************************************/
                
            /* Sequence List */
                else if (arbol.getClass().getName().equals(PathManager.getMathEditorClasses()+".SequenceList")) {
                    try{
                        SequenceList sl = (SequenceList) arbol;
                        int i = 0;
                        
                        while(i<sl.getNumItems()){
                            loadMathEditor(sl.getItem(i),cuadriculaPadre);
                            i++;
                            if(i<sl.getNumItems())
                                insertarTexto(sl.getSeparator(), cuadriculaPadre);
                        }
                    }catch(ImportTranslationEditorException ex){
                        throw new ImportTranslationEditorException(ex.getMessage(),ex);
                    }catch(Exception ex){
                        throw new ImportTranslationEditorException("Error en la importación de un sequence list.",ex);
                    }
                }
            /* *******************************************************/    
            
            /* Binarios */
            
                else if (arbol.getClass().getName().equalsIgnoreCase(PathManager.getMathEditorClasses() + "." + Category.BinaryType)) {
                    Binary bin = (Binary) arbol;
                    
                    /* Operador simple y binario (+,-,·) */
                        if(this.manager.getAllowedOperatorsAndFunctions().existsOperatorByTypeAndName(Category.BinaryType, bin.getName())) {
                            try{
                                List<BaseOperator> opeList = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName(bin.getName());
                                BaseOperator ope = opeList.get(0);
                                
                                if(ope.isText()){
                                
                                    loadMathEditor(bin.getLeftChild(), cuadriculaPadre);
                                    Character operador = ope.getSymbolEditor().charAt(0);
                                    insertarTexto(operador.toString(), cuadriculaPadre);
                                    loadMathEditor(bin.getRightChild(), cuadriculaPadre);
                                }
                                else {
                                    
                                    BinaryOperator cBin = null;
                                    
                                    /* Raíz enésima */
                                    try{
                                        if (arbol.getName().equals("nthRoot")){
                                            cBin = new NthRoot(this.manager, cuadriculaPadre);
                                        }
                                    }catch(Exception ex){
                                        throw new ImportTranslationEditorException("Error en la importación de una raíz enésima.",ex);
                                    }
                                /* *******************************************************/
                
                                    /* Fracción */
                                        try{
                                            if (arbol.getName().equals("fraction")){
                                                cBin = new Fraction(this.manager, cuadriculaPadre);
                                            }
                                        }catch(Exception ex){
                                            throw new ImportTranslationEditorException("Error en la importación de una fracción.",ex);
                                        }
                                    /* *******************************************************/
                                        
                                    /* Coordenada */
                                        try{
                                            if(arbol.getName().equals("geometricPoint"))
                                                cBin = new GeometricPoint(this.manager, cuadriculaPadre);
                                        }catch(Exception ex){
                                            throw new ImportTranslationEditorException("Error en la importación de una coordenada.",ex);
                                        }
                                    /* *******************************************************/
                
                                    /* Exponente */
                                        try{
                                            if(arbol.getName().equals("exponent")){                                
                                                
                                                loadMathEditor(bin.getLeftChild(), cuadriculaPadre);
                                                
                                                UnaryOperator cUna = new Exponent(this.manager, cuadriculaPadre);
                                                
                                                GridExpression cua2 = cUna.getCuadriculaEdicion1();
                                                Control[] ch2 = cua2.getChildren();
                                                this.manager.getStateManager().setTextoActivo((SimpleOperator) ch2[0]);
                                                this.manager.getStateManager().setPosicionCursor(0);
                                                
                                                loadMathEditor(bin.getRightChild(), cUna.getCuadriculaEdicion1());
        
                                                this.manager.getCentralManager().setFocoUltimoComponente(cua2);
                                                
                                                bin = null;
                                                
                                                this.manager.getStateManager().setTextoActivo(null);
                                                this.manager.getStateManager().setPosicionCursor(-1);
                                            }
                                        }catch(Exception ex){
                                            throw new ImportTranslationEditorException("Error en la importación de un exponente.",ex);
                                        }
                                    /* *******************************************************/
                                        
                                    /* Común para todos los componentes de tipo Binario */
                                        try{
                                            if (bin != null) {
                                                GridExpression cua1 = cBin.getCuadriculaEdicion1();
                                                Control[] ch1 = cua1.getChildren();
                                                this.manager.getStateManager().setTextoActivo((SimpleOperator) ch1[0]);
                                                this.manager.getStateManager().setPosicionCursor(0);
                                                loadMathEditor(bin.getLeftChild(), cBin.getCuadriculaEdicion1());
                        
                                                GridExpression cua2 = cBin.getCuadriculaEdicion2();
                                                Control[] ch2 = cua2.getChildren();
                                                this.manager.getStateManager().setTextoActivo((SimpleOperator) ch2[0]);
                                                this.manager.getStateManager().setPosicionCursor(0);
                                                loadMathEditor(bin.getRightChild(), cBin.getCuadriculaEdicion2());
                                                
                                                this.manager.getStateManager().setTextoActivo(null);
                                                this.manager.getStateManager().setPosicionCursor(-1);
                                            }
                                        }catch(Exception ex){
                                            throw new ImportTranslationEditorException("Error en el módulo común en la importación de los componentes binarios.",ex);
                                        }
                                    /* *******************************************************/
                                }
                                
                            }catch(ImportTranslationEditorException ex){
                                throw new ImportTranslationEditorException(ex.getMessage(),ex);
                            }catch(Exception ex){
                                throw new ImportTranslationEditorException("Error en la importación de un operador.",ex);
                            }
                        }
                    /* *******************************************************/
                    
                        
                        //this.manager.getStateManager().setTextoActivo(null);
                        //this.manager.getStateManager().setPosicionCursor(-1);
                }
            /* *******************************************************/
        
            /* Unarios */
                else if (arbol.getClass().getName().equals(PathManager.getMathEditorClasses()+".Unary")) {
                    Unary un = (Unary) arbol;
                    
                    /* Raíz cuadrada */
                        if (arbol.getName().equals("squareRoot")) {                                
                            try{
                                
                                SquareRoot r = new SquareRoot(this.manager, cuadriculaPadre);
                                GridExpression cua = r.getCuadriculaEdicion1();
                                Control[] ch = cua.getChildren();
                                this.manager.getStateManager().setTextoActivo((SimpleOperator) ch[0]);
                                loadMathEditor(un.getChild(), r.getCuadriculaEdicion1());
            
                                this.manager.getStateManager().setTextoActivo(null);
                                this.manager.getStateManager().setPosicionCursor(-1);
                            }catch(Exception ex){
                                throw new ImportTranslationEditorException("Error en la importación de una raíz.",ex);
                            }
                        }
                    /* *******************************************************/
            
                    /* Paréntesis */
                        else if (arbol.getName().equals("openGroup")) {
                            try{
                                BaseOperator ope1 = this.manager.getAllowedOperatorsAndFunctions().getOperatorById(arbol.getID());
                                insertarTexto(ope1.getSymbolEditor(), cuadriculaPadre);
                                
                                loadMathEditor(un.getChild(), cuadriculaPadre);
                                
                                BaseOperator ope2 = this.manager.getAllowedOperatorsAndFunctions().getOperatorById(arbol.getID()+1);
                                insertarTexto(ope2.getSymbolEditor(), cuadriculaPadre);
                                if (this.manager.getStateManager().getTextoActivo() != null)
                                    this.manager.getStateManager().setPosicionCursor(this.manager.getStateManager().getTextoActivo().getNumCaracteres());
                            }catch(ImportTranslationEditorException ex){
                                throw new ImportTranslationEditorException(ex.getMessage(),ex);
                            }catch(Exception ex){
                                throw new ImportTranslationEditorException("Error en la importación de un operador.",ex);
                            }
                        }
                    /* *******************************************************/
                        
                    /* Negativos */
                        else if (arbol.getName().equals("negative")) {
                            try{
                                insertarTexto("-", cuadriculaPadre);
                                loadMathEditor(un.getChild(), cuadriculaPadre);
                            }catch(ImportTranslationEditorException ex){
                                throw new ImportTranslationEditorException(ex.getMessage(),ex);
                            }catch(Exception ex){
                                throw new ImportTranslationEditorException("Error en la importación de un negativo.",ex);
                            }
                        }
                    /* *******************************************************/
            
                    /* Operador simple unario (!,%) */
                        else if(this.manager.getAllowedOperatorsAndFunctions().existsOperatorByTypeAndName(Category.UnaryType, arbol.getName())) {
                            
                            try{
                                List<BaseOperator> opeList = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName(arbol.getName());
                                BaseOperator ope = opeList.get(0);
                                loadMathEditor(un.getChild(),cuadriculaPadre);
                                insertarTexto(ope.getSymbolEditor(),cuadriculaPadre);
                            }catch(ImportTranslationEditorException ex){
                                throw new ImportTranslationEditorException(ex.getMessage(),ex);
                            }catch(Exception ex){
                                throw new ImportTranslationEditorException("Error en la importación de un operador especial.",ex);
                            }
                        }
                    /* *******************************************************/
                }
        
            /* Periódicos */
                else if (arbol.getClass().getName().equals(PathManager.getMathEditorClasses()+".RepeatingDecimal")) {
                    
                    try{
                        RepeatingDecimal per = (RepeatingDecimal) arbol;
                        
                        boolean esPeriodicoPuro = false;
                        
                        /* Detección de un periódico puro o mixto:
                         * 
                         * Si el "secondChild" es nulo, significa que no hay decimal -> es Puro
                         * Si el "secondChild" no es nulo pero está vacío, significa que no hay decimal -> es Puro
                         * 
                         * En las demás ocasiones -> es Mixto
                         */
                        
                        if(per.getSecondChild() != null && ((StringNumber) per.getSecondChild()).getText().trim().equals(""))
                            esPeriodicoPuro = true;
                        
                        if((per.getSecondChild() == null)||(esPeriodicoPuro)){
                            //Es periódico puro
                            
                            PureRepeatingDecimal objPeriodico = new PureRepeatingDecimal(this.manager, cuadriculaPadre);
                            String parteEntera = String.valueOf(((IntNumber) per.getFirstChild()).getNumber());
                            String partePeriodica = ((StringNumber) per.getThirdChild()).getText();
            
                            SimpleOperator texto1 = ((SimpleOperator) objPeriodico.getCuadriculaEdicion1().getChildren()[0]);
                            SimpleOperator texto2 = ((SimpleOperator) objPeriodico.getCuadriculaEdicion2().getChildren()[0]);
            
                            texto1.setTexto(parteEntera);
                            texto2.setTexto(partePeriodica);
                            
                        }else{
                            //Es periódico mixto
                            
                            MixedRepeatingDecimal objPeriodico = new MixedRepeatingDecimal(this.manager, cuadriculaPadre);
                            String parteEntera = String.valueOf(((IntNumber) per.getFirstChild()).getNumber());
                            String parteDecimal = ((StringNumber) per.getSecondChild()).getText();
                            String partePeriodica = ((StringNumber) per.getThirdChild()).getText();
            
                            SimpleOperator texto1 = ((SimpleOperator) objPeriodico.getCuadriculaEdicion1().getChildren()[0]);
                            SimpleOperator texto2 = ((SimpleOperator) objPeriodico.getCuadriculaEdicion2().getChildren()[0]);
                            SimpleOperator texto3 = ((SimpleOperator) objPeriodico.getCuadriculaEdicion3().getChildren()[0]);
            
                            texto1.setTexto(parteEntera);
                            texto2.setTexto(parteDecimal);
                            texto3.setTexto(partePeriodica);
                        }
                        
                        this.manager.getStateManager().setTextoActivo(null);
                        this.manager.getStateManager().setPosicionCursor(-1);
        
                        
                    }catch(Exception ex){
                        throw new ImportTranslationEditorException("Error en la importación de un periódico (mixto o puro).",ex);
                    }
                }
            /* *******************************************************/
        
            /* Números enteros */
                else if (arbol.getClass().getName().equals(PathManager.getMathEditorClasses()+".IntNumber")) {
                    try{
                        IntNumber in = (IntNumber) arbol;
                        String num = String.valueOf(in.getNumber());
                        insertarTexto(num, cuadriculaPadre);
                    }catch(ImportTranslationEditorException ex){
                        throw new ImportTranslationEditorException(ex.getMessage(),ex);
                    }catch(Exception ex){
                        throw new ImportTranslationEditorException("Error en la importación de un número entero.",ex);
                    }
                }
            /* *******************************************************/
        
            /* Números reales */
                else if (arbol.getClass().getName().equals(PathManager.getMathEditorClasses()+".RealNumber")) {
                    try{
                        RealNumber in = (RealNumber) arbol;
                        String num = String.valueOf(in.getNumber());
                        insertarTexto(num, cuadriculaPadre);
                    }catch(Exception ex){
                        throw new ImportTranslationEditorException("Error en la importación de un número real.",ex);
                    }
                }
            /* *******************************************************/
        
            /* Variables */
                else if (arbol.getClass().getName().equals(PathManager.getMathEditorClasses()+".Variable")) {
                    try{
                        
                        Variable var = (Variable) arbol;
                        
                        if(var.getIsText()){
                            String v = String.valueOf(var.getVar());
                            insertarTexto(v, cuadriculaPadre);
                        }else{
                            if(var.getVar().equalsIgnoreCase("pi"))
                                new Pi(this.manager,cuadriculaPadre);
                            if(var.getVar().equalsIgnoreCase("eulerNumber"))
                                new EulerNumber(this.manager,cuadriculaPadre);
                            if(var.getVar().equalsIgnoreCase("infinity"))
                                new Infinity(this.manager,cuadriculaPadre);
                        }
                        
                    }catch(ImportTranslationEditorException ex){
                        ex.printStackTrace();
                        throw new ImportTranslationEditorException(ex.getMessage(),ex);
                    }catch(Exception ex){
                        ex.printStackTrace();
                        throw new ImportTranslationEditorException("Error en la importación de un a variable.",ex);
                    }
                }
            /* *******************************************************/
                
            /* Funciones */
                else if (arbol.getClass().getName().equals(PathManager.getMathEditorClasses()+".Function")) {
                    try{
                        Function fun = (Function) arbol;
                        insertarTexto(fun.getName()+"(",cuadriculaPadre);
                        
                        for(int i=0;i<fun.getChildList().size();i++){
                            loadMathEditor(fun.getChild(i),cuadriculaPadre);
                            if(i<fun.getChildList().size()-1)
                                insertarTexto(",",cuadriculaPadre);
                        }
                        
                        insertarTexto(")",cuadriculaPadre);
                        
                    }catch(ImportTranslationEditorException ex){
                        throw new ImportTranslationEditorException(ex.getMessage(),ex);
                    }catch(Exception ex){
                        throw new ImportTranslationEditorException("Error en la importación de una función.",ex);
                    }
                }
            /* *******************************************************/
        
                
                
            cuadriculaPadre.pack();
        }
        
        if (this.manager.getStateManager().getTextoActivo() != null)
            this.manager.getStateManager().getTextoActivo().setPosicionCursor(this.manager.getStateManager().getPosicionCursor());
    }

    /**
     * Si existe un Texto activo, se inserta el contenido en él, en caso contrario, se crea un Texto con el contenido pasado por parámetro.
     * @param contenido Cadena que se inserta en un Texto.
     */
    @objid ("f847caa6-e81f-4823-b122-d4b80713fa9c")
    public void insertarTexto(String nuevoValor, GridExpression cuadriculaPadre) throws ImportTranslationEditorException {
        try{
            if (nuevoValor == null)
                nuevoValor = "";    
            
            if (this.manager.getStateManager().getTextoActivo() != null) {
                SimpleOperator texto = this.manager.getStateManager().getTextoActivo();
                /*
                 * El siguiente fragmento de código elimina el símbolo de la multiplicación 
                 * (el que el atributo name=Multiplicator en el fichero AllowedOperators.xml) 
                 * en los siguientes casos:
                 * 
                 * x·y -> xy
                 * 5·x -> 5x
                 * 1/2 ·x -> 1/2 x
                 * 
                 */
                    if(this.manager.getAllowedOperatorsAndFunctions().existsVariable(nuevoValor)){
                        
                        if(this.manager.getStateManager().getPosicionCursor() >= 2){
                            
                            //Caso 1: si el anterior y posterior sean variables y el operador un multiplicador, se elimina el multiplicador
                            
                            if(isMultiplication((texto.getTexto().substring(this.manager.getStateManager().getPosicionCursor()-1)))){
                                String text = texto.getTexto().substring(this.manager.getStateManager().getPosicionCursor()-2,this.manager.getStateManager().getPosicionCursor()-1);
                                if(this.manager.getAllowedOperatorsAndFunctions().existsVariable(text) || isNumeric(text)){
                                    
                                    String t = texto.getTexto().substring(0, texto.getTexto().length()-1);
                                    int posCursor = this.manager.getStateManager().getPosicionCursor();
                                    texto.setTexto(t);
                                    this.manager.getStateManager().setPosicionCursor(posCursor-1);
                                    //System.out.println(nuevoValor + " | " + texto.getTexto() + " | " + this.manager.getStateManager().getPosicionCursor());
                                }
                            }
                        }
                        else if(this.manager.getStateManager().getPosicionCursor() == 1){
                            
                            //Caso 2: Se trata de un operador complejo anterior a la variable:  1/2 ·x
                        
                            if(isMultiplication((texto.getTexto().substring(this.manager.getStateManager().getPosicionCursor()-1)))){
                                //String t = texto.getTexto().substring(0, texto.getTexto().length()-1);
                                //int posCursor = this.manager.getStateManager().getPosicionCursor();
                                //texto.setTexto(t);
                                //this.manager.getStateManager().setPosicionCursor(posCursor-1);
                            }
                        }
                    }
                /* ****************************************************************************************************************/                    
                    
                String string = "";
                int posCursor;
                
                // inserta el texto según donde se encuentre la posición del cursor
                if (texto.getNumCaracteres() == this.manager.getStateManager().getPosicionCursor()){                
                    string = texto.getTexto() + nuevoValor;
                }else {
                    string = texto.getTexto().substring(0, this.manager.getStateManager().getPosicionCursor());
                    string = string + nuevoValor;
                    string = string + texto.getTexto().substring(this.manager.getStateManager().getPosicionCursor(), texto.getNumCaracteres());
                }
                
                posCursor = this.manager.getStateManager().getPosicionCursor() + nuevoValor.length();
                texto.setTexto(string);
                this.manager.getStateManager().setPosicionCursor(posCursor);
                this.manager.getStateManager().setTextoActivo(texto);
                texto.setPosicionCursor(posCursor);
            }
            else {
                SimpleOperator texto = new SimpleOperator(this.manager, cuadriculaPadre, FormatTextBox.TODO, nuevoValor);
                this.manager.getStateManager().setTextoActivo(texto);
                int posCursor = nuevoValor.length();
                this.manager.getStateManager().setPosicionCursor(posCursor);
                texto.setPosicionCursor(posCursor);
                
            }
            
        }catch(Exception ex){
            //ex.printStackTrace();
            throw new ImportTranslationEditorException("Error en la creación de un Texto.",ex);
        }
    }

    /**
     * Si la cadena pasada por parámetro está reconocida como un multiplicador, devuelve true.
     * @param s Cadena que será comparada con un multiplicador.
     * @return Devuelve true si es un multiplicador, false en caso contrario.
     */
    @objid ("806644d7-9b7f-4039-9989-9dc48db31cfe")
    private boolean isMultiplication(String s) {
        List<BaseOperator> l = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName("multiplication");
        Iterator<BaseOperator> ite = l.iterator();
        Boolean encontrado = false;
        
        while(ite.hasNext() && !encontrado){
            BaseOperator ao = ite.next();
            if(s.equals(ao.getSymbolEditor()))
                encontrado = true;
        }
        return encontrado;
    }

    /**
     * Devuelve true si la cadena pasada por parámetro es un valor numérico.
     * @param s Cadena que va a ser testeada.
     * @return True si es valor numérico, false en caso contrario.
     */
    @objid ("6a51e43a-5713-4379-8e89-5277abc2c166")
    private boolean isNumeric(String s) {
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) < '0' || s.charAt(i)> '9' )
                return false;
        }
        return true;
    }

}
