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
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.utils.CPSwarmFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("4498f11d-6b7b-455f-845a-32c8e1eea429")
public class SubMachineTool extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("15225b0b-90f1-46e5-9b60-5ee25e651fff")
    public SubMachineTool() {
        super();
    }

    @objid ("7b9fca57-e43c-4392-8eb0-8ff67c6d39dc")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        if (element instanceof AbstractDiagram) {
            element = ((AbstractDiagram) element).getOrigin();
        }
        return  (element instanceof StateMachine);
    }

    @objid ("4b2814bc-16d6-45a7-bc22-40907b5cd6d0")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction ("Create SubMachine")){
        
            MObject element = target.getElement();
            if (element instanceof AbstractDiagram) {
                element = ((AbstractDiagram) element).getOrigin();
            }
        
            if( element instanceof StateMachine) {
                MObject constraint = CPSwarmFactory.createSubStateMachine((StateMachine) element);
                List<IDiagramGraphic> graph = representation.unmask (constraint, rect.x, rect.y);
        
                if((graph != null) &&  (graph.size() > 0) && (graph.get(0) instanceof IDiagramNode))
                    ((IDiagramNode)graph.get(0)).setBounds(rect);
        
                representation.save();
                representation.close();
                transaction.commit ();
            }
        }
    }

}
