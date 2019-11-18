package org.modelio.module.cpswarm.command.diagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.utils.CPSwarmFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("49ee3c6d-0c0d-4fd5-845a-f1bd7b3656bc")
public class EventTool extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("df43069d-7c25-4d19-ae2d-de85e3f70141")
    public EventTool() {
        super();
    }

    @objid ("ad212ef4-c4f7-43ef-9c23-5b5a86df82d3")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        if (element instanceof AbstractDiagram) {
            element = ((AbstractDiagram) element).getOrigin();
        }
        
        if (element instanceof Transition) {
            System.err.println("found");
        }
        return  (element instanceof Transition);
    }

    @objid ("d78b058e-39f8-46dd-8c44-50df93e96aba")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction ("Create Event")){
        
            MObject element = target.getElement();
            if (element instanceof AbstractDiagram) {
                element = ((AbstractDiagram) element).getOrigin();
            }
        
            if (element instanceof Transition) {
                MObject constraint = CPSwarmFactory.createEvent((Transition) element);
                List<IDiagramGraphic> graph = representation.unmask (constraint, rect.x, rect.y);
        
                if((graph != null) &&  (graph.size() > 0) && (graph.get(0) instanceof IDiagramNode))
                    ((IDiagramNode)graph.get(0)).setBounds(rect);
        
                representation.save();
                representation.close();
            }
            transaction.commit ();
        }
    }

}
