package org.modelio.module.cpswarm.command.explorer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.ui.SCXMLWizardExport;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("ce5de677-f348-438d-a3b7-47b0c6b1ccf9")
public class SCXMLGeneration extends DefaultModuleCommandHandler {
    @objid ("900f03f1-f672-4d81-9edc-afbedc5ced2c")
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
    @objid ("affec6c8-0ba1-4669-bf47-3c001415245d")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        
        return (selectedElements.get(0) instanceof StateMachine);
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @objid ("d7f71426-5498-4843-8486-15f9a999d1d2")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
