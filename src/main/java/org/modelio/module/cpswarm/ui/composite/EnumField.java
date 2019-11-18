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

@objid ("2d45f81c-019e-4899-9212-8ab0804e1882")
public class EnumField extends AbstractField {
    @objid ("ba98a72c-d01d-45e9-8e1e-2055a2554a88")
    private Combo combo;

    @objid ("d4195cc2-4726-4b92-b136-1c26073795dc")
    private ComboViewer comboViewer;

    @objid ("4e477e49-ba2a-4a7c-b4d4-a19cdc9e1dd4")
    public EnumField(FormToolkit toolkit, Composite parent, String value) {
        super(toolkit, parent, value);
    }

    /**
     * {@inheritDoc}
     */
    @objid ("44ac541a-0625-465f-98b9-16557786d402")
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
    @objid ("ba27b707-472e-4210-a64e-d746176b6c69")
    @Override
    public void refresh() {
        final Object value = getValue();
        
        if (value != null) {
            this.comboViewer.setSelection(new StructuredSelection(value));
        }
    }

    @objid ("9f735cf6-2bd4-47a2-a838-0e4d0358c2a0")
    public void setLabelProvider(IBaseLabelProvider labelProvider) {
        this.comboViewer.setLabelProvider(labelProvider);
    }

    @objid ("cf8ea8dd-1e75-4ce9-afa5-41c052d05e1c")
    @Override
    public void layout(Label label, Control control) {
        // TODO Auto-generated method stub
    }

}
