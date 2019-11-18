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

@objid ("5f07b1a1-09b1-466c-864c-1ea76d0965a9")
public class EulerNumber extends WithoutEntriesOperator {
    @objid ("96416c51-9542-4c1c-ae15-ef6f12a25764")
    private Label eulerNumberSymbol;

    @objid ("a5d929f9-b011-477c-a11a-6606fcf99c6a")
    public EulerNumber(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
        super(manager, cuadriculaPadre, "eulerNumber", 168);
        // TODO Auto-generated constructor stub
        
        GridLayout grid = new GridLayout(1,true);
        grid.marginRight = -4;
        grid.marginLeft = -4;
        grid.marginBottom = 0;
        grid.marginTop = 2;
        
        super.getCmpTodo().setLayout(grid);
        this.eulerNumberSymbol = new Label(super.getCmpTodo(), SWT.NONE);
        this.eulerNumberSymbol.setBackground(this.getColorFondo());
        super.setSymbol(eulerNumberSymbol);
        Image img = SWTResourceManager.getImage(GUIEditor.class, "images/operators/eulerNumber.png");
        this.eulerNumberSymbol.setImage(img);
        super.getCmpTodo().pack();
        super.getCmpTodo().setLocation(0, 1);
        setMenuEliminar();
        super.setAltura(0);
    }

    @objid ("0f3bd48d-76ec-4f6d-b49f-92aad26329e3")
    @Override
    public int getPosicionCentral() throws ComponentEditorException {
        // TODO Auto-generated method stub
        return 10;
    }

}
