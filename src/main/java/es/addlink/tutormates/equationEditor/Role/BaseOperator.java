package es.addlink.tutormates.equationEditor.Role;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Formats.MathML.MathML;

@objid ("ca0cf0c4-f53e-4aa8-ad01-fc10bbc80c9f")
public class BaseOperator {
    @objid ("d7c9302b-d575-4989-b16e-f661b2fdc070")
    private int id;

    @objid ("b007f607-5548-4f67-817c-a9a4255123d7")
    private Boolean isText;

    @objid ("8b2a52c0-24dd-4495-be28-0be2ab182291")
    private String name;

    @objid ("c6eb18bb-006f-4801-ad32-9d79f1624da3")
    private String symbolEditor;

    @objid ("43687eb3-7134-4712-bbe8-4337f52888dd")
    private String symbolMathML;

    @objid ("32a295e9-83e9-421a-8af8-20354e6d8563")
    private String type;

    @objid ("30e4c372-f40d-4346-a6dd-46a407b12235")
    private String position;

    @objid ("3c231670-4f53-4750-a112-109acddd18f3")
    private int priority;

    @objid ("c0be9331-9586-4603-95bc-a2fd3a56de97")
    private MathML mathML;

    @objid ("97a38f58-34d7-442b-8e80-4ed86752b6a6")
    public BaseOperator(int id, Boolean isText, String name, MathML mathML, String symbolEditor, String symbolMathML, String type, String position, int priority) {
        super();
        this.id = id;
        this.isText = isText;
        this.name = name;
        this.mathML = mathML;
        this.symbolEditor = symbolEditor;
        this.symbolMathML = symbolMathML;
        this.type = type;
        this.position = position;
        this.priority = priority;
    }

    @objid ("4f02a5d9-1243-4415-8ec9-84c7c0717c4b")
    public void setSymbolMathML(String symbolMathML) {
        this.symbolMathML = symbolMathML;
    }

    @objid ("028d866f-cce0-41e0-8258-51d0910af3cb")
    public String getType() {
        return type;
    }

    @objid ("ec20ffef-107e-4069-bed9-be1b1363e548")
    public String getPosition() {
        return position;
    }

    @objid ("6ac53c99-9ad4-49a3-9297-91133370d2fe")
    public int getPriority() {
        return priority;
    }

    @objid ("7422c37e-0cef-4156-a5fc-949274205a17")
    public int getId() {
        return id;
    }

    @objid ("fe7bede5-0a10-4199-9115-870780613d9e")
    public Boolean isText() {
        return isText;
    }

    @objid ("b33c1f96-50a4-4509-8056-3669054f4f5a")
    public String getName() {
        return name;
    }

    @objid ("807b8c91-ddc2-405f-8dd5-821331f97185")
    public MathML getMathML() {
        return mathML;
    }

    @objid ("8d479a0c-93d5-4b12-83ea-6b865a690466")
    public String getSymbolEditor() {
        return symbolEditor;
    }

    @objid ("62310b2b-49ce-489f-84c0-13f1d7669651")
    public String getSymbolMathML() {
        return symbolMathML;
    }

    @objid ("e0d7cdfe-4ccd-45cd-87bb-447b2c90a40d")
    public String toString() {
        String output;
        output = id + "|" + type + "|" + name + "|" + symbolEditor + "|" + mathML + "|" + isText + "|";
        return output;
    }

}
