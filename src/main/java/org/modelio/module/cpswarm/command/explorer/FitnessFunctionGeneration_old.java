package org.modelio.module.cpswarm.command.explorer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.api.CPSWarmStereotypes;
import org.modelio.module.cpswarm.impl.CPSWarmPeerModule;
import org.modelio.module.cpswarm.ui.window.FitnessFunctionWizardExport;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("5c92b96c-1279-47c1-9068-61e59a5ca33f")
public class FitnessFunctionGeneration_old extends DefaultModuleCommandHandler {
    @objid ("3e0bbc1e-89ba-4010-8b5e-3ba95db3fe59")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        FitnessFunctionWizardExport export = new FitnessFunctionWizardExport(Display.getCurrent().getActiveShell());
        export.setSelectedElt((ModelElement)selectedElements.get(0));
        export.open();
    }

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("c01a0190-d8f6-49d4-afa0-73a4a9b11dac")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        return ((selectedElements.get(0) instanceof org.modelio.metamodel.uml.statik.Class) && 
                (((ModelElement)selectedElements.get(0)).isStereotyped(CPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.FITNESSFUNCTION)));
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @objid ("2e7b9965-9a31-46ba-8b5f-eef117fadc48")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
