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
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.utils.CPSwarmFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("33f6b1d2-64d2-47d9-845a-dc4f6e7c01c1")
public class SwarmMemberTool extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("c78164a3-9674-4ec5-9127-07a58e8df6b4")
    public SwarmMemberTool() {
        super();
    }

    @objid ("e0f44ec7-d962-4409-9419-71a699cd6760")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        if (element instanceof AbstractDiagram) {
            element = ((AbstractDiagram) element).getOrigin();
        }
        return  (element instanceof org.modelio.metamodel.uml.statik.Package);
    }

    @objid ("f08b6227-e75f-4216-8205-9469d56cddcc")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction ("Create Swarm member")){
        
            MObject element = target.getElement();
            if (element instanceof AbstractDiagram) {
                element = ((AbstractDiagram) element).getOrigin();
            }
        
            if (element instanceof org.modelio.metamodel.uml.statik.Package) {
                org.modelio.metamodel.uml.statik.Class action = CPSwarmFactory.createSwarmMember((org.modelio.metamodel.uml.statik.Package) element);
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
