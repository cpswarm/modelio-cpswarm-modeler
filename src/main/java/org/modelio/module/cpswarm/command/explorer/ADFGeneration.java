package org.modelio.module.cpswarm.command.explorer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("cf5de156-d686-4ec3-b00a-2d7fd8370d1c")
public class ADFGeneration extends DefaultModuleCommandHandler {
    @objid ("ad366bc6-c9e8-4ff0-9bef-e2ff780c58c2")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
    }

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("789bacd2-dff9-42eb-a09f-9c025b534383")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        return (selectedElements.get(0) instanceof StateMachine);
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @objid ("e30634c2-94a6-487e-9793-75d8f0781916")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
