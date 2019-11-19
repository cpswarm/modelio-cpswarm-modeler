package org.modelio.module.cpswarm.ui.composite;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This class defines the file chooser composite.
 * This composite is composed of
 * - a text field in order to specify the desired file
 * 
 * It is a SWT composite
 * @author ebrosse
 */
@objid ("2be85c58-8819-455f-82de-387a8cf97e1f")
public class EnumerationComposite extends Composite {
    @objid ("57be13f0-7df2-4063-962d-efa39c89f954")
    private Label label = null;

    @objid ("4f7e2b6e-eeb3-4123-b96b-5ad49d43db2b")
    private Combo combo = null;

    /**
     * This method sets the label of the composite
     * @param label : the label of the composite
     */
    @objid ("755147a6-2923-437c-9f72-07a7df67d53b")
    public void setText(final String label) {
        if (label != null)
            this.combo.setText(label);
    }

    @objid ("8902ea62-a561-415f-844b-f7c1b8b05a93")
    public void setLabel(final String label) {
        if (label != null)
            this.label.setText(label);
    }

    /**
     * Constructor of the FileChooserComposite.
     * It needs :
     * - the parent composite
     * - its SWT style
     * - the selection type of the SWT FileDialog
     * @param parent : the SWT composite owner
     * @param style : the SWT style
     * @param typeSelection : the SWT selection type
     */
    @objid ("3084bd69-6ac4-46f8-b3ad-f0099079eb79")
    public EnumerationComposite(final Composite parent, final int style, final int typeSelection) {
        super(parent, style);
        setLayout(new FormLayout());
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        this.setLayout(gridLayout);
        this.label = new Label(this, SWT.BORDER);
        this.label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    }

    /**
     * This methods returns the text available in the SWT FileDialog
     * @return the text of the FileDialog
     */
    @objid ("7848a1ac-a5f7-4b6c-a823-150e1662c791")
    public String getComboValue() {
        return this.combo.getText();
    }

    /**
     * This method returns the SWT Text owned by the FileChooserComposite
     * @return the owned SWT TEXT
     */
    @objid ("fb662f8c-053e-4698-9ade-1f4d2aefe878")
    public Combo getComboButton() {
        return this.combo;
    }

}
