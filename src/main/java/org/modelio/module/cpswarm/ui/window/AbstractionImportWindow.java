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
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.type.Abstraction.AbstractionImporter;
import org.modelio.module.cpswarm.ui.composite.FileChooserComposite;
import org.modelio.module.cpswarm.ui.composite.ValidationBoutonComposite;

/**
 * This class provides the XMI import dialog
 * @author ebrosse
 */
@objid ("ef74fbcb-6e30-44f3-a182-84bf21dbd083")
public class AbstractionImportWindow extends AbstractSwtWizardWindow {
    @objid ("69ef542f-6b2e-4e29-9267-2c76261c78c1")
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
                AbstractionImportWindow.this.fileChooserComposite.searchFile();
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

    @objid ("642b10f0-8c52-4410-b710-260b08a535fe")
    @Override
    public void validationAction() {
        File theFile = getFileChooserComposite().getCurrentFile();
        
        
        try(ITransaction t = CPSWarmModule.getInstance().getModuleContext().getModelingSession().createTransaction("Import") ) {
        
            AbstractionImporter importer = new AbstractionImporter();
            importer.importing((NameSpace) this.selectedElt, theFile);
        
            t.commit();
        
            completeBox();
        
        
        } catch (final Exception e) {
            CPSWarmModule.logService.error(e);
        }
    }

    @objid ("17c47eaf-500e-4746-90df-0c4923d6f326")
    @Override
    public void setLabels() {
        setTitle("Title");
        setDescription("description");
        setFrametitle("Import");
        setCancelButton("Cancel");
        setValidateButton("Import");
    }

    @objid ("142bd476-f7bb-4522-b22c-32666080c432")
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

    @objid ("29b9b30a-d418-4a7a-9f6d-bf127da7d454")
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
    @objid ("50fc52fb-a7b4-4782-804a-71c0078e4022")
    public AbstractionImportWindow(final Shell parent) {
        super(parent);
    }

    @objid ("a080e022-0cfb-4420-a011-5411c4e10663")
    @Override
    public Object open() {
        createContents();
        return super.open();
    }

}
