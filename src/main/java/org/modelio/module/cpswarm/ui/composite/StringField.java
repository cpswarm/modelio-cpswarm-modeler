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
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

@objid ("9d468a4b-056c-44e8-a102-627d9dc0e66e")
public class StringField extends AbstractField {
    @objid ("5825e8ab-8756-4161-aed2-741f33531c89")
    private static final String EMPTY_STRING = "";

    @objid ("bb39e9bd-52eb-4bb0-b180-e25f9f8ded0c")
    private Text text;

    @objid ("cffedb32-fe5a-4759-a495-657c076bad92")
    public StringField(FormToolkit toolkit, Composite parent, String value) {
        super(toolkit, parent, value);
    }

    /**
     * {@inheritDoc}
     */
    @objid ("dbfe9592-eabd-4f0c-8994-11471e8a14bb")
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
    @objid ("77c5a55b-5aae-4cab-870e-f13210abd356")
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
    void applyValue() {
        final String value = this.text.getText();
//        if (getModel().getType().isValidValue(value)) {
            setValue(value);
//        } else {
//            refresh();
//        }
    }

}
