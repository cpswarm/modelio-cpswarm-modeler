package org.modelio.module.cpswarm.ui.window;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.ui.composite.FileChooserComposite;
import org.modelio.module.cpswarm.ui.composite.ValidationBoutonComposite;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * @author ebrosse
 */
@objid ("b25f33e8-1c31-474d-944c-7beccb360a15")
public abstract class AbstractSwtWizardWindow extends Dialog {
    @objid ("0002f374-2fa1-4922-82a5-5d2c31bccae7")
    private String title = "";

    @objid ("a7b888e4-d768-4cd6-a78c-36cd8e0ee172")
    private String description = "";

    @objid ("b211ee43-e43d-4ccc-9e69-230d63ce9be7")
    protected String frametitle = "";

    @objid ("96eb7364-392c-445e-8315-742d91fbc80e")
    protected String cancelButton = "";

    @objid ("7e3e6820-8a44-4d3e-8860-bf0071f37812")
    protected String validateButton = "";

    @objid ("acc0f573-e7b1-4aae-ad7d-f16eef36d01d")
    private static boolean cancelation = false;

    @objid ("2bf29c99-d7e7-402f-8f6e-20bb454935ef")
    protected boolean exportWindows = false;

    @objid ("06604fd7-7c4b-465c-8184-187113cd6ebf")
    protected boolean error = false;

    @objid ("029c8ef6-78eb-45fb-a77d-5dab52740da7")
    protected String path = "";

    @objid ("17cee687-152f-432e-9d7f-88a4326f0d44")
    protected ModelElement selectedElt = null;

    @objid ("cf24cd6e-6ad8-487e-9664-d49726d70758")
    protected Shell shell = null;

    @objid ("fad6fe2f-4c3f-4181-bd91-545c12108f09")
    protected FileChooserComposite fileChooserComposite = null;

    @objid ("9230ec97-84ae-4a41-89ae-01bbf349e13d")
    protected ValidationBoutonComposite validateComposite = null;

    /**
     * @return nothing
     */
    @objid ("8e9147ca-0f26-49bd-8a76-d5e851c8d81a")
    protected Object open() {
        setCancellation(false);
        Display display = getParent().getDisplay();
        centerOnPrimaryScreen(display);
        this.shell.open();
        this.shell.layout();
        
        while (!this.shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        return null;
    }

    /**
     * set default configuration of the dialog box
     */
    @objid ("66918e7a-ec99-4bc5-9134-b6d9c7c54128")
    public abstract void setDefaultDialog();

    /**
     * @return the selected element i.e. Package or IModule
     */
    @objid ("f8e80f41-82da-4ca4-9ffa-8af7df1efbfc")
    public MObject getSelectedElt() {
        return this.selectedElt;
    }

    @objid ("67d15ac4-3d19-4ff9-8e88-159c9dabcf7e")
    void cancelAction() {
        if ((this.shell != null) && (!this.shell.isDisposed())){
            this.shell.dispose();
        }
    }

    /**
     * @param cancelButton : the text of the cancel button
     */
    @objid ("bb27b218-5903-4741-9dbc-d86490f07043")
    public void setCancelButton(final String cancelButton) {
        this.cancelButton = cancelButton;
    }

    /**
     * @param description : the description of the windows
     */
    @objid ("84f2e05b-c41f-4ef1-8501-a365424b74fa")
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param frametitle : the title of the windows frame
     */
    @objid ("93a7b009-650b-44f4-a4dd-158305014622")
    public void setFrametitle(final String frametitle) {
        this.frametitle = frametitle;
    }

    /**
     * @param title : the title of the windows
     */
    @objid ("d882c9b7-41b4-4e72-b2bf-2873ac9d9a26")
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @param validateButton : the button of validation
     */
    @objid ("fb6f7974-723a-49b6-8817-f47772e18360")
    public void setValidateButton(final String validateButton) {
        this.validateButton = validateButton;
    }

    @objid ("d32c82cb-55a5-4cde-b4cd-7a0041e8a7f7")
    protected FileChooserComposite getFileChooserComposite() {
        return this.fileChooserComposite;
    }

    /**
     * Warning user that he does not select a file
     */
    @objid ("f5c9cbcb-d830-47eb-8152-621e42b57278")
    public void selectAFile() {
        MessageBox messageBox = new MessageBox(this.shell, SWT.ICON_WARNING);
        messageBox.setMessage(this.description);
        messageBox.open();
    }

    /**
     * @return true if the process is cancelled by the user
     */
    @objid ("3c7b3848-acfc-4edf-ba59-b868a5c09ec2")
    public static boolean isCancelation() {
        return AbstractSwtWizardWindow.cancelation;
    }

    /**
     * @param cancelation : set the cancellation of the process
     */
    @objid ("eca9ba63-7019-4f55-b6d2-383578345914")
    public static void setCancellation(final boolean cancelation) {
        AbstractSwtWizardWindow.cancelation = cancelation;
    }

    /**
     * the action i.e. import or export
     */
    @objid ("527ff5f3-94c5-4289-8096-d210c8d2a856")
    public abstract void validationAction();

    /**
     * set button labels
     */
    @objid ("22fb5ca3-1c55-4455-ba7b-ed43349ba201")
    public abstract void setLabels();

    /**
     * initialize file path
     */
    @objid ("337fdbac-a281-43b6-b5eb-adfce01b4f24")
    public abstract void setPath();

    /**
     * launch a dialog box for wrong path
     */
    @objid ("523cbbad-052d-4c88-8eb1-68c097ae304f")
    public void fileDontExist() {
        MessageBox messageBox = new MessageBox(this.shell, SWT.ICON_WARNING);
        messageBox.setMessage("Specified File do not exist");
        messageBox.open();
    }

    /**
     * @param parent : the shell parent
     * @param style : the swt style
     */
    @objid ("1f8c9050-ac89-47b8-b86e-5a16e5c54d09")
    public AbstractSwtWizardWindow(final Shell parent, final int style) {
        super(parent != null ? parent : new Shell(Display.getDefault()), style);
    }

    /**
     * @param parent : the shell parent
     */
    @objid ("bfb559f2-e979-47b5-865a-2603c9eb4a89")
    public AbstractSwtWizardWindow(final Shell parent) {
        this(parent, SWT.NONE);
    }

    /**
     * @param selectedElt : set the selected element
     */
    @objid ("b6d5061d-384d-463d-9ea2-8e55d7c08fba")
    public void setSelectedElt(final ModelElement selectedElt) {
        this.selectedElt = selectedElt;
    }

    @objid ("b4617ed9-e0be-42ef-ae8d-c24a50810c1a")
    private void centerOnPrimaryScreen(Display display) {
        Monitor primary = display.getPrimaryMonitor();
        Rectangle bounds = primary.getBounds();
        Rectangle rect = this.shell.getBounds();
        int x = bounds.x + (bounds.width - rect.width) / 2;
        int y = bounds.y + (bounds.height - rect.height) / 2;
        this.shell.setLocation(x, y);
        this.shell.open();
    }

    @objid ("afcabb78-61ea-4af2-8621-f7503df1b688")
    void setUMLExtension() {
        String filePath = this.fileChooserComposite.getText();
        int length = filePath.length();
        if ((length - 4) != filePath.lastIndexOf(".uml")) {
            if (filePath.lastIndexOf(".xmi") == (length - 4)) {
                filePath = filePath.substring(0, length - 4) + ".uml";
            } else {
                filePath = filePath + ".uml";
            }
            this.fileChooserComposite.setText(filePath);
        }
    }

    @objid ("21706ec8-e4d5-4a1e-8059-b5c3c3487161")
    void setXMIExtension() {
        String filePath = this.fileChooserComposite.getText();
        int length = filePath.length();
        if ((length - 4) != filePath.lastIndexOf(".xmi")) {
            if (filePath.lastIndexOf(".uml") == (length - 4)) {
                filePath = filePath.substring(0, length - 4) + ".xmi";
            } else {
                filePath = filePath + ".xmi";
            }
            this.fileChooserComposite.setText(filePath);
        }
    }

    /**
     * @param inpath : the initial path
     * @return the same path in a correct form
     */
    @objid ("f629750d-903f-4fe1-aa30-5ace5af59922")
    public static String checkAndReplaceEndPath(final String inpath) {
        if (inpath.endsWith("\\")) {
            return inpath.substring(0, inpath.lastIndexOf("\\"));
        } else if (inpath.endsWith("/")) {
            return inpath.substring(0, inpath.lastIndexOf("/"));
        }
        return inpath;
    }

    @objid ("360cd9f8-f80f-4d5e-a1b9-cdc271096548")
    protected void completeBox() {
        this.title = "Complete";
        
        this.description = "Complete";
        
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                customMessageBox(SWT.ICON_INFORMATION);
                AbstractSwtWizardWindow.this.shell.dispose();
            }
        });
    }

    @objid ("17a4fde8-6aa6-48cf-bff0-9810332b0db0")
    void customMessageBox(int icon) {
        MessageBox messageBox = new MessageBox(this.shell, icon);
        messageBox.setMessage(this.description);
        messageBox.setText(this.title);
        messageBox.open();
    }

}
