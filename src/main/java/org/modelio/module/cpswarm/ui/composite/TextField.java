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

@objid ("84a06beb-38c5-4b19-9a2d-6220246a6f9a")
public class TextField extends AbstractField {
    @objid ("20e05ac2-737a-4bb3-89ce-ceb65c55bcbe")
    private static final int VISIBLE_LINES_DEFAULT_NB = 4;

    /**
     * Indicates if {@link SWT#V_SCROLL} should be activated on {@link #text}.
     */
    @objid ("7c60a795-6137-4905-a13d-651d3ac82f95")
    private final int nVisibleLines;

    @objid ("b262faf1-92cc-44ad-8b6e-3b32efc66fa9")
    private static final String EMPTY_STRING = "";

    @objid ("bb7c9272-1484-4ff0-bc07-339ad85b609d")
    private Text text;

    @objid ("c2c47e08-9908-4768-8469-1d5be38ca723")
    public TextField(FormToolkit toolkit, Composite parent, String value) {
        this(toolkit, parent, value, VISIBLE_LINES_DEFAULT_NB);
    }

    /**
     * {@inheritDoc}
     */
    @objid ("4113d1c9-5167-4096-82b3-2d7f4a932d0c")
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
    @objid ("9919814c-10e5-4730-b63f-3d3c66fc7a0c")
    @Override
    public void refresh() {
        final String value = getValue();
        this.text.setText(value != null ? value.toString() : EMPTY_STRING);
    }

    @objid ("d472fdb1-c299-47d9-9536-ffef6831a930")
    public TextField(FormToolkit toolkit, Composite parent, String value, int nVisibleLines) {
        super(toolkit, parent, value);
        this.nVisibleLines = nVisibleLines;
    }

}
