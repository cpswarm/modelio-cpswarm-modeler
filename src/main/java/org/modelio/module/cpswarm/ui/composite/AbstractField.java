/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.module.cpswarm.ui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * An abstract implementation of IField composed of:
 * <ul>
 * <li>a Label</li>
 * <li>an edition control</li>
 * </ul>
 * <br/>
 * The composite field controls are layouted as follows:<br/>
 * [--- label ---] [-------- edition control -------- ] <br/>
 * [----------- help text ------------]<br/>
 * 
 * where the help text if folded/unfolded by the help button. The class is in charge of creating the different widgets and of their
 * layout. the creation of the edition control is delegated to concrete sub-classes.
 */
public abstract class AbstractField implements IField {
   
    private static final String EMPTY_STRING = "";


    private Label label;

    private Control control;


    private Composite fieldComposite;

    private final Composite parent;

    
    private final FormToolkit toolkit; 

    private String value;

    public abstract Control createControl(FormToolkit toolkit, Composite p);

    public AbstractField(FormToolkit toolkit, Composite parent, String value) {
        this.toolkit = toolkit;
        this.parent = parent;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void layout(Label lbl, Control ctrl) {
      
            // The label
            FormData formData = new FormData();
            formData.top = new FormAttachment(this.control, 0, SWT.TOP);
            formData.bottom = new FormAttachment(this.control, 0, SWT.BOTTOM);
            formData.left = new FormAttachment(0);
            formData.right = new FormAttachment(20);
            lbl.setLayoutData(formData);
        
            // The Control
            formData = new FormData();
            formData.top = new FormAttachment(0, 0);
            formData.bottom = new FormAttachment(this.label, 0, SWT.BOTTOM);
            formData.left = new FormAttachment(this.label, 10);
            formData.right = new FormAttachment(100);
            ctrl.setLayoutData(formData);
       
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public final Composite getComposite() {
        // Ensure widgets exist.
        if (this.fieldComposite == null) {
            buildGui();
            assert (this.fieldComposite != null);
        }
        return this.fieldComposite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Control getControl() {
        if (this.control == null) {
            buildGui();
            assert (this.control != null);
        }
        return this.control;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return this.value;
    }

    public final Label getLabel() {
        return this.label;
    }


    private void buildGui() {
        // The composite field is build on a Composite using a FormLayout
        this.fieldComposite = this.toolkit.createComposite(this.parent);
        this.fieldComposite.setBackground(this.parent.getBackground());
        final FormLayout l = new FormLayout();
        this.fieldComposite.setLayout(l);
        
        // Create the composite field controls
        
        this.label = this.toolkit.createLabel(this.fieldComposite, EMPTY_STRING);
        this.label.setBackground(this.parent.getBackground());
     
        
        // Create the control
        this.control = createControl(this.toolkit, this.fieldComposite);
        if (this.control == null) {
            throw new NullPointerException(String.format("%s.createControl(...) returned null", this));
        }
        
        // Layout the composite field controls
        layout(this.label, this.control);
        
        assert (this.label.getLayoutData() instanceof FormData);
        assert (this.control.getLayoutData() instanceof FormData);
        

      
    }


    public AbstractField(FormToolkit toolkit, Composite parent) {
        this.toolkit = toolkit;
        this.parent = parent;
        this.value = EMPTY_STRING;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void setEditable(boolean onoff) {
        if (this.control != null) {
            this.control.setEnabled(onoff);
        }
        refresh();
    }

}
