package org.modelio.module.cpswarm.ui.composite;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

@objid ("79e11be7-77be-4eb7-9d05-0fb89a9c145c")
public class StringField extends AbstractField {
    @objid ("8da05dcf-1abc-467b-9bda-b6949bbfdfb1")
    private static final String EMPTY_STRING = "";

    @objid ("00504259-18b7-4ba1-be0b-85a9262915aa")
    private Text text;

    @objid ("1da7e416-4f91-44b9-977a-afb7f5ece700")
    public StringField(FormToolkit toolkit, Composite parent, String value) {
        super(toolkit, parent, value);
    }

    /**
     * {@inheritDoc}
     */
    @objid ("c661eb31-9e7c-4ef3-8d14-701cde0be65f")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        this.text = toolkit.createText(parent, EMPTY_STRING, SWT.NONE);
        
        // Initialize values
        getLabel().setText(getValue());
        
        String value = getValue();
        this.text.setText(value != null ? value.toString() : EMPTY_STRING);
        
        // Install Listeners
        this.text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                applyValue();
            }
        });
        
        this.text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
                    applyValue();
                } else if (e.keyCode == SWT.ESC) {
                    refresh();
                }
            }
        });
        
        //        this.text.addModifyListener(new ModifyListener() {
        //            @Override
        //            public void modifyText(ModifyEvent e) {
        //                validate();
        //            }
        //        });
        return this.text;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("63e03f28-7969-453a-b908-6e9665f4000e")
    @Override
    public void refresh() {
        final String value = getValue();
        this.text.setText(value != null ? value : EMPTY_STRING);
    }

//    @objid ("510dc0fe-62d1-42d7-82da-c3d658b4dfe3")
//    void validate() {
//        final String value = this.text.getText();
//        if (getModel().getType().isValidValue(value)) {
//            this.text.setForeground(null);
//        } else {
//            this.text.setForeground(UIColor.RED);
//        }
//    }
//
//    @objid ("ed143876-7b7c-4fe6-839a-9c77922e8e73")
    @objid ("a29e263c-9528-4ecf-ae95-e0cfd1f07e24")
    void applyValue() {
        final String value = this.text.getText();
        //        if (getModel().getType().isValidValue(value)) {
            setValue(value);
        //        } else {
        //            refresh();
        //        }
    }

}
