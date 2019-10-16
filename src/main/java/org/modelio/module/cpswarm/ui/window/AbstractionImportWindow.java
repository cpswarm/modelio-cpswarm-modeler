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
@objid ("ea710891-a275-4523-a1f2-402acf87333c")
public class AbstractionImportWindow extends AbstractSwtWizardWindow {


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


    @objid ("54e9a756-61db-4392-8c51-ae214789dce8")
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

    @objid ("073f156d-159c-4419-aeca-d5a56261250b")
    @Override
    public void setLabels() {
        setTitle("Title");
        setDescription("description");
        setFrametitle("Import");
        setCancelButton("Cancel");
        setValidateButton("Import");
    }

    @objid ("3759e190-f11c-4b8b-913a-c0f3ddeab109")
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

    @objid ("b6feb3f7-b70a-409d-be67-7ff97278009d")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] { "Algortihm Files (*.json)" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] {  "*.json" });
        setPath();
    }

    /**
     * @param parent : the parent shell
     * @param style : the SWT style
     */
    @objid ("62fd7184-3a2d-43de-b370-42ca75091996")
    public AbstractionImportWindow(final Shell parent) {
        super(parent);
    }

    @Override
    public Object open() {
        createContents();
        return super.open();
    }

}
