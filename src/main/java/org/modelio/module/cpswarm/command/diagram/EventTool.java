package org.modelio.module.cpswarm.command.diagram;

import java.util.List;
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

public class EventTool extends DefaultBoxTool {
    /**
     * Default constructor
     */
    public EventTool() {
        super();
    }

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
