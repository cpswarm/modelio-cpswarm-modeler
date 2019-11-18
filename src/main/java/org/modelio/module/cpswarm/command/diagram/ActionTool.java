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
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.utils.CPSwarmFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("abe25325-4257-4b8c-b5ec-8a15f75decb6")
public class ActionTool extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("c38e0633-5c27-4688-bd87-669fbbce22f7")
    public ActionTool() {
        super();
    }

    @objid ("69861850-ba4a-475a-a700-b38fee7b596e")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        if (element instanceof AbstractDiagram) {
            element = ((AbstractDiagram) element).getOrigin();
        }
        return  (element instanceof StateMachine);
    }

    @objid ("91fe7691-253d-4570-8c58-2d5a50f7407f")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction ("Create Action")){
        
            MObject element = target.getElement();
            if (element instanceof AbstractDiagram) {
                element = ((AbstractDiagram) element).getOrigin();
            }
        
            if (element instanceof StateMachine) {
                State action = CPSwarmFactory.createAction((StateMachine) element);
                List<IDiagramGraphic> graph = representation.unmask (action, rect.x, rect.y);
        
                if((graph != null) &&  (graph.size() > 0) && (graph.get(0) instanceof IDiagramNode))
                    ((IDiagramNode)graph.get(0)).setBounds(rect);
        
                representation.save();
                representation.close();
            }
            transaction.commit ();
        }
    }

}
