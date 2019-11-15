/*
 * Copyright 2013 Modeliosoft
 *
 * This file is part of Modelio.
 *
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 *
 */


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
@objid ("f91e2337-fef4-4e6e-b382-cb9fb518f9b2")
public class SCXMLWizardExport extends AbstractSwtWizardWindow {

    public Object open() {
        createContents();
        return super.open();
    }

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


    public SCXMLWizardExport(Shell parent) {
        super(parent);

    }

    @objid ("4e2d6edc-3ccc-4cba-ad1f-15d93e376374")
    @Override
    public void setLabels() {
        setTitle("Title");
        setDescription("Description");
        setFrametitle("SCXML Export");
        setCancelButton("Cancel");
        setValidateButton("Export");
    }


    
    @objid ("9029119f-2c5b-4472-b2ad-9ecfc3a56cbc")
    @Override
    public void validationAction(){
        
        String pathDest = ResourcesManager.getInstance().getGeneratedPath() + File.separator + this.fileChooserComposite.getText() + ".xml";
        File file = new File(pathDest);
        
        SCXMLGeneration scxmlGen = new SCXMLGeneration((StateMachine) this.selectedElt);
        StringBuffer content = scxmlGen.generate();    
        FileUtils.write(file, content);
        
        completeBox();
    }
    
    @objid ("fdbbd9fa-c792-4c27-b1e3-f95ee6a8cd9c")
    @Override
    public void setPath() {

        if (this.path.equals(""))
            this.path = CPSWarmModule.getInstance().getModuleContext().getProjectStructure().getPath().toString();

        this.fileChooserComposite.setText(this.selectedElt.getName());
    }


    @objid ("063e9bd5-0823-4284-be39-aca4a7fb2a8c")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] { "xml" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.xml" });
        setPath();
    }


}
