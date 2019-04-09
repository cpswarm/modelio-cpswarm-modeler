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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
@objid ("0bd1394a-8404-436b-8808-6cadc328c916")
public abstract class AbstractField implements IField {
   
    private static final String EMPTY_STRING = "";


    @objid ("ae2416c6-78da-4158-8602-ab9b6ba15865")
    private Label label;

    @objid ("d901e4b1-a96c-4c53-8d20-aaf225a15b7a")
    private Control control;


    @objid ("8c93964d-0776-446b-8af5-05153384c4bc")
    private Composite fieldComposite;

    @objid ("17ca4f11-945f-4612-8297-6d6698128403")
    private final Composite parent;

    
    private final FormToolkit toolkit; 

    @objid ("d5062528-177d-489b-91eb-aa0c5d36542f")
    private String value;

    @objid ("17091519-a6ab-4ac0-b777-3d8fbdf4ce0b")
    public abstract Control createControl(FormToolkit toolkit, Composite p);

    @objid ("77c1c8a1-7453-40e6-93b9-5606dfbcce42")
    public AbstractField(FormToolkit toolkit, Composite parent, String value) {
        this.toolkit = toolkit;
        this.parent = parent;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("2e2919c6-c635-4893-a123-04e7e23c503a")
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
    @objid ("29f524d6-6ff9-4038-ad1d-e4937d692d92")
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
    @objid ("c929183e-78d4-4e90-bccb-d2c98fd09f64")
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
    @objid ("b32df004-b93f-4823-bf56-ea25854cc7ea")
    @Override
    public String getValue() {
        return this.value;
    }

    @objid ("f23e8e05-59a5-4bed-88c9-70ba914a3380")
    public final Label getLabel() {
        return this.label;
    }


    @objid ("8925d210-0086-4f04-9ef6-657f43edac5f")
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


    @objid ("3e47891d-fabf-4e1d-b552-3ed3116273f1")
    public AbstractField(FormToolkit toolkit, Composite parent) {
        this.toolkit = toolkit;
        this.parent = parent;
        this.value = EMPTY_STRING;
    }

    @objid ("e0268ef8-2ae7-49ed-95b1-ca1181db44a2")
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @objid ("b29444ff-ae58-4f49-ade8-27b441ddbf67")
    @Override
    public void setEditable(boolean onoff) {
        if (this.control != null) {
            this.control.setEnabled(onoff);
        }
        refresh();
    }

}
