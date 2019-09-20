package org.modelio.module.cpswarm.ui;

import org.eclipse.swt.widgets.Display;
import org.modelio.module.cpswarm.ui.window.CPDTWizard;

public class test {

    public static void main(String[] args) {
        CPDTWizard cpdtwizard = new CPDTWizard(Display.getCurrent().getActiveShell());
        cpdtwizard.open();

    }

}
