package es.addlink.tutormates.equationEditor.MathEditor;

import java.math.BigDecimal;
import java.math.BigInteger;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * árbol genérico, el único con el que es capaz de trabajar DIRECTAMENTE el editor.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
@objid ("49fe6a5d-01ce-4d62-8fb5-6815c2d69bd1")
public abstract class MathEditor {
    /**
     * Identificador del componente.
     */
    @objid ("0549365b-8c78-4bde-b4bd-a6d94550fd38")
    private int id;

    /**
     * Nombre del componente.
     */
    @objid ("82274f17-4db6-43ef-b032-d1cfffcc87b2")
    private String name;

    /**
     * Grupo al que pertenece el componente. Grupos: unario, binario, especial, numero.
     */
    @objid ("42085d0b-51e2-4989-81d6-27d92dd61102")
    private String grupo; // posibles valores: binario, unario, especial, operacion, numero

    /**
     * Padre del objeto MathEditor.
     */
    @objid ("07ad3267-a0fe-4f2f-86c9-1b40fd256789")
    private MathEditor parent = null;

    /**
     * Constructor
     * @param grupo Grupo al que pertenece el componente. Grupos: unario, binario, especial, numero.
     * @param name Nombre del componente.
     * @param id Identificador del componente.
     * @param parent Padre del objeto MathEditor.
     */
    @objid ("8f9ba928-69fa-409e-a063-da9bed5a38d8")
    public MathEditor(String grupo, String name, int id, MathEditor parent) {
        this.grupo = grupo;
        this.name = name;
        this.id = id;
        this.parent = parent;
    }

    /**
     * Devuelve el nombre del grupo al que pertenece el componente.
     * @return Nombre del grupo al que pertenece el componente.
     */
    @objid ("b7c33e14-42af-4278-9b70-9e3c6d8c341c")
    public String getGrupo() {
        return grupo;
    }

    /**
     * Devuelve el nombre del componente.
     * @return Nombre del componente.
     */
    @objid ("8fac5911-82db-4522-9adc-0bea7631eb91")
    public String getName() {
        return name;
    }

    /**
     * Devuelve el identificador del componente.
     * @return Identificador del componente.
     */
    @objid ("32dc1fa4-e3ab-4f04-8b12-50278f7a2343")
    public int getID() {
        return id;
    }

    /**
     * Devuelve el padre del objeto MathEditor.
     * @return Padre del objeto MathEditor.
     */
    @objid ("d85c92f9-a77b-40c3-83d5-1a46cb8f6240")
    public MathEditor getParent() {
        return parent;
    }

    /**
     * Establece el padre del objeto MathEditor.
     * @param newParent Nuevo padre para el objeto MathEditor.
     */
    @objid ("19d4ee30-40b7-4152-ba07-4c27b51249f4")
    public void setParent(MathEditor newParent) {
        parent = newParent;
    }

    /**
     * Devuelve el MathEditor en formato String.
     * @return MathEditor en formato String.
     */
    @objid ("d2e3401c-babd-44c4-a24a-3445cfd257b6")
    public abstract String toString();

    /**
     * Devuelve true si el MathEditor pasado por parámetro está bien formado, false en caso contrario.
     * @param object Objeto de tipo MathEditor para determinar si está bien formado.
     * @return True si el MathEditor pasado por parámetro está bien formado, false en caso contrario.
     */
    @objid ("b1e7a053-72e0-4787-a3f2-e32d0eb76d58")
    public static boolean isCorrect(MathEditor object) {
        try {
            if (object == null) {
                return false;
            }
            else {
                
                if (object.getGrupo() == "binario") {
                    Binary bin = (Binary) object;
        
                    boolean res;
                    if ((bin == null) || (bin.getLeftChild() == null) || (bin.getRightChild() == null))
                        return false;
                    else {
                        res = isCorrect(bin.getLeftChild());
                        if (res)
                            res = isCorrect(bin.getRightChild());
                        if (res)
                            return true;
                        else
                            return false;
                    }
                }
                else if (object.getGrupo() == "unario") {
                    Unary una = (Unary) object;
                    if ((una == null) || (una.getChild() == null))
                        return false;
                    else
                        return isCorrect(una.getChild());
                }
                else if(object.getName().equalsIgnoreCase("repeatingDecimal")){
                    RepeatingDecimal rd = (RepeatingDecimal)object;
                    if(rd.getFirstChild() == null  || rd.getThirdChild() == null)
                        return false;
                    
                    try{
                        ((IntNumber)rd.getFirstChild()).getNumber();
                    }catch(Exception ex){
                        return false;
                    }
        
                    if(rd.getSecondChild() != null && ((StringNumber)rd.getSecondChild()).getText().trim().equalsIgnoreCase(""))
                        return false;
                    
                    String third = ((StringNumber)rd.getThirdChild()).getText().trim();
                    if(third.equalsIgnoreCase(""))
                        return false;
                    else{
                        try{
                            Integer.parseInt(third);
                        }catch(Exception ex){
                            return false;
                        }
                    }
                    
                }
        
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    @objid ("25e4a788-dca8-461a-abae-52e8dea8ef8c")
    public static MathEditor getValue(String value) {
        MathEditor mathEditor = null;
        
        if(esEntero(value)){
            BigInteger num = new BigInteger(value);
            mathEditor = new IntNumber(num,null);
        }
        else if(esReal(value)){
            BigDecimal num = new BigDecimal(value);
            mathEditor = new RealNumber(num,null);
        }
        return mathEditor;
    }

    /**
     * Devuelve true si el valor es entero, false en caso contrario.
     * @param num Valor que se quiere estudiar.
     * @return True si el valor es entero, false en caso contrario.
     */
    @objid ("830abc95-a2c0-45ed-8e9b-83df52f1d8b0")
    private static boolean esEntero(String num) {
        try{
            
            //Decide que un número del tipo 8.0 no es entero
            String[] str = num.split("\\.");            
            if(str.length > 1){
                if(str[str.length-1].equalsIgnoreCase("0"))
                    return false;
            }
            
            
            double d = Double.parseDouble(num);
            if ((d % 1) == 0)    return true;
            else return false;
        }catch(NumberFormatException ex){
            return false;
        }
    }

    /**
     * Devuelve true si el valor es un número real, false en caso contrario.
     * @param num Valor que se quiere estudiar.
     * @return True si el valor es un número real, false en caso contrario.
     */
    @objid ("9e36d1cb-3792-46dd-b425-64631a340522")
    private static boolean esReal(String num) {
        boolean esReal;
        if (num.length() == 0) esReal = false;
        else esReal = true;
        
        try { Double.parseDouble(num);}
        catch (NumberFormatException e) { esReal = false;}
        return esReal;
    }

    /**
     * Devuelve true si los dos MathEditor pasados por parámetros son iguales, false en caso contrario.
     * @param obj1 Primer objeto de tipo MathEditor para comparar.
     * @param obj2 Segundo objeto de tipo MathEditor para comparar.
     * @return True si los dos MathEditor pasados por parámetros son iguales, false en caso contrario.
     */
    @objid ("c9bffd42-7b29-470f-9e83-49f065112a81")
    public static boolean equals(MathEditor obj1, MathEditor obj2) {
        try {
            if (obj1 == null || obj2 == null) {
                return false;
            }
            else {
                if (obj1.getName().equals(obj2.getName())) {
                    if (obj1.getGrupo().equals("binario")) {
                        Binary bin1 = (Binary) obj1;
                        Binary bin2 = (Binary) obj2;
                        return (equals(bin1.getLeftChild(), bin2.getLeftChild()) && equals(bin1.getRightChild(), bin2.getRightChild()));
                    }
                    if (obj1.getGrupo().equals("unario")) {
                        Unary una1 = (Unary) obj1;
                        Unary una2 = (Unary) obj2;
                        return (equals(una1.getChild(), una2.getChild()));
                    }
                    if (obj1.getGrupo().equals("especial")) {
                        RepeatingDecimal per1 = (RepeatingDecimal) obj1;
                        RepeatingDecimal per2 = (RepeatingDecimal) obj2;
                        return (equals(per1.getFirstChild(), per2.getFirstChild()) && equals(per1.getSecondChild(), per2.getSecondChild()) && equals(per1.getThirdChild(), per2.getThirdChild()));
                    }
                    if (obj1.getName().equals("entero")) {
                        IntNumber int1 = (IntNumber) obj1;
                        IntNumber int2 = (IntNumber) obj2;
                        return (int1.getNumber() == int2.getNumber());
                    }
                    if (obj1.getName().equals("real")) {
                        RealNumber real1 = (RealNumber) obj1;
                        RealNumber real2 = (RealNumber) obj2;
                        return (real1.getNumber() == real2.getNumber());
                    }
                    if (obj1.getName().equals("variable")) {
                        Variable var1 = (Variable) obj1;
                        Variable var2 = (Variable) obj2;
                        return (var1.getVar() == var2.getVar());
                    }
                    if (obj1.getGrupo().equals("")) { // Se trata de un TextoObject
                        StringNumber txt1 = (StringNumber) obj1;
                        StringNumber txt2 = (StringNumber) obj2;
                        return (txt1.getText().equals(txt2.getText()));
                    }
                    return false;
                }
                else {
                    return false;
                }
            }
        } catch (Exception ex) {
            return false;
        }
    }

}
