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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("83b989a7-4af1-4ca1-8493-92689e9c9aa0")
public class EnumField extends AbstractField {
    @objid ("bcd27c2b-7154-4fb7-a36a-fa9e95133922")
    private Combo combo;
    

    @objid ("b0900e68-5fc1-4105-b1cf-47b8f9895eba")
    private ComboViewer comboViewer;

    @objid ("c6e112b9-a881-4c93-b885-dccedfd228a1")
    public EnumField(FormToolkit toolkit, Composite parent, String value) {
        super(toolkit, parent, value);
    }

    /**
     * {@inheritDoc}
     */
    @objid ("80443d8e-64e5-44a2-a3a6-a146e472f496")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        this.comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
        this.comboViewer.setContentProvider(new ArrayContentProvider() {
            @Override
            public Object[] getElements(Object inputElement) {
                List<Object> ret = new ArrayList<>();
                for (Object element : super.getElements(inputElement)) {
                    if (element instanceof MObject) {
                        ret.add(((MObject) element).getName());
                    } else {
                        ret.add(element);
                    }
                }
                return ret.toArray();
            }
        });
        this.combo = this.comboViewer.getCombo();
                
        // Initialize values
        getLabel().setText(getValue());
        
        this.comboViewer.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                return super.getText(element);
            }
        });
        this.combo.add("");
        
        refresh();
        
        // Install Listeners
        this.comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final ComboViewer combo = (ComboViewer) event.getSource();
                final StructuredSelection selection = (StructuredSelection) combo.getSelection();
                if (!selection.isEmpty()) {
                    final Object selectedType = selection.getFirstElement();
                    // Tricky: Have to deal with multiple events for a single change. The combo listener is invoked when input
                    // is changed. The model change event is caught and processed by setting the combo input which in turn fires
                    // the combo listener This means that if the combo listener tries to change the value, it will initiate a
                    // Transaction while a model change notification is being processed, which is forbidden
                    // We fix it by comparing old and new values and ignoring the event if their are equal.
                    if (!selectedType.equals(getValue())) {
                        setValue(selectedType.toString());
                    }
                }
            }
        });
        return this.combo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        final Object value = getValue();
        
        if (value != null) {
            this.comboViewer.setSelection(new StructuredSelection(value));
        }
    }

    public void setLabelProvider(IBaseLabelProvider labelProvider) {
        this.comboViewer.setLabelProvider(labelProvider);
    }

    @Override
    public void layout(Label label, Control control) {
        // TODO Auto-generated method stub
        
    }


}
