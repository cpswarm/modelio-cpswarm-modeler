package es.addlink.tutormates.equationEditor.Formats;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Categorías de los operadores.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("48e056bd-2324-4e0d-a812-27f7ee26ce74")
public class Category {
    @objid ("2e600723-26a6-434e-8bae-f95fb025025f")
    public static final String SimpleOperatorsGroup = "simpleOperators";

    @objid ("dac8e8a2-b911-4076-92f8-c8373d8fa939")
    public static final String ComplexOperatorsGroup = "complexOperators";

    @objid ("0ebe35b4-6354-4637-a7f4-f2adf35122a0")
    public static final String TextOperatorsGroup = "textOperators";

    @objid ("81ce3094-7ae8-4d59-a535-9c02bbe4bd12")
    public static final String VariablesGroup = "variables";

    @objid ("08a38232-4f48-4d19-9ba8-300a55d83f8c")
    public static final String WithoutEntriesType = "numWithoutEntries";

    @objid ("91eb24f6-bd8c-46b6-aac9-aada0efec341")
    public static final String TextType = "texto";

    @objid ("51e505ee-c2c0-421e-a335-84fee536fadd")
    public static final String UnaryType = "unary";

    @objid ("bf896cfb-81d2-4f88-8fb6-29a5171ec902")
    public static final String UnaryComplexType = "unaryComplex";

    @objid ("b1e19338-440e-4a9a-8c6c-b150bfbbc790")
    public static final String BinaryType = "binary";

    @objid ("2c384d1a-42bb-4d30-83ff-d03e04f8d007")
    public static final String TernaryType = "ternary";

}
