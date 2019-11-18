package org.modelio.module.cpswarm.ui.window;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Shell;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.cpswarm.generator.FitnessFunctionGeneration;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.ui.composite.FileChooserComposite;
import org.modelio.module.cpswarm.ui.composite.ValidationBoutonComposite;
import org.modelio.module.cpswarm.utils.ResourcesManager;

/**
 * This class provides the XMI export dialog
 * @author ebrosse
 */
@objid ("8577dddb-6cd2-40ac-b12b-2a60057001a4")
public class FitnessFunctionWizardExport extends AbstractSwtWizardWindow {
    @objid ("a512222f-c2d3-41b8-b090-baebd032ef0a")
    public Object open() {
        createContents();
        return super.open();
    }

    @objid ("e87eda3c-bfcf-4a38-a846-4e23707401bb")
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

    @objid ("d3ddbc0c-7293-41e8-8d33-8e7898caa17e")
    public FitnessFunctionWizardExport(Shell parent) {
        super(parent);
    }

    @objid ("84e81fe4-f6ba-4e0a-9547-20887cfa0aa5")
    @Override
    public void setLabels() {
        setTitle("Title");
        setDescription("Description");
        setFrametitle("Fitness Function Export");
        setCancelButton("Cancel");
        setValidateButton("Export");
    }

    @objid ("677335e3-4764-4171-94c1-bcdeca5c2f95")
    private void write(String path, StringBuffer sbf) {
        File file = new File(path);
        file.getParentFile().mkdirs();
        
        try {
            file.createNewFile();
            /*
             * To write contents of StringBuffer to a file, use
             * BufferedWriter class.
             */
        
            BufferedWriter bwr;
        
            bwr = new BufferedWriter(new FileWriter(file));
        
        
            //write contents of StringBuffer to a file
            bwr.write(sbf.toString());
        
        
            //flush the stream
            bwr.flush();
        
            //close the stream
            bwr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @objid ("1576e2a6-156c-4114-ad0c-9e5378549cfd")
    @Override
    public void validationAction() {
        String pathDest = ResourcesManager.getInstance().getGeneratedPath() + File.separator + this.fileChooserComposite.getText() + ".java";
        
        FitnessFunctionGeneration fitGen = new FitnessFunctionGeneration((Class) this.selectedElt);
        StringBuffer content = fitGen.generate();        
        write(pathDest, content);
        
        completeBox();
    }

    @objid ("cef601d4-453c-453f-a984-4de1e3ac177c")
    @Override
    public void setPath() {
        if (this.path.equals(""))
            this.path = CPSWarmModule.getInstance().getModuleContext().getProjectStructure().getPath().toString();
        
        this.fileChooserComposite.setText(this.selectedElt.getName() + "Calculator");
    }

    @objid ("07076ff8-d0cf-48ea-b6de-6fec96fe1327")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] { "java" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.java" });
        setPath();
    }

}
