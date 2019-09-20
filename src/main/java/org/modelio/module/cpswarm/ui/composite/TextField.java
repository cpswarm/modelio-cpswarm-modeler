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
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TextField extends AbstractField {

    private static final int VISIBLE_LINES_DEFAULT_NB = 4;

    /**
     * Indicates if {@link SWT#V_SCROLL} should be activated on {@link #text}.
     */
    private final int nVisibleLines;

    private static final String EMPTY_STRING = "";

    private Text text;

    public TextField(FormToolkit toolkit, Composite parent, String value) {
        this(toolkit, parent, value, VISIBLE_LINES_DEFAULT_NB);
    }

    /**
     * {@inheritDoc}
     */
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
    @Override
    public void refresh() {
        final String value = getValue();
        this.text.setText(value != null ? value.toString() : EMPTY_STRING);
    }

    public TextField(FormToolkit toolkit, Composite parent, String value, int nVisibleLines) {
        super(toolkit, parent, value);
        this.nVisibleLines = nVisibleLines;
    }

}
