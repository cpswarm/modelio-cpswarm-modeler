package org.modelio.module.cpswarm.command.explorer;

import java.util.List;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.ui.window.SCXMLWizardExport;
import org.modelio.vcore.smkernel.mapi.MObject;


public class SCXMLGeneration extends DefaultModuleCommandHandler {

    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        SCXMLWizardExport export = new SCXMLWizardExport(Display.getCurrent().getActiveShell());
        export.setSelectedElt((ModelElement)selectedElements.get(0));
        export.open();
    }

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched.
     */

    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        
        return (selectedElements.get(0) instanceof StateMachine);
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */

    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
