package org.modelio.module.cpswarm.ui.composite;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This class defines the file chooser composite.
 * This composite is composed of
 * - a text field in order to specify the desired file
 * 
 * It is a SWT composite
 * @author ebrosse
 */
@objid ("5aae06a2-166f-45e8-bed0-1edfd4e0a26c")
public class StringComposite extends Composite {
    @objid ("14a1a458-342f-422b-adbb-521a3b47824d")
    private Label label = null;

    @objid ("dc8d663a-8c2a-4bcb-8523-e1c45758db8c")
    private Text text = null;

    /**
     * This method sets the label of the composite
     * @param label : the label of the composite
     */
    @objid ("f96b25ec-17f8-4170-bc65-b2d41fa2901c")
    public void setText(final String label) {
        if (label != null)
            this.text.setText(label);
    }

    @objid ("554cf188-0d41-4ca2-aed4-5549d32bd7bf")
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
    @objid ("71f05c21-ec15-4aec-8baf-a64ff31841f2")
    public StringComposite(final Composite parent, final int style, final int typeSelection) {
        super(parent, style);
        
        setLayout(new FormLayout());
        
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        this.setLayout(gridLayout);
        
        this.label = new Label(this, SWT.BORDER);
        
        this.text = new Text(this, SWT.None);
        //        this.text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        this.text.setEnabled(true);
    }

    /**
     * This methods returns the text available in the SWT FileDialog
     * @return the text of the FileDialog
     */
    @objid ("5dd07937-8395-41b2-92d0-641ddc4b86ed")
    public String getText() {
        return this.text.getText();
    }

    /**
     * This method returns the SWT Text owned by the FileChooserComposite
     * @return the owned SWT TEXT
     */
    @objid ("4bb2c4d9-d623-4857-8b0c-404727a1ab3c")
    public Text getTextButton() {
        return this.text;
    }

}
