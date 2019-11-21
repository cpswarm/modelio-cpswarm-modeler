package org.modelio.module.cpswarm.ui.composite;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.module.cpswarm.utils.ResourcesManager;

/**
 * This class defines the file chooser composite.
 * This composite is composed of
 * - a text field in order to specify the desired file
 * - a file browser button for allowing file browsing
 * 
 * It is a SWT composite
 * @author ebrosse
 */
@objid ("4fb251aa-8aa2-4ef2-a4f0-104d6536d90d")
public class FileChooserComposite extends Composite {
    @objid ("ebba1f1e-695f-4916-badb-e96ac1d8f35b")
    private File currentFile = null;

    @objid ("32959919-0cd0-40d4-8f3a-a4de429d23c7")
    private Text text = null;

    @objid ("80afaa15-1ad5-4f62-b356-96eb4578ec70")
    private Button searchButton = null;

    @objid ("6e3b10cf-2216-4b6b-9b2a-bd8ebeb8506c")
    protected FileDialog dialog = null;

    /**
     * This method returns the chosen file
     * @return the path of the chosen file
     */
    @objid ("5a81312d-b468-431f-a3e3-d729280a79c4")
    public File getCurrentFile() {
        String nomFichier = this.text.getText();
        if ((nomFichier != null) && (nomFichier.length() != 0)) {
            this.currentFile = new File(nomFichier);
        } else {
            this.currentFile = null;
        }
        return this.currentFile;
    }

    /**
     * This method allows to set the chosen file
     * @param currentFile : the chosen file
     */
    @objid ("34f4b366-450c-42c4-945e-4b5c3c0b4165")
    public void setCurrentFile(final File currentFile) {
        this.currentFile = currentFile;
    }

    /**
     * This method sets the label of the composite
     * @param label : the label of the composite
     */
    @objid ("5c3d048f-2049-4d54-b5e2-eb8c3afe4b1d")
    public void setText(final String label) {
        if (label != null)
            this.text.setText(label);
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
    @objid ("953cfb64-2c67-4567-9275-71646d8e0187")
    public FileChooserComposite(final Composite parent, final int style, final int typeSelection) {
        super(parent, style);
        setLayout(new FormLayout());
        this.dialog = new FileDialog((Shell) parent, typeSelection);
        
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        this.setLayout(gridLayout);
        this.text = new Text(this, SWT.BORDER);
        this.text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        this.text.setEnabled(true);
        this.searchButton = new Button(this, SWT.NONE);
        File file = new File(ResourcesManager.getInstance().getImage("directory.png"));  
        Image directory = new Image(Display.getDefault(), file.getAbsolutePath());
        this.searchButton.setImage(directory);
    }

    /**
     * This method launch the folder browsing and returns the path of the chosen file
     * @return the path of the chosen file
     */
    @objid ("116a5759-3df1-4c8b-bd9e-e521507f07e6")
    public String searchFile() {
        String nomFichier = this.dialog.open();
        if ((nomFichier != null) && (nomFichier.length() != 0)) {
            this.currentFile = new File(nomFichier);
            this.text.setText(nomFichier);
        }
        return this.text.getText();
    }

    /**
     * This method returns the search button of the composite
     * @return the search button
     */
    @objid ("97592105-6aa5-4309-9255-2224148c5b95")
    public Button getSearch() {
        return this.searchButton;
    }

    /**
     * This methods returns the text available in the SWT FileDialog
     * @return the text of the FileDialog
     */
    @objid ("d03ac21a-47cb-44d2-820d-6136f84aa408")
    public String getText() {
        return this.text.getText();
    }

    /**
     * This method returns the SWT Text owned by the FileChooserComposite
     * @return the owned SWT TEXT
     */
    @objid ("477ed32e-345e-478d-bf2d-285fddcaf06e")
    public Text getTextButton() {
        return this.text;
    }

    /**
     * This method returns the SWT FileDialog created inside the FileChooserComposite
     * @return the owned FileDialog
     */
    @objid ("7f26b6a4-7bc5-41d9-97b7-99165ce85cd5")
    public FileDialog getDialog() {
        return this.dialog;
    }

}
