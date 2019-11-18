package org.modelio.module.cpswarm.ui.composite;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * An abstract implementation of IField composed of:
 * <ul>
 * <li>a Label</li>
 * <li>an edition control</li>
 * </ul>
 * <br/>
 * The composite field controls are layouted as follows:<br/>
 * [--- label ---] [-------- edition control -------- ] <br/>
 * [----------- help text ------------]<br/>
 * 
 * where the help text if folded/unfolded by the help button. The class is in charge of creating the different widgets and of their
 * layout. the creation of the edition control is delegated to concrete sub-classes.
 */
@objid ("0fa1523a-a661-4fab-a17c-3f61fa4595da")
public abstract class AbstractField implements IField {
    @objid ("fd037225-676f-4f75-a4b0-db45c3be0866")
    private static final String EMPTY_STRING = "";

    @objid ("4bf3d793-dbef-43fd-b9d0-b7b0e6cae1fc")
    private final FormToolkit toolkit;

    @objid ("39d9bb49-e13a-4cfe-add1-536bd34d9d05")
    private String value;

    @objid ("d293a675-d39a-44b7-af87-0284fd1539d9")
    private Label label;

    @objid ("e47102f7-8d71-44a4-8b96-5c9ef8d33bb4")
    private Control control;

    @objid ("2709895c-a29d-4669-9357-5287167a21f5")
    private Composite fieldComposite;

    @objid ("edfa56e5-feec-46cc-805e-7bf48ce107a4")
    private final Composite parent;

    @objid ("dbc264bc-34af-448e-bce2-1088991aeb46")
    public abstract Control createControl(FormToolkit toolkit, Composite p);

    @objid ("c9029a77-49f4-4268-b63b-9f8405aeff89")
    public AbstractField(FormToolkit toolkit, Composite parent, String value) {
        this.toolkit = toolkit;
        this.parent = parent;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("39dfc05e-34cf-4458-9047-921f460ee5b9")
    @Override
    public void layout(Label lbl, Control ctrl) {
        // The label
        FormData formData = new FormData();
        formData.top = new FormAttachment(this.control, 0, SWT.TOP);
        formData.bottom = new FormAttachment(this.control, 0, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        formData.right = new FormAttachment(20);
        lbl.setLayoutData(formData);
                
        // The Control
        formData = new FormData();
        formData.top = new FormAttachment(0, 0);
        formData.bottom = new FormAttachment(this.label, 0, SWT.BOTTOM);
        formData.left = new FormAttachment(this.label, 10);
        formData.right = new FormAttachment(100);
        ctrl.setLayoutData(formData);
    }

    /**
     * {@inheritDoc}
     */
    @objid ("d323d052-b6cf-4015-886b-87e367745beb")
    @Override
    public final Composite getComposite() {
        // Ensure widgets exist.
        if (this.fieldComposite == null) {
            buildGui();
            assert (this.fieldComposite != null);
        }
        return this.fieldComposite;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("525d1d77-6bf5-4f7f-a0de-92d9f935a0ae")
    @Override
    public Control getControl() {
        if (this.control == null) {
            buildGui();
            assert (this.control != null);
        }
        return this.control;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("f21b756a-ee78-43c9-a582-d9b5debdc1a6")
    @Override
    public String getValue() {
        return this.value;
    }

    @objid ("6576ff4a-0519-4d92-931d-8841bc8d09ef")
    public final Label getLabel() {
        return this.label;
    }

    @objid ("f6c1720c-26b5-4bd7-869d-12ad9195a8c2")
    private void buildGui() {
        // The composite field is build on a Composite using a FormLayout
        this.fieldComposite = this.toolkit.createComposite(this.parent);
        this.fieldComposite.setBackground(this.parent.getBackground());
        final FormLayout l = new FormLayout();
        this.fieldComposite.setLayout(l);
        
        // Create the composite field controls
        
        this.label = this.toolkit.createLabel(this.fieldComposite, EMPTY_STRING);
        this.label.setBackground(this.parent.getBackground());
             
        
        // Create the control
        this.control = createControl(this.toolkit, this.fieldComposite);
        if (this.control == null) {
            throw new NullPointerException(String.format("%s.createControl(...) returned null", this));
        }
        
        // Layout the composite field controls
        layout(this.label, this.control);
        
        assert (this.label.getLayoutData() instanceof FormData);
        assert (this.control.getLayoutData() instanceof FormData);
    }

    @objid ("a2893ffc-0ac4-47c1-a591-0fd76f084a0b")
    public AbstractField(FormToolkit toolkit, Composite parent) {
        this.toolkit = toolkit;
        this.parent = parent;
        this.value = EMPTY_STRING;
    }

    @objid ("45ad705d-fb7a-4a34-9299-f0bc72140063")
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @objid ("410570f9-11fa-47f2-a6c1-f21f0c72828f")
    @Override
    public void setEditable(boolean onoff) {
        if (this.control != null) {
            this.control.setEnabled(onoff);
        }
        refresh();
    }

}
