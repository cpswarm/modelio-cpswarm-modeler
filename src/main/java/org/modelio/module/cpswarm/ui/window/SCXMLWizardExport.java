package org.modelio.module.cpswarm.ui.window;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Shell;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.module.cpswarm.generator.SCXMLGeneration;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.ui.composite.FileChooserComposite;
import org.modelio.module.cpswarm.ui.composite.ValidationBoutonComposite;
import org.modelio.module.cpswarm.utils.FileUtils;
import org.modelio.module.cpswarm.utils.ResourcesManager;

/**
 * This class provides the SCXML export dialog
 * @author ebrosse
 */
@objid ("2039bbdc-9469-4297-b4e1-02091eeb5c10")
public class SCXMLWizardExport extends AbstractSwtWizardWindow {
    @objid ("54d3dcad-5e5c-493f-8237-6896b99d349b")
    public Object open() {
        createContents();
        return super.open();
    }

    @objid ("12983b09-702f-4a7c-a85a-0e44bbba65e7")
    private void createContents() {
        setLabels();
        
        this.shell = new Shell(getParent(), 67696 | SWT.APPLICATION_MODAL | SWT.RESIZE | SWT.TITLE);
        this.shell.setLayout( new FormLayout());
        this.shell.setText(this.frametitle);
        
        // File chooser composite
        this.fileChooserComposite = new FileChooserComposite(this.shell, SWT.NONE, SWT.OPEN);
        
        
        // Validation Composite
        this.validateComposite = new ValidationBoutonComposite(this.shell, SWT.NONE, this.cancelButton, this.validateButton);
        
        this.validateComposite.getValidationButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
        
                if (getFileChooserComposite().getCurrentFile() != null) {
                    validationAction();
                } else {
                    selectAFile();
                }
            }
        });
        
        this.validateComposite.getCancelButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setCancellation(true);
                cancelAction();
            }
        });
        
        final FormData fd_fileChooserComposite = new FormData();
        fd_fileChooserComposite.right = new FormAttachment(100, 0);
        fd_fileChooserComposite.bottom = new FormAttachment(0, 50);
        fd_fileChooserComposite.top = new FormAttachment(0, 0);
        fd_fileChooserComposite.left = new FormAttachment(0, 0);
        this.fileChooserComposite.setLayoutData(fd_fileChooserComposite);
        
        final FormData fd_validateComposite = new FormData();
        fd_validateComposite.top = new FormAttachment(this.fileChooserComposite, 5);
        fd_validateComposite.bottom = new FormAttachment(100, -5);
        fd_validateComposite.left = new FormAttachment(this.fileChooserComposite, 0, SWT.LEFT);
        fd_validateComposite.right = new FormAttachment(this.fileChooserComposite, 0, SWT.RIGHT);
        this.validateComposite.setLayoutData(fd_validateComposite);
        
        this.fileChooserComposite.getSearch().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                fileChooserComposite.searchFile();
            }
        });
        
        setDefaultDialog();
        this.shell.pack();
        this.shell.setMinimumSize(new Point(this.shell.getBounds().width, this.shell.getBounds().height));
        
        this.validateComposite.getValidationButton().setFocus();
    }

    @objid ("dc300de7-a6d8-44e5-8ae5-171ce57a596d")
    public SCXMLWizardExport(Shell parent) {
        super(parent);
    }

    @objid ("bca097b1-034c-426a-b051-1c70afcdf644")
    @Override
    public void setLabels() {
        setTitle("Title");
        setDescription("Description");
        setFrametitle("SCXML Export");
        setCancelButton("Cancel");
        setValidateButton("Export");
    }

    @objid ("66bfb6f6-61a9-45a7-bdb7-021f4ae3fab7")
    @Override
    public void validationAction() {
        String pathDest = ResourcesManager.getInstance().getGeneratedPath() + File.separator + this.fileChooserComposite.getText() + ".xml";
        File file = new File(pathDest);
        
        SCXMLGeneration scxmlGen = new SCXMLGeneration((StateMachine) this.selectedElt);
        StringBuffer content = scxmlGen.generate();    
        FileUtils.write(file, content);
        
        completeBox();
    }

    @objid ("79f42951-dc0c-4ba6-9a45-6532168683a5")
    @Override
    public void setPath() {
        if (this.path.equals(""))
            this.path = CPSWarmModule.getInstance().getModuleContext().getProjectStructure().getPath().toString();
        
        this.fileChooserComposite.setText(this.selectedElt.getName());
    }

    @objid ("30b64d38-d279-4e31-9f43-603d16782ad6")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] { "xml" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.xml" });
        setPath();
    }

}
