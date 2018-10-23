package org.modelio.module.cpswarm.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.api.CPSWarmStereotypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.module.cpswarm.i18n.I18nMessageService;
import org.modelio.module.cpswarm.impl.CPSWarmModule;

@objid ("3c680331-e84f-4121-8525-67a9d69628e0")
public class CPSwarmFactory {
    @objid ("d9e2afda-73da-49b7-88b9-bd06c402f053")
    private static final String MODELERMODULE_NAME = "ModelerModule";

    @objid ("495c7577-f018-42f5-9542-bff71469c8d3")
    private static final String DESCRIPTION = "description";

    @objid ("eab17815-d3a0-4e0b-bde1-8aade4fe20d1")
    public static StaticDiagram createEnvironmentDefinitionDiagram(ModelElement owner) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        try{
            StaticDiagram diagram = model.createStaticDiagram("", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.ENVIRONMENT_DEFINITION);
            session.getModel().getDefaultNameService().setDefaultName(diagram, I18nMessageService.getString("Ui.Name.EnvironmentDefinition", owner.getName()));
        
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("a809cdc1-d368-493d-8390-b5e817d8715b")
    public static StaticDiagram createEnvironmentDefinitionDiagram(ModelElement owner, String name, String description) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        try{
            StaticDiagram diagram = model.createStaticDiagram("", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.ENVIRONMENT_DEFINITION);
            session.getModel().getDefaultNameService().setDefaultName(diagram, name);
            session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram,description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("43504223-85b6-4780-98cd-6eac232b9c15")
    public static StaticDiagram createFitnessDefinitionDiagram(ModelElement owner, String name, String description) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        try{
            StaticDiagram diagram = model.createStaticDiagram("", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.FITNESS_DEFINITION);
            session.getModel().getDefaultNameService().setDefaultName(diagram, I18nMessageService.getString("Ui.Name.FitnessDefinition", owner.getName()));
            session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram,description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("6b8c5ac6-baef-4041-adbc-83cfdda25d2e")
    public static StaticDiagram createProblemStatementDiagram(ModelElement owner, String name, String description) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        try{
            StaticDiagram diagram = model.createStaticDiagram("", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.PROBLEM_STATEMENT);
            session.getModel().getDefaultNameService().setDefaultName(diagram, I18nMessageService.getString("Ui.Name.ProblemStatement", owner.getName()));
            session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram,description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("7a14cfbe-4ddc-40e9-8721-0fb880c89c79")
    public static StaticDiagram createSwarmMemberArchitectureDiagram(ModelElement owner, String name, String description) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        try{
            StaticDiagram diagram = model.createStaticDiagram("", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARMMEMBER_ARCHITECTURE);
            session.getModel().getDefaultNameService().setDefaultName(diagram, I18nMessageService.getString("Ui.Name.SwarmMemberArchitecture", owner.getName()));
            session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram,description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("ff59620e-ee2d-472f-bd13-85edb401a080")
    public static StaticDiagram createSwarmDefinitionDiagram(ModelElement owner, String name, String description) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        try{
            StaticDiagram diagram = model.createStaticDiagram("", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARM_DEFINITION);
            session.getModel().getDefaultNameService().setDefaultName(diagram, I18nMessageService.getString("Ui.Name.SwarmDefinition", owner.getName()));
            session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram,description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

}
