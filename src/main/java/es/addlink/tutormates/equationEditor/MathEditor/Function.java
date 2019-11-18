package es.addlink.tutormates.equationEditor.MathEditor;

import java.util.List;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("742ddc76-7c72-47cf-a807-983c6a53189f")
public class Function extends MathEditor {
    @objid ("d245967c-2810-4f89-8f84-95b9d5ad16c0")
    private List<MathEditor> childList = null;

    @objid ("844815eb-8609-4a14-afa7-bd1b0d15f4c9")
    public Function(String name, int id, MathEditor parent) {
        super("functions", name, id, parent);
        this.childList = new Vector<MathEditor>();
        // TODO Auto-generated constructor stub
    }

    @objid ("632342ac-1cdc-4cfc-b71d-4c123d70ffd1")
    public int numChilds() {
        return this.childList.size();
    }

    @objid ("70ba599e-7726-4ece-9e3f-426753d38872")
    public MathEditor getChild(int n) {
        try{
            return this.childList.get(n);
        }catch(Exception e){
            return null;
        }
    }

    @objid ("586b8084-017f-4670-b306-45f4777b5d05")
    public List<MathEditor> getChildList() {
        return this.childList;
    }

    @objid ("fb1ff502-9f52-46b2-9780-9ccc476308a4")
    public void addChild(MathEditor me) {
        this.childList.add(me);
    }

    @objid ("07b89950-3f63-4dcf-817e-f7bdb3f1c2c8")
    public String toString() {
        String cad = "<function name=" + super.getName() + " id=" + super.getID() + ">";
        int i=0;
        
        while(i<this.childList.size()){
            cad += "\n<child>\n";
            cad += this.childList.get(i) + "\n";
            i++;
            cad += "</child>";
        }
        
        cad += "\n</function>\n";
        return cad;
    }

}
