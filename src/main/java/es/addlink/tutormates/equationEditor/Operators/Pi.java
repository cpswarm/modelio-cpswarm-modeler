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

@objid ("761fbd2e-0e06-4165-947c-ffbfbc69e049")
public class Pi extends WithoutEntriesOperator {
    @objid ("9900467d-3848-4389-9dca-0fea625210f5")
    private Label piSymbol;

    @objid ("f7c079ab-c926-42ad-a978-69e88791f41e")
    public Pi(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "pi", 167);
        // TODO Auto-generated constructor stub
        
        GridLayout grid = new GridLayout(1,true);
        grid.marginRight = -4;
        grid.marginLeft = -4;
        grid.marginBottom = 0;
        grid.marginTop = 2;
        
        super.getCmpTodo().setLayout(grid);
        this.piSymbol = new Label(super.getCmpTodo(), SWT.NONE);
        this.piSymbol.setBackground(this.getColorFondo());
        super.setSymbol(piSymbol);
        Image img = SWTResourceManager.getImage(GUIEditor.class, "images/operators/pi.png");
        this.piSymbol.setImage(img);
        super.getCmpTodo().pack();
        super.getCmpTodo().setLocation(0, 1);
        setMenuEliminar();
        super.setAltura(0);
    }

    @objid ("29605f7f-2485-4340-9809-544147b4471c")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        // TODO Auto-generated method stub
        return 10;
    }

}
