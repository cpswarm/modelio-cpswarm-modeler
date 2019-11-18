package es.addlink.tutormates.equationEditor.MathEditor;

import java.util.List;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("ee12b612-5d48-4844-8e68-aa13580880fd")
public class SequenceList extends MathEditor {
    @objid ("fa6dcd5f-b220-4bf4-bc86-2f087c85d039")
    private String separator;

    @objid ("7926740c-0503-493c-8a86-16024652329d")
    private List<MathEditor> list;

    @objid ("afacc7c7-2ffd-4e4b-94b1-c8ed2df05d68")
    public SequenceList(MathEditor parent, String separator) {
        super("list", "sequenceList", -1, parent);
        // TODO Auto-generated constructor stub
        
        this.list = new Vector<MathEditor>();
        this.separator = separator;
    }

    @objid ("e12e2bcd-5d18-4ac7-9e1b-8396027c4db6")
    @Override
    public String toString() {
        String cad = "<lista name=" + super.getName() + " id=" + super.getID() + ">";
        int i=0;
        
        while(i<this.list.size()){
            cad += "\n<child>\n";
            cad += this.list.get(i) + "\n";
            i++;
            cad += "</child>";
        }
        
        cad += "\n</lista>\n";
        return cad;
    }

    @objid ("bcb2bba7-0a61-42c4-ab27-642b6cb5c9ad")
    public int getNumItems() {
        return this.list.size();
    }

    @objid ("8857e56c-8621-4cfc-a4e7-58d60d18720c")
    public MathEditor getItem(int i) {
        if(i < getNumItems()) return this.list.get(i);
        else return null;
    }

    @objid ("59283df4-c432-4f42-a157-9011cff591a9")
    public void addMathEditor(MathEditor mathEditor) {
        this.list.add(mathEditor);
    }

    @objid ("919f04b9-67a9-4a63-a295-45e2be1cab19")
    public String getSeparator() {
        return separator;
    }

}
