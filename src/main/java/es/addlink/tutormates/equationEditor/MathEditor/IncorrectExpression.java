package es.addlink.tutormates.equationEditor.MathEditor;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("21c77d25-f9e2-4ded-a42c-b07aa2ce63da")
public class IncorrectExpression extends MathEditor {
    @objid ("11533b15-07ca-4665-b3e6-413dbe33073b")
    private List<MathEditor> list;

    @objid ("f2ce5ba2-6cf8-4098-bb9b-a00208464a40")
    public IncorrectExpression(MathEditor parent) {
        super("incorrectExpression", "incorrectExpression", -1, parent);
        // TODO Auto-generated constructor stub
        this.list = new Vector<MathEditor>();
    }

    @objid ("e5c02b39-f785-47a4-b60a-732e0fb3df9a")
    public void addMathEditor(MathEditor math) {
        this.list.add(math);
    }

    @objid ("f5a8c6b8-f112-46b4-a825-1b1a9f42b0c1")
    public List<MathEditor> getList() {
        return list;
    }

    @objid ("d6091999-105c-4a77-993f-0af376baddea")
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String str="*** incorrect expression ***\n";
        
        Iterator<MathEditor> ite = this.list.iterator();
        while(ite.hasNext()){
            MathEditor m = ite.next();
            str += "   > " + m + "\n";
        }
        
        str+="****************************";
        return str;
    }

}
