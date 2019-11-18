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

@objid ("2d2a038c-ab06-4f74-8fa4-bb983caad2a1")
public class FitnessFunctionGeneration extends DefaultModuleCommandHandler {
    @objid ("ff73023d-4bd3-4451-a33e-f050f64b7f5d")
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
    @objid ("aff6bde1-5bf8-4714-8f25-9b30481944d2")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        return ((selectedElements.get(0) instanceof org.modelio.metamodel.uml.statik.Class) && 
                (((ModelElement)selectedElements.get(0)).isStereotyped(CPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.FITNESSFUNCTION)));
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @objid ("63a13721-3200-419a-b461-ea3eead6ed1a")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
