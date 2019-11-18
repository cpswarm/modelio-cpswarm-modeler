package org.modelio.module.cpswarm.command.explorer.diagram.command;

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
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.utils.CPSwarmFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("f3b4fa89-51e7-4456-83ee-fdcbefc6ff7b")
public class SwarmDefinitionDiagram extends DefaultModuleCommandHandler {
    @objid ("f63edd9f-d67e-4b76-827e-07ab2d97d1cb")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModuleContext moduleContext = CPSWarmModule.getInstance().getModuleContext();
        IModelingSession modelingSession = moduleContext.getModelingSession();
        
        try (ITransaction tr = modelingSession.createTransaction("Swarm Diagram");){
            
            org.modelio.metamodel.uml.statik.Package owner = (org.modelio.metamodel.uml.statik.Package) selectedElements.get(0);
        
            AbstractDiagram diagram = CPSwarmFactory.createEnvironmentDefinitionDiagram(owner);
            if (diagram != null) {             
                IModelioServices modelioServices = moduleContext.getModelioServices();
                IDiagramService ds = modelioServices.getDiagramService();
                IDiagramHandle dh = ds.getDiagramHandle(diagram);
                IDiagramDG dg = dh.getDiagramNode();
                for (IStyleHandle style : ds.listStyles()){
                    if (style.getName().equals("cpswarm"))
                        dg.setStyle(style);
                }
                dh.save();
                dh.close();
                modelioServices.getEditionService().openEditor(diagram);
            }
           
            tr.commit();
        }
    }

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("b21555bb-95e1-4449-bfdd-1bdbde91d569")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if ((selectedElements != null) && (selectedElements.size() == 1 )){
            MObject selected = selectedElements.get(0);
            return  ((selected instanceof org.modelio.metamodel.uml.statik.Package) 
                    && !(selected instanceof Profile));
        
        }
        return false;
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @objid ("14074ae6-6248-4029-88e9-4337a101ee57")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
