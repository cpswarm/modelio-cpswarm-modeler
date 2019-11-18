package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

@objid ("fd57c8e9-fa82-43a5-8a14-d23c3b4a45b9")
public class Infinity extends WithoutEntriesOperator {
    @objid ("6b00cb15-a5df-452f-8118-f08110df028e")
    private Label infinitySymbol;

    @objid ("58a4dd63-f41c-4acc-bdd1-94222c896c0c")
    public Infinity(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "infinity", 169);
        // TODO Auto-generated constructor stub
        
        GridLayout grid = new GridLayout(1,true);
        grid.marginRight = -4;
        grid.marginLeft = -4;
        grid.marginBottom = 0;
        grid.marginTop = 2;
        
        super.getCmpTodo().setLayout(grid);
        this.infinitySymbol = new Label(super.getCmpTodo(), SWT.NONE);
        this.infinitySymbol.setBackground(this.getColorFondo());
        super.setSymbol(infinitySymbol);
        Image img = SWTResourceManager.getImage(GUIEditor.class, "images/operators/infinity.png");
        this.infinitySymbol.setImage(img);
        super.getCmpTodo().pack();
        super.getCmpTodo().setLocation(0, 1);
        setMenuEliminar();
        super.setAltura(0);
    }

    @objid ("e4cdb393-684d-41a7-897e-a885aa38c6c7")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        // TODO Auto-generated method stub
        return 10;
    }

}
