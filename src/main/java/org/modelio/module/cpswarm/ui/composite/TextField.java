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
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

@objid ("67399fc5-cfda-40cb-9fd6-b240b983563c")
public class TextField extends AbstractField {
    @objid ("64e600f1-fc4b-4143-94d0-e506ffed57dc")
    private static final int VISIBLE_LINES_DEFAULT_NB = 4;

    /**
     * Indicates if {@link SWT#V_SCROLL} should be activated on {@link #text}.
     */
    @objid ("393a5dab-6ae9-4239-8a37-b18e403135a1")
    private final int nVisibleLines;

    @objid ("dbb34a0c-829a-4c77-8137-b4021afa55ee")
    private static final String EMPTY_STRING = "";

    @objid ("56ff1db2-7583-4d92-9401-5e18523f2f4d")
    private Text text;

    @objid ("a87e467b-1375-451b-8ffa-7126244f010a")
    public TextField(FormToolkit toolkit, Composite parent, String value) {
        this(toolkit, parent, value, VISIBLE_LINES_DEFAULT_NB);
    }

    /**
     * {@inheritDoc}
     */
    @objid ("238f98a4-ae5f-4c67-9b17-77324a7ad9e7")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        if (this.nVisibleLines > 1) {
        this.text = toolkit.createText(parent, EMPTY_STRING, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        } else {
            this.text = toolkit.createText(parent, EMPTY_STRING, SWT.MULTI | SWT.WRAP);
        }
        this.text.addKeyListener(new KeyListener() {
        
            @Override
            public void keyReleased(KeyEvent e) {
                // Nothing to do
            }
        
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == 'a' && e.stateMask == SWT.CTRL) {
                    TextField.this.text.selectAll();
                }
            }
        });
        
        // Initialize values
        getLabel().setText(getValue());
        
        final Object value = getValue();
        this.text.setText(value != null ? value.toString() : EMPTY_STRING);
        
        // Install Listeners
        this.text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                setValue(TextField.this.text.getText());
            }
        });
        return this.text;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("ccc78e62-4e19-414a-88b9-9844d86abc7e")
    @Override
    public void refresh() {
        final String value = getValue();
        this.text.setText(value != null ? value.toString() : EMPTY_STRING);
    }

    @objid ("287ca589-2716-4448-a9da-9d1c280d0b82")
    public TextField(FormToolkit toolkit, Composite parent, String value, int nVisibleLines) {
        super(toolkit, parent, value);
        this.nVisibleLines = nVisibleLines;
    }

}
