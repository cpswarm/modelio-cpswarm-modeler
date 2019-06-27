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

public class EnumerationComposite extends Composite {
    
    private Label label = null;

    private Combo combo = null;

    /**
     * This method sets the label of the composite
     * @param label : the label of the composite
     */
    @objid ("30032f29-b646-4bc5-94b2-db3675da639c")
    public void setText(final String label) {
        if (label != null)
            this.combo.setText(label);
    }
    
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
    @objid ("46e26c47-2811-4605-a7e8-35c3f9f88468")
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
    @objid ("50095e7b-1f62-40b6-8fbd-b1fd6503333a")
    public String getComboValue() {
        return this.combo.getText();
    }

    /**
     * This method returns the SWT Text owned by the FileChooserComposite
     * @return the owned SWT TEXT
     */
    @objid ("006aee3c-4c2f-40ce-9c60-10df94d30594")
    public Combo getComboButton() {
        return this.combo;
    }


}
