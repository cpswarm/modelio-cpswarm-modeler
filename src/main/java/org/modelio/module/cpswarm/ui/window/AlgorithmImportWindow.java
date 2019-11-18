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
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.type.Algorithm.AlgorithmImporter;
import org.modelio.module.cpswarm.ui.composite.FileChooserComposite;
import org.modelio.module.cpswarm.ui.composite.ValidationBoutonComposite;

/**
 * This class provides the XMI import dialog
 * @author ebrosse
 */
@objid ("46498fd4-f763-42c8-b712-b7597fe6394a")
public class AlgorithmImportWindow extends AbstractSwtWizardWindow {
    @objid ("35738af2-67f4-4ca7-b806-fa867d85c75c")
    private void createContents() {
        setLabels();
        
        this.shell = new Shell(getParent(), 67696 | SWT.APPLICATION_MODAL | SWT.RESIZE | SWT.TITLE);
        this.shell.setLayout( new FormLayout());
        this.shell.setText(this.frametitle);
        
        // File chooser composite
        this.fileChooserComposite = new FileChooserComposite(this.shell, SWT.NONE, SWT.OPEN);
        
        
        // Validation Composite
        this. validateComposite = new ValidationBoutonComposite(this.shell, SWT.NONE, this.cancelButton, this.validateButton);
        
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
        fd_fileChooserComposite.bottom = new FormAttachment(0, 30);
        fd_fileChooserComposite.top = new FormAttachment(0, 0);
        fd_fileChooserComposite.left = new FormAttachment(0, 0);
        this.fileChooserComposite.setLayoutData(fd_fileChooserComposite);
        
        this.fileChooserComposite.getSearch().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AlgorithmImportWindow.this.fileChooserComposite.searchFile();
            }
        });
        
        final FormData fd_validateComposite = new FormData();
        fd_validateComposite.top = new FormAttachment(this.fileChooserComposite, 5);
        fd_validateComposite.bottom = new FormAttachment(100, -5);
        fd_validateComposite.left = new FormAttachment(this.fileChooserComposite, 0, SWT.LEFT);
        fd_validateComposite.right = new FormAttachment(this.fileChooserComposite, 0, SWT.RIGHT);
        this.validateComposite.setLayoutData(fd_validateComposite);
        
        setDefaultDialog();
        this.shell.pack();
        this.shell.setMinimumSize(new Point(400, this.shell.getBounds().height));
        
        this.validateComposite.getValidationButton().setFocus();
    }

    @objid ("8ccc01be-95e8-4599-abc8-0e0b11961b70")
    @Override
    public void validationAction() {
        File theFile = getFileChooserComposite().getCurrentFile();
        
        //        String extension = theFile.getName();
        //        extension = extension.substring(extension.lastIndexOf("."));
        //        if (extension.equals(".fmu")) {
        //            File dirFile = new File(System.getProperty("java.io.tmpdir"));
        //            FileUtils.unZipIt(theFile, dirFile);
        //            theFile = new File(dirFile.getAbsoluteFile() + File.separator + "modelDescription.xml");
        //            extension = ".xml";
        //        }
        
        
        //        if (theFile.exists() && theFile.isFile()) {
        
        //            if (extension.equals(".xml")) {
        
        try(ITransaction t = CPSWarmModule.getInstance().getModuleContext().getModelingSession().createTransaction("Import") ) {
        
            AlgorithmImporter importer = new AlgorithmImporter();
            importer.importing((Component) this.selectedElt, theFile);
        
            t.commit();
        
            completeBox();
        
        
        } catch (final Exception e) {
            CPSWarmModule.logService.error(e);
        }
        //            }
        //        } else {
        //            fileDontExist();
        //        }
    }

    @objid ("a534fd46-09b5-489c-926e-f2b9da13fac3")
    @Override
    public void setLabels() {
        setTitle("Title");
        setDescription("description");
        setFrametitle("Import");
        setCancelButton("Cancel");
        setValidateButton("Import");
    }

    @objid ("a2c272c0-3873-45cf-b7d5-d27e06955fa6")
    @Override
    public void setPath() {
        try {
            if (this.path.equals(""))
                this.path = CPSWarmModule.getInstance().getModuleContext().getProjectStructure().getPath().toAbsolutePath().toString();
        
            this.fileChooserComposite.getDialog().setFilterPath(this.path);
            this.fileChooserComposite.getDialog().setFileName("");
            this.fileChooserComposite.setText(this.path);
        } catch (@SuppressWarnings ("unused") final NoClassDefFoundError e) {
            this.fileChooserComposite.setText("");
        }
    }

    @objid ("956607c4-66ef-41a3-b89c-52ed17aa82a5")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] { "Algortihm Files (*.json)" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] {  "*.json" });
        setPath();
    }

    /**
     * @param style : the SWT style
     * @param parent : the parent shell
     */
    @objid ("8a6afe91-93fe-460f-b8fa-45fe9bb8ec3c")
    public AlgorithmImportWindow(final Shell parent) {
        super(parent);
    }

    @objid ("e70c972d-d349-4925-9330-1c0ff1273438")
    @Override
    public Object open() {
        createContents();
        return super.open();
    }

}
