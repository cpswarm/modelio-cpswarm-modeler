package org.modelio.module.cpswarm.command.explorer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.utils.CPSwarmFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("efc568cf-5bc0-4c3b-86fd-087ca92a3b79")
public class SwarmBehaviorCommand extends DefaultModuleCommandHandler {
    @objid ("494726c1-c414-4293-9c26-3df60b59fa4f")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModuleContext moduleContext = CPSWarmModule.getInstance().getModuleContext();
        IModelingSession modelingSession = moduleContext.getModelingSession();
        
        try (ITransaction tr = modelingSession.createTransaction("Swarm Behavior Diagram");){
        
            StateMachineDiagram diagram = null;
        
            MObject owner = selectedElements.get(0);   
            if (owner instanceof Operation)
                diagram = CPSwarmFactory.createBehavior((Operation) owner);
            else 
                diagram = CPSwarmFactory.createBehavior((org.modelio.metamodel.uml.statik.Class) owner);
        
            if (diagram != null) {      
        
                IModelioServices modelioServices = moduleContext.getModelioServices();
                IDiagramService ds = modelioServices.getDiagramService();
                try(IDiagramHandle dh = ds.getDiagramHandle(diagram);){
        
                    IDiagramDG dg = dh.getDiagramNode();
                    for (IStyleHandle style : ds.listStyles()){
                        if (style.getName().equals("cpswarm"))
                            dg.setStyle(style);
                    }
        
        
                    dh.save();
                    dh.close();
                }
                modelioServices.getEditionService().openEditor(diagram);
            }
        
            tr.commit();
        }
    }

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("3400a063-956d-403f-957d-e134351226ad")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if ((selectedElements != null) && (selectedElements.size() == 1 )){
            MObject selected = selectedElements.get(0);
            return  ((selected instanceof org.modelio.metamodel.uml.statik.Class) 
                    ||(selected instanceof Operation));
        
        }
        return false;
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @objid ("1472d8a2-2327-407f-9634-224fd91a1d70")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
