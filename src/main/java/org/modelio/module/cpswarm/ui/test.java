package org.modelio.module.cpswarm.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.module.cpswarm.ui.window.CPDTWizard;

@objid ("9b489b64-0861-4bdd-90c5-500651b21e8a")
public class test {
    @objid ("cf84f512-a4fa-429c-8fa5-1dba01a07b4e")
    public static void main(String[] args) {
        CPDTWizard cpdtwizard = new CPDTWizard(Display.getCurrent().getActiveShell());
        cpdtwizard.open();
    }

}
